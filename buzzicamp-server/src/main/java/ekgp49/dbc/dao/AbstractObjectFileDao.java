package ekgp49.dbc.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractObjectFileDao<T> {
  String filename;
  File file;
  List<T> list;

  public AbstractObjectFileDao(String filename) {
    this.file = new File(filename);
    list = new LinkedList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  protected void loadData() {
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      list = (List<T>) in.readObject();
      System.out.printf("총 %d개의 객체 데이터를 로딩했습니다.\n", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());
    }
  }

  protected void saveData() {
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.reset();
      out.writeObject(list);
      System.out.printf("총 %d개의 객체 데이터를 세이브했습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

  protected int size() throws Exception {
    return list.size();
  }

  protected abstract <K> int indexOf(K key) throws Exception;
}
