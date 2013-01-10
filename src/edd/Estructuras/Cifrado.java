package edd.Estructuras;

import edd.Variables.Globales;

public class Cifrado 
{
	public Globales GB ;
	private String [][] TablaCodigos;
	private int [][] MatrizqOrtogonal;
	private int [][] MatrizqOrtogonalInversa;
	private int FilasC = 40;
	private int ColumnasC = 2;
	
	private int DivisorDato = 2; 
	
	public Cifrado() 
	{
		// TODO Auto-generated constructor stub
		//GB = Globales.getInstance();
		TablaCodigos = new String [FilasC][ColumnasC];
		MatrizqOrtogonal = new int [2][2];
		MatrizqOrtogonalInversa = new int [2][2];
		
		MatrizqOrtogonal[0][0] = 2;
		MatrizqOrtogonal[0][1] = 1;
		MatrizqOrtogonal[1][0] = 1;
		MatrizqOrtogonal[1][1] = 1;

		MatrizqOrtogonalInversa[0][0] = 1;
		MatrizqOrtogonalInversa[0][1] = -1;
		MatrizqOrtogonalInversa[1][0] = -1;
		MatrizqOrtogonalInversa[1][1] = 2;
		this.LlenarCodigos();
	}

	public String Encriptar(String pDato)
	{
		String Dato = pDato;
		String Resultado = "";		
		int divisor = DivisorDato;
		boolean AgregarZ = false;
		int contador = 0;
		
		while ( true )
		{
			if((contador + divisor) > (Dato.length()))
			{
				divisor = Dato.length() - contador;
				AgregarZ = true;
			}
			
			if((contador + divisor) <= (Dato.length() ))
			{
				Resultado += Dato.substring(contador, contador + divisor);
				
				if((divisor + contador) == (Dato.length()))
				{
					break;
				}
				
				contador += divisor;
			}
		}		
		
		Resultado = Resultado.substring(0,Resultado.length()-1);
		if(AgregarZ)
		{
			Resultado += "Z";
		}
		
		String [] Parejas = Resultado.split("-");
		
		String Retornar = "";
		
		for(int a = 0 ; a<Parejas.length;a++)
		{
			int CodCa1 = ObtCodigo(Parejas[a].charAt(0));
			int CodCa2 = ObtCodigo(Parejas[a].charAt(1));
			
			Retornar += "" + ( (CodCa1*MatrizqOrtogonal[0][0]) + (CodCa2*MatrizqOrtogonal[0][1])) + " ";
			Retornar += "" + ( (CodCa1*MatrizqOrtogonal[1][0]) + (CodCa2*MatrizqOrtogonal[1][1])) + " ";
		}
		
		Retornar = Retornar.trim();
		return "";
	}
	
	private int ObtCodigo(char pCaracter)
	{
		for(int a = 0; a < FilasC;a++)
		{
			if(TablaCodigos[a][1].charAt(0) == pCaracter)
			{
				return Integer.parseInt(TablaCodigos[a][0]);
			}
		}
		return -1;
	}
	
	private void LlenarCodigos()
	{
		TablaCodigos[0][0] = "41";
		TablaCodigos[0][1] = "?";
		
		TablaCodigos[1][0] = "40";
		TablaCodigos[1][1] = "?";
		
		TablaCodigos[2][0] = "39";
		TablaCodigos[2][1] = ";";
		
		TablaCodigos[3][0] = "38";
		TablaCodigos[3][1] = ",";
		
		TablaCodigos[4][0] = "37";
		TablaCodigos[4][1] = ".";
		
		TablaCodigos[5][0] = "36";
		TablaCodigos[5][1] = "1";

		TablaCodigos[6][0] = "35";
		TablaCodigos[6][1] = "2";
		
		TablaCodigos[7][0] = "34";
		TablaCodigos[7][1] = "3";
		
		TablaCodigos[8][0] = "33";
		TablaCodigos[8][1] = "4";
		
		TablaCodigos[9][0] = "32";
		TablaCodigos[9][1] = "5";
		
		TablaCodigos[10][0] = "31";
		TablaCodigos[10][1] = "6";
		
		TablaCodigos[11][0] = "30";
		TablaCodigos[11][1] = "7";
		
		TablaCodigos[12][0] = "29";
		TablaCodigos[12][1] = "8";
		
		TablaCodigos[13][0] = "28";
		TablaCodigos[13][1] = "9";
		
		TablaCodigos[14][0] = "27";
		TablaCodigos[14][1] = "A";
		
		TablaCodigos[15][0] = "26";
		TablaCodigos[15][1] = "B";
		
		TablaCodigos[16][0] = "25";
		TablaCodigos[16][1] = "C";
		
		TablaCodigos[17][0] = "24";
		TablaCodigos[17][1] = "D";
		
		TablaCodigos[18][0] = "23";
		TablaCodigos[18][1] = "E";
		
		TablaCodigos[19][0] = "22";
		TablaCodigos[19][1] = "F";
		
		TablaCodigos[20][0] = "21";
		TablaCodigos[20][1] = "G";
		
		TablaCodigos[21][0] = "20";
		TablaCodigos[21][1] = "H";
		
		TablaCodigos[22][0] = "19";
		TablaCodigos[22][1] = "I";
		
		TablaCodigos[23][0] = "18";
		TablaCodigos[23][1] = "J";
		
		TablaCodigos[24][0] = "17";
		TablaCodigos[24][1] = "K";
		
		TablaCodigos[25][0] = "16";
		TablaCodigos[25][1] = "L";
		
		TablaCodigos[26][0] = "15";
		TablaCodigos[26][1] = "M";
		
		TablaCodigos[27][0] = "14";
		TablaCodigos[27][1] = "N";
		
		TablaCodigos[28][0] = "13";
		TablaCodigos[28][1] = "Ñ";
		
		TablaCodigos[27][0] = "12";
		TablaCodigos[27][1] = "0";
		
		TablaCodigos[28][0] = "11";
		TablaCodigos[28][1] = "P";
		
		TablaCodigos[29][0] = "10";
		TablaCodigos[29][1] = "Q";
		
		TablaCodigos[30][0] = "9";
		TablaCodigos[30][1] = "R";
		
		TablaCodigos[31][0] = "8";
		TablaCodigos[31][1] = "S";
		
		TablaCodigos[32][0] = "7";
		TablaCodigos[32][1] = "T";
		
		TablaCodigos[33][0] = "6";
		TablaCodigos[33][1] = "U";
		
		TablaCodigos[34][0] = "5";
		TablaCodigos[34][1] = "V";
		
		TablaCodigos[35][0] = "4";
		TablaCodigos[35][1] = "W";
		
		TablaCodigos[36][0] = "3";
		TablaCodigos[36][1] = "X";
		
		TablaCodigos[37][0] = "2";
		TablaCodigos[37][1] = "Y";
		
		TablaCodigos[38][0] = "1";
		TablaCodigos[38][1] = "Z";
		
		TablaCodigos[39][0] = "42";
		TablaCodigos[39][1] = " ";
	}
}
