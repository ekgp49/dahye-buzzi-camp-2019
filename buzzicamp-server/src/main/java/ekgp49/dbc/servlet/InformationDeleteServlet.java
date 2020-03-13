package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.service.InformationService;
import ekgp49.util.Prompt;

public class InformationDeleteServlet implements Servlet {
  InformationService informationService;

  public InformationDeleteServlet(InformationService informationService) {
    this.informationService = informationService;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "정보 번호? ");

    informationService.delete(no);
    out.println("삭제했습니다.");
  }
}
