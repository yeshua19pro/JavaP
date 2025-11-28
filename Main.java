import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Medida medida = null;

        while (medida == null) {
            System.out.println("Ingresa una medida (ejemplo: 100 cm o solo 100 para usar metros por defecto): ");
            String entrada = sc.nextLine().trim();

            try {
    
                String[] partes = entrada.split(" ");

                double valor;
                String unidad;

                if (partes.length == 1) {
                 
                    valor = Double.parseDouble(partes[0]);
                    unidad = "m";  
                } else if (partes.length == 2) {
                    valor = Double.parseDouble(partes[0]);
                    unidad = partes[1];
                } else {
                    System.out.println("Formato incorrecto. Usa: 100 o 100 cm\n");
                    continue;
                }

          
                medida = new Medida(valor, unidad);

            } catch (NumberFormatException e) {
                System.out.println("El valor numérico es inválido.\n");
            } catch (ConversionInvalidaException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

    
        System.out.println("¿A qué unidad deseas convertir? (cm, m, in, ft): ");
        String destino = sc.nextLine().trim();

        try {
            Medida resultado = medida.convertir(destino);
            System.out.println("\nResultado:");
            System.out.println(medida.getValor() + " " + medida.getUnidad() +
                    " = " + resultado.getValor() + " " + resultado.getUnidad());
        } catch (ConversionInvalidaException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }
}
