package com.proyecto.services;

import java.util.List;

import com.proyecto.models.PedidoDetalle;

public interface PedidoDetalleService {
	List<PedidoDetalle> guardarTodos(List<PedidoDetalle> detalles);

	List<PedidoDetalle> listarPorPedidoId(int id);

	List<PedidoDetalle> listarproductos(int id);

	

}
