package lv.javaguru.java2.service.timelaps;

import lv.javaguru.java2.database.TimeLapsDAO;
import lv.javaguru.java2.domain.TimeLaps;
import lv.javaguru.java2.model.exceptions.TimeLapsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by Ruslan on 2016.04.06..
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLapsServiceTest {

    @Mock
    private TimeLapsDAO timeLapsDAO;

    @InjectMocks
    TimeLapsService timeLapsService = new TimeLapsServiceImpl();

    @Test
    public void testAddNewTimeLaps() throws Exception {
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setCompleteTime(LocalDateTime.now());
        timeLaps.setUserId(1L);
        timeLaps.setCategory("Fun");
        timeLaps.setShortDescription("Short Description");
        timeLaps.setLongDescription("Long Description");
        timeLaps.setTimeLapsName("TestCreate2");

        timeLapsService.addTimeLaps(timeLaps);

        verify(timeLapsDAO).create(anyObject());

    }

    @Test
    public void testAddNewTimeLapsWithNullFields() throws Exception {
        assertEquals("This field must be not empty", checkFields(1L,null,null,null,null,null));
        assertEquals("This field must be not empty", checkFields(1L,"test",null,null,null,null));
        assertEquals("This field must be not empty", checkFields(1L,null,"test",null,null,null));
        assertEquals("ok", checkFields(1L,"test","test",null,null,null));
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

    @Test
    public void testDescriptionsLength() throws Exception {
        String description = "a";
        for (int i = 0; i < 1200; i++){
            description = description.concat("a");
        }
        assertEquals("Too long description, must be shorter(not more than 100 symbols)",
                checkFields(1L,"name","category",LocalDateTime.now(),description,null));
        assertEquals("Too long description, must be shorter(not more than 1000 symbols)",
                checkFields(1L,"name","category",LocalDateTime.now(),null,description));


    }

    public String checkFields(Long userId,String name, String category,LocalDateTime completeTime,
                              String shortDescription, String longDescription){
        TimeLaps timeLaps = new TimeLaps();
        timeLaps.setUserId(userId);
        timeLaps.setCategory(category);
        timeLaps.setTimeLapsName(name);
        timeLaps.setCompleteTime(completeTime);
        timeLaps.setShortDescription(shortDescription);
        timeLaps.setLongDescription(longDescription);
        try {
            for(Map.Entry entry: timeLapsService.addTimeLaps(timeLaps).entrySet()){
                String value = (String) entry.getValue();
                if(!value.equalsIgnoreCase("ok")) throw new TimeLapsException(value);
            }
        } catch (Exception e){
            return e.getMessage();
        }
        return "ok";
    }
}
