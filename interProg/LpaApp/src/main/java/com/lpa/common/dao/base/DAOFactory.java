//****************************************************************
//* Copyright (c) 2014 Ford Motor Company. All Rights Reserved.
//****************************************************************
package com.lpa.common.dao.base;

import com.ford.it.logging.ILogger;
import com.ford.it.logging.Level;
import com.ford.it.logging.LogFactory;

public class DAOFactory {

    private static ILogger logger = LogFactory.getInstance().getLogger(DAOFactory.class.getName());

    public static <T> T instanceOf(final Class<T> klass) {

        final StringBuilder klassName = new StringBuilder();
        klassName.append(klass.getPackage().getName());
        klassName.append(".impl.");
        klassName.append(klass.getSimpleName());
        klassName.append("Impl");

        try {
            final Class<?> k = Class.forName(klassName.toString());
            final Object o = k.newInstance();
            return klass.cast(o);
        } catch (final ClassNotFoundException e) {
            logger.log(Level.WARNING, "Overriding original exception", e);
            throw new RuntimeException(e);
        } catch (final IllegalAccessException e) {
            logger.log(Level.WARNING, "Overriding original exception", e);
            throw new RuntimeException(e);
        } catch (final InstantiationException e) {
            logger.log(Level.WARNING, "Overriding original exception", e);
            throw new RuntimeException(e);
        }

    }

}
