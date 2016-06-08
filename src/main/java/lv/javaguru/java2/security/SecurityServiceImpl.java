package lv.javaguru.java2.security;

import lv.javaguru.java2.todo.exception.ToDoError;
import lv.javaguru.java2.todo.exception.ToDoException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Long getCurrentUserId() throws Exception {
        try {
            UserSecurityEntity user = (UserSecurityEntity) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            return user.getUserId();
        } catch (Exception e) {
            throw new Exception("Bad Login", e);
        }
    }
}