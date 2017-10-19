package net.digitalid.database.client;

import java.sql.SQLException;

import javax.annotation.Nonnull;

import net.digitalid.utility.annotations.method.PureWithSideEffects;
import net.digitalid.utility.configuration.Configuration;
import net.digitalid.utility.file.Files;
import net.digitalid.utility.initialization.annotations.Initialize;
import net.digitalid.utility.validation.annotations.type.Utility;

import net.digitalid.database.interfaces.Database;
import net.digitalid.database.jdbc.JDBCDatabaseBuilder;

import org.sqlite.JDBC;

/**
 * This class initializes the database for the client.
 */
@Utility
public abstract class ClientDatabaseInitializer {
    
    /* -------------------------------------------------- Configuration -------------------------------------------------- */
    
    /**
     * Stores the file name of the SQLite database (without the suffix).
     */
    public static final @Nonnull Configuration<String> fileName = Configuration.with("client");
    
    /* -------------------------------------------------- Initialization -------------------------------------------------- */
    
    /**
     * Initializes the database with the configured SQLite file.
     */
    @PureWithSideEffects
    @Initialize(target = Database.class, dependencies = {Files.class, ClientDatabaseInitializer.class})
    public static void initializeDatabase() throws SQLException {
        final @Nonnull String URL = "jdbc:sqlite:" + Files.relativeToConfigurationDirectory(fileName.get() + ".db");
        Database.instance.set(JDBCDatabaseBuilder.withDriver(new JDBC()).withURL(URL).build());
    }
    
}
