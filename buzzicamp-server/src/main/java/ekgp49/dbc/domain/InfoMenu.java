package ekgp49.dbc.domain;

import java.io.Serializable;

public class InfoMenu implements Serializable {

  private static final long serialVersionUID = 1L;

  int no;
  String name;
  int informationNo;

  @Override
  public String toString() {
    return "Menu [no=" + no + ", name=" + name + ", informationNo=" + informationNo + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getInformationNo() {
    return informationNo;
  }

  public void setInformationNo(int informationNo) {
    this.informationNo = informationNo;
  }

}
