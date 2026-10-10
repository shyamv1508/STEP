package com.gdb.logging;

import com.gdb.command.TransactionCommand;
import com.gdb.db.SimulatedDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DatabaseLogDestination implements LogDestination {
    private static final String TABLE = "transaction_log";

    // ============================================================
    // 📝 STEP 11: Declare Field
    // ============================================================
    private final SimulatedDatabase db;

    // ============================================================
    // 📝 STEP 12: Constructor
    // ============================================================
    // implement constructor accepting SimulatedDatabase
    public DatabaseLogDestination(SimulatedDatabase db) {
        //  Step 12 - implement constructor
        this.db = db;
    }

    // ============================================================
    // 📝 STEP 13: write(cmd)
    // ============================================================
    // insert command into database table
    @Override
    public void write(TransactionCommand cmd) {
        //  Step 13 - insert command into database table
        db.insert(TABLE, cmd);
    }

    // ============================================================
    // 📝 STEP 14: readAll()
    //
    // INSTRUCTIONS:
    //   1. Call db.selectAll(TABLE).
    //   2. Map/cast each Object to TransactionCommand.
    //   3. Collect and return List<TransactionCommand>.
    // ============================================================
    // retrieve and return all commands from database
    @Override
    public List<TransactionCommand> readAll() {
        //  Step 14 - retrieve and return all commands from database
        List<TransactionCommand> transactions = new ArrayList<>();
        for(Object obj : db.selectAll(TABLE)) {
            transactions.add((TransactionCommand)obj);
        }
        return  transactions;

    }

    // ============================================================
    // 📝 STEP 15: clear()
    // ============================================================
    // delete all records from database table
    @Override
    public void clear() {
        //  Step 15 - delete all records from database table
        db.deleteAll(TABLE);
    }

    // ============================================================
    // 📝 STEP 16: getDestinationName()
    // ============================================================
    //  return "DATABASE"
    @Override
    public String getDestinationName() {
        // Step 16 - return "DATABASE"
        return "DATABASE";
    }
}
