/*
 * File: Node.java
 */
package aghannam.list;

/**
 * Represents a doubly-linked list node.
 * 
 * @author Ahmed Ghannam
 *
 */
public class Node<T> {
	/**
	 * The node following this node.
	 */
	protected Node<T> next;

	/**
	 * The node preceding this node.
	 */
	protected Node<T> prev;

	/**
	 * The value of this node.
	 */
	protected T val;

	/**
	 * Constructs a new list node containing the specified value.
	 * 
	 * @param value
	 *            the value to associate to this node
	 */
	protected Node(T val) {
		this.val = val;
	}
}