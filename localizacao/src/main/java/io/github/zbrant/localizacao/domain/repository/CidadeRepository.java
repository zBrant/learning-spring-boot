package io.github.zbrant.localizacao.domain.repository;

import io.github.zbrant.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
