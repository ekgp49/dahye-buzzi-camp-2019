package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationUpdateCommand implements Command {
  Prompt prompt;
  List<Information> informationList;
  private int no = 1;

  public InformationUpdateCommand(Prompt prompt, List<Information> list) {
    this.prompt = prompt;
    this.informationList = list;
  }

  @Override
  public void execute() {
    int index = indexOfInfo(prompt.inputInt("가게 정보 번호: "));

    for (int i = 0; i < this.informationList.size(); i++) {
      if (this.informationList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      System.out.println("해당 번호의 정보가 없습니다.");
      return;
    }

    Information old = informationList.get(index);

    int command = prompt.inputInt("수정할 영역을 입력하세요.\n" + "1 : 상호, 2: 주소, 3: 연락처, 4: 웹사이트, 5: 오픈시간\n"
        + "6 : 종료시간, 7: 정기 휴일, 8: 메뉴, 9: 전체\n" + "입력 >> ");

    switch (command) {
      case 1:
        old.setCafeName(prompt.inputString("카페 상호: "));
        System.out.println("수정했습니다.");
        break;
      case 2:
        old.setCafeAddress(prompt.inputString("주소: "));
        System.out.println("수정했습니다.");
        break;
      case 3:
        old.setCafeCall(prompt.inputString("연락처: "));
        System.out.println("수정했습니다.");
        break;
      case 4:
        old.setCafeWebSite(prompt.inputString("웹사이트: "));
        System.out.println("수정했습니다.");
        break;
      case 5:
        old.setOpenTime(prompt.inputString("오픈시간: "));
        System.out.println("수정했습니다.");
        break;
      case 6:
        old.setCloseTime(prompt.inputString("종료시간: "));
        System.out.println("수정했습니다.");
        break;
      case 7:
        old.setHolliday(prompt.inputString("정기 휴일: "));
        System.out.println("수정했습니다.");
        break;
      case 8:
        old.setCafeMenu(prompt.inputString("메뉴: "));
        System.out.println("수정했습니다.");
        break;
      case 9:
        old.setCafeName(prompt.inputString("카페 상호: "));
        old.setCafeAddress(prompt.inputString("주소: "));
        old.setCafeCall(prompt.inputString("연락처: "));
        old.setCafeWebSite(prompt.inputString("웹사이트: "));
        old.setOpenTime(prompt.inputString("오픈시간: "));
        old.setCloseTime(prompt.inputString("종료시간: "));
        old.setHolliday(prompt.inputString("정기 휴일: "));
        old.setCafeMenu(prompt.inputString("메뉴: "));
        System.out.println("수정했습니다.");
        break;
      default:
        System.out.println("유효한 입력이 아닙니다.");
    }

    this.informationList.set(index, old);
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
