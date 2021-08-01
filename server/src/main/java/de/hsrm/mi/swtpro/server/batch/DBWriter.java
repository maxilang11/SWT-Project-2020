package de.hsrm.mi.swtpro.server.batch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.repository.ProductRepository;
@Component
public class DBWriter implements ItemWriter<Product> {

    @Autowired
    private ProductRepository productRepository;

    /** 
     * @param products
     * @throws Exception
     */
    @Override
    public void write(List<? extends Product> products) throws Exception {       
        productRepository.saveAll(products);
    }    
}