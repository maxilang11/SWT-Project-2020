//package de.hsrm.mi.swtpro.server.ordertest;

//import de.hsrm.mi.swtpro.server.models.Order;

//@SpringBootTest
//public class OrderReproTest{

//        private final int ID = 1;
//        Product P1 = new Product(1,"Bananen", 3, "Obst", 1.29, "Wiesbaden", "Germany", 1);
//        Product P2 = new Product(2,"Gurken", 4, "Gemüse", 2.29, "Mainz", "Deutschland", 2);
//        private final List<Product> PRODUCTLIST = new ArrayList<Product>(Arrays.asList(P1, P2));


//        @Autowired
//        private OrderRepository orderRepo;

//        @Test
//        public void vorabcheck(){
//            assertThat(OrderRepository.class).isInterface();
//            assertThat(OrderReproTest).isNotNull();
//        }

//        @Test
//        @DisplayName("OrderRepro findByID")
//        public void cartFindById() {
//         final int ANZAHL = 5;
//
//        shoppingCartRepo.deleteAll();

//            for (int i=0; i < ANZAHL; i++) {
//             final Order u1 = new Order();
//             u1.setId(ID+i);
//             u1.setProductList(productList);
//             orderRepo.save(u1);
//         }
//         assertThat(orderRepo.count()).isEqualTo(ANZAHL);

//         for (int n=0; n < ANZAHL; n++) {
//             Order order = orderRepo.findById(ID+n);
//             assertThat(order.getID() == ID+n).isTrue();
//         }
//     }




//}