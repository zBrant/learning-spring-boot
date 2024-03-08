package io.github.zbrant.localizacao.domain.repository;

import io.github.zbrant.localizacao.domain.entity.Cidade;
import io.github.zbrant.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

  @Query(nativeQuery = true, value = " select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome ")
  List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);

  List<Cidade> findByNome(String nome);

  @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
  List<Cidade> findByNomeLike(String nome, Sort sort);

  @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
  Page<Cidade> findByNomeLike(String nome, Pageable pageable);

  List<Cidade> findByNomeStartingWith(String nome);

  List<Cidade> findByNomeEndingWith(String nome);

  List<Cidade> findByNomeContaining(String nome);

  List<Cidade> findByHabitantes(Long habitantes);

  List<Cidade> findByHabitantesLessThan(Long habitantes);

  List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
  List<Cidade> findByHabitantesGreaterThan(Long habitantes);

  List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
