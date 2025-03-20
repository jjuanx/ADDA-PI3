package ejercicio4;

import java.util.List;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class MainAG {

	public static void main(String[] args) {
		
		AlgoritmoAG.ELITISM_RATE  = 0.05;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		CromosomaEstaciones p = new CromosomaEstaciones("resources/ejercicio4/DatosEntrada1.txt");
		
		AlgoritmoAG<List<Integer>,SolucionEstaciones> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
	}

}
