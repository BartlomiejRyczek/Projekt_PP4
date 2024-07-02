package pl.bryczek.ecommerce.Sales;
import pl.bryczek.ecommerce.Sales.PaymentDetails;
import pl.bryczek.ecommerce.Sales.PaymentGateway;
import pl.bryczek.ecommerce.Sales.RegisterPaymentRequest;


public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;

    public Integer getRequestsCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}