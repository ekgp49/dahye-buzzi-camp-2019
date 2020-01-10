package util;

import java.lang.reflect.Array;

public class LinkedList<E> {

  Node<E> first;

  Node<E> last;

  private int size;

  public void add(E value) {
    Node<E> newNode = new Node<>();
    newNode.value = value;
    if (first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    this.size++;
  }

  public void add(int index, E value) {
    if (index < 0 || index >= this.size) {
      return;
    }
    Node<E> newNode = new Node<>();
    newNode.value = value;
    Node<E> cursor = first;
    if (index == 0) {
      newNode.next = first;
      first = newNode;
    } else {
      for (int i = 0; i < index - 1; i++) {
        cursor = cursor.next;
      }
      newNode.next = cursor.next;
      cursor.next = newNode;
    }
    this.size++;
  }

  public E get(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    Node<E> cursor = first;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  public E set(int index, E value) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    Node<E> cursor = first;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    E oldValue = cursor.value;
    cursor.value = value;
    return oldValue;
  }

  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      return null;
    }
    Node<E> deletedNode;
    Node<E> cursor = first;
    if (index == 0) {
      deletedNode = first;
      first = deletedNode.next;
    } else {
      for (int i = 0; i < index - 1; i++) {
        cursor = cursor.next;
      }
      deletedNode = cursor.next;
      cursor.next = deletedNode.next;
    }
    deletedNode.next = null;
    this.size--;
    return deletedNode.value;
  }

  public Object[] toArray() {
    Object[] arr = new Object[this.size];
    Node<E> cursor = first;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    arr = (E[]) Array.newInstance(arr.getClass().getComponentType(), this.size);
    Node<E> cursor = first;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next; 
    }
    return arr;
  }

  public int size() {
    return this.size;
  }

  class Node<T> {
    Node<T> next;
    T value;
  }

}