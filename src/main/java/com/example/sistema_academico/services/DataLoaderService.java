package com.example.sistema_academico.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistema_academico.common.loaders.DisciplinaDataLoader;
import com.example.sistema_academico.common.loaders.LocalidadeDataLoader;
import com.example.sistema_academico.common.utils.DataLoader;
import com.example.sistema_academico.entities.Disciplina;
import com.example.sistema_academico.repositories.DisciplinaRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoaderService {

    @Autowired
    private DisciplinaDataLoader disciplinaDataLoader;

    @Autowired
    private LocalidadeDataLoader localidadeDataLoader;

    
    public void init() {
        try {
            this.loadInitialData();
            // Código para carregar dados do CSV ou banco
            // dataLoader.load(); // ou qualquer outro código de inicialização
        } catch (IOException e) {
            // logger.error("Erro ao carregar dados: {}", e.getMessage());
            System.err.println(e.getMessage());
            throw new RuntimeException("Erro ao carregar dados no método de inicialização", e);
        }
    }

    @PostConstruct
    public void loadInitialData() throws IOException {
        // Carregar disciplinas a partir de um arquivo CSV
        disciplinaDataLoader.loadDataFromCsv();
        localidadeDataLoader.loadDataFromCsv();
        // dataLoader.loadData("src/main/resources/data/disciplinas.csv", "csv", Disciplina.class, disciplinaRepository);

        // // Carregar localidades a partir de um arquivo JSON
        // dataLoader.loadData("src/main/resources/localidades.json", "json", Localidade.class, localidadeRepository);
    }
}
