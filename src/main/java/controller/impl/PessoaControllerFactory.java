package controller.impl;

public class PessoaControllerFactory {

    public PessoaController criar(PessoaArmazenamentoTipo tipo) {
        if (tipo == PessoaArmazenamentoTipo.VOLATIL) {
            return new PessoaArmazenamentoVolatilController();
        } else {
            throw new RuntimeException("Nenhuma implementação disponível");
        }
    }

}
