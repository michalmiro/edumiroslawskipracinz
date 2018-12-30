package edu.miroslawski.pracinz.interfaces;

import java.sql.SQLException;

public interface DbAction {
    void execute(String userName) throws SQLException;
}
