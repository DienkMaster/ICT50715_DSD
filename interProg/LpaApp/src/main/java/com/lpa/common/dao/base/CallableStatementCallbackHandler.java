//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface CallableStatementCallbackHandler {
    void execute(CallableStatement statement) throws SQLException;
}
