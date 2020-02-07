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


  public void service() {

    System.out.println("앱 클라이언트입니다");

    try (Socket socket = new Socket("localhost", 9999);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      processCommand(out, in);

      System.out.println("서버연결종료");
    } catch (Exception e) {
      System.out.println("서버 연결 중 오류가 발생했습니다");
      e.printStackTrace();
    }
    keyboard.close();
  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/search", new SearchCommand(prompt, out, in));
    commandMap.put("/info/add", new InformationAddCommand(prompt, out, in));
    commandMap.put("/info/list", new InformationListCommand(out, in));
    commandMap.put("/info/update", new InformationUpdateCommand(prompt, out, in));
    commandMap.put("/info/delete", new InformationDeleteCommand(prompt, out, in));
    commandMap.put("/review/add", new ReviewAddCommand(prompt, out, in));
    commandMap.put("/review/list", new ReviewListCommand(out, in));
    commandMap.put("/review/update", new ReviewUpdateCommand(prompt, out, in));
    commandMap.put("/review/delete", new ReviewDeleteCommand(prompt, out, in));
    commandMap.put("/review/star", new ReviewSelectCommand(prompt, out, in));

    String command;
    try {
      while (true) {
        command = prompt.inputString("\n명령> ");
        if (command.length() == 0) {
          continue;
        } else if (command.equals("history")) {
          printCommandHistory(commandStack.iterator());
          continue;
        } else if (command.equals("history2")) {
          printCommandHistory(commandQueue.iterator());
          continue;
        } else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("종료합니다.");
          break;
        }

        commandStack.push(command);
        commandQueue.offer(command);

        Command commandHandler = commandMap.get(command);
        if (commandHandler != null) {
          try {
            commandHandler.execute();
          } catch (Exception e) {
            System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
          }
        } else {
          System.out.println("실행할 수 없는 명령입니다.");
        }
      }
    } catch (Exception e) {
      System.out.println("예외발생");
    }
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
