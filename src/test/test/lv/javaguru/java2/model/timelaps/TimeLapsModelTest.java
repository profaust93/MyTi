package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDateTime;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by Ruslan on 2016.04.06..
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLapsModelTest {

    @Mock
    private TimeLapsDAO timeLapsDAO;

    @InjectMocks
    TimeLapsModel timeLapsModel = new TimeLapsModelImpl();

    @Test
    public void testAddNewTimeLaps() throws Exception {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setUserId(1L);
        timeLaps.setCategory("Fun");
        timeLaps.setShortDescription("Short Description");
        timeLaps.setLongDescription("Long Description");
        timeLaps.setTimeLapsName("TestCreate2");

        timeLapsModel.addTimeLaps(timeLaps);

        verify(timeLapsDAO).create(anyObject());

    }

    @Test
    public void testAddNewTimeLapsWithNullFields() throws Exception {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(null);
        timeLaps.setUserId(1L);
        timeLaps.setCategory(null);
        timeLaps.setShortDescription(null);
        timeLaps.setLongDescription(null);
        timeLaps.setTimeLapsName(null);

        timeLapsModel.addTimeLaps(timeLaps);


    }

    @Test
    public void testGetTimeLapsById() throws Exception {
        timeLapsModel.getTimeLapsById(666L);
        verify(timeLapsDAO).getById(666L);
    }


}
