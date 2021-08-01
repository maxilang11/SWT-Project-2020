package de.hsrm.mi.swtpro.server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.models.ShoppingCart;

@Repository
@RepositoryRestResource(path="ProductEndpoint")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findByCity(String city);
    public long countByPrice(double price);
    public List<Product> deleteByName(String name);
    public List<Product> findByNameAndPrice(String name, double price);
     /**
     * Suchfunktions Query (case-insensitive)
     */
    @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE CONCAT(UPPER(?1),'%')")
    public List<Product> searchByName(String search);
    // Hier dann die SQL für Java Persistence Query-Sprache JPQL

    // Preis zwischen 0 Euro und inkl 2 Euro
    @Query( "Select e " +
    "from Product e " +
    "where e.price " +
    "Between 0 and 2" )
    public List<Product> getByProductViaSQL();

    // Preis zwischen 0 Euro und inkl 2 Euro
    @Query( "Select e " +
    "from Product e " +
    "where e.price " +
    "Between 0 and 2" )
    public List<Product> getByProductSection1();

    // Preis zwischen 2 Euro und inkl 5 Euro
    @Query( "Select e " +
    "from Product e " +
    "where e.price " +
    "Between 2 and 5" )
    public List<Product> getByProductSection2();

    // Preis zwischen 2 Euro und inkl 5 Euro
    @Query( "Select e " +
    "from Product e " +
    "where e.price " +
    "Between 6 and 10" )
    public List<Product> getByProductSection3();

    // Preis zwischen 10 Euro und inkl 1000 Euro
    @Query( "Select e " +
    "from Product e " +
    "where e.price " +
    "Between 10 and 1000" )
    public List<Product> getByProductSection4();

    // Die 5 Produkte mit der höchsten Stueckzahl im Lager
    @Query(nativeQuery = true,
    value = "SELECT TOP 5 * FROM Product e  ORDER BY e.amount DESC")
    List<Product> findByTop();


    List<Product> findByCart(ShoppingCart cart);
    List<Product> findByCategory(String category);
    Product findById(int id);
}