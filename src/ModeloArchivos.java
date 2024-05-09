import javax.swing.*;
import java.io.*;
import java.util.Observable;

public class ModeloArchivos extends Observable
{
    private String textoLabel  =  "";
    private String texto = "";

    public ModeloArchivos()
    {

    }

    public void cargarArchivo(String archivo)
    {
        texto = "";
        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            StringBuilder content = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }

            bufferedReader.close();

            texto = content.toString();
            textoLabel = "Archivo cargado correctamente";
        } catch (IOException e) {
            textoLabel = "Error al cargar el archivo";
        }
        setChanged();
        notifyObservers();
    }

    public void guardarArchivo()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if(!filePath.endsWith(".txt")) {
                filePath += ".txt";
            }

            try {
                FileWriter fileWriter = new FileWriter(filePath);

                fileWriter.write(texto);

                fileWriter.close();

                textoLabel = "El archivo se ha guardado correctamente";
            } catch (IOException e) {
                textoLabel = "Error al guardar el archivo";
            }
        } else {
            textoLabel = "Operación de guardado cancelada.";
        }
        setChanged();
        notifyObservers();
    }

    public void setTextoLabel(String texto)
    {
        this.textoLabel = texto;
        setChanged();
        notifyObservers();
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
        setChanged();
        notifyObservers();
    }

    public String getTexto()
    {
        return texto;
    }

    public String getTextoLabel()
    {
        return textoLabel;
    }
}