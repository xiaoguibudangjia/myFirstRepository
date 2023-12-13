package com.example.project;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// https://assertj.github.io/doc/
import static org.assertj.core.api.Assertions.assertThat;

public class EquationCountTests {
    private EquationCollection equationCollection;

    @BeforeEach
    void init(){
        equationCollection = new EquationCollection();
        System.out.println("init annotated with @BeforeEach");
    }

    @Test
    @DisplayName("A basic assert")
    void useCodeValue(){
        int count = 0;
        equationCollection.generate(10,EquationCheckerOfRange.getInstance());
        for(IEquation equation:equationCollection){
            count++;
        }
        assertEquals(10,count,"the count of Equation is 10");
    }


    @RepeatedTest(value = 5, name = "using RepeatedTest")
    void useRepeatedTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int count = 0;
        equationCollection.generate(10,EquationCheckerOfRange.getInstance());
        for(IEquation equation:equationCollection){
            count++;
        }
        System.out.println("useRepeatedTest -> " + repetitionInfo.getCurrentRepetition());
        Assertions.assertEquals(10,count, "the count of Equation is 10");
    }

    @ParameterizedTest(name = "using ValueSource")
    @ValueSource(ints = { 1, 2, 3 })
    void useValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @Test
    @DisplayName("using assert*** methods")
    void useAssert() {
        assertAll("testAssertAll",
                () -> assertEquals(1, 1),
                () -> assertEquals(2, 2));

        assertTimeout(java.time.Duration.ofMillis(10), () -> {
            Thread.sleep(1);
        });

        assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                throw new RuntimeException();
            }
        });
    }

    @Test
    @DisplayName("using assertJ methods")
    void useAssertJ() {
        assertThat("www.ncwu.edu.cn").isNotNull()
                .startsWith("www")
                .contains("ncwu")
                .endsWith("cn");
    }

    @Disabled
    @Test
    public void useDisabled() {
        assertTrue(true);
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown annotated with @AfterEach");
    }
}
