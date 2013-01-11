package edd.Variables;

import java.awt.Image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.util.StringTokenizer;
import org.apache.log4j.BasicConfigurator;

import org.apache.log4j.Logger;

import edd.Estructuras.*;
import edd.Rabol.*;

public class Globales
{
    /**
	 * 
	 */
	private static Globales INSTANCE = null;
    //Directorio donde estaran guardada la Informacion
	private String DirectorioDatos = "/home/danielz360/workspace/WebService/";
	private int OrdenFacturas = 5;
    //Lista Usuario
	private ListaUsuario Usuarios = new ListaUsuario();;
    //Tabla de Productos
	private TablaHashProducto Productos = new TablaHashProducto();;
    //Arbol B de Facturas
	private enlaceAB Facturas;  
	//Lista de Colas Detalle
	private ListadeColasDetalle listasDetalle = new ListadeColasDetalle();
	private ManipulacionDatos Mani;
    private static Logger log = Logger.getLogger(Globales.class); // se intancia un logger de la clase donde esta el metodo.
    private int YaCargado = 0;
    
    // Private constructor suppresses 
    private Globales()
    { 
    	//Facturas = new enlaceAB(OrdenFacturas);
    }
    
    // Creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple 
    private synchronized static void createInstance() 
    {
        if (INSTANCE == null) 
        { 
        	BasicConfigurator.configure();
            INSTANCE = new Globales();
        }
    }
 
    public static Globales getInstance() 
    {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }
    
    public int getYaCargado()
    {
    	return YaCargado;
    }
    public void SetYaCargado(int pCargado)
    {
    	YaCargado = pCargado;
    }
    
    public ListadeColasDetalle ListadeDetalles(){
    	return listasDetalle;
    }
    
    public void setDetalle(String id,int cantidad,int precio,int producto)
    {
    	Facturas.insertardetallealArbol(id, cantidad, precio, producto);
    }
    public int getDetalle(int idDetalle){
    	return listasDetalle.obtener(idDetalle).obtDetalle();
    }
    
    public int getOrdenFacturas()
    {
    	return this.OrdenFacturas;
    }
    public void setOrdenFacturas(int pOrdenFacturas)
    {
    	OrdenFacturas = pOrdenFacturas;
    }
    
    public ListaUsuario   getUsuarios()
    {
    	return this.Usuarios;
    }
    public void setUsuarios(ListaUsuario pUsuarios)
    {
    	Usuarios = pUsuarios;
    }
    
    public String getDirectorioDatos()
    {
    	return this.DirectorioDatos;
    }
    public void setDirectorioDatos(String pDirectorioDatos)
    {
    	DirectorioDatos = pDirectorioDatos;
    }
    
    public TablaHashProducto getProductos()
    {
    	return this.Productos;
    }
    public void setProductos(TablaHashProducto pProductos)
    {
    	Productos = pProductos;
    }
    
    
    public CreacionArbolB getFacturas()
    {
    	return this.Facturas.ObtArbolB();
    }
    public void setFacturas(String no,String fecha1,String total1,String user)
    {
    	NodoUsuario user1 = Usuarios.ObtBuscar(user);
    	Facturas.insertar(no, fecha1, total1, user1);
    }
    public void graficararbol (){
    	Facturas.graficarArbol();
    }
    
    public String getinfoItenArbol(String NoFactura){
    	return Facturas.buscarinfo(NoFactura);
    }
    
    public String getuserItemArbol(String NoFactura){
    	return Facturas.buscaruser(NoFactura);
    }
    
    public int getdetalleItemArbol(String NoFactura){
    	return Facturas.buscardetalle(NoFactura);
    }
    
    public ManipulacionDatos getMani()
    {
    	return this.Mani;
    }
    public void setMani(ManipulacionDatos pMani)
    {
    	Mani = pMani;
    }
            
    /**
     * Carga todos los Datos a Memoria desde los Archivos CSV
     */
    public void CargarDatos() 
    {
    	try
    	{
    		Mani = new ManipulacionDatos();
    		Mani.Cargar();
    	}
    	catch(Exception ex)
    	{
    		log.error("SERVIDOR->Error en CargarDatos" + ex.getMessage());
    	}
    }            
        
    /**
     * Metodo que permite iniciar sesion
     * @param pLogin
     * @param pPassword
     * @return
     */
    public int IniciarSesion(String pLogin, String pPassword) 
    {
    	NodoUsuario nodo = Usuarios.ObtBuscar(pLogin);
    	
    	if(nodo == null)
    	{
    		log.warn("SERVIDOR->Usuario o Contrasena invalida para el nickname " + pLogin);
    		return 0;	
    	}
    	
    	if(nodo.ObtNickName().equals(pLogin) && nodo.ObtContrasena().equals(pPassword))
		{
			if(YaInicioSesion(nodo))
			{
				log.warn("SERVIDOR->Este usuario se encuentra conectado " +pLogin);
				return 1;
			}

			nodo.SetInicioSesion(1);
			log.info("SERVIDOR->Usuario " + pLogin + " Autenticado Exitosamente ");			
			return 2;			
		}
    	log.warn("SERVIDOR->Usuario o Contrasena invalida para el nickname " + pLogin);
    	return 0;
    }

	/**
	 * Verifica si un NickName especifico hay Iniciado Sesion
	 * @param Login
	 * @return
	 */
    public boolean YaInicioSesion(NodoUsuario nodo)
    {
    	try
    	{
			if(nodo.ObtInicioSesion() == 1)
			{
				return true;
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			log.warn("SERVIDOR->Error en YaInicioSesion" + e.getMessage());
			//JOptionPane.showMessageDialog(null, "SERVIDOR->Error en YaInicioSesion" + e.getMessage());
		}	
    	return false;
    }    
    /**
     * 
     * @param p_NickName
     * @param p_Contrasena
     * @throws IOException
     */
    public int Registrar(String p_NickName,String p_Contrasena)
	{	    	    			
		try 
		{		 									
			NodoUsuario nodo = Usuarios.ObtBuscar(p_NickName);
			
			if(nodo != null)
			{
				log.info("SERVIDOR->Ya existe una cuenta con el nickname " + p_NickName);
				return 0;
			}		
		    
		    //Agregar a la Lista de Usuarios
		    Usuarios.InsertarFinal(p_NickName, p_Contrasena);
		    
		    log.info("SERVIDOR->Usuario " + p_NickName + " Ingresado Exitosamente");
		    return 2;
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			log.info("SERVIDOR->Error en Registrar" + e.getMessage());
			return 1;
		}		
	}
    
    /**
     * Metodo que devuelve los productos en una cadena string
     */
    public String ObtProductosEnCadena() 
	{	
    	try
    	{
    		String [] VCodigoProductos = this.Productos.ObtCodigos().split("-");
    		String Resultado = "";
    		
    		for(int a = 0; a < VCodigoProductos.length;a++)
    		{
				if(this.Productos.EstaBorrado(Integer.parseInt(VCodigoProductos[a])))
				{
					continue;
				}
				
    			NodoProducto producto = this.Productos.ObtBuscar(Integer.parseInt(VCodigoProductos[a]));
   				Resultado += producto.ObtCodigo() + "," + producto.ObtNombre() + "," + producto.ObtMarca() + "," + producto.ObtPrecio() + "," + producto.ObtUrlImagen() + ";";
    		}
    		Resultado = Resultado.substring(0, Resultado.length()-1);
    		return Resultado;
    	}
    	catch(Exception ex)
    	{
    		log.info("SERVIDOR->Error en ObtProductosEnCadena " + ex.getMessage());
    	}
    	return "";
	}
    /**Verifica si un Fichero esta Vacio en Limpio
     * @ p_RFichero Nombre del Fichero a Comprobar
     * */
    public boolean EstaVacio(String p_RFichero)
    {
    	String sFichero = p_RFichero;				
		BufferedReader Breader = null;
		try 
		{
			Breader = new BufferedReader(new FileReader (sFichero));
			String linea;
			int a = 0;
			while((linea=Breader.readLine())!= null)
			{
				a ++;
			}
			if (a == 0) return true;
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			log.error("Error en " + e.getMessage());
			//JOptionPane.showMessageDialog(null,"Error en " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null,"Error en " + e.getMessage());
			log.error("Error en " + e.getMessage());
			e.printStackTrace();
		}
		
		return false;
    }
    
	/**
	 * Cierra la Sesion de una Sesion Especifica
	 * @param Ss
	 */
    public boolean CerrarSesion(NodoUsuario pUsuario)
    {
    	try
    	{    		
    		pUsuario.SetInicioSesion(0);
    		log.info("SERVIDOR->El Usuario " + pUsuario.ObtNickName()  + " Cerro Sesion");
    		return true;
    	}
    	catch(Exception ex)
    	{
    		log.error("Error Cerrando Sesion " +ex.getMessage());
    		return false;
    	}    
    }
    
    public String ObtCaracter(int Pos, String Cadena, String Separador)
    {
		try
		{
			StringTokenizer DoFFrtokens = new StringTokenizer(Cadena,Separador);
			
			int z = 0;

			while(DoFFrtokens.hasMoreTokens())
			{
				String carac = DoFFrtokens.nextToken();
				if(z == Pos)
				{
					return carac;
				}	
				z++;
			}
		}
		catch(Exception e)
		{
			log.error("Error Obteniendo Caracter: " + e.getMessage());			
		}
		return "";
    }
    
    public String ObtDirectorioActual()
    {
    	File miDir = new File (".");
    	String Directorio = "";
    	try 
    	{
			Directorio = miDir.getCanonicalPath();
		} 
    	catch (Exception e) 
    	{
			// TODO Auto-generated catch block
    		JOptionPane.showMessageDialog(null, "Error en Obteniendo el Directorio Actual " + e.getMessage());
		}
    	return Directorio;
    }    
    
	public ImageIcon ObtImagen(String p_Dir, int p_Ancho, int p_Alto)
	{
		ImageIcon i = (new ImageIcon(getClass().getResource(p_Dir)));
		ImageIcon Img = new ImageIcon(i.getImage().getScaledInstance(p_Ancho,p_Alto, Image.SCALE_DEFAULT));
		return Img;
	}
    
    /**
     * Metodo estatico que verifica si una cadena es un entero
     * @param cadena
     * @return
     */
    public boolean esEntero(String cadena)
    {
        try
        {
            Integer.parseInt(cadena);
            return true;
        }
        catch(Exception ex){}
        
        return false;
    }
    
    /**
     * Metodo estatico que verifica si una cadena es un entero
     * @param cadena
     * @return
     */
    public boolean esDouble(String cadena)
    {
        try
        {
            Double.parseDouble(cadena);
            return true;
        }
        catch(Exception ex){}
        
        return false;
    }
    
    /**
     * Metodo estatico que verifica si una cadena es un decimal
     * @param cadena
     * @return
     */
    public boolean esDecimal(String cadena)
    {
        try
        {
            new BigDecimal(cadena);
            return true;
        }
        catch(Exception ex)
        {}
        return false;
    }
    /**
     * Metodo estatico que verifica si una cadena es nula (esta en blanco)
     * @param cadena
     * @return
     */
    public boolean esNulo(String cadena)
    {
        if(cadena.trim().equals(""))return true;
        return false;
    }
    /**
     * Metodo estatico que verifica si el tipo de clase  enviado es String 
     * @param pClase
     * @return
     */
    public boolean ClasEsString(Object pClase)
    {
        if(pClase.equals(java.lang.String.class))return true;
        return false;
    }
    /**
     * Metodo estatico que verifica si el tipo de clase  enviado es Integer 
     * @param pClase
     * @return
     */
    public boolean ClasEsInteger(Object pClase)
    {
        if(pClase.equals(java.lang.Integer.class))return true;
        return false;
    }
    /**
     * Metodo estatico que verifica si el tipo de clase  enviado es Date 
     * @param pClase
     * @return
     */
    public boolean ClasEsDate(Object pClase)
    {
        if(pClase.equals(java.sql.Date.class))return true;
        return false;
    }
    /**
     * Metodo estatico que verifica si el tipo de clase  enviado es BigDecimal 
     * @param pClase
     * @return
     */
    public boolean ClasEsBigDecimal(Object pClase)
    {
        if(pClase.equals(java.math.BigDecimal.class))return true;
        return false;
    }
    /**
     * Metodo estatico que verifica si el tipo de clase  enviado es Booleano 
     * @param pClase
     * @return
     */
    public boolean ClasEsBooleano(Object pClase)
    {
        if(pClase.equals(java.lang.Boolean.class))return true;
        return false;
    }
    /**
     * Metodo estatico que devuelve la fecha del sistema
     * @param cadena
     * @return
     */
    public String Fecha()
    {
        java.util.Calendar date = java.util.Calendar.getInstance();
        String fecha = "";
        
        if(date.get(java.util.Calendar.DAY_OF_MONTH) < 10) 
        {	
        	fecha += "0";
        }
        
        fecha += date.get(java.util.Calendar.DAY_OF_MONTH) + "/";
        
        if((date.get(java.util.Calendar.MONTH) + 1) < 10) 
        {	
        	fecha += "0";
        }
        
        fecha += (date.get(java.util.Calendar.MONTH) + 1) + "/";
        fecha += date.get(java.util.Calendar.YEAR);
        return fecha;
    }
}