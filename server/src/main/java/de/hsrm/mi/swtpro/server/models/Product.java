package de.hsrm.mi.swtpro.server.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.ManyToOne;
/**
 * Java-Klasse, welche die Produkte unserer Anwendung darstellt.
 */
@Entity
@Getter
@Setter
public class Product implements Serializable {
    // Fuer Caching Hazelcast
    private static final long serialVersionUID = 2356476520820056321L;
    @Id
    private int id;
    private String name;
    private int amount;
    private String category;
    private double price;
    private String city;
    private String country;
    @ManyToOne
    private ShoppingCart cart;
    @ManyToOne
    private Bookmark bookmark;
    
    public Product(){}        

    /** 
     * @param id ID des Produkts
     * @param name Name des Produkts
     * @param amount Anzahl von Produkten im Lager
     * @param category Kategorie des Produkts
     * @param price Preis des Produkts
     * @param city Stadt des Produkts
     * @param country Land des Produkts
     * @return 
     */    
    public Product(int id,String name, int amount, String category, double price, String city, String country){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.price = price;
        this.city = city;
        this.country = country;
    }
    /** 
     * @return String Repräsentation eines Produkts
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", amount=" + amount + ", category=" + category + ", price="
                + price + ", city=" + city + ",country=" + country + "]";
    }
}