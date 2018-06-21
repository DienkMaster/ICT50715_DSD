// ******************************************************************************
// * Copyright (c) 2010 Ford Motor Company. All Rights Reserved.
// * Original author: Ford Motor Company J2EE Center of Excellence
// *
// * $Workfile:   BARSBaseBF.java  $
// * $Revision:   1.1  $
// * $Author:   mtoll2  $
// * $Date:   15 Feb 2011 10:35:04  $
// ******************************************************************************
package com.lpa.business.layer;

import java.util.List;

import com.ford.bars.common.layer.BARSConstant;
import com.lpa.outbound.layer.BARSPersistenceAS;
import com.ford.it.bf.BaseBF;
import com.ford.it.entity.bo.BaseBO;
import com.ford.it.entity.ro.BaseRO;
import com.ford.it.persistencecore.context.PcExpressionQueryConfig;
import com.ford.it.persistencecore.context.PcNamedQueryConfig;
import com.ford.it.persistencecore.exception.PcQueryException;
import com.ford.it.persistencecore.exception.PcRuntimeException;

/**
 * This abstract class provides business functionality to the Presentation Layer while 'hiding' the technology specific Application Service instances.
 * 
 * @since v. 3.1
 */
public abstract class BARSBaseBF extends BaseBF {

    /**
     * Provides a handle to an Application Service instance.
     */
    protected static final BARSPersistenceAS BARS_PERSISTENCE_AS = new BARSPersistenceAS(BARSConstant.BOOKING_AGENT);

    /**
     * This method returns a RO if it is persisted. This overloaded version will throw an exception if an object with the primary key is not found. This overloaded version does not support refreshing
     * shared cache from the database.
     * 
     * @param <RO>
     * @param bo
     * @return RO
     * @throws PcQueryException
     */

    public <RO extends BaseRO> RO findByPk(final BaseBO bo) throws PcQueryException {

        return BARS_PERSISTENCE_AS.<RO> findByPk(bo);
    }

    /**
     * Returns <code>true</code> if an instance is persisted.
     * 
     * @param boContainingPK BO containing identity.
     * @param <BO> BO
     * @return boolean - Returns <code>true</code> if an instance is persisted.
     */
    public <BO extends BaseBO> boolean existsByPrimaryKey(final BO boContainingPK) {

        return BARS_PERSISTENCE_AS.existsByPrimaryKey(boContainingPK);
    }

    /**
     * Returns a list of ROs for a Named Query. An empty list is returned if the query does not return any results.
     * 
     * @param <RO>
     * @param namedQueryConfig
     * @return List<RO>
     * @throws PcQueryException
     */
    public <RO extends BaseRO> List<RO> queryMany(final PcNamedQueryConfig namedQueryConfig) throws PcQueryException {

        return BARS_PERSISTENCE_AS.queryMany(namedQueryConfig);

    }

    /**
     * Returns a list of ROs for an Expression Query. An empty list is returned if the query does not return any results.
     * 
     * @param <RO>
     * @param expressionQueryConfig
     * @return List<RO>
     * @throws PcQueryException
     */
    public <RO extends BaseRO> List<RO> queryMany(final PcExpressionQueryConfig expressionQueryConfig) throws PcQueryException {

        // Query.
        return BARS_PERSISTENCE_AS.queryMany(expressionQueryConfig);

    }

    /**
     * Returns a list of ROs for an Expression Query. An empty list is returned if the query does not return any results.
     * 
     * @param boClass The table that is mapped to the BO
     * @param orderBy Direct attribute for the BO.The order by clause will be generated using the supplied value. This is an optional comma separated strings that list the order by attributes and its
     *        sort order. example: streetAddress1 ascending,streetAddress2 ascending,city descending
     * @param <RO> extends BaseRO
     * @param <BO> extends BaseBO
     * @return List<RO> extends BaseRO
     */

    public <RO extends BaseRO, BO extends BaseBO> List<RO> findAll(final Class<BO> boClass, final String... orderBy) {

        return BARS_PERSISTENCE_AS.findAll(boClass, orderBy);

    }

    /**
     * This method returns a BO for setting to a parent BO. Associated objects should not be modified as they are lookup objects.
     * 
     * @param <RO>
     * @param <BO>
     * @param roOrBO
     * @return BO - Business object.
     * @throws PcRuntimeException
     */
    public <RO extends BaseRO, BO extends RO> BO preBindAssociate(final RO roOrBO) {

        return BARS_PERSISTENCE_AS.<RO, BO> preBindAssociate(roOrBO);
    }

    /**
     * This method provides a BO deep copy for modification. It returns a working copy that can be modified further. After modification the BO needs to be bound to the transaction by calling
     * bindCreateOrUpdate. Upon transaction commit any changes to this bound object will be updated in the database.
     * 
     * @param <RO>
     * @param <BO> The business object
     * @param roOrBO
     * @return BO class that extends BaseBO
     * @throws PcRuntimeException
     */
    public <RO extends BaseRO, BO extends RO> BO preBindUpdate(final RO roOrBO) {

        return BARS_PERSISTENCE_AS.<RO, BO> preBindUpdate(roOrBO);
    }

    /**
     * The method forwards the call to BARS_PERSISTENCE_AS.queryOne() for result.
     * 
     * @param <RO>
     * @param namedQueryConfig
     * @return <RO extends BaseRO> RO
     * @throws PcQueryException
     */
    public <RO extends BaseRO> RO queryOne(final PcNamedQueryConfig namedQueryConfig) throws PcQueryException {
        return BARS_PERSISTENCE_AS.<RO> queryOne(namedQueryConfig);
    }

    /**
     * The method forwards the call to BARS_PERSISTENCE_AS.existsOne() for result.
     * 
     * @param namedQueryConfig
     * @return boolean.
     * @throws PcQueryException
     */
    public boolean existsOne(final PcNamedQueryConfig namedQueryConfig) throws PcQueryException {
        return BARS_PERSISTENCE_AS.existsOne(namedQueryConfig);
    }
}
