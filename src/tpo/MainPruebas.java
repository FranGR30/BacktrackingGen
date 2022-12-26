package tpo;

import java.util.ArrayList;
import java.util.Scanner;

import uade.edu.ar.backtraking.Ubicacion;
public class MainPruebas {

	public static void main(String[] args) {
//---------Variables
	long tiempoInicio;
	long tiempoFinal;
    int x = 0;
    int y = 0;
    Scanner sc =  new Scanner(System.in);
    int filas , columnas;
    System.out.println("Cuantas filas deseas?: ");
    filas = sc.nextInt();
    System.out.println("Cuantas columnas deseas?: ");
    columnas = sc.nextInt();
//----------Solicitud de matriz
    char nombres[][] = new char [filas][columnas];
    for(int i = 0; i < nombres.length; i++){
        for(int j = 0; j < nombres[i].length; j++){
            System.out.println("Ingresa el nombre de la fila " + i + " y la columna " + j);
            String aux = sc.next();
            char auxChar = aux.charAt(0);
            Character.toUpperCase(auxChar);
            nombres[i][j] = Character.toUpperCase(auxChar);  
        }
    }
//----------Imprimir la matriz
    for(int f = 0; f < nombres.length; f++){
        for(int c = 0; c < nombres[f].length; c++){
            System.out.print("["+nombres[f][c]+"]");
        }
        System.out.println("");
    }
 // Si el GEN solicitado es ICE
//  int [] gen = new int[3];
//  gen[0] = 9;
//  gen[1] = 3;
//  gen[2] = 5;
  // Si el GEN solicitado es YELLOW
    int [] gen = new int[6];
    gen[0] = 25;
    gen[1] = 5;
    gen[2] = 12;
    gen[3] = 12;
    gen[4] = 15;
    gen[5] = 23;
 // Si el GEN solicitado es SPOOK
//  int [] gen = new int[5];
//  gen[0] = 19;
//  gen[1] = 16;
//  gen[2] = 15;
//  gen[3] = 15;
//  gen[4] = 11;
    ArrayList<Ubicacion> PosicionGen = new ArrayList<Ubicacion>();
    EncontrarGenesHumano testGen = new EncontrarGenesHumano();
    tiempoInicio = System.nanoTime();
    PosicionGen = testGen.resolverAlgoritmoDeBusquedaGen(nombres,x,y,gen);
    tiempoFinal = System.nanoTime();
    for(int i=0;i < PosicionGen.size();i++) {
    	int a = PosicionGen.get(i).X;
    	int b = PosicionGen.get(i).Y;
    	System.out.println(a+" , "+b);
    }
    System.out.println("Tiempo: " + (tiempoFinal-tiempoInicio) + " nanosegundos."); 	
}
}