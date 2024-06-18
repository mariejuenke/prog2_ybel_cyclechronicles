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
        order = spy(Order.class);
    }

    // test Shop#accept
    @Test
    public void acceptEBike(){
        doReturn(EBIKE).when(order).getBicycleType();
        assertFalse(shop.accept(order));
    }

    @Test
    public void acceptGravelBike(){
        doReturn(GRAVEL).when(order).getBicycleType();
        assertFalse(shop.accept(order));
    }

    @Test
    public void acceptRaceBike(){
        doReturn(RACE).when(order).getBicycleType();
        assertTrue(shop.accept(order));
    }
    @Test
    public void acceptFixieBike(){
        doReturn(FIXIE).when(order).getBicycleType();
        assertTrue(shop.accept(order));
    }
    @Test
    public void acceptSingleSpeedBike(){
        doReturn(SINGLE_SPEED).when(order).getBicycleType();
        assertTrue(shop.accept(order));
    }

    @Test
    public void acceptTwoOrdersFromSameCustomer(){
        doReturn(RACE).when(order).getBicycleType();
        doReturn("Hans").when(order).getCustomer();
        shop.accept(order);
        Order o1 = spy(Order.class);
        doReturn(FIXIE).when(o1).getBicycleType();
        doReturn("Hans").when(o1).getCustomer();
        assertFalse(shop.accept(o1));
    }

    @Test
    public void acceptMaxOrders(){
        doReturn(RACE).when(order).getBicycleType();
        doReturn("Hans").when(order).getCustomer();
        shop.accept(order);
        Order o1 = spy(Order.class);
        doReturn(RACE).when(o1).getBicycleType();
        doReturn("Frank").when(o1).getCustomer();
        shop.accept(o1);
        Order o2 = spy(Order.class);
        doReturn("Alex").when(o2).getCustomer();
        doReturn(RACE).when(o2).getBicycleType();
        shop.accept(o2);
        Order o3 = spy(Order.class);
        doReturn("Mario").when(o3).getCustomer();
        doReturn(RACE).when(o3).getBicycleType();
        shop.accept(o3);
        Order o4 = spy(Order.class);
        doReturn(RACE).when(o4).getBicycleType();
        doReturn("Gustav").when(o4).getCustomer();
        assertTrue(shop.accept(o4));
    }

    @Test
    public void acceptOverMaxOrders(){
        doReturn(RACE).when(order).getBicycleType();
        doReturn("Hans").when(order).getCustomer();
        shop.accept(order);
        Order o1 = spy(Order.class);
        doReturn(RACE).when(o1).getBicycleType();
        doReturn("Frank").when(o1).getCustomer();
        shop.accept(o1);
        Order o2 = spy(Order.class);
        doReturn("Alex").when(o2).getCustomer();
        doReturn(RACE).when(o2).getBicycleType();
        shop.accept(o2);
        Order o3 = spy(Order.class);
        doReturn("Mario").when(o3).getCustomer();
        doReturn(RACE).when(o3).getBicycleType();
        shop.accept(o3);
        Order o4 = spy(Order.class);
        doReturn(RACE).when(o4).getBicycleType();
        doReturn("Gustav").when(o4).getCustomer();
        shop.accept(o4);
        Order o5 = spy(Order.class);
        doReturn(RACE).when(o5).getBicycleType();
        doReturn("Maik").when(o5).getCustomer();
        assertFalse(shop.accept(o5));
    }

    @Test
    public void acceptOrdersFromDifferentCostumers() {
        doReturn(RACE).when(order).getBicycleType();
        doReturn("Hans").when(order).getCustomer();
        shop.accept(order);
        Order o1 = spy(Order.class);
        doReturn(RACE).when(o1).getBicycleType();
        doReturn("Frank").when(o1).getCustomer();
        assertTrue(shop.accept(o1));
    }
}
