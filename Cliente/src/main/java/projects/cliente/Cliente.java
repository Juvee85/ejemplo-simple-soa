package projects.cliente;

import java.io.IOException;
import java.util.Scanner;
import projects.conexion.Conexion;

/**
 *
 * @author Juventino López García - 00000248547
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        Conexion conexion = new Conexion();
        Thread thread = new Thread(conexion);
        thread.start();
        while (true) {
            Scanner tec = new Scanner(System.in);
            
            System.out.println("Elige un servicio");
            System.out.println("1.- Suma");
            System.out.println("2.- Resta");
            
            int opcion = tec.nextInt();
            if (opcion == 1) {
                System.out.println("Ingresa los numeros a sumar");
                conexion.solicitarSuma(tec.nextFloat(), tec.nextFloat());
            } else if (opcion == 2) {
                System.out.println("Ingresa los numeros a restar ");
                conexion.solicitarResta(tec.nextFloat(), tec.nextFloat());
            }
            
            tec.next();
        }
    }
}
