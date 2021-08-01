package de.hsrm.mi.swtpro.server;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import de.hsrm.mi.swtpro.server.models.Product;
import lombok.*;
@AllArgsConstructor
public class ProductPDFExporter {
    private List<Product> data;
    
    /** 
     * 
     * Tabelle fuer den PDF Export erstellen
     * @param table
     */
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("NAME", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PREIS", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("STADT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("LAND", font));
        table.addCell(cell);
    }
    
    /** 
     * 
     * Daten in die Tabelle schreiben
     * @param table
     */
    private void writeTableData(PdfPTable table) {
        for (Product e : data) {
            table.addCell(String.valueOf(e.getId()));
            table.addCell(e.getName());
            table.addCell(String.valueOf(e.getPrice()));
            table.addCell(e.getCity());
            table.addCell(e.getCountry());
        }
    }

    /**
     * Export als PDF
     * @param response
     * @throws DocumentException
     * @throws IOException
     */
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(CMYKColor.BLUE);

        Paragraph p = new Paragraph("List of Data", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}