package edd.Rabol;
import edd.Estructuras.Cola_Carrito;
import edd.Estructuras.*;
        
        
        
public class Nodo {

    int mK;
    int mB;
    busqueda[] mLlaves;
    Object[] mDatos;
    NodoUsuario[] aptUser;
    Cola_Detalle[] aptDetalle;
    Nodo[] mPunteros;
    
    private static int numeroDeNodo = 1;

    public String getDotName() {

        return "Nodo" + this.hashCode();

    }

    public String toDot() {
        StringBuilder bb = new StringBuilder();
        StringBuilder ptros = new StringBuilder();
        System.out.println("entroa a realizar cod");
        StringBuilder b = new StringBuilder();
        b.append(getDotName());
        b.append("[label=\"<P0>");
        System.out.println(b.toString());
        System.out.println("entroa 1");
        //bb.append(aptDetalle[0].codsubgrafo());
        //ptros.append(getDotName()).append(":P0").append(" -> ").append(aptDetalle[0].nodo1());
        for (int i = 0; i < mB; i++) {
            System.out.println("entro al for");
            
            b.append("|").append(mLlaves[i].getKey().toString());
            b.append("\\n ").append(mDatos[i]);
            b.append("|<P").append(i + 1).append(">");
            
            System.out.println("entro sub");
            bb.append(aptDetalle[i].subgraphLista());
            
            System.out.println("entro agrega");
            ptros.append("\n ").append(getDotName()).append(":P").append(i).append(" -> ").append(aptDetalle[i].nodo1()).append(";\n ");
            
            
            
            
        }
        System.out.println("salio del for");
        b.append("\"];\n");
        for (int i = 0; i <= mB; i++) {
            if (mPunteros[i] != null) {
                b.append(mPunteros[i].toDot());
                StringBuilder append = b.append(getDotName()).append(":P").append(i).append(" -> ").append(mPunteros[i].getDotName()).append(";\n");
            }
        }
        b.append(bb.toString());
        b.append(ptros.toString());
        return b.toString();

    }

    public Nodo(int pK) {

        this.mK = pK;
        mB = 0;
        mLlaves = new busqueda[2 * pK + 1];
        mDatos = new Object[2 * pK + 1];
        aptUser = new NodoUsuario[(2 * pK) + 1];
        aptDetalle = new Cola_Detalle[(2 * pK) + 1];
        mPunteros = new Nodo[(2 * pK) + 2];
        System.out.println((2 * pK) + 1);
        System.out.println((2 * pK) + 2);

    }

    public Nodo(int pK, busqueda pLlave, Object pDato,NodoUsuario user,Cola_Detalle detalle) {

        this(pK);
        mB = 1;
        mLlaves[0] = pLlave;
        System.out.println(pDato);
        mDatos[0] = pDato;
        //datos = info;
        //No[0] = no;
        //fecha[0] = fecha1;
        //total[0]=total1;
        System.out.println(user);
        System.out.println(aptUser.length);
        aptUser[0] = user;
        System.out.println("paso");
        aptDetalle[0] = detalle;

    }

    public void setK(int mK) {

        this.mK = mK;

    }

    public int getK() {

        return mK;

    }
}
