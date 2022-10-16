package com.linmsen.td.init;

import com.linmsen.td.TDUtils;
import com.linmsen.td.dao.DatabaseMapper;
import com.linmsen.td.dao.SubTableMapper;
import com.linmsen.td.dao.SuperTableMapper;
import com.linmsen.td.dao.TableMapper;
import com.linmsen.td.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TDInit implements ApplicationRunner {

    @Autowired
    DatabaseMapper databaseMapper;

    @Autowired
    SuperTableMapper superTableMapper;

    @Autowired
    SubTableMapper subTableMapper;

    @Autowired
    TableMapper tableMapper;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        databaseMapper.createDatabase("key_data");

        SuperTableMeta superTableMeta = new SuperTableMeta();
        superTableMeta.setDatabase("key_data");
        superTableMeta.setName("key_data");

        List<FieldMeta> fields = new ArrayList<>();
        fields.add(new FieldMeta("ts", "timestamp"));
        fields.add(new FieldMeta("pvElec", "double"));
        fields.add(new FieldMeta("elec", "double"));
        fields.add(new FieldMeta("onGrid", "double"));
        fields.add(new FieldMeta("offGrid", "double"));
        fields.add(new FieldMeta("charge", "double"));
        fields.add(new FieldMeta("disCharge", "double"));
        fields.add(new FieldMeta("consume", "double"));
        superTableMeta.setFields(fields);

        List<TagMeta> tags = new ArrayList<>();
        tags.add(new TagMeta("deviceId", "bigint"));
        tags.add(new TagMeta("stationId", "bigint"));
        tags.add(new TagMeta("companyId", "bigint"));
        superTableMeta.setTags(tags);

        superTableMapper.createSuperTable(superTableMeta);
        System.out.println("=================创建成功==================");

//        databaseMapper.useDatabase("key_data");
//
//        TableMeta tableMeta = new TableMeta();
//        tableMeta.setDatabase("key_data");
//        tableMeta.setName("test01");
//        tableMeta.setFields(fields);
//
//        tableMapper.create(tableMeta);


        SubTableMeta subTableMeta = TDUtils.generate(superTableMeta, "device_02" );

        subTableMapper.createUsingSuperTable(subTableMeta);

//        String device = "device";
//        for (int i=0;i<10;i++){
//            SubTableValue meta = new SubTableValue();
//            meta.setName(device + "_" + i);
//            meta.setDatabase("key_data");
//            meta.setSupertable("key_data");
//
//            List<TagValue> tagValues = new ArrayList<>();
//            tagValues.add(new TagValue<>("deviceId", (long) i));
//            tagValues.add(new TagValue<>("stationId", (long) i));
//            tagValues.add(new TagValue<>("companyId", (long) i));
//            meta.setTags(tagValues);
//
//            List<FieldValue> subFields = new ArrayList<>();
//            subFields.add(new FieldValue("ts", System.currentTimeMillis()));
//            subFields.add(new FieldValue("pvElec", 1.0));
//            subFields.add(new FieldValue("elec", 1.0));
//            subFields.add(new FieldValue("onGrid", 1.0));
//            subFields.add(new FieldValue("offGrid", 1.0));
//            subFields.add(new FieldValue("charge", 1.0));
//            subFields.add(new FieldValue("disCharge", 1.0));
//            subFields.add(new FieldValue("consume", 1.0));
//            List<RowValue> rows = new ArrayList<>();
//            rows.add(new RowValue(subFields));
//            meta.setValues(rows);
//
//            subTableMapper.insertOneTableMultiValuesUsingSuperTable(meta);
//        }

    }


}
