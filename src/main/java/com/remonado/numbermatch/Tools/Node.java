package com.remonado.numbermatch.Tools;

import java.util.ArrayList;


/**
 * Clase Node
 * Models a node in an 8 way linked list
 * @author Cecilia M. Curlango Rosas
 * @version 01 2026
 */
public class Node <T>{
    private T info; // INFO part
    private Node up, down,
            left, right,
            downLeft, downRight,
            upLeft, upRight;
    public Node(T info) {
        this.info = info;
        up = null;
        down = null;
        left = null;
        right = null;
        downLeft = null;
        downRight = null;
        upLeft = null;
        upRight = null;
    }
    public Node() {
        info = null;
        up = null;
        down = null;
        left = null;
        right = null;
        downLeft = null;
        downRight = null;
        upLeft = null;
        upRight = null;
    }


    /**
     * Updates all links
     * to neighboring nodes so that
     * nothing points to it anymore.
     */
    public void delete() {
        if(up != null) up.setDown(down);
        if(down != null) down.setUp(up);
        if(right != null) right.setLeft(left);
        if(left != null) left.setRight(right);
        if(downRight != null) downRight.setUpLeft(upLeft);
        if(downLeft != null) downLeft.setUpRight(upRight);
        if(upRight != null) upRight.setDownLeft(downLeft);
        if(upLeft != null) upLeft.setDownRight(downRight);
    }
    /**
     * Returns whether input node is next to
     * node.
     * @return true if nodes are next to each other
     */
    public boolean isNeighbor(Node input) {
        return up == input || down == input || left == input || right == input ||
                downLeft == input || downRight == input ||  upLeft == input || upRight == input;
    }

    public T getInfo() {
        return info;
    }
    public void setInfo(T info) {
        this.info = info;
    }


    public Node getDown() {
        return down;
    }


    public void setDown(Node down) {
        this.down = down;
    }


    public Node getLeft() {
        return left;
    }


    public void setLeft(Node left) {
        this.left = left;
    }


    public Node getRight() {
        return right;
    }


    public void setRight(Node right) {
        this.right = right;
    }


    public Node getDownLeft() {
        return downLeft;
    }


    public void setDownLeft(Node downLeft) {
        this.downLeft = downLeft;
    }


    public Node getDownRight() {
        return downRight;
    }


    public void setDownRight(Node downRight) {
        this.downRight = downRight;
    }


    public Node getUpLeft() {
        return upLeft;
    }


    public void setUpLeft(Node upLeft) {
        this.upLeft = upLeft;
    }


    public Node getUpRight() {
        return upRight;
    }


    public void setUpRight(Node upRight) {
        this.upRight = upRight;
    }


    public Node getUp() {
        return up;
    }


    public void setUp(Node up) {
        this.up = up;
    }


//    public int getNumber() {
//        return number;
//    }
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//

    /**
     * Returns an ArrayList containing all non-empty neighboring nodes
     * @return ArrayList<Node> with neighboring non-empty nodes
     */


    public ArrayList<Node> getNeighbors() {
        ArrayList<Node> neighbors = new ArrayList<>();
        neighbors.add(up);
        neighbors.add(down);
        neighbors.add(right);
        neighbors.add(left);
        neighbors.add(upRight);
        neighbors.add(upLeft);
        neighbors.add(downRight);
        neighbors.add(downLeft);
        return neighbors;
    }
}
