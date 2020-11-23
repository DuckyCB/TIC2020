package uy.edu.um.tic1.entitites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.contact.AddressDTO;
import uy.edu.um.tic1.entitites.contact.TelephoneNumberDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDTO {


    private Integer id;


    private AddressDTO address;


    private TelephoneNumberDTO telephoneNumber;

    private Set<BrandDTO> brandSet;

    private Set<StockDTO> stockSet;

    private Set<PurchaseDTO> purchaseSet;

    public void updateStock(ProductDTO product, String size, String color, Integer stock) {
        Boolean updated = false;
        this.stockSet.stream().forEach(st -> {
            SizeAndColorDTO sc = st.getSizeAndColor();
            if (st.getProduct().equals(product) && sc.getSize().equals(size) && sc.getColor().equals(color))
                st.setStock(stock);

        });

    }

    public void addStock(StockDTO stock){
        this.getStockSet().add(stock);
    }

    public void deleteStock(StockDTO stock) {
        stockSet.remove(stock);
    }

    public void addBrand(BrandDTO brand) {
        brandSet.add(brand);
    }
    public void deleteBrand(BrandDTO brand) {
        brandSet.remove(brand);
    }



    public StockDTO getStockBySizeAndColor(ProductDTO product, String size, String color){
        List<StockDTO> stocks = this.stockSet.stream().filter(st -> {
            SizeAndColorDTO sc = st.getSizeAndColor();
            if (st.getProduct().equals(product) &&
                    sc.getSize().equals(size) && sc.getColor().equals(color))
                return true;
            return false;
        }).collect(Collectors.toList());

        if(!stocks.isEmpty()){
            return stocks.get(0);
        }
        return null;


    }


    public void deliverPurchase(Integer id){
        this.purchaseSet.stream().forEach(p -> {
            if(p.getId().equals(id)){
                p.setDelivered(true);
                p.setDeliveryDate(LocalDate.now());
                p.setDeliveryTime(LocalTime.now());
            }
        });
    }
}