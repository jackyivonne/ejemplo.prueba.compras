package pruebas.tdd.ejemplo.prueba.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.entity.Store;
import pruebas.tdd.ejemplo.prueba.compras.repository.StoreRepository;
import pruebas.tdd.ejemplo.prueba.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public boolean saveStore(StoreDto storeDto) {
		// TODO Auto-generated method stub
		Store storeBdd = new Store();
		storeBdd.setName(storeDto.getName());
		storeBdd.setOwner(storeDto.getOwner());
		storeBdd.setCategory(storeDto.getCategory());
		storeRepository.save(storeBdd);
		return false;
	}

}
