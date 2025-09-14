package org.example.application.common;

import org.example.logistica.exceptions.EntregaException;

public class ResultadoOperacao<T> {
    private final T valor;
    private final String erro;
    private final boolean sucesso;

    private ResultadoOperacao(T valor, String erro, boolean sucesso) {
        this.valor = valor;
        this.erro = erro;
        this.sucesso = sucesso;
    }

    public static <T> ResultadoOperacao<T> sucesso(T valor) {
        return new ResultadoOperacao<>(valor, null, true);
    }

    public static <T> ResultadoOperacao<T> erro(String mensagem) {
        return new ResultadoOperacao<>(null, mensagem, false);
    }

    public boolean isSucesso() { return sucesso; }
    public T getValor() { return valor; }
    public String getErro() { return erro; }

    public T getValorOrThrow() {
        if (!sucesso) {
            throw new EntregaException(erro);
        }
        return valor;
    }
}

