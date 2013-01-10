package edd.Estructuras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.log4j.Logger;

import edd.Rabol.*;
import edd.Variables.Globales;


public class ManipulacionDatos 
{   
    private static Logger log = Logger.getLogger(Globales.class); // se intancia un logger de la clase donde esta el metodo.
    private Globales GB;
    private Cifrado Cf;
    
    public ManipulacionDatos() 
	{
		// TODO Auto-generated constructor stub		
		GB = Globales.getInstance();
		Cf = new Cifrado();
	}

	public void Cargar()
	{
		String sFichero = GB.getDirectorioDatos() + "entrada.txt";				
    	BufferedReader Breader = null;
		
		File fichero = new File(sFichero);
		
		if(!fichero.exists())
		{
			return;
		}
		
		//Leer Archivos
		try 
		{
			Breader = new BufferedReader(new FileReader (sFichero));

			// Lectura del fichero
			String linea;
					
			ListaDireccion Direcciones;
			String Usuario = "";
			String Direccion = "";
			String PorComprar = "";
			String Carrito = "";
			String Producto = "";
			String Venta = "";
			String Detalle = "";
			
			int contador = 0;
			
			while((linea=Breader.readLine())!=null)
			{
				if(linea.length() == 0)
				{
					contador++;
					continue;
				}
				
				switch(contador)
				{
					case 0:
					{
						linea = linea.substring(8, linea.length()-2);
						Usuario += linea + "--";
						break;
					}
					case 1:
					{
						linea = linea.substring(10, linea.length()-2);
						Direccion += linea + "--";
						break;
					}
					case 2:
					{
						linea = linea.substring(9, linea.length()-2);
						Producto += linea + "--";
						break;
					}
					case 3:
					{
						linea = linea.substring(11, linea.length()-2);
						PorComprar += linea + "--";
						break;
					}
					case 4:
					{
						linea = linea.substring(8, linea.length()-2);
						Carrito += linea + "--";
						break;
					}
					case 5:
					{
						linea = linea.substring(6, linea.length()-2);
						Venta += linea + "--";
						break;
					}
					case 6:
					{
						linea = linea.substring(8, linea.length()-2);
						Detalle += linea + "--";
						break;
					}
				}								
			}
			
			Usuario = Usuario.substring(0, Usuario.length() - 2);
			Direccion = Direccion.substring(0, Direccion.length() - 2 );
			PorComprar = PorComprar.substring(0, PorComprar.length() - 2);
			Carrito = Carrito.substring(0, Carrito.length() - 2);
			Producto = Producto.substring(0, Producto.length() - 2);
			Venta = Venta.substring(0, Venta.length() - 2);
			Detalle = Detalle.substring(0, Detalle.length() - 2);
			
			String [] VUsuario = Usuario.split("--");
			String [] VDireccion = Direccion.split("--");
			String [] VPorComprar = PorComprar.split("--");
			String [] VCarrito = Carrito.split("--");
			String [] VProducto = Producto.split("--");
			String [] VVenta = Venta.split("--");
			String [] VDetalle = Detalle.split("--");
			
			//Inserccion de Usuarios
			for(int a = 0 ; a < VUsuario.length ; a++)
			{
				String [] Vector = VUsuario[a].split(",");
				GB.getUsuarios().InsertarFinal(Vector[0], Vector[1]);
			}
			//Inserccion de Direcciones
			for(int a = 0 ; a < VDireccion.length ; a++)
			{
				String [] Vector = VDireccion[a].split(",");
				boolean envio = Vector[2].toString().equals("1")?true:false;
				boolean facturacion = Vector[3].toString().equals("1")?true:false;
				
				GB.getUsuarios().insertardiriFin(Vector[0], Vector[1], envio, facturacion);
			}
			
			//Inserccion de Productos 
			for(int a = 0 ; a < VProducto.length ; a++)
			{
				String [] Vector = VProducto[a].split(",");
				int codigo = Integer.parseInt(Vector[0]);
				double precio = Double.parseDouble(Vector[3]);
				GB.getProductos().Insertar(codigo, Vector[1], Vector[2], precio, Vector[4]);
			}
			
			//Inserccion de Productos x Comprar
			for(int a = 0 ; a < VPorComprar.length ; a++)
			{
				String [] Vector = VPorComprar[a].split(",");
				NodoProducto nodo = GB.getProductos().ObtBuscar(Integer.parseInt(Vector[2]));				
				GB.getUsuarios().InsertarProductoxComprar(Vector[0], Integer.parseInt(Vector[1]),nodo);
			}
			
			//Inserccion de Productos al Carrito
			for(int a = 0 ; a < VCarrito.length ; a++)
			{
				String [] Vector = VCarrito[a].split(",");
				NodoProducto nodo = GB.getProductos().ObtBuscar(Integer.parseInt(Vector[2]));
				GB.getUsuarios().insertarprodacarrito(Vector[0], Integer.parseInt(Vector[1]),nodo);
			}
			/*
			//Insertar en Ventas [Facturas] 
			for(int a = 0 ; a < VVenta.length ; a++)
			{
				String [] Vector = VVenta[a].split(",");
				Cola_Detalle  Det = new  Cola_Detalle();
				NodoUsuario Usr = GB.Usuarios.ObtBuscar(Vector[3]);
				
				GB.Facturas.insert(Vector[0], Vector[1],Vector[2],Usr , Det);
				NoVenta, fecha, total, usuario, nododetalle
				NodoProducto nodo = GB.Productos.ObtBuscar(Integer.parseInt(Vector[2]));
				GB.Usuarios.insertarprodacarrito(Vector[1], Integer.parseInt(Vector[2]),nodo);
			}
			
			//Insertar en Detalles de [Facturas] 
			for(int a = 0 ; a < VDetalle.length ; a++)
			{
				String [] Vector = VDetalle[a].split(",");
				GB.Facturas.
				NodoProducto nodo = GB.Productos.ObtBuscar(Integer.parseInt(Vector[2]));
				GB.Usuarios.insertarprodacarrito(Vector[1], Integer.parseInt(Vector[2]),nodo);
			}*/
			
			Breader.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, "SERVIDOR->Error en CargarDatos" + e.getMessage());
			 log.error("SERVIDOR->Error en CargarDatos Mani" + e.getMessage());
		}
	}
	
	public void Encriptar()
	{
		
	}
}
