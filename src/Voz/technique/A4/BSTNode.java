package Voz.technique.A4;

import java.util.Scanner;

class BSTNode<T> {
    BSTNode left, right;
    T data;
    String parts;

    public BSTNode() {
        parts= null;
        left = null;
        right = null;
        data = null;
    }

    /* Constructor */
    public BSTNode(T n) {
        parts = null;
        left = null;
        right = null;
        data = n;
    }

    public BSTNode(T n, String parts) {
        this.parts = parts;
        left = null;
        right = null;
        data = n;
    }

    /* Function to set left node */
    public void setLeft(BSTNode n) {
        left = n;
    }

    /* Function to set right node */
    public void setRight(BSTNode n) {
        right = n;
    }

    /* Function to get left node */
    public BSTNode getLeft() {
        return left;
    }

    /* Function to get right node */
    public BSTNode getRight() {
        return right;
    }
    public void setParts(String parts){this.parts = parts;}
    public String getParts(){return parts;}
    /* Function to set data to node */
    public void setElement(T d) {
        data = d;
    }
    public T getElement(){ return data;}
}