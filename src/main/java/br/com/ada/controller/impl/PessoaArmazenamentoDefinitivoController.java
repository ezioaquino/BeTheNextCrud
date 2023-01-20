package br.com.ada.controller.impl;

import br.com.ada.controller.impl.exception.PessoaNaoEncontrada;
import br.com.ada.model.pessoa.Pessoa;
import br.com.ada.model.pessoa.dao.PessoaDAO;

import java.util.List;
import java.util.UUID;

public class PessoaArmazenamentoDefinitivoController implements PessoaController{

    //DAO - Data access Object
    PessoaDAO pessoaDAO;

    public PessoaArmazenamentoDefinitivoController(
            PessoaDAO pessoaDAO
    ){
        this.pessoaDAO = pessoaDAO;
    }
    @Override
    public void cadastrar(Pessoa pessoa) {

        pessoa.setId(UUID.randomUUID());
        pessoaDAO.cadastrar(pessoa);

    }

    @Override
    public Pessoa ler(UUID id) {

        return pessoaDAO.buscar(id);
    }

    @Override
    public List<Pessoa> listar() {

        return pessoaDAO.listar();
    }

    @Override
    public void update(UUID id, Pessoa pessoa) {
        pessoaDAO.atualizar(id, pessoa);

    }

    @Override
    public Pessoa delete(UUID id) {

        return pessoaDAO.apagar(id);
    }
}
