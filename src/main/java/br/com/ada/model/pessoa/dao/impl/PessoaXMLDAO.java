package br.com.ada.model.pessoa.dao.impl;

import br.com.ada.controller.impl.exception.DAOException;
import br.com.ada.model.pessoa.Pessoa;
import br.com.ada.model.pessoa.dao.PessoaDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PessoaXMLDAO implements PessoaDAO {

    private String diretorio = "database/xml/pessoas";

    @Override
    public void cadastrar(Pessoa pessoa) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document = builder.newDocument();
            Element elementPessoa = document.createElement("pessoa");
            document.appendChild(elementPessoa);

            Element elementId = document.createElement("id");
            elementId.setTextContent(pessoa.getId().toString());
            elementPessoa.appendChild(elementId);

            Element elementNome = document.createElement("nome");
            elementNome.setTextContent(pessoa.getNome());
            elementPessoa.appendChild(elementNome);

            Element elementDataNascimento = document.createElement("data_nascimento");
            elementDataNascimento.setTextContent(pessoa.getDataNascimento().toString());
            elementPessoa.appendChild(elementDataNascimento);

            Element elementCpf = document.createElement("cpf");
            elementCpf.setTextContent(pessoa.getCpf());
            elementPessoa.appendChild(elementCpf);

            File arquivo = new File(diretorio, pessoa.getId().toString() + ".xml");
            try (FileOutputStream output = new FileOutputStream(arquivo)) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(output);

                transformer.transform(source, result);
            } catch (IOException | TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pessoa> listar() {
        FilenameFilter filter = (dir, nome) -> nome.endsWith(".xml");
        File diretoRaiz = new File(diretorio);
        List<Pessoa> pessoas = new ArrayList<>();
        for (File arquivo : diretoRaiz.listFiles(filter)) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(arquivo);

                Element elementPessoa = document.getDocumentElement();
                Node elementId = elementPessoa.getElementsByTagName("id").item(0);
                Node elementNome = elementPessoa.getElementsByTagName("nome").item(0);
                Node elementDataNascimento = elementPessoa.getElementsByTagName("data_nascimento").item(0);
                Node elementCpf = elementPessoa.getElementsByTagName("cpf").item(0);

                Pessoa pessoa = new Pessoa();
                pessoa.setId(UUID.fromString(elementId.getTextContent()));
                pessoa.setNome(elementNome.getTextContent());
                pessoa.setDataNascimento(LocalDate.parse(elementDataNascimento.getTextContent()));
                pessoa.setCpf(elementCpf.getTextContent());

                pessoas.add(pessoa);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pessoas;
    }

    @Override
    public Pessoa buscar(UUID id) {

        File arquivo = new File(diretorio, id.toString() + ".xml");


            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(arquivo);

                Element elementPessoa = document.getDocumentElement();
                Node elementId = elementPessoa.getElementsByTagName("id").item(0);
                Node elementNome = elementPessoa.getElementsByTagName("nome").item(0);
                Node elementDataNascimento = elementPessoa.getElementsByTagName("data_nascimento").item(0);
                Node elementCpf = elementPessoa.getElementsByTagName("cpf").item(0);

                Pessoa pessoa = new Pessoa();
                pessoa.setId(UUID.fromString(elementId.getTextContent()));
                pessoa.setNome(elementNome.getTextContent());
                pessoa.setDataNascimento(LocalDate.parse(elementDataNascimento.getTextContent()));
                pessoa.setCpf(elementCpf.getTextContent());

                return pessoa;
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }


    }

    @Override
    public void atualizar(UUID id, Pessoa pessoa) {
        File arquivo = new File(diretorio, id.toString() + ".xml");
        arquivo.delete();

        pessoa.setId(id);

        cadastrar(pessoa);
    }

    @Override
    public Pessoa apagar(UUID id) {
        Pessoa pessoa = buscar(id);
        if (pessoa != null){

            File arquivo = new File(diretorio, id.toString() + ".xml");
            arquivo.delete();

        }
        return pessoa;
    }
}
