package de.hsrm.mi.swtpro.server.controller;

import de.hsrm.mi.swtpro.server.services.ShoppingCartService;
import de.hsrm.mi.swtpro.server.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
public class ShoppingCartController {
    /**
     * Java Klasse, welche die unterschiedlichen Methoden des Warenkorbs verwaltet (Anzeigen, Anzahl, Hinzufügen, Löschen)
     * Die ID des Users wird im frontend mitgegeben, dadurch werden hier nur die Produkte des zum User passenden Warenkorbs angezeigt
     */
    @Autowired
    ShoppingCartService shoppingCartService;
    
    /** 
     * @return List<Product> Liste der Produkte im Warenkorb
     */
    @GetMapping("/api/cart/{id}")
    private List<Product> getAllProductsinCart(@PathVariable int id){
        return shoppingCartService.findShoppingCart(id).getCartList();
    }
    
    /** 
     * @param id unique ID des Users
     * @return HashMap<Integer, Integer>
     */
    @GetMapping("/api/amount/{id}")
    private Map<Integer,Integer> getAmountProducts(@PathVariable int id){
        return shoppingCartService.findShoppingCart(id).getAmount();
    }
    
    /** 
     * @param id unique ID des Users
     * @return int
     */
    //Gibt die Anzahl der Produkte im Einkauswagen zurueck
    @GetMapping("/api/shoppingCartQuantity/{id}")
    private int getQuantity(@PathVariable int id){
        return shoppingCartService.getQuantity(id);
    }
    
    /** 
     * @param id unique ID des Users
     * @return double
     */
    @GetMapping("/api/totalPrice/{id}")
    private double getTotalPrice(@PathVariable int id){
        return shoppingCartService.totalPrice(id);
    }
    
    /** 
     * @param id unique ID des Users
     * @param product
     * @return ResponseEntity<Product>
     */
    @PostMapping(value="/api/cart/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProductToCart(@PathVariable int id, @RequestBody Product product){
        try {
            
            shoppingCartService.addProduct(id, product);

            ResponseEntity<Product> b = new ResponseEntity<Product>(product, HttpStatus.CREATED);
            return b;
        } catch (NullPointerException e) {}
        return null;
    }
    
    /** 
     * @param id unique ID des Users
     * @param product
     * @return ResponseEntity<Product>
     */
    @PostMapping(value="/api/cartdel/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProductFromCart(@PathVariable int id, @RequestBody Product product){
        try {
            
            shoppingCartService.delProduct(id, product);

            ResponseEntity<Product> b = new ResponseEntity<Product>(product, HttpStatus.CREATED);
            return b;
        } catch (NullPointerException e) {}
        return null;
    }
}
