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
package io.github.abissell.javautil.concurrent;

import java.util.function.BiPredicate;

public final class LazyVolatile<V, T> {
    private final BiPredicate<T, T> rereadPred;
    private volatile V val;
    private LocalVal<V, T> localVal;

    public LazyVolatile(V initVal, T initTs, BiPredicate<T, T> rereadPred) {
        this.rereadPred = rereadPred;
        this.val = initVal;
        this.localVal = new LocalVal<>(initVal, initTs);
    }

    public V get(T ts) {
        if (rereadPred.test(localVal.ts, ts)) {
            localVal = new LocalVal<>(val, ts);
        }
        return localVal.val;
    }

    public void set(V val, T ts) {
        this.val = val;
        localVal = new LocalVal<>(val, ts);
    }

    private static record LocalVal<V, T>(V val, T ts) { }
}
