package uy.edu.ort.devops.paymentsserviceexample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import uy.edu.ort.devops.paymentsserviceexample.domain.PaymentStatus;
import uy.edu.ort.devops.paymentsserviceexample.logic.PaymentsLogic;

public class PaymentsServiceExampleApplicationTests {

    @InjectMocks
    private PaymentsLogic paymentsLogic;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPay_successfulPayment() {
        boolean successOccurred = false;

        for (int i = 0; i < 100; i++) { // Ejecutar múltiples iteraciones para cubrir ambos resultados posibles
            PaymentStatus result = paymentsLogic.pay("order1");

            if (result.isSuccess()) {
                successOccurred = true;
                assertEquals("Done.", result.getDescription());
                break;
            }
        }

        assertTrue(successOccurred, "Successful payment should occur at least once");
    }

    @Test
    public void testPay_failedPayment() {
        boolean failureOccurred = false;

        for (int i = 0; i < 100; i++) { // Ejecutar múltiples iteraciones para cubrir ambos resultados posibles
            PaymentStatus result = paymentsLogic.pay("order1");

            if (!result.isSuccess()) {
                failureOccurred = true;
                assertEquals("No money.", result.getDescription());
                break;
            }
        }

        assertTrue(failureOccurred, "Failed payment should occur at least once");
    }

}
