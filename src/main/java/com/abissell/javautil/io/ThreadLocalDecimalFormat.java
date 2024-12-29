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

import java.text.DecimalFormat;

public enum ThreadLocalDecimalFormat {
    ; // Enum singleton

    @SuppressWarnings("unchecked")
    private static final ThreadLocal<DecimalFormat>[] formatters = (ThreadLocal<DecimalFormat>[]) new ThreadLocal[20];
    static {
        for (int i = 0; i < formatters.length; i++) {
            final String pattern = switch (i) {
                case 0 -> "###,###,###,###,###,###,###,###,###,##0";
                case 1 -> "###,###,###,###,###,###,###,###,###,##0.0";
                case 2 -> "###,###,###,###,###,###,###,###,###,##0.00";
                default -> {
                    var builder = "###,###,###,###,###,###,###,###,###,##0.00";
                    for (int place = 3; place <= i; place++) {
                        //noinspection StringConcatenationInLoop
                        builder += '#';
                    }
                    yield builder;
                }
            };

            formatters[i] = ThreadLocal.withInitial(() -> new DecimalFormat(pattern));
        }
    }

    public static DecimalFormat with8SigDigits() {
        return formatters[8].get();
    }

    public static DecimalFormat with4SigDigits() {
        return formatters[4].get();
    }

    public static DecimalFormat with2SigDigits() {
        return formatters[2].get();
    }

    public static DecimalFormat withSigDigits(int digits) {
        return formatters[digits].get();
    }
}
