package co.edu.uptc.virtualcoffee.api_pedidos.controller;

import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoResponseDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.dto.PedidoDTO;
import co.edu.uptc.virtualcoffee.api_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO createPedido(@RequestBody PedidoDTO pedidoDTO) {

        double total = pedidoService.savePedido(pedidoDTO);

        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setMessage("Pedido creado exitosamente");
        response.setTotal(total);
        return response;
    }

    @GetMapping("/{id}")
    public PedidoDTO getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id);
    }
}