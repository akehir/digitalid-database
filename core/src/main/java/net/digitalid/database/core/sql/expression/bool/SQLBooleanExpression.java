package net.digitalid.database.core.sql.expression.bool;

import javax.annotation.Nonnull;
import net.digitalid.database.core.sql.expression.SQLExpression;
import net.digitalid.utility.annotations.state.Immutable;
import net.digitalid.utility.annotations.state.Pure;

/**
 * This class implements an expression that evaluates to a boolean.
 */
@Immutable
public abstract class SQLBooleanExpression implements SQLExpression {
    
    /* -------------------------------------------------- Unary Operations -------------------------------------------------- */
    
    /**
     * Returns the negated value of this boolean expression.
     * 
     * @return the negated value of this boolean expression.
     */
    @Pure
    public final @Nonnull SQLUnaryBooleanExpression negated() {
        return SQLUnaryBooleanExpression.get(SQLUnaryBooleanOperator.NOT, this);
    }
    
    /* -------------------------------------------------- Binary Operations -------------------------------------------------- */
    
    /**
     * Returns the logical conjunction with the given boolean expression.
     * 
     * @param expression the boolean expression to combine with this one.
     * 
     * @return the logical conjunction with the given boolean expression.
     */
    @Pure
    public final @Nonnull SQLBinaryBooleanExpression and(@Nonnull SQLBooleanExpression expression) {
        return SQLBinaryBooleanExpression.get(SQLBinaryBooleanOperator.AND, this, expression);
    }
    
    /**
     * Returns the inclusive disjunction with the given boolean expression.
     * 
     * @param expression the boolean expression to combine with this one.
     * 
     * @return the inclusive disjunction with the given boolean expression.
     */
    @Pure
    public final @Nonnull SQLBinaryBooleanExpression or(@Nonnull SQLBooleanExpression expression) {
        return SQLBinaryBooleanExpression.get(SQLBinaryBooleanOperator.OR, this, expression);
    }
    
    /**
     * Returns the exclusive disjunction with the given boolean expression.
     * 
     * @param expression the boolean expression to combine with this one.
     * 
     * @return the exclusive disjunction with the given boolean expression.
     */
    @Pure
    public final @Nonnull SQLBinaryBooleanExpression xor(@Nonnull SQLBooleanExpression expression) {
        return SQLBinaryBooleanExpression.get(SQLBinaryBooleanOperator.XOR, this, expression);
    }
    
    /**
     * Returns the logical equality with the given boolean expression.
     * 
     * @param expression the boolean expression to combine with this one.
     * 
     * @return the logical equality with the given boolean expression.
     */
    @Pure
    public final @Nonnull SQLBinaryBooleanExpression equal(@Nonnull SQLBooleanExpression expression) {
        return SQLBinaryBooleanExpression.get(SQLBinaryBooleanOperator.EQUAL, this, expression);
    }
    
    /**
     * Returns the logical inequality with the given boolean expression.
     * 
     * @param expression the boolean expression to combine with this one.
     * 
     * @return the logical inequality with the given boolean expression.
     */
    @Pure
    public final @Nonnull SQLBinaryBooleanExpression unequal(@Nonnull SQLBooleanExpression expression) {
        return SQLBinaryBooleanExpression.get(SQLBinaryBooleanOperator.UNEQUAL, this, expression);
    }
    
}