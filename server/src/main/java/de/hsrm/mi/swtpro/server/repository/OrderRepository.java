package de.hsrm.mi.swtpro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.hsrm.mi.swtpro.server.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(int id);

    void deleteAll();

// Status wird von Bereit zu Geschlossen geupdatet
@Modifying
@Query(value="UPDATE order_table SET orderstatus = 'GESCHLOSSEN' WHERE orderstatus = 'Bestellung bereit zur Abholung' or orderstatus = 'Bestellung bereit zur Auslieferung'", nativeQuery=true)
void deleteCron();

}
