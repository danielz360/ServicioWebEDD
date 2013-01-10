package edd.Estructuras;
import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import edd.Variables.var;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evelyn
 */
public class Cola_Carrito {
    
    private static Logger log = Logger.getLogger(Cola_Carrito.class); // se intancia un logger de la clase donde esta el metodo.
    Nodo_ColaCarrito cabeza;
    Nodo_ColaCarrito ultimoo;
    NodoProducto k;
    int tamaño;
    graphviz gv;
    int iteracion=0;
    int detalle;
    String nodo1 = "";
    
    public Cola_Carrito (){
        cabeza = null;
        ultimoo=null;
        tamaño = 0;
    }// termina constructor
    
    
    public void productoHash(int detalle1){
    	
    	String df = k.ObtNombre();
    	
    }
    
    
    
    public void idendetalle(int detalle1){
        System.out.println("se agrego detalle "+detalle);
        detalle = detalle1;
    }
   
//------------------------Metodo Vacio -----------------------------------
    public boolean Vacio (){        
        return (cabeza == null && ultimoo ==null);        
    }
    
//---------------------------Metodo Ultimo Ingresado ---------------------------------
 public Nodo_ColaCarrito ultimos(){
     Nodo_ColaCarrito aux = cabeza;
     Nodo_ColaCarrito ant = aux;
     while ( ant != null){
         aux = ant;
         ant = ant.ultimo;
     }
     System.out.println(aux.cantidad);
     return aux;
 }
 
 //--------------------Metodo de existencia -------------------------
 
    public boolean existe(int cant /** tablaHash product*/) {
        System.out.println("entro");
        boolean exist = false;
        Nodo_ColaCarrito aux = cabeza;
        while (aux != null) {
            if (aux.cantidad == (cant)) {
                //if(aux.apProducto.equals(product)){
                exist = true;
            }
            aux = aux.ultimo;
        }
        

        return exist;
    }
 
 
//---------------Metodo Ingresar -------------------------------------------------     
  public void ingresar (int cant , NodoProducto pProducto)   {
      System.out.println("entro en ingresar cola Carrito");
      Nodo_ColaCarrito nuevo = new Nodo_ColaCarrito( cant ,pProducto);
         if (Vacio()){
             cabeza = nuevo;
             ultimoo = cabeza;
             tamaño++;
              System.out.println("Ingreso Nuevo a lista de carrito");
              log.info("Ingreso Nuevo a lista de carrito");
         } else {
             if (!existe(cant)){
            //     if (!existe(product.nombre)){
                Nodo_ColaCarrito aux = ultimos();
                aux.ultimo = nuevo;
                tamaño++;
                System.out.println("ingreso nuevo producto en ultima posicion");
                log.info ("ingreso nuevo producto en ultima posicion  en Carrito");
            }else{
                System.out.println("El producto "+cant+" ya esta registrado");
                log.warn("El producto "+cant+" ya esta registrado");
                /*
                 * System.out.println("El producto "+producto.nombre+" ya esta registrado");
                log.warn("El producto "+product.nombre+" ya esta registrado");
                 */
            }
         }     
  }// termina metodo ingresar cola 
     
 //--------------------Metodo de imprimir en consola -------------------------     
      
    public void imprimir(){
        System.out.println(tamaño);
        Nodo_ColaCarrito aux = cabeza;
        Nodo_ColaCarrito ant = aux;
        while (aux != null){
            System.out.println("Producto " + aux.cantidad+  /*"aux.produc.name"*/ "");
            aux= aux.ultimo;
        }
        //Nodo_ColaCarrito esta = ultimos();
        //System.out.println("Producto: "+ esta.cantidad+ /*esta.product.nombre*/ "" );     
    }// termina metodo imprimir 
       
   //--------------------Metodo para obtener -------------------------
   public String obtener(){
      
       String cadenaNom = "";
       Nodo_ColaCarrito aux  = cabeza;
       Nodo_ColaCarrito ant = aux;
       while (aux.ultimo != null){
           cadenaNom+=aux.cantidad + ";" ;
           //cadenaNom+=aux.producto.nombre +";" ; 
            aux = aux.ultimo;
       }
       
        Nodo_ColaCarrito esta = ultimos();
         cadenaNom+=aux.cantidad + ";" ;
           //cadenaNom+=aux.producto.nombre +";" ;       
       return cadenaNom;
   }// termina metodo de obtener el producto   
    
   //--------------------Metodo para sacar -------------------------
   public String sacar(){ //sale el primero y el segundo queda como cabeza
       String sale = "";
       Nodo_ColaCarrito aux  = cabeza;
       sale = String.valueOf(cabeza.cantidad);
       cabeza = aux.ultimo;
       return sale;
   }// termina metodo para sacar  
    
     //--------------------Metodos de graficar -------------------------
    
    public String graphLista() {
        System.out.println("Tamaño de la lista: " + tamaño + "\n");
        Nodo_ColaCarrito aux = cabeza;
        Nodo_ColaCarrito aux2 = cabeza;
        String src = "edge[color = blue]\n";
        String tip2 = "";
        String tip3 = "";
        //System.out.print("\n\n\n"+src);
        int e = 0;
        //System.out.print("entra while 1");
        while (e < tamaño) {

            src = src + "nodo" + e + "[label = \"|cantidad "+aux.cantidad+"\\nproducto"/**+ aux.producto */+"  |\" ]\n";
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
    public String subgraphLista() {
        System.out.println("Tamaño de la lista: " + tamaño + "\n");
        Nodo_ColaCarrito aux = cabeza;
        Nodo_ColaCarrito aux2 = cabeza;
        String src = "subgraph A"+var.NoNodo+" {\nnode [fontsize=5,shape = record];\n";
        String tip2 = "";
        String tip3 = "";
        //System.out.print("\n\n\n"+src);
        int e = 0;
        //System.out.print("entra while 1");
        nodo1 = "nodo" +var.NoNodo+ e ;
        while (e < tamaño) {

            src = src + "nodo" +var.NoNodo+ e + "[label = \"cantidad "+aux.cantidad+"\\nproducto"/**+ aux.producto */+"  \" ]\n";
            tip3 = tip3 + "nodo" +var.NoNodo+ e + ";";
            e++;
            aux = aux.ultimo;
        }
        tip2 = "{" + tip3 + "};";
        //System.out.print("\n\n\n"+src);

        String tip4 = "";
        int f = 0;
        int r = f;
        int r2 = r + 1;
        int e4 = e - 1;
        src = src + "\n" + tip2 + "\n"; 
        src = src + "nodo" +var.NoNodo+ r + " -> " + "nodo" +var.NoNodo+ r2 + ";\n";
        int f1 = 1;
        //System.out.print("\n\n\n"+src);

        //System.out.print("entra while 2");
        while (f1 < tamaño - 1) {
            int g = f1;
            int g1 = g - 1;
            int g2 = g + 1;
            tip4 = tip4 + "nodo" +var.NoNodo +g + " -> " + "nodo" +var.NoNodo+ g2 + ";\n";
            f1++;

            aux2 = aux2.ultimo;
        }
        int t = e4 - 1;

        src = src + tip4 +  "\n" + "nodo" +var.NoNodo+ e4 +";\n";
        src = src +"\n}\n\n";
        var.NoNodo++;
        //System.out.print("\n\n\n"+src);
        return src;
    }
    public String nodo1(){
        return nodo1;
    }
    
    public void imacola() {
        String gph = graphLista();
        System.out.println(gph);
        String aux = gph;
        Iniciar_grafo();
        gv.addln(aux);

        gv.addln("}");//agrega la llave de cierre al codigo
        System.out.println(gv.getDotSource()); // imprimir el codigo que ejecuta el Dot

        String type = "jpg";    //tipo de archivo de salida
        File out = new File("C:\\Users\\"+var.ruta1+"\\Desktop\\colacarr" + iteracion + "." + type);   // nombrearchivo.gif
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
        String url="C:\\Users\\"+var.ruta1+"\\Desktop\\colacarr" + iteracion + "." + type;

        iteracion++;
        //return url;
    }

    private void Iniciar_grafo() {
        gv = new graphviz();
        gv.addln("digraph Mat {");
        gv.addln("node [shape = record];");
    }
}
