/*
 * Copyright 2021 Dominik Scholl
 *
 * This file is part of jtsgen.
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
 *
 */

package dz.jtsgen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this to configure the name of properties in classes/interfaces annotated with {@link TypeScript}.
 * This enables transpiling use cases where client logic is originally written in java.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.METHOD})
public @interface TSProperty {

    /**
     * Change this if the name of your method in java is not the same like the final name in javascript.
     *
     * @return the custom property name, or empty sting if the actual property name should be used
     */
    String name() default "";

}
