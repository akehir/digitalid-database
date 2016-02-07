package net.digitalid.database.conversion.testenvironment.iterable;

import java.util.List;

import javax.annotation.Nonnull;

import net.digitalid.utility.collections.freezable.FreezableArrayList;
import net.digitalid.utility.conversion.annotations.Constructing;
import net.digitalid.utility.conversion.annotations.GenericTypes;
import net.digitalid.utility.generator.conversion.Convertible;
import net.digitalid.utility.validation.annotations.elements.NonNullableElements;

/**
 *
 */
public class CompositeCollectionsClass implements Convertible {
    
    @GenericTypes(Integer.class)
    private static class ListOfIntegers extends FreezableArrayList<Integer> {}
    
    @GenericTypes(ListOfIntegers.class)
    public final @Nonnull @NonNullableElements FreezableArrayList<FreezableArrayList<Integer>> listOfListOfIntegers;
    
    private CompositeCollectionsClass(@Nonnull @NonNullableElements FreezableArrayList<FreezableArrayList<Integer>> listOfListOfIntegers) {
        this.listOfListOfIntegers = listOfListOfIntegers;
    }
    
    @Constructing
    public static @Nonnull CompositeCollectionsClass get(@Nonnull @NonNullableElements FreezableArrayList<FreezableArrayList<Integer>> listOfListOfIntegers) {
        return new CompositeCollectionsClass(listOfListOfIntegers);
    }
    
}
