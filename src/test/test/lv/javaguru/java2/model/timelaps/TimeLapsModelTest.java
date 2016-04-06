package lv.javaguru.java2.model.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Ruslan on 2016.04.06..
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLapsModelTest {

    @Mock
    TimeLapsDAO timeLapsDAO;

    @InjectMocks
    TimeLapsModel timeLapsModel = new TimeLapsModelImpl();



}
