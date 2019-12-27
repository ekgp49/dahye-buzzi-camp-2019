package ekgp49.dbc.handler;

import java.sql.Date;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;

public class ReviewHandler {
  static final int REVIEW_SIZE = 100;

  public Scanner input;
  Review[] reviews = new Review[REVIEW_SIZE];
  int reviewsCount = 0;

  public ReviewHandler(Scanner input) {
    this.input = input;
  }
  
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
    review.cafeName = input.nextLine();
    System.out.print("고객은? : ");
    review.customer = input.nextLine();
    System.out.print("별점은? : ");
    review.starRate = input.nextInt();
    input.nextLine();
    System.out.print("내용은? : ");
    review.content = input.nextLine();
    review.createdDate = new Date(System.currentTimeMillis()); 
    review.today = new java.util.Date(); 
    review.timeFormFromToday = String.format("%1$tp %1$tH:%1$tM:%1$tS ", review.today);
    review.viewCount = 0;

    this.reviews[this.reviewsCount++] = review;
    System.out.println("저장하였습니다.");
  }

  public void SelectStarRateReview() {
    System.out.print("별점은? ");
    int star = input.nextInt();
    input.nextLine();
    for(int i = 0; i < this.reviewsCount; i++) {
      if (star == this.reviews[i].starRate) {
        System.out.println(this.reviews[i].cafeName);
      }
    }
  }

}
