public class TicketGeneral extends Ticket implements InterfazTicket{


    /*Constructor*/
    public TicketGeneral(int numero, float precioBase) {
        super(numero, precioBase);
    }

    public void solicitarUpgrade() {
        /*Se tendria que fijar si hay tickets VIP disponibles en el Evento del ticket
         y en caso de que si, "devolverTicket" de la clase Persona y
         cuando haga toda la parte de si, se saca la Persona del ticket con "eliminarPersona".
         En caso de que no, no se permite el Upgrade*/
    }

    public float calcularPrecio() {
        float precio = 0;/*cosas que den el precio(Precio base + porcentaje o numero) - "Descuento"(de Persona)*/
        return precio;
    }
}
