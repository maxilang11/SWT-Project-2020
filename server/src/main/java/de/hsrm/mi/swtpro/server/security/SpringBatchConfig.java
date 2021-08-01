package de.hsrm.mi.swtpro.server.security;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import de.hsrm.mi.swtpro.server.models.Product;
/**
 * You will build a service that imports data from a CSV spreadsheet, transforms
 * it with custom code, and stores the final results in a database.
 * 
 * @see https://spring.io/guides/gs/batch-processing/
 */

@Configuration
@EnableBatchProcessing

/**
 * Configuarationsklasse fuer den Import der Daten aus einer .csv Datei
 */
public class SpringBatchConfig {

    private static final String DELIMITER = ","; // Trennzeichen
    private static final String SET_NAME = "CSV-Reader"; // Name
    private static final int SKIP_THE_HEADER = 1; // skip first, dass hier ist der header der Datei
    //id name description category price city country
    private static final String[] THE_ROWS = new String[] { "id", "name", "description", "category","price","city","country" }; // Alle Spalten
    private static final Class<? extends Product> PRODUCT_TYPE = Product.class;
    
    /** 
     * @param jobBuilderFactory
     * @param stepBuilderFactory
     * @param itemReader
     * @param itemProcessor
     * @param itemWriter
     * @return Job
     */
    @Bean
    public Job job(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory,
        final ItemReader<Product> itemReader, final ItemProcessor<Product, Product> itemProcessor,
        final ItemWriter<Product> itemWriter) {

        final Step step = stepBuilderFactory.get("ETL-file-load").<Product, Product>chunk(100).reader(itemReader)
                .processor(itemProcessor).writer(itemWriter).build();

        return jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();
    }

    
    /** 
     * @return FlatFileItemReader<Product>
     */
    @Bean
    public FlatFileItemReader<Product> itemReader() {

        FlatFileItemReader<Product> flatFileItemReader = new FlatFileItemReader<>();
        //Set input file location
        //flatFileItemReader.setResource(new PathResource("/Users/marvin/Documents/Coding/MiMarkt/MiMarkt/server/src/main/resources/testLoadCSVData.csv"));
        flatFileItemReader.setResource(new ClassPathResource("data/MOCK_DATA_big.csv"));
        flatFileItemReader.setName(SET_NAME);
        flatFileItemReader.setLinesToSkip(SKIP_THE_HEADER);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    
    /** 
     * @return LineMapper<Product>
     */
    @Bean
    public LineMapper<Product> lineMapper() {

        final DefaultLineMapper <Product> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(DELIMITER); // Trennzeichen
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(THE_ROWS); // Namen der Spalten der CSV

        final BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PRODUCT_TYPE);

        
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }


}