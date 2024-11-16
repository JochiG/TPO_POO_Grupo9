public class Persona {
    private int Id;
    private String Nombre;
    private String Email;
    private int Edad;

    /*Hay que agregar una coleccion de tickets comprados*/

    /*Constructor*/
    public Persona(int id, String nombre, String email, int edad) {
        Id = id;
        Nombre = nombre;
        Email = email;
        Edad = edad;
    }

    public void comprarTicket(Evento evento){
        /*Pregunta si quiere VIP o General, Se fija que haya tickets disponibles, en caso de que SI:
            llama al metodo de evento "VenderTicket" del primer ticket de la coleccion deseada y
            al metodo de ticket "AsignarPersona".
          En caso de que NO:
            No se permite comprar el ticket*/
    }

    public void devolverTicket(Evento evento){
        /*Llama al metodo "reembolsarTicket" de tal evento
         y se saca el ticket de la coleccion de "TicketsComprados" de Persona.
         y se dice "Ticket reembolsado o algo asi"*/
    }

    public void mostrarTicketsComprados(){
        /*Se muestran todos los tickets comprados, que estarian en una coleccion*/
    }

    public int Descuento(){
        /*Se divide por rango etario, y devuelve un porcentaje */
    }

    public boolean cumpleReestriccion(Evento evento){
        /*Se consulta la edad minima de el evento y se compara con la edad de la persona
         dependiendo de eso, se devuelve true o false*/
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
}
