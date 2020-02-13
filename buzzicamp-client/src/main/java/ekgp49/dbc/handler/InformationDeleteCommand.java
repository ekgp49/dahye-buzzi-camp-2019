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
      infoDao.delete(no);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
