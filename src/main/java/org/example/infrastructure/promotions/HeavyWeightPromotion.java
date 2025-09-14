package org.example.infrastructure.promotions;

import org.example.logistica.entities.Entrega;
import org.example.logistica.interfaces.PromocaoFrete;

public class PromocaoPesoAlto implements PromocaoFrete {
    private static final double PESO_MINIMO = 10.0;
    private static final double DESCONTO_PERCENTUAL = 0.10;

    @Override
    public boolean seAplica(Entrega entrega) {
        return entrega.getPeso() > PESO_MINIMO;
    }

    @Override
    public double aplicarDesconto(double valorOriginal, Entrega entrega) {
        return valorOriginal * (1 - DESCONTO_PERCENTUAL);
    }

    @Override
    public String getDescricao() {
        return "10% de desconto para entregas acima de " + PESO_MINIMO + "kg";
    }
}
