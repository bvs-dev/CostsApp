package com.apps.expensesapp;

public class Debtor {

    public final int id;
    public final String name;
    public final int userDebt;
    public final int gap;
    public final int debt;

    public Debtor(int id, String name, int userDebt, int debt) {
        this.id = id;
        this.name = name;
        this.userDebt = userDebt;
        this.debt = debt;
        this.gap = debt - userDebt;
    }
}
