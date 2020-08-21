package br.com.fundatec.microservicecourse.repository;

import br.com.fundatec.microservicecourse.domain.Cachorro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Long> {

    Optional<Cachorro> findByNome(String nome);

}
