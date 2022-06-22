package pruebas.tdd.ejemplo.prueba.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pruebas.tdd.ejemplo.prueba.compras.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByCode(String code);

}
