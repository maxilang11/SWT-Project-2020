package de.hsrm.mi.swtpro.server.security;

import org.springframework.boot.actuate.info.Info.Builder;
import java.util.HashMap;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
@Component
/**
 * Klasse welche eine Default-Info im API-Monitoring anzeigt
 */
public class ApplicationInfoService implements InfoContributor {    
    /** 
     * @param builder
     */
    @Override
    public void contribute(final Builder builder) {
        // do
        final HashMap<String, String> data = new HashMap<>();
        data.put("key", "MI-Markt");
        data.put("key2", "Supermarkt");
        builder.withDetail("data", data);

        // oder wieder als Liste zurückgeben. Noch besprehen. Daten werden unter /info angezeigt.  
    }
}