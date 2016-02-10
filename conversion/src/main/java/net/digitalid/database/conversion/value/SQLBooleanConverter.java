package net.digitalid.database.conversion.value;

import java.lang.annotation.Annotation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.digitalid.utility.contracts.Require;
import net.digitalid.utility.validation.annotations.elements.NonNullableElements;
import net.digitalid.utility.validation.annotations.elements.NullableElements;
import net.digitalid.utility.validation.annotations.reference.NonCapturable;

import net.digitalid.database.core.interfaces.SelectionResult;
import net.digitalid.database.dialect.ast.expression.bool.SQLBooleanLiteral;
import net.digitalid.database.dialect.ast.statement.insert.SQLValues;
import net.digitalid.database.dialect.ast.statement.table.create.SQLType;
import net.digitalid.database.exceptions.operation.FailedValueRestoringException;
import net.digitalid.database.exceptions.operation.FailedValueStoringException;
import net.digitalid.database.exceptions.state.value.CorruptNullValueException;

/**
 *
 */
public class SQLBooleanConverter extends SQLSingleRowConverter<Boolean> {
    
    /* -------------------------------------------------- SQL Type -------------------------------------------------- */
    
    @Override
    public @Nullable SQLType getSQLType(@Nonnull Class<?> type, @Nonnull @NonNullableElements Annotation[] annotations) {
        return SQLType.BOOLEAN;
    }
    
    /* -------------------------------------------------- Value Collection -------------------------------------------------- */
    
    @Override
    public void collectValues(@Nullable Object object, @Nonnull Class<?> type, @Nonnull @NullableElements SQLValues values) throws FailedValueStoringException {
        Require.that(object == null || object instanceof Boolean).orThrow("The object is of type boolean.");
        
        final @Nonnull SQLBooleanLiteral booleanLiteral = SQLBooleanLiteral.get((Boolean) object);
        values.addValue(booleanLiteral);
    }
    
    /* -------------------------------------------------- Recovery -------------------------------------------------- */
    
    @Override
    @Nullable
    public Object recoverNullable(@Nonnull Class<?> type, @NonCapturable @Nonnull SelectionResult result) throws CorruptNullValueException, FailedValueRestoringException {
        final @Nullable Boolean value = result.getBoolean();
        return value;
    }
    
}
