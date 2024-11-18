import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            try {
                System.out.println("\n=== SUPERMENU ===");
                System.out.println("1. Gestión de Personas");
                System.out.println("2. Gestión de Eventos");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

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
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un numero.");
            }
        }
    }

    private void gestionarPersonas() {
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\n=== GESTIÓN DE PERSONAS ===");
                System.out.println("1. Ingresar Persona");
                System.out.println("2. Gestionar Personas");
                System.out.println("3. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

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
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un numero valido.");
            }
        }
    }

    private void ingresarPersona() {
        try {
            String nombre;
            // Validación del nombre para que contenga solo letras
            while (true) {
                System.out.print("Ingrese el nombre de la persona: ");
                nombre = scanner.nextLine();

                if (nombre.matches("[a-zA-Z ]+")) {  // Expresión regular para letras y espacios
                    break; // Si el nombre es válido, salimos del bucle
                } else {
                    System.out.println("El nombre solo debe contener letras y espacios. Intente de nuevo.");
                }
            }

            String email;
            while (true) {
                System.out.print("Ingrese el email de la persona: ");
                email = scanner.nextLine();
                if (email.contains("@")) {
                    break;
                } else {
                    System.out.println("El email debe contener un '@'. Por favor, ingrese un email válido.");
                }
            }

            int edad = -1; // Inicializamos la edad con un valor inválido
            while (edad < 0) {
                try {
                    System.out.print("Ingrese la edad de la persona: ");
                    edad = Integer.parseInt(scanner.nextLine());

                    if (edad < 0) {
                        System.out.println("La edad no puede ser negativa. Intente de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: La edad debe ser un número entero.");
                }
            }

            Persona nuevaPersona = new Persona(personas.size() + 1, nombre, email, edad);
            personas.add(nuevaPersona);
            System.out.println("Persona ingresada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
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
        scanner.nextLine();
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
            try {
                System.out.println("\n=== MENÚ DE GESTIÓN DE TICKETS ===");
                System.out.println("1. Comprar Ticket");
                System.out.println("2. Ver Tickets Comprados");
                System.out.println("3. Devolver Ticket");
                System.out.println("4. Solicitar Upgrade de Ticket");
                System.out.println("5. Ver Beneficios del Ticket VIP");
                System.out.println("6. Volver a la gestión de personas");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

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
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un numero.");
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
            try {
                System.out.println("\n=== GESTIÓN DE EVENTOS ===");
                System.out.println("1. Crear Evento");
                System.out.println("2. Ver Mis Eventos");
                System.out.println("3. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

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
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un numero.");
            }
        }
        }

    private void crearEvento() {
        System.out.print("Ingrese el nombre del evento: ");
        String nombre = scanner.nextLine();

        // Validación de la fecha con LocalDate
        LocalDate fecha = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (fecha == null) {
            System.out.print("Ingrese la fecha del evento (dd/MM/aaaa): ");
            String fechaInput = scanner.nextLine();

            try {
                // Parseamos la fecha ingresada usando LocalDate
                fecha = LocalDate.parse(fechaInput, formatter);

                // Comprobamos si la fecha es válida (mayor o igual a la fecha actual)
                LocalDate fechaActual = LocalDate.now();
                if (fecha.isBefore(fechaActual)) {
                    System.out.println("La fecha del evento debe ser igual o posterior a la fecha actual.");
                    fecha = null;  // Si la fecha es anterior a la actual, pedimos la fecha nuevamente
                }

            } catch (DateTimeParseException e) {
                System.out.println("La fecha ingresada no tiene el formato correcto. Debe ser dd/MM/aaaa.");
            }
        }

        System.out.print("Ingrese la ubicación del evento: ");
        String ubicacion = scanner.nextLine();

        // Edad mínima con validación
        int edadMinima = -1;
        while (edadMinima < 0) {
            try {
                System.out.print("Ingrese la edad mínima para el evento: ");
                edadMinima = scanner.nextInt();
                if (edadMinima < 0) {
                    throw new IllegalArgumentException("La edad mínima no puede ser negativa. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero. Intente de nuevo.");
                scanner.nextLine();  // Limpiar el buffer de entrada
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Tickets generales con validación
        int ticketsGenerales = -1;
        while (ticketsGenerales <= 0) { // Ahora pide más de 0
            try {
                System.out.print("Ingrese la cantidad de tickets generales: ");
                ticketsGenerales = scanner.nextInt();
                if (ticketsGenerales <= 0) {
                    throw new IllegalArgumentException("La cantidad de tickets generales debe ser mayor que 0. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero. Intente de nuevo.");
                scanner.nextLine();  // Limpiar el buffer de entrada
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Tickets VIP con validación
        int ticketsVIP = -1;
        while (ticketsVIP <= 0) { // Ahora pide más de 0
            try {
                System.out.print("Ingrese la cantidad de tickets VIP: ");
                ticketsVIP = scanner.nextInt();
                if (ticketsVIP <= 0) {
                    throw new IllegalArgumentException("La cantidad de tickets VIP debe ser mayor que 0. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero. Intente de nuevo.");
                scanner.nextLine();  // Limpiar el buffer de entrada
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Crear evento y agregarlo a la lista
        Evento nuevoEvento = new Evento(nombre, fecha.format(formatter), ubicacion, edadMinima, ticketsGenerales, ticketsVIP);
        eventos.add(nuevoEvento);
        System.out.println("Evento creado con éxito.");
        scanner.nextLine();  // Limpiar el buffer
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

        int seleccion = -1;
        while (seleccion < 1 || seleccion > eventos.size()) {
            System.out.print("Seleccione un evento: ");
            try {
                seleccion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer del scanner
                if (seleccion < 1 || seleccion > eventos.size()) {
                    System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                scanner.nextLine();  // Limpiar el buffer del scanner en caso de error
            }
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

            int opcion = -1;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer después de leer el número

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
            } catch (InputMismatchException e) {
                // Captura la excepción si el usuario ingresa algo que no es un número
                System.out.println("Entrada inválida. Debe ingresar un número entero. Intente de nuevo.");
                scanner.nextLine();  // Limpiar el buffer para evitar un ciclo infinito
            }
        }
    }
}

