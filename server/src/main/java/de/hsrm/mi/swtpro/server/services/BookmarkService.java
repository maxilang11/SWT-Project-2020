package de.hsrm.mi.swtpro.server.services;

import de.hsrm.mi.swtpro.server.models.Bookmark;
import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.repository.BookmarkRepository;
import de.hsrm.mi.swtpro.server.repository.ProductRepository;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
@Service
public class BookmarkService {
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    ProductRepository productRepository;
    boolean isIn = false;
    int amount = 0;
    int quantity = 0;
    

    /** 
     * @param id
     * @return Bookmark
     */
    public Bookmark findBookmark(int id) {
        Bookmark b = new Bookmark();
        b = bookmarkRepository.findById(id);
        return b;
    }
    
    /**
     * Ein Produkt wird der Merkliste Tabelle hinzugefügt, falls das Produkt bereits in der Merkliste
     * vorhanden ist, werden die Artikel bei gleicher ID "zusammengefasst"
     * @param id
     * @param product
     */
    @Transactional
    public void addProduct(int id, Product product) {
        try {
            if (bookmarkRepository.findById(id) != null) {
                product.setBookmark(bookmarkRepository.findById(id));

                CopyOnWriteArrayList<Product> bookmarkList = new CopyOnWriteArrayList<>(
                        bookmarkRepository.findById(id).getBookmarkList());
                
                if(bookmarkRepository.findById(id).getBookmarkList().size() > 0){
                    for(Product p: bookmarkList){
                        if(product.getId() == p.getId()){
                            isIn = true;
                            amount = productRepository.findById(product.getId()).getAmount();
                        }
                    }
                    if (isIn == true) {              
                        isIn = false;
                    } else {
                        bookmarkRepository.findById(id).getBookmarkList().add(product);
                    }
                } else {
                    bookmarkRepository.findById(id).getBookmarkList().add(product);
                }
            } else {
                bookmarkRepository.findById(id).getBookmarkList().add(product);
            }
        } catch (DataIntegrityViolationException e) {
        }
    }
    
    /** 
     * Produkt wird in der Datenbank aus der Merkliste Tabelle gelöscht
     * @param id
     * @param product
     */
    @Transactional
    public void delProduct(int id, Product product) {
        try {
            if (bookmarkRepository.findById(id) != null) {
                product.setBookmark(bookmarkRepository.findById(id));
                Iterator<Product> ele = bookmarkRepository.findById(id).getBookmarkList().iterator();
                while (ele.hasNext()) {
                    Product p = ele.next();
                    if (product.getId() == p.getId()) {
                        ele.remove();
                        
                    }
                }
            }
        } catch (DataIntegrityViolationException e) {
        }
    }

}

