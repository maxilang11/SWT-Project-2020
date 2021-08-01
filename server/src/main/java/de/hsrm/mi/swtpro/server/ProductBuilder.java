package de.hsrm.mi.swtpro.server;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.hsrm.mi.swtpro.server.models.Bookmark;
import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.models.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

/**
* ProduchtBuilder ist die Umsetzung des DesignPattern Builder
* Er wird beim DB Inputer zum hinzufuegen der Daten und zum Testing der Repos benutzt
*
*/
public class ProductBuilder {
    @Id
    private int id;
    private String name;
    private int amount;
    private String category;
    private double price;
    private String city;
    private String country;
    @JsonIgnore
    @ManyToOne
    private ShoppingCart cart;
    @JsonIgnore
    @ManyToOne
    private Bookmark bookmark;    

    public ProductBuilder() {}
    
    public ProductBuilder(int id,String name, int amount, String category, double price, String city, String country){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.price = price;
        this.city = city;
        this.country = country;    
    }

	public ProductBuilder setId(int id) {this.id = id;return this;}
    public ProductBuilder setName(String name) {this.name = name;return this;}
    public ProductBuilder setAmount(int amount) {this.amount = amount;return this;}
    public ProductBuilder setCategory(String category) {this.category = category;return this;}
    public ProductBuilder setPrice(double price) {this.price = price;return this;}
    public ProductBuilder setCity(String city) {this.city = city;return this;}
    public ProductBuilder setCountry(String country) {this.country = country;return this;}
    public ProductBuilder setCart(ShoppingCart cart) {this.cart = cart;return this;}
    public ProductBuilder setBookmark(Bookmark bookmark) {this.bookmark = bookmark;return this;}
   
    /**
     * 
     * @return Build Methode um Objekt ueber .build zu erzeugen
     */
     public Product build(){
         return new Product(id,name,amount,category,price,city,country);
     }    
}