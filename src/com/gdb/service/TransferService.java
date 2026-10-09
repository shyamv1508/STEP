package com.gdb.service;

import com.gdb.domain.*;
import com.gdb.exceptions.*;

public class TransferService {

    public TransferService() {
    }

    public void transfer(IAccount from, IAccount to, double amount, int pin)
            throws AccountException {
        if (from == null || to == null) {
            throw new AccountException("Source and destination accounts are required");
        }
        if (from == to || from.getAccountNumber().equals(to.getAccountNumber())) {
            throw new AccountException("Source and destination accounts must be different");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Transfer amount must be positive");
        }
        if (!"ACTIVE".equalsIgnoreCase(from.getStatus())
                || !"ACTIVE".equalsIgnoreCase(to.getStatus())) {
            throw new InactiveAccountException("Both accounts must be active to transfer funds");
        }

        AbstractAccount source = (AbstractAccount) from;
        if (!source.verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }
        if (!source.canWithdraw(amount)) {
            throw new InsufficientBalanceException(
                    "Insufficient balance for transfer of Rs. " + amount
                    + " (minimum balance must be maintained)");
        }

        source.resetDailyTransferIfNeeded();
        if (!source.canTransfer(amount)) {
            throw new AccountException("Daily transfer limit exceeded. Remaining today: Rs. "
                    + source.getRemainingDailyTransferLimit());
        }

        // All checks have passed. Complete the debit and credit, then record the transfer.
        from.withdraw(amount, String.valueOf(pin));
        to.deposit(amount);
        source.updateDailyTransferTotal(amount);
    }
}