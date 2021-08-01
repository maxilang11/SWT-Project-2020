package de.hsrm.mi.swtpro.server.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EXCEPTIONHandler {    
    /** 
     * @param e
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(value = EXCEPTIONApiRequest.class)
    public ResponseEntity<Object> handle(EXCEPTIONApiRequest e){
        //1. Payload ex details
        ApiException ex = new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("zoneId")));
        //2. return repsone Entity
        return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
    }
}