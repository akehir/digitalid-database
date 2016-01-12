package net.digitalid.database.sqlite;

import java.util.Map;
import javax.annotation.Nonnull;
import net.digitalid.database.core.SQLDialect;
import net.digitalid.database.core.sql.SQLNode;
import net.digitalid.database.core.sql.Transcriber;
import net.digitalid.database.core.sql.expression.bool.SQLBooleanLiteral;
import net.digitalid.database.core.sql.statement.insert.SQLInsertStatement;
import net.digitalid.database.core.table.Site;
import net.digitalid.utility.annotations.reference.NonCapturable;
import net.digitalid.utility.collections.annotations.elements.NonNullableElements;
import net.digitalid.utility.exceptions.internal.InternalException;

/**
 *
 */
public class SQLiteDialect extends SQLDialect {

    private static @Nonnull @NonNullableElements Map<Class<?>, Transcriber<?>> dialectSpecificTranscribers;
   
    private static void register(Transcriber<?> transcriber) {
        dialectSpecificTranscribers.put(transcriber.type, transcriber);
    }
    
    static {
        register(new Transcriber<SQLBooleanLiteral>(SQLBooleanLiteral.class) {

            @Override
            protected void transcribe(@Nonnull SQLDialect dialect, @Nonnull SQLBooleanLiteral node, @Nonnull Site site, @Nonnull @NonCapturable StringBuilder string) throws InternalException {
                string.append(node.getValue() ? "1" : "0");
            }
        });
    }
    
    @Override
    public void transcribe(@Nonnull Site site, @NonCapturable @Nonnull StringBuilder string, @Nonnull SQLNode<?> node) throws InternalException {
        if (dialectSpecificTranscribers.containsKey(node.getClass())) {
            dialectSpecificTranscribers.get(node.getClass()).transcribeNode(this, node, site, string);
        } else {
            super.transcribe(site, string, node);
        }
    }
    
}