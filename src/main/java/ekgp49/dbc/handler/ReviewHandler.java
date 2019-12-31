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
          r.getCafeName(), r.getCustomer(), r.getStarRate(), r.getCreatedDate(),
          r.getTimeFormFromToday(), r.getViewCount(), r.getContent());
    }
  }

  public void addReview() {
    System.out.println("리뷰");
    Review review = new Review();
    System.out.print("카페 상호는? : ");
    review.setCafeName(this.input.nextLine());
    System.out.print("고객은? : ");
    review.setCustomer(this.input.nextLine());
    System.out.print("별점은? : ");
    review.setStarRate(this.input.nextInt());
    this.input.nextLine();
    System.out.print("내용은? : ");
    review.setContent(this.input.nextLine());
    review.setCreatedDate(new Date(System.currentTimeMillis())); 
    review.setTimeFormFromToday(String.format("%1$tp %1$tH:%1$tM:%1$tS ", new java.util.Date()));
    review.setViewCount(0);

    this.reviews[this.reviewsCount++] = review;
    System.out.println("저장하였습니다.");
  }

  public void SelectStarRateReview() {
    System.out.print("별점은? ");
    int star = input.nextInt();
    this.input.nextLine();
    for(int i = 0; i < this.reviewsCount; i++) {
      if (star == this.reviews[i].getStarRate()) {
        System.out.println(this.reviews[i].getCafeName());
      }
    }
  }

}
