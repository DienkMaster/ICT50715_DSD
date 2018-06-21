//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PrepareStatementCallbackHandler {
    void execute(PreparedStatement statement) throws SQLException;
}
