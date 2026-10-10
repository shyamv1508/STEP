package com.gdb.logging;

import com.gdb.command.TransactionCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLogDestination implements LogDestination {
    // ============================================================
    // 📝 STEP 5: Declare Field
    // ============================================================
    private TransactionLog log;

    // ============================================================
    // 📝 STEP 6: Constructor
    // ============================================================
    //  initialize log = new TransactionLog()
    public FileLogDestination() {
        log = new TransactionLog();

    }

    // ============================================================
    // 📝 STEP 7: write(cmd)
    // ============================================================
    //  delegate to log.log(cmd)
    @Override
    public void write(TransactionCommand cmd) {
        //  Step 7 - delegate to log.log(cmd)
        try {
            log.log(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ============================================================
    // 📝 STEP 8: readAll()
    // ============================================================
    //  delegate to log.readAll()
    @Override
    public List<TransactionCommand> readAll() {
        //  Step 8 - delegate to log.readAll()
        try {
            return log.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // ============================================================
    // 📝 STEP 9: clear()
    // ============================================================
    //  delegate to log.clear()
    @Override
    public void clear() {
        //  Step 9 - delegate to log.clear()
        log.clear();
    }

    // ============================================================
    // 📝 STEP 10: getDestinationName()
    // ============================================================
    // return "FILE"
    @Override
    public String getDestinationName() {
        // Step 10 - return "FILE"
        return "FILE";
    }
}
