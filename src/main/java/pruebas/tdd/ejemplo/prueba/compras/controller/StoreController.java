package pruebas.tdd.ejemplo.prueba.compras.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreException;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreNotFoundException;
import pruebas.tdd.ejemplo.prueba.compras.services.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private StoreService storeService;

	public final static Logger LOGGER = LoggerFactory.getLogger(StoreController.class);
	
	@Value("${message.log.storeController}")
	private String message;

	@PostMapping("")
	public ResponseEntity<ResponseDto> createStore(@RequestBody StoreDto store) {
		try {
			LOGGER.info(message, StoreController.class.getName());
			return new ResponseEntity(storeService.saveStore(store), HttpStatus.OK);

		} catch (StoreException e) {
			// TODO Auto-generated catch block			
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping()
	public ResponseEntity<StoreDto> searchStoreByName(@RequestHeader String storeName) {
		try {
			LOGGER.info(message, StoreController.class.getName());
			return new ResponseEntity(storeService.findStoreByName(storeName), HttpStatus.OK);
		} catch (StoreNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.info(message, StoreController.class.getName());
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/{storeId}")
	public ResponseEntity<StoreDto> updateStoreById(@RequestBody StoreDto storeDto, @PathVariable Long storeId) {

		try {
			LOGGER.info("Log updateStoreById'", StoreController.class.getName());
			return new ResponseEntity(storeService.updateStore(storeDto, storeId), HttpStatus.OK);
		} catch (StoreNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}

	}

	@DeleteMapping(path = "/{storeId}")
	public ResponseEntity<ResponseDto> deteteStoreById(@PathVariable Long storeId) {
		try {
			LOGGER.info("Log deteteStoreById'", StoreController.class.getName());
			return new ResponseEntity(storeService.deleteStore(storeId), HttpStatus.OK);
		} catch (StoreNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}
	}

}
