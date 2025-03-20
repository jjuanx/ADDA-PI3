package ejercicio2;

import java.io.IOException;

import us.lsi.gurobi.GurobiLp;
import us.lsi.solve.AuxGrammar;

public class MainPLE {

	public static void main(String[] args)  throws IOException {
		DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada1.txt");
		AuxGrammar.generate(DatosCursos.class, "src/ejercicio2/ejercicio2.lsi", "gurobi_models/Ejercicio2.lp");
		var solucion = GurobiLp.solveSolution("gurobi_models/Ejercicio2.lp");
		System.out.println(solucion);
	}

}
