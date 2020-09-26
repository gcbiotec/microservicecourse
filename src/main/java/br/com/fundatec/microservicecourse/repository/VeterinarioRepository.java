package br.com.fundatec.microservicecourse.repository;

import java.util.List;
import java.util.Optional;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fundatec.microservicecourse.domain.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    Optional<Veterinario> findByNome(String nome);

    @Query(value = "select v.cachorros from Veterinario v where v.id = :id")
    List<Cachorro> findAllCachorros(@Param("id")Long id);
}
