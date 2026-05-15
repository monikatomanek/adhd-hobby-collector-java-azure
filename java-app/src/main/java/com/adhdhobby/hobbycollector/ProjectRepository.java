package com.adhdhobby.hobbycollector;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<HobbyProject> findAll() {
        String sql = """
                SELECT project_id, hobby_id, project_name, status, notes, started_date
                FROM Projects
                ORDER BY project_id
                """;

        return jdbcTemplate.query(sql, (resultSet, rowNumber) ->
                new HobbyProject(
                        resultSet.getInt("project_id"),
                        resultSet.getInt("hobby_id"),
                        resultSet.getString("project_name"),
                        resultSet.getString("status"),
                        resultSet.getString("notes"),
                        resultSet.getDate("started_date").toString(),
                        null
                )
        );
    }
}