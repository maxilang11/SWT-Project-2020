package de.hsrm.mi.swtpro.server.controller;

import de.hsrm.mi.swtpro.server.services.BookmarkService;
import de.hsrm.mi.swtpro.server.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
public class BookmarkController {
    /**
     * Java Klasse, welche die unterschiedlichen Methoden der Merkliste verwaltet (Anzeigen, Hinzufügen, Löschen)
     * Die ID des Users wird im frontend mitgegeben, dadurch erhält jeder User seine eigene Merkliste
     */
    @Autowired
    BookmarkService bookmarkService;
    
    /** 
     * @param id unique ID des Users
     * @return List<Product> Liste der Produkte in der Merklist
     */
    @GetMapping("/api/bookmark/{id}")
    private List<Product> getAllProductsinBookmarks(@PathVariable int id){
        return bookmarkService.findBookmark(id).getBookmarkList();
    }
    
    /** 
     * @param id unique ID des Users
     * @param product Produkt, das geaddet wird
     * @return ResponseEntity<Product>
     */
    @PostMapping(value="/api/bookmark/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProductToBookmark(@PathVariable int id, @RequestBody Product product){
        try {
            bookmarkService.addProduct(id, product);

            ResponseEntity<Product> b = new ResponseEntity<Product>(product, HttpStatus.CREATED);
            return b;
        } catch (NullPointerException e) {}
        return null;
    }
    
    /** 
     * @param id unique ID des Users
     * @param product Produkt, das gelöscht wird
     * @return ResponseEntity<Product>
     */
    @PostMapping(value="/api/bookmarkdel/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProductFromCart(@PathVariable int id, @RequestBody Product product){
        try {
            bookmarkService.delProduct(id, product);

            ResponseEntity<Product> b = new ResponseEntity<Product>(product, HttpStatus.CREATED);
            return b;
        } catch (NullPointerException e) {}
        return null;
    }
}
