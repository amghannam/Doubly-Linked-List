/*
 * File: LinkedList.java
 */
package aghannam.list;

/**
 * Implements an unbounded doubly-linked list that supports basic operations.
 * 
 * @author Ahmed Ghannam
 *
 */
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size = 0;

	/**
	 * Constructs a new doubly-linked list instance without any nodes.
	 */
	public LinkedList() {
		/* Empty list */
	}

	/**
	 * Constructs a new doubly-linked list instance with a head node that
	 * contains the specified value.
	 * 
	 * @param val
	 *            the value to be assigned to the head node of this list
	 */
	public LinkedList(T val) {
		Node<T> head = new Node<>(val);
		this.head = head;
		this.tail = head;
		size++;
	}

	/**
	 * Constructs a new doubly-linked list instance from the specified array.
	 * 
	 * @param a
	 *            the array whose elements are to be added to this list
	 */
	public LinkedList(T[] a) {
		if (a.length > 0) {
			for (int i = 0; i < a.length; i++) {
				insertLast(a[i]);
			}
		}
	}

	/**
	 * Appends a node with the specified value to the end of the list.
	 * 
	 * @param val
	 *            the value of the node to be added to this list
	 */
	public void insertLast(T val) {
		if (val != null) {
			Node<T> newNode = new Node<>(val);
			if (isEmpty()) {
				head = newNode;
				tail = newNode;
				size++;
			} else {
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
				size++;
			}
		}
	}

	/**
	 * Adds a node with the specified value immediately before the first
	 * occurrence of the node with the specified <b>before</b> value. If the
	 * target node is not found, the list remains unchanged.
	 * 
	 * @param before
	 *            the value of the target node before which to add the new node
	 * @param val
	 *            the value of the node to be added to this list
	 */
	public void insertBefore(T before, T val) {
		if (!isEmpty() && before != null && val != null) {
			if (head.val == before) {
				insertFirst(val);
			} else {
				Node<T> curr = head;
				while (curr.val != before) {
					curr = curr.next;
					if (curr == null) {
						return;
					}
				}
				Node<T> newNode = new Node<>(val);
				curr.prev.next = newNode;
				newNode.prev = curr.prev;
				newNode.next = curr;
				size++;
			}
		}
	}

	/**
	 * Appends a node with the specified value to the beginning of the list.
	 * 
	 * @param val
	 *            the value of the node to be added to this list
	 */
	public void insertFirst(T val) {
		if (val != null) {
			Node<T> newNode = new Node<>(val);
			if (isEmpty()) {
				head = newNode;
				tail = newNode;
				size++;
			} else {
				head.prev = newNode;
				newNode.next = head;
				head = newNode;
				size++;
			}
		}
	}

	/**
	 * Adds a node with the specified value immediately following the first
	 * occurrence of the node with the specified <b>after</b> value. If the
	 * target node is not found, the list remains unchanged.
	 * 
	 * @param after
	 *            the value of the target node after which to add the new node
	 * @param val
	 *            the value of the node to be added
	 */
	public void insertAfter(T after, T val) {
		if (!isEmpty() && after != null && val != null) {
			Node<T> curr = head;
			while (curr.val != after) {
				curr = curr.next;
				if (curr == null) {
					return;
				}
			}
			if (curr == tail) {
				insertLast(val);
			} else {
				Node<T> newNode = new Node<>(val);
				curr.next.prev = newNode;
				newNode.next = curr.next;
				curr.next = newNode;
				newNode.prev = curr;
				size++;
			}
		}
	}

	/**
	 * Removes the node that immediately precedes the first occurrence of the
	 * node with the specified value and returns its value. If the list is
	 * empty, this method returns <code>null</code>.
	 * 
	 * @param val
	 *            the value of the node before which to remove a node
	 * @return the value of the node removed
	 */
	public T removeBefore(T val) {
		if (!isEmpty() && val != null) {
			if (head.next.val == val) {
				return removeFirst();
			}
			Node<T> curr = head;
			while (curr.val != val) {
				curr = curr.next;
				if (curr == null) {
					return null;
				}
			}
			T removed = curr.prev.val;
			curr.prev = curr.prev.prev;
			curr.prev.next = curr;
			size--;
			return removed;
		}
		return null;
	}

	/**
	 * Removes the first occurrence of the node containing the specified value
	 * and returns its value. If the list is empty, this method returns
	 * <code>null</code>.
	 * 
	 * @param val
	 *            the value of the node to remove from the list
	 * @return the value of the removed node
	 */
	public T remove(T val) {
		if (!isEmpty() && val != null) {
			if (head.val == val) {
				return removeFirst();
			}
			Node<T> curr = head;
			while (curr.val != val) {
				curr = curr.next;
				if (curr == null) {
					return null;
				}
			}
			if (curr == tail) {
				return removeLast();
			}
			T removed = curr.val;
			curr.next.prev = curr.prev;
			curr.prev.next = curr.next;
			size--;
			return removed;
		}
		return null;
	}

	/**
	 * Removes the node that immediately follows the first occurrence of the
	 * node with the specified value. If the list is empty, this method returns
	 * <code>
	 * null</code>.
	 * 
	 * @param val
	 *            the value of the node after which to remove a node
	 * @return the value of the node removed
	 */
	public T removeAfter(T val) {
		if (!isEmpty() && val != null) {
			Node<T> curr = head;
			while (curr.val != val) {
				curr = curr.next;
				if (curr == tail || curr == null) {
					return null;
				}
			}
			if (curr.next == tail) {
				return removeLast();
			}
			T removed = curr.next.val;
			curr.next = curr.next.next;
			curr.next.prev = curr;
			size--;
			return removed;
		}
		return null;
	}

	/**
	 * Removes all occurrences of the node containing the specified value from
	 * the list.
	 * 
	 * @param val
	 *            the value of the nodes to be removed
	 */
	public void removeAll(T val) {
		if (!isEmpty() && val != null) {
			Node<T> curr = head;
			while (curr != null) {
				if (curr.val == val) {
					if (curr == head) {
						removeFirst();
					} else if (curr == tail) {
						removeLast();
					} else {
						curr.next.prev = curr.prev;
						curr.prev.next = curr.next;
						size--;
					}
				}
				curr = curr.next;
			}
		}
	}

	/**
	 * Removes the node at the beginning of the list and returns its value. If
	 * the list is empty, this method returns <code>null</code>.
	 * 
	 * @return the value of the node removed
	 */
	public T removeFirst() {
		if (!isEmpty()) {
			T removed = head.val;
			head = head.next;
			size--;
			return removed;
		}
		return null;
	}

	/**
	 * Removes the node at the end of the list and returns its value. If the
	 * list is empty, this method returns <code>null</code>.
	 * 
	 * @return the value of the removed node
	 */
	public T removeLast() {
		if (!isEmpty()) {
			if (tail.prev == null) {
				return removeFirst();
			}
			T removed = tail.val;
			tail.prev.next = null;
			tail = tail.prev;
			size--;
			return removed;
		}
		return null;
	}

	/**
	 * Returns the value of the node preceding the first occurrence of the node
	 * with the specified value. If no node with the specified value is found,
	 * this method returns <code>null</code>.
	 * 
	 * @param val
	 *            the value of node before which to return
	 * @return the value of the node before the specified node
	 */
	public T getBefore(T val) {
		if (!isEmpty() && val != null) {
			if (head.next.val == val) {
				return getFirst();
			}
			Node<T> curr = head.next;
			while (curr.val != val) {
				curr = curr.next;
				if (curr == null) {
					return null;
				}
			}
			return curr.prev.val;
		}
		return null;
	}

	/**
	 * Returns the value of the node at the beginning of this list, without
	 * removing it.
	 * 
	 * @return the value of the first node in this list
	 */
	public T getFirst() {
		return isEmpty() ? null : head.val;
	}

	/**
	 * Returns the value of the node following the first occurrence of the node
	 * with the specified value, without removing it. If no node with the
	 * specified value is found, this method returns <code>null</code>.
	 * 
	 * @param val
	 *            the value of the node after which to return
	 * @return the value of the node after the specified node
	 */
	public T getAfter(T val) {
		if (!isEmpty() && val != null) {
			if (head.val == val) {
				return head.next.val;
			}
			Node<T> curr = head;
			while (curr.val != val) {
				curr = curr.next;
				if (curr == null) {
					return null;
				}
			}
			return curr == tail ? null : curr.next.val;
		}
		return null;
	}

	/**
	 * Returns the value of the middle node in the list, without removing it.
	 * 
	 * @return the value of the middle node in this list
	 */
	public T getMiddle() {
		if (!isEmpty()) {
			Node<T> slow = head;
			Node<T> fast = head;
			while (fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			return slow.val;
		}
		return null;
	}

	/**
	 * Returns the value of the node at the end of the list, without removing
	 * it.
	 * 
	 * @return the value of the last node in the list
	 */
	public T getLast() {
		return isEmpty() ? null : tail.val;
	}

	/**
	 * Returns the value of the node at the specified index. If the list is
	 * empty or the specified index is not valid, this method returns
	 * <code>null</code>.
	 * 
	 * @param index
	 *            the zero-based location of the desired node in this list
	 * @return the value of the node at the specified index or location
	 */
	public T getAt(int index) {
		if (!isEmpty()) {
			if (index < 0 || index >= size) {
				return null;
			}
			Node<T> curr = head;
			int i = 0;
			while (i != index) {
				curr = curr.next;
				i++;
			}
			return curr.val;
		}
		return null;
	}

	/**
	 * Returns <code>true</code> if and only if the list contains a node with
	 * the specified value.
	 * 
	 * @param val
	 *            the value to search for in this list
	 * @return <code>true</code> if the list contains the a node with 
	 *         the target value <code>false</code> otherwise
	 */
	public boolean contains(T val) {
		if (!isEmpty()) {
			if (head.val == val || tail.val == val) {
				return true;
			}
			Node<T> curr = head.next;
			while (curr != null) {
				if (curr.val == val) {
					return true;
				}
				curr = curr.next;
			}
		}
		return false;
	}

	/**
	 * Replaces the value of the first occurrence of the node containing the
	 * specified <b>target</b> value with the specified <b>replacement</b>
	 * value. If the target node is not found, no changes are made to the list.
	 * 
	 * @param target
	 *            the value of the node to be updated
	 * @param replacement
	 *            the new value to assign to the target node
	 */
	public void replace(T target, T replacement) {
		if (!isEmpty() && target != null && replacement != null) {
			if (head.val == target) {
				head.val = replacement;
			} else if (tail.val == target) {
				tail.val = replacement;
			} else {
				Node<T> curr = head.next;
				while (curr != null) {
					if (curr.val == target) {
						curr.val = replacement;
						return;
					}
					curr = curr.next;
				}
			}
		}
	}

	/**
	 * Replaces the values of all nodes containing the specified <b>target</b>
	 * value with the specified <b>replacement</b> value.
	 * 
	 * @param target
	 *            the value of the nodes to be updated
	 * @param replacement
	 *            the new value to assign to the target nodes
	 */
	public void replaceAll(T target, T replacement) {
		if (!isEmpty() && target != null && replacement != null) {
			Node<T> curr = head;
			while (curr != null) {
				if (curr.val == target) {
					curr.val = replacement;
				}
				curr = curr.next;
			}
		}
	}

	/**
	 * Reverses the order of all nodes in the list.
	 */
	public void reverse() {
		if (!isEmpty()) {
			// First, swap the head and the tail.
			Node<T> temp = head;
			head = tail;
			tail = temp;

			// Then, swap the rest of the nodes.
			Node<T> curr = head;
			while (curr != null) {
				temp = curr.next;
				curr.next = curr.prev;
				curr.prev = temp;
				curr = curr.next;
			}
		}
	}

	/**
	 * Clears the list of all nodes and resets its size to zero.
	 */
	public void clear() {
		if (!isEmpty()) {
			head.next = null;
			head = null;
			tail.prev = null;
			tail = null;
			size = 0;
		}
	}

	/**
	 * Returns <code>true</code> if and only if the list is empty.
	 * 
	 * @return <code>true</code> if this list is empty, <code>false</code>
	 *         otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of nodes in the list.
	 * 
	 * @return the number of nodes currently in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns a string representation of the list and its nodes. 
	 * 
	 * @return a string representation of the current list
	 */
	@Override
	public String toString() {
		if (!isEmpty()) {
			StringBuilder list = new StringBuilder();
			Node<T> curr = head;
			list.append("[");
			while (curr != tail) {
				list.append(curr.val).append(", ");
				curr = curr.next;
			}
			list.append(curr.val).append("]");
			return list.toString();
		}
		return "[]";
	}
}