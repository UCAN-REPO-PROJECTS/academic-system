package com.example.sistema_academico.common.loaders;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.sistema_academico.common.utils.FileDataLoader;
import com.example.sistema_academico.entities.Localidade;
import com.example.sistema_academico.repositories.LocalidadeRepository;

import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe para carregar dados de roles a partir de um arquivo CSV.
 */
@Component
public class LocalidadeDataLoader implements FileDataLoader<Localidade> {

    @Autowired
    private final LocalidadeRepository localidadeRepository;

    public LocalidadeDataLoader(LocalidadeRepository localidadeRepository) {
        this.localidadeRepository = localidadeRepository;
    }

    /**
     * Método para carregar dados de roles a partir de um arquivo CSV na
     * inicialização do sistema.
     */
    // @PostConstruct
    public List<Localidade> loadDataFromCsv() throws IOException {
        String filePath = "src/main/resources/data/localidades.csv";

        // Ler o arquivo CSV
        List<Localidade> localidadesList = new ArrayList<>();


        // Mapas para armazenar dados temporários
        Map<String, Localidade> nomeParaLocalidade = new HashMap<>();
        List<String[]> relacionamentosPendentes = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withDelimiter(';')
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            // Carregar localidades e mapear relações pai-filho
            for (CSVRecord record : csvParser) {
                String nome = record.get("nome");
                String nomePai = record.get("nome_pai");

                // Criar ou buscar a localidade
                Localidade localidade = nomeParaLocalidade
                        .computeIfAbsent(nome, n -> findOrCreateLocalidade(n));

                if (nomePai == null || nomePai.isBlank()) {
                    // Localidade raiz: sem pai
                    continue;
                } else {
                    // Adicionar relação pai-filho pendente
                    relacionamentosPendentes.add(new String[]{nome, nomePai});
                }
            }

            // Resolver relações pai-filho
            for (String[] relacao : relacionamentosPendentes) {
                String nome = relacao[0];
                String nomePai = relacao[1];

                Localidade localidade = nomeParaLocalidade.get(nome);
                Localidade localidadePai = nomeParaLocalidade
                        .computeIfAbsent(nomePai, n -> findOrCreateLocalidade(n));

                localidade.setFkLocalidadePai(localidadePai);
                // localidadePai.getLocalidadeList().add(localidade);
            }

            // Salvar no banco
            localidadeRepository.saveAll(nomeParaLocalidade.values());
        }

        return localidadesList;
    }

    @Override
    public List<Localidade> loadData(String filePath) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadData'");
    }

    @Override
    public List<Localidade> loadDataFromJson() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDataFromJson'");
    }

    private Localidade findOrCreateLocalidade(String nome) {
        // Tentar buscar a localidade no banco
        return localidadeRepository.findByNome(nome)
                .orElseGet(() -> new Localidade(nome));
    }
}
