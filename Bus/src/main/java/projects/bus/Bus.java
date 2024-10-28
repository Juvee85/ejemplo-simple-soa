package projects.bus;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import mensajes.Mensaje;

/**
 *
 * @author Juventino López García - 00000248547
 */
public class Bus {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(17000);

        while (true) {
            Socket cliente = socket.accept();

            ObjectMapper mapper = new ObjectMapper();
          
            BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
          
            Mensaje mensaje = mapper.readValue(reader, Mensaje.class);

            Socket socketRegistro = new Socket("localhost", 18001);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketRegistro.getOutputStream()));

            mapper.writeValue(writer, mensaje);
        }
    }
}
