package uy.edu.um.tic1.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import uy.edu.um.tic1.controllers.StoreController;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.cart.Purchase;
import uy.edu.um.tic1.entities.contact.Address;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.StoreDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

//    @Autowired
//    private static StoreController storeController;


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Embedded
    private Address address;

    @Embedded
    private Email email;

    @Embedded
    @Column(unique = true)
    private TelephoneNumber telephoneNumber;

    @ManyToMany
        @JoinTable(name = "store_brand",
            joinColumns = @JoinColumn(name = "store_id"),
            foreignKey = @ForeignKey(name = "fk_storebrand_store"),
            inverseJoinColumns = @JoinColumn(name = "brand_id"),
            inverseForeignKey = @ForeignKey(name = "fk_storebrand_brand"))
    private Set<Brand> brandSet;




    @Lazy
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "store",
                foreignKey = @ForeignKey(name = "fk_stock_store")
        )
    private Set<Stock> stockSet;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "store",
            foreignKey = @ForeignKey(name = "fk_purchase_store")
    )
    private Set<Purchase> purchaseSet;



    public void updateStock(Product product, Integer quantity) {

        stockSet.stream().forEach(stock -> {
            if (stock.getProduct().equals(product)){
                stock.setStock(stock.getStock()+quantity);
            }
        });


    }

    public void deleteStock(Stock stock) {
        stockSet.remove(stock);
    }

    public void addBrand(Brand brand) {
        brandSet.add(brand);
    }
    public void deleteBrand(Brand brand) {
        brandSet.remove(brand);
    }

    public StoreDTO toDTO() {
        return StoreDTO.builder()
                .id(this.id)
                .address(this.address.toDTO())
                .brandSet(this.brandSet.stream().map(Brand::toDTO).collect(Collectors.toSet()))
                .stockSet(this.stockSet.stream().map(Stock::toDTO).collect(Collectors.toSet()))
                .purchaseSet(this.purchaseSet.stream().map(Purchase::toDTO).collect(Collectors.toSet()))
                .telephoneNumber(this.telephoneNumber.toDTO())
                .build();
    }

    public void addPurchase(Purchase purchase) {

        purchase.getPurchaseItems().stream().forEach(purchaseItem -> {
            updateStock(purchaseItem.getProduct(), - purchaseItem.getQuantity());
        });
        this.purchaseSet.add(purchase);
        //autoSave();
    }

    public List<Purchase> getUndeliveredPurchases(){
        return this.purchaseSet.stream().filter(purchase -> !purchase.getDelivered()).collect(Collectors.toList());
    }


    public void addStock(Stock stock) {
        stockSet.add(stock);
    }

//    public void autoSave(){
//        this.storeController.save(this);
//    }
}
