package pl.bryczek.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.bryczek.ecommerce.Sales.SalesFacade;
import pl.bryczek.ecommerce.catalog.ArrayListProductStorage;
import pl.bryczek.ecommerce.catalog.ProductCatalog;
import pl.bryczek.ecommerce.Sales.HashMapCartStorage;
import pl.bryczek.ecommerce.Sales.OfferCalculator;
import pl.bryczek.ecommerce.Sales.PaymentDetails;
import pl.bryczek.ecommerce.Sales.PaymentGateway;
import pl.bryczek.ecommerce.Sales.RegisterPaymentRequest;
import pl.bryczek.ecommerce.Sales.ReservationRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main (String[] args){
        System.out.println("Czesc");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        var pid1 = catalog.addProduct("Lego set 8083", "nice one");
        catalog.changePrice(pid1, BigDecimal.valueOf(100.10));

        var pid2 = catalog.addProduct("Cobi set 8083", "nice one");
        catalog.changePrice(pid2, BigDecimal.valueOf(50.10));

        return catalog;
    }

    @Bean
    SalesFacade createSales() {
        return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new PaymentGateway() {
                    @Override
                    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
                        return null;
                    }
                },
                new ReservationRepository()
        );
    }
}
