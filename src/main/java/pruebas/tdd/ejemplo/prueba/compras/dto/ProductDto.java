package pruebas.tdd.ejemplo.prueba.compras.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {

	private Long id;
	private String name;
	private Double price;
	private List<StoreStockDto> stockByStore;
}
