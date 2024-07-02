package pl.bryczek.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    Product() {

    }

    public Product(UUID id, String name, String description) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
    }

    public String getID() {
        return id;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }



    public void changePrice(BigDecimal newPrice) {

        this.price = newPrice;
    }

}

