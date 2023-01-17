package br.com.ada.controller.impl;


import br.com.ada.model.pessoa.Pessoa;

import java.util.List;
import java.util.UUID;

//CRUD - create, read, update and delete
public interface PessoaController {

     void cadastrar(Pessoa pessoa);

     Pessoa ler(UUID id);

     List<Pessoa> listar();

     void update(UUID id, Pessoa pessoa);


     Pessoa delete(UUID id);
}
