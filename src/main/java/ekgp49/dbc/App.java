package ekgp49.dbc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import ekgp49.dbc.handler.InformationHandler;
import ekgp49.dbc.handler.ReviewHandler;
import ekgp49.dbc.handler.SearchHandler;
import util.Prompt;
public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();
  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);

    SearchHandler search = new SearchHandler(prompt);
    ReviewHandler review = new ReviewHandler(prompt, new LinkedList<>());
    InformationHandler information = new InformationHandler(prompt, new ArrayList<>());

    String command; 
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command != "") {
        commandStack.push(command);
        commandQueue.offer(command);
      }

      switch (command) {
        case "/search":
          search.keySearch(information); // 이거 하면 검색 키워드 선택하게 하고 
          //=> 검색 키워드 선택한 대로 information에서 찾아서 쫙 보여줄거임
          // App에서 만든 InformationHandler 객체를 넘겨줘야 그 객체에 저장된 informationList 객체를 이용할 수 있다
          break;
        case "/info/add":
          information.addInformation();
          break;
        case "/info/list":
          information.listInformaition();
          break;
        case "/info/update":
          information.updateInformation();
          break;
        case "/info/delete":
          information.deleteInformaition();
          break;
        case "/review/add":
          review.addReview();
          break;
        case "/review/list":
          review.listReview();
          break;
        case "/review/update":
          review.updateReview();
          break;
        case "/review/delete":
          review.deleteReview();
          break;
        case "/review/star":
          review.SelectStarRateReview();
          break;
        case "history" :
          printCommandHistory(commandStack.iterator());
          break;
        case "history2" :
          printCommandHistory(commandQueue.iterator());
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("종료합니다.");
    keyboard.close();
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    Iterator<String> commands = iterator;
    System.out.println("명령 목록 출력!");
    int count = 0;
    while (commands.hasNext()) {
      System.out.println(commands.next());
      if ((++count % 5) == 0 && commands.hasNext()) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
