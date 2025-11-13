package co.edu.uptc.virtualcoffee.api_pedidos.service;

import co.edu.uptc.virtualcoffee.api_pedidos.dto.BebidaDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.exception.PedidoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementación de la lógica de negocio de Pedidos.
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    private final RestTemplate restTemplate;
    private final String bebidasApiUrl = "http://localhost:8000/menu/";

    @Autowired
    public PedidoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double savePedido(PedidoDTO pedidoDTO) { // <-- 1. Cambia 'void'

        double totalPedido = 0.0;
        for (String itemName : pedidoDTO.getItems()) {
            // ... (lógica del try/catch/getForObject) ...
            try {
                BebidaDTO bebida = restTemplate.getForObject(bebidasApiUrl + itemName, BebidaDTO.class);
                if (bebida != null) {
                    totalPedido += bebida.getPrice();
                }
            } catch (Exception e) {
                System.out.println("Error al buscar bebida: " + itemName + " - " + e.getMessage());
            }
        }
        System.out.println("SERVICE: Total del pedido: " + totalPedido);

        return totalPedido; // <-- 2. Devuelve el total
    }

    @Override
    public PedidoDTO getPedidoById(Long id) {
        System.out.println("SERVICE: Buscando pedido con ID: " + id);

        if (id == 1L) {
            PedidoDTO pedido = new PedidoDTO();
            pedido.setCustomerName("Valeria (desde el servicio real)");
            return pedido;
        } else {
            throw new PedidoNotFoundException("Pedido no encontrado con ID: " + id);
        }
    }
}