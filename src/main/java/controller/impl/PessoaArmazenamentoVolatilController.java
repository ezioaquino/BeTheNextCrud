package controller.impl;

import controller.impl.exception.PessoaNaoEncontrada;
import model.Pessoa;

import java.util.*;

public class PessoaArmazenamentoVolatilController implements PessoaController {

    private Scanner scanner = new Scanner(System.in);
    private Map<UUID, Pessoa> pessoas = new HashMap<>();

    @Override
    public void cadastrar(Pessoa pessoa) {
        pessoa.setId(UUID.randomUUID());

        pessoas.put(pessoa.getId(), pessoa);
    }

    @Override
    public Pessoa ler(UUID id) {

        Pessoa encontrada = pessoas.get(id);
        if (encontrada == null){
            throw new PessoaNaoEncontrada();
        }
        return encontrada;
    }

    @Override
    public List<Pessoa> listar() {

        return new ArrayList<>(pessoas.values());
    }

    @Override
    public void update(UUID id, Pessoa pessoa) {
        if (pessoas.containsKey(id)) {
            pessoas.put(id, pessoa);
        } else {
            throw new PessoaNaoEncontrada();
        }
    }

    @Override
    public Pessoa delete(UUID id) {

        Pessoa apagada = pessoas.remove(id);
        if (apagada == null) {
            throw new PessoaNaoEncontrada();
        }
        return apagada;
    }




}
