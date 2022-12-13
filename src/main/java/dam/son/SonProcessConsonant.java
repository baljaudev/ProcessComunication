package dam.son;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SonProcessConsonant {


    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        String ruta = lector.nextLine();
        String cadena = lector.nextLine();
        int numConsonantes;

        try  (FileWriter fw = new FileWriter(ruta);
              FileReader fr = new FileReader(ruta)) {

            while (cadena != null) {
                numConsonantes = contarConsonantes(cadena.toUpperCase());
                fw.write(String.valueOf(numConsonantes));
                fw.flush();

                char[] buf = String.valueOf(numConsonantes).toCharArray();

                int i;
                while ((i = fr.read(buf)) != -1) {
                    System.out.println(("La cadena introducida '" + cadena + "' tiene el siguiente número de consonantes: " + String.valueOf(buf, 0, i)));
                }
                cadena = lector.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lector.close();
    }

    private static int contarConsonantes(String cadena) {
        int contadorConsonantes = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (!(cadena.charAt(i) == 'A') && !(cadena.charAt(i) == 'E') && !(cadena.charAt(i) == 'I') &&
                    !(cadena.charAt(i) == 'O') && !(cadena.charAt(i) == 'U')) {
                contadorConsonantes++;
            }
        }
        return contadorConsonantes;
    }


}
