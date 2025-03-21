package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ejercicio1.DatosAlmacenes.Producto;

public class SolucionAlmacen {
	
	public static SolucionAlmacen create(List<Integer> ls) {
		return new SolucionAlmacen(ls);
	}

	private Integer numproductos;
	private Map<Producto, Integer> solucion;

	private SolucionAlmacen(List<Integer> ls) {
		this.numproductos = 0;
		this.solucion = new HashMap<>();
		
		
		for(int i=0; i < ls.size(); i++) {
			if(ls.get(i) > 0) {
				Integer productoActual = i % DatosAlmacenes.getNumProductos();
				Integer almacenActual = i / DatosAlmacenes.getNumProductos();
				Producto prod = DatosAlmacenes.getProducto(productoActual);
				
				this.numproductos = this.numproductos + ls.get(i);
				this.solucion.put(prod, almacenActual);
			}
		}
	}
	
	@Override
	public String toString() {
		return solucion.entrySet().stream()
		.map(p -> p.getKey().producto()+": Almacen "+p.getValue())
		.collect(Collectors.joining("\n", "Reparto de productos y almacen en el que se coloca cada uno de ellos:\n", String.format("\nProductos colocados: %d", numproductos)));
	}

}
