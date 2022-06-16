package pruebas.tdd.ejemplo.prueba.compras.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetailOrderDto {

	private Long id;
	private ProductDto productOwner;
	private ProductOrderDto orderOwner;
	private Integer totalOrdered;
	private Double priceUnit;
	private Double priceTotal;

}
