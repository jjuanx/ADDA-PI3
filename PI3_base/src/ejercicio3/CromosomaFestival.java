package ejercicio3;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;


import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class CromosomaFestival implements ValuesInRangeData<Integer, SolucionFestival> {
	
	public CromosomaFestival(String fichero) {
		DatosFestival.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosFestival.getNumTiposEntrada() * DatosFestival.getNumAreas();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double goal = 0.;
		Integer restriccionCuota = 0;
		Integer restriccionAforo = 0;
		
		Integer NT =  DatosFestival.getNumTiposEntrada(); //n
		Integer NA = DatosFestival.getNumAreas();
		
		Map<Integer, Integer> ticketsArea = new HashMap<>();
		Map<Integer, Integer> ticketsTipo = new HashMap<>();
		
		for (int i=0; i < value.size(); i++) {
			Integer tickets = value.get(i);
			Integer TipoActual = i / NA;
			Integer AreaActual = i % NA;
			
			goal += DatosFestival.getCosteAsignacion(TipoActual, AreaActual) * tickets;
			
			ticketsTipo.put(TipoActual, ticketsTipo.getOrDefault(TipoActual, 0) + value.get(i));
			ticketsArea.put(AreaActual, ticketsArea.getOrDefault(AreaActual, 0) + value.get(i));
		}
		
		//RESTRICCION AFORO MAXIMO
		for(int j=0; j < NA; j++) {
			Integer total = ticketsArea.getOrDefault(j, 0);
			if (total > DatosFestival.getAforoMaximoArea(j)) {
				restriccionAforo += (total - DatosFestival.getAforoMaximoArea(j));
			}
		}
		
		//RESTRICCION CUOTA MINIMA
		for (int i=0; i < NT; i++) {
			Integer total = ticketsTipo.getOrDefault(i, 0);
			if (total < DatosFestival.getCuotaMinima(i)) {
				restriccionCuota += (DatosFestival.getCuotaMinima(i) - total);
			}
		}
		
		return -goal - 10000*(restriccionAforo*restriccionAforo + restriccionCuota*restriccionCuota);
	}

	@Override
	public SolucionFestival solucion(List<Integer> value) {
		return SolucionFestival.create(value);
	}

	@Override
	public Integer max(Integer i) {
		Integer AreaActual = i % DatosFestival.getNumAreas();
		return DatosFestival.getAforoMaximoArea(AreaActual) + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
}
//	
//	public CromosomaFestival(String fichero) {
//		DatosFestival.iniDatos(fichero);
//	}
//
//	@Override
//	public ChromosomeType type() {
//		return ChromosomeType.Permutation;
//	}
//
//	@Override
//	public Double fitnessFunction(List<Integer> value) {
//		Double goal = 0.;
//		Double error = 0.;
//		
//		Integer n = DatosFestival.getNumTiposEntrada();
//		Integer m = DatosFestival.getNumAreas();
//		
//		// Map para calcular el número de entradas por Área y por Tipo de Entrada
//		Map<Integer, Integer> entradasArea = new HashMap<>();
//		Map<Integer, Integer> entradasTipo = new HashMap<>();
//		
//		
//		for(Integer i=0; i < value.size(); i++) {
//			Integer tickets = value.get(i);
//			
//			Integer TipoActual = i / m;
//			Integer AreaActual = i % m;
//			
//			goal = goal + DatosFestival.getCosteAsignacion(TipoActual, AreaActual) * tickets;
//			
//			if(entradasArea.containsKey(AreaActual)) {
//				entradasArea.put(AreaActual, entradasArea.get(AreaActual) + tickets);
//			} else {
//				entradasArea.put(AreaActual, tickets);
//			}
//			if(entradasTipo.containsKey(TipoActual)) {
//				entradasTipo.put(TipoActual, entradasTipo.get(TipoActual) + tickets);
//			} else {
//				entradasTipo.put(TipoActual, tickets);
//			}
//		}
//		
//		//RESTRICCION 1:
//		for(int a =0; a < m; a++) {
//			if(entradasArea.containsKey(a)) {
//				Integer AforoTotalArea = entradasArea.get(a);
//				Integer AforoMax = DatosFestival.getAforoMaximoArea(a);
//				if(AforoTotalArea > AforoMax) {
//					error += Math.pow(AforoTotalArea - AforoMax, 2);
//				}
//			}
//		}
//		
//		//RESTRICCION2:
//		for(int t = 0; t < n; t++) {
//			if(entradasTipo.containsKey(t)) {
//				Integer TotalTipo = entradasTipo.get(t);
//				Integer TipoMin = DatosFestival.getCuotaMinima(t);
//				if(TotalTipo < TipoMin) {
//					error += Math.pow(TipoMin - TotalTipo, 2);
//				}
//			}
//		}
//		
//		return -goal - 10000*error;
//	}
//
//	@Override
//	public SolucionFestival solucion(List<Integer> value) {
//		return SolucionFestival.create(value);
//	}
//	
//	@Override
//	public Integer maxMultiplicity(int index) {
//		Integer AreaActual = index % DatosFestival.getNumAreas();
//		return DatosFestival.getAforoMaximoArea(AreaActual) + 1;
//	}
//
//	@Override
//	public Integer itemsNumber() {
//		return DatosFestival.getNumTiposEntrada() * DatosFestival.getNumAreas();
//	}
