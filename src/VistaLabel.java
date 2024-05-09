import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VistaLabel extends JLabel implements Observer
{
    private ModeloArchivos mFile;
    private ModeloSubrrayado mSub;

    public VistaLabel(String texto, ModeloArchivos modelo, ModeloSubrrayado sub)
    {
        super(texto);
        this.mFile = modelo;
        this.mSub = sub;
        setAlignmentX(CENTER_ALIGNMENT);
    }

    public void update(Observable o, Object arg)
    {
        if(o.equals(mFile))
        {
            setText(mFile.getTextoLabel());
        }
        else if(o.equals(mSub))
        {
            setText(mSub.getTextoLabel());
        }
    }
}