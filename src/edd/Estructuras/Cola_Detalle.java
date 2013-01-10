/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.Estructuras;
import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import edd.Variables.var;

public class Cola_Detalle {
     private static Logger log = Logger.getLogger(Cola_Detalle.class); // se intancia un logger de la clase donde esta el metodo.
    Nodo_Detalle cabeza;
    Nodo_Detalle ultimoo;
    int tamaño;
    graphviz gv;
    int iteracion=0;
    
    
    public Cola_Detalle (){
        cabeza = null;
        ultimoo=null;
        tamaño = 0;
    }// termina constructor
   
//------------------------Metodo Vacio -----------------------------------
    public boolean Vacio (){        
        return (cabeza == null && ultimoo ==null);        
    }
    
//---------------------------Metodo Ultimo Ingresado ---------------------------------
 public Nodo_Detalle ultimos(){
     Nodo_Detalle aux = cabeza;
     Nodo_Detalle ant = aux;
     while ( ant != null){
         aux = ant;
         ant = ant.ultimo;
     }
     System.out.println(aux.id);
     return aux;
 }
 
 //--------------------Metodo de existencia -------------------------
 
    public boolean existe(int idd) {
        System.out.println("entro");
        boolean exist = false;
        Nodo_Detalle aux = cabeza;
        while (aux != null) {
            if (aux.id == (idd)) {
                
                exist = true;
            }
            aux = aux.ultimo;
        }
        

        return exist;
    }
 
 
//---------------Metodo Ingresar -------------------------------------------------     
  public void ingresar (int idd, int cantt, int precioo, NodoProducto compras1)   {
      System.out.println("entro en ingresar cola Detalle");
      Nodo_Detalle nuevo = new Nodo_Detalle( idd, cantt, precioo, compras1);
         if (Vacio()){
             cabeza = nuevo;
             ultimoo = cabeza;
             tamaño++;
              System.out.println("Ingreso Nuevo a Cola Detalle");
              log.info("Ingreso Nuevo a Cola Detalle" +nuevo.id);
         } else {
             if (!existe(idd)){
            //     if (!existe(product.nombre)){
                Nodo_Detalle aux = ultimos();
                aux.ultimo = nuevo;
                tamaño++;
                System.out.println("ingreso nuevo Detalle en ultima posicion"+nuevo.id);
                log.info ("ingreso nuevo Detalle en ultima posicion"+nuevo.id);
            }else{
                System.out.println("El Detalle "+idd+" ya esta registrado");
                log.warn("El Detalle "+idd+" ya esta registrado");
                /*
                 * System.out.println("El producto "+producto.nombre+" ya esta registrado");
                log.warn("El producto "+product.nombre+" ya esta registrado");
                 */
            }
         }     
  }// termina metodo ingresar cola 
 
  public Cola_Carrito obtenerCarrito(int id){
        Nodo_Detalle nodo =  obtenernodo(id);
        return nodo.obtenercarrito();
    }
  
  
     
 //--------------------Metodo de imprimir en consola -------------------------     
      
    public void imprimir(){
        System.out.println(tamaño);
        Nodo_Detalle aux = cabeza;
        Nodo_Detalle ant = aux;
        while (aux != null){
            System.out.println("Detalle " + aux.id+  /*"aux.produc.name"*/ "");
            aux= aux.ultimo;
        }
        //Nodo_ColaCarrito esta = ultimos();
        //System.out.println("Producto: "+ esta.cantidad+ /*esta.product.nombre*/ "" );     
    }// termina metodo imprimir 
       
   //--------------------Metodo para obtener -------------------------
   public Nodo_Detalle obtener(){
      
       String cadenaNom = "";
       Nodo_Detalle aux  = cabeza;
       Nodo_Detalle ant = aux;
       while (aux.ultimo != null){
           cadenaNom+=aux.id + ";" ;
           //cadenaNom+=aux.producto.nombre +";" ; 
            aux = aux.ultimo;
       }
       
        Nodo_Detalle esta = ultimos();
         cadenaNom+=aux.id + ";" ;
         System.out.println("Prueba metodo de obtener el nodo de cola detalle");       
         System.out.println("con aux "+aux);
         System.out.println("Con cadneaNom "+cadenaNom);
                 
           //cadenaNom+=aux.producto.nombre +";" ;       
       return aux;
   }// termina metodo de obtener el producto  
   
   public Nodo_Detalle buscarobtener(int id){
       Nodo_Detalle aux  = cabeza;
       Nodo_Detalle ant = null;
       
       while (aux != null){
           if(id == aux.id){
               ant = aux;
           }
            aux = aux.ultimo;
       }    
       
       return ant;
   }
    
   //--------------------Metodo para sacar -------------------------
   public String sacar(){ //sale el primero y el segundo queda como cabeza
       String sale = "";
       Nodo_Detalle aux  = cabeza;
       sale = String.valueOf(cabeza.id);
       cabeza = aux.ultimo;
       return sale;
   }// termina metodo para sacar  
    
     //--------------------Metodos de graficar -------------------------
    
    public String graphLista() {
        System.out.println("Tamaño de la lista: " + tamaño + "\n");
        Nodo_Detalle aux = cabeza;
        Nodo_Detalle aux2 = cabeza;
        String src = "edge[color = blue]\n";
        String tip2 = "";
        String tip3 = "";
        //System.out.print("\n\n\n"+src);
        int e = 0;
        //System.out.print("entra while 1");
        while (e < tamaño) {

            src = src + "nodo" + e + "[label = \"|id "+aux.id+"\\ncantidad"+ aux.cantidad +"\\nprecio"+ aux.precio +"  |\" ]\n";
            tip3 = tip3 + "nodo" + e + ";";
            e++;
            aux = aux.ultimo;
        }
        tip2 = "{rank = same;" + tip3 + "};";
        //System.out.print("\n\n\n"+src);

        String tip4 = "";
        int f = 0;
        int r = f;
        int r2 = r + 1;
        int e4 = e - 1;
        src = src + "\n" + tip2 + "\n"; 
        src = src + "nodo" + r + " -> " + "nodo" + r2 + ";\n";
        int f1 = 1;
        //System.out.print("\n\n\n"+src);

        //System.out.print("entra while 2");
        while (f1 < tamaño - 1) {
            int g = f1;
            int g1 = g - 1;
            int g2 = g + 1;
            tip4 = tip4 + "nodo" + g + " -> " + "nodo" + g2 + ";\n";
            f1++;

            aux2 = aux2.ultimo;
        }
        int t = e4 - 1;

        src = src + tip4 +  ";\n" + "nodo" + e4 + " -> " + "null;\n";
        //System.out.print("\n\n\n"+src);
        return src;
    }
    
    public void imacoladet() {
        String gph = graphLista();
        System.out.println(gph);
        String aux = gph;
        Iniciar_grafo();
        gv.addln(aux);

        gv.addln("}");//agrega la llave de cierre al codigo
        System.out.println(gv.getDotSource()); // imprimir el codigo que ejecuta el Dot

        String type = "jpg";    //tipo de archivo de salida
        File out = new File("C:\\Users\\"+var.ruta1+"\\Desktop\\colaDetalle" + iteracion + "." + type);   // nombrearchivo.gif
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
        String url="C:\\Users\\"+var.ruta1+"\\Desktop\\colaDetalle" + iteracion + "." + type;

        iteracion++;
        //return url;
    }

    private void Iniciar_grafo() {
        gv = new graphviz();
        gv.addln("digraph Mat {");
        gv.addln("node [shape = record];");
    }
    
    public Nodo_Detalle obtenernodo(int idd){
        Nodo_Detalle aux=cabeza;
        Nodo_Detalle enc = aux;
        while (aux != null){
            if(idd == aux.id){
                enc = aux;
            }
            aux= aux.ultimo;
        }
        
        return enc;
    }
    
    
    
    
    
    
    
    
    
    
}//termina clase
