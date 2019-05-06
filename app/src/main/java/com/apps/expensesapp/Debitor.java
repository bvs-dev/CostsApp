package com.apps.costsapp;

public class Debitor {

    public final int id;
    public final String name;
    public final double userDebt;
    public final double debt;
    public final double gap;

    public Debitor(int id, String name, double userDebt, double debt) {
        this.id = id;
        this.name = name;
        this.userDebt = userDebt;
        this.debt = debt;
        this.gap = userDebt - debt;
    }
}
