package net.digitalid.database.core.sql;

import javax.annotation.Nonnull;
import net.digitalid.database.core.SQLDialect;
import net.digitalid.database.core.table.Site;
import net.digitalid.utility.annotations.reference.NonCapturable;
import net.digitalid.utility.exceptions.internal.InternalException;

/**
 *
 */
public abstract class Transcriber<N> {
    
    public final @Nonnull Class<N> type;
    
    @SuppressWarnings("unchecked")
    protected Transcriber() {
        this.type = (Class<N>) this.getClass();
    }
    
    protected Transcriber(@Nonnull Class<N> type) {
        this.type = type;
    }
    
    protected abstract void transcribe(@Nonnull SQLDialect dialect, @Nonnull N node, @Nonnull Site site, @Nonnull @NonCapturable StringBuilder string) throws InternalException;
    
    public void transcribeNode(@Nonnull SQLDialect dialect, @Nonnull SQLNode node, @Nonnull Site site, @Nonnull @NonCapturable StringBuilder string) throws InternalException {
        if (type.isInstance(node)) {
            transcribe(dialect, type.cast(node), site, string);
        } else {
            throw InternalException.get("Cannot transcribe node of type '" + node.getClass() + "' with transcriber for type '" + type + "'.");
        }
    }
    
}