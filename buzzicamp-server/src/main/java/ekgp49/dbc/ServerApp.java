package ekgp49.dbc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import ekgp49.dbc.context.ApplicationContextListener;
import ekgp49.dbc.service.InformationService;
import ekgp49.dbc.service.ReviewService;
import ekgp49.dbc.servlet.InformationAddServlet;
import ekgp49.dbc.servlet.InformationDeleteServlet;
import ekgp49.dbc.servlet.InformationDetailServlet;
import ekgp49.dbc.servlet.InformationListServlet;
import ekgp49.dbc.servlet.InformationSearchServlet;
import ekgp49.dbc.servlet.InformationUpdateServlet;
import ekgp49.dbc.servlet.ReviewAddServlet;
import ekgp49.dbc.servlet.ReviewDeleteServlet;
import ekgp49.dbc.servlet.ReviewListServlet;
import ekgp49.dbc.servlet.ReviewRateServlet;
import ekgp49.dbc.servlet.ReviewUpdateServlet;
import ekgp49.dbc.servlet.Servlet;
import ekgp49.sql.SqlSessionFactoryProxy;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();
  ExecutorService executorService = Executors.newCachedThreadPool();

  boolean serverStop = false;

  void service() {
    notifyApplicationInitialized();
    InformationService informationService = (InformationService) context.get("informationService");
    ReviewService reviewService = (ReviewService) context.get("reviewService");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.get("sqlSessionFactory");

    System.out.println("앱 서버입니다");

    servletMap.put("/info/add", new InformationAddServlet(informationService));
    servletMap.put("/info/delete", new InformationDeleteServlet(informationService));
    servletMap.put("/info/list", new InformationListServlet(informationService));
    servletMap.put("/info/update", new InformationUpdateServlet(informationService));
    servletMap.put("/info/search", new InformationSearchServlet(informationService));
    servletMap.put("/info/detail", new InformationDetailServlet(informationService));

    servletMap.put("/review/add", new ReviewAddServlet(reviewService));
    servletMap.put("/review/delete", new ReviewDeleteServlet(reviewService));
    servletMap.put("/review/list", new ReviewListServlet(reviewService));
    servletMap.put("/review/star", new ReviewRateServlet(reviewService));
    servletMap.put("/review/update", new ReviewUpdateServlet(reviewService));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트 연결 대기중...");
      while (true) {
        Socket socket = serverSocket.accept(); // 여기에 try{} 걸면 소켓 닫혀서 오류남
        executorService.submit(() -> {
          processRequest(socket);

          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();
          System.out.println("-------------연결 종료---------------");
        });

        if (serverStop == true) {
          break;
        }
      }


    } catch (Exception e) {
      System.out.println("서버 문제 발생");
      e.printStackTrace();
    }
    executorService.shutdown();
    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    notifyApplicationDestroyed();
    System.out.println("서버 종료");
  }


  public void addListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }


  private void notFount(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다");
  }


  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener a : listeners) {
      a.contextDestroyed(context);
    }
  }


  private void notifyApplicationInitialized() {
    for (ApplicationContextListener a : listeners) {
      a.contextInitialized(context);
    }
  }


  void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      String request = in.nextLine();
      System.out.println(request);
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
      }

      Servlet servlet = servletMap.get(request);
      if (servlet != null) {
        try {
          servlet.service(out, in);
          out.println("!end!");
        } catch (Exception e) {
          out.println("요청처리 중 오류 발생: " + e.getMessage());
          System.out.println("클라이언트 요청 처리 중 오류 발생");
          e.printStackTrace();
        }
      } else {
        notFount(out);
        out.println("!end!");
      }
      out.flush();
      System.out.println("클라이언트에게 응답하였음");
    } catch (Exception e) {
      System.out.println("서버 문제 발생");
      e.printStackTrace();
    }
  }


  private void quit(PrintStream out) throws IOException {
    out.println("OK");
    out.println("!end!");
    serverStop = true;
  }

  public void removeListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  public static void main(String[] args) {
    ServerApp app = new ServerApp();
    app.addListener(new DataLoaderListener());
    app.service();
  }
}

