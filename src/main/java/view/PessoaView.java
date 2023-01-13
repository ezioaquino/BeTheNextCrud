package view;

import controller.impl.PessoaController;
import model.Pessoa;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PessoaView {

    private PessoaController controller;
    private Scanner scanner = new Scanner(System.in);


    public void listar(UUID id){
        Pessoa pessoa = controller.ler(id);
        exibirPessoa(pessoa);

    }

    public void exibirPessoa(Pessoa pessoa){
        System.out.println(
                "Pessoa - Nome: " +pessoa.getNome()
                        + ", nascida em " + pessoa.getDataNascimento()
                        + ", registra com o cpf: " + pessoa.getCpf()
        );
    }

    public void cadastrar(){
        Pessoa pessoa = new Pessoa();

        System.out.println("Informe o nome: ");
        String nome = scanner.nextLine();
        pessoa.setNome(nome);

        System.out.println("Informe a data de nasciemnto(dd/mm/aa):");
        String dataNascimento = scanner.nextLine();
        pessoa.setDataNascimento(LocalDate.parse(
                dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yy"))
        );

        System.out.println("Informe o CPF:");
        String cpf = scanner.nextLine();
        pessoa.setCpf(cpf);

        controller.cadastrar(pessoa);
    }

    public void atualizar(Pessoa pessoa){
        exibirPessoa(pessoa);

        System.out.println("Informe o novo nome:");
        String nome = scanner.nextLine();
        pessoa.setNome(nome);

        System.out.println("Informe a data de nasciemnto(dd/mm/aa):");
        String dataNascimento = scanner.nextLine();
        pessoa.setDataNascimento(LocalDate.parse(
                dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yy"))
        );

        System.out.println("Informe o CPF:");
        String cpf = scanner.nextLine();
        pessoa.setCpf(cpf);

        controller.update(pessoa.getId(), pessoa);
    }

    public void apagar(UUID id){

        Pessoa pessoa = controller.delete(id);
        System.out.println("Pessoa apagada foi:");
        exibirPessoa(pessoa);

    }

    public void apagar(){
        listar();
        System.out.println("Inform o npumero do cliente que deseja apagar:");
        Integer numero = scanner.nextInt();
        Pessoa pessoa = controller.listar().get(numero - 1);
        apagar(pessoa.getId());
    }

    public void atualizar(){
        listar();
        System.out.println("Informe o número do cliente que deseja atualizar:");
        Integer numeroCliente = scanner.nextInt();
        Pessoa pessoa = controller.listar().get(numeroCliente -1);
        atualizar(pessoa);

    }

    public void listar(){
        List<Pessoa> pessoas = controller.listar();
        for (int index=0; index <pessoas.size(); index++ ){
            System.out.println((index +1) + " - ");
            exibirPessoa(pessoas.get(index));
        }
    }

    public void exibirOpcoes(){
        System.out.println("norme a opção desejada:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Sair");
        Integer opcao = scanner.nextInt();
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
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida");
        }
    }

}
