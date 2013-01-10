package edd.Estructuras;


public class NodoProducto 
{
	private int Codigo;
	private double Precio;
	private String Nombre, Marca, UrlImagen;
	private boolean Envio,Facturacion;
	private NodoProducto NodoSig;
	private NodoProducto NodoAnt;
	
	/**
	* 
	* @param pDireccion
	* @param pEnvio
	* @param pFacturacion
	*/
	
	public NodoProducto(int pCodigo,String pNombre, String pMarca, double pPrecio ,String pUrlImg) 
	{
		// TODO Auto-generated constructor stub
		Codigo = pCodigo;
		Nombre = pNombre;
		Marca = pMarca;
		Precio = pPrecio;
		UrlImagen = pUrlImg;
		NodoAnt = null;
		NodoSig = null;
	}

	public int ObtCodigo()
	{
		return Codigo;
	}
	public void SetDireccion(int pCodigo)
	{
		Codigo = pCodigo;
	}
	
	public String ObtNombre()
	{
		return Nombre;
	}
	/**
	 * 
	 * @param pNombre
	 */
	public void SetNombre(String pNombre)
	{
		Nombre = pNombre;
	}
	
	public String ObtMarca()
	{
		return Marca;
	}
	/**
	 * 
	 * @param pMarca
	 */
	public void SetMarca(String pMarca)
	{
		Marca = pMarca;
	}
	
	public String ObtUrlImagen()
	{
		return UrlImagen;
	}
	/**
	 * 
	 * @param pUrlImg
	 */
	public void SetUrlImagen(String pUrlImg)
	{
		UrlImagen = pUrlImg;
	}
	public double ObtPrecio()
	{
		return Precio;
	}
	public void SetPrecio(double pPrecio)
	{
		Precio = pPrecio;
	}
	
	public NodoProducto ObtNodoSiguiente()
	{
		return NodoSig;
	}
	public void SetNodoSiguiente(NodoProducto nodo)
	{
		NodoSig = nodo;
	}
	public NodoProducto ObtNodoAnterior()
	{
		return NodoAnt;
	}
	public void SetNodoAnterior(NodoProducto nodo)
	{
		NodoAnt = nodo;	
	}
}
