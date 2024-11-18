import java.util.ArrayList;
import java.util.Scanner;

public class TicketVIP extends Ticket implements InterfazTicket{
    private boolean AccesoExclusivo;
    private boolean IncluyeCatering;
    private boolean IncluyeParking;
    private boolean AccesoBackstage;
    private boolean MeetNGreet;

    ArrayList<String> BeneficiosInactivos;

    /*Constructor*/

    public TicketVIP(int Numero, float PrecioBase, Evento eventoAsignado, boolean incluyeCatering, boolean incluyeParking, boolean accesoBackstage, boolean meetNGreet) {
        super(Numero, PrecioBase, eventoAsignado);
        this.AccesoExclusivo = true;
        this.IncluyeCatering = incluyeCatering;
        this.IncluyeParking = incluyeParking;
        this.AccesoBackstage = accesoBackstage;
        this.MeetNGreet = meetNGreet;
        BeneficiosInactivos = new ArrayList<>();
    }

    public void solicitarUpgrade() {
        // Verifica si el ticket ya tiene todos los beneficios
        if (AccesoExclusivo && IncluyeCatering && IncluyeParking && AccesoBackstage && MeetNGreet) {
            System.out.println("Este ticket ya tiene todos los beneficios.");
            return;
        }

        // Si no tiene todos los beneficios, muestra qué beneficios faltan
        Scanner scanner = new Scanner(System.in);

        // Mostrar los beneficios inactivos
        ArrayList<String> beneficiosInactivos = new ArrayList<>();
        if (!IncluyeCatering) {
            beneficiosInactivos.add("Catering - $100");
        }
        if (!IncluyeParking) {
            beneficiosInactivos.add("Parking - $50");
        }
        if (!AccesoBackstage) {
            beneficiosInactivos.add("Backstage - $300");
        }
        if (!MeetNGreet) {
            beneficiosInactivos.add("MeetNGreet - $150");
        }

        // Si hay beneficios inactivos, muestra la lista
        if (beneficiosInactivos.size() > 0) {
            System.out.println("Beneficios que NO están en este ticket:");
            for (int i = 0; i < beneficiosInactivos.size(); i++) {
                System.out.println((i + 1) + "- " + beneficiosInactivos.get(i));
            }
            System.out.println("Elija un beneficio POR NÚMERO o ingrese 0 para salir:");

            int opcion = -1;
            while (opcion < 0 || opcion > beneficiosInactivos.size()) {
                try {
                    String input = scanner.nextLine();  // Captura la entrada completa
                    opcion = Integer.parseInt(input);  // Intenta convertir a entero

                    if (opcion == 0) {
                        System.out.println("Proceso de upgrade cancelado.");
                        return;  // Sale del proceso si elige 0
                    }

                    if (opcion < 1 || opcion > beneficiosInactivos.size()) {
                        System.out.println("Opción inválida. Por favor, elige un número válido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, ingresa un número.");
                }
            }

            // Aplicar el beneficio elegido
            String beneficioElegido = beneficiosInactivos.get(opcion - 1);
            switch (beneficioElegido) {
                case "Catering - $100":
                    this.IncluyeCatering = true;
                    break;
                case "Parking - $50":
                    this.IncluyeParking = true;
                    break;
                case "Backstage - $300":
                    this.AccesoBackstage = true;
                    break;
                case "MeetNGreet - $150":
                    this.MeetNGreet = true;
                    break;
            }

            System.out.println("Beneficio agregado: " + beneficioElegido);

            // Repetir el proceso hasta que todos los beneficios estén añadidos o el usuario decida salir
            this.solicitarUpgrade();
        } else {
            System.out.println("Este ticket ya tiene todos los beneficios.");
        }
    }


    public float calcularPrecio() {
        float precio = this.getPrecioBase();

        if (this.IncluyeCatering){
            precio += 100;
        }
        if (this.IncluyeParking){
            precio += 50;
        }
        if (this.AccesoBackstage){
            precio += 300;
        }
        if (this.MeetNGreet){
            precio += 150;
        }

        precio -= precio * (this.getPropietario().Descuento() / 100.0f);
        return precio;
    }

    public void obtenerBeneficios(){
        /*devolver beneficios que sean true*/
        if (AccesoExclusivo) {
            System.out.println("- Acceso Exclusivo");
        }
        if (IncluyeCatering) {
            System.out.println("- Incluye Catering");
        }
        if (IncluyeParking) {
            System.out.println("- Incluye Parking");
        }
        if (AccesoBackstage) {
            System.out.println("- Acceso Backstage");
        }
        if (MeetNGreet) {
            System.out.println("- Meet and Greet");
        }
    }

}
