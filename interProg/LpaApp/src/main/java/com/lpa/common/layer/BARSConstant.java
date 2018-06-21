package com.lpa.common.layer;

/**
 * This class contains constants used by BARS presentation layer.
 * 
 * @since v. 1.0
 */
public class BARSConstant {

    /** The Class Name used for Logging */
    private static final String CLASS_NAME = BARSConstant.class.getName();

    /**
     * Private Constructor - This class should not be instantiated.
     */
    private BARSConstant() {
        // Empty
    }

    /*======================================================================
     *  UI Constants
     *======================================================================*/

    public static final String BARS_TEMPLATE_DEFAULT = "/layout/template.xhtml";
    public static final String BARS_FUNDO_VERDE = "fundo_header_verde";
    public static final String BARS_FUNDO_AZUL = "fundo_header_azul";
    public static final String BARS_FUNDO_CINZA = "fundo_header_cinza";
    public static final String BARS_LOGO_BRANCO = "LogoBarsBranco";
    public static final String BARS_LOGO_PRETO = "LogoBarsPreto";
    public static final String BARS_ESTILO = "estilo";
    public static final String BARS_ESTILO2 = "estilo2";
    public static final String BARS_TEMA_PADRAO = "sam";
    public static final int BARS_FONTE_PADRAO = 11;
    public static final String BARS_IDIOMA_PADRAO = "pt";
    public static final String BARS_MOEDA_PADRAO = "R$";

    /**
     * Default resource bundle for internationalized messages
     */
    public static final String BARS_RESOURCE_BUNDLE = "bundle";

    // Dynaprop Namespace constant
    public static final String DYNAPROP_CONFIG_DB = "dbCredentials";

    public static final String DYNAPROP_DATA_SOURCE_GROUP = DYNAPROP_CONFIG_DB;
    public static final String DYNAPROP_DATA_SOURCE_PASSWORD_SQL = "passwSql";
    public static final String DYNAPROP_DATA_SOURCE_USER_SQL = "userSql";

    /*======================================================================
     *  Session/Request Constants
     *======================================================================*/
    /** Used to store bars alias email id in session. */
    public static final String BARS_ALIAS_EMAIL_ID_KEY = CLASS_NAME + ".BARS_ALIAS_EMAIL_ID_KEY.window-";

    /** Used to store the Authorization Provider in the Request */
    public static final String AUTHORIZATION_PROVIDER = CLASS_NAME + ".AUTHORIZATION_PROVIDER";

    // CODIGO DOS PAISES
    public static final int BRASIL = 1;
    public static final int VENEZUELA = 2;

    /*======================================================================
     *  Authorization Constants
     *======================================================================*/
    /** Authorization Constant */
    public static final String AGENT_EMAIL_ID = "barsagent@ford.com";

    /** Authorization Constant */
    public static final String EXECUTE_PRIVILEDGE = "execute";

    /** Authorization Constant */
    public static final String RESOURCE_VIEW_ANY_BOOKING = "ViewAnyBooking";

    /**
     * Bundle token constant containing the Customer role
     */
    public static final String CUSTOMER = "label.header.customer";

    /**
     * Bundle token constant containing the Bookin Agent role
     */
    public static final String BOOKING_AGENT = "label.header.bookingAgent";
}
