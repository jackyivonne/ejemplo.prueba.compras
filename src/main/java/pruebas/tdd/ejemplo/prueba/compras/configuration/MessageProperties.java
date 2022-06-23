package pruebas.tdd.ejemplo.prueba.compras.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "parameter.global")
@PropertySource("classpath:global.properties")
@Getter
@Setter
public class MessageProperties {

	private String url;

}
