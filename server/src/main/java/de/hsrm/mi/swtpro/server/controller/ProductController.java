package de.hsrm.mi.swtpro.server.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import de.hsrm.mi.swtpro.server.ProductCSVExporter;
import de.hsrm.mi.swtpro.server.ProductExcelExporter;
import de.hsrm.mi.swtpro.server.ProductPDFExporter;
import de.hsrm.mi.swtpro.server.models.Product;
import de.hsrm.mi.swtpro.server.services.ProductService;


/**
 * Der ProductController ist fuer alle Anfragen der eigentlichen Produkte zuständig. 
 * Hier sind neben den CRUD Operationen auch die ExportFunktionen zu finden 
 */
@RestController

/**
 * Der ProductController ist fuer alle Anfragen der eigentlichen Produkte zuständig. 
 * Hier sind neben den CRUD Operationen auch die ExportFunktionen zu finden 
 */
public class ProductController{
    @Autowired
    ProductService productService;    

    /**
     * @return List<Product>
     */
    @GetMapping("/api/products")
    private List<Product> getAllProducts(){
        return productService.allProducts();
    }

    /** Produkte nach Kategorie */
    @GetMapping("/api/category/{categoryname}")
    private List<Product> getProductsByCategory(@PathVariable String categoryname){
        return productService.findByCategory(categoryname);
    }

    /**
    * update oder insert DB Element(sicherer, falls ID schon vorhanden)
    */
    @PostMapping("/api/products")
    private int saveProduct(@RequestBody final Product product){
        productService.saveOrUpdate(product);
        return product.getId();
    }

    /**
     * @param editProductData
     * @return ResponseEntity<String>
     * @throws Exception
     */
    @PostMapping(value = "/api/productEdit")
    public ResponseEntity<String> editProduct(@Valid @RequestBody Product editProductData) throws Exception {
  
        productService.editProduct(editProductData);
        return ResponseEntity.ok().body("User Profil wurde erfolgreich bearbeitet");
    }

    /**
     * Produkte loeschen
     * 
     * @param product
     * @return ResponseEntity<Product>
     */
    @PostMapping(value="/api/delProduct", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product){
        try {
            productService.delete(product.getId());
            ResponseEntity<Product> b = new ResponseEntity<Product>(product, HttpStatus.CREATED);
            return b;
        } catch (NullPointerException e) {}
        return null;
    }

    /**
     * @param id
     * @param amount
     */
    @PostMapping(value="/api/changeAmount/{id}/{amount}", produces=MediaType.APPLICATION_JSON_VALUE)
    public void changeAmount(@PathVariable int id, @PathVariable int amount) {
        productService.changeAmount(id, amount);
    }    
    
    /**
     * Die Sucheleiste welche der Produkt und Kategorieseite angezeigt wird
     * @param search
     * @return List<Product> die über die Suche gefunden wird
     */
    @GetMapping("/api/search/{search}")
    public List<Product> searchByName(@PathVariable("search") String search) {
        List<Product> products = productService.searchByName(search);
        return products;
    }

    /**
     * Der Export der Produkte in den unterschiedlichen Dateiformaten
     * @param response
     * @throws DocumentException
     * @throws IOException
     * @throws InterruptedException
     */
    @RequestMapping("/api/admin/products/export/{file}")
    public void exportData(HttpServletResponse response, @PathVariable String file)
            throws DocumentException, IOException, InterruptedException {

        if (file.equals("pdf")) {
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=data_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            List<Product> data = productService.allProducts();

            ProductPDFExporter exporter = new ProductPDFExporter(data);
            exporter.export(response);
        }
        if (file.equals("xls")) {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=data_" + currentDateTime + ".xls";
            response.setHeader(headerKey, headerValue);
            List<Product> data = productService.allProducts();
            ProductExcelExporter productExcelExporter = new ProductExcelExporter(data);
            productExcelExporter.export(response);
        }
        if (file.equals("csv")) {
            response.setContentType("text/csv");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String fileName = "data";
            String headerKey = "Content-Disposition";

            String headerValue = "attachment; filename=" + fileName + " " + currentDateTime + ".csv";
            response.setHeader(headerKey, headerValue);

            List<Product> data = productService.allProducts(); // Alle Produkte holen

            ProductCSVExporter productCSVExporter = new ProductCSVExporter(data);
            productCSVExporter.export(response);
        }
    }
            
    /**
     * Gibt die Produkte als Liste zurück welche über die Stadt gefunden werden können
     * @param city
     * @return
     */
    @RequestMapping(value="/api/products/city/{city}")
    public List<Product> getProductByCity(@PathVariable final String city){
        return productService.getProductByCity(city);
    }

    /**
     * Zählt die Produkte nach einem bestimmten Preis
    * @param price
    * @return Anzahl an Preis als int
    */
    @RequestMapping(value = "/api/products/price/pricecount/{price}")
    public String getCountByPrice(@PathVariable final double price){

        final long count = productService.getCounts(price);
        return "data:" + count;
    }

    /**
     *
    * @param name
    * @return Delete by Name
    */
    @DeleteMapping("/api/products/delete/{name}")
    public List<Product> deleteByName(@PathVariable final String name){
        return productService.deleteByName(name);
    }

    /**
     * Suche mit zwei Parametern mit einem Namen und mit dem Preis
    * @param name
    * @param price
    * @return Find by Name und Preis
    */
    @GetMapping("/api/products/findbymultiple/{name}/{price}")
    public List<Product> findByNameAndPrice(@PathVariable final String name, @PathVariable final double price){
        return productService.findByNameAndPrice(name, price);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable final Integer id){
        productService.updateProduct(id, productService.allProducts());
    }


    /**
     *Suchen mit einer SQL Query
    * @return SQL Query
    */
    @GetMapping("/api/products/query")
    public List<Product> getByProductViaSQL(){
        return productService.getByProductViaSQL();
    }

    /**
     * 
    * @return Die Anzahl aller Produkte in der Datenbank als int
    */
    @GetMapping("/api/products/countAll")
    public int countAllProducts(){
        return productService.count();
    }
    /**
     * Sucht die Produkte nach unterschiedlichen Preisen
    * @return Liste mit Preisklassen
    */
    @GetMapping("/api/products/section")
    public List<Integer> getSection(){
        return productService.getByProductPriceClass();

    }
    /**
     * Gibt die 5 Produkte mit den höchsten Lagerbestand zurück um sie auf der Adminseite anzuzeigen
    * @return TOP 5 Produkte
    */
    @GetMapping("/api/products/top")
    public List<Product> getTop(){
        return productService.findbyTop();
    }
}