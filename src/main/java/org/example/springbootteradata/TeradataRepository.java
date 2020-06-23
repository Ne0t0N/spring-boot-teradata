package org.example.springbootteradata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TeradataRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @EventListener(ApplicationReadyEvent.class)
    public void printDatabases() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select distinct databasename from dbc.tables");
        result.forEach(it -> it.forEach((key, value) -> System.out.println(key + " : " + value)));
    }
}
