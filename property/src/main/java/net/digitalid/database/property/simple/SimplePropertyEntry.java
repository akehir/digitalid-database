package net.digitalid.database.property.simple;

import javax.annotation.Nonnull;

import net.digitalid.utility.annotations.method.Pure;
import net.digitalid.utility.generator.annotations.generators.GenerateSubclass;
import net.digitalid.utility.validation.annotations.type.Immutable;

import net.digitalid.database.property.PropertyEntry;

/**
 * Description.
 */
@Immutable
@GenerateSubclass
public interface SimplePropertyEntry<O, V> extends PropertyEntry<O, V> {
    
    /**
     * Returns the value to which the property belongs.
     */
    @Pure
    public @Nonnull V getValue();
    
}