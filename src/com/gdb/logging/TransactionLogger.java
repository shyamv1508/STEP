package com.gdb.logging;

import com.gdb.command.TransactionCommand;
import java.util.ArrayList;
import java.util.List;

/**
 * Bridge abstraction class decoupling high-level logger API from pluggable storage backends.
 */
public class TransactionLogger {
    // ============================================================
    // 📝 STEP 17: Declare Field
    // ============================================================
    protected LogDestination destination;

    // ============================================================
    // 📝 STEP 18: Constructor
    // ============================================================
    // implement constructor accepting LogDestination
    public TransactionLogger(LogDestination destination) {
        //  Step 18 - implement constructor
        this.destination = destination;
    }

    // ============================================================
    // 📝 STEP 19: setDestination
    // ============================================================
    //  implement setter for hot-swapping destination
    public void setDestination(LogDestination destination) {
        // Step 19 - implement setter
        this.destination = destination;

    }

    // ============================================================
    // 📝 STEP 20: log(TransactionCommand cmd)
    // ============================================================
    // delegate to destination.write(cmd)
    public void log(TransactionCommand cmd) {
        destination.write(cmd);
    }

    // ============================================================
    // 📝 STEP 21: readAll()
    // ============================================================
    // delegate to destination.readAll()
    public List<TransactionCommand> readAll() {
        destination.readAll();
        return new ArrayList<>();
    }

    // ============================================================
    // 📝 STEP 22: clear()
    // ============================================================
    // delegate to destination.clear()
    public void clear() {
        destination.clear();
    }

    // ============================================================
    // 📝 STEP 23: getDestinationName()
    // ============================================================
    // delegate to destination.getDestinationName()
    public String getDestinationName() {
        destination.getDestinationName();
        return "";
    }
}
