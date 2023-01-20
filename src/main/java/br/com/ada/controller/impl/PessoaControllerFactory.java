package br.com.ada.controller.impl;

import br.com.ada.model.pessoa.dao.impl.PessoaBinaryDAO;
import br.com.ada.model.pessoa.dao.impl.PessoaXMLDAO;

public class PessoaControllerFactory {

    public PessoaController criar(PessoaArmazenamentoTipo tipo) {
        if (tipo == PessoaArmazenamentoTipo.VOLATIL) {
            return new PessoaArmazenamentoVolatilController();
        } else if (tipo == PessoaArmazenamentoTipo.DEFINITIVO) {
            return new PessoaArmazenamentoDefinitivoController(
                    new PessoaXMLDAO()
            );
        } else {
            throw new RuntimeException("Nenhuma implementação disponível");
        }
    }

}
