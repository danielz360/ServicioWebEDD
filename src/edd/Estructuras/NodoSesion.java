package edd.Estructuras;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import edd.Variables.*;;

public class NodoSesion  
{
	private Socket SocketConexion; 
	private NodoUsuario Usuario;
	public String Estado;
	private int ID;
	public Globales GbSe;
	private NodoSesion  InicioSig;
	/*private Timer timer;*/
	
	public NodoSesion(int p_ID,NodoUsuario pUsuario,Socket pSocketConexion) 
	{
		// TODO Auto-generated constructor stub
	//	GbSe = Globales.getInstance();
		Usuario = pUsuario;
		SocketConexion = pSocketConexion;
		ID = p_ID;
		
		//CargarDatosGuardados( p_Nombre, p_NickName, p_NickName, p_Password);
	}
	
	
	public void setInicioSig(NodoSesion p_Inicio)
	{
		InicioSig = p_Inicio;
	}
	public NodoSesion ObtInicioSig()
	{
		return InicioSig;
	}	
	public NodoUsuario ObtUsuario()
	{
		return Usuario;
	}	
	public Socket ObtSocket()
	{
		return SocketConexion;
	}	
	   
	public void CargarDatosGuardados(String p_Nombre,String p_NickName,String p_Sexo,String p_Password)
	{
		String sFichero = GbSe.getDirectorioDatos() + "Contactos_" + p_NickName +  ".csv";	
		File fichero = new File(sFichero);
		
		if(!fichero.exists())
		{
			PrimerPartida();	
			return;
		}
		BufferedReader Breader = null;
		//GB.GuardarJuego(ID, TFrutas,EFrutas,DoF,PosXFrutas,PosYFrutas, Dinero, TmJuego, TmVenta,  V1Venta,  V2Venta,  V3Venta);
		
		// Lectura del fichero
		String linea;
		boolean hayusuario = false;
		int a = 0;

		try 
		{
			Breader = new BufferedReader(new FileReader (sFichero));

			while((linea = Breader.readLine())!=null)
			{					

				if(linea.equals("---"))
				{
					hayusuario = true;
				}
				if(hayusuario)
				{
						
				}
			}
					a++;			
		}		
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error en Cargando Datos Guardados " + e.getMessage());
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error en Cargando Datos Guardados " + e.getMessage());
		}
		
		/*if(PrimerPartida)
		{
			PrimerPartida();			
		}*/		
	}
	
	public void PrimerPartida()
	{
		//Valores Por Default Cuando es la Primera vez que se Juega
		//Tiempo Jugado

	}
}
