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
package com.abissell.javautil.io;

import java.text.DecimalFormat;

public enum ThreadLocalFormat {
    ; // Enum singleton

    private static final ThreadLocal<DecimalFormat> with8SigDigits =
            ThreadLocal.withInitial(() -> new DecimalFormat("###,###,###,###,###,##0.00#######"));
    private static final ThreadLocal<DecimalFormat> with4SigDigits =
            ThreadLocal.withInitial(() -> new DecimalFormat("###,###,###,###,###,##0.00#######"));

    public static DecimalFormat with8SigDigits() {
        return with8SigDigits.get();
    }

    public static DecimalFormat with4SigDigits() {
        return with4SigDigits.get();
    }
}
