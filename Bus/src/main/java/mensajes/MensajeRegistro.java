/*
 * MensajeRegistro.java
 */
package mensajes;

/**
 * 
 * @author Juventino López García - 00000248547 - 28/10/2024
 */
public class MensajeRegistro extends Mensaje {

    private String tipoServicio;
    
    private int puerto;

    public MensajeRegistro() {
    }

    public MensajeRegistro(String tipoServicio, int puerto) {
        tipo = "registro";
        this.tipoServicio = tipoServicio;
        this.puerto = puerto;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    
}
