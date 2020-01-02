package ekgp49.dbc.handler;
import java.util.Arrays;

public class ArrayList {
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

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public void add(Object obj) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = obj;
  }

  //  public Review[] get(int star) {
  //    Review[] arr = new Review[size];
  //    int count = 0;
  //    for (int i = 0; i < size; i++) {
  //      if (list[i].getStarRate() == star) {
  //        arr[count++] = list[i];
  //      }
  //    }
  //    return Arrays.copyOf(arr, count);
  //  } 일단 막아놓자
}
