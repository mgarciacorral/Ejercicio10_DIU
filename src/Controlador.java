import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class Controlador extends JFrame
{
    private ModeloArchivos mFile = new ModeloArchivos();
    private ModeloSubrrayado mSub = new ModeloSubrrayado();
    private VistaLabel label;
    private VistaTexto texto;
    private JPanel panel = new JPanel();
    private JPanel panelBuscar = new JPanel();
    private JPanel panelArchivos = new JPanel();
    private JButton encontrar, siguiente, cargar, guardar;
    private TextField buscar = new TextField();
    private JFileChooser fileChooser = new JFileChooser();
    private String comprobador = "";
    private String comprobador2 = "";

    public Controlador()
    {
        setTitle("Editor de Texto");
        setLayout(new BorderLayout());
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        texto = new VistaTexto(mFile, mSub);
        label = new VistaLabel("Panel de Informacion", mFile, mSub);

        mFile.addObserver(label);
        mFile.addObserver(texto);
        mSub.addObserver(texto);
        mSub.addObserver(label);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panelBuscar.setLayout(new FlowLayout());
        panelArchivos.setLayout(new FlowLayout());

        encontrar = new JButton("Buscar");
        siguiente = new JButton("Siguiente");
        siguiente.setEnabled(false);
        cargar = new JButton("Cargar Archivo");
        guardar = new JButton("Guardar Archivo");

        encontrar.addActionListener(e -> {
            mSub.buscarPalabra(buscar.getText(), texto.getText());
            if (mSub.haySiguiente(buscar.getText(), texto.getText()))
            {
                siguiente.setEnabled(true);
            }
        });

        siguiente.addActionListener(e -> {
                    mSub.buscarSigPalabra(buscar.getText(), texto.getText());
                    if (!mSub.haySiguiente(buscar.getText(), texto.getText())){
                        siguiente.setEnabled(false);
                    }
        });

        cargar.addActionListener(e -> {
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));
            int seleccion = fileChooser.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION)
            {
                mFile.cargarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        guardar.addActionListener(e -> {
            texto.setTexto();
            mFile.guardarArchivo();
        });

        new Timer( 1000, e -> {
            if (!comprobador.equals(texto.getText()))
            {
                comprobador = texto.getText();
                siguiente.setEnabled(false);
                mSub.setIndiceI(0);
                mSub.setIndiceF(0);
            }
            if (!comprobador2.equals(buscar.getText()))
            {
                comprobador2 = buscar.getText();
                siguiente.setEnabled(false);
                mSub.setIndiceI(0);
                mSub.setIndiceF(0);
            }
        }).start();

        panelBuscar.add(encontrar);
        panelBuscar.add(siguiente);

        panelArchivos.add(cargar);
        panelArchivos.add(guardar);

        panel.add(buscar);
        panel.add(panelBuscar);
        panel.add(panelArchivos);
        panel.add(label);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        add(texto, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}