package edd.Estructuras;

import edd.Variables.Globales;

public class Cifrado 
{
	public Globales GB ;
	private String [][] TablaCodigos;
	private int [][] MatrizqOrtogonal;
	private int [][] MatrizqOrtogonalInversa;
	private int FilasC = 71;
	private int ColumnasC = 2;
	private String Grafico = "";
	
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
				Resultado += Dato.substring(contador, contador + divisor) + "_";
				
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
			Resultado += "\\";
		}
		
		String [] Parejas = Resultado.split("_");
		
		String Retornar = "";
		
		for(int a = 0 ; a<Parejas.length;a++)
		{
			int CodCa1 = ObtCodigo(Parejas[a].charAt(0));
			int CodCa2 = ObtCodigo(Parejas[a].charAt(1));
			
			Retornar += "" + ( (CodCa1*MatrizqOrtogonal[0][0]) + (CodCa2*MatrizqOrtogonal[0][1])) + " ";
			Retornar += "" + ( (CodCa1*MatrizqOrtogonal[1][0]) + (CodCa2*MatrizqOrtogonal[1][1])) + " ";
		}
		
		Retornar = Retornar.trim();
		return Retornar;
	}
	
	public String Desencriptar(String pDato)
	{
		String Dato = pDato;
		
		String [] Valores = Dato.split(" ");		
				
		String Retornar = "";
		
		for(int a = 0 ; a < Valores.length;a++)
		{
			int Cod1 = Integer.parseInt(Valores[a]);
			int Cod2 = Integer.parseInt(Valores[a + 1]);
			int CodRe1 = (Cod1 * this.MatrizqOrtogonalInversa[0][0]) + (Cod2 * this.MatrizqOrtogonalInversa[0][1]);
			int CodRe2 = (Cod1 * this.MatrizqOrtogonalInversa[1][0]) + (Cod2 * this.MatrizqOrtogonalInversa[1][1]);
			
			Retornar += this.ObtCaracter(CodRe1) + "" + this.ObtCaracter(CodRe2);
			a++;
		}
		
		Retornar = Retornar.trim();
		return Retornar;
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
		return 0;
	}
	
	private String ObtCaracter(int pCodigo)
	{
		for(int a = 0; a < FilasC;a++)
		{
			if(Integer.parseInt(TablaCodigos[a][0]) == pCodigo)
			{
				return TablaCodigos[a][1];
			}
		}
		return "";
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
		
		TablaCodigos[29][0] = "12";
		TablaCodigos[29][1] = "O";
		
		TablaCodigos[30][0] = "11";
		TablaCodigos[30][1] = "P";
		
		TablaCodigos[31][0] = "10";
		TablaCodigos[31][1] = "Q";
		
		TablaCodigos[32][0] = "9";
		TablaCodigos[32][1] = "R";
		
		TablaCodigos[33][0] = "8";
		TablaCodigos[33][1] = "S";
		
		TablaCodigos[34][0] = "7";
		TablaCodigos[34][1] = "T";
		
		TablaCodigos[35][0] = "6";
		TablaCodigos[35][1] = "U";
		
		TablaCodigos[36][0] = "5";
		TablaCodigos[36][1] = "V";
		
		TablaCodigos[37][0] = "4";
		TablaCodigos[37][1] = "W";
		
		TablaCodigos[38][0] = "3";
		TablaCodigos[38][1] = "X";
		
		TablaCodigos[39][0] = "2";
		TablaCodigos[39][1] = "Y";
		
		TablaCodigos[40][0] = "1";
		TablaCodigos[40][1] = "Z";
											
		TablaCodigos[41][0] = "42";
		TablaCodigos[41][1] = " ";
		
		TablaCodigos[42][0] = "43";
		TablaCodigos[42][1] = "\\";		
		
		TablaCodigos[43][0] = "-27";
		TablaCodigos[43][1] = "a";
		
		TablaCodigos[44][0] = "-26";
		TablaCodigos[44][1] = "b";
		
		TablaCodigos[45][0] = "-25";
		TablaCodigos[45][1] = "c";
		
		TablaCodigos[46][0] = "-24";
		TablaCodigos[46][1] = "d";
		
		TablaCodigos[47][0] = "-23";
		TablaCodigos[47][1] = "e";
		
		TablaCodigos[48][0] = "-22";
		TablaCodigos[48][1] = "f";
		
		TablaCodigos[49][0] = "-21";
		TablaCodigos[49][1] = "g";
		
		TablaCodigos[50][0] = "-20";
		TablaCodigos[50][1] = "h";
		
		TablaCodigos[51][0] = "-19";
		TablaCodigos[51][1] = "i";
		
		TablaCodigos[52][0] = "-18";
		TablaCodigos[52][1] = "j";
		
		TablaCodigos[53][0] = "-17";
		TablaCodigos[53][1] = "k";
		
		TablaCodigos[54][0] = "-16";
		TablaCodigos[54][1] = "l";
		
		TablaCodigos[55][0] = "-15";
		TablaCodigos[55][1] = "m";
		
		TablaCodigos[56][0] = "-14";
		TablaCodigos[56][1] = "n";
		
		TablaCodigos[57][0] = "-13";
		TablaCodigos[57][1] = "ñ";
		
		TablaCodigos[58][0] = "-12";
		TablaCodigos[58][1] = "o";
		
		TablaCodigos[59][0] = "-11";
		TablaCodigos[59][1] = "p";
		
		TablaCodigos[60][0] = "-10";
		TablaCodigos[60][1] = "q";
		
		TablaCodigos[61][0] = "-9";
		TablaCodigos[61][1] = "r";
		
		TablaCodigos[62][0] = "-8";
		TablaCodigos[62][1] = "s";
		
		TablaCodigos[63][0] = "-7";
		TablaCodigos[63][1] = "t";
		
		TablaCodigos[64][0] = "-6";
		TablaCodigos[64][1] = "u";
		
		TablaCodigos[65][0] = "-5";
		TablaCodigos[65][1] = "v";
		
		TablaCodigos[66][0] = "-4";
		TablaCodigos[66][1] = "w";
		
		TablaCodigos[67][0] = "-3";
		TablaCodigos[67][1] = "x";
		
		TablaCodigos[68][0] = "-2";
		TablaCodigos[68][1] = "y";
		
		TablaCodigos[69][0] = "-1";
		TablaCodigos[69][1] = "z";
		
		TablaCodigos[70][0] = "44";
		TablaCodigos[70][1] = "-";
	}
	
	/**public static void main(String[] args) 
	{
		Cifrado c = new Cifrado();
		String Original = "Quedamos en el Altozano a las----adsfasdfl ;lkjha;ldfadfakdsfhakdsfjhalkdsfh Nueve Exelente MIJO";
		System.out.println("or - " + Original);
		String Encriptado = c.Encriptar(Original);
		System.out.println("en - " + Encriptado);
		String DesEncriptado = c.Desencriptar(Encriptado);
		
		if(DesEncriptado.charAt(DesEncriptado.length()-1) == '\\')
		{
			DesEncriptado = DesEncriptado.substring(0, DesEncriptado.length()-1);
		}
		System.out.println("des - " + DesEncriptado);		
	}*/
	
}
