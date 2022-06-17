package pruebas.tdd.ejemplo.prueba.compras.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.entity.Store;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreException;
import pruebas.tdd.ejemplo.prueba.compras.repository.StoreRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StoreServiceImplTest {

	@MockBean
	private StoreRepository storeRepository;

	@InjectMocks
	private StoreServiceImpl storeService;

	private StoreDto storeDto;

	@BeforeEach
	private void inicialize() {
		storeDto = new StoreDto();
		storeDto.setCategory("legumbres");
		storeDto.setName("Tienda de prueba");
		storeDto.setOwner("Pepe");
	}

	@Test
	public void givenStoreWithStock_whenSaveAStoreRequest_thenSaveStoreInDb() throws StoreException {
		Mockito.when(storeRepository.save(Mockito.any(Store.class)))
				.thenReturn(new Store(1L, "mock", null, null, null));
		ResponseDto response = storeService.saveStore(storeDto);
		Assert.hasText(response.getMessage(), "No se guardo en la bdd");
	}

	@Test
	public void givenStoreWithStock_whenSaveAStoreRequestAndBddProblems_thenStoreException() throws StoreException {
		Mockito.when(storeRepository.save(Mockito.any(Store.class)))
				.thenThrow(new RuntimeException("Something error with bdd"));
		StoreException exception = null;
		try {
			storeService.saveStore(storeDto);
		} catch (StoreException e) {
			// TODO: handle exception
			exception = e;
		}
		Assert.notNull(exception, "No se produjo la exception esperada");

	}

}
