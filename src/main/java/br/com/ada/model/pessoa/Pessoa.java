package br.com.ada.model.pessoa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Pessoa implements Serializable {

    public static final long serialVersionUID = 1l;

    private UUID Id;
    private String Nome;
    private LocalDate DataNascimento;
    private String Cpf;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        this.Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {

        this.DataNascimento = dataNascimento;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        this.Cpf = cpf;
    }
}
