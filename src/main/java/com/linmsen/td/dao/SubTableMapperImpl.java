package com.linmsen.td.dao;

import com.linmsen.td.DataSourceFactory;
import com.linmsen.td.SqlSpeller;
import com.linmsen.td.domain.SubTableMeta;
import com.linmsen.td.domain.SubTableValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class SubTableMapperImpl implements SubTableMapper {

    private static final Logger logger = LogManager.getLogger(SubTableMapperImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public SubTableMapperImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUsingSuperTable(SubTableMeta subTableMeta) throws SQLException {
        String sql = SqlSpeller.createTableUsingSuperTable(subTableMeta);
        logger.debug("SQL >>> " + sql);
        jdbcTemplate.execute(sql);
    }

    @Override
    public int insertOneTableMultiValues(SubTableValue subTableValue) {
        String sql = SqlSpeller.insertOneTableMultiValues(subTableValue);
        logger.debug("SQL >>> " + sql);

        int affectRows = 0;
        try {
            affectRows = jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int insertOneTableMultiValuesUsingSuperTable(SubTableValue subTableValue) {
        String sql = SqlSpeller.insertOneTableMultiValuesUsingSuperTable(subTableValue);
        logger.debug("SQL >>> " + sql);

        int affectRows = 0;
        try {
            affectRows = jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int insertMultiTableMultiValues(List<SubTableValue> tables) {
        String sql = SqlSpeller.insertMultiSubTableMultiValues(tables);
        logger.debug("SQL >>> " + sql);
        int affectRows = 0;
        try {
            affectRows = jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectRows;
    }

    @Override
    public int insertMultiTableMultiValuesUsingSuperTable(List<SubTableValue> tables) {
        String sql = SqlSpeller.insertMultiTableMultiValuesUsingSuperTable(tables);
        logger.debug("SQL >>> " + sql);
        int affectRows = 0;
        try {
            affectRows = jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectRows;
    }
}
