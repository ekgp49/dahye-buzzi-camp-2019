package ekgp49.dbc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import ekgp49.dbc.context.ApplicationContextListener;
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
  Scanner keyboard = new Scanner(System.in);
  Deque<String> commandStack = new ArrayDeque<>();
  Queue<String> commandQueue = new LinkedList<>();

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

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


  @SuppressWarnings("unchecked")
  public void service() {
    notifyApplicationInitialized();

    Prompt prompt = new Prompt(keyboard);

    List<Information> informationList = (List<Information>) context.get("informationList");
    List<Review> reviewList = (List<Review>) context.get("reviewList");

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
        try {
          commandHandler.get(command).execute();
        } catch (Exception e) {
          System.out.printf("명령어 실행 중 오류 발생: %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }

    notifyApplicationDestroyed();
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
    App app = new App();
    app.addListener(new DataLoaderListner());
    app.addListener(new GreetingListener());

    app.service();

  }

}
