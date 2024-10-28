/*
 * MensajeOperacion.java
 */
package mensajes;

/**
 * 
 * @author Juventino López García - 00000248547 - 27/10/2024
 */
public class MensajeOperacion extends Mensaje {

    private String operacion;
    
    private float numero1;
    
    private float numero2;

    public MensajeOperacion() {
    }

    public MensajeOperacion(String operacion, float numero1, float numero2) {
        tipo = "operacion";
        this.operacion = operacion;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public String getOperacion() {
        return operacion;
    }

    public float getNumero1() {
        return numero1;
    }

    public void setNumero1(float numero1) {
        this.numero1 = numero1;
    }

    public float getNumero2() {
        return numero2;
    }

    public void setNumero2(float numero2) {
        this.numero2 = numero2;
    }
    
}
