
package com.remonado.numbermatch.Tools;

public class List<T> {

    private Node<T> inicio;
    public List() {
        inicio = null;

    }

    public boolean isEmpty() {
        return inicio == null;
    }

    public void insertarFinal(T dato) {
        Node<T> nuevo = new Node<>(dato);
        if(inicio == null) {
            nuevo.setRight(inicio);
            inicio = nuevo;
        }else{
            Node<T> aux = inicio;
            while(aux.getRight() != null) {
                aux = aux.getRight();
            }
            aux.setRight(nuevo);
            nuevo.setRight(null);
        }
    }
    public void insertarInicio(T dato){
        Node<T> nuevo = new Node<>(dato);
        nuevo.setRight(inicio);
        inicio = nuevo;
    }
    public void setInicio(Node<T> inicio) {
        this.inicio = inicio;
    }
    public T removeInicio() {
        T erased = null;
        if(inicio == null) {
            erased = null;
        }else{
            erased = (T)inicio.getInfo();
            inicio = inicio.getRight();
        }
        return erased;
    }
    public T removeFinal() {
        T erased = null;
        if(inicio == null) {
            erased = null;
        }else{
            if(inicio.getRight() == null) {
                erased = (T)inicio.getInfo();
                inicio = null;
            }else{
                Node<T> r = inicio;
                Node<T> a = r;
                while(r.getRight() != null) {
                    a = r;
                    r = r.getRight();
                }
                erased = r.getInfo();
                a.setRight(null);
            }
        }
        return erased;
    }
    public Node<T> getInicioNode() {
        return inicio;
    }
    public Node<T> get(int index){
        if(index<0) return null;
        Node<T> r = inicio;
        for(int i = 0; i < index && r != null; i++){
            r = r.getRight();
        }
        return r;
    }

    public int getSize() {
        int size = 0;
        Node<T> r = inicio;
        while(r != null) {
            size++;
            r = r.getRight();
        }
        return size;
    }


}