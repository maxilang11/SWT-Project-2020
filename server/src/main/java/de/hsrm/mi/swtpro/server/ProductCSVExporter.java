package de.hsrm.mi.swtpro.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import de.hsrm.mi.swtpro.server.models.Product;
import lombok.*;

@AllArgsConstructor
public class ProductCSVExporter {
    private List<Product> data;

    /**
     *Exportiert die Daten als CSV-Datei
     * 
     * @param response
     * @throws IOException
     */
    public void export(HttpServletResponse response) throws IOException {
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        final String[] csvHeader = { "ID", "NAME", "KATEGORIE", "PREIS", "STADT", "LAND" };
        final String[] nameMapping = { "id", "name", "category", "price", "city", "country" };

        csvWriter.writeHeader(csvHeader);
        for (Product e : data) {
            csvWriter.write(e, nameMapping);
        }
        csvWriter.close();
    }
}