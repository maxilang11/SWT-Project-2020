package de.hsrm.mi.swtpro.server.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Java-Klasse, welche einen Warenkorb unserer Anwendung darstellt. 
 */
@Entity
@Getter
@Setter
public class ShoppingCart {
    /**
     * @param id ID des Warenkorbs anhängig von der UserID
     * @param amount Anzahl von Produkten in Warenkorb
     * @param cartList Liste der Produkte in Warenkorb
     */
    @Id
    private int id;
    @ElementCollection
    private Map<Integer,Integer> amount = new HashMap<Integer,Integer>();
    @JsonIgnore
    @ManyToMany
    private List<Product> cartList = new ArrayList<Product>();

    public ShoppingCart(){}
    
    /** 
     * @param id ID des Warenkorbs abhängig von der USER ID
     * @param cartList Liste der Produkte in Warenkorb
     * @return nothing
     */
    public ShoppingCart(int id, List<Product> cartList) {
        this.id = id;
        this.cartList = cartList;
    }

    /** 
     * @param cartList Liste der Produkte in Warenkorb
     */
    public void setShoppingCartList(List<Product> cartList) {
        this.cartList = cartList;
    }
    // public Map<Integer, Integer> getAmount() {
    //     return amount;
    // }
}