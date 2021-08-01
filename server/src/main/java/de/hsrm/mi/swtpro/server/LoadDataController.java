package de.hsrm.mi.swtpro.server;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


/**
 * Daten werden ueber den Aufruf von / load ueber Spring-Batch der Datenbak hinzugefuegt.
 * Diese Funktion ist dem Admin unserer Anwendung vorbehalten.
 * 
 * @see Funktion nicht im taiga mehr verplant im Frontend
 */
@RestController
@RequestMapping("/load")
public class LoadDataController {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job job;
    
    /** 
     * Lädt die Daten um die Daten in der Datenbank mit neuen Daten auszutauschen
     * 
     * @return BatchStatus, welcher angibt ob das Laden erfolgreich war oder nicht
     * @throws JobParametersInvalidException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     */
    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        while (jobExecution.isRunning()) {}
        return jobExecution.getStatus(); // return FAILED if fail
    }
}