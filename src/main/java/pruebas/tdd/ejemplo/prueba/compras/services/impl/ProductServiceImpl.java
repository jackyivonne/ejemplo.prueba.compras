package pruebas.tdd.ejemplo.prueba.compras.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pruebas.tdd.ejemplo.prueba.compras.configuration.MessageProperties;
import pruebas.tdd.ejemplo.prueba.compras.dto.ProductWs;
import pruebas.tdd.ejemplo.prueba.compras.dto.ResponseWs;
import pruebas.tdd.ejemplo.prueba.compras.entity.Product;
import pruebas.tdd.ejemplo.prueba.compras.entity.Store;
import pruebas.tdd.ejemplo.prueba.compras.entity.StoreStock;
import pruebas.tdd.ejemplo.prueba.compras.exceptions.StoreNotFoundException;
import pruebas.tdd.ejemplo.prueba.compras.repository.ProductRepository;
import pruebas.tdd.ejemplo.prueba.compras.repository.StoreRepository;
import pruebas.tdd.ejemplo.prueba.compras.repository.StoreStockRepository;
import pruebas.tdd.ejemplo.prueba.compras.services.ProductService;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreStockRepository storeStockRepository;
	
	@Autowired
	MessageProperties properties;
	
		
	@Override
	@Transactional
	public void loadProductsFromWS(Long storeId) throws StoreNotFoundException {
		// TODO Auto-generated method stub

		// Verifico si existen Store
		Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException(storeId));

		// Consumo el Rest
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = properties.getUrl();
		ResponseEntity<ResponseWs> response = restTemplate.getForEntity(baseUrl, ResponseWs.class);

		// Pongo los Productos del WS en la lista
		List<ProductWs> products = response.getBody().getProducts().stream().filter(product -> product.getStock() > 5)
				.collect(Collectors.toList());

		// Validar q no exista con ese code para guardar en base
		List<ProductWs> productsToSaveInDb = products.stream()
				.filter(product -> productRepository.findByCode(product.getCod()) == null).collect(Collectors.toList());

		// Guarda en la base cada elemento de la lista
		productsToSaveInDb.stream().forEach(product -> productRepository
				.save(new Product(null, product.getCod(), product.getName(), product.getPrice(), null)));

		// Arma la lista de StoreStock
		List<StoreStock> productsInDb = products
				.stream().map(product -> new StoreStock(null, product.getPrice(),
						productRepository.findByCode(product.getCod()), store, product.getStock()))
				.collect(Collectors.toList());

		// Llena en la tabla StoreStock
		List<StoreStock> stockToUpdate = productsInDb.stream().map(stock -> {
			StoreStock productStok = storeStockRepository.findStockByStoreAndproduct(store.getId(),
					stock.getProductOwner().getId());
			if (productStok != null) {
				productStok.setStock(stock.getStock());
			} else {
				productStok = stock;
			}
			return productStok;
		}).collect(Collectors.toList());

		storeStockRepository.saveAll(stockToUpdate);
		log.info("Log de guardado de Product");

	}

}
