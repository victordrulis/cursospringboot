package com.victor.cursospring.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.victor.cursospring.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer numeroDeParcelas;
    
    public PagamentoComCartao () {}

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,
            Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    /**
     * @return the numeroDeParcelas
     */
    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    /**
     * @param numeroDeParcelas the numeroDeParcelas to set
     */
    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
    
}