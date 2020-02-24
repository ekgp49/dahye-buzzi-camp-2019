package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;

public class InformationUpdateServlet implements Servlet {
  InformationDao infoDao;

  public InformationUpdateServlet(InformationDao infoDao) {
    this.infoDao = infoDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    out.println("번호? \n!{}!");
    int no = Integer.parseInt(in.nextLine());
    Information info = infoDao.findByNo(no);
    if (info == null) {
      out.println("해당번호의 회원정보이 없습니다.");
      return;
    }
    out.println("수정할 영역을 입력하세요.\n1 : 상호, 2: 주소, 3: 연락처, 4: 웹사이트, 5: 오픈시간\n"
        + "6 : 종료시간, 7: 정기 휴일, 8: 메뉴, 9: 전체 \n입력 >>");
    out.println("!{}!");
    out.flush();
    int command = Integer.parseInt(in.nextLine());
    switch (command) {
      case 1:
        out.println("상호: \n!{}!");
        info.setCafeName(in.nextLine());
        break;
      case 2:
        out.println("주소: \n!{}!");
        info.setCafeAddress(in.nextLine());
        break;
      case 3:
        out.println("연락처: \n!{}!");
        info.setCafeCall(in.nextLine());
        break;
      case 4:
        out.println("웹사이트: \n!{}!");
        info.setCafeWebSite(in.nextLine());
        break;
      case 5:
        out.println("오픈시간: \n!{}!");
        info.setOpenTime(in.nextLine());
        break;
      case 6:
        out.println("종료시간: \n!{}!");
        info.setCloseTime(in.nextLine());
        break;
      case 7:
        out.println("정기 휴일: \n!{}!");
        info.setHolliday(in.nextLine());
        break;
      case 8:
        out.println("메뉴: \n!{}!");
        info.setCafeMenu(in.nextLine());
        break;
      case 9:
        out.println("상호: \n!{}!");
        info.setCafeName(in.nextLine());
        out.println("주소: \n!{}!");
        info.setCafeAddress(in.nextLine());
        out.println("연락처: \n!{}!");
        info.setCafeCall(in.nextLine());
        out.println("웹사이트: \n!{}!");
        info.setCafeWebSite(in.nextLine());
        out.println("오픈시간: \n!{}!");
        info.setOpenTime(in.nextLine());
        out.println("종료시간: \n!{}!");
        info.setCloseTime(in.nextLine());
        out.println("정기 휴일: \n!{}!");
        info.setHolliday(in.nextLine());
        out.println("메뉴: \n!{}!");
        info.setCafeMenu(in.nextLine());
        break;
      default:
        out.println("유효한 입력이 아닙니다.");
    }

    if (infoDao.update(info) > 0) {
      out.println("정보를 수정하였습니다.");
    } else {
      out.println("정보 수정 실패");
    }
  }
}
