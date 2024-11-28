package com.example.sistema_academico.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Implementação genérica do carregador de dados para arquivos JSON.
 * 
 * Este carregador é capaz de ler qualquer arquivo JSON e mapear os objetos do
 * JSON para entidades
 * do tipo T, desde que a entidade T tenha um construtor padrão (sem
 * parâmetros).
 * 
 * @param <T> Tipo da entidade a ser carregada a partir do arquivo JSON.
 */
@Component
public class JsonDataLoader<T> implements FileDataLoader<T> {

    private final ObjectMapper objectMapper;

    public JsonDataLoader() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<T> loadData(String filePath) throws IOException {
        // Carregar os dados do arquivo JSON para uma lista de objetos
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, getEntityClass()));
    }

    /**
     * Obtém a classe da entidade T usando reflexão.
     * 
     * @return A classe da entidade T.
     */
    private Class<T> getEntityClass() {
        // Usando reflexão para obter o tipo de classe T
        return (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public List<T> loadDataFromCsv() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDataFromCsv'");
    }

    @Override
    public List<T> loadDataFromJson() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadDataFromJson'");
    }
}
