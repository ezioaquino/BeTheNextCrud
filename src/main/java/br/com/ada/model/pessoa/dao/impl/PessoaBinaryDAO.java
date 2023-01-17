package br.com.ada.model.pessoa.dao.impl;

import br.com.ada.model.pessoa.Pessoa;
import br.com.ada.model.pessoa.dao.PessoaDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class PessoaBinaryDAO implements PessoaDAO {

    String diretorioRaiz = "database/binario";
    String diretorioPessoa = diretorioRaiz + "/pessoas";

    @Override
    public void cadastrar(Pessoa pessoa) {
        Path diretorio = Paths.get(diretorioPessoa);
        if(!diretorio.toFile().exists()){
            throw new RuntimeException("Diretório não disponível");
        }
        File file = new File(
                diretorio.toAbsolutePath().toString(),
                pessoa.getId().toString() + ".dat"
        );
        try {

            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
            objOutputStream.writeObject(pessoa);
            objOutputStream.close();
            outputStream.close();
        } catch (IOException ex){

        }
    }

    @Override
    public List<Pessoa> listar() {
        return null;
    }

    @Override
    public Pessoa buscar(UUID id) {
        return null;
    }

    @Override
    public void atualizar(UUID id, Pessoa pessoa) {

    }

    @Override
    public Pessoa apagar(UUID id) {
        return null;
    }
}
