package com.fpinjava.introduction.listing01_02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DonutShopTest {

    @Test
    public void testBuyDonut() {
        CreditCard creditCard = new CreditCard();
        Tuple<Donut, Payment> purchase = DonutShop.buyDonut(creditCard);
        assertNotNull(purchase._1);
        assertEquals(Donut.price, purchase._2.amount);
        assertEquals(creditCard, purchase._2.creditCard);
    }
}
