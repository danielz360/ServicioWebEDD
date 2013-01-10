package edd.Rabol;
import edd.Estructuras.Nodo_Detalle;
import edd.Estructuras.NodoUsuario;
public class CreacionArbolB {

    private Nodo mRaiz = null;
    private int mK = 2;
    private int mAltura = 0;

    public String toDot() {
        System.out.println("entro a graficar arbol");
        StringBuilder b = new StringBuilder();
        b.append("digraph g { \n node [shape=record];\n");
        b.append(mRaiz.toDot());
        
        b.append("}");
        //System.out.println("Codigo Final");
        //System.out.println(b.toString());
        //System.out.println("*******************************");
        return b.toString();

    }

    public CreacionArbolB() 
    {
    }

    public CreacionArbolB(int k) {

        this.mK = k;

    }

    public CreacionArbolB(Nodo pRaiz) {

        mK = pRaiz.getK();
        this.mRaiz = pRaiz;
        mAltura = 1;

    }

    public void insert(busqueda key, Object obj,NodoUsuario user,Nodo_Detalle detalle) {

        if (this.mAltura == 0) {
            //System.out.println("entro 1");
            this.mRaiz = new Nodo(this.mK, key, obj,user,detalle);
            this.mAltura = 1;
            return;
        }
        identificar splitted = insert(this.mRaiz, key, obj,user,detalle, 1);
        if (splitted == null) {
        } else {
            Nodo ptr = this.mRaiz;
            this.mRaiz =
                    new Nodo(this.mK, splitted.getLlave(), splitted.getDato(),splitted.getuser(),splitted.getdetalle());
            this.mRaiz.mPunteros[0] = ptr;
            this.mRaiz.mPunteros[1] = splitted.getPuntero();
            this.mAltura++;
        }

    }

    protected identificar insert(Nodo node, busqueda key, Object obj,NodoUsuario user,Nodo_Detalle detalle, int level) {
        identificar splitted = null;
        Nodo ptr = null;
        int i = 0;
        while ((i < node.mB) && (key.mayorQue(node.mLlaves[i]))) {
            i++;
        }
        if ((i < node.mB) && key.igualA(node.mLlaves[i])) {
            node.mDatos[i] = obj;
            node.aptUser[i]=user;
            node.aptDetalle[i]=detalle;
            return null;
        }
        if (level < this.mAltura) {
            splitted = insert(node.mPunteros[i], key, obj,user,detalle, level + 1);
            if (splitted == null) {
                return null;
            } else {
                key = splitted.mLlave;
                obj = splitted.mDato;
                user = (NodoUsuario) splitted.puser;
                detalle = splitted.pdetalle;
                ptr = splitted.mPuntero;
            }
        }
        i = node.mB - 1;
        while ((i >= 0)
                && (node.mLlaves[i] == null || key.menorQue(node.mLlaves[i]))) {
            node.mLlaves[i + 1] = node.mLlaves[i];
            node.mDatos[i + 1] = node.mDatos[i];
            node.aptUser[i + 1] = node.aptUser[i];
            node.aptDetalle[i + 1] = node.aptDetalle[i];
            node.mPunteros[i + 2] = node.mPunteros[i + 1];
            i--;
        }
        node.mLlaves[i + 1] = key;
        node.mDatos[i + 1] = obj;
        node.aptUser[i + 1] = user;
        node.aptDetalle[i + 1] = detalle;
        if (splitted != null) {
            node.mPunteros[i + 2] = splitted.mPuntero;
        }
        node.mB++;
        if (node.mB > 2 * mK) {
            Nodo newnode = new Nodo(this.mK);
            newnode.mPunteros[this.mK] = node.mPunteros[node.mB];
            node.mPunteros[node.mB] = null;
            node.mB = this.mK + 1;
            for (i = 0; i < this.mK; i++) {
                newnode.mLlaves[i] = node.mLlaves[i + node.mB];
                node.mLlaves[i + node.mB] = null;
                newnode.mDatos[i] = node.mDatos[i + node.mB];
                node.mDatos[i + node.mB] = null;
                newnode.aptUser[i] = node.aptUser[i + node.mB];
                node.aptUser[i + node.mB] = null;
                newnode.aptDetalle[i] = node.aptDetalle[i + node.mB];
                node.aptDetalle[i + node.mB] = null;
                newnode.mPunteros[i] = node.mPunteros[i + node.mB];
                node.mPunteros[i + node.mB] = null;
            }
            node.mB--;
            splitted =
                    new identificar(newnode, node.mLlaves[node.mB], node.mDatos[node.mB],node.aptUser[node.mB],node.aptDetalle[node.mB]);
            node.mLlaves[node.mB] = null;
            node.mDatos[node.mB] = null;
            node.aptUser[node.mB] = null;
            node.aptDetalle[node.mB] = null;
            newnode.mB = this.mK;
            node.mB = this.mK;
            return splitted;
        }
        return null;
    }

    public Object search(busqueda key) {

        return search(key, mRaiz);

    }

    public Object search(busqueda key, Nodo node) {

        if ((node == null) || (node.mB < 1)) {
            System.err.println("error");
            return null;
        }
        if (key.menorQue(node.mLlaves[0])) {
            return search(key, node.mPunteros[0]);
        }
        if (key.mayorQue(node.mLlaves[node.mB - 1])) {
            return search(key, node.mPunteros[node.mB]);
        }
        int i = 0;
        while ((i < node.mB - 1) && (key.mayorQue(node.mLlaves[i]))) {
            i++;
        }
        if (key.igualA(node.mLlaves[i])) {
            return node.mDatos[i];
            //return node.No;
        }
        return search(key, node.mPunteros[i]);

    }
    
    //**********************Mtodo Search especial para Detalle*********************
    
    public Nodo_Detalle searchDet(busqueda key, Nodo node) {

        if ((node == null) || (node.mB < 1)) {
            System.err.println("error");
            return null;
        }
        if (key.menorQue(node.mLlaves[0])) {
            return searchDet(key, node.mPunteros[0]);
        }
        if (key.mayorQue(node.mLlaves[node.mB - 1])) {
            return searchDet(key, node.mPunteros[node.mB]);
        }
        int i = 0;
        while ((i < node.mB - 1) && (key.mayorQue(node.mLlaves[i]))) {
            i++;
        }
        if (key.igualA(node.mLlaves[i])) {
            return (Nodo_Detalle) node.mDatos[i];
            //return node.No;
        }
        return searchDet(key, node.mPunteros[i]);

    }
    
    // termina el metodo Search especial para Detalle********************************
    
    
    
    
    
    

     public Object searchuser(busqueda key) {

        return searchuser(key, mRaiz);

    }

    public Object searchuser(busqueda key, Nodo node) {

        if ((node == null) || (node.mB < 1)) {
            System.err.println("error");
            return null;
        }
        if (key.menorQue(node.mLlaves[0])) {
            return search(key, node.mPunteros[0]);
        }
        if (key.mayorQue(node.mLlaves[node.mB - 1])) {
            return search(key, node.mPunteros[node.mB]);
        }
        int i = 0;
        while ((i < node.mB - 1) && (key.mayorQue(node.mLlaves[i]))) {
            i++;
        }
        if (key.igualA(node.mLlaves[i])) {
            return "usuario: "+node.aptUser[i];
            //return node.No;
        }
        return search(key, node.mPunteros[i]);

    }
    
    public Nodo_Detalle searchdetalle(busqueda key) {

        return searchdetalle(key, mRaiz);

    }

    public Nodo_Detalle searchdetalle(busqueda key, Nodo node) {

        if ((node == null) || (node.mB < 1)) {
            System.err.println("error");
            return null;
        }
        if (key.menorQue(node.mLlaves[0])) {
            return searchDet(key, node.mPunteros[0]);
        }
        if (key.mayorQue(node.mLlaves[node.mB - 1])) {
            return searchDet(key, node.mPunteros[node.mB]);
        }
        int i = 0;
        while ((i < node.mB - 1) && (key.mayorQue(node.mLlaves[i]))) {
            i++;
        }
        if (key.igualA(node.mLlaves[i])) {
           // return "detalle: "+ node.aptDetalle[i];
           // Nodo_Detalle prueba;
           // prueba= node.aptDetalle[i];
          //  return prueba;
            
            return node.aptDetalle[i];
          
            //return node.No;
        }
        return searchDet(key, node.mPunteros[i]);

    }
    
    public int getAltura() {

        return mAltura;

    }
    
}