/*
 * Copyright (C) 2017 Synacts GmbH, Switzerland (info@synacts.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.digitalid.database.jdbc.decoder;

import java.io.InputStream;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import net.digitalid.utility.annotations.method.Impure;
import net.digitalid.utility.contracts.Ensure;
import net.digitalid.utility.generator.annotations.generators.GenerateBuilder;
import net.digitalid.utility.generator.annotations.generators.GenerateSubclass;
import net.digitalid.utility.validation.annotations.size.Size;
import net.digitalid.utility.validation.annotations.type.Mutable;

import net.digitalid.database.exceptions.DatabaseException;
import net.digitalid.database.exceptions.DatabaseExceptionBuilder;
import net.digitalid.database.interfaces.SQLDecoder;
import net.digitalid.database.jdbc.encoder.JDBCEncoder;

/**
 * This class implements the {@link SQLDecoder} for JDBC.
 * 
 * @see JDBCEncoder
 */
@Mutable
@GenerateBuilder
@GenerateSubclass
public abstract class JDBCDecoder extends SQLDecoder {
    
    /* -------------------------------------------------- Result Set -------------------------------------------------- */
    
    /**
     * The result set from which we read the data.
     */
    private final @Nonnull ResultSet resultSet;
    
    /* -------------------------------------------------- Column Index -------------------------------------------------- */
    
    /**
     * Indicates at which column we currently are.
     */
    private int columnIndex;
    
    /* -------------------------------------------------- Constructor -------------------------------------------------- */
    
    /**
     * Constructs a new JDBC decoder
     */
    protected JDBCDecoder(@Nonnull ResultSet resultSet) {
        this.resultSet = resultSet;
        this.columnIndex = 1;
    }
    
    /* -------------------------------------------------- Move -------------------------------------------------- */
    
    @Impure
    @Override
    public boolean moveToNextRow() throws DatabaseException {
        try {
            this.columnIndex = 1;
            return resultSet.next();
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    @Impure
    @Override
    public boolean moveToFirstRow() throws DatabaseException {
        try {
            this.columnIndex = 1;
            return resultSet.first();
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    /* -------------------------------------------------- Closing -------------------------------------------------- */
    
    @Impure
    @Override
    public void close() throws DatabaseException {
        try {
            resultSet.close();
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    /* -------------------------------------------------- Nullness -------------------------------------------------- */
    
    @Impure
    @Override
    public boolean wasNull() throws DatabaseException {
        try {
            return resultSet.wasNull();
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    /* -------------------------------------------------- Decoding -------------------------------------------------- */
    
    @Impure
    @Override
    public boolean decodeBoolean() throws DatabaseException {
        try {
            return resultSet.getBoolean(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public byte decodeInteger08() throws DatabaseException {
        try {
            return resultSet.getByte(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public short decodeInteger16() throws DatabaseException {
        try {
            return resultSet.getShort(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public int decodeInteger32() throws DatabaseException {
        try {
            return resultSet.getInt(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public long decodeInteger64() throws DatabaseException {
        try {
            return resultSet.getLong(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public @Nonnull BigInteger decodeInteger() throws DatabaseException {
        try {
            final @Nonnull byte[] bytes = resultSet.getBytes(columnIndex++);
            if (wasNull()) { throwNullException(); }
            final @Nonnull BigInteger bigInteger = new BigInteger(bytes);
            return bigInteger;
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    @Impure
    @Override
    public float decodeDecimal32() throws DatabaseException {
        try {
            return resultSet.getFloat(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public double decodeDecimal64() throws DatabaseException {
        try {
            return resultSet.getDouble(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public char decodeString01() throws DatabaseException {
        try {
            final @Nonnull String string = resultSet.getString(columnIndex++);
            if (wasNull()) { throwNullException(); }
            Ensure.that(string.length() == 1).orThrow("The encoded object is not a character.");
            return string.charAt(0);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    @Impure
    @Override
    public @Nonnull String decodeString64() throws DatabaseException {
        try {
            final @Nonnull String string = resultSet.getString(columnIndex++);
            if (wasNull()) { throwNullException(); }
            Ensure.that(string.length() <= 64).orThrow("The encoded object is not a string with less or equal than 64 characters.");
            return string;
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    @Impure
    @Override
    public @Nonnull String decodeString() throws DatabaseException {
        try {
            return resultSet.getString(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public @Nonnull @Size(16) byte[] decodeBinary128() throws DatabaseException {
        try {
            final @Nonnull byte[] bytes = resultSet.getBytes(columnIndex++);
            if (wasNull()) { throwNullException(); }
            Ensure.that(bytes.length <= 16).orThrow("The encoded byte array is not less or equal to 16 bytes");
            return bytes;
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    @Impure
    @Override
    public @Nonnull @Size(32) byte[] decodeBinary256() throws DatabaseException {
        try {
            final @Nonnull byte[] bytes = resultSet.getBytes(columnIndex++);
            if (wasNull()) { throwNullException(); }
            Ensure.that(bytes.length <= 16).orThrow("The encoded byte array is not less or equal to 16 bytes");
            return bytes;
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        }
    }
    
    @Impure
    @Override
    public @Nonnull byte[] decodeBinary() throws DatabaseException {
        try {
            return resultSet.getBytes(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
    @Impure
    @Override
    public @Nonnull InputStream decodeBinaryStream() throws DatabaseException {
        try {
            return resultSet.getBinaryStream(columnIndex++);
        } catch (SQLException exception) {
            throw DatabaseExceptionBuilder.withCause(exception).build();
        } finally {
            if (wasNull()) { throwNullException(); }
        }
    }
    
}
