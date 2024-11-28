package com.example.sistema_academico.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe responsável por carregar dados iniciais para a base de dados a partir
 * de arquivos.
 * 
 * Esta classe utiliza carregadores de dados específicos para diferentes tipos
 * de arquivos
 * (CSV, JSON, etc.) e as salva na base de dados, caso ainda não existam.
 */
@Service
public class DataLoader<T> {

    @Autowired
    private CsvDataLoader<T> csvDataLoader;

    @Autowired
    private JsonDataLoader<T> jsonDataLoader;

    // Mapa de tipos de arquivo para seus respectivos carregadores
    private final Map<String, FileDataLoader<?>> fileDataLoaders = new HashMap<>();

    /**
     * Inicializa o mapa de carregadores de dados após a criação do bean.
     * Este método é chamado automaticamente pelo Spring após a construção da
     * classe.
     */
    @PostConstruct
    public void init() {
        fileDataLoaders.put("csv", csvDataLoader);
        fileDataLoaders.put("json", jsonDataLoader);
    }

    /**
     * Carrega dados de um arquivo para a base de dados.
     * 
     * O tipo de arquivo e a classe da entidade são passados como parâmetros,
     * permitindo que
     * qualquer tipo de entidade seja carregado a partir de diferentes tipos de
     * arquivos.
     * 
     * @param filePath    Caminho do arquivo (CSV, JSON, etc.) que contém os dados.
     * @param fileType    Tipo de arquivo (ex: "csv", "json").
     * @param entityClass Classe da entidade que representa os dados a serem
     *                    carregados.
     * @param repository  Repositório da entidade correspondente para persistir os
     *                    dados.
     * @throws IOException Se ocorrer um erro durante o carregamento do arquivo.
     */
    public <T> void loadData(String filePath, String fileType, Class<T> entityClass, JpaRepository<T, Integer> repository)
            throws IOException {
        // Obtenção do carregador de dados adequado com base no tipo de arquivo
        FileDataLoader<T> fileDataLoader = (FileDataLoader<T>) fileDataLoaders.get(fileType);
        if (fileDataLoader == null) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado: " + fileType);
        }

        // Carregar os dados do arquivo
        List<T> data = fileDataLoader.loadData(filePath);

        // Salvar os dados na base de dados, se ainda não existirem
        for (T item : data) {
            // Verifica se o item já existe na base de dados
            if (!existsInDatabase(item, repository)) {
                repository.save(item);
            }
        }
    }

    /**
     * Verifica se o item já existe na base de dados.
     * 
     * Este método verifica se uma entidade já existe na base de dados usando o seu
     * ID.
     * Caso a entidade já exista, ela não será salva novamente.
     * 
     * @param item A entidade a ser verificada.
     * @param repo O repositório da entidade correspondente.
     * @param <T>  O tipo da entidade.
     * @return true se a entidade já existir na base de dados, caso contrário false.
     */
    private <T> boolean existsInDatabase(T item, JpaRepository<T, Integer> repo) {
        Optional<T> optionalEntity = repo.findById(getId(item));
        return optionalEntity.isPresent();
    }

    /**
     * Obtém o ID de uma entidade usando reflexão.
     * 
     * Este método usa reflexão para chamar o método `getId()` da entidade e obter o
     * seu ID.
     * Isso permite que o código seja genérico para qualquer tipo de entidade.
     * 
     * @param entity A entidade da qual o ID será extraído.
     * @param <T>    O tipo da entidade.
     * @return O ID da entidade.
     */
    private <T> Integer getId(T entity) {
        try {
            return (Integer) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar o ID da entidade", e);
        }
    }
}
