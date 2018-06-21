//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionHelper {

    public static DataSource jndiLookup(final String name) throws NamingException {
        InitialContext context = null;
        try {
            context = new InitialContext();
            return (DataSource)context.lookup(name);
        } finally {
            context.close();
        }
    }

}
