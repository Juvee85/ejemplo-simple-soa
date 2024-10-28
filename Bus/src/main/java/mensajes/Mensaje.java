/*
 * Mensaje.java
 */
package mensajes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * @author Juventino López García - 00000248547 - 28/10/2024
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(MensajeOperacion.class),
    @JsonSubTypes.Type(MensajeRegistro.class),
    @JsonSubTypes.Type(MensajeResultado.class)}
)
public class Mensaje {

    protected String tipo;
    
     public String getTipo() {
        return tipo;
    }
}
