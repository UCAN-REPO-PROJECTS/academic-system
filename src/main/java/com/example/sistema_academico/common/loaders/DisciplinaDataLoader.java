package com.example.sistema_academico.common.loaders;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.sistema_academico.common.utils.FileDataLoader;
import com.example.sistema_academico.entities.Disciplina;
import com.example.sistema_academico.repositories.DisciplinaRepository;

import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para carregar dados de roles a partir de um arquivo CSV.
 */
@Component
public class DisciplinaDataLoader implements FileDataLoader<Disciplina> {

    @Autowired
    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaDataLoader(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    /**
     * Método para carregar dados de roles a partir de um arquivo CSV na
     * inicialização do sistema.
     */
    // @PostConstruct
    public List<Disciplina> loadDataFromCsv() throws IOException {
        String filePath = "src/main/resources/data/disciplinas.csv"; // Caminho do arquivo CSV

        // Ler o arquivo CSV
        FileReader reader = new FileReader(Paths.get(filePath).toFile());
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord(true));
        List<Disciplina> disciplinasList = new ArrayList<>();

        List<CSVRecord> records = csvParser.getRecords();
        for (CSVRecord record : records) {
            // Criar um novo objeto Role a partir dos dados do CSV
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(record.get("name")); // Mapeando a coluna 'name' para o atributo 'name'

            // Salvar no repositório
            disciplinaRepository.save(disciplina);
            disciplinasList.add(disciplina);
        }

        csvParser.close();
        return disciplinasList;
    }

    @Override
    public List<Disciplina> loadData(String filePath) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadData'");
    }

    @Override
    public List<Disciplina> loadDataFromJson() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDataFromJson'");
    }
}
