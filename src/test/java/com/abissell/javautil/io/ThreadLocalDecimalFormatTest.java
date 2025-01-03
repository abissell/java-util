/*
 * Copyright 2024 Andrew Bissell. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.abissell.javautil.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ThreadLocalDecimalFormatTest {
    @Test
    public void test0SigDigits() {
        var toTest = 12345678.12345678d;
        var formatted = ThreadLocalDecimalFormat.withSigDigits(0).format(toTest);
        assertEquals("12,345,678", formatted);
    }

    @Test
    public void test1SigDigits() {
        var toTest = 12345678.12345678d;
        var formatted = ThreadLocalDecimalFormat.withSigDigits(1).format(toTest);
        assertEquals("12,345,678.1", formatted);
    }

    @Test
    public void test2SigDigits() {
        var toTest = 12345678.12345678d;
        var formatted = ThreadLocalDecimalFormat.with2SigDigits().format(toTest);
        assertEquals("12,345,678.12", formatted);
    }

    @Test
    public void test3SigDigits() {
        var toTest = 12345678.12345678d;
        var formatted = ThreadLocalDecimalFormat.withSigDigits(3).format(toTest);
        assertEquals("12,345,678.123", formatted);
    }

    @Test
    public void test4SigDigits() {
        var toTest = 12345678.12345678d;
        var formatted = ThreadLocalDecimalFormat.with4SigDigits().format(toTest);
        assertEquals("12,345,678.1235", formatted);
    }

    @Test
    public void test8SigDigits() {
        var toTest = 12345678.123456789d;
        var formatted = ThreadLocalDecimalFormat.with8SigDigits().format(toTest);
        assertEquals("12,345,678.12345679", formatted);
    }
}
