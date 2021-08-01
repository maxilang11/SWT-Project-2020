package de.hsrm.mi.swtpro.server.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
/**
 * Java-Klasse, welche die Merkliste unserer Anwendung darstellt.
 */
@Entity
@Getter
@Setter
public class Bookmark {
    /**
     * @param id ID der Merkliste abhänging von der UserID
     * @param bookmarkList Liste der Produkte in Merkliste
     */
    @Id
    private int id;
    @JsonIgnore
    @ManyToMany
    private List<Product> bookmarkList = new ArrayList<Product>();    
 
    public Bookmark(){}
    
    /** 
     * @param id ID der Merkliste abhängig von der USER ID
     * @param bookmarkList Liste der Produkte in Merkliste
     * @return 
     */
    public Bookmark(int id, List<Product> bookmarkList) {
        this.id = id;
        this.bookmarkList = bookmarkList;
    }
}