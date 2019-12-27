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
        case "/search/add":
          search.addSearch();
          break;
        case "/search/list":
          search.listSearch();
          break;
        case "/information/add":
          information.addInformation();
          break;
        case "/information/list":
          information.listInformaition();
          break;
        case "/review/add":
          review.addReview();
          break;
        case "/review/list":
          review.listReview();
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
