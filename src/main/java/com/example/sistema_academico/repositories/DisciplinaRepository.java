package com.example.sistema_academico.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistema_academico.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    boolean existsByNome(String nome);

    Optional<Disciplina> findByNome(String disciplina);
}
