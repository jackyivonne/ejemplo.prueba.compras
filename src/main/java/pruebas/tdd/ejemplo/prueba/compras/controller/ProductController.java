package pruebas.tdd.ejemplo.prueba.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreNotFoundException;
import pruebas.tdd.ejemplo.prueba.compras.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/stockByStore/{storeId}")
	public ResponseEntity<?> loadProductStore(@PathVariable Long storeId) {
		try {
			productService.loadProductsFromWS(storeId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (StoreNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
