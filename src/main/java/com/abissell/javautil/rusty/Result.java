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
package com.abissell.javautil.rusty;

import java.util.function.Function;

public sealed interface Result<T, E extends ErrType<E>> permits Ok, Err {
    default boolean isOk() {
        return this instanceof Ok;
    }

    default boolean isErr() {
        return this instanceof Err;
    }

    default Opt<E> err() {
        return switch (this) {
            case Err<T, E>(E e) -> Opt.of(e);
            case Ok<T, E>(T t) -> Opt.none();
        };
    }

    default Opt<T> ok() {
        return switch (this) {
            case Ok<T, E>(T t) -> Opt.of(t);
            case Err<T, E>(E e) -> Opt.none();
        };
    }

    default <O> Opt<O> map(Function<T, O> mapper) {
        return switch (this) {
            case Ok<T, E>(T t) -> Opt.of(mapper.apply(t));
            case Err<T, E>(E __) -> Opt.none();
        };
    }

    static <T, E extends ErrType<E>> Result<T, E> of(T t) {
        return new Ok<>(t);
    }

    static <T, E extends ErrType<E>> Result<T, E> err(E e) {
        return e.err();
    }
}
