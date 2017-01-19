package net.digitalid.database.dialect.statement.update;

import javax.annotation.Nonnull;

import net.digitalid.utility.immutable.ImmutableList;

import net.digitalid.database.dialect.SQLDialect;
import net.digitalid.database.dialect.expression.SQLParameter;
import net.digitalid.database.dialect.expression.number.SQLNumberLiteralBuilder;
import net.digitalid.database.dialect.statement.SQLStatementTest;
import net.digitalid.database.subject.site.SimpleSite;

import org.junit.Test;

public class SQLUpdateStatementTest extends SQLStatementTest {
    
    @Test
    public void testSQLUpdateStatement() {
        final @Nonnull SQLAssignment assignment = SQLAssignmentBuilder.withColumn(thirdColumn).withExpression(SQLParameter.INSTANCE).build();
        final @Nonnull SQLUpdateStatement updateStatement = SQLUpdateStatementBuilder.withTable(qualifiedTable).withAssignments(ImmutableList.withElements(assignment)).withWhereClause(firstColumn.equal(SQLNumberLiteralBuilder.withValue(8l).build())).build();
        assertEquals("UPDATE \"default\".\"test_table\" SET \"third_column\" = ? WHERE (\"first_column\") = (8)", SQLDialect.unparse(updateStatement, SimpleSite.INSTANCE));
    }
    
}