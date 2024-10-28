package projects.serviciosuma;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import mensajes.MensajeOperacion;
import mensajes.MensajeRegistro;
import mensajes.MensajeResultado;

/**
 *
 * @author Juventino López García - 00000248547
 */
public class ServicioSuma {

    private Socket socketBus;
    private InputStream din;
    private OutputStream out;
    private ServerSocket serverSocket;

    public ServicioSuma() throws IOException {
        serverSocket = new ServerSocket(18005);
        socketBus = new Socket("localhost", 17000);
        din = socketBus.getInputStream();
        out = socketBus.getOutputStream();

        MensajeRegistro msj = new MensajeRegistro("suma", 18005);

        ObjectMapper objectMapper = new ObjectMapper();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.writeValue(writer, msj);

        while (true) {
            Socket socket = serverSocket.accept();

            ObjectMapper mapper = new ObjectMapper();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            MensajeOperacion mensaje = mapper.readValue(reader, MensajeOperacion.class);
            float resultado = suma(mensaje.getNumero1(), mensaje.getNumero2());

            MensajeResultado mensajeResultado = new MensajeResultado(resultado);
            OutputStream outputStream = socket.getOutputStream();

            writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            mapper.writeValue(writer, mensajeResultado);
        }
    }

    public static void main(String[] args) throws IOException {
        new ServicioSuma();
    }

    public float suma(float num1, float num2) {
        return num1 + num2;
    }
}
