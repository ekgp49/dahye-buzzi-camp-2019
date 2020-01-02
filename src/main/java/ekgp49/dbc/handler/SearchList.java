package ekgp49.dbc.handler;

import java.util.Arrays;
import ekgp49.dbc.domain.Search;

public class SearchList {
  static final int DEFAULT_SIZE = 100;
  Search[] list;
  int size = 0;

  public SearchList() {
    this.list = new Search[DEFAULT_SIZE];
  } 

  public SearchList(int capacity) {
    if (capacity > 100000 || capacity < DEFAULT_SIZE) {
      this.list = new Search[DEFAULT_SIZE];
    } else {
      this.list = new Search[capacity];
    }
  }

  public Search[] toArray() {
    /*
    Search[] arr = new Search[this.searchesCount];
    for(int i = 0; i < this.searchesCount; i++) {
      arr[i] = this.searches[i];
    }
     */
    Search[] arr = Arrays.copyOf(this.list, this.size);
    return arr;
  }

  public void add(Search search) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      /*
      Search[] arr = new Search[newCapacity];
      for (int i = 0; i < oldCapacity; i++) {
        arr[i] = this.list[i];
      }
      list = arr;
       */
      this.list = Arrays.copyOf(this.list, newCapacity);
    } 
    this.list[this.size++] = search;

  }
}
