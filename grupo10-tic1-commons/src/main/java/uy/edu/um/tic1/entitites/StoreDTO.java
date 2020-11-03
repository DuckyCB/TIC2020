package uy.edu.um.tic1.entitites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.contact.AddressDTO;
import uy.edu.um.tic1.entitites.contact.TelephoneNumberDTO;

import java.util.Set;

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


    public void updateStock(StockDTO stock) {

        stockSet.remove(stock);
        stockSet.add(stock);


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
}