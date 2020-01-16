package util;

public abstract class AbstractList<E> implements List<E> {
  protected int size;
  
  public int size() {
    return this.size;
  }
  
  public Iterator<E> iterator() {
    return new ListIterator<>(this);
  }
  
  public static class ListIterator<T> implements Iterator<T> {
    List<T> list;
    int cursor;
    
    public ListIterator(List<T> list) {
     this.list = list;
    }
    
    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }
    @Override
    public T next() {
      return list.get(cursor++);
    }
  }
}
