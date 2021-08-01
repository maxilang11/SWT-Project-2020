package de.hsrm.mi.swtpro.server.exception;
public class EXCEPTIONApiRequest extends RuntimeException{
    /**
     *The serialVersionUID is a universal version identifier for a Serializable class.
      Deserialization uses this number to ensure that a loaded class corresponds exactly to a serialized object.
       If no match is found, then an InvalidClassException is thrown.
     */
    private static final long serialVersionUID = 1L;

    public EXCEPTIONApiRequest(String msg) {
        super(msg); // Oberklasse
    }

    public EXCEPTIONApiRequest(String msg, Throwable cause){
        super(msg, cause); //Oberklasse
    }
}