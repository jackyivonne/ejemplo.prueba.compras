package pruebas.tdd.ejemplo.prueba.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pruebas.tdd.ejemplo.prueba.compras.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
