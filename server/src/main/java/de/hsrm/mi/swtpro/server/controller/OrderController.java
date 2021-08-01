package de.hsrm.mi.swtpro.server.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swtpro.server.models.Order;
import de.hsrm.mi.swtpro.server.models.User;
import de.hsrm.mi.swtpro.server.services.OrderService;

/**
 * Der Order Controller ist für das verwalten von Bestellungen zuständig
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * Man erhält alle Bestellungen eines Nutzers
     *
     * @param username
     * @return List>Order>
     */
    @GetMapping("/api/userOrders/{username}")
    private List<Order> userOrder(@PathVariable String username) {
        return orderService.getUserOrder(username);
    }

    /**
     * Wenn ein Nutzer seinen Einkauf abschließt wird eine Bestellung erstellt und
     * im Repository abgespeichert
     *
     * @param username
     * @param order
     * @return ResponseEntity<Order>
     */
    @PostMapping(value = "/api/checkout/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> checkout(@PathVariable String username, @RequestBody Order order) {

        try {
            orderService.addOrder(username, order);
            ResponseEntity<Order> b = new ResponseEntity<Order>(order, HttpStatus.CREATED);
            return b;
        } catch (NullPointerException e) {
        }
        return null;

    }

    /**
     * Alle Bestellungen
     *
     * @return List<Order>
     */
    @GetMapping("/api/orders")
    private List<Order> orders() {
        return orderService.getOrders();
    }

    /**
     * Man erhält eine Bestellund anhand ihrer ID
     *
     * @param orderId
     * @return Order
     */
    @GetMapping("/api/orders/{orderId}")
    private Order getOrderById(@PathVariable int orderId) {
        return orderService.findByOrder(orderId);
    }

    /**
     * Man erhält den User anhand der orderid
     *
     * @param orderId
     * @return User
     */
    @GetMapping("/api/orders/user/{orderId}")
    private User getUser(@PathVariable int orderId) {
        return orderService.findUserByOrderId(orderId);
    }

    /**
     * Zum verändert des Bestellstatus
     *
     * @param orderId
     * @return Order
     */
    @GetMapping("/api/orderstatus/{orderId}")
    private Order getStatusById(@PathVariable int orderId) {
        return orderService.changeStatus(orderId);
    }

}
