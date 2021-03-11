package com.fpinjava.introduction.listing01_04;

import com.fpinjava.common.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DonutShopTest {


    @Test
    public void testBuyDonuts() {
        CreditCard creditCard = new CreditCard();
        Tuple<Donut, Payment> purchase1 = DonutShop.buyDonut(creditCard);
        Tuple<Donut, Payment> purchase2 = DonutShop.buyDonut(creditCard);
        Payment combinedPayments = purchase1._2.combine(purchase2._2);
        assertEquals(Donut.price * 2, combinedPayments.amount);
        assertEquals(creditCard, combinedPayments.creditCard);
    }

}
