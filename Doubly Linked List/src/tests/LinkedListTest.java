/*
 * File: LinkedListTest.java
 */
package tests;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;

import aghannam.list.LinkedList;

/**
 * This class is a collection of unit tests for the Doubly-Linked List class. 
 * 
 * @author Ahmed Ghannam
 */
public class LinkedListTest {
	
	/**
	 * This array contains our test data and will be used in conjunction with 
	 * the actual linked list. 
	 */
	private Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; 
	
	private LinkedList<Integer> list; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new LinkedList<>(data); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsertLast() {
		// insertLast() is already used by the constructor. 
		assertFalse(list.isEmpty());
		assertEquals(10, list.size()); 
		assertArrayEquals(toArray(list), data); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#insertBefore(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testInsertBefore() {
		assertEquals(Integer.valueOf(4), list.getBefore(5));
		list.insertBefore(5, 15);
		assertTrue(list.size() == 11); 
		assertEquals(Integer.valueOf(15), list.getBefore(5));
		list.insertBefore(15, 17); 
		assertTrue(list.size() == 12); 
		assertEquals(Integer.valueOf(17), list.getBefore(15)); 		
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#insertFirst(java.lang.Object)}.
	 */
	@Test
	public void testInsertFirst() {
		assertEquals(Integer.valueOf(1), list.getFirst());
		list.insertFirst(0);
		assertTrue(list.size() == 11); 
		assertEquals(Integer.valueOf(0), list.getFirst());	
		list.insertFirst(-1);
		assertTrue(list.size() == 12);
		assertEquals(Integer.valueOf(-1), list.getFirst()); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#insertAfter(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testInsertAfter() {
		assertEquals(Integer.valueOf(6), list.getAfter(5));
		list.insertAfter(5, 15);
		assertTrue(list.size() == 11); 
		assertEquals(Integer.valueOf(15), list.getAfter(5));
		list.insertAfter(15, 17); 
		assertTrue(list.size() == 12); 
		assertEquals(Integer.valueOf(17), list.getAfter(15)); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#removeBefore(java.lang.Object)}.
	 */
	@Test
	public void testRemoveBefore() {
		assertEquals(Integer.valueOf(4), list.getBefore(5));
		list.removeBefore(5);
		assertTrue(list.size() == 9); 
		assertEquals(Integer.valueOf(3), list.getBefore(5));
		list.removeBefore(3); 
		assertTrue(list.size() == 8); 
		assertEquals(Integer.valueOf(1), list.getBefore(3)); 
		list.removeBefore(1); 
		assertTrue(list.size() == 8); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemove() {
		assertEquals(Integer.valueOf(8), list.remove(8)); 
		assertTrue(list.size() == 9);
		assertEquals(Integer.valueOf(7), list.remove(7));
		assertTrue(list.size() == 8); 
		list.remove(100);
		assertTrue(list.size() == 8); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#removeAfter(java.lang.Object)}.
	 */
	@Test
	public void testRemoveAfter() {
		assertEquals(Integer.valueOf(6), list.getAfter(5));
		list.removeAfter(5);
		assertTrue(list.size() == 9); 
		assertEquals(Integer.valueOf(7), list.getAfter(5));
		list.removeAfter(7); 
		assertTrue(list.size() == 8); 
		assertEquals(Integer.valueOf(9), list.getAfter(7)); 
		list.removeAfter(10); 
		assertTrue(list.size() == 8); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#removeAll(java.lang.Object)}.
	 */
	@Test
	public void testRemoveAll() {
		list.removeAll(1);
		assertTrue(list.size() == 9); 
		list.removeAll(2);
		assertTrue(list.size() == 8); 
		list.insertAfter(5, 10); // size is now 9
		list.removeAll(10);
		assertTrue(list.size() == 7);
		list.removeAll(100);
		assertTrue(list.size() == 7); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst() {
		assertEquals(Integer.valueOf(1), list.removeFirst());
		assertTrue(list.size() == 9); 
		assertEquals(Integer.valueOf(2), list.removeFirst());
		assertTrue(list.size() == 8); 
		assertEquals(Integer.valueOf(3), list.removeFirst());
		assertTrue(list.size() == 7); 
		list.insertFirst(55);
		assertEquals(Integer.valueOf(55), list.removeFirst());
		assertEquals(Integer.valueOf(4), list.getFirst());
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#removeLast()}.
	 */
	@Test
	public void testRemoveLast() {
		assertEquals(Integer.valueOf(10), list.removeLast());
		assertTrue(list.size() == 9); 
		assertEquals(Integer.valueOf(9), list.removeLast());
		assertTrue(list.size() == 8); 
		assertEquals(Integer.valueOf(8), list.removeLast());
		assertTrue(list.size() == 7); 
		list.insert(55);
		assertEquals(Integer.valueOf(55), list.removeLast());
		assertEquals(Integer.valueOf(7), list.getLast());
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#getBefore(java.lang.Object)}.
	 */
	@Test
	public void testGetBefore() {
		assertNull(list.getBefore(list.getFirst())); 
		assertEquals(Integer.valueOf(2), list.getBefore(3)); 
		assertEquals(Integer.valueOf(1), list.getBefore(2));
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#getFirst()}.
	 */
	@Test
	public void testGetFirst() {
		assertEquals(Integer.valueOf(1), list.getFirst());
		list.insertFirst(0);
		assertEquals(Integer.valueOf(0), list.getFirst()); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#getAfter(java.lang.Object)}.
	 */
	@Test
	public void testGetAfter() {
		assertEquals(Integer.valueOf(2), list.getAfter(1));
		list.insertFirst(0);
		assertEquals(Integer.valueOf(1), list.getAfter(0)); 
		assertNull(list.getAfter(10)); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#getMiddle()}.
	 */
	@Test
	public void testGetMiddle() {
		assertEquals(Integer.valueOf(5), list.getMiddle()); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#getLast()}.
	 */
	@Test
	public void testGetLast() {
		assertEquals(Integer.valueOf(10), list.getLast()); 
		list.insert(11);
		assertEquals(Integer.valueOf(11), list.getLast()); 
		assertTrue(list.size() == 11); 
		list.removeLast();
		assertTrue(list.size() == data.length); 
		assertEquals(Integer.valueOf(10), list.getLast()); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#getAt(int)}.
	 */
	@Test
	public void testGetAt() {
		// Already tested by the following call. 
		assertArrayEquals(toArray(list), data); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		assertTrue(list.contains(1));
		assertTrue(list.contains(5));
		assertFalse(list.contains(11));
		list.insert(11);
		assertTrue(list.contains(11)); 
		assertFalse(list.contains(0));
		list.insertFirst(0);
		assertTrue(list.contains(0)); 
		list.remove(5);
		assertFalse(list.contains(5)); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#replace(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testReplace() {
		list.replace(Integer.valueOf(5), 33);
		assertTrue(list.getMiddle() == 33);
		assertTrue(list.contains(33)); 
		list.remove(33); 
		assertTrue(list.size() == 9); 
		list.replace(list.getFirst(), 0);
		assertTrue(list.getFirst() == 0); 
		list.replace(list.getLast(), 0);
		assertTrue(list.getLast() == 0); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#replaceAll(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testReplaceAll() {
		list.insertFirst(0);
		list.insertAfter(5,0);
		list.insert(0);
		assertTrue(list.contains(0));
		assertTrue(list.size() == 13);
		list.replaceAll(0, 100);
		assertFalse(list.contains(0));
		assertTrue(list.contains(100)); 
		assertTrue(list.size() == 13); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#reverse()}.
	 */
	@Test
	public void testReverse() {
		reverse(data); 
		assertThat(data, IsNot.not(IsEqual.equalTo(toArray(list)))); 
		list.reverse();
		assertArrayEquals(toArray(list), data); 
	}

	/**
	 * Test method for {@link aghannam.list.LinkedList#clear()}.
	 */
	@Test
	public void testClear() {
		assertFalse(list.isEmpty()); 
		list.clear();
		assertTrue(list.isEmpty()); 
		assertTrue(toArray(list).length == 0); 
	}
	
	/*
	 * Helper methods to make testing certain operations a bit easier.
	 */
	
	private Integer[] toArray(LinkedList<Integer> l) {
		Integer[] a = new Integer[l.size()]; 
		for (int i = 0; i < a.length; i++) {
			a[i] = (Integer) l.getAt(i); 
		}
		return a; 
	}
	
	private void reverse(Integer[] a) {
		int lo = 0; 
		int hi = a.length - 1; 
		while (lo < hi) {
			Integer temp = a[lo];
			a[lo] = a[hi];
			a[hi] = temp;
			lo++;
			hi--; 
		}
	}
}
