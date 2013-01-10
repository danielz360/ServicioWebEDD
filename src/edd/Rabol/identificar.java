package edd.Rabol;
import edd.Estructuras.Nodo_Detalle;
class identificar {

    Nodo mPuntero;
    busqueda mLlave;
    Object mDato;
    Object puser;
    Nodo_Detalle pdetalle;

    public identificar(Nodo pPuntero, busqueda pLlave, Object pDato,Object ppUser, Nodo_Detalle ppDetalle) {//agarra los objetos los guarda, hac el equilibrio y los jala 

        this.mPuntero = pPuntero;
        this.mLlave = pLlave;
        this.mDato = pDato;
        this.puser = ppUser;
        this.pdetalle = ppDetalle;

    }

    public void setPuntero(Nodo mPuntero) {

        this.mPuntero = mPuntero;

    }

    public Nodo getPuntero() {

        return mPuntero;

    }

    public void setLlave(busqueda mLlave) {

        this.mLlave = mLlave;

    }

    public busqueda getLlave() {

        return mLlave;

    }

    public void setDato(Object mDato) {

        this.mDato = mDato;

    }

    public Object getDato() {

        return mDato;

    }
    public void setuser(Object muser) {

        this.puser = muser;

    }

    public Object getuser() {

        return puser;

    }
    public void setdetalle(Nodo_Detalle mDetalle) {

        this.pdetalle = mDetalle;

    }

    public Nodo_Detalle getdetalle() {

        return pdetalle;

    }
}