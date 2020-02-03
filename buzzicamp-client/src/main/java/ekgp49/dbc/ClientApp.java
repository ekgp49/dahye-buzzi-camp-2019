package ekgp49.dbc;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
  public static void main(String[] args) throws Exception {
    System.out.println("앱 클라이언트입니다");

    try (Socket socket = new Socket("localhost", 9999);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());
        Scanner scan = new Scanner(System.in);) {

      String message = scan.nextLine();
      out.println(message);
      System.out.println("앱: " + message);

      System.out.println("서버측 메시지: " + in.nextLine());

      System.out.println("서버연결종료");
    } catch (Exception e) {
      System.out.println("서버 연결 중 오류가 발생했습니다");
      e.printStackTrace();
    }

  }
}
