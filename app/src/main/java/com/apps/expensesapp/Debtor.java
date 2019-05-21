package com.apps.expensesapp;

public class Debtor {

    public final int id;
    public final String name;
    public final double userDebt;
    public final double gap;
    public final double debt;

    public Debtor(int id, String name, double userDebt, double debt) {
        this.id = id;
        this.name = name;
        this.userDebt = userDebt;
        this.debt = debt;
        this.gap = debt - userDebt;
    }
}
