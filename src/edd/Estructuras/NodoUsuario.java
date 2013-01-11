package edd.Estructuras;


public class NodoUsuario 
{
	private String NickName, Contrasena;
	private int InicioSesion;
	private NodoUsuario NodoSig;
	private NodoUsuario NodoAnt;
	private ListaDireccion Direcciones;
	private Cola_ProdxComp ProductosxComprar;
    private Cola_Carrito carrito = new Cola_Carrito();

	/**Constructor
	 * @pNombre Nombre del Usuario
	 * @pNickName NickName del Usuario
	 * @pContrasena Contrasena del Usuario
	 * @pNodoAnt Nodo Padre 
	 * */
	public NodoUsuario(String pNickName,String pContrasena, ListaDireccion pDirecciones) 
	{
		// TODO Auto-generated constructor stub
		NickName = pNickName;
		Contrasena = pContrasena;
		Direcciones = pDirecciones;
		ProductosxComprar = new Cola_ProdxComp();
		carrito = new Cola_Carrito();
		NodoAnt = null;
		NodoSig = null;
		InicioSesion = 0;
	}
	
	public NodoUsuario(String pNickName,String pContrasena) 
	{
		// TODO Auto-generated constructor stub
		NickName = pNickName;
		Contrasena = pContrasena;
		Direcciones = new ListaDireccion();
		ProductosxComprar = new Cola_ProdxComp();
		carrito = new Cola_Carrito();
		NodoAnt = null;
		NodoSig = null;
		InicioSesion = 0;
	}
	
	public ListaDireccion ObtDirecciones()
	{
		return Direcciones;
	}
	public void SetDirecciones(ListaDireccion pDirecciones)
	{
		Direcciones = pDirecciones;
	}
	
	public Cola_ProdxComp ObtProductosxComprar()
	{
		return ProductosxComprar;
	}
	public void SetProductosxComprar(Cola_ProdxComp pProductosxComprar)
	{
		ProductosxComprar = pProductosxComprar;
	}
	
	public NodoUsuario ObtNodoSiguiente()
	{
		return NodoSig;
	}
	public void SetNodoSiguiente(NodoUsuario nodo)
	{
		NodoSig = nodo;
	}
	public NodoUsuario ObtNodoAnterior()
	{
		return NodoAnt;
	}
	public void SetNodoAnterior(NodoUsuario nodo)
	{
		NodoAnt = nodo;	
	}
	public String ObtNickName()
	{
		return NickName;
	}	
	public void SetNickName(String pNickName)
	{
		NickName =  pNickName;
	}	
	public String ObtContrasena()
	{
		return Contrasena;
	}	
	public void SetContrasena(String pContrasena)
	{
		Contrasena = pContrasena;
	}	
	public int ObtInicioSesion()
	{
		return InicioSesion;
	}
	public void SetInicioSesion(int pInicioSesion)
	{
		InicioSesion = pInicioSesion;
	}
	
    public void setdireccionfin(String dir, boolean penvio, boolean pfacturacion){
        Direcciones.InsertarFinal(dir, penvio, pfacturacion);
    }
    public void setdireccionini(String dir, boolean penvio, boolean pfacturacion){
        Direcciones.InsertarInicio(dir, penvio, pfacturacion);
    }
    
    public void SetProductoxComprar(int cantidad, NodoProducto pProducto){
    	ProductosxComprar.ingresar(cantidad, pProducto);
    }
    
    public void setitemcarrito(int cantidad, NodoProducto pProducto){
        carrito.ingresar(cantidad,pProducto);
    }
    
    public Cola_Carrito obtenercarrito(){
        return carrito;
    }
}
