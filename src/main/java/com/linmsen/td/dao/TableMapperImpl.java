package com.linmsen.td.dao;

import com.linmsen.td.SqlSpeller;
import com.linmsen.td.TDUtils;
import com.linmsen.td.domain.TableMeta;
import com.linmsen.td.domain.TableValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Repository
public class TableMapperImpl implements TableMapper {
    private static final Logger logger = LogManager.getLogger(TableMapperImpl.class);
    private JdbcTemplate template;

    public TableMapperImpl(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public void create(TableMeta tableMeta) throws Exception {
        String sql = SqlSpeller.createTable(tableMeta);
        logger.debug("SQL >>> " + sql);
        Connection connection = TDUtils.getTestConnection();
        connection.createStatement().execute(sql);
//        template.execute(sql);
    }

    @Override
    public int insertOneTableMultiValues(TableValue values) {
        String sql = SqlSpeller.insertOneTableMultiValues(values);
        logger.debug("SQL >>> " + sql);
        return template.update(sql);
    }

    @Override
    public int insertOneTableMultiValuesWithColumns(TableValue values) {
        String sql = SqlSpeller.insertOneTableMultiValuesWithColumns(values);
        logger.debug("SQL >>> " + sql);
        return template.update(sql);
    }

    @Override
    public int insertMultiTableMultiValues(List<TableValue> tables) {
        String sql = SqlSpeller.insertMultiTableMultiValues(tables);
        logger.debug("SQL >>> " + sql);
        return template.update(sql);
    }

    @Override
    public int insertMultiTableMultiValuesWithColumns(List<TableValue> tables) {
        String sql = SqlSpeller.insertMultiTableMultiValuesWithColumns(tables);
        logger.debug("SQL >>> " + sql);
        return template.update(sql);
    }
}
