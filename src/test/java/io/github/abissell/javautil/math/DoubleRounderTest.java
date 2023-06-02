/*
 * Copyright 2023 Andrew Bissell. All Rights Reserved.
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
package io.github.abissell.javautil.math;

import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleRounderTest {
    @Test
    public void testDoubleRounder() {
        var doubleRounder = new DoubleRounder(Set.of(0, 3, 5));
        assertEquals(1.0d, doubleRounder.round(1.1d, 0));
        assertEquals(1.0d, doubleRounder.round(1.1d, 0, RoundingMode.DOWN));
        assertEquals(1.0d, doubleRounder.round(0.9d, 0));
        assertEquals(0.0d, doubleRounder.round(0.9d, 0, RoundingMode.DOWN));
        assertEquals(123.123d, doubleRounder.round(123.1229, 3));
        assertEquals(123.123d, doubleRounder.round(123.12349, 3));
        assertEquals(123.124d, doubleRounder.round(123.12351, 3));
        assertEquals(123.123d, doubleRounder.round(123.1239999, 3, RoundingMode.DOWN));
        assertEquals(123.12345d, doubleRounder.round(123.123459999, 5, RoundingMode.DOWN));
        assertEquals(123.12345d, doubleRounder.round(123.123440001, 5, RoundingMode.UP));
    }
}
