package co.edu.uptc.virtualcoffee.api_pedidos.service;

import co.edu.uptc.virtualcoffee.api_pedidos.dto.BebidaDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.exception.PedidoNotFoundException;
// --- 👇 1. IMPORTA EL LOGGER (SLF4J) ---
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PedidoServiceImpl implements PedidoService {

    // --- 👇 2. CREA LA INSTANCIA DEL LOGGER ---
    private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

    public final RestTemplate restTemplate;
    private final String bebidasApiUrl = "http://localhost:8000/menu/";

    @Autowired
    public PedidoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public double savePedido(PedidoDTO pedidoDTO) {
        double totalPedido = 0.0;
        for (String itemName : pedidoDTO.getItems()) {
            String url = bebidasApiUrl + itemName;
            try {
                BebidaDTO bebida = restTemplate.getForObject(url, BebidaDTO.class);
                if (bebida != null) {
                    totalPedido += bebida.getPrice();
                }
            } catch (Exception e) {
                // --- 👇 3. REEMPLAZA System.out CON log.error ---
                log.error("Error al buscar bebida: {} - {}", itemName, e.getMessage());
            }
        }

        // --- 👇 3. REEMPLAZA System.out CON log.info ---
        log.info("SERVICE: Total del pedido: {}", totalPedido);
        log.info("SERVICE: Guardando pedido para: {}", pedidoDTO.getCustomerName());

        return totalPedido;
    }

    @Override
    public PedidoDTO getPedidoById(Long id) {
        // --- 👇 3. REEMPLAZA System.out CON log.info ---
        log.info("SERVICE: Buscando pedido con ID: {}", id);

        if (id == 1L) {
            PedidoDTO pedido = new PedidoDTO();
            pedido.setCustomerName("Valeria (desde el servicio real)");
            return pedido;
        } else {
            throw new PedidoNotFoundException("Pedido no encontrado con ID: " + id);
        }
    }
    @Override
    public java.util.List<PedidoDTO> getAllPedidos() {
        // Aquí iría la lógica para obtener todos los pedidos desde la base de datos
        log.info("SERVICE: Obteniendo todos los pedidos");
        return new java.util.ArrayList<>(); // Retorna una lista vacía por ahora
    }
}