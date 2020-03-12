package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import ekgp49.sql.PlatformTransactionManager;
import ekgp49.sql.TransactionTemplate;
import ekgp49.util.Prompt;

public class InformationUpdateServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;
  PlatformTransactionManager txManager;
  TransactionTemplate transactionTemplate;

  public InformationUpdateServlet(InformationDao infoDao, InfoMenuDao infoMenuDao,
      PlatformTransactionManager txManager) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
    this.txManager = txManager;
    transactionTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "번호? ");

    transactionTemplate.execute(() -> {
      Information info = infoDao.findByNo(no);
      if (info == null) {
        throw new Exception("해당번호의 회원정보이 없습니다.");
      }
      int command = Prompt.getInputInt(in, out, "수정할 영역을 입력하세요."//
          + "\n1 : 상호, 2: 주소, 3: 연락처, 4: 웹사이트, 5: 오픈시간\n"
          + "6 : 종료시간, 7: 정기 휴일, 8: 메뉴, 9: 전체 \n입력 >>");

      switch (command) {
        case 1:
          info.setCafeName(Prompt.getInputString(in, out, "상호: "));
          break;
        case 2:
          info.setCafeAddress(Prompt.getInputString(in, out, "주소: "));
          break;
        case 3:
          info.setCafeCall(Prompt.getInputString(in, out, "연락처: \\n!{}!: "));
          break;
        case 4:
          info.setCafeWebSite(Prompt.getInputString(in, out, "웹사이트: "));
          break;
        case 5:
          info.setOpenTime(Prompt.getInputString(in, out, "오픈시간: "));
          break;
        case 6:
          info.setCloseTime(Prompt.getInputString(in, out, "종료시간: "));
          break;
        case 7:
          info.setHolliday(Prompt.getInputString(in, out, "정기 휴일: "));
          break;
        case 8:
          out.println("메뉴: ");
          changeMenu(out, in, info);
          break;
        case 9:
          info.setCafeName(Prompt.getInputString(in, out, "상호: "));
          info.setCafeAddress(Prompt.getInputString(in, out, "주소: "));
          info.setCafeCall(Prompt.getInputString(in, out, "연락처: "));
          info.setCafeWebSite(Prompt.getInputString(in, out, "웹사이트: "));
          info.setOpenTime(Prompt.getInputString(in, out, "오픈시간: "));
          info.setCloseTime(Prompt.getInputString(in, out, "클로즈시간: "));
          info.setHolliday(Prompt.getInputString(in, out, "정기 휴일: "));
          out.println("메뉴: ");
          changeMenu(out, in, info);
          break;
        default:
          out.println("유효한 입력이 아닙니다.");
      }

      if (infoDao.update(info) > 0) {
        out.println("정보를 수정하였습니다.");
      } else {
        throw new Exception("정보 수정 실패");
      }
      return null;
    });

  }

  private void changeMenu(PrintStream out, Scanner in, Information info) throws Exception {
    transactionTemplate.execute(() -> {
      infoMenuDao.deleteAll(info.getNo());
      List<InfoMenu> menuList = new ArrayList<>();
      while (true) {
        InfoMenu menu = new InfoMenu();
        String name = Prompt.getInputString(in, out, "> ");
        if (name.length() == 0) {
          break;
        }
        menu.setName(name);
        menu.setInformationNo(info.getNo());
        menuList.add(menu);
      }
      info.setMenuList(menuList);
      infoMenuDao.insert(info);
      return null;
    });
  }
}
