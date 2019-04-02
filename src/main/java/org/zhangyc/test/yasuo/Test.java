package org.zhangyc.test.yasuo;

/**
 * Created by gongye1 on 2017/5/27.
 */
public class Test {
    public static void main(String[] args){
        String str = "aaaaaabbbbbbrrrrrrcccccckkkkkkaaaaaaddddddffffffbbbbbbddddddaaaaaacc";

        Node node = null;
        for(char c : str.toCharArray()){
            if(node == null){
                node = new Node();
                node.setC(c);
                node.setI(1);
            }else{
                if(c == node.getC()){
                    node.setI(node.getI()+1);
                }else{
                    Node temp = new Node();
                    temp = node;
                    node = new Node();
                    temp.setNext(node);
                    node.setPre(temp);
                    node.setC(c);
                    node.setI(1);
                }
            }
        }

//        System.out.print(node.getC()+""+node.getI());
//        System.out.print(node.getPre().getC()+""+node.getPre().getI());

        StringBuilder sb = new StringBuilder();
        getStr(sb, node);
        System.out.print(sb.toString());
    }

    private static void getStr(StringBuilder sb, Node node){
        if(node.getPre() != null){
            getStr(sb, node.getPre());
        }
        sb.append(node.getC()).append(node.getI());
    }
}
