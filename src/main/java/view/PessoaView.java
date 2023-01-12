package view;

import controller.PessoaController;
import model.Pessoa;

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

    public void update(UUID id){
        Pessoa pessoa = controller.ler(id);
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

        controller.update(id, pessoa);
    }

    public void delete(UUID id){

        Pessoa pessoa = controller.delete(id);
        System.out.println("Pessoa apagada foi:");
        exibirPessoa(pessoa);

    }

    public void listar(){
        List<Pessoa> pessoas = controller.listar();
        for (int index=0; index <pessoas.size(); index++ ){
            System.out.println((index +1) + " - ");
            exibirPessoa(pessoas.get(index));
        }
    }
}
