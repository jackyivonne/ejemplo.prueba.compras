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
public class StoreDto {

	private Long id;
	private String name;
	private String category;
	private String owner;
	private List<StoreStockDto> products;

}
