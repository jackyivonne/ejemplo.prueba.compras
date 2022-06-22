package pruebas.tdd.ejemplo.prueba.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pruebas.tdd.ejemplo.prueba.compras.entity.StoreStock;

public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {

	@Query(value = "select * from store_stock where product_id = :productId and store_id = :storeId", nativeQuery = true)
	StoreStock findStockByStoreAndproduct(@Param("storeId") Long storeId, @Param("productId") Long productId);

}
