package edd.Estructuras;
/**
*
* @author Evelyn
*/
public class ListadeColasDetalle {
	NodoListaColasDetalle cabeza,ultimo;
	int tamano;
	
	public ListadeColasDetalle (){
        cabeza = null;
        tamano = 0;
    }// termina constructor
	
	//------------------------Metodo Vacio -----------------------------------
    public boolean Vacio (){        
        return (cabeza == null);        
    }
    
  //---------------------------Metodo Ultimo Ingresado ---------------------------------
    public NodoListaColasDetalle ultimos(){
    	NodoListaColasDetalle aux = cabeza;
    	NodoListaColasDetalle ant = aux;
        while ( ant != null){
            aux = ant;
            ant = ant.sig;
        }
        System.out.println(aux.idDetalle);
        return aux;
    }
    
    //--------------------Metodo de existencia -------------------------
    
    public boolean existe(int id) {
        System.out.println("entro");
        boolean exist = false;
        NodoListaColasDetalle aux = cabeza;
        while (aux != null) {
            if (aux.idDetalle == (id)) {
                //if(aux.apProducto.equals(product)){
                exist = true;
            }
            aux = aux.sig;
        }
        

        return exist;
    }
	
  //---------------Metodo Ingresar -------------------------------------------------     
    public void ingresar (int id,int cantidad,int precio,int producto)   {
        System.out.println("entro en ingresar cola Carrito");
        NodoListaColasDetalle nuevo = new NodoListaColasDetalle(id, cantidad, precio, producto);
           if (Vacio()){
               cabeza = nuevo;
               ultimo = cabeza;
               tamano++;
           } else {
               if (!existe(id)){
              //     if (!existe(product.nombre)){
            	   NodoListaColasDetalle aux = ultimos();
                  aux.sig = nuevo;
                  tamano++;
              }else{
            	  NodoListaColasDetalle nodo =obtener(id);
            	  nodo.setitemDetalle(id, cantidad, precio, producto);
              }
           }     
    }// termina metodo ingresar cola 
    
  //--------------------Metodo de imprimir en consola -------------------------     
    
    public void imprimir(){
        System.out.println(tamano);
        NodoListaColasDetalle aux = cabeza;
        NodoListaColasDetalle ant = aux;
        while (aux != null){
            System.out.println("Producto " + aux.idDetalle+  /*"aux.produc.name"*/ "");
            aux= aux.sig;
        }
        //Nodo_ColaCarrito esta = ultimos();
        //System.out.println("Producto: "+ esta.cantidad+ /*esta.product.nombre*/ "" );     
    }
    
  //--------------------Metodo para obtener -------------------------
      
    public NodoListaColasDetalle obtener(int id) {
        System.out.println("entro");
        
        NodoListaColasDetalle aux = cabeza;
        NodoListaColasDetalle exist = aux;
        while (aux != null) {
            if (aux.idDetalle == (id)) {
                exist = aux;
            }
            aux = aux.sig;
        }
        

        return exist;
    }
	
	
}
