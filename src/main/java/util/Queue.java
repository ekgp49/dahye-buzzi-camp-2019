package util;

public class Queue<E> extends LinkedList<E>{
  
  public void offer(E value) {
    this.add(value);
  }
  
  public E poll() {
    return this.remove(0);
  }
  
  @Override
  public Queue<E> clone() {
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < this.size(); i++) {
      temp.offer(this.get(i));
    }
    return temp;
  }
  
  public Iterator<E> iterator() {
    return new QueueIterator<>(this);
  }
  
  public static class QueueIterator<T> implements Iterator<T> {
    Queue<T> queue;

    public QueueIterator(Queue<T> queue) {
      this.queue = queue.clone();
    }
    @Override
    public boolean hasNext() {
      return queue.size() > 0;
    }
    @Override
    public T next() {
      return queue.poll();
    }
  }
}
