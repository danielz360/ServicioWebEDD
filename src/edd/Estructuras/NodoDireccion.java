package edd.Estructuras;


public class NodoDireccion 
{
	private String NickNameUsuario, Direccion;
	private boolean Envio,Facturacion;
	private NodoDireccion NodoSig;
	private NodoDireccion NodoAnt;
	
	/**
	* 
	* @param pDireccion
	* @param pEnvio
	* @param pFacturacion
	*/
	
	public NodoDireccion(String pDireccion, boolean pEnvio,boolean pFacturacion) 
	{
		// TODO Auto-generated constructor stub
		Direccion = pDireccion;
		Envio = pEnvio;
		pFacturacion = Facturacion;
		NodoAnt = null;
		NodoSig = null;
	}
	
	public String ObtDireccion()
	{
		return Direccion;
	}
	public void SetDireccion(String pDireccion)
	{
		Direccion = pDireccion;
	}
	
	public boolean ObtEnvio()
	{
		return Envio;
	}
	public void SetEnvio(boolean pEnvio)
	{
		Envio = pEnvio;
	}
	public boolean ObtFacturacion()
	{
		return Facturacion;
	}
	public void SetFacturacion(boolean pFacturacion)
	{
		Facturacion = pFacturacion;
	}
	
	public NodoDireccion ObtNodoSiguiente()
	{
		return NodoSig;
	}
	public void SetNodoSiguiente(NodoDireccion nodo)
	{
		NodoSig = nodo;
	}
	public NodoDireccion ObtNodoAnterior()
	{
		return NodoAnt;
	}
	public void SetNodoAnterior(NodoDireccion nodo)
	{
		NodoAnt = nodo;	
	}
	public String ObtNickNameUsuario()
	{
		return NickNameUsuario;
	}	
	public String SetNickNameUsuario()
	{
		return NickNameUsuario;
	}	
}
