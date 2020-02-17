package ekgp49.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.dao.mariadb.InformationDaoImpl;
import ekgp49.dbc.dao.mariadb.ReviewDaoImpl;
import ekgp49.dbc.handler.Command;
import ekgp49.dbc.handler.InformationAddCommand;
import ekgp49.dbc.handler.InformationDeleteCommand;
import ekgp49.dbc.handler.InformationListCommand;
import ekgp49.dbc.handler.InformationUpdateCommand;
import ekgp49.dbc.handler.ReviewAddCommand;
import ekgp49.dbc.handler.ReviewDeleteCommand;
import ekgp49.dbc.handler.ReviewListCommand;
import ekgp49.dbc.handler.ReviewSelectCommand;
import ekgp49.dbc.handler.ReviewUpdateCommand;
import ekgp49.dbc.handler.SearchCommand;
import util.Prompt;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  HashMap<String, Command> commandMap;

  Connection con;
  InformationDao infoDao;
  ReviewDao reviewDao;

  String command;

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
    commandMap = new HashMap<>();

    Class.forName("org.mariadb.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/myproject", "buzzi", "1111");

    // DaoProxyHelper daoProxyHelper = new DaoProxyHelper("localhost", 9999);
    // infoDao = new InformationDaoProxy(daoProxyHelper);
    // reviewDao = new ReviewDaoProxy(daoProxyHelper);
    infoDao = new InformationDaoImpl(con);
    reviewDao = new ReviewDaoImpl(con);

    commandMap.put("/search", new SearchCommand(prompt, infoDao));
    commandMap.put("/info/add", new InformationAddCommand(prompt, infoDao));
    commandMap.put("/info/list", new InformationListCommand(infoDao));
    commandMap.put("/info/update", new InformationUpdateCommand(prompt, infoDao));
    commandMap.put("/info/delete", new InformationDeleteCommand(prompt, infoDao));
    commandMap.put("/review/add", new ReviewAddCommand(prompt, reviewDao));
    commandMap.put("/review/list", new ReviewListCommand(reviewDao));
    commandMap.put("/review/update", new ReviewUpdateCommand(prompt, reviewDao));
    commandMap.put("/review/delete", new ReviewDeleteCommand(prompt, reviewDao));
    commandMap.put("/review/star", new ReviewSelectCommand(prompt, reviewDao));
  }

  public void service() {
    System.out.println("앱 클라이언트입니다");

    while (true) {
      command = prompt.inputString("\n명령> ");
      if (command.length() == 0) {
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        break;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        break;
      } else if (command.equalsIgnoreCase("quit")) {
        System.out.println("종료합니다");
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand();
    }
    keyboard.close();

    try {
      con.close();
    } catch (Exception e) {
    }
  }

  private void processCommand() {
    Command commandHandler = commandMap.get(command);
    if (commandHandler != null) {
      commandHandler.execute();
    } else {
      System.out.println("실행할 수 없는 명령입니다.");
      return;
    }
    System.out.println("서버연결종료");
  }


  private void printCommandHistory(Iterator<String> iterator) {
    Iterator<String> commands = iterator;
    System.out.println("명령 목록 출력!");
    int count = 0;
    while (commands.hasNext()) {
      System.out.println(commands.next());
      if (++count % 5 == 0 && commands.hasNext()) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    ClientApp app = new ClientApp();
    app.service();
  }

}
