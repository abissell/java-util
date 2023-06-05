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
package com.abissell.javautil.math;

import java.math.RoundingMode;
import java.util.Set;

public final class DoubleRounder {

    private final org.decimal4j.util.DoubleRounder[] rounders;

    public DoubleRounder(Set<Integer> places) {
        int highestPlace = places.stream().max(Integer::compareTo).orElseThrow();
        if (highestPlace > 20) {
            throw new IllegalArgumentException("" + highestPlace);
        }
        rounders = new org.decimal4j.util.DoubleRounder[highestPlace + 1];
        for (int place : places) {
            rounders[place] = new org.decimal4j.util.DoubleRounder(place);
        }
    }

    public double round(double val, int places) {
        return rounders[places].round(val);
    }

    public double round(double val, int places, RoundingMode roundingMode) {
        return rounders[places].round(val, roundingMode);
    }
}
