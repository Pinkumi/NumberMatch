package com.remonado.numbermatch.Tools;

import com.remonado.numbermatch.Logic.Game;

public class Pila<T> {
    private T[] pila;
    private int tope;
    public Pila(int capacidad){
        pila = (T[]) new Object[capacidad]; //Creamos el arreglo de objetos T
        tope= -1; 
    }
    public Pila(){
        pila = (T[]) new Object[10];
        tope= -1;
    }
    public void push(T dato){//Agrega un dato a la pila
        if (pila_llena()){ 
            System.out.println("Desbordamiento");
        }else{
            tope++;
            pila[tope]=dato;
        }
    }
    public T pop() { //toma un dato de la pila 
        if (pila_vacia()) {
            System.out.println("Subdesbordamiento");
            return null;
        }
        T elemento = pila[tope];
        tope--;
        return elemento;
    }
    public T getTope(){ //para verificar el valor del objeto superior
        if(pila_vacia())return null;
        return pila[tope];
    }
    public boolean pila_vacia(){
        return tope==-1;
    }
    public boolean pila_llena(){
        return tope ==pila.length-1;
    }
    @Override
    public String toString(){
        StringBuilder strb = new StringBuilder();
        strb.append("[ ");
        for(int i =0; i<tope+1;i++){
            strb.append(pila[i]);
            strb.append(" > ");
        }
        strb.append(" ]");
        return strb.toString();
    }


}