package net.digitalid.database.property.simple;

import net.digitalid.utility.annotations.method.Pure;
import net.digitalid.utility.generator.annotations.generators.GenerateSubclass;
import net.digitalid.utility.validation.annotations.type.Immutable;

import net.digitalid.database.annotations.metadata.Embedded;
import net.digitalid.database.property.PropertyEntry;

/**
 * This class models a database entry of the {@link SimpleObjectProperty}.
 */
@Immutable
@GenerateSubclass
public interface SimplePropertyEntry<O, V> extends PropertyEntry<O, V> {
    
    /* -------------------------------------------------- Value -------------------------------------------------- */
    
    /**
     * Returns the value to which the property belongs.
     */
    @Pure
    @Embedded // TODO: This is dependent on the actual type (and should cause a foreign key constraint if necessary).
    public V getValue();
    
}
