package pruebas.tdd.ejemplo.prueba.compras.services;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreException;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreNotFoundException;

public interface StoreService {

	ResponseDto saveStore(StoreDto storeDto) throws StoreException;

	StoreDto findStoreByName(String storeName) throws StoreNotFoundException;

	Boolean updateStore(StoreDto storeDto, Long storeId) throws StoreNotFoundException;

	ResponseDto deleteStore(Long storeId) throws StoreNotFoundException;
}
