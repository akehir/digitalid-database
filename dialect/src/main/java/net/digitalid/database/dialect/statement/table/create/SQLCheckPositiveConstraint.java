package net.digitalid.database.dialect.statement.table.create;

import javax.annotation.Nonnull;

import net.digitalid.utility.annotations.method.Pure;
import net.digitalid.utility.annotations.ownership.NonCaptured;
import net.digitalid.utility.contracts.Require;
import net.digitalid.utility.conversion.model.CustomAnnotation;
import net.digitalid.utility.validation.annotations.math.Positive;

import net.digitalid.database.dialect.expression.SQLExpression;
import net.digitalid.database.dialect.expression.bool.SQLComparisonOperator;
import net.digitalid.database.dialect.expression.bool.SQLNumberComparisonBooleanExpression;
import net.digitalid.database.dialect.expression.number.SQLNumberLiteral;
import net.digitalid.database.dialect.expression.number.SQLNumberReference;
import net.digitalid.database.exceptions.operation.FailedSQLValueConversionException;
import net.digitalid.database.interfaces.SQLEncoder;

/**
 * This SQL node represents a check-positive constraint.
 */
public class SQLCheckPositiveConstraint extends SQLCheckConstraint {
    
    /* -------------------------------------------------- Check Constraint -------------------------------------------------- */
    
    /**
     * The expression for the check constraint.
     */
    private final @Nonnull SQLExpression checkConstraint;
    
    @Pure
    @Override
    protected @Nonnull SQLExpression<?> getCheckConstraint() {
        return checkConstraint;
    }
    
    /* -------------------------------------------------- Constructor -------------------------------------------------- */
    
    /**
     * Creates an SQL check non-positive constraint instance for the {@link Positive @Positive} annotation and a given column name.
     */
    SQLCheckPositiveConstraint(@Nonnull CustomAnnotation annotation, @Nonnull String columnName) {
        Require.that(annotation.getAnnotationType().isAssignableFrom(Positive.class)).orThrow("The annotation @Positive is present.");
        
        checkConstraint = SQLNumberComparisonBooleanExpression.get(SQLComparisonOperator.GREATER, SQLNumberReference.get(columnName), SQLNumberLiteral.get(0L));
    }
    
    /* -------------------------------------------------- SQL Parameterized Node -------------------------------------------------- */
    
    @Pure
    @Override
    public void storeValues(@NonCaptured @Nonnull SQLEncoder collector) throws FailedSQLValueConversionException {
        collector.setInteger64(0L);
    }
    
}