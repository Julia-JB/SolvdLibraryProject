package user;

import librarySystem.LibrarySystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Implemented in event.Event class, stores library event attendees
public class LinkedListCustom <E> {
	Node head;
	Node tail;

	public class Node <E> {
		E data;
		Node next = null;

		Node(E data) {
			this.data = data;
		}
	}
	Logger logger = LogManager.getLogger(LibrarySystem.class);

	public LinkedListCustom add(LinkedListCustom list, E data) {
		Node newNode = new Node<>(data);

		if (list.head == null) {
			list.head = newNode;
		} else {
			Node last = list.head;

			while (last.next != null) {
				last = last.next;
			}
			last.next = newNode;
		}
		return list;
	}

	public LinkedListCustom removeLast(LinkedListCustom list) {

		if (head.next == null) {
			list.head = null;
		} else {
			Node last = list.head;

			while (last.next.next != null) {
				last = last.next;
			}
			last.next = null;
		}
		return list;
	}

	public void printCustomList(LinkedListCustom list) {
		Node currentNode = list.head;

		while (currentNode != null) {
			logger.info(currentNode.data);
			currentNode = currentNode.next;
		}
	}

	public LinkedListCustom remove(LinkedListCustom list, E data) {
		Node currentNode = list.head;
		Node previousNode = null;
		boolean matchFound = false;

		while (currentNode != null) {
			if (currentNode.data.equals(data)) {
				if (previousNode == null) {
					list.head = currentNode.next;
				} else {
					previousNode.next = currentNode.next;
				}
				matchFound = true;
				break;
			}
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		if (!matchFound) {
			logger.warn("Element is not in the list");
		}
		return list;
	}

	public int size(LinkedListCustom list) {
		int count = 0;
		Node currentNode = list.head;

		while (currentNode != null) {
			currentNode = currentNode.next;
			count++;
		}
		return count;
	}
}
