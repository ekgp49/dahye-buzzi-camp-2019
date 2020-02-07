package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.domain.Review;
import util.Prompt;

public class ReviewSelectCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public ReviewSelectCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    int star = prompt.inputInt("별점: ");
    try {
      out.writeUTF("/review/select");
      out.writeInt(star);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Review[] arr = (Review[]) in.readObject();
      for (Review r : arr) {
        System.out.println(r.getCafeName());
      }
    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }
}
