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
  
  public int size() {
    return this.size;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= this.size)
      return null;
    
    return (E)list[index];
  }

  @SuppressWarnings("unchecked")
  public E set(E obj, int index) {
    
    if (index < 0 || index >= this.size)
      return null;
    
    this.list[index] = obj;
    E old = (E) this.list[index];
    
    return old;
  }

  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= this.size)
      return null;
  
    E old = (E) this.list[index];
   
    for (int i = index + 1; i < this.size; i++) {
      this.list[i-1] = this.list[i];
    }
   
    size--;
    this.list[size] = null;
   
    return old;
  }

}
