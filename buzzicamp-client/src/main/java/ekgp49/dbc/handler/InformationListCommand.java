package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import ekgp49.dbc.domain.Information;

public class InformationListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public InformationListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/info/list");
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      List<Information> infos = (List<Information>) in.readObject();
      for (Information info : infos) {
        System.out.printf("%d, %s, %s, %s, %s, %s ~ %s, %s, %s\n", info.getNo(), info.getCafeName(),
            info.getCafeAddress(), info.getCafeCall(), info.getCafeWebSite(), info.getOpenTime(),
            info.getCloseTime(), info.getHolliday(), info.getCafeMenu());
      }
    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }
  }

}
