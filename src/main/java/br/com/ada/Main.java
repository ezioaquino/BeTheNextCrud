package br.com.ada;
import controller.impl.PessoaArmazenamentoTipo;
import controller.impl.PessoaControllerFactory;
import view.PessoaView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String tipoArmazenamento = args[0];
        PessoaArmazenamentoTipo tipo = PessoaArmazenamentoTipo.valueOf(tipoArmazenamento);

        PessoaControllerFactory factory = new PessoaControllerFactory();

        PessoaView view = new PessoaView(
                factory.criar(tipo),
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }

}

}