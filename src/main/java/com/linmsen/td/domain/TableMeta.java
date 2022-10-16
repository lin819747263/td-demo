package com.linmsen.td.domain;

import lombok.Data;

import java.util.List;

@Data
public class TableMeta {

    private String database;
    private String name;
    private List<FieldMeta> fields;
}
