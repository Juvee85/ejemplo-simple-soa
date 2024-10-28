package projects.conexion;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mensajes.MensajeOperacion;
import mensajes.MensajeRegistro;
import mensajes.MensajeResultado;

/**
 *
 * @author Juventino López García - 00000248547
 */
public class Conexion implements Runnable {

    private Socket socketBus;
    private InputStream din;
    private OutputStream out;
    private BufferedWriter writer;
    private ServerSocket serverSocket;

    public Conexion() {
        try {
            serverSocket = new ServerSocket(18002);
            socketBus = new Socket("localhost", 17000);
            din = socketBus.getInputStream();
            out = socketBus.getOutputStream();
            
            MensajeRegistro msj = new MensajeRegistro("cliente", 18002);
            
            ObjectMapper objectMapper = new ObjectMapper();
           writer = new BufferedWriter(new OutputStreamWriter(out));
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.writeValue(writer, msj);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarSuma(float num1, float num2) {
        MensajeOperacion mensaje = new MensajeOperacion("suma", num1, num2);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(writer, mensaje);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void solicitarResta(float num1, float num2) throws IOException {
        MensajeOperacion mensaje = new MensajeOperacion("resta", num1, num2);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, mensaje);
    }

    public float obtenerResultado() {
        return 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectMapper mapper = new ObjectMapper();
                
                MensajeResultado resultado = mapper
                        .readValue(socket.getInputStream(), MensajeResultado.class);
                
                
            }

        } catch (IOException ex) {
            System.out.println("Error del servidor: " + ex.getMessage());
        }
    }
}
