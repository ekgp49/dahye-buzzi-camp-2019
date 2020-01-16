package util;

import java.util.Arrays;

public class Stack<E> {
  Object[] elementData;
  int size;
  private static final int DEFAULT_SIZE = 10;
  
  public Stack() {
    elementData = new Object[DEFAULT_SIZE];
  }
  
  public void push(E value) {
    if (this.size == this.elementData.length) {
      grow();
    }
    this.elementData[this.size++] = value; 
  }
  
  private void grow() {
    this.elementData = Arrays.copyOf(this.elementData, newCapacity());
  }
  
  private int newCapacity() {
    int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }
  
  @SuppressWarnings("unchecked")
  public E pop() {
    E value = (E) this.elementData[--this.size];
    this.elementData[this.size] = null;
    return value;
  }
  
  public Boolean empty() {
    return this.size == 0;
  }
  
  @Override
  public Stack<E> clone() {
    Stack<E> stack = new Stack<>();
    Object[] arr = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.elementData[i];
    }
    stack.size = this.size;
    stack.elementData = arr;
    return stack;
  }
  
  public Iterator<E> iterator() {
    return new StackIterator<>(this);
  }
  
  public static class StackIterator<T> implements Iterator<T> {
    Stack<T> stack;
    
    public StackIterator(Stack<T> stack) {
     this.stack = stack.clone();
    }
    
    @Override
    public boolean hasNext() {
      return !stack.empty();
    }
    @Override
    public T next() {
      return stack.pop();
    }
  }

}
