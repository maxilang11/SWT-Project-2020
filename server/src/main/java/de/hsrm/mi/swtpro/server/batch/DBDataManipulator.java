package de.hsrm.mi.swtpro.server.batch;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import de.hsrm.mi.swtpro.server.models.Product;
@Component
public class DBDataManipulator implements ItemProcessor<Product, Product> {
    private static final Map<String, String> MYDICTIONARY = new HashMap<>();    
    /** 
     * @return 
     */
    public DBDataManipulator() {
        MYDICTIONARY.put("BATCH", "REPLACEBATCH");
        MYDICTIONARY.put("002", "VAL2");
        MYDICTIONARY.put("003", "VAL3");
    } 
    /** 
     * @param product
     * @return Product
     * @throws Exception
     */
     @Override
    public Product process(Product product) throws Exception {
        String mykey = product.getName();
        String myvalue = MYDICTIONARY.get(mykey);
        product.setName(myvalue);
        return product;
    }
}