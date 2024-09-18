/**********************************************************

 * EECS2101A: Fundamentals of Data Structures,  Fall 2023
 * Assignment 2, Problem 1: Coins.java
 * Student Name:   Isha Pannu
 * Student EECS account:  pannui
 * Student ID number:  218061861
 **********************************************************/
package a2;

import java.util.Scanner;

/**
 * Class coins takes an int amount of money. 
 * With pure recursion,
 * on completion the money is enumerated in all different ways
 * using different units of currency in a given coin system.
 * Default is the Canadian coin system.
 */

public class Coins {
	
	//for user input / output
	private int[] coinVal;
	private  String[] singularName;
	private  String[] pluralName;
	private String str;


	public Coins(int[] coinVal, String[] singularName, String[] pluralName) {
		this.coinVal = coinVal;
		this.singularName = singularName;
		this.pluralName = pluralName;
		this.str = "";
	
	}

	/**
	 * ways()
	 * Time complexity: O(1).
	 * @param money
	 * 			integer amount input by user
	 * @return
	 * 			the amount of ways possible
	 */
	public int ways(int money) {
		return waysRecursiveHelper(money, 0, str, 0);

	}

	/**
	 * waysRecursiveHelper()
	 * @param money
	 * 			integer amount input by user
	 * @param i
	 * 			used in the calculation for the index of the coinVal
	 * @param str 
	 * 			output string
	 * @param sum
	 * 			counter for each use of a denominator
	 * 		
	 * Is of O(2^n) time complexity.
	 * 
	 * See a2sol.pdf for the logic of the recursion
	 */
	
	private int waysRecursiveHelper(int money, int i, 
			String str, int sum) {

		if (money == 0) {  
			// base, valid case
			printWays(str + helper(i, str, sum));
			sum = 0;
			return 1;
			
				//	not (negative || exhausted all coins)
		} else if (!(money < 0 || i >= coinVal.length)) { 

			// recursive calls			
			int way1= waysRecursiveHelper(money-coinVal[i], i, 
					str, sum+1) ;

			int way2= waysRecursiveHelper(money, i+1, str + 
					helper(i, str, sum), 0);

			return way1+way2;
		} 
		else {
			//base, invalid case
			sum = 0;
			return 0;
		}
	}
	
	private String helper(int i, String str, int sum) {
		if (sum == 0) {
			return "";
		} else {
		String s = (" "
				+ sum
				+ " " + (sum == 1 ? singularName[i] : pluralName[i])
				+ ", ");
			
		return s;
		}
	}
	
	private int count = 1;
	public void printWays(String str) {
		String s = "";

			s = ("\n" + (count++) + ")" 
			+ str.substring(0, str.length()-2 ) //erase last comma
			);
			
			//reset string to empty
			str = ""; 
			System.out.print(s);
		
	}

	/*
	 * main() runs and outputs the program depending on the user's input
	 */
	public static void main(String[] args) {
		int[] coinVal = {25, 10, 5, 1};
		String[] singularName = {"Quarter" , "Dime" , "Nickel", "Penny"};
		String[] pluralName = {"Quarters" , "Dimes" , "Nickels" , "Pennies"};
		//default Canadian system.
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Would you like to specify a coin system? 0 = No, 1 = Yes: ");
		int specCoin = scan.nextInt();
		if (specCoin == 1) {
			System.out.print("How many elements are in your coin system?: ");
			int size = scan.nextInt();
			coinVal = new int[size];
			singularName = new String[size];
			pluralName = new String[size];
			
			System.out.print("Enter the coin denomination, singular name, plural name.:\n");
			for (int i = 0; i < size; i++) {
				coinVal[i] = scan.nextInt();
				singularName[i] = scan.next();
				pluralName[i] = scan.next();
			}
		}
		Coins coinSys = new Coins(coinVal, singularName, pluralName );
		System.out.print("Enter an amount in cents: ");
		int money = scan.nextInt();
		
		System.out.print("\nThe amount can be changed in the above " + coinSys.ways(money) + " ways."); 
		
	
}
}
