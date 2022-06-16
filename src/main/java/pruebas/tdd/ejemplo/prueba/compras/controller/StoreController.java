package pruebas.tdd.ejemplo.prueba.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseDto;
import pruebas.tdd.ejemplo.prueba.compras.dto.StoreDto;
import pruebas.tdd.ejemplo.prueba.compras.services.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private StoreService storeService;

	@PostMapping()
	public ResponseEntity<ResponseDto> createStore(@RequestBody StoreDto store) {
		return new ResponseEntity(storeService.saveStore(store), HttpStatus.OK);
	}

}
