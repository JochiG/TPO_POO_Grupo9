public class Ticket {
    private int Numero;
    private float PrecioBase;
    private boolean Disponible;
    private Evento EventoAsignado;
    private Persona Propietario;

    /*Constructor*/
    public Ticket(int numero, float precioBase) {
        this.Disponible = true;
        this.EventoAsignado = null;
        this.Propietario = null;
        this.Numero = numero;
        this.PrecioBase = precioBase;
    }

    public void asignarPersona(Persona persona){
        this.Propietario = persona;
        Disponible = false;
    }

    public void eliminarPersona(){
        this.Propietario = null;
    }

    /*Getters and Setters*/
    public Evento getEventoAsignado() {
        return EventoAsignado;
    }

    public void setEventoAsignado(Evento eventoAsignado) {
        EventoAsignado = eventoAsignado;
    }

    public boolean estaDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }

}
