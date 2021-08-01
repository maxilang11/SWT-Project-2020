package de.hsrm.mi.swtpro.server.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    /**
     * Alles Produkte
     * @return List<Product>
     */
    public List<Product> allProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }

    /**
     * @param id
     * @return Product
     */
    @Cacheable("id")
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    /**
     * @param category
     * @return List<Product>
     */
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Cacheable("update")
    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    /**
     * Zum Löschen eines Produktes
     * @param id
     */
    @Cacheable("delete")
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    /**
     * @param city
     * @return List<Product>
     */
    @Cacheable("city")
    public List<Product> getProductByCity(String city) {
        return productRepository.findByCity(city);
    }

    /**
     * @param price
     * @return long
     */
    @Cacheable("getCounts")
    public long getCounts(double price) {
        return productRepository.countByPrice(price);
    }

    /**
     * @param name
     * @return List<Product>
     */
    @Cacheable("deletebyName")
    public List<Product> deleteByName(String name) {
        return productRepository.deleteByName(name);
    }

    /**
     * @param id
     * @param amount
     */
    @Transactional
    public void changeAmount(int id, int amount) {
        int x = productRepository.findById(id).getAmount() + amount;
        productRepository.findById(id).setAmount(x);
    }

    /**
     * @param name
     * @param price
     * @return List<Product>
     */
    @Cacheable("findByNameAndPrice")
    public List<Product> findByNameAndPrice(String name, double price) {
        return productRepository.findByNameAndPrice(name, price);
    }

    /**
     * @param search
     * @return List<Product>
     */
    @Cacheable("searchbyName")
    public List<Product> searchByName(String search) {
        return productRepository.searchByName(search);
    }

    /**
     * @return List<Product>
     */
    @Cacheable("getByProductViaSQL")
    public List<Product> getByProductViaSQL() {
        return productRepository.getByProductViaSQL();
    }

    public List<Product> findbyTop() {
        return productRepository.findByTop();
    }

    public List<Integer> getByProductPriceClass() {

        ArrayList<Integer> psection = new ArrayList<>();

        // Anzahl an Produkten je Preisregion holen
        var x1 = productRepository.getByProductSection1().size();
        var x2 = productRepository.getByProductSection2().size();
        var x3 = productRepository.getByProductSection3().size();
        var x4 = productRepository.getByProductSection4().size();

        // Der Liste hinzufuegen
        psection.add(x1); // 0-2
        psection.add(x2); // 2-5
        psection.add(x3); // 6-10
        psection.add(x4); // < 10 - 1000 (max)

        // Anzahl der Werte je Preiskategorie zurückgeben
        return psection;
    }

    @Cacheable("deleteAll")
    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void updateProduct(Integer id, List<Product> allProducts) {
        for (int i = 0; i < allProducts.size(); i++) {
            Product p = allProducts.get(i);
            if (p.getId() == id) {
                allProducts.set(i, p);
            }
        }
    }

    // Methode welche die Anzahl an Produkten in der Datenbank zurückgibt
    public int count() {
        return allProducts().size(); // Aufruf der Methode allProducts und von dieser ArrayListe die Groeße bestimmen
                                     // und nur die Anzhal zurueckgeben
    }

    /**
     * Bearbeiten eines Produktes
     * @param editProductData
     */
    @Transactional
    public void editProduct(Product editProductData) {
        productRepository.findById(editProductData.getId()).setName(editProductData.getName());
        productRepository.findById(editProductData.getId()).setCity(editProductData.getCity());
        productRepository.findById(editProductData.getId()).setCountry(editProductData.getCountry());
        productRepository.findById(editProductData.getId()).setCategory(editProductData.getCategory());
        productRepository.findById(editProductData.getId()).setPrice(editProductData.getPrice());
    }
}
