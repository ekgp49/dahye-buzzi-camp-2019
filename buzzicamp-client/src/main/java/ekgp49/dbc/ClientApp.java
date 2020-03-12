package ekgp49.dbc;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import util.Prompt;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String command;

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
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

      if (command.endsWith("/server/stop")) {
        processCommand();
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand();
    }
    keyboard.close();

  }

  // bitcamp://localhost:9999/review/list
  private void processCommand() {
    String host = null;
    int port = 9999;
    String servletPath = null;
    if (!command.startsWith("bitcamp://")) {
      System.out.println("유효한 커맨드가 아닙니다.");
      return;
    }
    String url = command.substring(10);
    int index = url.indexOf("/");
    String[] str = url.substring(0, index).split(":");
    host = str[0];
    if (str.length == 2) {
      port = Integer.parseInt(str[1]);
    }
    servletPath = url.substring(index);

    try (Socket socket = new Socket(host, port);
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {
      out.println(servletPath);
      while (true) {
        String response = in.nextLine();

        if (response.equals("!end!")) {
          break;
        } else if (response.equals("!{}!")) {
          out.println(prompt.inputString(""));
          out.flush();
        } else {
          System.out.println(response);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
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

  public static void main(String[] args) throws Exception {
    ClientApp app = new ClientApp();
    app.service();
  }

}
