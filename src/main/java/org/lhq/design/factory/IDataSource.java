package org.lhq.design.factory;

import java.sql.Connection;

public interface IDataSource {
    Connection getConnection();
}
