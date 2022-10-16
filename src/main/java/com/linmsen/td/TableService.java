package com.linmsen.td;

import com.linmsen.td.dao.TableMapper;
import com.linmsen.td.domain.TableMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService extends AbstractService {

    Logger logger = LoggerFactory.getLogger(TableService.class);

    private TableMapper tableMapper;

    //创建一张表
    public void create(TableMeta tableMeta) {
        try {
            tableMapper.create(tableMeta);
        }catch (Exception e){
            logger.warn("", e);
        }

    }

    //创建多张表
    public void create(List<TableMeta> tables) {
        tables.stream().forEach(this::create);
    }

}
