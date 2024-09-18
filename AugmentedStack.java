/**********************************************************

 * EECS2101A: Fundamentals of Data Structures,  Fall 2023
 * Assignment 2, Problem 3: AugmentedStack.java
 * Student Name:   Isha Pannu
 * Student EECS account:  pannui
 * Student ID number:  218061861
 **********************************************************/
package a2;

/**
 * The purpose of this class is to maintain a generic stack
 * with comparable element type.
 * 
 * The space used is O(n).
 * Each operation runs in time O(1).
 */

public class AugmentedStack<E extends Comparable<E>> implements Stack<E>{

	// NESTED CLASSES 

	public class Node<E extends Comparable<E>>{
		private E element;
		private Node<E> next;
		private E min;

		public Node(E e, Node<E> n, E m) {
			element = e;
			next = n;
			min = m;
		}

		// getters
		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public E getMin() {
			return min;
		}

		//setters
		public void setNext(Node<E> n) {
			next = n;

		}

	}

	public class SinglyLinkedList<E extends Comparable<E>>{
		private Node<E> top = null;
		private Node<E> tail = null;
		private int size;


		// getters
		public boolean isEmpty() {
			return (size == 0); 
		}

		public int getSize() {
			return size; 
		}

		public E getTop() {
			if (isEmpty()) { 
				return null;
			} else {
				return top.getElement(); 
			}
		}

		public E getTail() {
			if (isEmpty()) { 
				return null;
			} else {
				return tail.getElement(); 
			}
		}

		/**
		 * pushHelper()
		 * adding a node, a new top, to the linked list.
		 * first make a new node with element,
		 * then insert the node by having the new node point to the old head.
		 * then update the head to point to the new node.
		 * 
		 * for the minimum, we keep track of the minimum for each node
		 * 
		 * if this is the only element, the tail is the top.
		 * 
		 * Space complexity: O(1)
		 * 
		 * @param x
		 *	type E, to push
		 */
		public void pushHelper(E x) {
			E min = null;
			if (!isEmpty()) {
				min = (E) top.getMin();
			}
			if (min == null || x.compareTo(min) <= 0) {
				min = x;
			}
			Node<E> temp= new Node(x, top, min);
			top = temp;

			if (size==0) {
				tail = top;
			}
			size++;
		}
		/**
		 * popHelper()
		 * removing the top from the linked list.
		 * update the head to point to the next node
		 * 
		 * if it's now empty, the tail node should be updated
		 * 
		 * Space complexity: O(1)
		 */
		public void popHelper() {
			top = top.getNext();
			size--;
			if (size==0) {
				tail = null;
			}
		}

	}
	// END OF NESTED CLASSES


	private SinglyLinkedList<E> s;

	public AugmentedStack() {
		s = new SinglyLinkedList<>();

	}


	/**
	 * getMin()
	 *	getter method
	 * 
	 * @return E
	 *	without removing element,
	 *	minimum element on S, if not empty
	 *	null, if empty
	 * 
	 * time complexity: O(1).
	 */
	public E getMin() { 
		if (s.isEmpty()) {
			return null;
		}
		return (E) s.top.getMin();
	}

	// getters, as defined in the stack interface
	public E top() {
		return s.getTop();
	}

	public int size() {
		return s.getSize();
	}

	public boolean isEmpty() {
		return s.isEmpty();
	}

	// explained in SinglyLinkedList class
	public void push(E x) {
		s.pushHelper(x);
	}

	/**
	 * pop()
	 * 	remove & return top element on s	
	 * 
	 * @return popped
	 *	generic type, top element of s, if not empty
	 *	null, if empty
	 *
	 */
	public E pop() {

		if (s.isEmpty()){
			return null;
		} else {

			E popped = s.getTop(); 

			s.popHelper();

			return popped;

		}
	}


	/**
	 * mainMethodHelper()
	 * Solely for the main method testing, to avoid typing repetitive commands in printing and testing.
	 * 
	 * @param test
	 * 		of any generic type
	 * @param size
	 * 
	 * Time complexity: O(n)
	 * Space complexity: in place, O(1)
	 */
	
	public static <E extends Comparable <E>> void mainMethodHelper(AugmentedStack<E> test, int size) {
		while (size > 0) {
			System.out.println("top: " + test.top() +
					" , size: " + test.size() + 
					" , min: " + test.getMin() 
					+ " , popping");
			test.pop();
			size--;
		}
		if (size == 0) {
			System.out.println("top: " + test.top() 
			+ " , min: " + test.getMin() 
			+ " , empty String stack?: " + test.isEmpty());
		}
	}

	public static void main(String[] args) {

		AugmentedStack<String> test1 = new AugmentedStack<>();
		System.out.println("empty String stack?: " + test1.isEmpty() + "\npushing thank you ");
		test1.push("thank");
		test1.push("you");
		mainMethodHelper(test1, test1.size());

		AugmentedStack<Integer> test2 = new AugmentedStack<>();
		System.out.println("\nempty Integer stack?: " + test2.isEmpty() + "\npushing 3 2 1 ");
		test2.push(3);
		test2.push(2);
		test2.push(1);
		mainMethodHelper(test2, test2.size());
		
		AugmentedStack<Character> test3 = new AugmentedStack<>();
		System.out.println("\nempty Character stack?: " + test3.isEmpty() + "\npushing b a c ");
		test3.push('b');
		test3.push('a');
		test3.push('c');
		mainMethodHelper(test3, test3.size());
		System.out.println("pushing z");
		test3.push('z');
		mainMethodHelper(test3, test3.size());
		
		AugmentedStack<String> test4 = new AugmentedStack<>();
		System.out.println("\nempty String stack?: " + test4.isEmpty());
		mainMethodHelper(test4, test4.size());

	}
}
