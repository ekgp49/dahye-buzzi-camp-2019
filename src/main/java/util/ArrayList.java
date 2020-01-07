package util;
import java.util.Arrays;

public class ArrayList<E> {
  static final int DEFAULT_SIZE = 3;
  Object[] list;
  int size;

  public ArrayList() {
    this.list = new Object[DEFAULT_SIZE];
  }

  public ArrayList(int capacity) {
    if (capacity > 100000 || capacity < DEFAULT_SIZE) {
      this.list = new Object[DEFAULT_SIZE];
    } else {
      this.list = new Object[capacity];
    }
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if (arr.length < this.size) {
      return (E[])Arrays.copyOf(this.list, this.size, arr.getClass());
    }
   
    System.arraycopy(this.list, 0, arr, 0, this.size);
    
    return arr;
  }

  public void add(E obj) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = obj;
  }
  
  public int getSize() {
    return this.size;
  }

}
