package edd.Rabol;
import edd.Estructuras.*;
class identificar {

    Nodo mPuntero;
    busqueda mLlave;
    Object mDato;
    NodoUsuario puser;
    Cola_Detalle pdetalle;

    public identificar(Nodo pPuntero, busqueda pLlave, Object pDato,NodoUsuario ppUser, Cola_Detalle ppDetalle) {//agarra los objetos los guarda, hac el equilibrio y los jala 

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
    public void setuser(NodoUsuario muser) {

        this.puser = muser;

    }

    public NodoUsuario getuser() {

        return puser;

    }
    public void setdetalle(Cola_Detalle mDetalle) {

        this.pdetalle = mDetalle;

    }

    public Cola_Detalle getdetalle() {

        return pdetalle;

    }
}