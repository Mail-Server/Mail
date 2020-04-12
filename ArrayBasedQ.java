package com.company;

public class ArrayBasedQ implements IQueue {

    public static void main(String[] args) {
	ArrayBasedQ q=new ArrayBasedQ();
	q.enqueue(5);
	q.enqueue(3);
	q.enqueue(7);
	q.enqueue(8);
	q.enqueue(10);
	int j=q.size();
	for(int i=0;i<j;i++) {
        q.dequeue();
    }

	if(q.isEmpty())
	    System.out.println("faddddy");

	q.show();

    }
    int f=0,r=0,count=0;
    int N=50;
    Object[] arr=new Object[N];


    public void show(){
        for(int i=f;i<r;i++)
            System.out.println(arr[i]+" ");
    }

    @Override
    public void enqueue(Object item) {
        if(count==N)
            throw new RuntimeException("Full Queue");
        arr[r]=item;
        if(r==N-1){
            r=0;
        }else{
            r++;
        }
        count++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty())
            throw new RuntimeException("Empty Queue");
        Object temp=arr[f];
        arr[f]=null;
        if(f==N-1){
            f=0;
        }else{
            f++;
        }
        count--;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        if(count==0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return count;
    }
}
