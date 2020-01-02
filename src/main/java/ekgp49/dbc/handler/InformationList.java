package ekgp49.dbc.handler;

import java.util.Arrays;
import ekgp49.dbc.domain.Information;

public class InformationList {
  static final int DEFAULT_SIZE = 3;
  Information[] list;
  int size;

  public InformationList() {
    list = new Information[DEFAULT_SIZE];
  }

  public InformationList(int capacity) {
    if (capacity > 100000 || capacity < DEFAULT_SIZE) {
      list = new Information[DEFAULT_SIZE];
    } else {
      list = new Information[capacity];
    }
  }

  public void add(Information information) {

    if (this.size == this.list.length) {
      int oldCapacity = list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      list = Arrays.copyOf(list, newCapacity);
      /*
      Information[] arr = new Information[newCapacity];
      for (int i = 0; i < this.list.length; i++) {
        arr[i] = this.list[i];
      }
      list = arr;
       */
    } 
    this.list[this.size++] = information;
  }

  public Information[] toArray() {
    /*
    Information[] arr = new Information[size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
     */
    return Arrays.copyOf(this.list, this.size);
  }


}
