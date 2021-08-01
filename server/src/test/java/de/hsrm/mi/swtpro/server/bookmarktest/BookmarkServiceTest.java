//package de.hsrm.mi.swtpro.server.bookmarktest;


//@SpringBootTest
//public class BookmarkService{
//    private final int ID = 1;
//    Product P1 = new Product(1,"Bananen", 3, "Obst", 1.29, "Wiesbaden", "Germany", 1);
//    Product P2 = new Product(2,"Gurken", 4, "Gemüse", 2.29, "Mainz", "Deutschland", 2);

//    @Autowired
//    BookmarkService bookmarkService;


//    @Test
//    @DisplayName("BookmarkService: getQuantity()")
//    @Transactional
//    public void bookmark_getQuantity() {
//        orderService.addbookmark(ID, P1);
//        orderService.addbookmark(ID, P2);

//        int total = P1.getAmountCart() + P2.getAmountCart();
//        assertThat(total == bookmarkService.getQuantity()).isTrue();

//    }
//}