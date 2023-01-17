package br.com.ada.view;

import br.com.ada.controller.impl.PessoaController;
import br.com.ada.controller.impl.exception.PessoaNaoEncontrada;
import br.com.ada.model.pessoa.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PessoaView {

    private PessoaController controller;
    private Scanner scanner;

    public PessoaView(
            PessoaController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }



    public void cadastrar(){
        Pessoa pessoa = new Pessoa();

        System.out.println("Informe o nome: ");
        String nome = scanner.next();
        pessoa.setNome(nome);

        System.out.println("Informe a data de nasciemnto(dd/mm/aaaa):");
        String dataNascimento = scanner.next();
        pessoa.setDataNascimento(LocalDate.parse(
                dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );

        System.out.println("Informe o CPF:");
        String cpf = scanner.next();
        pessoa.setCpf(cpf);

        controller.cadastrar(pessoa);
    }

    public void atualizar(){
        listar();
        System.out.println("Informe o número do cliente que deseja atualizar:");
        Integer numeroCliente = scanner.nextInt();
        Pessoa pessoa = controller.listar().get(numeroCliente -1);
        atualizar(pessoa);

    }

    public void atualizar(Pessoa pessoa){
        exibirPessoa(pessoa);

        System.out.println("Informe o novo nome:");
        String nome = scanner.next();
        pessoa.setNome(nome);

        System.out.println("Informe a data de nasciemnto(dd/mm/aaaa):");
        String dataNascimento = scanner.next();
        pessoa.setDataNascimento(LocalDate.parse(
                dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );

        System.out.println("Informe o CPF:");
        String cpf = scanner.next();
        pessoa.setCpf(cpf);

        try {

            controller.update(pessoa.getId(), pessoa);
        }catch (PessoaNaoEncontrada ex){
            System.out.println("Pessoa informada não existe na base. Tente novamente.");
        }
    }

    public void apagar(){
        listar();
        System.out.println("Inform o npumero do cliente que deseja apagar:");
        Integer numero = scanner.nextInt();
        Pessoa pessoa = controller.listar().get(numero - 1);
        apagar(pessoa.getId());
    }

    public void apagar(UUID id){

        try{

            Pessoa pessoa = controller.delete(id);
            System.out.println("Pessoa apagada foi:");
            exibirPessoa(pessoa);
        } catch (PessoaNaoEncontrada ex){
            System.out.println("Pessoa não foi apagada pois não foi localizada. Tente novamente!");
        }

    }

    public void listar(UUID id){
        Pessoa pessoa = controller.ler(id);
        exibirPessoa(pessoa);

    }

    public void listar(){
        List<Pessoa> pessoas = controller.listar();

        System.out.println("| Número | Nome        | Data nasc.       | CPF      |");

        for (int index=0; index <pessoas.size(); index++ ){
            System.out.println((index +1) + " - ");
            exibirPessoa(pessoas.get(index));
        }
    }

    public void exibirPessoa(Pessoa pessoa){

        System.out.print("| ");
        System.out.print(pessoa.getNome());
        System.out.print("    | ");
        System.out.print(pessoa.getDataNascimento());
        System.out.print("    |  ");
        System.out.print(pessoa.getCpf());
        System.out.println("    |");

    }

    public void exibirOpcoes(){
        System.out.println("norme a opção desejada:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Sair");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao){
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                atualizar();
                break;
            case 4:
                apagar();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida");
        }
        exibirOpcoes();
    }

}
