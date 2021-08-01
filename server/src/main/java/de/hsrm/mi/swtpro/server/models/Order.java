package de.hsrm.mi.swtpro.server.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Setter;
import lombok.Getter;

/**
 * Java-Klasse, welche die Bestellungen unserer Anwendung darstellt
 */
@Getter
@Setter
@Table(name = "order_table")
@Entity
public class Order {

    @Id
    @GeneratedValue
    private int id;
    @ManyToMany
    private List<Product> productList = new ArrayList<Product>();
    private double totalPrice;
    private String orderstatus;
    private LocalDate date;
    private int totalAmount;
    @ManyToOne
    private User user;
    @ElementCollection
    private Map<Integer, Integer> amount = new HashMap<Integer, Integer>();
    private String deliveryOption;
    private String payment;

    public Order() {
    }

    /**
     *
     * @param id             Bestellnummer/ID einer Bestellung
     * @param productList    Produkte innerhalb der Bestellung
     * @param totalPrice     Gesamtpreis der Bestellung
     * @param orderstatus    Bestellstatus der Bestellung
     * @param date           Bestelldatum
     * @param totalAmount    Die Anzahl an unterschiedlichen Produkte in der
     *                       Bestellung
     * @param user           Nutzer der die Bestellung aufgeben hat
     * @param amount         Anzahl, wie oft das gleiche Produkt in der Bestellung
     *                       vorkommt
     * @param deliveryOption Die Lieferoption der Bestellung
     * @param payment        Die Bezahlmethode der Bestellung
     */
    public Order(int id, List<Product> productList, int totalPrice, String orderstatus, LocalDate date, int totalAmount,
            User user, Map<Integer, Integer> amount, String deliveryOption, String payment) {
        this.id = id;
        this.productList = productList;
        this.totalPrice = totalPrice;
        this.orderstatus = orderstatus;
        this.date = date;
        this.totalAmount = totalAmount;
        this.user = user;
        this.amount = amount;
        this.deliveryOption = deliveryOption;
        this.payment = payment;
    }

    /**
     * @return String Repräsentation einer Bestellung
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", productList=" + productList + ", totalPrice=" + totalPrice + ", oderstatus="
                + orderstatus + ", date=" + date + ", totalAmount=" + totalAmount + ",user=" + user + ", amount="
                + amount + ", deliveryOption=" + deliveryOption + ", payment=" + payment + ",  ]";
    }
}