package views;

import models.Cliente;
import services.ClienteService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Gestión de Clientes ===");
                System.out.println("1. Insertar Cliente");
                System.out.println("2. Consultar Clientes");
                System.out.println("3. Actualizar Cliente");
                System.out.println("4. Eliminar Cliente");
                System.out.println("5. Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (opcion) {
                        case 1:
                            System.out.print("Nombre: ");
                            String nombre = scanner.nextLine();
                            System.out.print("Email: ");
                            String email = scanner.nextLine();
                            clienteService.insertarCliente(new Cliente(0, nombre, email));
                            System.out.println("Cliente insertado con éxito.");
                            break;

                        case 2:
                            System.out.println("=== Lista de Clientes ===");
                            for (Cliente cliente : clienteService.obtenerClientes()) {
                                System.out.println(cliente);
                            }
                            break;

                        case 3:
                            System.out.print("ID del Cliente a actualizar: ");
                            int idActualizar = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nuevo Nombre: ");
                            String nuevoNombre = scanner.nextLine();
                            System.out.print("Nuevo Email: ");
                            String nuevoEmail = scanner.nextLine();
                            clienteService.actualizarCliente(new Cliente(idActualizar, nuevoNombre, nuevoEmail));
                            System.out.println("Cliente actualizado con éxito.");
                            break;

                        case 4:
                            System.out.print("ID del Cliente a eliminar: ");
                            int idEliminar = scanner.nextInt();
                            clienteService.eliminarCliente(idEliminar);
                            System.out.println("Cliente eliminado con éxito.");
                            break;

                        case 5:
                            System.out.println("Saliendo del sistema...");
                            System.exit(0);

                        default:
                            System.out.println("Opción no válida.");
                    }
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
