package edd.Estructuras;

import java.io.BufferedWriter;
import java.io.FileWriter;



public class ListaUsuario 
{
	private NodoUsuario Cabeza;
	private NodoUsuario Fin;
	
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
		String Complemento = "";
		
		String sFicheroWriter = "GraficaLista.dot";    
	    BufferedWriter Bwriter = null;
		
	    try 
		{		         
	    	Bwriter = new BufferedWriter(new FileWriter(sFicheroWriter));			    
			
	        Bwriter.write("digraph G\n");
	        Bwriter.write("{\n");
	        Bwriter.write("node [shape=record];label=\" Lista de Usuarios \";");
	        
	        NodoUsuario iteradorUsr = Cabeza;
	        
 	    	while ( iteradorUsr != null ) 
	        {
 	    		NodoDireccion iteradorDir = iteradorUsr.ObtDirecciones().ObtCabeza();
 	    		
 	 	    	while ( iteradorDir != null ) 
 		        {
 	 	    		Bwriter.write("Uno_0 -> Uno_1 ;\n");
 	 	    		Bwriter.write("Uno_1 -> Uno_0 ;\n");
 	 	    		Bwriter.write("Uno_0 [label=\"{Caja_1|1 Turnos|Libre}\"];\n"); 	 	    		
 	 	    		Bwriter.write("Uno_1 [label=\"{Caja_2|2 Turnos|Libre}\"];\n"); 	 	    		
 		        }
 	 	    	
 	 	    	//NodoProductoxComprar iteradorPrxC = iteradorUsr.ObtProductosxComprar().ObtCabeza();
 	    		
 	 	    	/*while ( iteradorPrxC != null ) 
 		        {
 	 	    		
 		        }*/

	        }
	    	
	        Bwriter.write("}\n");	     		    
		}
	    catch (Exception e) 
		{
			// TODO Auto-generated catch block
	    	//log.error("Error en " + e.getMessage());
			System.out.println( "Error en " + e.getMessage());
		}		
		finally 
		{
			try 
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
					pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o","GraficaLista.png" , sFicheroWriter );
					pbuilder.redirectErrorStream( true );
					//Ejecuta el proceso
					pbuilder.start();
					//log.info("SERVIDOR->Grafica Generada Exitosamente");
					//System.out.println( "Grafica Generada Exitosamente");
				}
			} 
			catch (Exception e) 
			{
				//log.error("Error en " + e.getMessage());
				System.out.println( "Error en " + e.getMessage());
			}
		}
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
}
