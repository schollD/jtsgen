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

package dz.jtsgen.processor.model;

import dz.jtsgen.processor.model.rendering.TSMethodElement;
import dz.jtsgen.processor.model.rendering.TSMethodVisitor;

import java.util.Map;
import java.util.Optional;

/** This super class is needed for immutables */
public interface TSMethod extends TSMethodElement {

    String getName() ;

    TSTargetType getType();

    Map<String, TSTargetType> getArguments();

    Optional<String> getComment();

    void accept(TSMethodVisitor visitor, int ident);

    TSMethod changedTSTarget(TSTargetType newTargetType, Map<String, TSTargetType> newArguments);

}
