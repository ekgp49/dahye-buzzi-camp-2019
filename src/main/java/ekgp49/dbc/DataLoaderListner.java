package ekgp49.dbc;

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
import java.util.Map;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Review;

public class DataLoaderListner implements ApplicationContextListener {
  List<Information> informationList = new LinkedList<>();
  List<Review> reviewList = new LinkedList<>();

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    saveInformationData();
    saveReviewData();
  }

  @Override
  public void contextInitialized(Map<String, Object> context) {
    loadInformationData();
    loadReviewData();

    context.put("informationList", informationList);
    context.put("reviewList", reviewList);
  }


  @SuppressWarnings("unchecked")
  private void loadInformationData() {
    File file = new File("./information.data");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      informationList = (List<Information>) in.readObject();
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());
    }
    System.out.printf("총 %d개의 정보 데이터를 로딩했습니다.\n", informationList.size());
  }

  private void saveInformationData() {
    File file = new File("./information.data");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(informationList);
      System.out.printf("총 %d개의 정보 데이터를 세이브했습니다.\n", informationList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadReviewData() {
    File file = new File("./review.data");
    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      reviewList = (List<Review>) in.readObject();
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());
    }
    System.out.printf("총 %d개의 리뷰 데이터를 로딩했습니다.\n", reviewList.size());
  }

  private void saveReviewData() {
    File file = new File("./review.data");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(reviewList);
      System.out.printf("총 %d개의 리뷰 데이터를 세이브했습니다.\n", reviewList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

}
