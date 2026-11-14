package com.gdb.repository;

import com.gdb.domain.IAccount;
import java.util.*;

/**
 * In-memory implementation of AccountRepository backed by a Map.
 */
public class InMemoryAccountRepository implements AccountRepository {

    // ============================================================
    // 📝 STEP 1: Declare Fields DONE
    //
    // INSTRUCTIONS:
    //   1. Declare a private final Map<Integer, IAccount> accounts = new HashMap<>()
    //   2. Declare a private int nextAccountNumber = 1001
    // ============================================================
    // TODO: Step 1 - declare fields done
    private final Map<Integer, IAccount> accounts = new HashMap<>();
    private int nextAccountNumber = 1001;

    // ============================================================
    // 📝 STEP 2: Implement save(IAccount account)
    //
    // INSTRUCTIONS:
    //   Store the account in the map keyed by account.getAccountNumber().
    // ============================================================
    @Override
    public void save(IAccount account) {

        // TODO: Step 2 - save account to map   done
        if (account != null) {
            accounts.put(account.getAccountNumber(), account);
        }

    }

    // ============================================================
    // 📝 STEP 3: Implement findById(int accountNumber)
    //
    // INSTRUCTIONS:
    //   Return the account associated with accountNumber, or null if not present.
    // ============================================================
    @Override
    public IAccount findById(int accountNumber) {
        // TODO: Step 3 - retrieve account by ID done

        return accounts.get(accountNumber);
    }

    // ============================================================
    // 📝 STEP 4: Implement findAll()
    //
    // INSTRUCTIONS:
    //   Return a new List containing all accounts in the map.
    // ============================================================
    @Override
    public List<IAccount> findAll() {
        // TODO: Step 4 - return all accounts as a List done
        return new ArrayList<>(accounts.values());
    }

    // ============================================================
    // 📝 STEP 5: Implement update(IAccount account)
    //
    // INSTRUCTIONS:
    //   Update the account in the map if it exists.
    // ============================================================
    @Override
    public void update(IAccount account) {
        // TODO: Step 5 - update account in map done
        if (account != null && accounts.containsKey(account.getAccountNumber())) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    // ============================================================
    // 📝 STEP 6: Implement delete(int accountNumber)
    //
    // INSTRUCTIONS:
    //   Remove the account with the given accountNumber from the map.
    // ============================================================
    @Override
    public void delete(int accountNumber) {
        // TODO: Step 6 - remove account from map   done
        accounts.remove(accountNumber);
    }

    // ============================================================
    // 📝 STEP 7: Implement exists(int accountNumber)
    //
    // INSTRUCTIONS:
    //   Return true if the map contains the given accountNumber, false otherwise.
    // ============================================================
    @Override
    public boolean exists(int accountNumber) {
        // TODO: Step 7 - check if account exists   done

        return accounts.containsKey(accountNumber);
    }

    // ============================================================
    // 📝 STEP 8: Implement nextAccountNumber()  DONE
    //
    // INSTRUCTIONS:
    //   Return current nextAccountNumber and post-increment it.
    // ============================================================
    @Override
    public synchronized int nextAccountNumber() {
        // TODO: Step 8 - return and increment nextAccountNumber done
        return this.nextAccountNumber++;
    }
}
