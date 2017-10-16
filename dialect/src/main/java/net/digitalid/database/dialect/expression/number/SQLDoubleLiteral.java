package net.digitalid.database.dialect.expression.number;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.digitalid.utility.annotations.method.Pure;
import net.digitalid.utility.annotations.ownership.NonCaptured;
import net.digitalid.utility.annotations.parameter.Modified;
import net.digitalid.utility.generator.annotations.generators.GenerateBuilder;
import net.digitalid.utility.generator.annotations.generators.GenerateSubclass;
import net.digitalid.utility.storage.interfaces.Unit;
import net.digitalid.utility.validation.annotations.type.Immutable;

import net.digitalid.database.annotations.sql.SQLFraction;
import net.digitalid.database.dialect.SQLDialect;

/**
 * A double literal.
 */
@Immutable
@GenerateBuilder
@GenerateSubclass
public interface SQLDoubleLiteral extends SQLNumberLiteral {
    
    /* -------------------------------------------------- Value -------------------------------------------------- */
    
    /**
     * Returns the value of this double literal.
     */
    @Pure
    public double getValue();
    
    /* -------------------------------------------------- Unparse -------------------------------------------------- */
    
    @Pure
    @Override
    public default void unparse(@Nonnull SQLDialect dialect, @Nonnull Unit unit, @NonCaptured @Modified @Nonnull @SQLFraction StringBuilder string) {
        string.append(new BigDecimal(getValue()).toPlainString());
    }
    
}