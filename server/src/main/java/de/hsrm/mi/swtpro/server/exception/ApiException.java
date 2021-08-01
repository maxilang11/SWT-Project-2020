package de.hsrm.mi.swtpro.server.exception;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
public class ApiException {
    private final String msg;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime time;

public ApiException(String msg, Throwable throwable, HttpStatus httpStatus, ZonedDateTime time){
    this.msg= msg;
    this.throwable = throwable;
    this.httpStatus = httpStatus;
    this.time = time;
}

public String getMsg(){return msg;}
public Throwable getThrowable(){return throwable;}
public HttpStatus gethttpStatus(){return httpStatus;}
public ZonedDateTime gettime(){return time;}

}