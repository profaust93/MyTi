package lv.javaguru.java2.service.userProfile;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.UserProfileDAOImpl;
import lv.javaguru.java2.domain.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


/**
 * Created by Camille on 10.04.2016.
 *
 * тест заебись, но моки нихуя не работают.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest {
    //под @Mock вписывается объкет который симулируется т.е тот чье поведение мы далее определим
    @Mock
    UserProfileDAOImpl mockedUserProfileDAO;

    //под @InjectMocks объект, методы которого будем тестировать и в которых используется
    //созданный выше мок
    @InjectMocks
    ProfileServices profileServices = new ProfileServices();

    @Before
    public void setUp() throws DBException{
        //создаем обект userProfile и пишем что наш мок при вызове метода getById вернет
        //объект userProfile

        UserProfile userProfile = new UserProfile();
            when(mockedUserProfileDAO.getById(1L)).thenReturn(userProfile);
        when(mockedUserProfileDAO.getById(2L)).thenReturn(null);

    }
    @Test
    public void profileExistTest ()throws DBException {
        //сам тест
        assertEquals(true,profileServices.profileExist(1L));
        assertEquals(false,profileServices.profileExist(2L));
    }
}
