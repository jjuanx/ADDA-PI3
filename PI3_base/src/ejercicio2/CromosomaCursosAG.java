package ejercicio2;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.BinaryData;

public class CromosomaCursosAG implements BinaryData<SolucionCursos>{
	
	public CromosomaCursosAG(String fichero) {
		DatosCursos.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosCursos.getNumCursos();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Integer score = 0;
		Integer restriccionTec = 0;
		Integer restriccionAreas = 0;
		Integer duracionTotal = 0;
		Integer restriccionDuracion = 0;
		Integer costeTotal = 0;
		Integer restriccionCoste = 0;
		
		List<Integer> areas = new ArrayList<>();
		for (int j=0 ; j < DatosCursos.getNumAreas(); j++) {
			areas.add(0);
		}
		
		int numeroCursosSeleccionados = 0;
		
		//RECORREMOS LOS CURSOS SELCCIONADOS
		for( int i=0; i < value.size(); i++) {
			if(value.get(i) != 0) {
				score = score + DatosCursos.getRelevancia(i);
				int areaCurso = DatosCursos.getArea(i);
				areas.set(areaCurso, areas.get(areaCurso) + 1);
				duracionTotal = duracionTotal + DatosCursos.getDuracion(i);
				costeTotal = costeTotal + DatosCursos.getCoste(i);
				numeroCursosSeleccionados++;
			}
		}
		
		//RESTRICCION 1: verificamos si hay al menos un curso por cada area
		for(int j=0; j < areas.size(); j++) {
			if(areas.get(j) < 1) {
				restriccionAreas++;
			}
		}
		
		//// Verificar que el área de tecnología tenga al menos tantos cursos como cualquier otra área
		int numCursosTec = areas.get(0); // Suponiendo que Tecnología es el área 0
		for(int j=0; j < areas.size(); j++) {
			if(areas.get(j) > numCursosTec) {
				restriccionTec++;
			}
		}
		
		//RESTRICCION DURACION
		if(numeroCursosSeleccionados > 0) {
			double duracionMedia = (double) duracionTotal / numeroCursosSeleccionados;
			if(duracionMedia < 20) {
				restriccionDuracion=1;
			}
		} else {
			restriccionDuracion=0;
		}
		
		//RESTRICCION COSTE
		if(costeTotal > DatosCursos.getPresupuestoTotal()) {
			restriccionCoste=1;
		}
		
		//FITNESS
		return (double) (score - 1000*(restriccionTec + restriccionAreas + restriccionDuracion + restriccionCoste));
	}

	@Override
	public SolucionCursos solucion(List<Integer> value) {
		return SolucionCursos.create(value);
	}

}
