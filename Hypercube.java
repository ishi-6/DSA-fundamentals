package a2;

import java.util.ArrayDeque;

/**
 * Hypercube class
 * Creates a cube of n-dimension, with 2^n corners.
 * 
 * @author Isha
 */
public class Hypercube {
	/**
	 * Corner, the coordinates of the cube, 
	 * are restricted to an n sized set of 0 and 1.
	 * 
	 * space complexity: O(n)
	 */
	public class Corner {
		int[] coords;
		boolean visited;

		public Corner(int[] coords) {
			this.coords = coords;
			this.visited = false;
		}

		/**
		 * coordPrint()
		 * helper method for coordinate output printing
		 * 
		 * time complexity: O(n)
		 * @return string
		 * 		s, the string version of the walk
		 */
		public String coordPrint() {
			StringBuilder s = new StringBuilder("");
			for (int coords: coords) {
				s.append(coords);
			}
			return s.toString();
		}

		public void setVisited(boolean b) {
			this.visited = b;
		}

		public boolean getVisited() {
			return this.visited;
		}
	}

	private int n;
	private Corner[] corners;

	/**
	 * Hypercube() constructor
	 * @param n
	 * 		dimension
	 * Initializes the Corner[] array, 
	 * the corners are of O(2^n) space complexity.
	 */
	public Hypercube(int n) {
		this.n = n;
		int numCorners = (int) Math.pow(2, n);
		corners = new Corner[numCorners];
		initializeCorners();
	}

	/**
	 * converts decimal to binary.
	 * Time complexity: O(2^n)
	 */
	public void initializeCorners() {
		for (int i =0; i<corners.length; i++) {
			int[] coords = new int[n];
			int temp = i; //0 -> 2^n-1
			
			//decimal to binary
			for (int j = (n-1); j >= 0; j--) { 
				// gives us binary digit for last number in coordinate
				coords[j] = temp%2;
				// shift number to left.
				temp = temp/2; 

			}
			corners[i] = new Corner(coords);
		}
	}

	/**
	 * recursiveWalk()
	 * recursive approach of walk on the Hypercube.
	 * starts at a corner, 
	 * walks along a sequence of edges so that it visits all corners exactly once.

	 * time complexity: O(2^n) visits all corners
	 */
	public void recursiveWalk() {
		Corner startCorner = corners[0];
		corners[0].setVisited(true);
		System.out.print(startCorner.coordPrint() + " "); 

		recursiveWalkHelper(startCorner);
	}

	/**
	 * recursiveWalkHelper()
	 * base case: no unvisited corners
	 * recursive method: continue traversing the cube
	 * 
	 * @param current
	 * 			the starting corner
	 * time complexity: O(2^n) 
	 * 
	 */
	private void recursiveWalkHelper(Corner current) {
		boolean unvisitedFound = false;
		
		for (int i = 0; i < corners.length; i++) { 

			if (!corners[i].getVisited() && isAdj(current, corners[i])) {
				corners[i].setVisited(true);
				System.out.print(corners[i].coordPrint() + " "); 
				unvisitedFound = true;
				
				recursiveWalkHelper(corners[i]); 
			}
		}
		
		if (!unvisitedFound) {
			return;
		}
	}


	/**
	 * iterativeWalk()
	 * 
	 * an iterative approach.
	 * solves the problem using a single queue
	 * check a2sol.pdf for logic
	 * 
	 * Time complexity: O(2^n)
	 */

	public void iterativeWalk() {
		ArrayDeque<Corner> q = new ArrayDeque<>();

		q.add(corners[0]);
		corners[0].setVisited(true);

		while (!q.isEmpty()) {

			Corner current = q.remove();
			System.out.print(current.coordPrint() + " ");
			for (int i = 0; i < corners.length; i++) { 
				if (!(corners[i].getVisited()) && isAdj(current, corners[i])) {

					q.add(corners[i]);
					corners[i].setVisited(true);

					break;
				}
			}
		}
	}

	/**
	 * resetWalk()
	 * for testing purposes.
	 * When running the two kinds of method on a single Hypercube,
	 * We need to reset the visited values back to false in between.
	 */
	public void resetWalk() {
		for (Corner corners: corners) {
			corners.setVisited(false);
		}
	}
	
	/**
	 * isAdj()
	 * 	Helper method for the walks.
	 * 	Refer to diagram in a2sol.pdf for logic.
	 * 
	 * @param c1
	 * 		Corner
	 * @param c2
	 * 		Corner
	 * @return true if adjacent, false if not adjacent.
	 */
	private boolean isAdj(Corner c1, Corner c2) {
		int diff = 0;

		for (int i = 0; i < n; i++) {
			if (c1.coords[i] != c2.coords[i]) {
				diff++;
				if (diff > 1) {
					return false;
				}
			}
		}
		return diff==1; 
	}


	/**
	 * main() runs & outputs test cases on recursiveWalk & iterativeWalk
	 */
	public static void main(String[] args) {
		Hypercube test1 = new Hypercube(1);
		System.out.println("n = 1\nrecursive walk: ");
		test1.recursiveWalk();
		test1.resetWalk();
		System.out.println("\niterative walk: ");
		test1.iterativeWalk();

		Hypercube test6 = new Hypercube(2);
		System.out.println("\n\nn = 2\nrecursive walk: ");
		test6.recursiveWalk();
		test6.resetWalk();
		System.out.println("\niterative walk: ");
		test6.iterativeWalk();

		Hypercube test2 = new Hypercube(3);
		System.out.println("\n\nn = 3\nrecursive walk: ");
		test2.recursiveWalk();
		test2.resetWalk();
		System.out.println("\niterative walk: ");
		test2.iterativeWalk();

		Hypercube test3 = new Hypercube(4);
		System.out.println("\n\nn = 4\nrecursive walk: ");
		test3.recursiveWalk();
		test3.resetWalk();
		System.out.println("\niterative walk: ");
		test3.iterativeWalk();

		Hypercube test4 = new Hypercube(5);
		System.out.println("\n\nn = 5\nrecursive walk: ");
		test4.recursiveWalk();
		test4.resetWalk();
		System.out.println("\niterative walk: ");
		test4.iterativeWalk();
		
	}
}
