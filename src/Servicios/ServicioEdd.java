package Servicios;
import edd.Variables.*;
import edd.Estructuras.*;

public class ServicioEdd 
{
	public Globales GB = Globales.getInstance();
	public static String a  = "a";
	public NodoUsuario Usuario = new NodoUsuario("oop","asdfa");
	
	
	public String IniciarSesion(String pLogin, String pPassword)
	{
		String resultado = "" + GB.IniciarSesion(pLogin, pPassword);
		System.out.println("epicawrc  " + GB.ObtDirectorioActual());
		return resultado;
	}
	
	public void Cargar()
	{
		a = "bb";
		GB.CargarDatos();		
		Usuario.SetNickName("porque tuve que asignarme a ti");		
	}	
	
	public String Registrar(String pLogin, String pPassword)
	{
		String resultado = "" + GB.Registrar(pLogin, pPassword);
		return resultado;
	}
	
	public String CerrarSesion(String pLogin, String pPassword)
	{
		NodoUsuario Usuario = GB.getUsuarios().ObtBuscar(pLogin);
		boolean resultado = GB.CerrarSesion(Usuario);
		
		String retorna = (resultado)?"1":"0"; 
		return retorna;
	}
	
	public String AgregarDireccion(String pNickName,String pDireccion, boolean pEnvio, boolean pFacturacion)
	{
		try
		{
			GB.getUsuarios().insertardiriFin(pNickName, pDireccion, pEnvio, pFacturacion);
			return "1";
		}
		catch(Exception ex)
		{
			
		}
		return "0";
	}
	
	public String AgregarCarrito(String pNickName,int pCantidad, int pCodigoProducto)
	{
		try
		{
			NodoProducto Producto = GB.getProductos().ObtBuscar(pCodigoProducto);
			GB.getUsuarios().insertarprodacarrito(pNickName, pCantidad, Producto);
			return "1";
		}
		catch(Exception ex)
		{
			
		}
		return "0";
	}
	
	public String AgregarListaPorComprar(String pNickName,int pCantidad, int pCodigoProducto)
	{
		try
		{
			NodoProducto Producto = GB.getProductos().ObtBuscar(pCodigoProducto);
			GB.getUsuarios().InsertarProductoxComprar(pNickName, pCantidad, Producto);
			return "1";
		}
		catch(Exception ex)
		{
			
		}
		return "0";
	}
	
	/**
	 * Metodo que toma un producto de la Lista por comprar
	 * en orde LIFO [de cola] y lo agrega al carrito de compras
	 * de un usuario especifico
	 * @param pNickName
	 * @return
	 */
	public String AddDeXComprarACarrito(String pNickName)
	{
		try
		{
			NodoUsuario Usuario = GB.getUsuarios().ObtBuscar(pNickName);
			Nodo_ColaProdxComp ProductoxComprar = Usuario.ObtProductosxComprar().sacar();
			
			Usuario.setitemcarrito(ProductoxComprar.ObtCantidad(), ProductoxComprar.ObtProducto());
			return "1";
		}
		catch(Exception ex)
		{
			
		}
		return "0";
	}
	/**
	 * Metodo que elimina un producto de la Lista por comprar de un usuario especifico
	 * en orden LIFO [De Cola]
	 * @param pNickName
	 * @return
	 */
	public String SacarProductosXComprar(String pNickName)
	{
		try
		{
			NodoUsuario Usuario = GB.getUsuarios().ObtBuscar(pNickName);
			Usuario.ObtProductosxComprar().sacar();
			
			return "1";
		}
		catch(Exception ex)
		{
			
		}
		return "0";
	}
	
	public String ObtProductos()
	{
		String resultado = GB.ObtProductosEnCadena();
		return resultado;
	}
}
