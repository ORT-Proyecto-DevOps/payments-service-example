package uy.edu.ort.devops.paymentsserviceexample;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import uy.edu.ort.devops.paymentsserviceexample.domain.PaymentStatus;
import uy.edu.ort.devops.paymentsserviceexample.logic.PaymentsLogic;

public class PaymentsServiceExampleApplicationTests {

    @InjectMocks
    private PaymentsLogic paymentsLogic;

    @Mock
    private Random random;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPay_successfulPayment() {
        // Mocking random to always return 0 (successful payment)
        when(random.nextInt(anyInt())).thenReturn(0);

        PaymentStatus result = paymentsLogic.pay("order1");

        assertEquals("order1", result.getOrderId());
        assertEquals(true, result.isSuccess());
        assertEquals("Done.", result.getDescription());
    }

    @Test
    public void testPay_failedPayment() {
        // Mocking random to always return 1 (failed payment)
        when(random.nextInt(anyInt())).thenReturn(1);

        PaymentStatus result = paymentsLogic.pay("order1");

        assertEquals("order1", result.getOrderId());
        assertEquals(false, result.isSuccess());
        assertEquals("No money.", result.getDescription());

    }

}
