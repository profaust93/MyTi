package lv.javaguru.java2.security;



import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Long getCurrentUserId() throws SecurityServiceException {
        try {
            UserSecurityEntity user = (UserSecurityEntity) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            return user.getUserId();
        } catch (Exception e) {
            throw new SecurityServiceException(SecurityServiceError.SECURITY_SERVICE_ERROR);
        }
    }
}
