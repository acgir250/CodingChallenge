package com.shreeji.tech;

import java.util.List;

public interface Queue<T> {

	/*Add  the element in the immutable queue, and returns the new queue.*/
	public Queue<T> enQueue(T t);

    /*Removes the element at the beginning of the immutable queue, and returns the new queue.*/
	public Queue<T> deQueue() ;

	public T head() ;

	public boolean isEmpty();

}
