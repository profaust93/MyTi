package lv.javaguru.java2.service.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.service.validators.Validators;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDateTime;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TimeLapsServiceTest {

    private TimeLaps timeLaps;
    private TimeLaps secondTimeLaps;
    private final LocalDateTime DATE = LocalDateTime.now();
    private final String SHORT_DESCRIPTION = "Short Description";
    private final String LONG_DESCRIPTION = "Long Description";
    private final String CATEGORY = "Sport";
    private final Long FIRST_USER_ID = 12L;
    private final Long SECOND_USER_ID = 15L;
    private final String NAME = "TimeLaps";
    @Mock
    private TimeLapsDAO timeLapsDAO;
    @Mock
    private Validators validators;

    @InjectMocks
    TimeLapsService timeLapsService = new TimeLapsServiceImpl();


    @Before
    public void setUp(){
        timeLaps = new TimeLaps()
                .setCompleteTime(DATE)
                .setUserId(FIRST_USER_ID)
                .setCategory(CATEGORY)
                .setShortDescription(SHORT_DESCRIPTION)
                .setLongDescription(LONG_DESCRIPTION)
                .setTimeLapsName(NAME);

    }
    @Test
    public void testAddNewTimeLaps() throws Exception {

        timeLapsService.addTimeLaps(timeLaps);

        verify(timeLapsDAO).create(anyObject());

    }



    @Test
    public void testGetTimeLapsById() throws Exception {
        timeLapsService.getTimeLapsById(666L);
        verify(timeLapsDAO).getById(666L);
    }

    @Test
    public void testDeleteAllTimeLaps() throws Exception {
        timeLapsService.deleteAllTimeLaps(666L);
        verify(timeLapsDAO).deleteAllTimeLaps(666L);

    }


}
