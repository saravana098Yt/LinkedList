package project;



public class Linkedlist<E>{
	transient Node<E> head;
	transient Node<E> last;
	transient int size=0;
	
	public Linkedlist() {
	}
	
	public Linkedlist(Linkedlist<E> list) {
		addAll(list);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(E elem){
	Node<E> node=new Node(elem,null,null);
	if(head == null){
		head = node;
	}
	else{
		Node<E> currNode = head;
		while(currNode.nextNode != null){
			currNode=currNode.nextNode;
		}
		
		currNode.nextNode = node;
		last = node;
		last.prevNode=currNode;
	}
	size++;
	}
	


	public E remove(int index) {
		Node<E> currNode=head;
		Node<E> prevNode=null;
		for(int i=0;i<index;i++){
			if(currNode != null && currNode.nextNode != null) {
				prevNode=currNode;
				currNode=currNode.nextNode;
			}
			
		}
		Node<E> nodeToconnect=currNode.nextNode;
		currNode.nextNode=null;
		prevNode.nextNode=nodeToconnect;
		size--;
		return (E) currNode.data;
	}
	
	
	public E remove(E elem){
		Node<E> currNode=head;
		Node<E> prevNode=null;
		while(currNode.nextNode != null){
			
        if(currNode.data == elem){
				Node<E> Nodeto=currNode.nextNode;
				currNode.nextNode=null;
				prevNode.nextNode=Nodeto;
				break;
				
			}
        else{
        prevNode=currNode;
		currNode=currNode.nextNode;
        }
        
		}
		size--;
		return (E) currNode.data;
	}
	
	
	public String toString(){
		StringBuffer f1=new StringBuffer("[");
		Node<E> currNode=head;
		while(currNode.nextNode != null) {
			f1=f1.append(currNode.data).append(", ");
			currNode=currNode.nextNode;
		}
		f1.append(currNode.data);
		f1=f1.append("]");
		return f1.toString();
	}
	
	public int indexOf(Object o) {
		return indexOfRange(0,o,size);
	}
    public int lastIndexOf(Object o) {
    	return lastIndexOfRange(0,0,size);
    }
    public int indexOf(Object o,int start) {
		return indexOfRange(start,o,size);
	}
    public int lastIndexOf(Object o,int start) {
    	return lastIndexOfRange(start,0,size);
    }
   
	private int indexOfRange(int i, Object o, int size2) {
		int index=0;
		if(o == null) {
			for(Node<E> node=head;node!= null;node=node.nextNode) {
				if(o == null && index >= i && index <= size2) {
					return index;
				}
				index++;
			}
		}
		else {
			for(Node<E> node=head;node!= null;node=node.nextNode) {
				if(o.equals(node.data) && index >= i && index <= size2) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}
	
	private int lastIndexOfRange(int i, Object o, int size2) {
		int index=size-1;
		if(o == null) {
			for(Node<E> node=last;node != null;node=node.prevNode) {
				if(o == null && index >= i && index < size2) {
					return index;
				}
				index--;
			}
		}
		else {
			for(Node<E> node=last;node!= null;node=node.prevNode) {
				if(o.equals(node.data) && index >= i && index < size2) {
					return index;
				}
				index--;
			}
		}
		return -1;
	}
	
	public void add(int index,E ele) {
		Node<E> currNode;
		if(index == size) {
			add(ele);
		}
		else {
			int index1=0;
			for(Node<E> node=head;node != null;node=node.nextNode) {
				if(index == index1) {
					currNode=node;
					currNode.data=ele;
					break;
				}
				index1++;
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean addAll(int index,Linkedlist<E> list) {
		Object[] a=list.toArray();
		int numNew=a.length;
		if(numNew == 0) {
			return false;
		}
		Node<E> succ,pred;
		
		if(index == size) {
			succ=null;
			pred=last;
		}
		else {
			succ = node(index);
			pred = succ.prevNode;
		}
		
		for(Object o: a) {
			E value= (E) o;
			@SuppressWarnings("rawtypes")
			Node<E> node=new Node(value,pred,null);
			
			if(pred == null) {
				head = node;
			}
			else {
				pred.nextNode=node;
				pred=node;
			}
		}
			if(succ == null) {
				last=pred;
			}
			else {
				pred.nextNode=succ;
				succ.prevNode=pred;
			}
			size+=numNew;
			return true;
	}



	private Node<E> node(int index) {
		int index1=0;
		for(Node<E> node=head;node != null;node=node.nextNode) {
			if(index1 == index) {
				return node;
			}
			index1++;
		}
		return null;
	}



	private Object[] toArray() {
		Object[] arr=new Object[size];
		int index=0;
		for(Node<E> node=head;node!= null;node=node.nextNode) {
			arr[index]=node.data;
			index++;
		}
		return arr;
	}
	
	public void clear() {
		for(Node<E> node=head;node!=null;node=node.nextNode) {
			node.data=null;
			node.prevNode=null;
			node.nextNode=null;
		}
		head=last=null;
		size=0;
	}
	
	public boolean addAll(Linkedlist<E> list) {
		return addAll(size,list);
	}
	
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}
	
	
}
class Node<E>{
	E data;
	Node<E> nextNode;
	Node<E> prevNode;
	public Node(E data,Node<E> nextNode,Node<E> prev){
		this.data= data;
		this.nextNode=nextNode;
		this.prevNode=prev;
	}
}