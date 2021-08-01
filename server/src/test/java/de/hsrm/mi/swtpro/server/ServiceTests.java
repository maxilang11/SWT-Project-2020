package de.hsrm.mi.swtpro.server;


import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import de.hsrm.mi.swtpro.server.repository.ProductRepository;
import de.hsrm.mi.swtpro.server.services.ProductService;

@SpringBootTest
public class ServiceTests {

    @MockBean
    private ProductRepository productrepo;

    @InjectMocks
    private ProductService productservice;

    // @Test
    // public void test(){

    //     String name = "Testing";
    //     double price = 0.01;

    //     Product px = new ProductBuilder().setId(99).setName(name).setPrice(0.01).build();
    //     Mockito.doReturn(px).when(productrepo.findByNameAndPrice(name, price));


    // }


}