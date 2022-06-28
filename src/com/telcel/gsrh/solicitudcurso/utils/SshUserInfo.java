package com.telcel.gsrh.solicitudcurso.utils;

import org.apache.log4j.Logger;

import com.jcraft.jsch.UserInfo;

public class SshUserInfo implements UserInfo {

    private String password;  
    private String passPhrase; 
    private static final Logger LOGGER = Logger.getLogger(SshUserInfo.class);
   
    public SshUserInfo (String password, String passPhrase) {  
        this.password = password;  
        this.passPhrase = passPhrase;  
    }  
    
    @Override
    public String getPassphrase() {  
        return passPhrase;  
    }  
    
    @Override
    public String getPassword() {  
        return password;  
    }  
    
    @Override
    public boolean promptPassphrase(String arg0) {  
        LOGGER.info("Parametro promptPassphrase: " + arg0);
        return true;  
    }  
    
    @Override
    public boolean promptPassword(String arg0) {  
        LOGGER.info("Parametro promptPassword: " + arg0);
        return false;  
    }  
    
    @Override
    public boolean promptYesNo(String arg0) {  
        LOGGER.info("Parametro promptYesNo: " + arg0);
        return true;  
    }  
    
    @Override
    public void showMessage(String arg0) { 
        LOGGER.debug(arg0);
    }  
}