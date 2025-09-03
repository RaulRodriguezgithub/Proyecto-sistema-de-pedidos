package com.proyecto.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();

    private double total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoDetalle> detalles;

    // NUEVO: estado del pedido
    private String estado = "Pendiente"; // Por defecto cuando se crea

    // getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<PedidoDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<PedidoDetalle> detalles) { this.detalles = detalles; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

