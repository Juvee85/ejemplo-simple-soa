package projects.registro;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import mensajes.Mensaje;
import mensajes.MensajeOperacion;
import mensajes.MensajeRegistro;
import mensajes.MensajeResultado;

/**
 *
 * @author Juventino López García - 00000248547
 */
public class Registro {

    List<Integer> direccionesServiciosSuma;

    List<Integer> direccionesServiciosResta;

    List<Integer> direccionesClientes;

    public Registro(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        direccionesServiciosSuma = new ArrayList<>();
        direccionesServiciosResta = new ArrayList<>();
        direccionesClientes = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();

            ObjectMapper mapper = new ObjectMapper();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Mensaje mensaje = mapper.readValue(reader, Mensaje.class);

            switch (mensaje.getTipo()) {
                case "operacion" -> {
                    MensajeOperacion operacion = (MensajeOperacion) mensaje;
                    if (operacion.getOperacion().equals("suma")) {
                        for (int puerto : direccionesServiciosSuma) {
                            OutputStream outputStream = new Socket("localhost", puerto)
                                    .getOutputStream();

                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                            mapper.writeValue(writer, operacion);
                        }
                    } else if (operacion.getOperacion().equals("resta")) {
                        for (int puerto : direccionesServiciosResta) {
                            OutputStream outputStream = new Socket("localhost", puerto)
                                    .getOutputStream();
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                            mapper.writeValue(writer, operacion);
                        }
                    }
                }

                case "registro" -> {
                    MensajeRegistro registro = (MensajeRegistro) mensaje;
                    String tipo = registro.getTipoServicio();
                    switch (tipo) {
                        case "suma" ->
                            direccionesServiciosSuma.add(registro.getPuerto());
                        case "resta" ->
                            direccionesServiciosResta.add(registro.getPuerto());
                        case "cliente" ->
                            direccionesClientes.add(registro.getPuerto());
                    }
                    System.out.println("Registro exitoso!!! Tipo de servicio: " + tipo);
                }

                case "resultado" -> {
                    MensajeResultado solicitud = (MensajeResultado) mensaje;
                    for (int puerto : direccionesClientes) {
                        OutputStream outputStream = new Socket("localhost", puerto)
                                .getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                        mapper.writeValue(writer, solicitud);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Registro(18001);
    }
}
