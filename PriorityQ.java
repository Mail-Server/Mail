package com.company;

public class PriorityQ implements IPriorityQueue{

    public class Node2{
        private Object element;
        private int access;
        private Node2 next;

        public Node2(Object obj, Node2 n){
            element=obj;
            next=n;
        }

        public Node2() {}

        public int getAccess() {
            return access;
        }

        public void setAccess(int acc) {
            access = acc;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object obj) {
            element = obj;
        }

        public Node2 getNext() {
            return next;
        }

        public void setNext(Node2 newNext) {
            next = newNext;
        }
    }
    private int size=0;
    private Node2 head;
    private Node2 rear;

    public static void main(String[] args) {
        PriorityQ pr=new PriorityQ();
        pr.insert(5,1);
        pr.insert(10,2);
        pr.insert(8,4);
        System.out.println("The removed is "+pr.removeMin());
        System.out.println("The min is "+pr.min());
        pr.show();
        System.out.println("The size is "+pr.size());
    }


    @Override
    public void insert(Object item, int key) {
        Node2 node=new Node2();
        node.setElement(item);
        node.setAccess(key);
        if(size==0){
            head=node;
        }else {
            Node2 n=head;
            for(int i=0;i<size;i++){
                while(n.next!=null) {
                    if (key > n.getAccess()) {
                        n = n.next;
                    } else
                        break;
                }
            }
            n.next=node;
        }
        size++;
    }

    @Override
    public Object removeMin() {
        if(isEmpty())
            throw new RuntimeException("Empty Queue");

        Object temp=head.getElement();
        head=head.getNext();
        size--;
        if(isEmpty())
            rear=null;
        return temp;
    }

    @Override
    public Object min() {
        if(isEmpty())
            throw new RuntimeException("Empty Queue");
        return head.getElement();
    }

    @Override
    public boolean isEmpty() {
        if(size==0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    public void show(){
        Node2 node=head;
        for(int i=0;i<size;i++){
            System.out.println(node.getElement());
            node=node.getNext();
        }
    }
}
