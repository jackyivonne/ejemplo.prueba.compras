package pruebas.tdd.ejemplo.prueba.compras.exceptions;

public class StoreException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3347765840863696539L;
	
	
	private String message;

	public StoreException(String message, Throwable exception) {
		super(message, exception);
		this.message = message;
	}

}
