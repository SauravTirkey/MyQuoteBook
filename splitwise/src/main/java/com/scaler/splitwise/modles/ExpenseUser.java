package com.scaler.splitwise.modles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import javax.print.attribute.standard.OrientationRequested;

@Getter
@Setter
@Entity
public class ExpenseUser extends BaseModel{
    private int amount;
    @ManyToOne
    private Expense expense;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;
}
