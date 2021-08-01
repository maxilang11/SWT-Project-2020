// package de.hsrm.mi.swtpro.server;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.List;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import de.hsrm.mi.swtpro.server.models.Product;
// import de.hsrm.mi.swtpro.server.models.Role;
// import de.hsrm.mi.swtpro.server.models.User;
// import de.hsrm.mi.swtpro.server.repository.ProductRepository;
// import de.hsrm.mi.swtpro.server.repository.RoleRepository;
// import de.hsrm.mi.swtpro.server.repository.UserRepository;



// @DataJpaTest
// public class RepoTests {

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private RoleRepository roleRepository;

//     @Autowired 
//     private UserRepository userRepository;

//     // @Autowired 
//     // private BookmarkRepository bookmarkRepository;

//     @Test
//     @DisplayName("Alle Produkte aus der Datenbank holen")
//     public void testProductGetAll(){
//         final Integer expected = 0; // Am Anfang vor dem Input noch leer Produkte erst über den Command Line Runner
//         List<Product> data = productRepository.findAll();
//         assertEquals(expected, data.size());
//     }

//     @Test
//     @DisplayName("Alle User aus der Datenbank holen")
//     public void testUserGetAll(){
//         final Integer expected = 0; // Am Anfang vor dem Input noch leer Produkte erst über den Command Line Runner
//         List<User> data = userRepository.findAll();
//         assertEquals(expected, data.size());
//     }

//     @Test
//     @DisplayName("Alle Rollen aus der Datenbank holen")
//     public void testRoleGetAll(){
//         final Integer expected = 3; // User und Admin und Lager per SQL-file
//         List<Role> data = roleRepository.findAll();
//         assertEquals(expected, data.size());
//     }

//     @Test
//     @DisplayName("Produkte der Datenbank hinzufügen")
//     public void testAddProduct(){
//         final Integer expected = 1;

//         Product testP1 = new Product();
//         productRepository.save(testP1);
        
//         List<Product> data = productRepository.findAll();
//         assertEquals(expected, data.size());
//         productRepository.deleteAll();
//     }

//     @Test
//     @DisplayName("Benutzer der Datenbank hinzufügen")
//     public void testAddUser(){
//         final Integer expected = 1;

//        User u1 = new User();
//        userRepository.save(u1);
//         List<User> data = userRepository.findAll();
//         assertEquals(expected, data.size());
//         userRepository.deleteAll();
//     }

//     @Test
//     @DisplayName("Produkt nach Stadt finden")
//     public void findbyProductCity(){
//         final Integer expected = 1;

//         Product testP1 = new ProductBuilder().setCity("NeuTestStadt").build();
//         productRepository.save(testP1);
        
//         List<Product> data = productRepository.findByCity("NeuTestStadt");
//         assertEquals(expected, data.size());
//         productRepository.deleteAll();
//     }

//     @Test
//     @DisplayName("Produkte nach Namen und Preis finden")
//     public void findByNameAndPrice(){
//         final Integer expected = 1;

//         Product testP1 = new ProductBuilder().setName("testname").setPrice(0.09).build();
//         productRepository.save(testP1);
        
//         List<Product> data = productRepository.findByNameAndPrice("testname", 0.09);
//         assertEquals(expected, data.size());
//         productRepository.deleteAll();
//     }

//     @Test
//     @DisplayName("Produkte zwischen 0-2 Euro mit einer JPQL Query finden")
//     public void findbyCustomSQLQuery(){
        

//         Product testP1 = new ProductBuilder().setPrice(1.99).build();
//         productRepository.save(testP1);
        
//         List<Product> data = productRepository.getByProductViaSQL();
//         assertFalse(data.isEmpty()); // Ist nicht leer wenn wir ein Testprodukt hinzufügen
//         productRepository.deleteAll();
//     }
//     @Test
//     @DisplayName("Teste die Suchfunktion der verschiedenen Seiten")
//     public void searchFuctionTest(){
        

//         Product testP1 = new ProductBuilder().setName("TZuchini").build();
//         productRepository.save(testP1);
//         Integer expected = 1;
//         String searchString = "TZuchini";
//         List<Product> data = productRepository.searchByName(searchString);
//         assertEquals(expected, data.size());
//         productRepository.deleteAll();
//     }

//     @Test
//     @DisplayName("Leeres Productrepo Test")
//     public void testEmptyProductRepo(){
        
//         Integer expected = 0;
//         productRepository.deleteAll(); // repo leer machen
//         List<Product> data = productRepository.findAll();
//         assertEquals(expected, data.size());
    
//     }

//     @Test
//     @DisplayName("TBD")
//     public void testii(){
        
//         Product p1 = new ProductBuilder().setName("Deleteme").build();
//         productRepository.save(p1);
//         productRepository.deleteByName("Deletme");
       
//     }

//     @Test
//     @DisplayName("Test Product ID Range")
//     public void testRangeOnProduct(){
        
//         Product p1 = new ProductBuilder().setId(69).setName("Tester").build();
//         productRepository.save(p1);
//         assertNotNull(p1);
//         assertTrue(p1.getId() > 0); // if generatet
//         assertTrue(p1.getId() < Integer.MAX_VALUE);
        
       
//     }

//     @Test
//     @DisplayName("Produkt-Objektvergleich")
//     public void usingRecursiveComparisonProduct(){
        
//         Product p1 = new ProductBuilder().setId(0).setName("Testname").setPrice(0.99).build();
//         productRepository.save(p1);
//         // Objektvergleich mit allen Felder ob diese Ubereinstimmen oder nicht. ID raus, weil evtnll autogenerated
//         assertThat(p1).usingRecursiveComparison().ignoringFields("user_id").isEqualTo(p1);
//        productRepository.delete(p1);
//     }

//     @Test
//     @DisplayName("User-Objektvergleich")
//     public void usingRecursiveComparisonUser(){
        
//         User ux1 = new User();
//         userRepository.save(ux1);
//         // Objektvergleich mit allen Felder ob diese Ubereinstimmen oder nicht. ID raus, weil evtnll autogenerated
//         assertThat(ux1).usingRecursiveComparison().ignoringFields("user_id").isEqualTo(ux1);
//        userRepository.delete(ux1);
//     }

//     /**
//      * Wenn wir Daten beim Starten unserer Anwendung über SQL Inserts mitgeben, waere hier der passende Testfall
//      */
//     // @Test
//     // @DisplayName("TBD")
//     // @Sql("classpath:data.sql")
//     // public void testi(){
//     //     assertTrue(true);

//     // }

   

// }
