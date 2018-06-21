package com.lpa.model;

import java.io.Serializable;
import java.util.Date;

public class BaseVO implements Serializable{
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;


    private String usuarioCriacao;
    private String usuarioAlteracao;
    private Date dataCriacao;
    private Date dataAlteracao;
    
    
	/** complements field integer until 'n' positions */
	protected String complementField(final String field, final int positions){

		if(field != null && !field.trim().equals("")){
			StringBuffer sField = new StringBuffer(field);
			while(sField.toString().length() < positions){
				sField.insert(0, "0");
			}
			
			return sField.toString();
		}

		return null;	
	}

    
    /***
     * Remove os caracteres especiais da string
     * @param String str
     * @return String 
     */
    protected String replaceSpecialCharacters(String str) {
        String C_acentos = "����������������������������������������������";
        String S_acentos = "AAAAAAaaaaaEEEEeeeeIIIIiiiiOOOOOoooooUUUuuuuCc";

        if(str != null && !str.trim().equals("")){
            for(int i=0; i < C_acentos.length(); i++){
                str= str.replace(C_acentos.charAt(i),S_acentos.charAt(i));
              }            
        }

        return str;
    }
    
    
    /**
     * @return Returns the dataAlteracao.
     */
    public Date getDataAlteracao() {
        return dataAlteracao;
    }
    /**
     * @param dataAlteracao The dataAlteracao to set.
     */
    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    /**
     * @return Returns the dataCriacao.
     */
    public Date getDataCriacao() {
        return dataCriacao;
    }
    /**
     * @param dataCriacao The dataCriacao to set.
     */
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    /**
     * @return Returns the usuarioAlteracao.
     */
    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }
    /**
     * @param usuarioAlteracao The usuarioAlteracao to set.
     */
    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }
    /**
     * @return Returns the usuarioCriacao.
     */
    public String getUsuarioCriacao() {
        return usuarioCriacao;
    }
    /**
     * @param usuarioCriacao The usuarioCriacao to set.
     */
    public void setUsuarioCriacao(String usuarioCriacao) {
        this.usuarioCriacao = usuarioCriacao;
    }
}