package edd.Rabol;


/**
 *
 * @author Evelyn Oliva
 */
public class Llaveuno extends busqueda {

    public Llaveuno(String pValor) {

        mLlave = pValor;

    }
    private String mLlave = null;

    @Override
    public Object getKey() {

        return mLlave;

    }

    @Override
    public boolean igualA(busqueda pObjeto) {

        return mLlave.equals(pObjeto.getKey());

    }

    @Override
    public boolean menorQue(busqueda pObjeto) {

        return mLlave.compareTo((String) pObjeto.getKey()) < 0;

    }

    @Override
    public boolean mayorQue(busqueda pObjeto) {

        return mLlave.compareTo((String) pObjeto.getKey()) > 0;

    }

    @Override
    public boolean menorOIgualQue(busqueda pObjeto) {

        return mLlave.compareTo((String) pObjeto.getKey()) <= 0;

    }

    @Override
    public boolean mayorOIgualQue(busqueda pObjeto) {

        return mLlave.compareTo((String) pObjeto.getKey()) >= 0;

    }

    @Override
    public busqueda minKey() {

        return new Llaveuno("");

    }
}
