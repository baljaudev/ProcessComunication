package dam.son;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SonProcessVowel {


    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        String ruta = lector.nextLine();
        String cadena = lector.nextLine();
        int numVocales;

        try  (FileWriter fw = new FileWriter(ruta);
              FileReader fr = new FileReader(ruta)) {

            while (cadena != null) {
                numVocales = contarVocales(cadena.toUpperCase());
                fw.write(String.valueOf(numVocales));
                fw.flush();

                char[] buf = String.valueOf(numVocales).toCharArray();

                int i;
                while ((i = fr.read(buf)) != -1) {
                    System.out.println(("La cadena introducida '" + cadena + "' tiene el siguiente número de vocales: " + String.valueOf(buf, 0, i)));
                }
                cadena = lector.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lector.close();
    }


    private static int contarVocales(String cadena) {
        int contadorVocales = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == 'A' || cadena.charAt(i) == 'E' || cadena.charAt(i) == 'I' ||
                    cadena.charAt(i) == 'O' || cadena.charAt(i) == 'U') {
                contadorVocales++;
            }
        }
        return contadorVocales;
    }


}
