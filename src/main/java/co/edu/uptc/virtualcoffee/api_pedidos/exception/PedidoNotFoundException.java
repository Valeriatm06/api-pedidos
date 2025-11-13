package co.edu.uptc.virtualcoffee.api_pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada que se lanza cuando un pedido no se encuentra.
 * @ResponseStatus(HttpStatus.NOT_FOUND) le dice a Spring
 * que esto debe resultar en un 404.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(String message) {
        super(message);
    }
}