import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Time2JUnitTest {
    Time2 time0;

    @Test
    void testDefaultConstructor(){
        time0 = new Time2();
        assertEquals(0, time0.getHour());
        assertEquals(0, time0.getMinute());
        assertEquals(0, time0.getSecond());
    }

    @Test
    void testConstructorWithOneArg() {
        time0 = new Time2(1);
        assertEquals(1, time0.getHour());
    }

    @Test
    void testConstructorWithTwoArgs() {
        time0 = new Time2(1, 7);
        assertEquals(1, time0.getHour());
        assertEquals(7, time0.getMinute());
    }

    @Test
    void testSetHourValid () {
        time0 = new Time2();
        time0.setHour(10);
        assertEquals(10, time0.getHour());
    }

    @Test
    void testSetMinuteValid () {
        time0 = new Time2();
        time0.setMinute(10);
        assertEquals(10, time0.getMinute());
    }

    @Test
    void testSetSecondValid () {
        time0 = new Time2();
        time0.setSecond(10);
        assertEquals(10, time0.getSecond());
    }

    @ParameterizedTest(name="run #{index} with [{arguments}]")
    @CsvSource({"0,0,0,12:00:00 AM", "12,00,00,12:00:00 PM", "15,00,00,3:00:00 PM"})
    void testToString(int hour, int minute, int second, String correctTime){
        time0 = new Time2(hour, minute, second);
        assertEquals(time0.toString(), correctTime);
    }

    @ParameterizedTest
    @ValueSource (ints = {-1, 24})
    void testSetHourInvalid (int hour) {
        time0 = new Time2();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> time0.setHour(hour));
        assertEquals("hour must be 0-23", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource (ints = {-1, 60})
    void testSetMinuteInvalid (int minute){
        time0 = new Time2();
        assertThrows(IllegalArgumentException.class, () -> time0.setMinute(minute));
    }

    @ParameterizedTest
    @ValueSource (ints = {-1, 60})
    void testSetSecondInvalid (int second){
        time0 = new Time2();
        assertThrows(IllegalArgumentException.class, () -> time0.setSecond(second));
    }

    //Invalid Constructor Tests, Should All Throw Illegal Argument Exceptions
    @ParameterizedTest(name="run #{index} with [{arguments}]")
    @CsvSource({"-1,10,10", "24,10,10", "10,-1,10", "10,70,10", "10,50,-1","10,50,70"})
    void invalidConstructor3Arguments(int hour, int minute, int second){
        assertThrows(IllegalArgumentException.class, () -> new Time2(hour, minute, second));
    }

    @ParameterizedTest(name="run #{index} with [{arguments}]")
    @CsvSource({"-1,10", "24,10", "10,-1", "10,70"})
    void invalidConstructor2Args(int hour, int minute){
        assertThrows(IllegalArgumentException.class, () -> new Time2(hour, minute));
    }

    @ParameterizedTest
    @ValueSource (ints = {-1, 24, 59})
    void invalidConstructorOneArg(int hour){
        assertThrows(IllegalArgumentException.class, () -> new Time2(hour));
    }

    // This also tests toUniversalString
    @Test
    void copyConstructor(){
        Time2 original = new Time2(1,2,3);
        Time2 copy = new Time2(original);
        //deep copy not shallow
        original.setHour(10);
        assertEquals(copy.toUniversalString(),"01:02:03");
    }

    @Test
    void testConstructorWithThreeArgs() {
        time0 = new Time2(1, 30, 15);
        assertAll("time 1:30:15",
                () -> assertEquals(1, time0.getHour()),
                () -> assertEquals(30, time0.getMinute()),
                () -> assertEquals(15, time0.getSecond())
        );
    }
}