package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import util.Prompt;

public class InformationDeleteCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public InformationDeleteCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("가게 정보 번호: ");
    try {
      out.writeUTF("/info/delete");
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
