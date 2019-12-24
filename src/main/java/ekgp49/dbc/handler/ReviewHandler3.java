package ekgp49.dbc.handler;

import java.sql.Date;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;

public class ReviewHandler3 {
  public static Scanner keyboard;
  static final int REVIEW_SIZE = 100;

  static Review[] reviews = new Review[REVIEW_SIZE];
  static int reviewsCount = 0;
  
  public static void listReview() {
    System.out.println("리뷰");
    for(int i = 0; i < reviewsCount; i++) {
      Review r = reviews[i];
      System.out.printf("%s, %s, %s, %s, %s, %s \n%s\n", 
          r.cafeName, r.customer, r.starRate, r.createdDate,
          r.timeFormFromToday, r.viewCount, r.content);
    }
  }

  public static void addReview() {
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
  
}
