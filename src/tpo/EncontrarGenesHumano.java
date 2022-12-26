package tpo;

import java.util.ArrayList;
import java.util.HashMap;

import uade.edu.ar.backtraking.AlgoritmoGenetico;
import uade.edu.ar.backtraking.Ubicacion;

public class EncontrarGenesHumano implements AlgoritmoGenetico {
	
	@Override
	public ArrayList<Ubicacion> resolverAlgoritmoDeBusquedaGen(char[][] mapa, int ubicacionX, int ubicacionY,
			int[] gen) {
		ArrayList<Ubicacion> PosicionGen = new ArrayList<Ubicacion>();
		int etapa = 0;
		HashMap<Character, Integer> mapaGen = new HashMap<>();
		mapaGen.put('C', 3);
		mapaGen.put('E', 5);
		mapaGen.put('I', 9);
		mapaGen.put('K', 11);
		mapaGen.put('L', 12);
		mapaGen.put('O', 15);
		mapaGen.put('P', 16);
		mapaGen.put('S', 19);
		mapaGen.put('W', 23);
		mapaGen.put('Y', 25);
		ArrayList<Ubicacion> visitados = new ArrayList<Ubicacion>();
		PosicionGen = BuscarGen(mapa,gen,ubicacionX,ubicacionY,PosicionGen,etapa,mapaGen,visitados,0);
		return PosicionGen;
	}

	private ArrayList<Ubicacion> BuscarGen(char[][] mapaGenetico, int[] gen, int ubicacionX, int ubicacionY,ArrayList<Ubicacion> PosicionGen,int etapa,HashMap<Character, Integer> mapaGen,ArrayList<Ubicacion> visitados,int flag){
		if(PosicionGen.size() == gen.length)
			return PosicionGen;
		if (etapa == 0) {
			Ubicacion auxUbicacion = new Ubicacion(ubicacionX,ubicacionY);
			if(!fueVisitado(visitados,auxUbicacion)) {
				visitados.add(auxUbicacion);
				if(hayEspacioHorizontal(mapaGenetico,ubicacionY)) {
						BuscarGen(mapaGenetico,gen,ubicacionX,ubicacionY+1,PosicionGen,etapa,mapaGen,visitados,0);
				}
				if(hayEspacioVertical(mapaGenetico,ubicacionX)) {
						BuscarGen(mapaGenetico,gen,ubicacionX+1,ubicacionY,PosicionGen,etapa,mapaGen,visitados,0);
				}
			}
		}
		if(Character.isDigit(mapaGenetico[ubicacionX][ubicacionY]) || mapaGen.containsKey(mapaGenetico[ubicacionX][ubicacionY])) {
			int auxNum;
			if(Character.isDigit(mapaGenetico[ubicacionX][ubicacionY])){
				auxNum = Character.getNumericValue(mapaGenetico[ubicacionX][ubicacionY]);
			}else {
				auxNum = mapaGen.get(mapaGenetico[ubicacionX][ubicacionY]);
			}
			if(auxNum == gen[etapa]) {
				Ubicacion aux = new Ubicacion(ubicacionX,ubicacionY);
				PosicionGen.add(aux);
				if((ValidarGenHorizontal(mapaGenetico,gen,ubicacionX,ubicacionY,etapa,mapaGen)) && (flag == 0 || flag == 1)) {	
					BuscarGen(mapaGenetico,gen,ubicacionX,ubicacionY+1,PosicionGen,etapa+1,mapaGen,visitados,1);
				}
				if((ValidarGenVertical(mapaGenetico,gen,ubicacionX,ubicacionY,PosicionGen,etapa,mapaGen)) && (flag == 0 || flag == 2)) {				
					BuscarGen(mapaGenetico,gen,ubicacionX+1,ubicacionY,PosicionGen,etapa+1,mapaGen,visitados,2);
				}
				if((ValidarGenDiagonal(mapaGenetico,gen,ubicacionX,ubicacionY,PosicionGen,etapa,mapaGen)) && (flag == 0 || flag == 3)) {
					BuscarGen(mapaGenetico,gen,ubicacionX+1,ubicacionY+1,PosicionGen,etapa+1,mapaGen,visitados,3);
				}
				if(PosicionGen.size() != gen.length)
					PosicionGen.remove(etapa);
				
			}
		}
		return PosicionGen;
	}
	
	private boolean fueVisitado(ArrayList<Ubicacion> visitados,Ubicacion ubicacion) {
		for (int i = 0;i<visitados.size();i++) {
			if (visitados.get(i).X == ubicacion.X && visitados.get(i).Y == ubicacion.Y) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hayEspacioHorizontal(char[][] MapaGenetico, int ubicacionY) {
		if(ubicacionY >= 0 && ubicacionY < MapaGenetico[0].length - 1) {
			return true;
		}
		return false;
	}
	private boolean hayEspacioVertical(char[][] MapaGenetico, int ubicacionX) {
		if(ubicacionX >= 0 && ubicacionX < MapaGenetico.length - 1) {
			return true;
		}
		return false;
	}

	private boolean ValidarGenHorizontal(char[][] MapaGenetico, int[] gen, int ubicacionX, int ubicacionY,int etapa,HashMap<Character, Integer> mapaGen) {
		if(ubicacionY == MapaGenetico[0].length - 1 || etapa == gen.length-1) {
			return false;
		}
		if(Character.isDigit(MapaGenetico[ubicacionX][ubicacionY+1])) {
			if(Character.getNumericValue(MapaGenetico[ubicacionX][ubicacionY+1]) == gen[etapa+1] && (gen.length-1)-etapa <= (MapaGenetico[0].length-1-ubicacionY)) {
				return true;
			}
		}	
		if(mapaGen.containsKey(MapaGenetico[ubicacionX][ubicacionY+1])){
			if(mapaGen.get(MapaGenetico[ubicacionX][ubicacionY+1]) == gen[etapa+1] && (gen.length-1)-etapa <= (MapaGenetico[0].length-1-ubicacionY)) {
				return true;
			}
		}
		return false;		
	}

	private boolean ValidarGenVertical(char[][] MapaGenetico, int[] gen, int ubicacionX, int ubicacionY,ArrayList<Ubicacion> PosicionGen,int etapa,HashMap<Character, Integer> mapaGen) {
		if(ubicacionX == MapaGenetico.length - 1 || etapa == gen.length-1) {
			return false;
		}
		if(Character.isDigit(MapaGenetico[ubicacionX + 1][ubicacionY])) {
			if(Character.getNumericValue(MapaGenetico[ubicacionX+1][ubicacionY]) == gen[etapa+1] && (gen.length-1)-etapa <= (MapaGenetico.length-1-ubicacionX)) { 
				return true;
			}
		}
		if(mapaGen.containsKey(MapaGenetico[ubicacionX + 1][ubicacionY])){
			if(mapaGen.get(MapaGenetico[ubicacionX + 1][ubicacionY]) == gen[etapa+1] && (gen.length-1)-etapa <= (MapaGenetico.length-1-ubicacionX)) { 
				return true;
			}
		}
		return false;		
	}
	private boolean ValidarGenDiagonal(char[][] MapaGenetico, int[] gen, int ubicacionX, int ubicacionY,ArrayList<Ubicacion> PosicionGen,int etapa,HashMap<Character, Integer> mapaGen) {
		if(ubicacionY == MapaGenetico[0].length - 1 || ubicacionX == MapaGenetico.length - 1 || etapa == gen.length-1) {
			return false;
		}
		if(Character.isDigit(MapaGenetico[ubicacionX + 1][ubicacionY + 1])) {
			if(Character.getNumericValue(MapaGenetico[ubicacionX+1][ubicacionY+1]) == gen[etapa+1] && (gen.length -1) - etapa <= Math.min(((MapaGenetico[0].length -1) -ubicacionY),((MapaGenetico.length -1)-ubicacionX))) {
					return true;
			}
		}
		if(mapaGen.containsKey(MapaGenetico[ubicacionX + 1][ubicacionY + 1])){
			if(mapaGen.get(MapaGenetico[ubicacionX + 1][ubicacionY + 1])== gen[etapa+1] && (gen.length -1) - etapa <= Math.min(((MapaGenetico[0].length -1) -ubicacionY),((MapaGenetico.length -1)-ubicacionX))) {
				return true;
			}
		}
		return false;		
	}
}