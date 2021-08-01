package de.hsrm.mi.swtpro.server.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController

/**
 * Verantowrtlich fuer Anuftrettende Fehler bei falscher URL
 */
public class CustomErrorController implements ErrorController {    
	/** 
	 * @param request
	 * @return String welcher den Fehler zurueckgibt
	 */
	@RequestMapping(value = "/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
		
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error-404";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error-500";
			}
		}
		return "error";
	}
	
	/** 
	 * @return String welcher nie aufgerufen wird
	 */
	@Override
	public String getErrorPath(){
		throw new RuntimeException("This will not be called.");
	}
}
