package uts.util;

import com.zaxxer.hikari.*;

public class DatabaseUtil {
    private static final HikariDataSource hikariDataSource;

    static{
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database?serverTimezone=Asia/Jakarta");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("");

        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setMaxLifetime(60*60*1000);
        hikariConfig.setIdleTimeout(60_000);

        hikariDataSource = new HikariDataSource(hikariConfig);
    }
    public static HikariDataSource gDataSource(){
        return hikariDataSource;
    }
}
