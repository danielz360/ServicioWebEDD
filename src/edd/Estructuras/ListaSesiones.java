package edd.Estructuras;

public class ListaSesiones 
{
	private NodoSesion R = null;
	private String Id;
	
	public ListaSesiones(String p_Id)
	{
		// TODO Auto-generated constructor stub
		Id = p_Id;
	}	
	
	void Push(NodoSesion p_Inicio)
	{
		NodoSesion A = p_Inicio;
		
		if(R == null)
		{
			A.setInicioSig(null);
            R = A;
        }
		else
		{
			NodoSesion t;
        	t = R;
            //Llega hasta el final de la Cola
        	while(t.ObtInicioSig() != null)
            {
            	t = t.ObtInicioSig();
            }
            
            A.setInicioSig(null);
            t.setInicioSig(A);
		}

	}	
	
	void Pop()
	{
		NodoSesion t = R;
        if(t != null)
        {
        	//System.out.println("Sale: Número: " + t.No + ", Nombre: " + t.Nombre);
        	R = t.ObtInicioSig();
        	t.setInicioSig(null);
        }
        else
        {
        	System.out.println("Cola Vacia");
        }
	 }

	 void Recorrer()
	 {
		 NodoSesion t = R;
		 
         while(t != null)
         {
        	 //System.out.println("Número: " + t.No + ", Nombre: " + t.Nombre);
             t = t.ObtInicioSig();
         }
	 }

	 public void Eliminar(int a)
	 {
		 int cont = 0;
		 NodoSesion t = R;
		 
         while(t != null)
         {
        	 if((a==0))
        	 {
        		 R = R.ObtInicioSig();
        		 return;
        	 }
         	if((a>0) && cont == (a-1))
         	{
         		NodoSesion n = t.ObtInicioSig();
         		n = n.ObtInicioSig();
                t.setInicioSig(n);
         		return;
         	}
         	else
         	{
            	t = t.ObtInicioSig();   
            	cont++;
         	}
         }
	 }
	 
	 public NodoSesion Obtener(int a)
	 {
		 int cont = 0;
		 NodoSesion t = R;
		 
         while(t != null)
         {
         	if(cont == a)
         	{
         		return t; 
         	}
         	else
         	{
            	t = t.ObtInicioSig();   
            	cont++;
         	}
         }
         return null;
	 }
	 
	 public NodoSesion Obtener(String NombreUsuario)
	 {
		 NodoSesion t = R;
		 
         while(t != null)
         {
         	if(t.ObtUsuario().ObtNickName().equals(NombreUsuario))
         	{
         		return t; 
         	}
         	else
         	{
            	t = t.ObtInicioSig();   
         	}
         }
         return null;
	 }
}
