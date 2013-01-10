package edd.Rabol;


/**
 *
 * @author Evelyn Oliva
 */
public class Llavedos extends busqueda {

    private Integer mLlave = null;

    public Llavedos(int pValor) {

        mLlave = new Integer(pValor);

    }

    public Llavedos(Integer pValor) {

        mLlave = pValor;

    }

    @Override
    public Object getKey() {

        return mLlave;

    }

    @Override
    public boolean igualA(busqueda pObjeto) {

        return mLlave.equals(pObjeto.getKey());

    }

    

    @Override
    public boolean mayorQue(busqueda pObjeto) {

        return mLlave.compareTo((Integer) pObjeto.getKey()) > 0;

    }

    @Override
    public boolean menorOIgualQue(busqueda pObjeto) {

        return mLlave.compareTo((Integer) pObjeto.getKey()) <= 0;

    }

    
    @Override
    public boolean menorQue(busqueda pObjeto) {

        return mLlave.compareTo((Integer) pObjeto.getKey()) < 0;

    }
    
    
    
    
    
    @Override
    public boolean mayorOIgualQue(busqueda pObjeto) {

        return mLlave.compareTo((Integer) pObjeto.getKey()) >= 0;

    }

    @Override
    public busqueda minKey() {

        return new Llavedos(Integer.MIN_VALUE);

    }
}
