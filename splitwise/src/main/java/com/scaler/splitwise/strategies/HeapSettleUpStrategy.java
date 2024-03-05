package com.scaler.splitwise.strategies;

import com.scaler.splitwise.modles.Expense;
import com.scaler.splitwise.services.Transaction;

import java.util.List;

public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settleUp(List<Expense> expenses){
        return  null;
    }
}
