package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationDeleteCommand implements Command {
  Prompt prompt;
  List<Information> informationList;

  public InformationDeleteCommand(Prompt prompt, List<Information> list) {
    this.prompt = prompt;
    this.informationList = list;
  }

  @Override
  public void execute() {
    int index = indexOfInfo(prompt.inputInt("가게 정보 번호: "));
    if (index == -1) {
      System.out.println("해당 번호의 정보가 없습니다.");
      return;
    }

    this.informationList.remove(index);
    System.out.println("정보를 삭제했습니다.");
  }

  private int indexOfInfo(int no) {
    for (int i = 0; i < this.informationList.size(); i++) {
      if (this.informationList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
