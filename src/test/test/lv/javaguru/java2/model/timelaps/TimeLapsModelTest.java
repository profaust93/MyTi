package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.database.jdbc.TimeLapsDAOImpl;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.domain.TimeLapsList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by Ruslan on 2016.04.06..
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLapsModelTest {

    @Mock
    TimeLapsDAO timeLapsDAO;

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
        timeLaps.setTimeLapsName("Test#1");
        timeLaps.setTimeLapsId(666L);

        timeLapsModel.addTimeLaps(timeLaps);

        Boolean status = true;
        for(Map.Entry entry:timeLapsModel.addTimeLaps(timeLaps).entrySet()) {
            String value = (String) entry.getValue();
            if (!value.equalsIgnoreCase("ok")) status = false;
        }
        assertEquals(true,status);

    }
}
