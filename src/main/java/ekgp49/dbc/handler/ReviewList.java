package ekgp49.dbc.handler;
import java.util.Arrays;
import ekgp49.dbc.domain.Review;

public class ReviewList {
  static final int DEFAULT_SIZE = 100;
  Review[] list;
  int size;

  public ReviewList() {
    list = new Review[DEFAULT_SIZE];
  }

  public ReviewList(int capacity) {
    if (capacity > 100000 || capacity < DEFAULT_SIZE) {
      list = new Review[DEFAULT_SIZE];
    } else {
      list = new Review[capacity];
    }
  }

  public Review[] toArray() {
    /*
    Review[] arr = new Review[size];
    for (int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    return arr;
     */

    return Arrays.copyOf(this.list, this.size);
  }

  public void add(Review review) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = review;
  }

  public Review[] get(int star) {
    Review[] arr = new Review[size];
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (list[i].getStarRate() == star) {
        arr[count++] = list[i];
      }
    }
    return Arrays.copyOf(arr, count);
  }
}
