/*
 * Copyright (C) 2017 Synacts GmbH, Switzerland (info@synacts.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.digitalid.database.conversion;

import javax.annotation.Nonnull;

import net.digitalid.utility.annotations.generics.Specifiable;
import net.digitalid.utility.annotations.method.Pure;
import net.digitalid.utility.annotations.ownership.NonCaptured;
import net.digitalid.utility.annotations.parameter.Modified;
import net.digitalid.utility.conversion.interfaces.Converter;
import net.digitalid.utility.generator.annotations.generators.GenerateBuilder;
import net.digitalid.utility.generator.annotations.generators.GenerateSubclass;
import net.digitalid.utility.rootclass.RootClass;
import net.digitalid.utility.validation.annotations.generation.Default;
import net.digitalid.utility.validation.annotations.type.Immutable;

import net.digitalid.database.exceptions.DatabaseException;
import net.digitalid.database.interfaces.encoder.SQLEncoder;

/**
 * This class models a simple where condition.
 */
@Immutable
@GenerateBuilder
@GenerateSubclass
public abstract class WhereCondition<@Specifiable WHERE_TYPE> extends RootClass {
    
    /* -------------------------------------------------- Fields -------------------------------------------------- */
    
    /**
     * Returns the converter that encodes the {@link #getObject() object}.
     */
    @Pure
    public abstract @Nonnull Converter<WHERE_TYPE, ?> getConverter();
    
    /**
     * Returns the object that is encoded by the {@link #getConverter() converter}.
     */
    @Pure
    public abstract WHERE_TYPE getObject();
    
    /**
     * Returns the prefix of the columns which this where condition should match.
     */
    @Pure
    @Default("\"\"")
    public abstract @Nonnull String getPrefix();
    
    /* -------------------------------------------------- Encoding -------------------------------------------------- */
    
    /**
     * Encodes the object of this where condition with the given encoder.
     */
    @Pure
    public void encode(@NonCaptured @Modified @Nonnull SQLEncoder encoder) throws DatabaseException {
        encoder.encodeNullableObject(getConverter(), getObject());
    }
    
}
