package controller.impl;

import model.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PessoaArmazenamentoVolatilController implements PessoaController {

    private List<Pessoa> pessoas = new ArrayList<>();
    private PessoaController controller;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void cadastrar(Pessoa pessoa) {
        pessoa.setId(UUID.randomUUID());

        pessoas.add(pessoa);
    }

    @Override
    public Pessoa ler(UUID id) {

        Pessoa encontrada = null;
        for (Pessoa pessoa: pessoas){
            if(pessoa.getId().equals(id)){
                encontrada = pessoa;
            }
        }
        return encontrada;
    }

    @Override
    public List<Pessoa> listar() {

        return pessoas;
    }

    @Override
    public void update(UUID id, Pessoa pessoa) {

        for (int index = 0; index < pessoas.size(); index++) {
            Pessoa antiga = pessoas.get(index);
            if (antiga.getId().equals(id)){
                pessoas.set(index, pessoa);
            }
        }

    }

    @Override
    public Pessoa delete(UUID id) {

        Iterator<Pessoa> iterator = pessoas.iterator();
        Pessoa apagada = null;
        while (iterator.hasNext()){
            Pessoa pessoa = iterator.next();
            if (pessoa.getId().equals(id)){
                apagada = pessoa;
                iterator.remove();
            }
        }
        return apagada;
    }




}
