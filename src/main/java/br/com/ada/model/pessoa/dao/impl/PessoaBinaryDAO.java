package br.com.ada.model.pessoa.dao.impl;

import br.com.ada.controller.impl.exception.DAOException;
import br.com.ada.model.pessoa.Pessoa;
import br.com.ada.model.pessoa.dao.PessoaDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objOutputStream =
                     new ObjectOutputStream(outputStream);) {


            objOutputStream.writeObject(pessoa);
            objOutputStream.flush();
        } catch (IOException ex){
            throw new DAOException("Falha ao trabalhar com arquivos.", ex);
        }
    }

    @Override
    public List<Pessoa> listar() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".dat");

        List<Pessoa> pessoas = new ArrayList<>();

        File diretorio = new File(diretorioPessoa);
        for (File arquivo: diretorio.listFiles(filter)) {
            try (
                    FileInputStream fileInputStream = new FileInputStream(arquivo);
                    ObjectInputStream objectInputStream =
                            new ObjectInputStream(fileInputStream);
                    ){


                Object object = objectInputStream.readObject();
                if(object instanceof Pessoa){
                    Pessoa pessoa = (Pessoa) object;
                    pessoas.add(pessoa);
                }
            }catch (IOException | ClassNotFoundException ex){
                throw new DAOException("Falha na leitura do arquivo" + arquivo.getAbsolutePath(), ex);
            }

        }
        return pessoas;
    }

    @Override
    public Pessoa buscar(UUID id) {

        File file = new File(diretorioPessoa, id.toString()+".dat");
        Pessoa pessoa = null;
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream =
                        new ObjectInputStream(fileInputStream);

        ) {
            Object object = objectInputStream.readObject();
            if (object instanceof Pessoa) {
                return  (Pessoa) object;
            }else {
                return null;
            }
        }catch (IOException | ClassNotFoundException ex){
            throw new DAOException("Falha na leitura do arquivo " + file.getAbsolutePath(), ex);
        }
    }

    @Override
    public void atualizar(UUID id, Pessoa pessoa) {
        File file = new File(diretorioPessoa, id.toString()+".dat");
        Path diretorio = Paths.get(diretorioPessoa);

        try (FileOutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objOutputStream =
                     new ObjectOutputStream(outputStream);) {


            objOutputStream.writeObject(pessoa);
            objOutputStream.flush();
        } catch (IOException ex){
            throw new DAOException("Falha ao trabalhar com arquivos.", ex);
        }
    }

    @Override
    public Pessoa apagar(UUID id) {
        Pessoa pessoa = buscar(id);
        if (pessoa != null){
            File arquivo = new File(diretorioPessoa, id.toString() + ".dat");
            arquivo.delete();
        }
        return pessoa;
    }
}
