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
import edd.Variables.var;


public class enlaceAB {
    private static Logger log = Logger.getLogger(enlaceAB.class); // se intancia un logger de la clase donde esta el metodo.
    CreacionArbolB tree3 = new CreacionArbolB(2);
    Nodo_Detalle nuevo;
    edd.Rabol.graphviz grafo = new edd.Rabol.graphviz();
    String tempFolder2 = System.getProperty("C:\\Users\\"+var.ruta1+"\\Desktop\\");
    int it=0;
    private String direccionImagen;
    
    
    public void insertar(String no,String fecha1,String total1,NodoUsuario user,Nodo_Detalle detalle){
        //meter nombre usuario, id detalle 
        int codigoGraficar = Integer.valueOf(no);
        Object user1=user;
        Nodo_Detalle detalle1 = detalle;
        Nodo_ColaCarrito nuevo = new Nodo_ColaCarrito(2,null);
        tree3.insert(new Llavedos(codigoGraficar), "Dummy " +it+","+ no+","+fecha1+","+total1,user1,detalle1);
        it++;
        log.info("Ingreso Venta en Arbol B" +no+ "" +user1+ "" +detalle1.id+"");
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
    
    public void buscar (String No){
        int codigo = Integer.valueOf(No);
        System.out.println(tree3.search(new Llavedos(codigo)));
        
    }
    public void buscaruser (String No){
        int codigo = Integer.valueOf(No);
        System.out.println(tree3.searchuser(new Llavedos(codigo)));
        
    }
    public void buscardetalle (String No){
        int codigo = Integer.valueOf(No);
       // System.out.println(tree3.searchdetalle(new Llavedos(codigo)));
    nuevo =  tree3.searchdetalle(new Llavedos(codigo)) ;
        int datos = nuevo.id;
        System.out.println("El Id del Detalle es:" +datos);
        
        
        /*nodo_detalle nuevo;
nuevo = tree3.searchdetalle(new Llavedos(codigo));
luego si lo qures rebiri en un string 
String datos = nuevo.info;*/
    }

   /* void insertar(String dat1, String dat2, String dat3, String dat4, String dat5) {
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
}
