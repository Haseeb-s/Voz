/**
 *
 */
package Voz.technique.A4;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Voz.controller.Post;

public class BST<T extends Comparable<Post>> implements BSTInterface<T> {
	BSTNode<T> root, current, previous;
	Collection<T> output;
	List<T> values;
	String ingredients = null;
	boolean found;

	public BST() {
		root = null;
	}

	private synchronized BSTNode<T> recAdd(T element, BSTNode<T> tree) {
		if (tree == null) {
			// Addition place found
			tree = new BSTNode<T>(element);
			if (ingredients != null) {
				tree.setParts(ingredients);
				ingredients = null;
			}
		} else if (element.compareTo((Post) tree.getElement()) <= 0)
			tree.setLeft(recAdd(element, tree.getLeft())); // Add in left
															// subtree
		else
			tree.setRight(recAdd(element, tree.getRight())); // Add in right
																// subtree
		return tree;
	}

	public synchronized void add(T element) {
		root = recAdd(element, root);
	}

	public synchronized void add(T element, String ingredients) {
		this.ingredients = ingredients;
		root = recAdd(element, root);
	}

	@Override
	public String search(T element) throws BSTUnderflowException {
		return recSearch(element, root);
	}

	public String recSearch(T element, BSTNode<T> tree) {
		if (tree == null)
			return null; // element is not found
		else if (element.compareTo((Post) tree.getElement()) < 0)
			return recSearch(element, tree.getLeft()); // get from left subtree
		else if (element.compareTo((Post) tree.getElement()) > 0)
			return recSearch(element, tree.getRight()); // get from right
														// subtree
		else
			return tree.getParts(); // element is found

	}

	@Override
	public boolean contains(T element) throws BSTUnderflowException {
		String returned = search(element);
		if (returned != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public Collection<T> traversal(Order order) {
		// TODO Auto-generated method stub
		output = new LinkedList<T>();
		if (!isEmpty()) {

			if (order == Order.INORDER) {
				inOrder(root);
			} else if (order == Order.POSTORDER) {
				postOrder(root);
			} else if (order == Order.PREORDER) {
				preOrder(root);
			}
			return output;
		}
		return output;
	}

	private void inOrder(BSTNode<T> tree)
	// Initializes inOrderQueue with tree elements in inOrder order.
	{
		if (tree != null) {
			inOrder(tree.getLeft());
			output.add(tree.getElement());
			inOrder(tree.getRight());
		}
	}

	private synchronized void preOrder(BSTNode<T> tree)
	// Initializes preOrderQueue with tree elements in preOrder order.
	{
		if (tree != null) {
			output.add(tree.getElement());
			preOrder(tree.getLeft());
			preOrder(tree.getRight());
		}
	}

	private synchronized void postOrder(BSTNode<T> tree)
	// Initializes postOrderQueue with tree elements in postOrder order.
	{
		if (tree != null) {
			postOrder(tree.getLeft());
			postOrder(tree.getRight());
			output.add(tree.getElement());
		}
	}

	public synchronized BSTNode<T> getLeftNode(BSTNode<T> r) {
		if (r.getLeft() == null) {
			return r;
		}
		return getLeftNode(r.getLeft());
	}

	public synchronized BSTNode<T> getRightNode(BSTNode<T> r) {
		if (r.getRight() == null) {
			return r;
		}
		return getRightNode(r.getRight());
	}

	private synchronized Object getPredecessor(BSTNode tree) {
		while (tree.getRight() != null)
			tree = tree.getRight();
		return tree.getElement();
	}

	private synchronized BSTNode<T> removeNode(BSTNode<T> tree) {
		T data;

		if (tree.getLeft() == null)
			return tree.getRight();
		else if (tree.getRight() == null)
			return tree.getLeft();
		else {
			data = (T) getPredecessor(tree.getLeft());
			tree.setElement(data);
			tree.setLeft(recRemove(data, tree.getLeft()));
			return tree;
		}
	}

	private synchronized BSTNode<T> recRemove(T element, BSTNode<T> tree) {
		if (tree == null)
			found = false;
		else if (element.compareTo((Post) tree.getElement()) < 0)
			tree.setLeft(recRemove(element, tree.getLeft()));
		else if (element.compareTo((Post) tree.getElement()) > 0)
			tree.setRight(recRemove(element, tree.getRight()));
		else {
			tree = removeNode(tree);
			found = true;
		}
		return tree;
	}

	public synchronized boolean remove(T element) {
		root = recRemove(element, root);
		return found;
	}

	private Object[] temp;

	private synchronized void balance(int low, int high) {
		temp = traversal(Order.INORDER).toArray();
		balanceRecursive(low, high);
	}

	private synchronized void balanceRecursive(int low, int high) {

		if (low == high)
			return;

		int midpoint = (low + high) / 2;

		T insert = (T) temp[midpoint];
		add(insert);

		balanceRecursive(midpoint + 1, high);
		balanceRecursive(low, midpoint);
	}
}
