package com.example.machvan;
/* Developed by Roee Weisbert (C) March, 9 2024
   You are eligible to use this app on your responsibility
   You are permitted to distribute and use this code
   for any learning\teaching purposes you see fit
   Share the credit - don't take it to yourself!
*/
public class Subject {
    private String name;
    private boolean advanced;

    public Subject(String name) {
        this.name = name;
        this.advanced = ((name.split("-")[0]).equals("9") || (name.split("-")[0]).equals("10"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public boolean equals(Object obj) {
        return getName().equals(((Subject)obj).getName());
    }

    public int getSubjectType(){
        return Integer.valueOf(getName().split("-")[0]);
    }

}
