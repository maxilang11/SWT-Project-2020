//package de.hsrm.mi.swtpro.server.shoppingCartTest;

//import static org.assertj.core.api.Assertions.assertThat;
//import javax.transaction.Transactional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import de.hsrm.mi.swtpro.server.services.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import de.hsrm.mi.swtpro.server.models.Product;



//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//public class OrderServiceTest {

//     private final int ID = 1;
//     Product P1 = new Product(1,"Bananen", 3, "Obst", 1.29, "Wiesbaden", "Germany", 1);
//     Product P2 = new Product(2,"Gurken", 4, "Gemüse", 2.29, "Mainz", "Deutschland", 2);

//     @Autowired
//     OrderService orderService;


//     @Test
//     @DisplayName("OrderService: getQuantity()")
//     @Transactional
//     public void order_getQuantity() {
//         orderService.addOrder(ADMINADMIN, P1);
//         orderService.addOrder(ADMINADMIN, P2);

//         int total = P1.getAmountCart() + P2.getAmountCart();
//         assertThat(total == orderService.getQuantity()).isTrue();

//     }




