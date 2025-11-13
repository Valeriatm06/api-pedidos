package co.edu.uptc.virtualcoffee.api_pedidos.service;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.BebidaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Prueba unitaria para PedidoServiceImpl
 */
@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Test
    void testSavePedido_DebeLlamarApiBebidas() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setCustomerName("Test");
        pedidoDTO.setItems(List.of("Latte"));
        String urlEsperada = "http://localhost:8000/menu/Latte";

        pedidoService.savePedido(pedidoDTO);
        verify(restTemplate).getForObject(urlEsperada, BebidaDTO.class);
    }

    @Test
    void testSavePedido_DebeCalcularTotalCorrectamente() {
        BebidaDTO bebida1 = new BebidaDTO();
        bebida1.setPrice(4.5);

        BebidaDTO bebida2 = new BebidaDTO();
        bebida2.setPrice(3.0);

        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setItems(List.of("Latte", "Espresso"));

        when(restTemplate.getForObject("http://localhost:8000/menu/Latte", BebidaDTO.class))
                .thenReturn(bebida1);
        when(restTemplate.getForObject("http://localhost:8000/menu/Espresso", BebidaDTO.class))
                .thenReturn(bebida2);

        double totalCalculado = pedidoService.savePedido(pedidoDTO);

        assertEquals(7.5, totalCalculado, "La suma de los precios debe ser 7.5");
    }
}