package de.hsrm.mi.swtpro.server.batch;

import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import de.hsrm.mi.swtpro.server.models.*;
import de.hsrm.mi.swtpro.server.repository.*;
/**
 * Zum Testen wird die Datenbank mit "Dummydaten gefüllt". Durch implemtentieren des
 * CommandLineRunner werden die Daten automatisch beim Start des Servers in die
 * Datenbank gepackt. Da die Daten jedes mal in die DB gepackt werden, werden Sie am
 * Ende gelöscht.
 */
@Component
public class DBDataInputer implements CommandLineRunner {
    private RoleRepository roleRepository;
    private ProductRepository productRepository;
    private ShoppingCartRepository shoppingcartRepository;
    private UserRepository userRepository;
    private BookmarkRepository bookmarkRepository;
    /**
     * @param productRepository
     * @param userRepository
     * @param shoppingcartRepository
     * @param bookmarkRepository
     * @param roleRepository
     * @return
     */
    public DBDataInputer(ProductRepository productRepository, ShoppingCartRepository shoppingcartRepository, UserRepository userRepository, BookmarkRepository bookmarkRepository, RoleRepository roleRepository){
        this.productRepository = productRepository;
        this.shoppingcartRepository = shoppingcartRepository;
        this.userRepository = userRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.roleRepository = roleRepository;
    }
    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(final String... args) throws Exception {
        /**
         * Produkte zum testen der Datenbank hinzufuegen
         */
        Product p1 = new Product(1,"Bananen", 50, "Obst", 0.98, "Machala", "Ecuador");
        Product p2 = new Product(2,"Trauben", 30, "Obst", 2.49, "Ica", "Peru");
        Product p3 = new Product(3,"Äpfel", 50, "Obst", 2.99, "Rio Negro", "Argentinien");
        Product p4 = new Product(4,"Orangen", 20, "Obst", 2.79, "Valencia", "Spanien");
        Product p5 = new Product(5,"Kiwi",25, "Obst", 0.29, "Latium", "Italien");
        Product p6 = new Product(6,"Erdbeeren", 20, "Obst", 1.75, "Waikato", "Neuseeland");
        Product p7 = new Product(7,"Mango", 30, "Obst", 1.49, "Belem", "Brasilien");
        Product p8 = new Product(8,"Birne", 50, "Obst", 0.67, "Valencia", "Spanien");
        Product p9 = new Product(9,"Heidelbeeren", 35, "Obst", 1.29, "Ica", "Peru");
        Product p10 = new Product(10,"Zitrone", 45, "Obst", 0.65, "Sizilien", "Italien");

        Product p11 = new Product(11,"Gurke", 45, "Gemüse", 0.69, "Patras", "Griechenland");
        Product p12 = new Product(12,"Paprika", 40, "Gemüse", 0.79, "Kapstadt", "Südafrika");
        Product p13 = new Product(13,"Salat", 35, "Gemüse", 2.19, "Almeria", "Spanien");
        Product p14 = new Product(14,"Zwiebeln", 30, "Gemüse", 0.85, "Niedersachsen", "Deutschland");
        Product p15 = new Product(15,"Karotten", 35, "Gemüse", 1.29, "Nordrhein-Westfalen", "Deutschland");
        Product p16 = new Product(16,"Tomate", 50, "Gemüse", 0.24, "Süd-Holland", "Niederlande");
        Product p17 = new Product(17,"Kartoffeln", 30, "Gemüse", 1.59, "Niedersachsen", "Deutschland");
        Product p18 = new Product(18,"Zucchini", 25, "Gemüse", 0.99, "Almeria", "Spanien");
        Product p19 = new Product(19,"Broccoli", 20, "Gemüse", 1.65, "Apulien", "Spanien");
        Product p20 = new Product(20,"Champignons", 30, "Gemüse", 1.59, "Podlachien ", "Polen");

        Product p21 = new Product(21,"Gummibärchen", 45, "Süßigkeiten", 0.96, "Bonn", "Deutschland");
        Product p22 = new Product(22,"Schokoriegel", 50, "Süßigkeiten", 1.69, "Stuttgart", "Deutschland");
        Product p23 = new Product(23,"Schokoladentafel", 50, "Süßigkeiten", 1.25, "Stuttgart", "Deutschland");
        Product p24 = new Product(24,"Chips", 35, "Süßigkeiten", 1.29, "Petersau", "Deutschland");
        Product p25 = new Product(25,"Pistazien", 30, "Süßigkeiten", 3.49, "Sauerland", "Deutschland");
        Product p26 = new Product(26,"Erdnüsse", 40, "Süßigkeiten", 1.89, "Sauerland", "Deutschland");
        Product p27 = new Product(27,"Cookies", 40, "Süßigkeiten", 1.85, "Barsinghausen", "Deutschland");

        Product p28 = new Product(28,"Spaghetti", 55, "Teigwaren", 1.43, "Parma", "Deutschland");
        Product p29 = new Product(29,"Tortelloni", 40, "Teigwaren", 2.69, "Mannheim", "Deutschland");
        Product p30 = new Product(30,"Cannelloni", 55, "Teigwaren", 2.19, "Parma", "Deutschland");
        Product p31 = new Product(31,"Penne", 55, "Teigwaren", 1.65, "Mannheim", "Deutschland");
        Product p32 = new Product(32,"Spirelli", 55, "Teigwaren", 1.99, "Parma", "Deutschland");

        Product p33 = new Product(33,"Rinderhackfleisch", 40, "Fleisch", 3.98, "Brandenburg", "Deutschland");
        Product p34 = new Product(34,"Hähnchenbrustfilet", 40, "Fleisch", 3.92, "Brandenburg", "Deutschland");
        Product p35 = new Product(35,"Hamburger", 35, "Fleisch", 4.99, "Rietberg", "Deutschland");
        Product p36 = new Product(36,"Putenschnitzel", 40, "Fleisch", 3.79, "Wildeshausen", "Deutschland");
        Product p37 = new Product(37,"Bratwurst", 30, "Fleisch", 0.85, "Brandenburg", "Deutschland");
        Product p38 = new Product(38,"Lyoner", 45, "Fleisch", 1.19, "Wildeshausen", "Deutschland");
        Product p39 = new Product(39,"Salami", 45, "Fleisch", 1.09, "Rietberg", "Deutschland");
        Product p40 = new Product(40,"Fleischkäse", 40, "Fleisch", 1.15, "Brandenburg", "Deutschland");

        Product p41 = new Product(41,"Wildlachs", 30, "Fisch", 1.69, "Ihlow", "Deutschland");
        Product p42 = new Product(42,"Räucherlachs", 30, "Fisch", 1.99, "Emsland", "Deutschland");
        Product p43 = new Product(43,"Forellen-Filets", 15, "Fisch", 2.19, "Emsland", "Deutschland");
        Product p44 = new Product(44,"Thunfischsalat", 20, "Fisch", 2.29, "Krefeld", "Deutschland");
        Product p45 = new Product(45,"Seelachsfilet", 25, "Fisch", 2.37, "Münsterland", "Deutschland");

        Product p46 = new Product(46,"Toast", 40, "Backwaren", 1.29, "Dortmund", "Deutschland");
        Product p47 = new Product(47,"Toasties", 35, "Backwaren", 1.29, "Dortmund", "Deutschland");
        Product p48 = new Product(48,"Kürbiskernbrot", 30, "Backwaren", 1.39, "Frankfurt am Main", "Deutschland");
        Product p49 = new Product(49,"Tortillawraps", 40, "Backwaren", 1.15, "Stolberg", "Deutschland");
        Product p50 = new Product(50,"Baguette", 15, "Backwaren", 0.89, "Frankfurt am Main", "Deutschland");
        Product p51 = new Product(51,"Brötchen", 25, "Backwaren", 0.15, "Frankfurt am Main", "Deutschland");
        Product p52 = new Product(52,"Schokomuffins", 20, "Backwaren", 1.59, "Frankfurt am Main", "Deutschland");
        Product p53 = new Product(53,"Donut", 20, "Backwaren", 0.93, "Frankfurt am Main", "Deutschland");
        Product p54 = new Product(54,"Vollkornbrot", 30, "Backwaren", 1.15, "Frankfurt am Main", "Deutschland");
        Product p55 = new Product(55,"Laugenbrötchen", 15, "Backwaren", 0.25, "Frankfurt am Main", "Deutschland");
        Product p56 = new Product(56,"Käsestange", 15, "Backwaren", 0.59, "Frankfurt am Main", "Deutschland");

        Product p57 = new Product(57,"Mineralwasser", 30, "Getränke", 4.49, "Frankfurt am Main", "Deutschland");
        Product p58 = new Product(58,"Hohes-C", 30, "Getränke", 1.79, "Nieder-Olm", "Deutschland");
        Product p59 = new Product(59,"Smoothie", 25, "Getränke", 2.49, "Bonn", "Deutschland");
        Product p60 = new Product(60,"RedBull" , 40, "Getränke", 1.19, "Nüziders", "Österreich");
        Product p61 = new Product(61,"Cola", 45, "Getränke", 1.09, "Berlin", "Deutschland");
        Product p62 = new Product(62,"Fanta", 40, "Getränke", 0.94, "Berlin", "Deutschland");
        Product p63 = new Product(63,"Aperitivo", 15, "Getränke", 10.69, "München", "Deutschland");
        Product p64 = new Product(64,"Baileys", 15, "Getränke", 12.99, "Hamburg", "Deutschland");
        Product p65 = new Product(65,"Vodka", 15, "Getränke", 14.99, "Schonen", "Schweden");
        Product p66 = new Product(66,"Pils", 20, "Getränke",0.79, "Kreuztal", "Deutschland");
        Product p67 = new Product(67,"Sauvignon", 15, "Getränke",5.79, "Rheingau", "Deutschland");
        Product p68 = new Product(68,"Wein", 15, "Getränke",4.99, "Ilbesheim", "Deutschland");

        Product p69 = new Product(69,"Pizza", 25, "Tiefkühl", 2.63, "Nonnweiler", "Deutschland");
        Product p70 = new Product(70,"Pommes", 30, "Tiefkühl", 1.45, "Lille", "Frankreich");
        Product p71 = new Product(71,"Beeren", 25, "Tiefkühl", 1.99, "Hochheim", "Deutschland");
        Product p72 = new Product(72,"Spinat", 20, "Tiefkühl", 1.36, "Ihlow", "Deutschland");
        Product p73 = new Product(73,"Flammkuchen", 20, "Tiefkühl", 2.19, "Nonnweiler", "Deutschland");
        Product p74 = new Product(74,"Gemüsepfanne", 30, "Tiefkühl", 2.54, "Bremerhaven", "Deutschland");
        Product p75 = new Product(75,"Magnum", 25, "Tiefkühl", 3.46, "Hamburg", "Deutschland");
        Product p76 = new Product(76,"Mövenpickeis", 30, "Tiefkühl", 1.99, "Nürnberg", "Deutschland");
        Product p77 = new Product(77, "Capri-Eis", 10, "Tiefkühl", 1.11, "Heppenheim", "Deutschland");
        
        Product p78 = new Product(78,"Waschmaschine", 15, "Angebote", 399.99, "Nauen", "Deutschland");
        Product p79 = new Product(79,"Staubsauger", 20, "Angebote", 129.99, "Hamburg", "Deutschland");
        Product p80 = new Product(80,"Bügeleisen", 30, "Angebote", 59.99, "Gunzenhausen", "Deutschland");
        Product p81 = new Product(81,"Geschirr", 35, "Angebote", 38.99, "Dornbirn", "Österreich");
        Product p82 = new Product(82,"Besteck", 35, "Angebote", 45.99, "Geislingen", "Deutschland");
        Product p83 = new Product(83, "Sandwichmaker", 20, "Angebote", 35.99, "Marktheidenfeld", "Deutschland");
        
        // final Product p4 = new ProductBuilder().setid(5).setName("Buildername").setDescription("description").setCategory("category").setPrice(2.00).setCity("City").setCountry("D").build();

        /**
         * Benutzer zum testen der Datenbank hinzufuegen
         */

        ShoppingCart s0 = new ShoppingCart();
        Bookmark b0 = new Bookmark();

        User testuser = new User(null, "USERNAME1", "$2a$10$urpvBvZzedkBs7BnXrI5XewwcdGG4Cy537B9beFfIYvOJLe3oYAxm", "Test", "User" , "test@test.de", "Teststraße", 4, "Geschlecht", "Teststadt", 12345, null,null);
        testuser.setRole(roleRepository.getOne((long) 3));
        ShoppingCart s1 = new ShoppingCart();
        Bookmark b1 = new Bookmark();
        
        User adminUser = new User(null, "ADMINADMIN", "$2a$10$MgdkubdPkckMtPc6csgn..nQCK1aP2X9yTgdvf58QSKzsV.SlzJCW", "Admin", "Admin", "admin@admin.de", "Adminstraße", 6, "Geschlecht", "Adminstadt", 56789, null,null);
        adminUser.setRole(roleRepository.getOne((long) 1));
        ShoppingCart s2 = new ShoppingCart();
        Bookmark b2 = new Bookmark();

        User lagerist = new User(null, "LAGERLAGER", "$2a$10$2o5qUhMWZylz8.iqHpyuaOOJ5naa8oNYdPHWpiAsGv6vwrcSoS/.W", "Lager", "Lager", "lager@lager.de", "Lagerstraße", 8, "Geschlecht", "Lagerstadt", 13579, null,null);
        lagerist.setRole(roleRepository.getOne((long) 2));
        ShoppingCart s3 = new ShoppingCart();
        Bookmark b3 = new Bookmark();

        //Hier werden Standartbenutzer hinzugefuegt

        //User u = new User(id, username, password, name, surname, email, street, addressnr, gender, city, zipcode, role)
        User u001 = new User(null, "marvinschroeder", "$2a$10$urpvBvZzedkBs7BnXrI5XewwcdGG4Cy537B9beFfIYvOJLe3oYAxm", "Schroeder", "Marvin", "info.marvin@online.de", "Unter-den-Eichen", 1, "M", "Wiesbaden", 65183, null,
                null);
        u001.setRole(roleRepository.getOne((long) 3)); // Role 3 -> User

        User u002 = new User(null, "larawirth", "$2a$10$urpvBvZzedkBs7BnXrI5XewwcdGG4Cy537B9beFfIYvOJLe3oYAxm", "Wirth", "Lara", "lara-lacht@online.de", "Unter den Eichen", 2, "F", "Wiesbaden", 65182, null, null);
        u002.setRole(roleRepository.getOne((long) 3)); // Role 3 -> User


        



        // drop all
        this.productRepository.deleteAll();
        this.userRepository.deleteAll();
        this.shoppingcartRepository.deleteAll();
        this.bookmarkRepository.deleteAll();

        // add to db
        List<Product> empty = Arrays.asList();
        List<User> user_data = Arrays.asList(testuser, adminUser, lagerist);
        List<ShoppingCart> cart_data = Arrays.asList(s0,s1,s2,s3);
        List<Product> product_data = Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,
                        p41, p42, p43, p44, p45, p46, p47, p48, p49, p50, p51, p52, p53, p54, p55, p56, p57, p58, p59,
                        p60, p61, p62, p63, p64, p65, p66, p67, p68, p69, p70, p71, p72, p73, p74, p75, p76, p77, p78,
                        p79, p80, p81, p82, p83);
        List<Bookmark> bookmark_data = Arrays.asList(b0,b1,b2,b3);
        
        s0.setId(0);
        s1.setId(1);
        s2.setId(2);
        s3.setId(3);

        s0.setShoppingCartList(empty);
        s1.setShoppingCartList(empty);
        s1.setShoppingCartList(empty);
        s3.setShoppingCartList(empty);

        b0.setId(0);
        b1.setId(1);
        b2.setId(2);
        b3.setId(3);

        b0.setBookmarkList(empty);
        b1.setBookmarkList(empty);
        b2.setBookmarkList(empty);
        b3.setBookmarkList(empty);    

        //Alle User (u001 und u002 add)
        List<User> moreUsers = Arrays.asList(u001,u002);

        

        // this.productRepository.saveAll(data);
        // this.shoppingcartRepository.save(s);
        // this.bookmarkRepository.save(b);

        List<User> u_data = Arrays.asList(testuser, adminUser, lagerist);
        this.userRepository.saveAll(u_data);
        this.userRepository.saveAll(moreUsers);




        this.productRepository.saveAll(product_data);
        this.shoppingcartRepository.saveAll(cart_data);
        this.bookmarkRepository.saveAll(bookmark_data);
        this.userRepository.saveAll(user_data);
        // this.userRepository.saveAll(moreUsers);
    }
}