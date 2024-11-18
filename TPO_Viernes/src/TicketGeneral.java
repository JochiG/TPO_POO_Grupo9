public class TicketGeneral extends Ticket implements InterfazTicket{


    /*Constructor*/
    public TicketGeneral(int numero, float precioBase, Evento eventoasignado) {
        super(numero, precioBase, eventoasignado);
    }

    public void solicitarUpgrade() {
        try {
            // Verifica si el ticket general tiene un evento asignado
            if (this.getEventoAsignado() == null) {
                throw new NullPointerException("No se ha asignado un evento a este ticket.");
            }

            Evento evento = this.getEventoAsignado();

            // Verificar si hay tickets VIP disponibles para el evento
            if (evento.getTicketsVIPSDisponibles().isEmpty()) {
                throw new IllegalStateException("No hay tickets VIP disponibles para upgrade.");
            }

            // Eliminar el ticket general de la lista de tickets comprados de la persona
            Persona propietario = this.getPropietario();
            if (propietario != null) {
                propietario.getTicketsComprados().remove(this);  // Eliminar el ticket general de la lista
            }

            // Reembolsar el ticket general en el evento
            evento.reembolsarTicket(this);

            // Crear un ticket VIP desde la lista de tickets disponibles
            TicketVIP ticketVIP = evento.getTicketsVIPSDisponibles().remove(0);  // Tomamos el primer ticket VIP disponible
            ticketVIP.asignarPersona(this.getPropietario());  // Asignamos el propietario al ticket VIP

            // Agregar el ticket VIP a la lista de tickets vendidos
            evento.getTicketsVIPSVendidos().add(ticketVIP);

            // Agregar el ticket VIP a la lista de tickets comprados de la persona
            propietario.getTicketsComprados().add(ticketVIP);

            // Mostrar mensaje de éxito
            System.out.println("Upgrade a VIP realizado con éxito.");
        } catch (NullPointerException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }


    public float calcularPrecio() {
        float precio = this.getPrecioBase() - (this.getPrecioBase() * (this.getPropietario().Descuento() / 100.0f));
        return precio;
    }
}
