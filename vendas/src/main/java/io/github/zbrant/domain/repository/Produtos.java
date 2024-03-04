package io.github.zbrant.domain.repository;

import io.github.zbrant.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
