package com.lpa.common.layer;

import com.ford.it.context.RequestContext;
import com.ford.it.context.SubContext;

/**
 * This class holds the BARS user's information (in this case the alias user id). Based on the current design, the alias user id is the email address of either the 'BARS Agent' or the real user
 * depending on whether the user elects to enter as 'Agent' or 'Customer'.
 */
public class BARSRequestContext implements SubContext {
    /**
     * The bars alias user id
     */
    private String barsAliasEmailId;

    /**
     * @see com.ford.it.context.SubContext#close()
     */
    @Override
    public void close() {
        this.barsAliasEmailId = null;
    }

    /**
     * @see com.ford.it.context.SubContext#init()
     */
    @Override
    public void init() {
        // empty block
    }

    /**
     * @return The local instance of the BARSRequestContext class
     */
    public static BARSRequestContext getInstance() {
        final BARSRequestContext ctx = RequestContext.getLocalInstance().getSubContext(BARSRequestContext.class);
        return ctx;
    }

    /**
     * Get the bars alias email id stored in the thread. This will either be the BARS Agent email id (barsagent@ford.com) if the user logged in as Agent, or the logged in user's real email id if they
     * logged in as a customer (e.g. jburnard@ford.com).
     * 
     * @return The bars alias user id stored in the thread
     */
    public String getBARSAliasEmailId() {
        return this.barsAliasEmailId;

    }

    /**
     * Setting the bars alias user id stored in the thread. If the user logged on as the BARS Agent, this set to barsagent@ford.com otherwise it is set to the real email id for the user logged in.
     * 
     * @param barsAliasEmailId The bars alias user id to set
     */
    public void setBARSAliasEmailId(final String barsAliasEmailId) {
        this.barsAliasEmailId = barsAliasEmailId;
    }
}
