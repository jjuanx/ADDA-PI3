package ejercicio1;

import java.io.IOException; 

import us.lsi.gurobi.GurobiLp;
import us.lsi.solve.AuxGrammar;


public class Ejercicio1MainPLE {
	

	public static void main(String[] args) throws IOException {
		DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada1.txt");
		AuxGrammar.generate(DatosAlmacenes.class,"src/ejercicio1/ejercicio1.lsi","gurobi_models/Ejercicio1.lp");
		var solution = GurobiLp.solveSolution("gurobi_models/Ejercicio1.lp");
		System.out.println(solution);
	}

}
