package lv.javaguru.java2.service.validators;

import lv.javaguru.java2.timelaps.database.TimeLapsDAO;
import lv.javaguru.java2.timelaps.domain.TimeLaps;
import lv.javaguru.java2.timelaps.service.TimeLapsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest {

    private TimeLaps timeLaps;

    private final LocalDateTime DATE = LocalDateTime.now();
    private final String SHORT_DESCRIPTION = "Short Description";
    private final String LONG_DESCRIPTION = "Long Description";
    private final String CATEGORY = "Sport";
    private final Long FIRST_USER_ID = 12L;
    private final String NAME = "TimeLaps";

    @Mock
    private TimeLapsDAO timeLapsDAO;

    @Mock
    private TimeLapsService timeLapsService;

    @InjectMocks
    Validators validators = new ValidatorsImpl();


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

    @Ignore
    @Test
    public void testTimeLapsValidator() throws Exception {
        validators.timeLapsValidator(timeLaps);

    }
}
