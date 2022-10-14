package application;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	//Using website: https://www.javatpoint.com/binary-tree-java
	//https://www.softwaretestinghelp.com/binary-search-tree-in-java/
	
	
	//Root of the binary tree
	Node root;
	String sortBy;
	//Made public so it can be accessed by main
	public static Boolean balancingDetailsAdded = false;
	
	/**
	 * An empty binary tree
	 */
	public BinaryTree(String sortBy) {
		this.sortBy = sortBy;
		root = null;
	}
	
	/**
	 * Delete node from tree
	 */
//	public void deleteKey (Person key) {
//		root = recursiveDelete (root,key);
//	}
	
	/**
	 * Delete recursively function
	 */
//	public Node recursiveDelete (Node root, Person key) {
//		//if the tree is empty
//		if (root == null) {
//			return root;
//		}
//		
//		//Comparing lexicographically first name of key vs root
//		int comparison = compareByFirstName(key,root.key);
//		//This is to traverse the tree
//		if (comparison < 0) {
//			root.left = recursiveDelete(root.left,key);
//		} else if (comparison > 0){
//			root.right = recursiveDelete(root.right,key);
//		} else {
//			//If the node has only one child
//			if (comparison == 0) {
//				return root.right;
//			} else if (root.right == null) {
//				return root.left;
//			}
//			//If the node has two children
//			//get the in order success and then delete it
//			root.key = smallestValue(root.right);
//			root.right = recursiveDelete(root.right,root.key);
//		}
//		return root;
//	}
	

	
	/**
	 * Left hand value: minimum
	 * 
	 */
	public Person smallestValue (Node root) {
		Person minimumValue = root.key;
		//find the smallest value:
		while (root.left != null) {
			minimumValue = root.left.key;
			root = root.left;
		}
		return minimumValue;
	}
	
	/**
	 * Inserting a node into tree
	 */
	public void insertNode (Person key) {
		root = recursiveInsert(root, key);
	}
	
	/**
	 * method for inserting recursively
	 */
	public Node recursiveInsert(Node root, Person key) {
	//when the tree is empty
		if (root == null) {
			root = new Node(key);
			return root;
		}
		//Tree needs to be traversed
		//Comparing depending on the type of binary tree
		int comparison;
		if (sortBy.equalsIgnoreCase("firstName")) {
			comparison = compareByFirstName(key,root.key);
		} else if (sortBy.equalsIgnoreCase("lastName")) {
			comparison = compareByLastName(key,root.key);
		} else {
			comparison = compareByAge(key,root.key);
		}
		
		//using integer comparison
		if (comparison < 0) { //if key is less, then insert to left branch
			root.left = recursiveInsert(root.left, key);
		} else if (comparison > 0) { //if key is more, insert right
			root.right = recursiveInsert(root.right, key);
		} 
		return root;
	}
	
	/**
	 * Used to compare and order first name first 
	 * @param a
	 * @param b
	 * @return
	 */
	public int compareByFirstName (Person a, Person b) {
		if (a.getFirstName().compareTo(b.getFirstName()) != 0) {
			return a.getFirstName().compareTo(b.getFirstName());
		} else if (a.getLastName().compareTo(b.getLastName()) != 0) {
			return a.getLastName().compareTo(b.getLastName());
		} else if (a.getAge() != (b.getAge())) {
			return a.getAge() - b.getAge();
		} else {
			//If they are the same name, automatically puts it to the right side.
			return +1;
		}
	}
	
	/**
	 * Compare by last name first 
	 * @param a
	 * @param b
	 * @return
	 */
	public int compareByLastName (Person a, Person b) {
		
		if (a.getLastName().compareTo(b.getLastName()) != 0) {
			return a.getLastName().compareTo(b.getLastName());
		} else if (a.getFirstName().compareTo(b.getFirstName()) != 0) {
			return a.getFirstName().compareTo(b.getFirstName());
		} else if (a.getAge() != (b.getAge())) {
			return a.getAge() - b.getAge();
		} else {
			//If they are the same name, automatically puts it to the right side.
			return +1;
		}
	}
	
	/**
	 * Compare by age first
	 * @param a
	 * @param b
	 * @return
	 */
	public int compareByAge (Person a, Person b) {
		if (a.getAge() != b.getAge()) {
			return a.getAge() - b.getAge();
		} else if (a.getFirstName().compareTo(b.getFirstName()) != 0) {
			return a.getFirstName().compareTo(b.getFirstName());
		} else if (a.getLastName().compareTo(b.getLastName()) != 0) {
			return a.getLastName().compareTo(b.getLastName());
		} else {
			//If they are the same name, automatically puts it to the right side.
			return +1;
		}
	}

	
	/**
	 * Method for in order traversal of tree
	 */
	public void inOrder() {
		recursiveInOrder(root);
	}
	
	
	/**
	 * recursively traverse the tree
	 */
	public void recursiveInOrder(Node root) {
		if (root != null) {
			recursiveInOrder(root.left);
			System.out.println(root.key + " ");
			Main.listOfNames.add(root.key.toString());
			recursiveInOrder(root.right);
		}
	}
	
	/**
	 * Printing whether it is skewed or not and the height
	 */
	public void addSkewedOrNot (Node root) {
		if (!balancingDetailsAdded) {
			int inOrderNumber = subTreeCompare(root);
			if (inOrderNumber == -1) {
				System.out.println("The binary tree is skewed");
				Main.listOfNames.add("The binary tree is skewed");
				balancingDetailsAdded = true;
			} else if (inOrderNumber > -1 ) {
				System.out.println("The binary tree is balanced with a height of " + inOrderNumber);
				Main.listOfNames.add("The binary tree is balanced with a height of " + inOrderNumber);
				balancingDetailsAdded = true;
			}
		}
	}
	
	/**
	 * recursively traverse the tree using pre order
	 */
	public void printPreOrderBinaryTree(Node root, String label) {
		addSkewedOrNot(root);
//		if (!balancingDetailsAdded) {
//			int inOrderNumber = subTreeCompare(root);
//			if (inOrderNumber == -1) {
//				System.out.println("The binary tree is skewed");
//				Main.listOfNames.add("The binary tree is skewed");
//				balancingDetailsAdded = true;
//			} else if (inOrderNumber > -1 ) {
//				System.out.println("The binary tree is balanced with a height of " + inOrderNumber);
//				Main.listOfNames.add("The binary tree is balanced with a height of " + inOrderNumber);
//				balancingDetailsAdded = true;
//			}
//		}
		if (root != null) {	
			System.out.println(label + root.key );
			Main.listOfNames.add(label + root.key);
			printPreOrderBinaryTree(root.left, "    " + label);
			printPreOrderBinaryTree(root.right, "    " + label);
		}
	}
	
	/**
	 * recursively traverse the tree using pre order
	 */
	public void printInBinaryTree(Node root, String label) {
		addSkewedOrNot(root);
		if (root != null) {
			printInBinaryTree(root.left, "    " + label);
			System.out.println(label + root.key );
			Main.listOfNames.add(label + root.key);
			printInBinaryTree(root.right, "    " + label);
		}
	}
	
	/**
	 * recursively traverse the tree using pre order
	 */
	public void printPostOrderBinaryTree(Node root, String label) {
		addSkewedOrNot(root);
		if (root != null) {
			printPostOrderBinaryTree(root.left, "    " + label);
			printPostOrderBinaryTree(root.right, "    " + label);
			System.out.println(label + root.key );
			Main.listOfNames.add(label + root.key);
		}
	}
	
	/**
	 *  Method to search for a node
	 */
	public boolean search (Person key) {
		//Making a temporary root so that the original binary tree remains
		Node tempRoot;
		tempRoot = recursiveSearch(root,key);
		if (tempRoot != null) {
			Main.listOfNames.add(key + " exists in the list");
			return true;
		} else {
			Main.listOfNames.add(key + " does not exist in the list");
		return false;
		}
	}
	
	/**
	 * recursive method to search tree
	 * if it is not present, then it will be inserted
	 */
	public Node recursiveSearch (Node root, Person key) {
		//If the root is present, checking first name, last name and age.
		
		if (root == null || ( key.getFirstName().equalsIgnoreCase(root.key.getFirstName())
			&& key.getLastName().equalsIgnoreCase(root.key.getLastName()) &&
			key.getAge() == root.key.getAge()
				)) {
			return root;
		}
		
		int firstNameCompare = key.getFirstName().compareTo(root.key.getFirstName());
		//if the value is greater than the root's key
		if (firstNameCompare < 0) {
			return recursiveSearch (root.left, key);
		} else	if (firstNameCompare > 0) { 
		//if the value is less than root's key
			return recursiveSearch(root.right,key);
		//if the first names are the same, have to compare the last names
		} else {
			int lastNameCompare = key.getLastName().compareTo(root.key.getLastName());
			if (lastNameCompare < 0) {
				return recursiveSearch (root.left, key);
			} else if (lastNameCompare > 0){ 
			//if the value is less than root's key
				return recursiveSearch(root.right,key);
			} else {
			//IF the first AND last names are the same, sort by age
				if (key.getAge() <root.key.getAge()) {
					return recursiveSearch(root.left,key);
				} else {
					return recursiveSearch(root.right,key);
				}
			} 
		} 
	}
	
	/**
	 * To determine whether the left and right subtrees are balanced
	 * Code used from
	 * https://www.digitalocean.com/community/tutorials/balanced-binary-tree-check
	 */
	public int subTreeCompare (Node root) {
		if (root == null) {
			return 0;
		}
		//checking the left subtree
		int leftTreeCompare = subTreeCompare (root.left);
		if (leftTreeCompare == -1) {
			return -1;
		}
		//checking the right subtree
		int rightTreeCompare = subTreeCompare (root.right);
		if (rightTreeCompare == -1) {
			return -1;
		}
		//Check left and right subtree difference for current node
		if (Math.abs(leftTreeCompare - rightTreeCompare) > 1) {
			return -1;
		} else {
			return (Math.max(leftTreeCompare,rightTreeCompare) +1);
		}
	}
	
	/**
	 * Find person with a specific first name
	 */
	public List<Person> findFirstName (Node p, String firstName) {
		List <Person> firstNameList = new ArrayList<Person>();
		findFirstNameRecursion(p,firstName,firstNameList);
		return firstNameList;
	}
	public void findFirstNameRecursion (Node p, String firstName, List<Person>firstNameList) {
		if (p!=null) {
			if (p.key.getFirstName().equalsIgnoreCase(firstName)) {
				firstNameList.add(p.key);
			}
			findFirstNameRecursion(p.left,firstName,firstNameList);
			findFirstNameRecursion(p.right,firstName,firstNameList);
		}
	}
	
	/**
	 * Find person with a specific Last name
	 */
	public List<Person> findLastName (Node p, String lastName) {
		List <Person> lastNameList = new ArrayList<Person>();
		findLastNameRecursion(p,lastName,lastNameList);
		return lastNameList;
	}
	public void findLastNameRecursion (Node p, String lastName, List<Person>lastNameList) {
		if (p!=null) {
			if (p.key.getLastName().equalsIgnoreCase(lastName)) {
				lastNameList.add(p.key);
			}
			findLastNameRecursion(p.left,lastName,lastNameList);
			findLastNameRecursion(p.right,lastName,lastNameList);
		}
	}
	
	/**
	 * Find person with a specific age
	 */
	public List<Person> findAge (Node p, int age) {
		List <Person> ageList = new ArrayList<Person>();
		findAgeRecursion(p,age,ageList);
		return ageList;
	}
	public void findAgeRecursion (Node p, int age, List<Person>ageList) {
		if (p!=null) {
			if (p.key.getAge() == age) {
				ageList.add(p.key);
			}
			findAgeRecursion(p.left,age,ageList);
			findAgeRecursion(p.right,age,ageList);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
