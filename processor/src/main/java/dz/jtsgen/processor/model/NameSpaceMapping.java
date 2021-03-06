/*
 * Copyright (c) 2017 Dragan Zuvic
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

package dz.jtsgen.processor.model;

import org.immutables.value.Value;

/**
 * just a simple representation of a name space mapping
 *
 * the empty string represents the root name space
 */
@Value.Immutable
public abstract class NameSpaceMapping {

    @Value.Parameter
    public abstract String originNameSpace();

    @Value.Parameter
    public abstract String targetNameSpace();

    @Value.Default
    public boolean exact() {
        return false;
    }

    @Override
    public String toString() {
        return (this.exact() ? "=" : "") + this.originNameSpace() +" -> " + this.targetNameSpace();
    }
}
