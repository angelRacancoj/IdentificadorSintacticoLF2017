package analizadorLexico.Archivo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author angelrg
 */
public class ManejadorArchivo {

    BufferedReader lectura;

    public String lecturaArchivo(String path) throws FileNotFoundException, IOException {
        System.out.println(path);
        File file = new File(path);
        String textoTotal = "";
        String linea = "";
        lectura = new BufferedReader(new FileReader(file));
        while ((linea = lectura.readLine()) != null) {
            textoTotal += (linea + "\n");
        }
        System.out.println(textoTotal);
        return textoTotal;
    }

    public void guardarArchivo(String path, String texto) throws IOException {
        FileWriter fichero = null;
        File file = new File(path);
        fichero = new FileWriter(file);
        fichero.write(texto);
        fichero.close();
    }
}
