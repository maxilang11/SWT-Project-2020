package de.hsrm.mi.swtpro.server.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.hsrm.mi.swtpro.server.models.Order;
import de.hsrm.mi.swtpro.server.models.User;
import de.hsrm.mi.swtpro.server.repository.OrderRepository;
import de.hsrm.mi.swtpro.server.repository.ProductRepository;
import de.hsrm.mi.swtpro.server.repository.UserRepository;
import de.hsrm.mi.swtpro.server.services.OrderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import de.hsrm.mi.swtpro.server.models.EMailCfg;

@Service
/**
 * Service Klasse für alle Bestellungen
 */
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EMailCfg emailCfg;
    @Autowired
    ShoppingCartService shoppingCartService;

    int total = 0;

    /**
     * Die Bestellung wird anhand ihrer ID gefunden
     *
     * @param id
     * @return Order
     */
    public Order findByOrder(int id) {
        Order o = new Order();
        o = orderRepository.findById(id);
        return o;
    }

    /**
     * Alle Bestellungen eines Users
     *
     * @param username
     * @return List<Order>
     */
    public List<Order> getUserOrder(String username) {
        return userRepository.findByUsername(username).getOrders();
    }

    /**
     * @param orderId
     * @return User
     */
    public User findUserByOrderId(int orderId) {
        return orderRepository.findById(orderId).getUser();
    }

    /**
     * Status wird von Bearbeitung zu Bereit gesetzt, wenn Bestellung fertig gepackt
     * wurde
     *
     * @param id Bestellnummer
     * @return Order Bestellung
     */
    @Transactional
    public Order changeStatus(int id) {
        Order o = new Order();
        o = orderRepository.findById(id);
        if (o.getDeliveryOption().equals("Abholung in der Filiale")) {
            o.setOrderstatus("Bestellung bereit zur Abholung");
        } else if (o.getDeliveryOption().equals("Lieferung nach Hause")) {
            o.setOrderstatus("Bestellung bereit zur Auslieferung");
        }
        return o;
    }

    /**
     * Die Bestellung wird anhand der Daten aus dem frontend erstellt und im
     * Repository abgespeichert
     *
     * @param username
     * @param order
     */
    @Transactional
    public void addOrder(String username, Order order) {
        Order newOrder = new Order();
        newOrder.setProductList(order.getProductList());
        newOrder.setTotalPrice(order.getTotalPrice());
        newOrder.setOrderstatus("In Bearbeitung");
        newOrder.setDate(LocalDate.now());
        newOrder.setAmount(order.getAmount());
        for (int key : order.getAmount().keySet()) {
            total += order.getAmount().get(key);
        }
        newOrder.setTotalAmount(total);
        newOrder.setDeliveryOption(order.getDeliveryOption());
        newOrder.setPayment(order.getPayment());
        User user = userRepository.findByUsername(username);
        newOrder.setUser(user);
        userRepository.findByUsername(username).getOrders().add(newOrder);
        shoppingCartService.findShoppingCart(user.getId()).setCartList(null);
        shoppingCartService.findShoppingCart(user.getId()).setAmount(null);
        orderRepository.save(newOrder);
        total = 0;
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Eine E-Mail Instanz wird erstellt
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("service@misupermarkt.de");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Vielen Dank für Ihre Bestellung beim Super MiMarkt!");
        mailMessage.setText("Vielen Dank für Ihre Bestellung, " + user.getGender() + " " + user.getSurname() + "."
                + " Ihre Bestellung ist bald für Sie fertig.");

        // E-Mail wird versendet
        mailSender.send(mailMessage);

    }

    /**
     * Alle Bestellungen
     *
     * @return List<Order>
     */
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteCron() {
        orderRepository.deleteCron();
    }
}
