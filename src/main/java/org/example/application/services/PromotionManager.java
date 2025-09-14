package org.example.application.services;

import org.example.logistica.interfaces.PromocaoFrete;
import org.example.infrastructure.promotions.PromocaoFreteGratis;
import org.example.infrastructure.promotions.PromocaoPesoAlto;
import org.example.logistica.entities.Entrega;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorPromocoes {
    private final List<PromocaoFrete> promocoes;

    public GerenciadorPromocoes() {
        this.promocoes = new ArrayList<>();
        promocoes.add(new PromocaoFreteGratis());
        promocoes.add(new PromocaoPesoAlto());
    }

    public void adicionarPromocao(PromocaoFrete promocao) {
        promocoes.add(promocao);
    }

    public double calcularComPromocoes(double valorOriginal, Entrega entrega) {
        return promocoes.stream()
                .filter(promocao -> promocao.seAplica(entrega))
                .findFirst()
                .map(promocao -> promocao.aplicarDesconto(valorOriginal, entrega))
                .orElse(valorOriginal);
    }

    public List<String> getPromocoesAplicaveis(Entrega entrega) {
        return promocoes.stream()
                .filter(promocao -> promocao.seAplica(entrega))
                .map(PromocaoFrete::getDescricao)
                .collect(Collectors.toList());
    }
}

