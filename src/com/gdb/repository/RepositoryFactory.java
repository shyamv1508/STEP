package com.gdb.repository;

import java.io.*;
import java.util.Properties;

/**
 * Factory creating and supplying repository instances based on persistence.properties configuration.
 */
public class RepositoryFactory {

    private static AccountRepository accountRepositoryInstance;
    private static TransactionRepository transactionRepositoryInstance;
    private static final String CONFIG_RESOURCE = "/config/persistence.properties";

    // ============================================================
    // 📝 STEP 14: Read persistence.mode from Configuration
    //
    // INSTRUCTIONS:
    //   1. Load properties from "config/persistence.properties" or classpath resource.
    //   2. Return property value for "persistence.mode" (default to "memory" if missing).
    // ============================================================
    public static String getPersistenceMode() {
        // TODO: Step 14 - read persistence.mode from properties    done
        Properties properties = new Properties();

        try (InputStream input =
                     RepositoryFactory.class.getResourceAsStream(CONFIG_RESOURCE)) {
            if (input != null) {
                properties.load(input);
            } else {
                // Fallback for running from a project directory.
                try (InputStream fileInput =
                             new FileInputStream("src/main/resources/config/persistence.properties")) {
                    properties.load(fileInput);
                }
            }
        } catch (IOException e) {
            // Keep the default mode if the configuration cannot be read.
        }

        return properties.getProperty("persistence.mode", "memory").trim();

    }

    // ============================================================
    // 📝 STEP 15: Implement getAccountRepository()
    //
    // INSTRUCTIONS:
    //   1. Check persistence mode:
    //      - "memory" -> return singleton InMemoryAccountRepository
    //      - "jdbc"   -> throw UnsupportedOperationException("JDBC repository not implemented yet - coming in Activity 22")
    //      - "file"   -> throw UnsupportedOperationException("File repository not implemented yet")
    //      - default  -> return InMemoryAccountRepository
    // ============================================================
    public static synchronized AccountRepository getAccountRepository() {
        // TODO: Step 15 - return appropriate AccountRepository
        return null;
    }

    // ============================================================
    // 📝 STEP 16: Implement getTransactionRepository()
    //
    // INSTRUCTIONS:
    //   1. Check persistence mode:
    //      - "memory" -> return singleton InMemoryTransactionRepository
    //      - "jdbc"   -> throw UnsupportedOperationException("JDBC repository not implemented yet - coming in Activity 22")
    //      - "file"   -> throw UnsupportedOperationException("File repository not implemented yet")
    //      - default  -> return InMemoryTransactionRepository
    // ============================================================
    public static synchronized TransactionRepository getTransactionRepository() {
        // TODO: Step 16 - return appropriate TransactionRepository
        return null;
    }
}
