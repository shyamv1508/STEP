package com.gdb.logging;

import com.gdb.command.TransactionCommand;
import java.io.*;
import java.util.*;

public class TransactionLog {
    private static final String FILE_PATH = "data/transactions.ser";

    // ============================================================
    // Helper: AppendableObjectOutputStream (COMPLETE — non-placeholder)
    // ============================================================
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }
        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write header when appending to an existing stream
        }
    }

    // ============================================================
    // 📝 STEP 7: Implement log(TransactionCommand cmd)
    //
    // INSTRUCTIONS:
    //   1. Create parent directory (data/) if missing.
    //   2. If file does not exist or is empty (length == 0), use new ObjectOutputStream(...).
    //   3. If file already has data, use new AppendableObjectOutputStream(new FileOutputStream(file, true)).
    //   4. Write cmd object, flush, and close stream.
    // ============================================================
    //  log transaction command to binary file

    public synchronized void log(TransactionCommand cmd) throws IOException {
        File file = new File(FILE_PATH);

        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        ObjectOutputStream out;

        if (!file.exists() || file.length() == 0) {
            out = new ObjectOutputStream(new FileOutputStream(file));
        } else {
            out = new AppendableObjectOutputStream(
                    new FileOutputStream(file, true)
            );
        }

        out.writeObject(cmd);
        out.flush();
        out.close();
    }


    // ============================================================
    // 📝 STEP 8: Implement readAll()
    //
    // INSTRUCTIONS:
    //   1. If file does not exist, return empty List.
    //   2. Open ObjectInputStream and loop calling readObject() until EOFException.
    //   3. Collect and return List<TransactionCommand>.
    // ============================================================
    // TODO: read and return all logged transaction commands
    public synchronized List<TransactionCommand> readAll() throws IOException, ClassNotFoundException {
        // TODO: Step 8 - deserialize all transaction commands from file
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        ObjectInputStream in;
        in = new ObjectInputStream(new FileInputStream(file));

        return (List<TransactionCommand>) in.readObject();
    }

    // ============================================================
    // 📝 STEP 9: Implement clear()
    //
    // INSTRUCTIONS:
    //   Delete the log file if it exists.
    // ============================================================
    // TODO: delete log file to reset history
    public synchronized void clear() {
        // TODO: Step 9 - delete log file if it exists

    }
}
