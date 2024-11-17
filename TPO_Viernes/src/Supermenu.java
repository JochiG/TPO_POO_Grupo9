import java.util.ArrayList;
import java.util.Scanner;

public class Supermenu {
    private ArrayList<Persona> personas;
    private ArrayList<Evento> eventos;
    private Scanner scanner;

    public Supermenu() {
        personas = new ArrayList<>();
        eventos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== SUPERMENU ===");
            System.out.println("1. Gestión de Personas");
            System.out.println("2. Gestión de Eventos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestionarPersonas();
                    break;
                case 2:
                    gestionarEventos();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void gestionarPersonas() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== GESTIÓN DE PERSONAS ===");
            System.out.println("1. Ingresar Persona");
            System.out.println("2. Gestionar Personas");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ingresarPersona();
                    break;
                case 2:
                    verPersonas();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void ingresarPersona() {
        System.out.print("Ingrese el nombre de la persona: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el email de la persona: ");
        String email = scanner.next();
        System.out.print("Ingrese la edad de la persona: ");
        int edad = scanner.nextInt();

        Persona nuevaPersona = new Persona(personas.size() + 1, nombre, email, edad);
        personas.add(nuevaPersona);
        System.out.println("Persona ingresada con éxito.");
    }

    private void verPersonas() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }

        System.out.println("=== LISTA DE PERSONAS ===");
        for (int i = 0; i < personas.size(); i++) {
            System.out.println((i + 1) + ". " + personas.get(i).getNombre());
        }

        System.out.print("Seleccione una persona: ");
        int seleccion = scanner.nextInt();
        if (seleccion < 1 || seleccion > personas.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Persona persona = personas.get(seleccion - 1);
        mostrarMenuPersona(persona);
    }

    private void mostrarMenuPersona(Persona persona) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENÚ DE GESTIÓN DE TICKETS ===");
            System.out.println("1. Comprar Ticket");
            System.out.println("2. Ver Tickets Comprados");
            System.out.println("3. Devolver Ticket");
            System.out.println("4. Solicitar Upgrade de Ticket");
            System.out.println("5. Ver Beneficios del Ticket VIP");
            System.out.println("6. Volver a la gestión de personas");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (!eventos.isEmpty()) {
                        persona.comprarTicket(eventos);
                    } else {
                        System.out.println("No hay eventos disponibles");
                    }
                    break;
                case 2:
                    persona.mostrarTicketsComprados();
                    break;
                case 3:
                    devolverTicket(persona);
                    break;
                case 4:
                    persona.personaSolicitaUpgrade();
                    break;
                case 5:
                    persona.personaObtieneBeneficios();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void devolverTicket(Persona persona) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos disponibles para devolver tickets.");
            return;
        }
        persona.devolverTicket();
    }

    private void gestionarEventos() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== GESTIÓN DE EVENTOS ===");
            System.out.println("1. Crear Evento");
            System.out.println("2. Ver Mis Eventos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearEvento();
                    break;
                case 2:
                    verMisEventos();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void crearEvento() {
        System.out.print("Ingrese el nombre del evento: ");
        scanner.nextLine(); // Consumir el salto de línea sobrante del buffer
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la fecha del evento: ");
        String fecha = scanner.nextLine();

        System.out.print("Ingrese la ubicación del evento: ");
        String ubicacion = scanner.nextLine();

        System.out.print("Ingrese la edad mínima para el evento: ");
        int edadMinima = scanner.nextInt();

        System.out.print("Ingrese la cantidad de tickets generales: ");
        int ticketsGenerales = scanner.nextInt();

        System.out.print("Ingrese la cantidad de tickets VIP: ");
        int ticketsVIP = scanner.nextInt();

        Evento nuevoEvento = new Evento(nombre, fecha, ubicacion, edadMinima, ticketsGenerales, ticketsVIP);
        eventos.add(nuevoEvento);
        System.out.println("Evento creado con éxito.");
    }

    private void verMisEventos() {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos creados.");
            return;
        }

        System.out.println("=== LISTA DE EVENTOS ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getNombre());
        }

        System.out.print("Seleccione un evento: ");
        int seleccion = scanner.nextInt();
        if (seleccion < 1 || seleccion > eventos.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Evento evento = eventos.get(seleccion - 1);
        mostrarMenuEvento(evento);
    }

    private void mostrarMenuEvento(Evento evento) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENÚ DE EVENTO ===");
            System.out.println("1. Ver Tickets Disponibles");
            System.out.println("2. Ver Tickets Vendidos");
            System.out.println("3. Volver a la gestión de eventos");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    evento.ticketsDisponibles();
                    break;
                case 2:
                    evento.ticketsVendidos();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
