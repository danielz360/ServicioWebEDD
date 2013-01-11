package edd.Estructuras;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import edd.Variables.Globales;



public class ListaUsuario 
{
	private NodoUsuario Cabeza;
	private NodoUsuario Fin;
	private String Grafico = "";
	
	/**Constructor*/
	public ListaUsuario() 
	{
		// TODO Auto-generated constructor stub
	    Cabeza = null;
	    Fin = null;
	}
	
	/**Indica si la lista está vacia*/
    private boolean EstaVacia() 
    {
        boolean vacia = false;
        if (Cabeza == null) 
        {
            vacia = true;
        }
        return vacia;
    }
    
    /**Enlaza dos nodos mediante enlace doble*/
    private void Enlazar(NodoUsuario nodoA, NodoUsuario nodoB) 
    {
    	nodoA.SetNodoSiguiente(nodoB);
        nodoB.SetNodoAnterior(nodoA);
    }

    /*inserta un nuevo nodo al inicio de la lista*/
    public void InsertarInicio(String pNickName,String pContrasena,ListaDireccion pDirecciones) 
    {
    	NodoUsuario nuevo = new NodoUsuario(pNickName,pContrasena,pDirecciones);
        if ( EstaVacia() ) 
        {
            Cabeza = nuevo;
            Fin = nuevo;
        } else 
        {
            Enlazar(nuevo, Cabeza);
            Cabeza = nuevo;
        }
    }

    //inserta un nuevo nodo al final de la lista
    public void InsertarFinal(String pNickName,String pContrasena, ListaDireccion pDirecciones) 
    {
    	NodoUsuario nuevo = new NodoUsuario( pNickName,pContrasena,pDirecciones);
        if ( EstaVacia() ) 
        {
            Cabeza = nuevo;
            Fin = nuevo;
        } 
        else 
        {
            Enlazar(Fin, nuevo);
            Fin = nuevo;
        }
    }

    //inserta un nuevo nodo al final de la lista
    public void InsertarFinal(String pNickName,String pContrasena) 
    {
    	NodoUsuario nuevo = new NodoUsuario( pNickName,pContrasena);
        if ( EstaVacia() ) 
        {
            Cabeza = nuevo;
            Fin = nuevo;
             System.out.println("Inserto Usuario3");
        } 
        else 
        {
            Enlazar(Fin, nuevo);
            Fin = nuevo;
             System.out.println("Inserto Usuario4");
        }
    }
    //elimina el nodo del frente de la lista
    public void EliminarInicio() 
    {
        if ( !EstaVacia() ) 
        {
        	NodoUsuario primero = Cabeza.ObtNodoSiguiente();
            if ( primero == null ) 
            {
                Cabeza = null;
                Fin = null;
            } 
            else 
            {
                primero.SetNodoAnterior(null) ;
                Cabeza = primero;
            }
        }
    }

    //elimina el nodo del final de la lista
    public void EliminarFinal() 
    {
        if ( !EstaVacia() ) 
        {
        	NodoUsuario ultimo = Fin.ObtNodoAnterior();
            if ( ultimo == null ) 
            {
                Cabeza = null;
                Fin = null;
            } 
            else 
            {
                ultimo.SetNodoSiguiente(null);
                Fin = ultimo;
            }
        }
    }

    //devuelve una referencia al nodo buscado con su nickname, si no se encuentra devuelve null
    public NodoUsuario ObtBuscar(String pNickName) 
    {
    	NodoUsuario buscado = null;
    	NodoUsuario iterador = Cabeza;
        while ( buscado == null && iterador != null ) 
        {
            if ( iterador.ObtNickName().equals(pNickName)) 
            {
                buscado = iterador;
                break;
            }
            iterador = iterador.ObtNodoSiguiente();
        }
        return buscado;
    }
    
    //muestra los valores en la lista
    public void mostrar() 
    {
    	NodoUsuario iterador = Cabeza;
        while ( iterador != null ) 
        {
            System.out.print( iterador.ObtNickName() + " -> " );
            iterador = iterador.ObtNodoSiguiente();
        }
        System.out.println( "null" );
    }
    
	/**
	 * Metodo que Grafica la Lista
	 * @param arbol
	 * @param EsEspejo
	 */
	public void Graficar()
	{
				
	    try 
		{		         			    			
	    	this.Grafico += "digraph G\n";
	    	this.Grafico += "{\n";
	    	Grafico += "rankdir = LR;\n";	
	    	this.Grafico += "node [shape=record];label=\" Lista de Usuarios \";";
	        
	        NodoUsuario iteradorUsr = Cabeza;
	        
	        int contador = 0;
	    	int contadorD = 0;
	    	int contadorP = 0;
 	    	
	    	while ( iteradorUsr != null ) 
	        {
 	    		this.Grafico += "UnoU_" + contador + " -> UnoD_" + contadorD + " ";
 	    		this.Grafico += "UnoU_" + contador + " -> UnoP_" + contadorP + " ";
 	    		this.Grafico += "UnoU_" + contador +" [label=\"{" + iteradorUsr.ObtNickName() + "}\"];";
 	    		
 	    		if(contador >0)
 	    		{
 	    			this.Grafico += "UnoU_" + (contador - 1) + " -> UnoU_" + contador + " ";
 	    			this.Grafico += "UnoU_" + contador + " -> UnoU_" + (contador - 1) + " ";
 	    		}
 	    		
 	    		NodoDireccion iteradorDir = iteradorUsr.ObtDirecciones().ObtCabeza(); 	    		 	   
 	    		if(iteradorDir !=  null)
 	    		{
 	    			this.Grafico += "UnoD_" + contadorD +" [label=\"{" + iteradorDir.ObtDireccion() + "}\"];";
 	    		} 	    		
 	    		
 	 	    	while ( iteradorDir != null ) 
 		        { 	
 	 	    		contadorD++;
 	 	    		this.Grafico += "UnoD_" + (contadorD - 1)  + " -> UnoD_" + contadorD + " ";
 	 	    		this.Grafico += "UnoD_" + contadorD +" [label=\"{" + iteradorDir.ObtDireccion() + "}\"];";
 	 	    		iteradorDir = iteradorDir.ObtNodoSiguiente(); 	 	    		
 		        }
 	 	    	
 	 	    	
 	 	    	Nodo_ColaProdxComp iteradorPrxC = iteradorUsr.ObtProductosxComprar().cabeza;
 	 	    	if(iteradorPrxC !=  null)
 	 	    	{this.Grafico += "UnoP_" + contadorP +" [label=\"{" + iteradorPrxC.ObtProducto().ObtNombre() + "}\"];";}
 	 	    	

 	 	    	while ( iteradorPrxC != null ) 
 		        {
 	 	    		contadorP++;
 	 	    		this.Grafico += "UnoP_" + (contadorP - 1)  + " -> UnoP_" + contadorP + " ";
 	 	    		this.Grafico += "UnoP_" + contadorP +" [label=\"{" + iteradorPrxC.ObtProducto().ObtNombre() + "}\"];";
 	 	    		iteradorPrxC = iteradorPrxC.ultimo; 	 	    		
 		        }
 	 	    	iteradorUsr = iteradorUsr.ObtNodoSiguiente();
 	    		contadorP++;
 	    		contadorD++;
 	 	    	contador++;
	        }	    		     		    
		}
	    catch (Exception e) 
		{
			// TODO Auto-generated catch block
	    	//log.error("Error en " + e.getMessage());
			System.out.println( "Error en " + e.getMessage());
		}	
	    Grafico += "}";
	}	
	
    public NodoUsuario ObtCabeza()
    {
    	return Cabeza;
    }
    public void SetCabeza(NodoUsuario pCabeza)
    {
    	Cabeza = pCabeza;
    }
    
    public NodoUsuario ObtFin()
    {
    	return Fin;
    }
    public void SetFin(NodoUsuario pFin)
    {
    	Fin = pFin;
    }
    
    public void insertardiriFin(String pNickName,String dir, boolean penvio, boolean pfacturacion ){
        NodoUsuario nodo =  ObtBuscar(pNickName);
        nodo.setdireccionfin(dir, penvio, pfacturacion);
    }
    
    public void insertardiriIni(String pNickName,String dir, boolean penvio, boolean pfacturacion ){
        NodoUsuario nodo =  ObtBuscar(pNickName);
        nodo.setdireccionini(dir,penvio, pfacturacion);
    }
    
    public void InsertarProductoxComprar(String pNickName,int cantidad, NodoProducto pProducto){
    	NodoUsuario nodo =  ObtBuscar(pNickName);
    	nodo.SetProductoxComprar(cantidad, pProducto);
    }
    
    public void insertarprodacarrito(String pNickName,int cantidad, NodoProducto pProducto){
        NodoUsuario nodo =  ObtBuscar(pNickName);
        nodo.setitemcarrito(cantidad,pProducto);
    }
    public Cola_Carrito obtenerCarrito(String pNickName){
        NodoUsuario nodo =  ObtBuscar(pNickName);
        return nodo.obtenercarrito();
    }
    
	private String ObtGrafica()
	{
		return this.Grafico;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Globales G = Globales.getInstance();
		G.CargarDatos();
		
		G.getUsuarios().Graficar();
		String Ds = G.getUsuarios().ObtGrafica();
		BufferedWriter Bwriter = null;
		String sFicheroWriter = "Graficaaaa.dot";
		try
		{			    		   
		    Bwriter = new BufferedWriter(new FileWriter(sFicheroWriter));
		    Bwriter.write(Ds);
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			// Nuevamente aprovechamos el finally para 
			// asegurarnos que se cierra el fichero.
			if (Bwriter != null)
			{
				Bwriter.close();
				
		        ProcessBuilder pbuilder;
			    
				/*
				 * Realiza la construccion del comando    
				 * en la linea de comandos esto es: 
				 * dot -Tpng -o archivo.png archivo.dot
				 */
				pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o","Grafic11ooo.png" , sFicheroWriter );
				pbuilder.redirectErrorStream( true );
				//Ejecuta el proceso
				pbuilder.start();
				//JOptionPane.showMessageDialog(null, "Grafica Generada Exitosamente");
			}	
		}
		
		System.out.println(Ds);
	}
}
