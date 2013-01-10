package Servicios;
import edd.Variables.*;
import edd.Estructuras.*;

public class ServicioEdd 
{
	public Globales GB = Globales.getInstance();	
	private static Cifrado C = new Cifrado();
	
	public ServicioEdd() {
		
		Cargar();
	}
	/**
	 * Metodo que Inicia la sesion de un usario
	 * @param pLogin
	 * @param pPassword
	 * @return 2 si la autenticacion fue exitosa, 1 si el usuario ya 
	 * habia iniciado sesion y 0 si el login o contrasena del usuario no existe
	 */
	public String IniciarSesion(String pLogin, String pPassword)
	{
		String resultado = "" + GB.IniciarSesion(pLogin, pPassword);
		resultado = C.Encriptar(resultado);
		return resultado;
	}
	
	public void Cargar()
	{
		
		GB.CargarDatos();				
	}	
	
	/**
	 * Registra a un Usuario al Sistema
	 * @param pLogin
	 * @param pPassword
	 * @return 1 si el registro fue exitoso y 0 si no lo fue
	 */
	public String Registrar(String pLogin, String pPassword)
	{
		String resultado = "" + GB.Registrar(pLogin, pPassword);
		resultado = C.Encriptar(resultado);
		return resultado;
	}
	
	/**
	 * Metodo que cierra la sesion de un Usuario 
	 * @param pLogin
	 * @param pPassword
	 * @return 1 si se Cerro Sesion Correctamente y 0 si no
	 */
	public String CerrarSesion(String pLogin, String pPassword)
	{
		NodoUsuario Usuario = GB.getUsuarios().ObtBuscar(pLogin);
		boolean resultado = GB.CerrarSesion(Usuario);
		
		String retorna = (resultado)?"1":"0";
		retorna = C.Encriptar(retorna);
		return retorna;
	}
	
	/**
	 * Metodo que Agrega una Direccion a un Usuario especifico
	 * @param pNickName
	 * @param pDireccion
	 * @param pEnvio
	 * @param pFacturacion
	 * @return 1 si se Agrego Correctamente y 0 si no fue asi
	 */
	public String AgregarDireccion(String pNickName,String pDireccion, boolean pEnvio, boolean pFacturacion)
	{
		try
		{
			GB.getUsuarios().insertardiriFin(pNickName, pDireccion, pEnvio, pFacturacion);
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");
	}
	
	/**
	 * Metodo que Agrega una Producto al carrito de un Usuario especifico
	 * @param pNickName
	 * @param pCantidad
	 * @param pCodigoProducto
	 * @return 1 si se Agrego Correctamente y 0 si no fue asi
	 */
	public String AgregarCarrito(String pNickName,int pCantidad, int pCodigoProducto)
	{
		try
		{
			NodoProducto Producto = GB.getProductos().ObtBuscar(pCodigoProducto);
			GB.getUsuarios().insertarprodacarrito(pNickName, pCantidad, Producto);
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");
	}
	/**
	 * Metodo que Agrega una Producto a la lista de Productos por Comprar
	 *  de un Usuario especifico
	 * @param pNickName
	 * @param pCantidad
	 * @param pCodigoProducto
	 * @return 1 si se Agrego Correctamente y 0 si no fue asi
	 */
	public String AgregarListaPorComprar(String pNickName,int pCantidad, int pCodigoProducto)
	{
		try
		{
			NodoProducto Producto = GB.getProductos().ObtBuscar(pCodigoProducto);
			GB.getUsuarios().InsertarProductoxComprar(pNickName, pCantidad, Producto);
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");
	}
	
	/**
	 * Metodo que toma un producto de la Lista por comprar
	 * en orde LIFO [de cola] y lo agrega al carrito de compras
	 * de un usuario especifico
	 * @param pNickName
	 * @return 1 si se Agrego Correctamente y 0 si no fue asi
	 */
	public String AddDeXComprarACarrito(String pNickName)
	{
		try
		{
			NodoUsuario Usuario = GB.getUsuarios().ObtBuscar(pNickName);
			Nodo_ColaProdxComp ProductoxComprar = Usuario.ObtProductosxComprar().sacar();
			
			Usuario.setitemcarrito(ProductoxComprar.ObtCantidad(), ProductoxComprar.ObtProducto());
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");
	}
	/**
	 * Metodo que elimina un producto de la Lista por comprar de un usuario especifico
	 * en orden LIFO [De Cola]
	 * @param pNickName
	 * @return 1 si se Saco Correctamente y 0 si no fue asi
	 */
	public String SacarProductosXComprar(String pNickName)
	{
		try
		{
			NodoUsuario Usuario = GB.getUsuarios().ObtBuscar(pNickName);
			Usuario.ObtProductosxComprar().sacar();
			
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");
	}
	
	public String ObtProductos()
	{
		String resultado = GB.ObtProductosEnCadena();
		return resultado;
	}
	
	public String InsertarProducto(int pCodigo,String pNombre, String pMarca, double pPrecio ,String pUrlImg)
	{
		try
		{
			GB.getProductos().Insertar(pCodigo, pNombre, pMarca, pPrecio, pUrlImg);
			
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");		
	}
	
	public String InsertarFactura(int pCodigo,String pNombre, String pMarca, double pPrecio ,String pUrlImg)
	{
		try
		{
			GB.getProductos().Insertar(pCodigo, pNombre, pMarca, pPrecio, pUrlImg);
			
			return C.Encriptar("1");
		}
		catch(Exception ex)
		{
			
		}
		return C.Encriptar("0");		
	}
}
