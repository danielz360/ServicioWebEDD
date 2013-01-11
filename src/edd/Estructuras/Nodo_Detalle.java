/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.Estructuras;


public class Nodo_Detalle {
      Nodo_Detalle ultimo;
    int cantidad;
    int precio;
    int id;
    NodoProducto compras;
    
    
    public Nodo_Detalle( int idd, int cantt, int precioo, NodoProducto compras1){
    	id = idd;
    	cantidad = cantt;
    	precio = precioo;
    	compras =compras1;
    }  
    /*public void ingresoProd(int cantidad, NodoProducto pProducto){
        compras.ingresar(cantidad, pProducto);
    } 
    
    public void imprimirconsola(){
        compras.imprimir();
    }
    
    public void graficar(){
        compras.imacola();
    }
    public Cola_Carrito obtenercarrito(){
            return compras;
        }
    
    public String codsubgrafo(){
    return compras.subgraphLista();
    }
    public String nodo1(){
       return compras.nodo1();
    }*/
    
}//termina clase Nodo_Detalle
