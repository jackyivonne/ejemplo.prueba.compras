package pruebas.tdd.ejemplo.prueba.compras.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.entity.Store;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreException;
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

}
