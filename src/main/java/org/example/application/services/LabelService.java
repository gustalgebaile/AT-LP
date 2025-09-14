package org.example.application.services;

import org.example.infrastructure.frete.calculadoras.FreteEconomico;
import org.example.infrastructure.frete.calculadoras.FreteExpresso;
import org.example.infrastructure.frete.calculadoras.FretePadrao;
import org.example.logistica.entities.Entrega;
import org.example.logistica.entities.TipoFrete;
import org.example.logistica.interfaces.CalculadoraFrete;

import java.util.Map;

public class EtiquetaService {
    private final Map<TipoFrete, CalculadoraFrete> calculadoras;

    public EtiquetaService() {
        this.calculadoras = Map.of(
                TipoFrete.EXPRESSO, new FreteExpresso(),
                TipoFrete.PADRAO, new FretePadrao(),
                TipoFrete.ECONOMICO, new FreteEconomico()
        );
    }

    public String gerarEtiqueta(Entrega entrega) {
        double valorFrete = calcularFrete(entrega);
        return String.format("Destinatário: %s%nEndereço: %s%nValor do Frete: R$%.2f",
                entrega.getDestinatario(), entrega.getEndereco(), valorFrete);
    }

    public String gerarResumoPedido(Entrega entrega) {
        double valorFrete = calcularFrete(entrega);
        return String.format("Pedido para %s com frete tipo %s no valor de R$%.2f",
                entrega.getDestinatario(), entrega.getTipoFrete().getCodigo(), valorFrete);
    }

    private double calcularFrete(Entrega entrega) {
        CalculadoraFrete calculadora = calculadoras.get(entrega.getTipoFrete());
        if (calculadora == null) {
            throw new IllegalStateException("Calculadora não encontrada para tipo: " + entrega.getTipoFrete());
        }
        return calculadora.calcular(entrega.getPeso());
    }

    public boolean isFreteGratis(Entrega entrega) {
        return entrega.getTipoFrete() == TipoFrete.ECONOMICO && entrega.getPeso() < 2;
    }
}

