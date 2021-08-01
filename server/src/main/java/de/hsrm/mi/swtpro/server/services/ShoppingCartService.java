package de.hsrm.mi.swtpro.server.services;

import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.models.ShoppingCart;
import de.hsrm.mi.swtpro.server.repository.ProductRepository;
import de.hsrm.mi.swtpro.server.repository.ShoppingCartRepository;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ProductRepository productRepository;
    boolean isIn = false;
    int amount = 0;
    int quantity = 0;

    /**
     * @param id
     * @return ShoppingCart
     */
    public ShoppingCart findShoppingCart(int id) {
        ShoppingCart s = new ShoppingCart();
        s = shoppingCartRepository.findById(id);
        return s;
    }

    /**
     * Die Anzahl der Produkte im Warenkorb
     * @param id
     * @return int
     */
    public int getQuantity(int id) {
        int total = 0;
        Map<Integer, Integer> amount = shoppingCartRepository.findById(id).getAmount();
        for (int key : amount.keySet()) {
            total += amount.get(key);
        }
        return total;
    }

    /**
     * Der Gesamtpreis des Warenkorbs
     * @param id
     * @return double
     */
    public double totalPrice(int id) {
        double total = 0;
        Map<Integer, Integer> amount = shoppingCartRepository.findById(id).getAmount();
        for (int key : amount.keySet()) {
            total += (double) amount.get(key) * productRepository.findById(key).getPrice();
        }
        return total;
    }

    /**
     * Ein Produkt wird dem Warenkorb hinzugefügt, die Anzahl des Prdouktes im
     * Warenkorb wird in einer Map gespeichert (amount), die ID des Produktes ist
     * der key und der Wert die Menge des Produktes im Warenkorb Beim Hinzufügen
     * eines Produktes wird der Wert in amount angepasst bzw. gespeichert und die
     * eigentliche Anzahl des Produktes verringert (Bestand im MIMarkt)
     *
     * @param id
     * @param product
     */
    @Transactional
    public void addProduct(int id, Product product) {
        try {

            if (shoppingCartRepository.findById(id) != null) {
                product.setCart(shoppingCartRepository.findById(id));

                CopyOnWriteArrayList<Product> shoppingCartList = new CopyOnWriteArrayList<>(
                        shoppingCartRepository.findById(id).getCartList());

                if (shoppingCartRepository.findById(id).getCartList().size() > 0) {
                    for (Product p : shoppingCartList) {
                        if (product.getId() == p.getId()) {
                            isIn = true;
                            quantity = shoppingCartRepository.findById(id).getAmount().get(product.getId())
                                    + product.getAmount();
                            shoppingCartRepository.findById(id).getAmount().put(product.getId(), quantity);
                            amount = productRepository.findById(product.getId()).getAmount();
                            productRepository.findById(product.getId()).setAmount(amount - product.getAmount());

                        }
                    }
                    if (isIn == true) {

                        isIn = false;
                    } else {
                        shoppingCartRepository.findById(id).getCartList().add(product);
                        shoppingCartRepository.findById(id).getAmount().put(product.getId(), product.getAmount());
                        amount = productRepository.findById(product.getId()).getAmount();
                        productRepository.findById(product.getId()).setAmount(amount - product.getAmount());
                    }
                } else {
                    shoppingCartRepository.findById(id).getCartList().add(product);
                    shoppingCartRepository.findById(id).getAmount().put(product.getId(), product.getAmount());
                    amount = productRepository.findById(product.getId()).getAmount();
                    productRepository.findById(product.getId()).setAmount(amount - product.getAmount());
                }
            } else {
                shoppingCartRepository.findById(id).getCartList().add(product);
            }
        } catch (DataIntegrityViolationException e) {
        }
    }

    /**
     * Ein Produkt wird aus dem Warenkorb gelöscht, der Bestand des Produktes wird
     * wieder geholt und die Map amount wird angepasst
     *
     * @param id
     * @param product
     */
    @Transactional
    public void delProduct(int id, Product product) {
        try {
            if (shoppingCartRepository.findById(id) != null) {
                product.setCart(shoppingCartRepository.findById(id));
                Iterator<Product> ele = shoppingCartRepository.findById(id).getCartList().iterator();
                while (ele.hasNext()) {
                    Product p = ele.next();
                    if (product.getId() == p.getId()) {
                        ele.remove();

                        quantity = shoppingCartRepository.findById(id).getAmount().get(product.getId())
                                - product.getAmount();
                        shoppingCartRepository.findById(id).getAmount().put(product.getId(), quantity);
                        amount = productRepository.findById(product.getId()).getAmount() + product.getAmount();
                        productRepository.findById(product.getId()).setAmount(amount);
                    }
                }
            }
        } catch (DataIntegrityViolationException e) {
        }
    }
}
