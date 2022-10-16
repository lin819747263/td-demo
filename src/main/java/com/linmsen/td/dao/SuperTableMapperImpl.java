package com.linmsen.td.dao;

import com.linmsen.td.SqlSpeller;
import com.linmsen.td.domain.SuperTableMeta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SuperTableMapperImpl implements SuperTableMapper {
    private static final Logger logger = LogManager.getLogger(SuperTableMapperImpl.class);
    private JdbcTemplate jdbcTemplate;

    public SuperTableMapperImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createSuperTable(SuperTableMeta tableMetadata) {
        String sql = SqlSpeller.createSuperTable(tableMetadata);
        logger.debug("SQL >>> " + sql);
        jdbcTemplate.execute(sql);
    }

    @Override
    public void dropSuperTable(String database, String name) {
        String sql = "drop table if exists " + database + "." + name;
        logger.debug("SQL >>> " + sql);
        jdbcTemplate.execute(sql);
    }
}
