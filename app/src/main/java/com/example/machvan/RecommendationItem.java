package com.example.machvan;
/* Developed by Roee Weisbert (C) March, 9 2024
   You are eligible to use this app on your responsibility
   You are permitted to distribute and use this code
   for any learning\teaching purposes you see fit
   Share the credit - don't take it to yourself!
*/

public class RecommendationItem {
    private int Saif6,Saif9,Saif10;
    private boolean Saif7IsRemote;

    public RecommendationItem(int saif6, int saif9, int saif10, boolean saif7IsRemote) {
        Saif6 = saif6;
        Saif9 = saif9;
        Saif10 = saif10;
        Saif7IsRemote = saif7IsRemote;
    }

    public int getSaif6() {
        return Saif6;
    }

    public void setSaif6(int saif6) {
        Saif6 = saif6;
    }

    public int getSaif9() {
        return Saif9;
    }

    public void setSaif9(int saif9) {
        Saif9 = saif9;
    }

    public int getSaif10() {
        return Saif10;
    }

    public void setSaif10(int saif10) {
        Saif10 = saif10;
    }

    public boolean isSaif7IsRemote() {
        return Saif7IsRemote;
    }

    public void setSaif7IsRemote(boolean saif7IsRemote) {
        Saif7IsRemote = saif7IsRemote;
    }

}
