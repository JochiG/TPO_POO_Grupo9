public class TicketVIP extends Ticket implements InterfazTicket{
    private boolean AccesoExclusivo;
    private boolean IncluyeCatering;
    private boolean IncluyeParking;
    private boolean AccesoBackstage;
    private boolean MeetNGreet;

    /*hacer coleccion de beneficios que sean true*/
    /*Agregar constructor*/

    public TicketVIP(int Numero, float PrecioBase, boolean incluyeCatering, boolean incluyeParking, boolean accesoBackstage, boolean meetNGreet) {
        super(Numero, PrecioBase);
        this.AccesoExclusivo = true;
        this.IncluyeCatering = incluyeCatering;
        this.IncluyeParking = incluyeParking;
        this.AccesoBackstage = accesoBackstage;
        this.MeetNGreet = meetNGreet;
    }

    public void solicitarUpgrade() {
        /*Se fija si NO tiene todos los beneficios, en caso de que NO:
            darle un listado de cuales NO tiene, y preguntarle cual quiere (poner precio),
            luego de agregarlo, fijarse si tiene todos los beneficios, en caso de que NO:
                preguntarle si quiere agregar otro, en caso de que NO,
            termina proceso, en caso de que SI, repetir proceso.
         En caso de SI tener todos los beneficios, no permitir el Upgrade*/

    }

    public float calcularPrecio() {
        float precio = 0;/*cosas que den el precio(Precio base + porcentaje o numero) - "Descuento"(de Persona)*/
        return precio;
    }

    public void obtenerBeneficios(){
        /*devolver la coleccion de beneficios que sean true*/
    }

    /*Getters and Setters*/

    public boolean isIncluyeCatering() {
        return IncluyeCatering;
    }

    public void setIncluyeCatering(boolean incluyeCatering) {
        IncluyeCatering = incluyeCatering;
    }

    public boolean isAccesoExclusivo() {
        return AccesoExclusivo;
    }

    public boolean isIncluyeParking() {
        return IncluyeParking;
    }

    public void setIncluyeParking(boolean incluyeParking) {
        IncluyeParking = incluyeParking;
    }

    public boolean isAccesoBackstage() {
        return AccesoBackstage;
    }

    public void setAccesoBackstage(boolean accesoBackstage) {
        AccesoBackstage = accesoBackstage;
    }

    public boolean isMeetNGreet() {
        return MeetNGreet;
    }

    public void setMeetNGreet(boolean meetNGreet) {
        MeetNGreet = meetNGreet;
    }
}
