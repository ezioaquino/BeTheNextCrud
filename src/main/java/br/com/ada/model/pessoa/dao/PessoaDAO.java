package br.com.ada.model.pessoa.dao;

import br.com.ada.model.pessoa.Pessoa;

import java.util.List;
import java.util.UUID;

public interface PessoaDAO {

    void cadastrar(Pessoa pessoa);

    List<Pessoa> listar();

    Pessoa buscar(UUID id);

    void atualizar(UUID id, Pessoa pessoa);

    Pessoa apagar(UUID id);
}
