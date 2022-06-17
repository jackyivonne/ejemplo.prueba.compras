package pruebas.tdd.ejemplo.prueba.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pruebas.tdd.ejemplo.prueba.compras.entity.StoreStock;

public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {

}
