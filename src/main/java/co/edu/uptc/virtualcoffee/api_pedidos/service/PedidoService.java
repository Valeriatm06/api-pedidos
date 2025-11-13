package co.edu.uptc.virtualcoffee.api_pedidos.service;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;

/**
 * Interfaz para la lógica de negocio de Pedidos.
 */
public interface PedidoService {

    /**
     * Procesa y guarda un nuevo pedido.
     * @param pedidoDTO El DTO que entra desde el controlador.
     */
    double savePedido(PedidoDTO pedidoDTO);

    PedidoDTO getPedidoById(Long id);
    java.util.List<PedidoDTO> getAllPedidos();
}
