package pl.bryczek.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProductCatalogTest {

        @Test
        void itListsProducts() {
            ProductCatalog catalog = getProductCatalog();

            List<Product> productList = catalog.allProducts();

            assert productList.isEmpty();
            assertThat(productList).hasSize(0);

        }

    private static ProductCatalog getProductCatalog() {
        ProductCatalog catalog = new ProductCatalog(new ArrayListProductStorage());
        return catalog;
    }


    @Test
        void itAllowsToAddProducts() {
        ProductCatalog catalog = getProductCatalog();

        catalog.addProduct("Lego set 8083", "Nice one");
            List<Product> productList = catalog.allProducts();
            assertThat(productList).hasSize(1);

        }

        @Test
        void itLoadsProductDetails() {
            ProductCatalog catalog = getProductCatalog();
            String id = catalog.addProduct("Lego set 8083", "Nice one");
            Product loadedProduct = catalog.getProductBy(id);
            assertThat(id).isEqualTo(loadedProduct.getID());
        }

        @Test
        void itAllowsToChangePrice(){
            ProductCatalog catalog = getProductCatalog();
            String id= catalog.addProduct("Lego set 8083", "Nice one");
            catalog.changePrice(id, BigDecimal.valueOf(10.10));
            Product loadedProduct = catalog.getProductBy(id);
            assertThat(BigDecimal.valueOf(10.10))
                    .isEqualTo(loadedProduct.getPrice());
        }
}
