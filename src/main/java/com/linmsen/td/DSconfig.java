package com.linmsen.td;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DSconfig {

    @Bean
    public DataSource datasource() throws IOException {
        return DataSourceFactory.getInstance("td01", 6030, "root", "taosdata");
    }
}
