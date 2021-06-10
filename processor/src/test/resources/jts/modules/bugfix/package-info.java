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

@TSModule(
        moduleName = "MethodRealScenarioTest",
        outputType = OutputType.NO_MODULE,
        author = "Me",
        authorUrl = "Https://some.url",
        printDate = true,
        excludes = {
                "^sun",
                "^jdk.internal",
                "^java.lang.",
                "^com.google.gwt"
        },
        enumExportStrategy = EnumExportStrategy.STRING,
        customTypeMappings = {
                "com.google.gwt.dom.client.Element -> Element",
        },
        appendOriginalNamesToJavaDoc = true
)
package jts.modules.bugfix;

import dz.jtsgen.annotations.EnumExportStrategy;
import dz.jtsgen.annotations.OutputType;
import dz.jtsgen.annotations.TSModule;