package com.company.Controller.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
    void shutdown() throws SQLException;
}