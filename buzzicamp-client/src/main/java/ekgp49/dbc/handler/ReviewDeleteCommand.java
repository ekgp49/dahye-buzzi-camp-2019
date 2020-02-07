package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import util.Prompt;

public class ReviewDeleteCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewDeleteCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("리뷰 번호는 ");
    try {
      out.writeUTF("/review/delete");
      out.writeInt(no);
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println(in.readUTF());
    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }
}
