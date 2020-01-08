package ekgp49.dbc;

import java.util.Scanner;
import ekgp49.dbc.handler.InformationHandler;
import ekgp49.dbc.handler.ReviewHandler;
import ekgp49.dbc.handler.SearchHandler;
public class App {
  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) {
    
    SearchHandler search = new SearchHandler(keyboard);
    ReviewHandler review = new ReviewHandler(keyboard);
    InformationHandler information = new InformationHandler(keyboard);
    
    String command; 
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
   
      switch (command) {
        case "/search/list":
          search.listSearch(); // 이거 하면 검색 키워드 선택하게 하고 
                               //=> 검색 키워드 선택한 대로 information에서 찾아서 쫙 보여줄거임
          break;
        case "/information/add":
          information.addInformation();
          break;
        case "/information/list":
          information.listInformaition();
          break;
        case "/information/update":
          information.updateInformation();
          break;
        case "/information/delete":
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
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));
    System.out.println("종료합니다.");
    keyboard.close();
  }
 
}
