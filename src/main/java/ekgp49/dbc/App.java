package ekgp49.dbc;

import java.sql.Date;
import java.util.Scanner;
public class App {
  static Scanner keyboard = new Scanner(System.in);
 
  static final int SEARCH_SIZE = 100;
  static final int INFORMATION_SIZE = 100;
  static final int REVIEW_SIZE = 100;
  
  static class Search {
    String cafeArea;
    String cafeName;
    String cafeMenu; 
    String starRate;
  }
  
  static class Information {
    String cafeName;
    String cafeAddress;
    String cafeCall;
    String cafeWebSite; 
    String openTime;
    String closeTime;
    String holliday; 
    String cafeMenu;
    String starRate;
  }
  
  static class Review {
    String cafeName;
    String customer;
    String starRate;
    String content;
    int viewCount;
    Date createdDate; 
    java.util.Date today; 
    String timeFormFromToday;
  }
  
  static Search[] searches = new Search[SEARCH_SIZE];
  static Information[] informations = new Information[INFORMATION_SIZE];
  static Review[] reviews = new Review[REVIEW_SIZE];
  
  static int searchesCount = 0;
  static int informationsCount = 0;
  static int reviewsCount = 0;
 
  public static void main(String[] args) {
   
    
    String command; 
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
   
      switch (command) {
        case "/search/add":
          addSearch();
          break;

        case "/search/list":
          listSearch();
          break;

        case "/information/add":
          addInformation();
          break;
          
        case "/information/list":
          listInformaition();
          break;
          
        case "/review/add":
          addReview();
          break;
          
        case "/review/list":
          listReview();
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

  private static void listReview() {
    System.out.println("리뷰");
    for(int i = 0; i < reviewsCount; i++) {
      Review r = reviews[i];
      System.out.printf("%s, %s, %s, %s, %s, %s \n%s\n", 
          r.cafeName, r.customer, r.starRate, r.createdDate,
          r.timeFormFromToday, r.viewCount, r.content);
    }
  }

  private static void addReview() {
    System.out.println("리뷰");
    Review review = new Review();
    System.out.print("카페 상호는? : ");
    review.cafeName = keyboard.nextLine();
    System.out.print("고객은? : ");
    review.customer = keyboard.nextLine();
    System.out.print("별점은? : ");
    review.starRate = keyboard.nextLine();
    System.out.print("내용은? : ");
    review.content = keyboard.nextLine();
    review.createdDate = new Date(System.currentTimeMillis()); 
    review.today = new java.util.Date(); 
    review.timeFormFromToday = String.format("%1$tp %1$tH:%1$tM:%1$tS ", review.today);
    review.viewCount = 0;
    
    reviews[reviewsCount++] = review;
    System.out.println("저장하였습니다.");
  }

  private static void listInformaition() {
    for(int i = 0; i < informationsCount; i++) {
      Information info = informations[i];
      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s, %s\n", 
      info.cafeName, info.cafeAddress, info.cafeCall, info.cafeWebSite, info.openTime, 
      info.closeTime, info.holliday, info.cafeMenu, info.starRate);
    }
  }

  private static void addInformation() {
    Information information = new Information();
    System.out.print("카페 상호는? : ");
    information.cafeName = keyboard.nextLine();
    System.out.print("주소는? : ");
    information.cafeAddress = keyboard.nextLine();
    System.out.print("연락처는? : ");
    information.cafeCall = keyboard.nextLine();
    System.out.print("웹사이트는? : ");
    information.cafeWebSite = keyboard.nextLine();
    System.out.print("오픈시간은? : ");
    information.openTime = keyboard.nextLine();
    System.out.print("종료시간은? : ");
    information.closeTime = keyboard.nextLine();
    System.out.print("정기 휴일은? : ");
    information.holliday = keyboard.nextLine();
    System.out.print("메뉴는? : ");
    information.cafeMenu = keyboard.nextLine();
    System.out.print("별점은? : ");
    information.starRate = keyboard.nextLine();
   
    informations[informationsCount++] = information;
    System.out.println("저장하였습니다.");
  }

  private static void listSearch() {
    System.out.println("검색 키워드");
    for(int i = 0; i < searchesCount; i++) {
      Search s = searches[i];
      System.out.printf("%s, %s, %s, %s\n", 
          s.cafeArea, s.cafeName, s.cafeMenu, s.starRate);
    }
  }

  private static void addSearch() {
    Search search = new Search();
    System.out.print("지역은? : ");
    search.cafeArea = keyboard.nextLine();
    System.out.print("카페 상호는? : ");
    search.cafeName = keyboard.nextLine();    
    System.out.print("메뉴는? : ");
    search.cafeMenu = keyboard.nextLine();
    System.out.print("별점은? : ");
    search.starRate = keyboard.nextLine();
    
    searches[searchesCount++] = search;
    System.out.println("저장하였습니다.");
  }

}
