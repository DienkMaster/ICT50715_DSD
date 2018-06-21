package com.lpa.common.service.impl;

import com.ford.bars.common.dao.base.IConnectionManager;
import com.ford.bars.common.layer.BARSConstant;
import com.ford.bars.common.service.base.ServiceBase;
import com.ford.bars.common.service.base.SqlServiceBase;

public class SqlServiceBaseImpl extends ServiceBaseImpl implements ServiceBase, SqlServiceBase {

    public SqlServiceBaseImpl() {
        this.setJndiName("jdbc/BARS_DB");
    }

    @Override
    public IConnectionManager getConnectionManager() {
        return getConnectionManager(BARSConstant.DYNAPROP_DATA_SOURCE_GROUP, BARSConstant.DYNAPROP_DATA_SOURCE_USER_SQL, BARSConstant.DYNAPROP_DATA_SOURCE_PASSWORD_SQL, null);

    }

}
