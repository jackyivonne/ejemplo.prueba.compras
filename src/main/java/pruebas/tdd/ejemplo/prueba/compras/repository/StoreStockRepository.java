package pruebas.tdd.ejemplo.prueba.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pruebas.tdd.ejemplo.prueba.compras.entity.StoreStock;

public interface StoreStockRepository extends JpaRepository<StoreStock, Long> {

	//@Query(value = "select * from store_stock where product_id = :productId and store_id = :storeId", nativeQuery = true)
	@Query(value = "select s from StoreStock s where s.productOwner.id = :productId and s.storeOwner.id = :storeId", nativeQuery = false)
	StoreStock findStockByStoreAndproduct(@Param("storeId") Long storeId, @Param("productId") Long productId);

}
