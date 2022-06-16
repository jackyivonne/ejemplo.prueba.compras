package pruebas.tdd.ejemplo.prueba.compras.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class StoreStockDto {

	private Long id;
	private Integer stock;
	private Double soldPrice;
	private ProductDto productOwner;
	private StoreDto storeOwner;

}
