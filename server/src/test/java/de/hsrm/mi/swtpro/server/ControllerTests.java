package de.hsrm.mi.swtpro.server;



import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import de.hsrm.mi.swtpro.server.controller.ProductController;
import de.hsrm.mi.swtpro.server.services.ProductService;

@WebMvcTest(ProductController.class)
public class ControllerTests {


    @MockBean
    private ProductService productService;

//Aktuell haben wir das Problem, dass Spring Security keinen Zugriff durchlaesst


//     @Test
//     public void testi() throws Exception {
//     when(productService.allProducts())
//     .thenReturn(List.of(new ProductBuilder().setId(9999).setName("Testname").build()));
 
//   this.mvc
//     .perform(MockMvcRequestBuilders.get("/products/9999"))
//     .andExpect(MockMvcResultMatchers.status().isOk())
//     .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
//     .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(9999))
//     .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Testname"));
// }   

    
}
  