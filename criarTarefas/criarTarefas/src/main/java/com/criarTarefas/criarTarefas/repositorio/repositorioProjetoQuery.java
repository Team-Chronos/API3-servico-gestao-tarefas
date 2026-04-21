package com.criarTarefas.criarTarefas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.criarTarefas.criarTarefas.modelo.DTO.projetoLimiteQueryDTO;

@Repository
public class repositorioProjetoQuery {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<projetoLimiteQueryDTO> projetoLimiteRowMapper = (rs, rowNum) ->
            new projetoLimiteQueryDTO(
                    rs.getLong("projeto_id"),
                    rs.getString("tipo_projeto"),
                    rs.getBigDecimal("horas_contratadas")
            );

    public repositorioProjetoQuery(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<projetoLimiteQueryDTO> buscarProjetoParaValidacao(Long projetoId) {
        String sql = """
                SELECT
                    p.id AS projeto_id,
                    p.tipo_projeto AS tipo_projeto,
                    p.horas_contratadas AS horas_contratadas
                FROM projeto p
                WHERE p.id = :projetoId
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("projetoId", projetoId);

        List<projetoLimiteQueryDTO> resultados = jdbcTemplate.query(sql, params, projetoLimiteRowMapper);
        return resultados.stream().findFirst();
    }
}