package org.zhangyc.test.yasuo;

/**
 * Created by gongye1 on 2017/5/27.
 */
public class Node {
    private char c;
    private int i;
    private Node pre;
    private Node next;

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
