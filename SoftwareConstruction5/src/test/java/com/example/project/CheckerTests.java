package com.example.project;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CheckerTests {
    private final EquationCheckerOfRange checker = new EquationCheckerOfRange();

    @BeforeEach
    void init(){
        System.out.println("init annotated with @BeforeEach");
    }

    @Test
    @DisplayName("A basic assert")
    void useCodeValue(){
        AddEquation.AddEquationBuilder builder = new AddEquation.AddEquationBuilder();
        builder.setNum1((short) 10).setNum2((short)10);
        assertTrue(checker.check(new AddEquation(builder)),"10 + 10 is in the checker of range [0,100]");
    }

    @ParameterizedTest(name = "using CsvSource")
    @CsvSource({
            "20, 20",
            "100, 0",
            "30, 20",
    })
    void useCsvSource(short num1, short num2){
        SubEquation.SubEquationBuilder subBuilder = new SubEquation.SubEquationBuilder();
        subBuilder.setNum1(num1).setNum2(num2);
        assertTrue(checker.check(new SubEquation(subBuilder)),num1 + " - " + num2 + " is in the checker of range [0,100]");
    }

    @RepeatedTest(value =  5, name = "using RepeatedTest")
    void useRepeatedTest(TestInfo testInfo,  RepetitionInfo repeatedTestInfo){
        AddEquation.AddEquationBuilder addBuilder = new AddEquation.AddEquationBuilder();
        addBuilder.setNum1((short) 10).setNum2((short)10);
        System.out.println("useRepeatedTest -> " + repeatedTestInfo.getCurrentRepetition());
        Assertions.assertTrue(checker.check(new AddEquation(addBuilder)),"10 + 10 is in the checker of range [0,100]");
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

        assertTrue(true);

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

    @AfterEach
    void tearDown() {
        System.out.println("tearDown annotated with @AfterEach");
    }
}
