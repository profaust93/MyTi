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
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

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
        timeLaps.setTimeLapsName("Test#1");
        timeLaps.setTimeLapsId(666L);

        timeLapsModel.setTimeLapsDAO(new TimeLapsDAOImpl());
        timeLapsModel.addTimeLaps(timeLaps);



        List<TimeLapsList> timeLapsLists = timeLapsModel.getAllTimeLapsForUser("1");

        Optional<TimeLapsList> filtered = timeLapsLists
                .stream()
                .filter(t -> t.getTimeLapsName().matches("Test#1"))
                .filter(t -> t.getTimeLapsId().equals(timeLaps.getTimeLapsId()))  // 666 не работает (и результат другой у getTimeLapsId)
                .filter(t -> t.getCategory().matches("Fun"))
                .findAny();
        assertTrue(filtered.isPresent());

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
        timeLaps.setTimeLapsId(null);

        timeLapsModel.addTimeLaps(timeLaps);

    }

    @Test
    public void testGetTimeLapsById() throws Exception {
        timeLapsModel.getTimeLapsById(666L);
        verify(timeLapsDAO).getById(666L);
    }


}
