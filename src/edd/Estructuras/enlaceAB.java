/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.Estructuras;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import edd.Rabol.CreacionArbolB;
import edd.Rabol.Llavedos;
import edd.Variables.Globales;
import edd.Variables.var;
/**
*
* @author Evelyn
*/

public class enlaceAB {
    private static Logger log = Logger.getLogger(enlaceAB.class); // se intancia un logger de la clase donde esta el metodo.
    CreacionArbolB tree3 ;
    Nodo_Detalle nuevo;
    edd.Rabol.graphviz grafo = new edd.Rabol.graphviz();
    String tempFolder2 = System.getProperty("C:\\Users\\"+var.ruta1+"\\Desktop\\");
    int it=0;
    Globales GB;
    
    private String direccionImagen;
    
    
    public enlaceAB(int d){
    	tree3= new CreacionArbolB(d);
    	GB = Globales.getInstance();
    }
    
    public CreacionArbolB ObtArbolB(){
    	return tree3;
    }
    
    
    public void insertar2(String no,String fecha1,String total1,NodoUsuario user,int detalle){
        //meter nombre usuario, id detalle 
        int codigoGraficar = Integer.valueOf(no);
        Object user1=user;
        ListadeColasDetalle lista = GB.ListadeDetalles();
        Cola_Detalle detalle1 = lista.obtener(detalle).detalle;        
        Nodo_ColaCarrito nuevo = new Nodo_ColaCarrito(2,null);
        tree3.insert(new Llavedos(codigoGraficar), no+","+fecha1+","+total1,user,detalle1);
        it++;
        log.info("Ingreso Venta en Arbol B" +no+ "" +user1+ "" +detalle1.NoDetalle+"");
    }
    
    public void insertar(String no,String fecha1,String total1,NodoUsuario user){
        //meter nombre usuario, id detalle 
        int codigoGraficar = Integer.valueOf(no);
        Object user1=user;
        ListadeColasDetalle lista = GB.ListadeDetalles();
        Cola_Detalle detalle1 = new Cola_Detalle();
        tree3.insert(new Llavedos(codigoGraficar), no+","+fecha1+","+total1,user,detalle1);
        it++;
        log.info("Ingreso Venta en Arbol B" +no+ "" +user1+ "");
    }
    
    public void insertardetallealArbol(String NoFactura,int cantidad,int precio,int producto)
    {
        int codigo = Integer.valueOf(NoFactura);
        NodoProducto nprod = GB.getProductos().ObtBuscar(producto);
    	tree3.setdetalle(new Llavedos(codigo), codigo,cantidad, precio, nprod);
    }
    
    static void doDot(String pInput, String pOutput) {

        try {
            String dotPath = "C:\\"+var.ruta2+"\\Graphviz 2.28\bin\\dot.exe";
            String fileInputPath = pInput;
            String fileOutputPath = pOutput;
            String tParam = "-Tjpg";
            String tOParam = "-o";
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (Exception ex) {
        } finally {
        }
        try {
            String[] cmd = new String[4];
            cmd[0] = "cmd";
            cmd[1] = "/C";
            cmd[2] = "start";
            cmd[3] = pOutput;
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (Exception e) {
        }

    }
    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }
    
    public void graficarArbol() {
      

            try {
//                FileWriter f = new FileWriter(tempFolder2 + "grafo1.txt");

                String cadena = tree3.toDot();
                //System.out.print(cadena);

                try {
                    grafo = new edd.Rabol.graphviz();
                    grafo.add(cadena);
                    String type = "gif";
                    File out = new File("C:\\Users\\"+var.ruta1+"\\Desktop\\grafo." + type);
                    System.out.println("Codigo Final");
                    System.out.println(grafo.getDotSource());
                    grafo.writeGraphToFile(grafo.getGraph(grafo.getDotSource(), type), out);
                    System.out.println("*******************************");
                    this.setDireccionImagen("/imagen/grafo.gif");
                } catch (Exception ex) {
                }

//                f.write(arbol.toDot());
//                f.close();

            } catch (Exception e) {
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
//            doDot(tempFolder2 + "grafo1.txt", tempFolder2 + "grafo1.jpg");

        

    }
    
    public String buscarinfo (String No){
        int codigo = Integer.valueOf(No);
        String datos = String.valueOf(tree3.search(new Llavedos(codigo)));
        
        System.out.println(tree3.search(new Llavedos(codigo)));
        return datos;
        
    }
    public String buscaruser (String No){
        int codigo = Integer.valueOf(No);
        String user =tree3.searchuser(new Llavedos(codigo));
        System.out.println(tree3.searchuser(new Llavedos(codigo)));
        return user;
        
    }
    public int buscardetalle (String No){
        int codigo = Integer.valueOf(No);
       // System.out.println(tree3.searchdetalle(new Llavedos(codigo)));
        int datos =  tree3.searchNodetalle(new Llavedos(codigo)) ;
        System.out.println("El Id del Detalle es:" +datos);
        return datos;
        
        
        /*nodo_detalle nuevo;
nuevo = tree3.searchdetalle(new Llavedos(codigo));
luego si lo qures rebiri en un string 
String datos = nuevo.info;*/
    }
    
    public Cola_Detalle buscarColaDetalle(String No){
    	int codigo = Integer.valueOf(No);
    	return  tree3.searchdetalle(new Llavedos(codigo)) ;
    }

   /* void insertar(String dat1, String dat2, String dat3, String dat4, String dat5) {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
}
