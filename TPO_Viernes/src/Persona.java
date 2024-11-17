import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Persona {
    private int Id;
    private String Nombre;
    private String Email;
    private int Edad;

    ArrayList<Ticket> TicketsComprados;
    /*Hay que agregar una coleccion de tickets comprados*/

    /*Constructor*/
    public Persona(int id, String nombre, String email, int edad) {
        Id = id;
        Nombre = nombre;
        Email = email;
        Edad = edad;
        TicketsComprados = new ArrayList();
    }

    public void comprarTicket(ArrayList<Evento> eventosDisponibles) {
        /*Pregunta si quiere VIP o General, Se fija que haya tickets disponibles, en caso de que SI:
            llama al metodo de evento "VenderTicket" del primer ticket de la coleccion deseada y
            al metodo de ticket "AsignarPersona".
          En caso de que NO:
            No se permite comprar el ticket*/
        // Mostrar los eventos disponibles
        System.out.println("=== Lista de eventos disponibles ===");
        for (int i = 0; i < eventosDisponibles.size(); i++) {
            Evento evento = eventosDisponibles.get(i);
            System.out.println((i + 1) + ". " + evento.getNombre() + " - Fecha: " + evento.getFecha() + " - Edad Requerida: " + evento.getEdadMinima());
        }

        // Preguntar al usuario qué evento desea
        System.out.println("Seleccione el evento al que desea acudir: ");
        Scanner scanner = new Scanner(System.in);
        int eventoSeleccionado = scanner.nextInt();

        if (eventoSeleccionado < 1 || eventoSeleccionado > eventosDisponibles.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        // Obtener el evento seleccionado
        Evento evento = eventosDisponibles.get(eventoSeleccionado - 1);
        if (evento.getEdadMinima() > this.getEdad()) {
            System.out.println("No cumple con la edad necesaria para ingresar el evento.");
        } else {
            // Preguntar el tipo de ticket
            System.out.println("Seleccione el tipo de ticket (1 para General - $500, 2 para VIP - $1000 (Solo Acceso Exlusivo)):");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                if (!evento.getTicketsGeneralesDisponibles().isEmpty()) {
                    TicketGeneral ticket = (TicketGeneral) evento.venderTicket(1);
                    ticket.setEventoAsignado(evento);  // Asignamos el evento al ticket
                    ticket.asignarPersona(this);
                    // Agregar a la lista de tickets comprados
                    this.TicketsComprados.add(ticket);
                    System.out.println("Ticket General comprado con éxito.");
                } else {
                    System.out.println("No hay tickets generales disponibles.");
                }
            } else if (opcion == 2) {
                if (!evento.getTicketsVIPSDisponibles().isEmpty()) {
                    TicketVIP ticket = (TicketVIP) evento.venderTicket(2);
                    ticket.setEventoAsignado(evento);  // Asignamos el evento al ticket
                    ticket.asignarPersona(this);
                    this.TicketsComprados.add(ticket);
                    System.out.println("Ticket VIP comprado con éxito.");
                } else {
                    System.out.println("No hay tickets VIP disponibles.");
                }
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    public void devolverTicket(){
        /*Llama al metodo "reembolsarTicket" de tal evento
         y se saca el ticket de la coleccion de "TicketsComprados" de Persona.
         y se dice "Ticket reembolsado o algo asi"*/

        // Verificar si la persona tiene tickets comprados
        if (TicketsComprados.isEmpty()) {
            System.out.println("No tienes tickets comprados.");
            return;
        }

        // Mostrar los nombres de los eventos de los tickets comprados
        System.out.println("Tickets comprados:");
        for (int i = 0; i < TicketsComprados.size(); i++) {
            Ticket ticket = TicketsComprados.get(i);
            String tipoTicket = (ticket instanceof TicketVIP) ? "VIP" : "General";
            System.out.println(i + 1 + ". Evento: " + ticket.getEventoAsignado().getNombre() + " | Ticket Nº: " + ticket.getNumero() + " | Tipo: " + tipoTicket);
        }

        // Solicitar que la persona elija el ticket a devolver
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige el número del ticket que deseas devolver:");
        int opcion = scanner.nextInt();

        // Verificar si la opción es válida
        if (opcion < 1 || opcion > TicketsComprados.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        // Obtener el ticket seleccionado
        Ticket ticketSeleccionado = TicketsComprados.get(opcion - 1);

        // Llamar al méthod reembolsarTicket del evento y eliminar el ticket de la colección
        ticketSeleccionado.getEventoAsignado().reembolsarTicket(ticketSeleccionado);
        TicketsComprados.remove(ticketSeleccionado);

        // Confirmar que el ticket ha sido reembolsado
        System.out.println("Ticket reembolsado con éxito.");
    }

    public void mostrarTicketsComprados(){
        /*Se muestran todos los tickets comprados, que estarian en una coleccion*/

        if (TicketsComprados.isEmpty()) {
            System.out.println("No has comprado tickets.");
            return;
        }
        for (int i = 0; i < TicketsComprados.size(); i++) {
            Ticket ticket = TicketsComprados.get(i);
            String tipoticket = (ticket instanceof TicketVIP) ? "VIP" : "General";
            System.out.println(i + 1 + ". Evento: " + ticket.getEventoAsignado().getNombre() + " | Ticket Nº: " + ticket.getNumero() + " | Tipo: " + tipoticket);
        }
    }

    public void personaSolicitaUpgrade() {
        // Asegurarse de que la persona tenga tickets comprados
        if (this.getTicketsComprados().isEmpty()) {
            System.out.println("No tienes tickets comprados para solicitar un upgrade.");
        } else {
            // Mostrar los tickets comprados con su número de orden, nombre del evento y tipo de ticket
            System.out.println("Tickets comprados:");
            for (int i = 0; i < this.getTicketsComprados().size(); i++) {
                Ticket ticket = this.getTicketsComprados().get(i);
                String tipoTicket = (ticket instanceof TicketVIP) ? "VIP" : "General";
                System.out.println((i + 1) + ". Evento: " + ticket.getEventoAsignado().getNombre() + " | Ticket Nº: " + ticket.getNumero() + " | Tipo: " + tipoTicket);
            }

            // Solicitar que el usuario elija el ticket por el número de orden
            Scanner scannerUpgrade = new Scanner(System.in);
            System.out.print("Elige el número del ticket que deseas upgradear: ");
            int opcion = scannerUpgrade.nextInt();

            // Verificar si la opción es válida
            if (opcion < 1 || opcion > this.getTicketsComprados().size()) {
                System.out.println("Opción inválida.");
            } else {
                // Obtener el ticket seleccionado
                Ticket ticketSeleccionado = this.getTicketsComprados().get(opcion - 1);

                // Verificar si el ticket es general y permitir el upgrade, si es VIP, no se puede hacer upgrade
                if (ticketSeleccionado instanceof TicketGeneral) {
                    TicketGeneral ticketGeneral = (TicketGeneral) ticketSeleccionado;
                    ticketGeneral.solicitarUpgrade();
                } else {
                    TicketVIP ticketVIP = (TicketVIP) ticketSeleccionado;
                    ticketVIP.solicitarUpgrade();
                }
            }
        }
    }

    public void personaObtieneBeneficios() {

        // Asegurarse de que la persona tenga tickets comprados
        if (this.getTicketsComprados().isEmpty()) {
            System.out.println("No tienes tickets comprados.");
        } else {
            // Filtrar y mostrar solo los tickets VIP
            ArrayList<TicketVIP> ticketsVIP = new ArrayList<>();
            for (int i = 0; i < this.getTicketsComprados().size(); i++) {
                Ticket ticket = this.getTicketsComprados().get(i);
                if (ticket instanceof TicketVIP) {
                    ticketsVIP.add((TicketVIP) ticket);
                }
            }

            // Verificar si la persona tiene tickets VIP
            if (ticketsVIP.isEmpty()) {
                System.out.println("No tienes tickets VIP.");
            } else {
                // Mostrar los tickets VIP disponibles
                System.out.println("Tickets VIP comprados:");
                for (int i = 0; i < ticketsVIP.size(); i++) {
                    TicketVIP ticketVIP = ticketsVIP.get(i);
                    System.out.println((i + 1) + ". Evento: " + ticketVIP.getEventoAsignado().getNombre() + " | Ticket Nº: " + ticketVIP.getNumero());
                }

                // Solicitar que el usuario elija el ticket por número de orden
                Scanner scannerBeneficios = new Scanner(System.in);
                System.out.print("Elige el número del ticket VIP para ver los beneficios: ");
                int opcionVIP = scannerBeneficios.nextInt();

                // Verificar si la opción es válida
                if (opcionVIP < 1 || opcionVIP > ticketsVIP.size()) {
                    System.out.println("Opción inválida.");
                } else {
                    // Obtener el ticket VIP seleccionado
                    TicketVIP ticketVIPSeleccionado = ticketsVIP.get(opcionVIP - 1);

                    // Mostrar los beneficios del ticket VIP seleccionado
                    System.out.println("Beneficios del Ticket VIP " + ticketVIPSeleccionado.getNumero() + ":");
                    ticketVIPSeleccionado.obtenerBeneficios();
                    System.out.println("Si quiere mas beneficios, tendra que solicitar un upgrade en su ticket");
                }
            }
        }
    }

    public int Descuento(){
        /*Se divide por rango etario, y devuelve un porcentaje */
        if (Edad < 18) {
            return 50; // Descuento del 50% para menores de 18
        } else if (Edad >= 60) {
            return 30; // Descuento del 30% para mayores de 60
        } else {
            return 0; // Sin descuento
        }
    }

    public boolean cumpleReestriccion(Evento evento){
        /*Se consulta la edad minima de el evento y se compara con la edad de la persona
         dependiendo de eso, se devuelve true o false*/
        return Edad >= evento.getEdadMinima();
    }

    /*Getters*/

    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public int getEdad() {
        return Edad;
    }

    public ArrayList<Ticket> getTicketsComprados() {
        return TicketsComprados;
    }
}
