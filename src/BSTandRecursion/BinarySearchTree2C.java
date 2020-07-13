package BSTandRecursion;

// ====================================================
@SuppressWarnings("javadoc")
public class BinarySearchTree2C {

	private Node root;

	public BinarySearchTree2C() {
		this.root = null;
	}

	// ====================================================
	public boolean find(int id) {
		Node current = root;
		while (current != null) {
			if (current.data == id) {
				return true;
			} else if (current.data > id) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	// ====================================================
	public boolean find2(int id) {
		return find2(id, root);
	}

	private boolean find2(int id, Node n) {
		if (n == null)
			return false;
		if (n.data == id)
			return true;
		if (id < n.data)
			return find2(id, n.left);
		return find2(id, n.right);
	}

	// ====================================================
	public boolean delete(int id) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while (current.data != id) {
			parent = current;
			if (current.data > id) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			if (current == null) {
				return false;
			}
		}
		// if i am here that means we have found the node
		// Case 1: if node to be deleted has no children
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			}
			if (isLeftChild == true) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// Case 2 : if node to be deleted has only one child
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.left != null && current.right != null) {

			// now we have found the minimum element in the right sub tree
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;
	}

	// ====================================================
	public Node getSuccessor(Node deleleNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleleNode.right;
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		// check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
		// successsorParent
		if (successsor != deleleNode.right) {
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}

	// ====================================================
	public void insert(int id) {
		root = insert2(root, id);
	}
	
	// Recursive insert
	public Node insert2(Node node, int n) {
		if (node == null) {
			node = new Node(n);
			return node;
		}
		if (n < node.data) {
			node.left = insert2(node.left, n);
		} else if (n > node.data) {
			node.right = insert2(node.right, n);
		}
		return node;
	}

	// ====================================================
	private void display(Node root) {
		if (root != null) {
			display(root.left);
			System.out.print(" " + root.data);
			display(root.right);
		}
	}

	public void display() {
		display(root);
		System.out.println();
	}

	// ====================================================

	public int depth() {
		return depth(this.root);
	}
	
	public int depth(Node leaf) {
		if (leaf == null) {
			return 0;
		} else {
			int leftDepth = depth(leaf.left);
			int rightDepth = depth(leaf.right);
			if (leftDepth > rightDepth) {
				return leftDepth + 1;
			} else {
				return rightDepth + 1;
			}
		}
	}

	public int node_count() {
		return node_count(this.root);
	}
	
	public int node_count(Node node) {
		if (node == null) {
			return 0;
		} else {
			int count = 1;
			if (node.left != null) {
				count += node_count(node.left);
			}
			if (node.right != null) {
				count += node_count(node.right);
			}
			return count;
		}
	}
	
	public BinarySearchTree2C clone() {
		return clone(this.root);
	}
	
	public BinarySearchTree2C clone(Node node) {
		BinarySearchTree2C clone = new BinarySearchTree2C();
		clone.root = node;
		return clone;
	}

	public static void main(String arg[]) {
		BinarySearchTree2C b = new BinarySearchTree2C();
		BinarySearchTree2C c = b;
		b.insert(3);
		b.insert(8);
		b.insert(1);
		b.insert(4);
		b.insert(6);
		b.insert(2);
		b.insert(10);
		b.insert(9);
		b.insert(20);
		b.insert(25);
		b.insert(15);
		b.insert(16);
		System.out.println("Original Tree b: ");
		b.display();
		System.out.println("B depth: " + b.depth(b.root));
		System.out.println("B node count: " + b.node_count(b.root));
		BinarySearchTree2C bClone = b.clone();
		System.out.println("Clone Tree B: ");
		bClone.display();
		System.out.println("Copy Tree c: ");
		c.display();
		System.out.println("");
		System.out.println("Check whether Node with value 4 exists : " + b.find2(4));
		System.out.println("Delete Node with no children (2) : " + b.delete(2));
		b.display();
		System.out.println("\n Delete Node with one child (4) : " + b.delete(4));
		b.display();
		System.out.println("\n Delete Node with Two children (10) : " + b.delete(10));
		System.out.println("Original Tree b: ");
		b.display();
		System.out.println("Copy Tree c: ");
		c.display();

	}
}

// ====================================================
@SuppressWarnings("javadoc")
class Node {

	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}