package ejercicio3;

import java.io.IOException;

import us.lsi.gurobi.GurobiLp;
import us.lsi.solve.AuxGrammar;

public class MainPLE {

	public static void main(String[] args) throws IOException {
		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada1.txt");
		AuxGrammar.generate(DatosFestival.class, "src/ejercicio3/ejercicio3.lsi", "gurobi_models/Ejercicio3.lp");
		var solucion = GurobiLp.gurobi("gurobi_models/Ejercicio3.lp");
		System.out.println(solucion);
	}

}
