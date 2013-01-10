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
    Cola_Carrito compras;
    
    
    public Nodo_Detalle( int idd, int cantt, int precioo, Cola_Carrito compras1){
    id = idd;
    cantidad = cantt;
    precio = precioo;
    compras =compras1;
    compras.idendetalle(idd); 
    }  
    public void ingresoProd(int cantidad, NodoProducto pProducto){
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
    }
    
}//termina clase Nodo_Detalle
