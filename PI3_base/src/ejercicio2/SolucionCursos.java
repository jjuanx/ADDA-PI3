package ejercicio2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolucionCursos {

    public static SolucionCursos create(List<Integer> ls) {
        return new SolucionCursos(ls);
    }

    private Integer numCursos;
    private Map<Integer, Integer> solucion;
    private Double puntuacionTotal;
    private Integer costeTotal;

    private SolucionCursos(List<Integer> ls) {
        this.numCursos = 0;
        this.solucion = new HashMap<>();
        this.puntuacionTotal = 0.;
        this.costeTotal = 0;
        
        for(int i=0; i < ls.size(); i++) {
        	if(ls.get(i) != 0) {
        		solucion.put(i, ls.get(i));
        		numCursos = numCursos +1;
        		puntuacionTotal = puntuacionTotal + DatosCursos.getRelevancia(i);
        		costeTotal = costeTotal + DatosCursos.getCoste(i);
        	}
        }
    }

    @Override
    public String toString() {
        return solucion.entrySet().stream()
                .map(p -> "Curso " + p.getKey() + ": Seleccionado")
                .collect(Collectors.joining("\n", "Cursos seleccionados:\n", String.format("\nTotal de cursos seleccionados: %d\nPuntuación total: %.2f\nCoste total: %d", numCursos, puntuacionTotal, costeTotal)));
    }

    public Integer getNumCursos() {
        return numCursos;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public Integer getCosteTotal() {
        return costeTotal;
    }
}
