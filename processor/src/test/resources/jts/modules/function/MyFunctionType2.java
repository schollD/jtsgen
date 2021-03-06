/*
 * Copyright (c) 2021 Dominik Scholl
 *
 * This file is part of jtsgen.
 *
 * jtsgen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jtsgen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jtsgen.  If not, see http://www.gnu.org/licenses/
 *
 */

package jts.modules.function;

import dz.jtsgen.annotations.TSMethod;
import dz.jtsgen.annotations.TypeScript;
import dz.jtsgen.processor.test.Nullable;

/**
 * Interface doc
 */
@TypeScript(name = "FooFunction")
@FunctionalInterface
public interface MyFunctionType2 {

    /**
     * method doc
     * @param a
     * @param b
     * @return some return value
     */
    @Nullable
    @TSMethod
    Object calc(@Nullable Object a, Object b);

}