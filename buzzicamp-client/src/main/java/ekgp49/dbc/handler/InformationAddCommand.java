package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationAddCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public InformationAddCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      out.writeUTF("/info/add");
      out.flush();
      Information information = new Information();
      int no = in.readInt();
      information.setNo(no);
      information.setCafeName(prompt.inputString("카페 상호: "));
      information.setCafeAddress(prompt.inputString("주소: "));
      information.setCafeCall(prompt.inputString("연락처: "));
      information.setCafeWebSite(prompt.inputString("웹사이트: "));
      information.setOpenTime(prompt.inputString("오픈시간: "));
      information.setCloseTime(prompt.inputString("종료시간: "));
      information.setHolliday(prompt.inputString("정기 휴일: "));
      information.setCafeMenu(prompt.inputString("메뉴: "));

      out.writeObject(information);
      if (in.readUTF().equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println(in.readUTF());

    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }
}
