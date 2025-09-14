package org.example.application.validators;

import org.example.application.common.ResultadoOperacao;
import org.example.logistica.entities.Entrega;
import org.example.logistica.entities.TipoFrete;

public class ValidadorEntrega {

    public static ResultadoOperacao<Entrega> validarECriar(String endereco, double peso,
                                                           String tipoFreteStr, String destinatario) {
        try {
            // Validações específicas
            if (endereco == null || endereco.trim().isEmpty()) {
                return ResultadoOperacao.erro("Endereço é obrigatório");
            }

            if (peso < 0) {
                return ResultadoOperacao.erro("Peso deve ser positivo");
            }

            if (peso > 1000) {
                return ResultadoOperacao.erro("Peso excede limite máximo de 1000kg");
            }

            if (destinatario == null || destinatario.trim().isEmpty()) {
                return ResultadoOperacao.erro("Destinatário é obrigatório");
            }

            TipoFrete tipoFrete;
            try {
                tipoFrete = TipoFrete.fromCodigo(tipoFreteStr);
            } catch (IllegalArgumentException e) {
                return ResultadoOperacao.erro("Tipo de frete inválido: " + tipoFreteStr);
            }

            Entrega entrega = new Entrega(endereco.trim(), peso, tipoFrete, destinatario.trim());
            return ResultadoOperacao.sucesso(entrega);

        } catch (Exception e) {
            return ResultadoOperacao.erro("Erro inesperado na validação: " + e.getMessage());
        }
    }
}

