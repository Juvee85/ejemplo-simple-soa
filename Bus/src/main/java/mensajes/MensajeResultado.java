/*
 * MensajeResultado.java
 */
package mensajes;

/**
 *
 * @author Juventino López García - 00000248547 - 27/10/2024
 */
public class MensajeResultado extends Mensaje {

    private float resultado;

    public MensajeResultado() {
    }

    public MensajeResultado(float result) {
        tipo = "resultado";
        this.resultado = result;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float result) {
        this.resultado = result;
    }

}
