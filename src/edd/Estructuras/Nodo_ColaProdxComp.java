
package edd.Estructuras;


public class Nodo_ColaProdxComp {
    Nodo_ColaProdxComp ultimo;
    int cantidad;
    NodoProducto Producto;
    
 public Nodo_ColaProdxComp (int cant ,NodoProducto pProducto){
        cantidad = cant;
        Producto = pProducto;
        ultimo = null;
    }
      
 public int ObtCantidad()
 {
	 return cantidad;
 }
 public NodoProducto ObtProducto()
 {
	 return Producto;
 }
}//termina clase
