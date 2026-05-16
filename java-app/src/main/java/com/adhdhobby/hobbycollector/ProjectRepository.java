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

    public List<DeletedProject> findArchivedProjects() {
        String sql = """
                SELECT deleted_id, project_id, hobby_id, project_name, status, notes, started_date, deleted_date
                FROM DeletedProjects
                ORDER BY deleted_id
                """;

        return jdbcTemplate.query(sql, (resultSet, rowNumber) ->
                new DeletedProject(
                        resultSet.getInt("deleted_id"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("hobby_id"),
                        resultSet.getString("project_name"),
                        resultSet.getString("status"),
                        resultSet.getString("notes"),
                        resultSet.getDate("started_date").toString(),
                        resultSet.getDate("deleted_date").toString()
                )
        );
    }

    public int add(ProjectRequest request) {
        String sql = """
                INSERT INTO Projects (hobby_id, project_name, status, notes, started_date)
                VALUES (?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                request.getHobbyId(),
                request.getProjectName(),
                request.getStatus(),
                request.getNotes(),
                request.getStartedDate()
        );
    }

    public int updateStatus(int projectId, StatusUpdateRequest request) {
        String sql = """
                UPDATE Projects
                SET status = ?
                WHERE project_id = ?
                """;

        return jdbcTemplate.update(
                sql,
                request.getStatus(),
                projectId
        );
    }

    public int archiveProject(int projectId) {
        String archiveSql = """
                INSERT INTO DeletedProjects (project_id, hobby_id, project_name, status, notes, started_date)
                SELECT project_id, hobby_id, project_name, status, notes, started_date
                FROM Projects
                WHERE project_id = ?
                """;

        String deleteSql = """
                DELETE FROM Projects
                WHERE project_id = ?
                """;

        int archivedRows = jdbcTemplate.update(archiveSql, projectId);

        if (archivedRows == 0) {
            return 0;
        }

        return jdbcTemplate.update(deleteSql, projectId);
    }
}