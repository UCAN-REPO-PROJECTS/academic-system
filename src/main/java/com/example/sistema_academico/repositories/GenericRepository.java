package com.example.sistema_academico.repositories;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Repositório genérico para fornecer operações CRUD padrão e algumas personalizações.
 *
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo da chave primária da entidade.
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * Recupera todos os registros da entidade com paginação.
     *
     * @param pageable A configuração de paginação.
     * @return Lista paginada de entidades.
     */
    List<T> findAll(Pageable pageable);

    /**
     * Recupera uma entidade pelo ID, retornando um Optional.
     *
     * @param id O ID da entidade.
     * @return Um Optional com a entidade.
     */
    Optional<T> findById(ID id);

    /**
     * Recupera todos os registros da entidade sem paginação.
     *
     * @return Lista de entidades.
     */
    List<T> findAll();

    /**
     * Salva ou atualiza uma entidade.
     *
     * @param entity A entidade a ser salva.
     * @return A entidade salva.
     */
    <S extends T> S save(S entity);

    /**
     * Deleta uma entidade pelo ID.
     *
     * @param id O ID da entidade a ser deletada.
     */
    void deleteById(ID id);
}
