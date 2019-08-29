package com.shreeji.tech;

import java.util.NoSuchElementException;
import com.queue.pack.Queue;

public final class CustomQueueImpl<T> implements Queue<T> {

	private CustomLinkedList<T> realQueue;
	private CustomLinkedList<T> replicatedQueue;

	public CustomQueueImpl() {
		this.realQueue = CustomLinkedList.getInstance();
		this.replicatedQueue = CustomLinkedList.getInstance();
	}

	public static final CustomQueueImpl getInstance() {
		return new CustomQueueImpl();
	}

	private CustomQueueImpl(CustomLinkedList<T> realQueue, CustomLinkedList<T> replicatedQueue) {
		this.realQueue = realQueue;
		this.replicatedQueue = replicatedQueue;
	}

	@Override
	public final Queue<T> enQueue(T object) {
		if (object == null)
			throw new NullPointerException("Element must not be empty while adding in the queue");
		return new CustomQueueImpl<T>(this.realQueue.add(object), this.replicatedQueue);
	}

	@Override
	public final Queue<T> deQueue() {
		if (this.isEmpty())
			throw new IllegalArgumentException("Current Queue is empty");
		if (!this.replicatedQueue.isEmpty()) {
			return new CustomQueueImpl<T>(this.realQueue, this.replicatedQueue.next);
		} else {
			return new CustomQueueImpl<T>(CustomLinkedList.getInstance(), this.realQueue.replicateList().next);
		}
	}

	@Override
	public final T head() {
		if (this.isEmpty())
			throw new NoSuchElementException("Current Queue is empty");
		if (this.replicatedQueue.isEmpty())
			copyRealQueueToReplicateQueue();
		return (T) this.replicatedQueue.data;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private int size() {
		return this.realQueue.size + this.replicatedQueue.size;
	}

	private void copyRealQueueToReplicateQueue() {
		this.replicatedQueue = this.realQueue.replicateList();
		this.realQueue = CustomLinkedList.getInstance();
	}

	private static final class CustomLinkedList<T>{

		private T data = null;
		private CustomLinkedList<T> next = null;

		private int size = 0;

		private CustomLinkedList() {
		}

		private CustomLinkedList(T obj, CustomLinkedList<T> tail) {
			this.data = obj;
			this.next = tail;
			this.size = tail.size + 1;
		}

		public static CustomLinkedList getInstance() {
			return new CustomLinkedList();
		}

		public boolean isEmpty() {
			return this.size == 0;
		}

		public CustomLinkedList<T> add(T obj) {
			return new CustomLinkedList(obj, this);
		}

		public CustomLinkedList<T> replicateList() {
			CustomLinkedList<T> list = new CustomLinkedList<T>();
			CustomLinkedList<T> tail = this;
			while (!tail.isEmpty()) {
				list = list.add(tail.data);
				tail = tail.next;
			}
			return list;
		}
	}


}

