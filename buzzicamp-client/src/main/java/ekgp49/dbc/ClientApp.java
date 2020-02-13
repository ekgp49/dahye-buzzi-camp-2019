package ekgp49.dbc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.dao.ReviewDao;
import ekgp49.dbc.dao.proxy.InformationDaoProxy;
import ekgp49.dbc.dao.proxy.ReviewDaoProxy;
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
  String command;
  HashMap<String, Command> commandMap;
  InformationDao infoDao;
  ReviewDao reviewDao;

  public ClientApp() {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
    commandMap = new HashMap<>();

    infoDao = new InformationDaoProxy("localhost", 9999);
    reviewDao = new ReviewDaoProxy("localhost", 9999);

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
    commandMap.put("/server/stop", () -> {
      try {
        try (Socket socket = new Socket("localhost", 9999);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
          out.writeUTF(command);
          out.flush();
          System.out.println("종료합니다.");
        }
      } catch (Exception e) {
        //
      }
    });
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

  public static void main(String[] args) {
    ClientApp app = new ClientApp();
    app.service();

  }

}
