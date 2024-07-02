package pl.bryczek.creditcard;

import org.junit.jupiter.api.Test;
import pl.bryczek.ecommerce.catalog.ArrayListProductStorage;
import pl.bryczek.ecommerce.catalog.Product;
import pl.bryczek.ecommerce.catalog.ProductCatalog;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {

    @Test
    void itListProducts() {
        ProductCatalog catalog = getProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    private static ProductCatalog createProductCatalog(){
        ProductCatalog catalog = getProductCatalog();
        return catalog;
    }

    private static ProductCatalog getProductCatalog() {
        ProductCatalog catalog = new ProductCatalog(new ArrayListProductStorage());
        return catalog;
    }


    @Test
    void itAllowsToAddProduct() {
        ProductCatalog catalog = getProductCatalog();

        catalog.addProduct("Lego set 8083", "Nice one");
        List<Product> products = catalog.allProducts();

        assertThat(products)
                .hasSize(1);
    }

    @Test
    void itLoadsSingleProductById() {
        ProductCatalog catalog = getProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one");

        Product loaded = catalog.getProductBy(id);

        assertThat(id).isEqualTo(loaded.getID());
    }

    @Test
    void itAllowsChangePrice() {
        ProductCatalog catalog = getProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }

    private static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(new ArrayListProductStorage());
    }
}