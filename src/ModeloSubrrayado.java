import java.util.Observable;

public class ModeloSubrrayado extends Observable
{
    private int indiceI = 0;
    private int indiceF = 0;
    private String textoLabel = "";

    public ModeloSubrrayado()
    {

    }

    public void buscarPalabra(String palabra, String textoCompleto)
    {
        int i = textoCompleto.indexOf(palabra);
        if(i != -1)
        {
            setIndices(i, i + palabra.length());
        }
        else
        {
            textoLabel = "No se encontro la palabra";
            setChanged();
            notifyObservers();
        }
    }

    public void buscarSigPalabra(String palabra, String textoCompleto)
    {
        String texto = textoCompleto.substring(indiceF).toLowerCase();
        int i = texto.indexOf(palabra) + textoCompleto.substring(0, indiceF).length();
        if(i != -1)
        {
            setIndices(i, i + palabra.length());
        }else
        {
            textoLabel = "No se encontro la palabra";
            setChanged();
            notifyObservers();
        }
    }

    public boolean haySiguiente(String palabra, String textoCompleto)
    {
        String texto = textoCompleto.substring(indiceF).toLowerCase();
        return texto.contains(palabra);
    }

    public void setIndices(int i, int f)
    {
        indiceI = i;
        indiceF = f;
        textoLabel = "Cadena encontrada en la posicion " + indiceI;
        setChanged();
        notifyObservers();
    }

    public int getIndiceI()
    {
        return indiceI;
    }

    public int getIndiceF()
    {
        return indiceF;
    }

    public void setIndiceI(int i)
    {
        indiceI = i;
    }

    public void setIndiceF(int f)
    {
        indiceF = f;
    }

    public String getTextoLabel()
    {
        return textoLabel;
    }
}
