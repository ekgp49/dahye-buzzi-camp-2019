package ekgp49.dbc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Review;
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

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);

    List<Information> informationList = new LinkedList<>();
    List<Review> reviewList = new LinkedList<>();

    HashMap<String, Command> commandHandler = new HashMap<>();

    commandHandler.put("/search", new SearchCommand(prompt, informationList));
    commandHandler.put("/info/add", new InformationAddCommand(prompt, informationList));
    commandHandler.put("/info/list", new InformationListCommand(informationList));
    commandHandler.put("/info/update", new InformationUpdateCommand(prompt, informationList));
    commandHandler.put("/info/delete", new InformationDeleteCommand(prompt, informationList));
    commandHandler.put("/review/add", new ReviewAddCommand(prompt, reviewList));
    commandHandler.put("/review/list", new ReviewListCommand(reviewList));
    commandHandler.put("/review/update", new ReviewUpdateCommand(prompt, reviewList));
    commandHandler.put("/review/delete", new ReviewDeleteCommand(prompt, reviewList));
    commandHandler.put("/review/star", new ReviewSelectCommand(prompt, reviewList));

    String command;
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
      } else if (command.equalsIgnoreCase("quit")) {
        System.out.println("종료합니다.");
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      if (commandHandler.get(command) != null) {
        commandHandler.get(command).execute();
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
  }

  private static void printCommandHistory(Iterator<String> iterator) {
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
}
