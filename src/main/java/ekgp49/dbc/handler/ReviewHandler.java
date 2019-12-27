package ekgp49.dbc.handler;

import java.sql.Date;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;

public class ReviewHandler {
  public static Scanner keyboard;
  static final int REVIEW_SIZE = 100;

  Review[] reviews = new Review[REVIEW_SIZE];
  int reviewsCount = 0;

  public void listReview() {
    System.out.println("리뷰");
    for(int i = 0; i < reviewsCount; i++) {
      Review r = this.reviews[i];
      System.out.printf("%s, %s, %s, %s, %s, %s \n%s\n", 
          r.cafeName, r.customer, r.starRate, r.createdDate,
          r.timeFormFromToday, r.viewCount, r.content);
    }
  }

  public void addReview() {
    System.out.println("리뷰");
    Review review = new Review();
    System.out.print("카페 상호는? : ");
    review.cafeName = keyboard.nextLine();
    System.out.print("고객은? : ");
    review.customer = keyboard.nextLine();
    System.out.print("별점은? : ");
    review.starRate = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("내용은? : ");
    review.content = keyboard.nextLine();
    review.createdDate = new Date(System.currentTimeMillis()); 
    review.today = new java.util.Date(); 
    review.timeFormFromToday = String.format("%1$tp %1$tH:%1$tM:%1$tS ", review.today);
    review.viewCount = 0;

    this.reviews[this.reviewsCount++] = review;
    System.out.println("저장하였습니다.");
  }

  public void SelectStarRateReview() {
    System.out.print("별점은? ");
    int star = keyboard.nextInt();
    keyboard.nextLine();
    for(int i = 0; i < this.reviewsCount; i++) {
      if (star == this.reviews[i].starRate) {
        System.out.println(this.reviews[i].cafeName);
      }
    }
  }

}
