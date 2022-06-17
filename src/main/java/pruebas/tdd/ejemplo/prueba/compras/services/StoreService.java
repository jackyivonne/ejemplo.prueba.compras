package pruebas.tdd.ejemplo.prueba.compras.services;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreException;

public interface StoreService {

	ResponseDto saveStore(StoreDto storeDto) throws StoreException;

}
