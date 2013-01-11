package edd.Estructuras;

import edd.Variables.Globales;

public class NodoListaColasDetalle {
	int idDetalle;
	NodoListaColasDetalle sig;
	Cola_Detalle detalle = new Cola_Detalle();
	Globales GB = Globales.getInstance();
	
	public NodoListaColasDetalle(int id,int cantidad,int precio,int producto){
		
		setitemDetalle(id, cantidad, precio, producto);
	}
	
	public void setitemDetalle(int id,int cantidad,int precio,int producto){
		NodoProducto nprod = GB.getProductos().ObtBuscar(producto);
		detalle.ingresar(id, cantidad, precio, nprod);
	}
	
	public int obtDetalle(){
		return idDetalle;
	}
	
}
