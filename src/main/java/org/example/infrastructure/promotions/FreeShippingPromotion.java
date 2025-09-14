package org.example.infrastructure.promocoes;

import org.example.logistica.entities.Entrega;
import org.example.logistica.entities.TipoFrete;
import org.example.logistica.interfaces.PromocaoFrete;

public class PromocaoFreteGratis implements PromocaoFrete {
    @Override
    public boolean seAplica(Entrega entrega) {
        return entrega.getTipoFrete() == TipoFrete.ECONOMICO && entrega.getPeso() < 2;
    }

    @Override
    public double aplicarDesconto(double valorOriginal, Entrega entrega) {
        return 0.0;
    }

    @Override
    public String getDescricao() {
        return "Frete grátis para entregas econômicas até 2kg";
    }
}
