/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.Estructuras;


public class Nodo_ColaCarrito {
    Nodo_ColaCarrito ultimo;
    int cantidad;
    NodoProducto Producto;
  //tablaHash apProducto;
    

public Nodo_ColaCarrito(int cant, NodoProducto pProducto){
        cantidad = cant;
        Producto = pProducto;
        // apProducto = product;
        ultimo = null;
    }




}//termina clase
