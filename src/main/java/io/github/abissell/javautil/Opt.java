/*
 *
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
package io.github.abissell.javautil;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface Opt<T> permits Some, None {
    default void ifPresent(Consumer<? super T> consumer) {
        switch (this) {
            case Some<T>(T t) -> consumer.accept(t);
            case None<T>() -> {
            }
        }
    }

    default T orElse(T ifAbsent) {
        return switch (this) {
            case Some<T>(T t) -> t;
            case None<T>() -> ifAbsent;
        };
    }

    default T orElseGet(Supplier<? extends T> supplier) {
        return switch (this) {
            case Some<T>(T t) -> t;
            case None<T>() -> supplier.get();
        };
    }

    default <U> Opt<U> map(Function<? super T, ? extends U> mapper) {
        return switch (this) {
            case Some<T>(T t) -> Opt.of(mapper.apply(t));
            case None<T>() -> Opt.none();
        };
    }

    default boolean isSome() {
        return this instanceof Some;
    }

    default boolean isNone() {
        return !isSome();
    }

    default T get() throws NoSuchElementException {
        return switch (this) {
            case Some<T>(T t) -> t;
            case None<T>() -> throw new NoSuchElementException();
        };
    }

    public static <T> Opt<T> none() {
        return None.none();
    }

    public static <T> Opt<T> of(T t) {
        return new Some<>(t);
    }

    public static <T> Opt<T> ofNullable(T t) {
        return t == null ? None.none() : new Some<>(t);
    }
}
