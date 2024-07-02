package pl.bryczek.ecommerce.Sales;
import pl.bryczek.ecommerce.Sales.Cart;
import pl.bryczek.ecommerce.Sales.HashMapCartStorage;
import pl.bryczek.ecommerce.Sales.Offer;
import pl.bryczek.ecommerce.Sales.OfferCalculator;
import pl.bryczek.ecommerce.Sales.PaymentDetails;
import pl.bryczek.ecommerce.Sales.PaymentGateway;
import pl.bryczek.ecommerce.Sales.RegisterPaymentRequest;
import pl.bryczek.ecommerce.Sales.AcceptOfferRequest;
import pl.bryczek.ecommerce.Sales.Reservation;
import pl.bryczek.ecommerce.Sales.ReservationDetails;
import pl.bryczek.ecommerce.Sales.ReservationRepository;

import java.util.UUID;
public class SalesFacade {
    private HashMapCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(HashMapCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRespository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRespository;
    }

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public void addProduct(String customerId, String productId) {
        Cart cart = getCartForCustomer(customerId);

        cart.add(productId);

    }

    private Cart getCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );

        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());
    }
}

