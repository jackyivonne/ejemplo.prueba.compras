package pruebas.tdd.ejemplo.prueba.compras.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.entity.Store;
import pruebas.tdd.ejemplo.prueba.compras.entity.StoreStock;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreException;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreNotFoundException;
import pruebas.tdd.ejemplo.prueba.compras.repository.StoreRepository;
import pruebas.tdd.ejemplo.prueba.compras.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public ResponseDto saveStore(StoreDto storeDto) throws StoreException {
		// TODO Auto-generated method stub
		Store storeBdd = new Store();
		storeBdd.setName(storeDto.getName());
		storeBdd.setOwner(storeDto.getOwner());
		storeBdd.setCategory(storeDto.getCategory());
		try {
			Store saveStore = storeRepository.save(storeBdd);
			return new ResponseDto("Stored saved:" + saveStore.getId());
		} catch (Exception e) {
			// TODO: handle exception
			throw new StoreException("No se puede guardar los datos en la bdd", e);
		}

	}

	@Override
	public StoreDto findStoreByName(String storeName) throws StoreNotFoundException {
		// TODO Auto-generated method stub
		Store store = storeRepository.findByName(storeName);
		Optional<Store> optionalStore = Optional.ofNullable(store);
		if (optionalStore.isPresent()) {
			return new StoreDto(store.getId(), store.getName(), store.getCategory(), store.getOwner(), null);
		} else {
			throw new StoreNotFoundException(storeName);
		}

	}

	@Override
	public Boolean updateStore(StoreDto storeDto, Long storeId) throws StoreNotFoundException {
		// TODO Auto-generated method stub
		Optional<Store> optionalStore = storeRepository.findById(storeId);
		boolean isUpdate;
		if (optionalStore.isPresent()) {
			Store storeBdd = optionalStore.get();
			storeBdd.setName(storeDto.getName());
			storeBdd.setOwner(storeDto.getOwner());
			storeBdd.setCategory(storeDto.getCategory());
			storeRepository.save(storeBdd);
			isUpdate = true;
		} else {
			throw new StoreNotFoundException(storeId);
		}
		return isUpdate;
	}

	@Override
	public ResponseDto deleteStore(Long storeId) throws StoreNotFoundException {
		// TODO Auto-generated method stub
		Optional<Store> optionalStore = storeRepository.findById(storeId);
		if (optionalStore.isPresent()) {
			 Store store= optionalStore.get();
			 List<StoreStock> lista=store.getProducts();
			// if(!Objects.isNull(lista) ) {
			 if(!lista.isEmpty()) {
				 return new ResponseDto("No se puede eliminar porque existen productos: " + storeId);
			 }
			storeRepository.deleteById(storeId);
			return new ResponseDto("Se ha eliminado el store id: " + storeId);
		} else
			throw new StoreNotFoundException(storeId);
	}

}
