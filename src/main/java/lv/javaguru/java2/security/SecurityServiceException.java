package lv.javaguru.java2.security;

/**
 * Created by Ruslan on 2016.05.29..
 */
public class SecurityServiceException extends Exception {

    private SecurityServiceError securityServiceError;

    public SecurityServiceException(){
        super();
        this.securityServiceError = SecurityServiceError.SECURITY_SERVICE_ERROR;
    }

    public SecurityServiceException(SecurityServiceError securityServiceError){
        super();
        this.securityServiceError = securityServiceError;
    }

    public SecurityServiceError getSecurityServiceError(){
        return securityServiceError;
    }
}
