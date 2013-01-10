package edd.Estructuras;


public class ListaDireccion 
{
	private NodoDireccion Cabeza;
	private NodoDireccion Fin;
	
	/**Constructor*/
	public ListaDireccion() 
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
    private void Enlazar(NodoDireccion nodoA, NodoDireccion nodoB) 
    {
    	nodoA.SetNodoSiguiente(nodoB);
        nodoB.SetNodoAnterior(nodoA);
    }

    /*inserta un nuevo nodo al inicio de la lista*/
    public void InsertarInicio(String pDireccion, boolean pEnvio,boolean pFacturacion) 
    {
    	NodoDireccion nuevo = new NodoDireccion( pDireccion, pEnvio, pFacturacion);
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
    public void InsertarFinal(String pDireccion, boolean pEnvio,boolean pFacturacion) 
    {
    	NodoDireccion nuevo = new NodoDireccion( pDireccion, pEnvio, pFacturacion);
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

    //elimina el nodo del frente de la lista
    public void EliminarInicio() 
    {
        if ( !EstaVacia() ) 
        {
        	NodoDireccion primero = Cabeza.ObtNodoSiguiente();
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
        	NodoDireccion ultimo = Fin.ObtNodoAnterior();
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
    public NodoDireccion ObtBuscar(String pDireccion) 
    {
    	NodoDireccion buscado = null;
    	NodoDireccion iterador = Cabeza;
        while ( buscado == null && iterador != null ) 
        {
            if ( iterador.ObtDireccion().equals(pDireccion)) 
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
    	NodoDireccion iterador = Cabeza;
        while ( iterador != null ) 
        {
            System.out.print( iterador.ObtDireccion() + " -> " );
            iterador = iterador.ObtNodoSiguiente();
        }
        System.out.println( "null" );
    }
    
    public NodoDireccion ObtCabeza()
    {
    	return Cabeza;
    }
    public void SetCabeza(NodoDireccion pCabeza)
    {
    	Cabeza = pCabeza;
    }
    
    public NodoDireccion ObtFin()
    {
    	return Fin;
    }
    public void SetFin(NodoDireccion pFin)
    {
    	Fin = pFin;
    }
}
