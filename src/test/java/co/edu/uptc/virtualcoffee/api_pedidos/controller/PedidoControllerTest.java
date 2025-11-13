package co.edu.uptc.virtualcoffee.api_pedidos.controller;

import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import co.edu.uptc.virtualcoffee.api_pedidos.service.PedidoService;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import co.edu.uptc.virtualcoffee.api_pedidos.exception.PedidoNotFoundException;

/**
 * Prueba unitaria para el PedidoController (TDD).
 * * @WebMvcTest enfoca la prueba solo en la capa web (el Controller),
 * sin levantar todo el contexto de Spring.
 */
@WebMvcTest(PedidoController.class) //
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void testCrearPedido_DebeDevolver201Creado() throws Exception {

        String pedidoJson = """
            {
                "customerName": "Valeria",
                "items": ["Latte", "Espresso"]
            }
            """;

        mockMvc.perform(
                        post("/pedidos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(pedidoJson)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testGetPedidoPorId_DebeDevolver404SiNoExiste() throws Exception {
        when(pedidoService.getPedidoById(anyLong()))
                .thenThrow(new PedidoNotFoundException("Test: Pedido no encontrado"));

        mockMvc.perform(
                        get("/pedidos/99") // ID 99 (no existe)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound()); // Esperamos 404
    }

    @Test
    void testGetPedidoPorId_DebeDevolverPedidoSiExiste() throws Exception {

        long idExistente = 1L;
        PedidoDTO pedidoExistente = new PedidoDTO();
        pedidoExistente.setCustomerName("Valeria");
        when(pedidoService.getPedidoById(1L)).thenReturn(pedidoExistente);

        mockMvc.perform(
                        get("/pedidos/1") // ID 1 (existe)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Valeria"));
    }
}
