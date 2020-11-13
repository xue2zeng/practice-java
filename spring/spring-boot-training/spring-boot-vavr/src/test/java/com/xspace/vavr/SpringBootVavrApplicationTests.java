package com.xspace.vavr;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SpringBootVavrApplicationTests {

    @Test
    public void givenValueWhenNullCheckNeededThenCorrect() {
        Object possibleNullObj = null;
        if (possibleNullObj == null) {
            possibleNullObj = "someDefaultValue";
        }
        assertNotNull(possibleNullObj);
    }

    @Test
    public void givenValueWhenNullCheckNeededThenCorrect2() {
        Object possibleNullObj = null;
        Exception exception = assertThrows(
                NullPointerException.class,
                () -> possibleNullObj.toString());

        assertEquals(null, exception.getMessage());
    }

    @Test
    public void givenValueWhenCreatesOptionThenCorrect() {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals("None", noneOption.toString());
        assertEquals("Some(val)", someOption.toString());
    }

    @Test
    public void givenNullWhenCreatesOptionThenCorrect() {
        String name = null;
        Option<String> nameOption = Option.of(name);

        assertEquals("baeldung", nameOption.getOrElse("baeldung"));
    }

    @Test
    public void givenNonNullWhenCreatesOptionThenCorrect() {
        String name = "baeldung";
        Option<String> nameOption = Option.of(name);

        assertEquals("baeldung", nameOption.getOrElse("notbaeldung"));
    }
}
