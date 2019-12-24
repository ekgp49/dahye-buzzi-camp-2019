package ekgp49.dbc;

import java.util.Scanner;
import ekgp49.dbc.handler.InformationHandler;
import ekgp49.dbc.handler.ReviewHandler;
import ekgp49.dbc.handler.ReviewHandler2;
import ekgp49.dbc.handler.ReviewHandler3;
import ekgp49.dbc.handler.ReviewHandler4;
import ekgp49.dbc.handler.ReviewHandler5;
import ekgp49.dbc.handler.SearchHandler;
public class App {
  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) {
    
    SearchHandler.keyboard = keyboard;
    InformationHandler.keyboard = keyboard;
    ReviewHandler.keyboard = keyboard;
    ReviewHandler2.keyboard = keyboard;
    ReviewHandler3.keyboard = keyboard;
    ReviewHandler4.keyboard = keyboard;
    ReviewHandler5.keyboard = keyboard;
    
    
    String command; 
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
   
      switch (command) {
        case "/search/add":
          SearchHandler.addSearch();
          break;
        case "/search/list":
          SearchHandler.listSearch();
          break;
        case "/information/add":
          InformationHandler.addInformation();
          break;
        case "/information/list":
          InformationHandler.listInformaition();
          break;
        case "/review/add":
          ReviewHandler.addReview();
          break;
        case "/review/list":
          ReviewHandler.listReview();
          break;
        case "/review2/add":
          ReviewHandler2.addReview();
          break;
        case "/review2/list":
          ReviewHandler2.listReview();
          break;
        case "/review3/add":
          ReviewHandler3.addReview();
          break;
        case "/review3/list":
          ReviewHandler3.listReview();
          break;
        case "/review4/add":
          ReviewHandler4.addReview();
          break;
        case "/review4/list":
          ReviewHandler4.listReview();
          break;
        case "/review5/add":
          ReviewHandler5.addReview();
          break;
        case "/review5/list":
          ReviewHandler5.listReview();
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
