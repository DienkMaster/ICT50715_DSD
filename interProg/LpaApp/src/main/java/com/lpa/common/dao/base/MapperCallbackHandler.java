//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapperCallbackHandler<T> {
    T execute(ResultSet rs) throws SQLException;
}
