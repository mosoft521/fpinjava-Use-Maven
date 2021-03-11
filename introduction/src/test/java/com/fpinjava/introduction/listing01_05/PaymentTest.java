package com.fpinjava.introduction.listing01_05;

import com.fpinjava.common.List;
import com.fpinjava.common.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentTest {

    @Test
    public void testBuyDonuts() {
        CreditCard creditCard = new CreditCard();
        Tuple<List<Donut>, Payment> purchase = DonutShop.buyDonuts(5, creditCard);
        assertEquals(Donut.price * 5, purchase._2.amount);
        assertEquals(creditCard, purchase._2.creditCard);
    }
}
