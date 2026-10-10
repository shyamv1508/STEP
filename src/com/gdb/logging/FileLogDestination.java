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
        File f = new File("transactions.log");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    // 📝 STEP 8: readAll()
    // ============================================================
    //  delegate to log.readAll()
    @Override
    public List<TransactionCommand> readAll() {
        //  Step 8 - delegate to log.readAll()
        File file = new File("transactions.log");
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        List<TransactionCommand> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
        }catch (IOException e){}
        return list;

    }

    // ============================================================
    // 📝 STEP 9: clear()
    // ============================================================
    //  delegate to log.clear()
    @Override
    public void clear() {
        //  Step 9 - delegate to log.clear()
        File file = new File("transactions.log");
        if (file.exists()) {
            file.delete();
        }
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
