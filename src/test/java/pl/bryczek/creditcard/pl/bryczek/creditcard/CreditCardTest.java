package pl.bryczek.creditcard.pl.bryczek.creditcard;

import org.junit.jupiter.api.Test;
import pl.bryczek.creditcard.CreditBelowThresholdException;
import pl.bryczek.creditcard.CreditCantBeReassignException;
import pl.bryczek.creditcard.CreditCard;
import pl.bryczek.creditcard.InsufficientFoundsException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;

public class CreditCardTest {
    @Test
    void itAllowsToAssignCredit(){
        //Arrange
        CreditCard card = new CreditCard();

        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        //Assert
        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }
    @Test
    void itAllowsToAssignCreditv2(){
        //Arrange
        CreditCard card = new CreditCard();

        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1200));
        //Assert
        assert BigDecimal.valueOf(1200).equals(card.getBalance());
    }
    @Test
    void itDenyCreditBelowThreshold(){
        CreditCard card = new CreditCard();

        try {
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }
    }
    @Test
    void itDenyCreditBelowThresholdv1(){
        CreditCard card = new CreditCard();
        //python /lambda x: x*x
        //java /(x)-> x*x

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(50))
        );
        try {
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }
    }
    @Test
    void itDenyCreditReassginment(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditCantBeReassignException.class,
                ()-> card.assignCreditLimit(BigDecimal.valueOf(1200))
        );
    }
    @Test
    void payForSomething(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        card.pay(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(900),
                card.getBalance()
        );
    }

    @Test
    void itDenyPaymentWhenNotEnoughMoney(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        card.pay(BigDecimal.valueOf(900));

        assertThrows(
                InsufficientFoundsException.class,
                ()-> card.pay(BigDecimal.valueOf(200))
        );
    }
}
