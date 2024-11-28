package com.example.sistema_academico.common.utils;

import java.io.IOException;
import java.util.List;

/**
 * Interface para carregadores de dados a partir de arquivos.
 * 
 * Essa interface define o contrato para carregadores de dados de diferentes
 * tipos de arquivos,
 * como CSV e JSON. Cada implementação deve fornecer a lógica específica para
 * ler e carregar
 * dados de um arquivo.
 */
public interface FileDataLoader<T> {

    /**
     * Carrega os dados de um arquivo.
     * 
     * Este método será implementado para ler os dados do arquivo e convertê-los em
     * objetos
     * do tipo T (geralmente uma entidade).
     * 
     * @param filePath O caminho do arquivo que contém os dados.
     * @return Lista de objetos do tipo T, que representam os dados do arquivo.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    List<T> loadData(String filePath) throws IOException;

    /**
     * Carrega os dados de um arquivo.
     * 
     * Este método será implementado para ler os dados do arquivo e convertê-los em
     * objetos
     * do tipo T (geralmente uma entidade).
     * 
     * @param filePath O caminho do arquivo que contém os dados.
     * @return Lista de objetos do tipo T, que representam os dados do arquivo.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    List<T> loadDataFromCsv() throws IOException;

    /**
     * Carrega os dados de um arquivo.
     * 
     * Este método será implementado para ler os dados do arquivo e convertê-los em
     * objetos
     * do tipo T (geralmente uma entidade).
     * 
     * @param filePath O caminho do arquivo que contém os dados.
     * @return Lista de objetos do tipo T, que representam os dados do arquivo.
     * @throws IOException Se ocorrer um erro ao ler o arquivo.
     */
    List<T> loadDataFromJson() throws IOException;
}
