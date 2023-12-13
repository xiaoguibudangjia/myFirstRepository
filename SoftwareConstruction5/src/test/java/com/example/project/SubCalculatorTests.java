/*
 * Copyright 2015-2022 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// https://assertj.github.io/doc/
import static org.assertj.core.api.Assertions.assertThat;

class SubCalculatorTests {
    private SubCalculator subCalculator;

    @BeforeEach
    void init() {
        subCalculator = new SubCalculator();
        System.out.println("init annotatted with @BeforeEach");
    }

    @Test
    @DisplayName("A basic assert")
    void useCodedValue() {
        assertEquals(1, subCalculator.sub(2, 1), "2 - 1 is equal to 1");
    }

    @ParameterizedTest(name = "using CsvSource")
    @CsvSource({
            "3, 1, 2",
            "4, 1, 3"
    })
    void useCsvSource(int first, int second, int expectedResult) {
        assertEquals(expectedResult, subCalculator.sub(first, second),
                () -> first + " - " + second + " is equal to " + expectedResult);
    }

    @ParameterizedTest(name = "using ValueSource")
    @ValueSource(ints = { 3, 2, 1 })
    void useValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @RepeatedTest(value = 5, name = "using RepeatedTest")
    void useRepeatedTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("useRepeatedTest -> " + repetitionInfo.getCurrentRepetition());
        Assertions.assertEquals(3, subCalculator.sub(5, 2), "5 - 2 is equal to 3");
    }

    @ParameterizedTest(name = "using MethodSource")
    @MethodSource("equationProvider")
    void useMethodSource(SimpleTriplet triplet) {
        assertEquals(triplet.result, subCalculator.sub(triplet.op1, triplet.op2), "should equal");
    }

    public static class SimpleTriplet {
        public int op1;
        public int op2;
        public int result;

        public SimpleTriplet(int op1, int op2, int result) {
            this.op1 = op1;
            this.op2 = op2;
            this.result = result;
        }
    }

    public static Set<SimpleTriplet> equationProvider() {
        Set<SimpleTriplet> set = new HashSet<SimpleTriplet>();
        set.add(new SimpleTriplet(1, 0, 1));
        set.add(new SimpleTriplet(3, 1, 2));
        return set;
    }

    @Test
    @DisplayName("using assert*** methods")
    void useAssert() {
        assertAll("testAssertAll",
                () -> assertEquals(1, 1),
                () -> assertEquals(2, 2));

        assertTimeout(java.time.Duration.ofMillis(10), () -> {
            Thread.currentThread().sleep(1);
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
        System.out.println("tearDown annotatted with @AfterEach");
    }
}
