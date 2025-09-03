package com.proyecto.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.proyecto.models.Pedido;
import com.proyecto.models.PedidoDetalle;
import com.proyecto.services.PedidoDetalleService;
import com.proyecto.services.PedidoService;


@Controller
@RequestMapping("/admin/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    @GetMapping("/listar")
    public String listar(@RequestParam(value = "keyword", required = false) String keyword,
                         Model model) {

        List<Pedido> pedidos;

        if (keyword != null && !keyword.isBlank()) {
            try {
                Integer id = Integer.valueOf(keyword);
                Pedido pedidoPorId = pedidoService.buscarPorId(id).orElse(null);

                if (pedidoPorId != null) {
                    pedidos = List.of(pedidoPorId);
                } else {
                    pedidos = List.of();
                    model.addAttribute("mensaje", "No se encontró el pedido con ID " + id);
                }

            } catch (NumberFormatException e) {
                pedidos = pedidoService.buscarPorCliente(keyword);
                if (pedidos.isEmpty()) {
                    model.addAttribute("mensaje", "No se encontraron pedidos para el cliente: " + keyword);
                }
            }

        } else {
            pedidos = pedidoService.listar();
        }

        model.addAttribute("pedidos", pedidos);
        return "admin/pedidos/listar";
    }

    @PostMapping("/{id}/estado")
    public String actualizarEstado(@PathVariable Integer id,
                                   @RequestParam("estado") String estado,
                                   RedirectAttributes redirectAttrs) {
        return pedidoService.buscarPorId(id).map(pedido -> {
            pedido.setEstado(estado);
            pedidoService.guardar(pedido);
            redirectAttrs.addFlashAttribute("msg", "Estado del pedido actualizado a " + estado);
            return "redirect:/admin/pedidos/listar";
        }).orElseGet(() -> {
            redirectAttrs.addFlashAttribute("error", "Pedido no encontrado.");
            return "redirect:/admin/pedidos/listar";
        });
    }

    @GetMapping("/enviados")
    public String listarEnviados(Model model) {
        var pedidos = pedidoService.listarPorEstado("Enviado");
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("filtroEstado", "Enviado");
        return "admin/pedidos/enviados";
    }

    @GetMapping("/detalles/{id}")
    public String verDetallesPedido(@PathVariable int id, Model model) {

        Optional<Pedido> pedido = pedidoService.buscarPorId(id);

        if (pedido.isEmpty()) {
            model.addAttribute("mensaje", "No se encontró el pedido con ID " + id);
            return "admin/pedidos/listar";
        }

        List<PedidoDetalle> pedidoDetalle = pedidoDetalleService.listarPorPedidoId(id);

        model.addAttribute("pedido", pedido.get());
        model.addAttribute("pedidoDetalle", pedidoDetalle);

        return "admin/pedidos/detalles";
    }

    @GetMapping("/imprimir/{id}")
    public String pedidoImprimir(@PathVariable int id, Model model) {
        Optional<Pedido> pedido = pedidoService.consultarunpedido(id);

        if (pedido.isEmpty()) {
            model.addAttribute("mensaje", "No se encontró el pedido con ID " + id);
            return "admin/pedidos/listar";
        }

        List<PedidoDetalle> pedidoDetalle = pedidoDetalleService.listarPorPedidoId(id);

        model.addAttribute("pedido", pedido.get());
        model.addAttribute("pedidoDetalle", pedidoDetalle);

        return "admin/pedidos/imprimir";
    }

    
}



