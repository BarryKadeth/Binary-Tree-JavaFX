package application;
//Using website: https://www.javatpoint.com/binary-tree-java

/*
 * Class Node of a binary tree
 */
public class Node {

//	int key;
	Person key;
	Node left;
	Node right;
	int height;
	
	public Node (Person key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
	
}
