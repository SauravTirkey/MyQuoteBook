package com.scaler.splitwise.services;

import com.scaler.splitwise.modles.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private User from;
    private User to;
    private int amount;
}
