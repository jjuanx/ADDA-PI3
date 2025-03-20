package ejercicio4;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class CromosomaEstaciones implements SeqNormalData<SolucionEstaciones> {
	
	public CromosomaEstaciones(String fichero) {
		DatosEstaciones.iniDatos(fichero);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		return -goal(value) - 10000*(r2(value) + r3(value));
	}
	
	private double goal(List<Integer> ls) {
		Double r;
		if(r1(ls) != 0) {
			r = Double.MAX_VALUE;
		} else {
			double sum = IntStream.range(0, ls.size())
					.mapToDouble(i -> 
					DatosEstaciones.getTramo(ls.get(i), (ls.get((i+1)%ls.size()))).tiempo()).sum();
			r = sum/ls.size();
		}
		return r;
	}

	private double r1(List<Integer> ls) {
		return IntStream.range(0, ls.size())
				.filter(i -> !DatosEstaciones.getGrafo().containsEdge(DatosEstaciones.getTramo(ls.get(1), (ls.get((i+1)%ls.size()))))).count();
	}
	
	private double r2(List<Integer> ls) {
		Double r;
		if(r1(ls) != 0) {
			r = Double.MAX_VALUE;
		} else {
			double sumCamino = IntStream.range(0, ls.size())
					.mapToDouble(i -> 
					DatosEstaciones.getTramo(ls.get(i), (ls.get((i+1)%ls.size()))).costeBillete()).sum();
			double sumGrafo = DatosEstaciones.getGrafo().edgeSet().stream().mapToDouble(e -> e.costeBillete()).sum();
			r = AuxiliaryAg.distanceToLeZero(sumCamino - (3/4)*sumGrafo);
		}
		return r;
	}
	
	private double r3(List<Integer> ls) {
		Double r;
		if(r1(ls) != 0) {
			r = Double.MAX_VALUE;
		} else {
			double count = IntStream.range(0, ls.size())
					.filter(i ->
					DatosEstaciones.getEstacion(ls.get(i)).satisfaccionClientes() >= 7 &&
					DatosEstaciones.getEstacion(ls.get((i+1)%ls.size())).satisfaccionClientes() >= 7).count();
			r = AuxiliaryAg.distanceToGeZero(count - 1);
		}
		return r;
	}

	
	@Override
	public SolucionEstaciones solucion(List<Integer> value) {
		return SolucionEstaciones.create(value);
	}

	@Override
	public Integer itemsNumber() {
		return DatosEstaciones.getNumEstaciones();
	}

}
