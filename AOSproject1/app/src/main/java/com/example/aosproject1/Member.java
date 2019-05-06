package com.example.aosproject1;

public class Member {
    private float Expense;
    private float Income;
    private String Description;
    private String Date;
    private String Time;
    private String Category;

    public Member() {
    }

    public float getExpense() {
        return Expense;
    }

    public void setExpense(float expense) {
        Expense = expense;
    }
    public float getIncome() {
        return Income;
    }

    public void setIncome(float income) {
        Income = income;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
