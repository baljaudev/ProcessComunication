package dam.father;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FatherProcess {


    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            }

        // Creación/conexión con el proceso hijo utilizando la ruta donde hemos creado el .jar:
        Process hijoVocales = null;
        Process hijoConsonantes = null;
        try {
            hijoVocales = new ProcessBuilder("java", "-jar",
                    "out/artifacts/ProcessVowel_jar/ProcessComunication.jar").start();
            hijoConsonantes = new ProcessBuilder("java", "-jar",
                    "out/artifacts/ProcessConsonant_jar/ProcessComunication.jar").start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Leer por teclado la información del proceso que enviamos al hijo:
        Scanner in = new Scanner(System.in);

        //Obtenemos el stdout (salida standar) del proceso hijo:
        BufferedReader brV = new BufferedReader(new InputStreamReader(hijoVocales.getInputStream()));
        BufferedReader brC = new BufferedReader(new InputStreamReader(hijoConsonantes.getInputStream()));


        //Entrada standar del proceso hijo:
        PrintStream psV = new PrintStream(hijoVocales.getOutputStream());
        PrintStream psC = new PrintStream(hijoConsonantes.getOutputStream());

        psV.println(args[0]);
        psC.println(args[1]);

        psV.flush();
        psC.flush();

        String cadena = "";

        ArrayList<String> listaPalabras = new ArrayList<>();

        //mientras no tecleemos fin:
        while (!cadena.equalsIgnoreCase("fin")) {
            System.out.println("Introduce una cadena de caracteres, puede estar formado por letras fin para salir:");
            //lee la cadena
            cadena = in.nextLine();

            if (listaPalabras.contains(cadena)) {
                System.out.println("Esta palabra ya se ha introducido, no puede volver a comprobarse.");
            } else {
                if (!cadena.equalsIgnoreCase("fin")) {
                    listaPalabras.add(cadena);
                    //Enviamos cadena a proceso hijo:
                    psV.println(cadena);

                    psC.println(cadena);

                    //volcado de toda la información:
                    psV.flush();
                    psC.flush();

                    try {
                        System.out.println(brV.readLine());
                        System.out.println(brC.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        in.close();
        System.out.println("*FIN*");
        System.out.println("\n***Se han introducido las siguientes palabras***");

        int contadorVocales = 0;
        int contadorConsonantes = 0;

        try  (FileWriter fw = new FileWriter("src/main/resources/palabrasIntroducidas.txt")) {
            for (String palabra : listaPalabras) {
                System.out.println("\n -" + palabra);
                for (int i = 0; i < palabra.toUpperCase().length(); i++) {
                    if (palabra.toUpperCase().charAt(i) == 'A' || palabra.toUpperCase().charAt(i) == 'E' || palabra.toUpperCase().charAt(i) == 'I' ||
                            palabra.toUpperCase().charAt(i) == 'O' || palabra.toUpperCase().charAt(i) == 'U') {
                        contadorVocales++;
                    }else {
                        contadorConsonantes++;
                    }
                }

                fw.write("\n -" +palabra);
                fw.flush();
            }

            try  (FileWriter fv = new FileWriter("src/main/resources/sumaVocalesyConsonantes.txt")) {


                fv.write("-La suma de las vocales del archivo es: " +contadorVocales+
                        "\n-La suma de las consonantes del archivo es: "+contadorConsonantes);
                fv.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n-La suma de las vocales del archivo es: " +contadorVocales+
                "\n-La suma de las consonantes del archivo es: "+contadorConsonantes);
    }
}
