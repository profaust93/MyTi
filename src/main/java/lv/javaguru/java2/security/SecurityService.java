package lv.javaguru.java2.security;



/**
 * Created by germans.kuzmins on 2016.05.28..
 */
public interface SecurityService {
    Long getCurrentUserId() throws SecurityServiceException;
}
