package br.com.ada;
import br.com.ada.controller.impl.PessoaArmazenamentoTipo;
import br.com.ada.controller.impl.PessoaControllerFactory;
import br.com.ada.view.PessoaView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final String ARQUIVO_PROPRIEDADES = "crud.properties";

    private static final String CONTROLLER_TIPO = "pessoa.controller.tipo";

    public static void main(String[] args) throws IOException {


        Properties properties = new Properties();
        properties.load(new FileInputStream(ARQUIVO_PROPRIEDADES));


        String tipoArmazenamento = properties.getProperty(CONTROLLER_TIPO);
        PessoaArmazenamentoTipo tipo =
                PessoaArmazenamentoTipo.valueOf(tipoArmazenamento);

        PessoaControllerFactory factory = new PessoaControllerFactory();

        PessoaView view = new PessoaView(
                factory.criar(tipo),
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }

}

