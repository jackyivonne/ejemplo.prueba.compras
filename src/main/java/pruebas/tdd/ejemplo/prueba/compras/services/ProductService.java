package pruebas.tdd.ejemplo.prueba.compras.services;

import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreNotFoundException;

public interface ProductService {

	void loadProductsFromWS(Long storeId) throws StoreNotFoundException;
}
