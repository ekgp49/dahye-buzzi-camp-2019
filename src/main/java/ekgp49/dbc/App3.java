package ekgp49.dbc;

import java.sql.Date;
import java.util.Scanner;
public class App3 {

  public static void main(String[] args) {
    // 한 카페의 리뷰 출력
    Scanner keyboard = new Scanner(System.in);
    final int SIZE = 100;

    class Review {
      String cafeName;
      String customer;
      String starRate;
      String content;
      int viewCount;
      Date createdDate; 
      java.util.Date today; 
      String timeFormFromToday;
    }

    Review[] reviews = new Review[SIZE];

    int count = 0;
    for(int i = 0; i < SIZE; i++) {
      System.out.println("리뷰");
      count++;

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

      reviews[i] = review;

      System.out.println("계속 입력하시겠습니까? Y/N");
      System.out.print("=> ");

      String response = keyboard.nextLine(); 

      System.out.println();

      if(!response.equalsIgnoreCase("y")) {
        System.out.println("----------------------------------------");
        break;
      }
    }


    for(int i = 0; i < count; i++) {
      Review review = reviews[i];
      System.out.printf("\n리뷰 \n%s, %s, %s, %s, %s, %s \n%s\n", 
          review.cafeName, review.customer, review.starRate, review.createdDate,
          review.timeFormFromToday, review.viewCount, review.content);
    }

    //    System.out.println("\n리뷰");
    //    System.out.printf("상호 : %s\n", cafeName[i]);
    //    System.out.printf("고객 : %s\n", customer[i]);
    //    System.out.printf("별점 : %s\n", starRate[i]);
    //    System.out.printf("업로드 시간 : %s %s\n", createdDate[i], timeFormFromToday[i]);
    //    System.out.printf("조회수 : %s\n", viewCount[i]);
    //    System.out.printf("내용 : %s\n", content[i]);

    keyboard.close();
  }

}
