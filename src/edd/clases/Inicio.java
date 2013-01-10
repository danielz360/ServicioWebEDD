package edd.clases;

import java.awt.event.ActionEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;
import edd.Variables.*;
import Servicios.*;

public class Inicio extends JFrame implements ActionListener
{    	
    private Globales Gbse;	
    private JTabbedPane JTPPPanel;
    private JPanel PPanel;
    private JButton BtnIniciar,BtnParar,BtnAlturaArbol, BtnFrecuenciPalabra,BtnReportar;
    
    public JMenuBar JMBBarraMenu;
	public JMenu JMGraficas,JMConsultas;
	public JMenuItem JMIGraficarUsuarios,JMIGraficarContactos,JMIGraficarPalabras;
	private static Logger log = Logger.getLogger(Inicio.class); // se intancia un logger de la clase donde esta el metodo.
	
	/**Constructor*/
	public Inicio() 
	{
		// TODO Auto-generated constructor stub
		PropertyConfigurator.configure("log4j.properties.txt"); // se llama al archivo en el cual se declaran las propiedades.
		//Gbse = Globales.getInstance();
		this.GUI();
	}

	public void GUI()
	{
		JTPPPanel = new JTabbedPane();
        //Se crea un PPanel contenedor
        PPanel = new JPanel( null );
		PPanel.setLayout(null);
		
		//Boton que Registra
		BtnIniciar = new JButton("Iniciar");
		BtnIniciar.setSize(100, 25 );
		BtnIniciar.setLocation(25, 55);
		BtnIniciar.addActionListener(this);
        PPanel.add(BtnIniciar);       
                
        //Boton que Registra
        BtnParar = new JButton("Parar");
        BtnParar.setSize(100, 25 );
        BtnParar.setLocation(130, 55);
        BtnParar.addActionListener(this);
        PPanel.add(BtnParar);       
        
        //Boton que Registra
        /*BtnReportar = new JButton("Reportar");
        BtnReportar.setSize(100, 25 );
        BtnReportar.setLocation(25, 85);
        BtnReportar.addActionListener(this);
        PPanel.add(BtnReportar);*/
        
        JMBBarraMenu = new JMenuBar();
		
        JMGraficas = new JMenu("Graficas");
        JMGraficas.setMnemonic('A');
        JMConsultas = new JMenu("Consultas");
        JMConsultas.setMnemonic('C');
		
        JMIGraficarUsuarios = new JMenuItem("Graficar Usuarios");
        JMIGraficarUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        JMIGraficarUsuarios.addActionListener(this);
		JMGraficas.add(JMIGraficarUsuarios);
		
		JMIGraficarContactos = new JMenuItem("Graficar Contactos");
		JMIGraficarContactos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		JMIGraficarContactos.addActionListener(this);
		JMGraficas.add(JMIGraficarContactos);

		/*JMIGraficarPalabras = new JMenuItem("Graficar Palabras");
		JMIGraficarPalabras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		//JMIDesconectar.setIcon(ImgDesconectar);
		JMIGraficarPalabras.addActionListener(this);
		JMGraficas.add(JMIGraficarPalabras);*/
		
		this.JMBBarraMenu.add(JMGraficas);
		this.JMBBarraMenu.add(JMConsultas);
		this.setJMenuBar(this.JMBBarraMenu);
        this.JTPPPanel.addTab("Herramientas", null, PPanel, "Panel Herramientas");
        this.add(this.JTPPPanel);
        
		 //Establecer parametros de visualizacion
        this.setTitle("Servidor Estructuras de Datos");
        this.setSize(300, 220);		
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
   
   public static void main( String args[] )
   {
	   new Inicio();
   }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == BtnIniciar)
		{		
			this.Gbse.CargarDatos();
		}
		if(e.getSource() == BtnParar)
		{
			ServicioEdd S = new ServicioEdd();
			S.Cargar();
			String a = S.IniciarSesion("Geb", "52969e4a");
			JOptionPane.showMessageDialog(null, a + "");
			
		}
		if(e.getSource() == JMIGraficarUsuarios)
		{
			
		}
		
		if(e.getSource() == JMIGraficarContactos)
		{
			
		}				
	}
	   
}
