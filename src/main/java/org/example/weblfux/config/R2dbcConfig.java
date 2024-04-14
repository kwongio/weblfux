package org.example.weblfux.config;

import io.asyncer.r2dbc.mysql.MySqlConnectionFactoryProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryOptionsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
@EnableR2dbcRepositories
@RequiredArgsConstructor
@EnableR2dbcAuditing
@Slf4j
public class R2dbcConfig  {

    @Bean
    public ConnectionFactoryOptionsBuilderCustomizer mysqlCustomizer() {
        return (builder) ->
                builder.option(MySqlConnectionFactoryProvider.SERVER_ZONE_ID, ZoneId.of(
                        "UTC"));
    }

}
