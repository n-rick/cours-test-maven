package com.example.coursunitmaven;

public class Calcul {
    public int somme(int x,int y){
        return x + y;
    }
    public int division(int x,int y){
        if( y == 0 ) throw new ArithmeticException();
        return x / y;
    }
}
