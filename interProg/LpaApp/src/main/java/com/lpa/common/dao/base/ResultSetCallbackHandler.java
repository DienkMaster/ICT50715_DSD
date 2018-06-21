//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import java.util.List;

public interface ResultSetCallbackHandler<T> {
    List<T> getList();

    void execute(T obj);
}
