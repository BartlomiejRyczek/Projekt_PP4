package pl.bryczek.ecommerce.Sales;

import org.springframework.web.bind.annotation.*;
import pl.bryczek.ecommerce.Sales.Offer;
import pl.bryczek.ecommerce.Sales.AcceptOfferRequest;
import pl.bryczek.ecommerce.Sales.ReservationDetails;

@RestController
public class SalesController {
    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable(name = "productId") String productId) {
        var customerId = getCurrentCustomerId();
        sales.addProduct(customerId, productId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        var customerId = getCurrentCustomerId();
        return sales.acceptOffer(customerId, acceptOfferRequest);
    }
    @GetMapping("/api/current-offer")
    Offer getCurrentOffer() {
        var customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    private String getCurrentCustomerId() {
        return "guest";
    }



}
