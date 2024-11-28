package com.example.sistema_academico.common.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação genérica do carregador de dados para arquivos CSV.
 * 
 * Este carregador é capaz de ler qualquer arquivo CSV e mapear as linhas do
 * arquivo para objetos
 * do tipo T, desde que a entidade T tenha um construtor correspondente aos
 * dados.
 * 
 * @param <T> Tipo da entidade a ser carregada a partir do arquivo CSV.
 */
@Component
public class CsvDataLoader<T> implements FileDataLoader<T> {

    @Override
    public List<T> loadData(String filePath) throws IOException {
        List<T> entities = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            // Itera sobre cada linha do CSV
            for (CSVRecord csvRecord : csvParser) {
                T entity = createEntityFromCsv(csvRecord);
                entities.add(entity);
            }
        }
        return entities;
    }

    /**
     * Cria uma instância da entidade T a partir de uma linha do CSV.
     * 
     * Este método usa reflexão para chamar o construtor da classe T e passar os
     * valores da linha.
     * 
     * @param csvRecord Linha do CSV contendo os dados.
     * @return Uma instância da entidade T.
     * @throws IOException Se ocorrer um erro ao criar a instância.
     */
    private T createEntityFromCsv(CSVRecord csvRecord) throws IOException {
        try {
            // Obtenha a classe da entidade
            Class<T> clazz = getEntityClass();
            // Obtenha o construtor da classe que corresponde aos dados do CSV
            Constructor<T> constructor = clazz.getConstructor(String.class, String.class); // Ajuste os parâmetros
                                                                                           // conforme necessário
            // Crie a instância da entidade passando os dados da linha
            return constructor.newInstance(csvRecord.get(0), csvRecord.get(1)); // Ajuste conforme o número de campos no
                                                                                // CSV
        } catch (Exception e) {
            throw new IOException("Erro ao criar entidade a partir do CSV", e);
        }
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
