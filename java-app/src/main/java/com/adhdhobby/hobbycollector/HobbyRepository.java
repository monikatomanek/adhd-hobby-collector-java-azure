package com.adhdhobby.hobbycollector;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HobbyRepository {

    private final JdbcTemplate jdbcTemplate;

    public HobbyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Hobby> findAll() {
        String sql = """
                SELECT hobby_id, name, category, skill_level
                FROM Hobbies
                ORDER BY hobby_id
                """;

        return jdbcTemplate.query(sql, (resultSet, rowNumber) ->
                new Hobby(
                        resultSet.getInt("hobby_id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("skill_level")
                )
        );
    }
}