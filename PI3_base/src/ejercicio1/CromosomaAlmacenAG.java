package ejercicio1;

import java.util.List;

import us.lsi.ag.BinaryData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class CromosomaAlmacenAG implements BinaryData<SolucionAlmacen>{
	
	// CROMOSOMA  --> A0 = [0,1,1,0,0,1,0,1,0]
	
	public CromosomaAlmacenAG(String fichero) {
		DatosAlmacenes.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosAlmacenes.getNumProductos() * DatosAlmacenes.getNumAlmacenes();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double goal = 0.;
		Double error = 0.;
		
		Integer c1 = 0; //UN producto en un unico almacen
		Integer c2 = 0; //Volumen total no supere capacidad almacen
		
		Integer n = DatosAlmacenes.getNumProductos();
		Integer m = DatosAlmacenes.getNumAlmacenes();
		
		//FUNCION OBJETIVO
		for(int p=0; p < value.size(); p++) {
			if (value.get(p) > 0) {
				goal = goal + value.get(p);
			}
		}
		
		//RESTRICCION 1
		for (int i = 0; i < n; i++) {
			c1=0;
			for (int j=0; j < m; j++) {
				Integer prod = j*n + i;
				if(value.get(prod) == 1) {
					c1++;
				}
			}
		
			if (c1>1) {
				error = error + c1;
			}
		}
		
		//RESTRICCION 2
		for (int a=0; a < m; a++) {
			c2 = 0;
			for(int p=0; p<n; p++) {
				Integer i = a*n + p;
				if(value.get(i) > 0) {
					c2 = c2 + DatosAlmacenes.getMetrosCubicosProducto(p);
				}
			}
			if(c2> DatosAlmacenes.getMetrosCubicosAlmacen(a)) {
				error = error + c2;
			}
		}
		
		//RESTRICCION 3
		for (int a=0; a<m; a++) {
			for (int p=0; p<n; p++) {
				Integer PrimerProducto = a*n + p;
				
				if(value.get(PrimerProducto) == 1) {
					for (int p2= p+1; p2<n; p2++) {
						Integer SegundoProducto = a*n + p2;
						if(value.get(SegundoProducto) == 1 && DatosAlmacenes.esIncompatible(p, p2) == 1){
							error++;
						}
					}
				}
			}
		}
		
		return goal - 10000*Math.pow(error, 2);
		
	}

	@Override
	public SolucionAlmacen solucion(List<Integer> value) {
		return SolucionAlmacen.create(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosAlmacenes.getNumAlmacenes() - 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
