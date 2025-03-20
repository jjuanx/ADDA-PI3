package ejercicio4;

import java.util.Map;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.Map2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class DatosEstaciones {
	
	private static SimpleWeightedGraph<Estacion, Tramo> g;
	private static Map<Estacion, Integer> map;
	
	public static void iniDatos(String txt) {
		g = GraphsReader.newGraph(txt, 
				Estacion::ofFormat,
				Tramo::ofFormat,
				Graphs2::simpleWeightedGraph);
		int index = 0;
		map = Map2.empty();
		for(Estacion v: g.vertexSet()) {
			map.put(v, index);
			index++;
		}
		System.out.println(g);
	}
	
	public static SimpleWeightedGraph<Estacion, Tramo> getGrafo() {
		return g;
	}
	
	public static Integer getNumEstaciones() {
		return g.vertexSet().size();
	}
	
	public static Estacion getEstacion(Integer index) {
		return map.keySet().stream()
				.filter(k -> map.get(k).equals(index)).findFirst().get();
	}
	
	public static Tramo getTramo(Integer i, Integer j) {
		return g.getEdge(getEstacion(i), getEstacion(j));
	}

}
