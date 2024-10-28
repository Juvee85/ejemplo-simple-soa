package projects.conexion;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajes.Mensaje;

/**
 *
 * @author Juventino López García - 00000248547
 */
public class Conexion implements Runnable {

    Socket server;
    InputStream din;
    OutputStream out;

    public Conexion() {
        try {
            server = new Socket("localhost", 18000);
            din = server.getInputStream();
            out = server.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarSuma(float num1, float num2) throws IOException {
        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("suma");
        mensaje.setNumero1(num1);
        mensaje.setNumero2(num2);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, mensaje);
    }

    public void solicitarResta(float num1, float num2) throws IOException {
        Mensaje mensaje = new Mensaje();
        mensaje.setTipo("resta");
        mensaje.setNumero1(num1);
        mensaje.setNumero2(num2);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, mensaje);
    }

    public float resultado() {
        return 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ObjectMapper objectMapper = new ObjectMapper();
                Mensaje msg = objectMapper.readValue(din, Mensaje.class);
            }

        } catch (IOException ex) {
            System.out.println("Error del servidor: " + ex.getMessage());
        }
    }
}
