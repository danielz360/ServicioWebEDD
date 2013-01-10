package edd.Estructuras;

public class TablaHashProducto 
{
	private NodoProducto [] Tabla;
	private int [] NoIntentos; 
	private int [] Borrados; 
	private int TamanoInicial = 23;
	private int n = 0;
	private String Codigos = "";
	//Indica el no de caracteres por cada 
	//pedazo que se va a tomar en la funcion de plegamiento
	private int DivisorLlave = 2;
	
	public TablaHashProducto() 
	{
		// TODO Auto-generated constructor stub
		Tabla = new NodoProducto [TamanoInicial];
		NoIntentos = new int [TamanoInicial];
		Borrados = new int [TamanoInicial];
		
		for (int i = 0; i < TamanoInicial; i++)
		{
			 Tabla[i] = null; 
		}             
	}
	
	private int FuncionHash(int pLlave, int pLongitudTabla)
	{
		String Llave = "" + pLlave;
		int IndiceObt = 0;		
		int divisor = DivisorLlave;
		int contador = 0;
		
		while ( true )
		{
			if((contador + divisor) > (Llave.length()))
			{
				divisor = Llave.length() - contador;				
			}
			
			if((contador + divisor) <= (Llave.length() ))
			{
				IndiceObt += Integer.parseInt(Llave.substring(contador, contador + divisor));
				
				if((divisor + contador) == (Llave.length()))
				{
					break;
				}
				
				contador += divisor;
			}
		}		
		
		String SinSignificativo = "" + IndiceObt ;
		String LongitudTabla  = "" + pLongitudTabla;
		//Primero Se hace que el numero de caracteres del valor obtenido sea igual
		// al numero de caracteres del numero de la longitud de la tabla
		if(SinSignificativo.length() > LongitudTabla.length())
		{
			//Se quitan las Cifras Significativas
			SinSignificativo = SinSignificativo.substring(SinSignificativo.length() - LongitudTabla.length(), SinSignificativo.length() );	
		}		

		//Se hace que el Numero obtenido con la misma cantidad de digitos sea menor en valor
		if(Integer.parseInt(SinSignificativo) > pLongitudTabla)
		{
			//Se quita una cifra significativa
			SinSignificativo = SinSignificativo.substring(1, SinSignificativo.length() );			
		}											
		
		IndiceObt = Integer.parseInt(SinSignificativo);
		
		return IndiceObt;
	}
	
	/**
	* La funcion de  rehashing es por exploracion cuadratica
	* @param pLlave
	* @param pIndice
	* @param pIntento
	* @return
	*/
	private int FuncionReHash(int pLlave, int pIndice,int pIntento)
	{
		int NuevoIndice = pIndice + (pIntento * pIntento);		
		return NuevoIndice;
	}
	
	private double ObtFactorCarga()
	{
		double Factor = 0;		
		int l =Tabla.length;		
		Factor = Double.parseDouble(""+n) / Double.parseDouble(""+l);
		
		return Factor;
	}
	
	private void Rehash()
	{
		NodoProducto [] AuxTabla = Tabla;
		int [] AuxBorrados = Borrados;
		
		int NuevaLongitud = Tabla.length*2;		
		NodoProducto [] NuevaTabla = new NodoProducto [NuevaLongitud];
		int [] NuevaNoIntentos = new int [NuevaLongitud];
		int [] NuevaBorrados = new int [NuevaLongitud];		
		
		Tabla = NuevaTabla;
		NoIntentos = NuevaNoIntentos;
		Borrados = NuevaBorrados;
		n = 0;
		String [] Codigo = Codigos.split("-");
		Codigos = "";
		for(int a = 0; a < Codigo.length;a++)
		{
			NodoProducto nodo = this.ObtBuscar(Integer.parseInt(Codigo[a]),AuxTabla,AuxBorrados);
			if(Codigo[a].equals("496382357"))
			{
				System.out.println("r");
			}
			if(nodo != null)
			{

				this.Insertar(nodo.ObtCodigo(), nodo.ObtNombre(), nodo.ObtMarca(), nodo.ObtPrecio(), nodo.ObtUrlImagen());
				
				if(this.EstabaBorrado(nodo.ObtCodigo(), AuxTabla, AuxBorrados))
				{
					this.Borrar(nodo.ObtCodigo());
				}
			}			
		}
	}
	
	/**
	 * 
	 * @param pCodigo
	 * @param pNombre
	 * @param pMarca
	 * @param pPrecio
	 * @param pUrlImg
	 */
    public void Insertar(int pCodigo,String pNombre, String pMarca, double pPrecio ,String pUrlImg) 
    {
    	double FactorCarga = ObtFactorCarga();
    	
    	if(FactorCarga > .8)
    	{
    		this.Rehash();
    	}
    	
    	//La llave es el Codigo del Producto
    	NodoProducto Nodo = new NodoProducto(pCodigo,pNombre, pMarca, pPrecio ,pUrlImg);
    	
    	int indice = FuncionHash(pCodigo,Tabla.length);    	            	
    	    	
    	if(YaExiste(indice,pCodigo))
    	{
    		System.out.println("SERVIDOR->Llave Repetida , el nodo con Codigo "  + pCodigo + " ya habia sido ingresado");    		
    		return;
    	}    	
    	
    	int intentos = 0;	
        while (Tabla[indice] != null)
        {
        	intentos++;
        	indice = this.FuncionReHash(pCodigo, indice, intentos);
        	        	
        	//Para hacerlo circular
        	while(indice > (Tabla.length-1)){indice = indice - Tabla.length;}
        	
        	if(YaExiste(indice,pCodigo))
        	{
        		System.out.println("SERVIDOR->Llave Repetida , el nodo con Codigo "  + pCodigo + " ya habia sido ingresado");
        		return;
        	}
        }
        
        Tabla[indice] = Nodo;
        this.NoIntentos[indice] = intentos;
        Codigos = (Codigos.length() == 0)? "" + pCodigo : Codigos + "-" + pCodigo;
        n++;
    }
    
	/**
	 * Verifica si un producto habia sido borrado de una tabla especifica
	 * @param pLlave
	 * @param pTabla
	 * @param pBorrados
	 * @return
	 */
    public boolean EstabaBorrado(int pLlave, NodoProducto [] pTabla,int [] pBorrados)
    {
    	int indice = FuncionHash(pLlave,pTabla.length);    	            	
    	
    	int intentos = 0;
        while (pTabla[indice].ObtCodigo() != pLlave)
        {
        	intentos++;
        	indice = this.FuncionReHash(pLlave, indice, intentos);       
        	
        	//Para hacerlo circular
        	while(indice > (pTabla.length - 1)){indice = indice - pTabla.length;}
        }    	
        
        if(pBorrados[indice] == 1)
        {
        	return true;
        }
        return false;
    }
    
	/**
	 * Verifica si un producto ha sido borrado de la tabla
	 * @param pLlave
	 * @return
	 */
    public boolean EstaBorrado(int pLlave)
    {
    	int indice = FuncionHash(pLlave,Tabla.length);    	            	
    	
    	int intentos = 0;
        while (Tabla[indice].ObtCodigo() != pLlave)
        {
        	intentos++;
        	indice = this.FuncionReHash(pLlave, indice, intentos);       
        	
        	//Para hacerlo circular
        	while(indice > (Tabla.length - 1)){indice = indice - Tabla.length;}
        }    	
        
        if(Borrados[indice] == 1)
        {
        	return true;
        }
        return false;
    }
    public void Borrar(int pLlave)
    {
    	int indice = FuncionHash(pLlave,Tabla.length);    	            	
    	
    	int intentos = 0;
        while (Tabla[indice].ObtCodigo() != pLlave)
        {
        	intentos++;
        	indice = this.FuncionReHash(pLlave, indice, intentos);       
        	
        	//Para hacerlo circular
        	while(indice > (Tabla.length-1)){indice = indice - Tabla.length;}
        }    	
        
        if(Borrados[indice] == 1)
        {
        	System.out.println("SERVIDOR-> No existe tal producto en la Tabla, ya que fue borrado");
        }
        else
        {
        	Borrados[indice] = 1;
        }
    }
    
    public boolean YaExiste(int pIndice,int pCodigo)
    {
    	if(Tabla[pIndice] != null && Tabla[pIndice].ObtCodigo() == pCodigo)
    	{
    		return true;
    	}
    	return false;
    }
       
    
    /**
     *Metodo que devuelve un Producto
     *retornara null si el nodo ha sido borrado 
     * @param pLlave
     * @return
     */
    public NodoProducto ObtBuscar(int pLlave)
    {
    	int indice = FuncionHash(pLlave,Tabla.length);    	            	
    	
    	int intentos = 0;
        while (Tabla[indice].ObtCodigo() != pLlave)
        {
        	intentos++;
        	indice = this.FuncionReHash(pLlave, indice, intentos);
        	
        	//Para hacerlo circular
        	while(indice > (Tabla.length-1)){indice = indice - Tabla.length;}
        }

        if(Borrados[indice] == 0)
        {
        	return Tabla[indice];
        }
        return null;        
    }
    
	/**
	 * Metodo que devuelve un Producto para una Tabla especifica
	 * retornara null si el nodo ha sido borrado
	 * @param pLlave
	 * @param pTabla
	 * @return
	 */
    public NodoProducto ObtBuscar(int pLlave,NodoProducto[]  pTabla,int[]  pBorrados)
    {
    	int indice = FuncionHash(pLlave,pTabla.length);    	            	
    	
    	int intentos = 0;
        while (pTabla[indice].ObtCodigo() != pLlave)
        {
        	intentos++;
        	indice = this.FuncionReHash(pLlave, indice, intentos);    
        	
        	//Para hacerlo circular
        	while(indice > (pTabla.length-1)){indice = indice - pTabla.length;}
        }
        
        if(pBorrados[indice] == 0)
        {
        	return pTabla[indice];
        }
        return null;
    }
    
    public NodoProducto []  ObtTabla()
    {
    	return Tabla;
    }
    public void  SetTabla(NodoProducto [] pTabla)
    {
    	Tabla = pTabla;
    }
    
    public int []  ObtTablaIntentos()
    {
    	return this.NoIntentos;
    }
    public void  SetTablaIntentos(int [] TablaIntentos)
    {
    	this.NoIntentos = TablaIntentos;
    }
    
    public int []  ObtTablaBorrados()
    {
    	return this.Borrados;
    }
    public void  SetTablaBorrados(int [] TablaBorrados)
    {
    	this.Borrados = TablaBorrados;
    }
    
    public String ObtCodigos()
    {
    	return Codigos;
    }
    
	/**
	 * @param args
	 */
	/*public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		TablaHashProducto Tabla= new TablaHashProducto();
		Tabla.Insertar(11010111, "Pepinillo", "Chapina", 1.50, "where");
		Tabla.Insertar(12341325, "Pepinillo1", "Chapina1", 1.50, "where1");
		Tabla.Insertar(13500544, "Pepinillo2", "Chapina2", 1.50, "where2");
		Tabla.Insertar(12345456, "Pepinillo3", "Chapina3", 1.50, "where3");
		Tabla.Insertar(13244435, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(21341412, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341413, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341414, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341415, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341416, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341417, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341418, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341419, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(13244436, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13244437, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13244438, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13244439, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13244431, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13244421, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13244422, "Pepinillo4", "Chapina4", 1.50, "where4");
		
		Tabla.Insertar(21341486, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341437, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341248, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(21341489, "Pepinillo5", "Chapina5", 1.50, "where5");
		Tabla.Insertar(13245436, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13264437, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13274438, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13264439, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13247431, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13246421, "Pepinillo4", "Chapina4", 1.50, "where4");
		Tabla.Insertar(13245422, "Pepinillo4", "Chapina4", 1.50, "where4");
		
		System.out.println(Tabla.ObtBuscar(21341412).ObtNombre());
		System.out.println(Tabla.ObtBuscar(12345456).ObtNombre());
		
		Tabla.Borrar(12345456);
		Tabla.Borrar(12345456);
		
		NodoProducto nodo = Tabla.ObtBuscar(12345456);
		
		if(nodo == null)
		{
			System.out.println("Esta Borrado el nodo con Codigo " + 12345456);	
		}
	}*/
}
