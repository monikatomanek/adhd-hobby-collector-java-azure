package com.adhdhobby.hobbycollector;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplyRepository {

    private final JdbcTemplate jdbcTemplate;

    public SupplyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Supply> findAll() {
        String sql = """
                SELECT supply_id, hobby_id, item_name, cost, is_essential, supply_type
                FROM Supplies
                ORDER BY supply_id
                """;

        return jdbcTemplate.query(sql, (resultSet, rowNumber) ->
                new Supply(
                        resultSet.getInt("supply_id"),
                        resultSet.getInt("hobby_id"),
                        resultSet.getString("item_name"),
                        resultSet.getDouble("cost"),
                        resultSet.getBoolean("is_essential"),
                        resultSet.getString("supply_type")
                )
        );
    }
}