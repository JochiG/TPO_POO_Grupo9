public class Evento {
    private String Nombre;
    private String Fecha;
    private String Ubicacion;
    private int EdadMinima;


    /*agregar las colecciones de los tickets disponibles generales
     y tickets disponibles VIP y tickets vendidos Generales y Tickets Vendidos VIPS*/

    /*Constructor*/
    public Evento(String nombre, String fecha, String ubicacion, int edadMinima, int TicketsGenerales, int TicketsVIPS) {
        Nombre = nombre;
        Fecha = fecha;
        Ubicacion = ubicacion;
        EdadMinima = edadMinima;
        /*Tomar como dato la cantidad de tickets necesarios y crear las colecciones con esa cantidad de tickets,
         tanto para Generales y VIPS*/
    }

    public void venderTicket(Ticket ticket) {
        /*Se pasa de tickets disponibles a vendidos*/
    }

    public void reembolsarTicket(Ticket ticket) {
        /*Se saca el ticket de vendidos y se pasa a disponible
         y se hace un "setDisponible = true"*/
    }

    public void ticketsDisponibles(){
        /*Devuelve coleccion de tickets disponibles*/
    }

    public void ticketsVendidos(){
        /*Devuelve coleccion de tickets vendidos, dividido por Generales y VIP,
         dando un TOTAL*/
    }

    /*Getters*/

    public int getEdadMinima() {
        return EdadMinima;
    }
}
