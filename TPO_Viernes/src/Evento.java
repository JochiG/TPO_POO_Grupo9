import java.util.ArrayList;
import java.util.Calendar;

public class Evento {
    private String Nombre;
    private String Fecha;
    private String Ubicacion;
    private int EdadMinima;

    private ArrayList<TicketGeneral> TicketsGeneralesDisponibles;
    private ArrayList<TicketVIP> TicketsVIPSDisponibles;
    private ArrayList<TicketGeneral> TicketsGeneralesVendidos;
    private ArrayList<TicketVIP> TicketsVIPSVendidos;

    // Constructor
    public Evento(String nombre, String fecha, String ubicacion, int edadMinima, int TicketsGenerales, int TicketsVIPS) {
        Nombre = nombre;
        Fecha = fecha;
        Ubicacion = ubicacion;
        EdadMinima = edadMinima;

        // Inicializamos las listas
        TicketsGeneralesDisponibles = new ArrayList<>();
        TicketsVIPSDisponibles = new ArrayList<>();
        TicketsGeneralesVendidos = new ArrayList<>();
        TicketsVIPSVendidos = new ArrayList<>();

        // Crear y agregar la cantidad especificada de tickets generales
        for (int i = 0; i < TicketsGenerales; i++) {
            TicketsGeneralesDisponibles.add(new TicketGeneral(i+1, 500, this));
        }

        // Crear y agregar la cantidad especificada de tickets VIP
        for (int i = 0; i < TicketsVIPS; i++) {
            TicketsVIPSDisponibles.add(new TicketVIP(i+1, 1000, this, false, false, false, false));
        }
    }

    public Ticket venderTicket(int opcion) {
        /*Se pasa de tickets disponibles a vendidos*/
        switch (opcion) {
            case 1: { // Ticket General
                TicketGeneral ticket = TicketsGeneralesDisponibles.remove(0); // Tomamos el primer ticket disponible
                ticket.setDisponible(false);
                TicketsGeneralesVendidos.add(ticket);
                return ticket; // Devolvemos el número del ticket vendido
            }
            case 2: { // Ticket VIP
                TicketVIP ticket = TicketsVIPSDisponibles.remove(0); // Tomamos el primer ticket VIP disponible
                ticket.setDisponible(false);
                TicketsVIPSVendidos.add(ticket);
                return ticket; // Devolvemos el número del ticket vendido
            }
        }
        return null;
    }

    public void reembolsarTicket(Ticket ticket) {
        /*Se saca el ticket de vendidos y se pasa a disponible
         y se hace un "setDisponible = true"*/
        if (ticket instanceof TicketGeneral) {
            TicketsGeneralesVendidos.remove(ticket); // Remover de la lista de vendidos
            TicketsGeneralesDisponibles.add((TicketGeneral) ticket); // Agregar a la lista de disponibles
        } else if (ticket instanceof TicketVIP) {
            TicketsVIPSVendidos.remove(ticket); // Remover de la lista de vendidos
            TicketsVIPSDisponibles.add((TicketVIP) ticket); // Agregar a la lista de disponibles
        }
        ticket.setDisponible(true); // Hacer el ticket disponible nuevamente
    }

    public void ticketsDisponibles(){
        /*Devuelve coleccion de tickets disponibles*/
        System.out.println("Tickets Generales Disponibles:" + TicketsGeneralesDisponibles.size());
        System.out.println("Tickets VIP Disponibles:" + TicketsVIPSDisponibles.size());
    }

    public void ticketsVendidos(){
        float total = 0;
        System.out.println("Tickets Generales Vendidos:");
        for (TicketGeneral ticket : TicketsGeneralesVendidos) {
            total = total + ticket.calcularPrecio();
            System.out.println("Ticket Nº: " + ticket.getNumero());
        }
        System.out.println("Tickets VIP Vendidos:");
        for (TicketVIP ticket : TicketsVIPSVendidos) {
            total = total + ticket.calcularPrecio();
            System.out.println("Ticket Nº: " + ticket.getNumero());
        }
        System.out.println("Total Tickets Vendidos: " + (TicketsGeneralesVendidos.size() + TicketsVIPSVendidos.size()));
        System.out.println("El total remunerado es: " + total);
    }

    /*Getters*/

    public int getEdadMinima() {
        return EdadMinima;
    }


    public ArrayList<TicketGeneral> getTicketsGeneralesDisponibles() {
        return TicketsGeneralesDisponibles;
    }

    public ArrayList<TicketVIP> getTicketsVIPSDisponibles() {
        return TicketsVIPSDisponibles;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getFecha() {
        return  this.Fecha;
    }

    public ArrayList<TicketVIP> getTicketsVIPSVendidos() {
        return TicketsVIPSVendidos;
    }
}
