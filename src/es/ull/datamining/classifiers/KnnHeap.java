package es.ull.datamining.classifiers;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.common.collect.MinMaxPriorityQueue;

public class KnnHeap implements Iterable<KnnHeapElement>{

	private MinMaxPriorityQueue<KnnHeapElement> queue;
	private int size;

	/**
	 * Creates a PriorityQueue with the specified initial capacity that orders
	 * its elements according to their natural ordering.
	 * 
	 * @param size
	 *            the capacity for this priority queue
	 */
	public KnnHeap(int size) {
		this.size = size;
		queue = MinMaxPriorityQueue.maximumSize(size).create();
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param element
	 *            the element to add
	 * @return true always
	 * @see com.google.common.collect.MinMaxPriorityQueue#add(E)
	 */
	public boolean add(KnnHeapElement element) {
		return queue.add(element);
	}

	/**
	 * Removes all of the elements from this queue. The queue will be empty
	 * after this call returns
	 * 
	 * @see com.google.common.collect.MinMaxPriorityQueue#clear()
	 */
	public void clear() {
		queue.clear();
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param element
	 *            the element to add
	 * @return if the element was added to this queue, else false
	 * @see com.google.common.collect.MinMaxPriorityQueue#offer(E)
	 */
	public boolean offer(KnnHeapElement element) {
		return queue.offer(element);
	}

	/**
	 * @see com.google.common.collect.MinMaxPriorityQueue#peek() Retrieves, but
	 *      does not remove, the head of this queue, or returns null if this
	 *      queue is empty.
	 * 
	 * @return the head of this queue, or null if this queue is empty
	 * @see com.google.common.collect.MinMaxPriorityQueue#peek()
	 */
	public KnnHeapElement peek() {
		return queue.peek();
	}

	/**
	 * Retrieves and removes the head of this queue, or returns null if this
	 * queue is empty.
	 * 
	 * @return the head of this queue, or null if this queue is empty
	 * @see com.google.common.collect.MinMaxPriorityQueue#poll()
	 */
	public KnnHeapElement poll() {
		return queue.poll();
	}

	/**
	 * 
	 * 
	 * Removes and returns the least element of this queue.
	 * 
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 * @see com.google.common.collect.MinMaxPriorityQueue#removeFirst()
	 */
	public void removeHead() throws NoSuchElementException {
		queue.removeFirst();
	}

	/**
	 * Removes and returns the greatest element of this queue.
	 * 
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public void removeTail() throws NoSuchElementException {
		queue.removeLast();
	}

	/**
	 * Returns the number of elements in this collection. If this collection
	 * contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * 
	 * @return the number of elements in this collection
	 * @see com.google.common.collect.MinMaxPriorityQueue#size()
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an array containing all of the elements in this queue. The
	 * elements are in no particular order. The returned array will be "safe" in
	 * that no references to it are maintained by this queue. (In other words,
	 * this method must allocate a new array). The caller is thus free to modify
	 * the returned array. This method acts as bridge between array-based and
	 * collection-based APIs.
	 * 
	 * @return an array containing all of the elements in this queue
	 * @see com.google.common.collect.MinMaxPriorityQueue#toArray()
	 */
	public Object[] toArray() {
		return queue.toArray();
	}

	/**
	 * Returns true if this collection contains no elements.
	 * 
	 * @return true if this collection contains no elements
	 * @see java.util.AbstractCollection#isEmpty()
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * @return an iterator over the {@link KnnHeapElement} contained in this
	 *         collection
	 */
	public Iterator<KnnHeapElement> iterator() {
		return queue.iterator();
	}

	/**
	 * Returns the instance class name at the specified position in this heap.
	 * 
	 * @param index
	 *            index of the element to return
	 * @return a {@link KnnHeapElement} instance class name
	 */
//	public String getInstanceClass(int index) {
//		Iterator<KnnHeapElement> it = this.iterator();
//		KnnHeapElement he = null;
//		int i = 0;
//		while (it.hasNext()) {
//			if (i == index)
//				he = it.next();
//			i++;
//		}
//		Instance instance = he.getInstance();
//		for (int j = 0; j < instance.size(); j++) {
//			if (!instance.getValue(j).isNumeric())
//				return instance.getValue(j).getValue().toString();
//		}
//		return null;
//	}

	public KnnHeapElement get(int index) {
		Iterator<KnnHeapElement> it = this.iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i == index)
				return it.next();
			i++;
		}
		return null;
	}

}
