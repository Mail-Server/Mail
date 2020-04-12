package com.company;

import java.awt.dnd.DnDConstants;

public class Study2 implements ILinkedList {

    class DNode{
        Object data;
        DNode next;
        DNode prev;
    }
    DNode head,tail;
    static Study2 list2 = new Study2();

    public static void main(String[] args){
        list2.add(5);
        list2.add(12);
        list2.insertAtStart(10);
        list2.insertAtStart(16);
        list2.add(1,1);

        list2.clear();
        try{
        list2.deleteAtStart();
        }
        catch (Exception e){
            System.out.println("you can't Delete an empty list");
        };


        //System.out.println(list2.size());
        //list2.show();
    }



    public void insertAtStart(Object element){ // insert element at the start
        DNode node = new DNode();
        node.data=element;
        node.next=head;
        node.prev=null;
        head=node;
    }

    public void deleteAtStart() throws NullPointerException{
        if(head == null){
            throw new NullPointerException("You can't delete an empty List");
        }else if (head.next==null){
            head=null;
        }else{
            head=head.next;
            head.prev=null;
        }

    }

    @Override
    public void add(int index, Object element) {
        DNode node = new DNode();
        node.data=element;
        if(index==0){
            insertAtStart(element);
        }else{
            DNode n = head;
            for(int i=0; i<index-1;i++){
                n= n.next;
            }
            n.next.prev=node;
            node.next=n.next;
            node.prev=n;
            n.next=node;
        }


    }

    @Override
    public void add(Object element) {
        DNode node = new DNode();
        node.data=element;
        if(head == null){
            head=tail=node;
            head.prev=null;
            tail.next=null;
        }else{
            tail.next=node;
            node.prev=tail;
            tail=node;
            tail.next=null;
        }
    }

    public void show(){
        DNode node = head;
        if(head == null){
            throw new NullPointerException();
        }else{
            while (node!=null){
                System.out.println(node.data);
                node=node.next;
            }
        }
    }

    @Override
    public Object get(int index) {
        DNode node = head;
        for(int i =0 ;i<index;i++){
            node = node.next;
        }
        return node.data;
    }

    @Override
    public void set(int index, Object element) {
        DNode node = head;
        for(int i=0 ;i <index;i++){
            node=node.next;
        }
        node.data=element;
    }

    @Override
    public void clear() {
        if (head == null) {
            throw new NullPointerException();
        } else {
            DNode node = head;
            while (node != null) {
                node = node.next;
                deleteAtStart();
            }
        }
    }

    @Override
    public boolean isEmpty() {
        if(head == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void remove(int index) {
        if (head == null) {
            throw new NullPointerException();
        } else {
            DNode node = head;
            if (index == 0) {
                deleteAtStart();
            } else {
                for (int i = 0; i < index - 1; i++) {
                    node = node.next;
                }
                node.next = node.next.next;
                node.next.prev = node;
            }
        }
    }

    @Override
    public int size() {
        DNode n = head;
        int count=0;
        if(head == null){
            return count;
        }else{
            while(n!=null){
                count++;
                n=n.next;
            }
            return count;
        }
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        Study2 kilo = new Study2();
        for(int i = fromIndex; i<=toIndex; i++){
            kilo.add(list2.get(i));
        }
        kilo.show();
        return kilo;
    }

    @Override
    public boolean contains(Object o) {
        DNode n=head;
        while(n.next!=null){
            if(n.data==o){
                return true;
            }else{
                n=n.next;
            }
        }
        if(n.data==o){
            return true;
        }else{
            return false;
        }
    }
}
