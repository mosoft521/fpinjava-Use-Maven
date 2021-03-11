package com.fpinjava.introduction.listing01_01;

import org.junit.Test;

import static com.fpinjava.introduction.listing01_01.DonutShop.buyDonut;
import static org.junit.Assert.assertEquals;

public class DonutShopTest {

    @Test
    public void testBuyCoffee() {
        CreditCard crediCard = new CreditCard();
        buyDonut(crediCard);
        buyDonut(crediCard);
        assertEquals(Donut.price * 2, crediCard.getTotal());
    }

}
