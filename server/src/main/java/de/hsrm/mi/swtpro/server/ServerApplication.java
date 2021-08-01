package de.hsrm.mi.swtpro.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import de.hsrm.mi.swtpro.server.services.OrderService;

@SpringBootApplication
//@EnableScheduling
@CrossOrigin(origins = "*")
@EnableCaching

/**
 * Startpunkt fuer das Backend in Spring-Boot
 * Hier werden die Cron Jobs gestartet und das Caching global in der App aktiviert
 */
public class ServerApplication {

	@Autowired
	OrderService orderService;

	// Fuer Zugriff mit den CronJobs auf die Bestellung
	public ServerApplication(OrderService orderService){
		this.orderService = orderService;
	}

	/**
	 * Startpunkt 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	/**
	 * Alle drei Minuten wird die Abholung der Prdoukte im Laden durch den Kunden simuliert.
	 * Da wir eine Lieferkette nicht abbilden wird diese Funktion hier umgesetzt
	 */
	@Scheduled(cron = "0 0/3 * * * *")
	public void doScheduledWork3(){
		//Zugriff auf die Bestellung
		orderService.deleteCron();
	}
	// Anotations bitte nicht auf Ebene der Server Application Klasse. Es kann sonst zu Problemen beim Testing kommen, insbesondere wenn wir mit Mock-Daten arbeiten
	@Configuration
	@EnableScheduling
	@ConditionalOnProperty(name="scheduling.enable", matchIfMissing = true)
	/**
	 * Config fuer Cron-Job. Wird hier definiert um keine Probleme mit dem Testing zu bekommen
	 */
	public class SchedulingConfig{}
}