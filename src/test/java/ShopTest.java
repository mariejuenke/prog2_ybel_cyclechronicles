import cyclechronicles.Shop;
import org.junit.jupiter.api.*;
import cyclechronicles.Order;
import static cyclechronicles.Type.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class ShopTest {
    Shop shop; Order order;
    @BeforeEach
    public void setUp(){
        shop = new Shop();
        order = spy(new Order(RACE, "Hans"));
    }

    // test Shop#accept
    @Test
    public void acceptEBike(){
        doReturn(EBIKE).when(order).bicycleType();
        assertFalse(shop.accept(order));
    }

    @Test
    public void acceptGravelBike(){
        doReturn(GRAVEL).when(order).bicycleType();
        assertFalse(shop.accept(order));
    }

    @Test
    public void acceptRaceBike(){
        doReturn(RACE).when(order).bicycleType();
        assertTrue(shop.accept(order));
    }
    @Test
    public void acceptFixieBike(){
        doReturn(FIXIE).when(order).bicycleType();
        assertTrue(shop.accept(order));
    }
    @Test
    public void acceptSingleSpeedBike(){
        doReturn(SINGLE_SPEED).when(order).bicycleType();
        assertTrue(shop.accept(order));
    }

    @Test
    public void acceptTwoOrdersFromSameCustomer(){
        doReturn(RACE).when(order).bicycleType();
        doReturn("Hans").when(order).customer();
        shop.accept(order);
        Order o1 = spy(new Order(FIXIE, "Hans"));
        assertFalse(shop.accept(o1));
    }

    @Test
    public void acceptMaxOrders(){
        doReturn(RACE).when(order).bicycleType();
        doReturn("Hans").when(order).customer();
        shop.accept(order);

        Order o1 = spy(new Order(RACE, "Frank"));
        shop.accept(o1);

        Order o2 =spy(new Order(FIXIE, "Alex"));
        shop.accept(o2);

        Order o3 = spy(new Order(FIXIE, "Mario"));
        shop.accept(o3);

        Order o4 = spy(new Order(FIXIE, "Gustav"));
        assertTrue(shop.accept(o4));
    }

    @Test
    public void acceptOverMaxOrders(){
        doReturn(RACE).when(order).bicycleType();
        doReturn("Hans").when(order).customer();
        shop.accept(order);
        Order o1 = spy(new Order(RACE, "Frank"));
        shop.accept(o1);

        Order o2 = spy(new Order(RACE, "Alex"));
        shop.accept(o2);

        Order o3 = spy(new Order(RACE, "Mario"));
        shop.accept(o3);

        Order o4 = spy(new Order(RACE, "Gustav"));
        shop.accept(o4);

        Order o5 = spy(new Order(RACE, "Maik"));
        assertFalse(shop.accept(o5));
    }

    @Test
    public void acceptOrdersFromDifferentCostumers() {
        doReturn(RACE).when(order).bicycleType();
        doReturn("Hans").when(order).customer();
        shop.accept(order);

        Order o1 = spy(new Order(RACE, "Frank"));
        assertTrue(shop.accept(o1));
    }
}
