package ekgp49.dbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Review;
import ekgp49.dbc.handler.Command;
import ekgp49.dbc.handler.InformationAddCommand;
import ekgp49.dbc.handler.InformationDeleteCommand;
import ekgp49.dbc.handler.InformationListCommand;
import ekgp49.dbc.handler.InformationUpdateCommand;
import ekgp49.dbc.handler.ReviewAddCommand;
import ekgp49.dbc.handler.ReviewDeleteCommand;
import ekgp49.dbc.handler.ReviewListCommand;
import ekgp49.dbc.handler.ReviewSelectCommand;
import ekgp49.dbc.handler.ReviewUpdateCommand;
import ekgp49.dbc.handler.SearchCommand;
import util.Prompt;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  static List<Information> informationList = new LinkedList<>();
  static List<Review> reviewList = new LinkedList<>();

  public static void main(String[] args) {
    loadInformationData();
    loadReviewData();
    Prompt prompt = new Prompt(keyboard);

    HashMap<String, Command> commandHandler = new HashMap<>();
    commandHandler.put("/search", new SearchCommand(prompt, informationList));
    commandHandler.put("/info/add", new InformationAddCommand(prompt, informationList));
    commandHandler.put("/info/list", new InformationListCommand(informationList));
    commandHandler.put("/info/update", new InformationUpdateCommand(prompt, informationList));
    commandHandler.put("/info/delete", new InformationDeleteCommand(prompt, informationList));
    commandHandler.put("/review/add", new ReviewAddCommand(prompt, reviewList));
    commandHandler.put("/review/list", new ReviewListCommand(reviewList));
    commandHandler.put("/review/update", new ReviewUpdateCommand(prompt, reviewList));
    commandHandler.put("/review/delete", new ReviewDeleteCommand(prompt, reviewList));
    commandHandler.put("/review/star", new ReviewSelectCommand(prompt, reviewList));

    String command;
    while (true) {
      command = prompt.inputString("\n명령> ");
      if (command.length() == 0) {
        continue;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equalsIgnoreCase("quit")) {
        System.out.println("종료합니다.");
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      if (commandHandler.get(command) != null) {
        try {
          commandHandler.get(command).execute();
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    saveInformationData();
    saveReviewData();
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    Iterator<String> commands = iterator;
    System.out.println("명령 목록 출력!");
    int count = 0;
    while (commands.hasNext()) {
      System.out.println(commands.next());
      if (++count % 5 == 0 && commands.hasNext()) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  private static void loadInformationData() {
    File file = new File("./information.data");
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Information info = new Information();
        info.setNo(in.readInt());
        info.setCafeName(in.readUTF());
        info.setCafeAddress(in.readUTF());
        info.setCafeCall(in.readUTF());
        info.setCafeWebSite(in.readUTF());
        info.setOpenTime(in.readUTF());
        info.setCloseTime(in.readUTF());
        info.setHolliday(in.readUTF());
        info.setCafeMenu(in.readUTF());

        informationList.add(info);
      }
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());
    }
    System.out.printf("총 %d개의 정보 데이터를 로딩했습니다.\n", informationList.size());
  }

  private static void saveInformationData() {
    File file = new File("./information.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(informationList.size());
      for (Information info : informationList) {
        out.writeInt(info.getNo());
        out.writeUTF(info.getCafeName());
        out.writeUTF(info.getCafeAddress());
        out.writeUTF(info.getCafeCall());
        out.writeUTF(info.getCafeWebSite());
        out.writeUTF(info.getOpenTime());
        out.writeUTF(info.getCloseTime());
        out.writeUTF(info.getHolliday());
        out.writeUTF(info.getCafeMenu());

      }
      System.out.printf("총 %d개의 정보 데이터를 세이브했습니다.\n", informationList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }

  private static void loadReviewData() {
    File file = new File("./review.data");
    try (DataInputStream in =
        new DataInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      int size = in.readInt();
      for (int i = 0; i < size; i++) {
        Review review = new Review();
        review.setNo(in.readInt());
        review.setCafeName(in.readUTF());
        review.setCustomer(in.readUTF());
        review.setStarRate(in.readInt());
        review.setContent(in.readUTF());
        review.setViewCount(in.readInt());
        review.setCreatedDate(Date.valueOf(in.readUTF()));
        review.setTimeFormFromToday(in.readUTF());

        reviewList.add(review);
      }
    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());
    }
    System.out.printf("총 %d개의 리뷰 데이터를 로딩했습니다.\n", reviewList.size());
  }

  private static void saveReviewData() {
    File file = new File("./review.data");
    try (DataOutputStream out =
        new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeInt(reviewList.size());
      for (Review review : reviewList) {
        out.writeInt(review.getNo());
        out.writeUTF(review.getCafeName());
        out.writeUTF(review.getCustomer());
        out.writeInt(review.getStarRate());
        out.writeUTF(review.getContent());
        out.writeInt(review.getViewCount());
        out.writeUTF(review.getCreatedDate().toString());
        out.writeUTF(review.getTimeFormFromToday());
      }
      System.out.printf("총 %d개의 리뷰 데이터를 세이브했습니다.\n", reviewList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());
    }
  }
}
