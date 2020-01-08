package ekgp49.dbc.handler;

import java.sql.Date;
import java.util.Arrays;
import java.util.Scanner;
import ekgp49.dbc.domain.Review;
import util.ArrayList;

public class ReviewHandler {
  public Scanner input;
  ArrayList<Review> reviewList;
  private static int no = 1;

  public ReviewHandler(Scanner input) {
    this.input = input;
    reviewList = new ArrayList<>();
  }

  public ReviewHandler(Scanner input, int capacity) {
    this.input = input;
    reviewList = new ArrayList<>(capacity);
  }

  public void listReview() {
    System.out.println("리뷰");
    Review[] arr = reviewList.toArray(new Review[] {});
    for(Review r : arr) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s \n%s\n", 
          r.getNo(), r.getCafeName(), r.getCustomer(), r.getStarRate(), r.getCreatedDate(),
          r.getTimeFormFromToday(), r.getViewCount(), r.getContent());
    }
  }

  public void addReview() {
    System.out.println("리뷰");
    Review review = new Review();
    review.setNo(no++);
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

    reviewList.add(review);
    System.out.println("저장하였습니다.");
  }

  public void updateReview() {
    System.out.println("리뷰 번호는? ");
    int no = this.input.nextInt();
    input.nextLine();
    int index = -1;
    
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      System.out.println("해당 번호의 리뷰가 없습니다.");
      return;
    }
    
    Review old = this.reviewList.get(index);
    
    System.out.println("리뷰를 변경하시겠습니까? (Y/n)");
    String answer = input.nextLine();
    if (!answer.equalsIgnoreCase("y")) {
      System.out.println("게시글 변경을 취소했습니다.");
      return;
    }
    Review review = new Review();
    review.setNo(this.reviewList.get(index).getNo());
    review.setCafeName(old.getCafeName());
    review.setCustomer(old.getCustomer());
    System.out.printf("별점은?(%s) : ", this.reviewList.get(index).getStarRate());
    review.setStarRate(this.input.nextInt());
    this.input.nextLine();
    System.out.print("내용은? : ");
    review.setContent(this.input.nextLine());
    review.setCreatedDate(old.getCreatedDate()); 
    review.setTimeFormFromToday(old.getTimeFormFromToday());
    review.setViewCount(old.getViewCount());

    reviewList.set(review, index);
    System.out.println("리뷰를 수정했습니다.");
  }

  public void deleteReview() {
    System.out.println("리뷰 번호는? ");
    int no = this.input.nextInt();
    input.nextLine();
    int index = -1;
    
    for (int i = 0; i < this.reviewList.size(); i++) {
      if (this.reviewList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      System.out.println("해당 번호의 리뷰가 없습니다.");
      return;
    }
    
    this.reviewList.remove(index);
    System.out.println("리뷰를 삭제했습니다.");
  }
  
    public void SelectStarRateReview() {
      System.out.print("별점? ");
      int star = input.nextInt();
      this.input.nextLine();
      
      Review[] arr = new Review[this.reviewList.size()];
      int count = 0;
      for (int i = 0; i < this.reviewList.size(); i++) {
        if (this.reviewList.get(i).getStarRate() == star) {
          Review review = this.reviewList.get(i);
          arr[count++] = review;
        }
      }
      arr = Arrays.copyOf(arr, count);
      
      for (Review r : arr) {
        System.out.println(r.getCafeName());
      }
    } 

}
