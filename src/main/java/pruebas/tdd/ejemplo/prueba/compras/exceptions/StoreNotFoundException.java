package pruebas.tdd.ejemplo.prueba.compras.exceptions;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

@Getter
public class StoreNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1346737895452975039L;

	private String storeName;
	private Long id;

	public StoreNotFoundException(String storeName) {
		super(" La Tienda '" + storeName + "' no encontrada");
		this.storeName = storeName;
	}

	public StoreNotFoundException(Long id) {
		super(" La Tienda con id: '" + id + "' no encontrada");
		this.id = id;
	}

}
