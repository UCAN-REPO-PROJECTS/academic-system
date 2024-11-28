package com.example.sistema_academico.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma entidade não é encontrada no banco de dados.
 * Essa exceção é mapeada para um código de status HTTP 404 (Not Found).
 * 
 * @author Jonathan Mandombe
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constrói a exceção com uma mensagem customizada.
     * 
     * @param message Mensagem de erro a ser passada.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
