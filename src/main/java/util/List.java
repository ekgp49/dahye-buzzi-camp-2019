package util;

public interface List<E> {
  void add(E obj);
  
  void add(int index, E value);
  
  E get(int index);
  
  E set(E obj, int index);
  
  E remove(int index);
  
  E[] toArray(E[] arr);
  
  Object[] toArray();
  
  int size();
}
