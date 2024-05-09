import javax.swing.*;
import javax.swing.text.Highlighter;
import javax.swing.text.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VistaTexto extends JScrollPane implements Observer
{
    private ModeloArchivos modelo;
    private ModeloSubrrayado mSub;
    private JTextArea texto = new JTextArea();
    private Highlighter h = new DefaultHighlighter();
    private Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
    private Highlighter.HighlightPainter previousPainter = null;

    public VistaTexto(ModeloArchivos modelo, ModeloSubrrayado modeloSubrrayado)
    {
        this.modelo = modelo;
        this.mSub = modeloSubrrayado;
        texto.setHighlighter(h);
        setViewportView(texto);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void update(Observable o, Object arg)
    {
        if(o.equals(modelo))
        {
            texto.setText(modelo.getTexto());
        }
        else if(o.equals(mSub))
        {
            h.removeAllHighlights();
            try
            {
                h.addHighlight(mSub.getIndiceI(), mSub.getIndiceF(), painter);
                previousPainter = painter;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setTexto()
    {
        modelo.setTexto(texto.getText());
    }

    public String getText()
    {
        return texto.getText();
    }
}
