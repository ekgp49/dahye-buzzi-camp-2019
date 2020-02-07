package ekgp49.dbc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Review;
import ekgp49.dbc.servlet.InformationAddServlet;
import ekgp49.dbc.servlet.InformationDeleteServlet;
import ekgp49.dbc.servlet.InformationListServlet;
import ekgp49.dbc.servlet.InformationUpdateServlet;
import ekgp49.dbc.servlet.ReviewAddServlet;
import ekgp49.dbc.servlet.ReviewDeleteServlet;
import ekgp49.dbc.servlet.ReviewListServlet;
import ekgp49.dbc.servlet.ReviewSelectServlet;
import ekgp49.dbc.servlet.ReviewUpdateServlet;
import ekgp49.dbc.servlet.Servlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

  List<Information> informationList;
  List<Review> reviewList;

  @SuppressWarnings("unchecked")
  void service() {
    notifyApplicationInitialized();
    informationList = (List<Information>) context.get("informationList");
    reviewList = (List<Review>) context.get("reviewList");
    System.out.println("앱 서버입니다");

    servletMap.put("/info/add", new InformationAddServlet(informationList));
    servletMap.put("/info/delete", new InformationDeleteServlet(informationList));
    servletMap.put("/info/list", new InformationListServlet(informationList));
    servletMap.put("/info/update", new InformationUpdateServlet(informationList));
    servletMap.put("/review/add", new ReviewAddServlet(reviewList));
    servletMap.put("/review/delete", new ReviewDeleteServlet(reviewList));
    servletMap.put("/review/list", new ReviewListServlet(reviewList));
    servletMap.put("/review/star", new ReviewSelectServlet(reviewList));
    servletMap.put("/review/update", new ReviewUpdateServlet(reviewList));
    servletMap.put("/search", new InformationListServlet(informationList));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept();

        if (processRequest(socket) == 9) {
          break;
        }

      }
      System.out.println("-------------연결 종료---------------");
    } catch (Exception e) {
      System.out.println("서버 문제 발생");
      e.printStackTrace();
    }
    notifyApplicationDestroyed();
  }


  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      while (true) {
        String request = in.readUTF();
        System.out.println(request);
        System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

        switch (request) {
          case "quit":
            quit(out);
            return 0;
          case "/server/stop":
            quit(out);
            return 9;
        }

        Servlet servlet = servletMap.get(request);
        if (servlet != null) {
          try {
            servlet.service(out, in);
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF("요청처리 중 오류 발생: " + e.getMessage());
            System.out.println("클라이언트 요청 처리 중 오류 발생");
          }
        } else {
          notFount(out);
        }

        System.out.println("클라이언트에게 응답하였음");
        out.flush();
      }
    } catch (Exception e) {
      System.out.println("서버 문제 발생");
      e.printStackTrace();
      return -1;
    }
  }


  private void notFount(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  public void addListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener a : listeners) {
      a.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener a : listeners) {
      a.contextDestroyed(context);
    }
  }

  public static void main(String[] args) {
    ServerApp app = new ServerApp();
    app.addListener(new DataLoaderListener());
    app.service();
  }
}

