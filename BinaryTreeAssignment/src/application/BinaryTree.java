package application;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
//		root = AVLInsert(root,key);
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
		} else if (a.getAge() != (b.getAge())) 
			{
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
		if (root != null) {	
			System.out.println(label + root.key );
			Main.listOfNames.add(label + root.key);
			printPreOrderBinaryTree(root.left, "    " + label);
			printPreOrderBinaryTree(root.right, "    " + label);
		}
	}
	
	/**
	 * recursively traverse the tree using In order
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
	 * recursively traverse the tree using Post order
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
	 * Traversal using breadth first
	 * Code used from the lecture slides 0301 and
	 * https://www.geeksforgeeks.org/level-order-tree-traversal/
	 */
	public void breadthFirstTraversal () {
		Queue<Node> todo = new ArrayDeque<Node>();
		todo.offer(root);
		while (!todo.isEmpty()) {
			Node temp;
			temp = todo.remove();
			System.out.println(temp.key);
			Main.listOfNames.add(temp.key.toString());
			//enqueue the left child
			if (temp.left != null) {
				todo.offer(temp.left);
			}
			//enqueue the right child
			if (temp.right != null) {
				todo.offer(temp.right);
			}
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
			System.out.println("Left: " + leftTreeCompare);
			System.out.println("Right: " + rightTreeCompare);
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
	
	/**
	 * Creates a list of people whos first and last names are longer than
	 * the specified minimum length
	 * Using the 0301TreesandGraphs slides
	 */
	public List<Person> findNameLength (Node p, int minLength) {
		List<Person> nameList = new ArrayList<Person>();
		findNameLengthRecursion(p, minLength, nameList);
		
		return nameList;
	}
	public void findNameLengthRecursion (Node p, int minLength, List<Person>nameList) {
		if (p!=null) {
			if (p.key.getFirstName().length() >= minLength  &&  
					p.key.getLastName().length() >= minLength) {
				nameList.add(p.key);
			}
			findNameLengthRecursion(p.left,minLength,nameList);
			findNameLengthRecursion(p.right,minLength,nameList);
			
		}
	}
	
	/**
	 * Creates a list of people whos first names are long than their last names 
	 * Using the 0301TreesandGraphs slides
	 */
	public List<Person> firstLarger (Node p) {
		List<Person> nameList = new ArrayList<Person>();
		firstLargerRecursion(p, nameList);
		
		return nameList;
	}
	public void firstLargerRecursion (Node p, List<Person>nameList) {
		if (p!=null) {
			if (p.key.getFirstName().length() > p.key.getLastName().length() ) {
				nameList.add(p.key);
			}
			firstLargerRecursion(p.left,nameList);
			firstLargerRecursion(p.right,nameList);
			
		}
	}
	
	/**
	 * Creates a list of people whose last names are longer than their first names 
	 * Using the 0301TreesandGraphs slides
	 */
	public List<Person> lastLarger (Node p) {
		List<Person> namesList = new ArrayList<Person>();
		lastLargerRecursion(p, namesList);
		
		return namesList;
	}
	public void lastLargerRecursion (Node p, List<Person>namesList) {
		if (p!=null) {
			if (p.key.getLastName().length() > p.key.getFirstName().trim().length()) {
				System.out.println(p.key);
				System.out.println("first name length: " +p.key.getFirstName().length());
				System.out.println("last name length: " +p.key.getLastName().length());
				namesList.add(p.key);
			} else {
				
			}
			lastLargerRecursion(p.left,namesList);
			lastLargerRecursion(p.right,namesList);
			
		}
	}
	
	//AVL Tree: resource was used
	//https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
	/**
	 * Height of tree
	 */
	public int height (Node p) {
		if (p == null) {
			return 0;
		}
		return p.height;
	}
	
	/**
	 * Utility to get the max of two integers
	 */
	public int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	/**
	 * Method to right rotate the subtree rooted with y
	 */
	public Node rightRotate (Node y) {
		Node x = y.left;
		if (x != null) {
			Node T2 = x.right;
			//Perform the rotation
			x.right = y;
			y.left = T2;
			//Update the heights
			y.height = max(height(y.left), height(y.right)) + 1;
			x.height = max(height(x.left), height(x.right)) + 1;
			//return the new root
			return x;
		}
		return y;
	}
	
	/**
	 * Method to left rotate the subtree rooted with x
	 */
	public Node leftRotate (Node x) {
		Node y = x.right;
		if (y != null) {
			Node T2 = y.left;
			//Rotation
			y.left = x;
			x.right = T2;
			//Update heights
			x.height = max(height(x.left), height(x.right)) + 1;
			y.height = max(height(y.left), height(y.right)) + 1;
			//return the new root
			return y;
		}
		return x;
	}

	/**
	 * Method for the balance factor of node p
	 */
	public int getBalance (Node p) {
		if (p == null) {
			return 0;
		}
		return height(p.left) - height (p.right);
	}
	
	/**
	 * Set up of determining which compare-by to use
	 * @param key
	 * @return
	 */
	public int comparisonNumber (Person key) {
		if (sortBy.equalsIgnoreCase("firstName")) {
			return compareByFirstName(key,root.key);
		} else if (sortBy.equalsIgnoreCase("lastName")) {
			return compareByLastName(key,root.key);
		} else {
			return compareByAge(key,root.key);
		}
	}
	
	/**
	 * Set up of determining which compare-by to use for person A person B Comparison
	 * @param key
	 * @return
	 */
	public int personABComparisonNumber (Person a, Person b) {
		if (sortBy.equalsIgnoreCase("firstName")) {
			return compareByFirstName(a,b);
		} else if (sortBy.equalsIgnoreCase("lastName")) {
			return compareByLastName(a,b);
		} else {
			return compareByAge(a,b);
		}
	}
	
	/**
	 * Method for new insertion
	 * Taken from the web site
	 * https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
	 */
    public Node AVLInsert(Node node, Person key) {
        /* 1.  Perform the normal BST insertion */
        if (node == null) {
            return (new Node(key));
        }
		//Comparing depending on the type of binary tree
		int comparison = comparisonNumber(key);
 
        if (comparison < 0) {
            node.left = AVLInsert(node.left, key);
        }
        else if (comparison > 0) {
            node.right = AVLInsert(node.right, key);
        }
        else // Duplicate keys not allowed
//Will need to change this to keep a counter of how many of a certain name there is 
            return node;
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), height(node.right));
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);
        // If this node becomes unbalanced, then there are 4 cases 
        //Left Left Case
        if (node.left !=null) {
            int comparison1 = personABComparisonNumber(key,node.left.key);
            if (balance > 1 && comparison1 < 0) {
                return rightRotate(node);
            }
        }
        // Right Right Case
        if (node.right != null) {
            int comparison2 = personABComparisonNumber(key,node.right.key);
            if (balance < -1 && comparison2 > 0) {
                return leftRotate(node);
            }
        }
        // Left Right Case
        if (node.left !=null) {
            int comparison3 = personABComparisonNumber(key,node.left.key);
            if (balance > 1 && comparison3 > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        // Right Left Case
        if (node.left != null) {
            int comparison4 = personABComparisonNumber(key,node.left.key);
            if (balance < -1 && comparison4 < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        //for the cases where key exists but node.left or node.right does not
        if (node.left == null) {
            if (balance > 1) {
            	return (new Node(key));
            }
        }
        if (node.right == null) {
            if (balance < -1) {
            	return (new Node(key));
            }
        }        
        
        /* return the (unchanged) node pointer */
        return node;
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
	
	
}
