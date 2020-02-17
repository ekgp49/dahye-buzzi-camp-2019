package ekgp49.dbc.handler;

import ekgp49.dbc.dao.InformationDao;
import util.Prompt;

public class InformationDeleteCommand implements Command {
  Prompt prompt;
  InformationDao infoDao;

  public InformationDeleteCommand(Prompt prompt, InformationDao infoDao) {
    this.prompt = prompt;
    this.infoDao = infoDao;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("가게 정보 번호: ");
    try {
      if (infoDao.delete(no) > 0) {
        System.out.println("삭제했습니다.");
      } else {
        System.out.println("해당하는 번호의 정보가 없습니다.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
