package ekgp49.dbc;

import java.util.Scanner;
public class App {
  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) {
    
    SearchHandler.keyboard = keyboard;
    InformationHandler.keyboard = keyboard;
    ReviewHandler.keyboard = keyboard;
    
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
