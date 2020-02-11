package ekgp49.dbc.dao.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;

public abstract class AbstractJsonFileDao<T> {
  String filename;
  List<T> list;
  File file;

  public AbstractJsonFileDao(String filename) {
    this.filename = filename;
    list = new LinkedList<>();
    file = new File(filename);
    loadData();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void loadData() {
    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      // 현재 클래스의 정보를 알아낸다.
      Class<?> currType = this.getClass();
      System.out.println(currType);

      // 제네틱타입의 수퍼 클래스 정보를 알아낸다.
      Type parentType = currType.getGenericSuperclass();
      System.out.println(parentType);

      // 수퍼클래스의 타입 파라미터중에서 T 값을 추출한다.
      // => 수퍼클래스에 제네릭이 적용된 경우 실제 타입은 다음과 같다.
      ParameterizedType parentType2 = (ParameterizedType) parentType;

      // => 제네릭 수퍼 클래스 정보로부터 타입파라미터 목록을 꺼낸다
      Type[] typeParams = parentType2.getActualTypeArguments();

      Type itemType = typeParams[0];
      System.out.println(itemType);

      T[] arr = (T[]) Array.newInstance((Class) itemType, 0); // 걍 T[]의 클래스 얻는 과정

      // list = (List<T>) new Gson().fromJson(in, List<T>)
      T[] dataArray = (T[]) new Gson().fromJson(in, arr.getClass());
      for (T b : dataArray) {
        list.add(b);
      }

      System.out.printf("총 %d개의 객체 데이터를 로딩했습니다.\n", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());
    }
  }

  protected void saveData() {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
      new Gson().toJson(list, out);
      // out.write(new Gson().toJson(list)); <= 이거로 해도 됨
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
