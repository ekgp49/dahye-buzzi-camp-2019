package ekgp49.dbc.domain;

public class Search {
  private int no;
  private String cafeArea;
  private String cafeName;
  private String cafeMenu; 
  private int starRate;
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cafeArea == null) ? 0 : cafeArea.hashCode());
    result = prime * result + ((cafeMenu == null) ? 0 : cafeMenu.hashCode());
    result = prime * result + ((cafeName == null) ? 0 : cafeName.hashCode());
    result = prime * result + starRate;
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Search other = (Search) obj;
    if (cafeArea == null) {
      if (other.cafeArea != null)
        return false;
    } else if (!cafeArea.equals(other.cafeArea))
      return false;
    if (cafeMenu == null) {
      if (other.cafeMenu != null)
        return false;
    } else if (!cafeMenu.equals(other.cafeMenu))
      return false;
    if (cafeName == null) {
      if (other.cafeName != null)
        return false;
    } else if (!cafeName.equals(other.cafeName))
      return false;
    if (starRate != other.starRate)
      return false;
    return true;
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getCafeArea() {
    return cafeArea;
  }
  public void setCafeArea(String cafeArea) {
    if (cafeArea != "") {
      this.cafeArea = cafeArea;
    }
  }
  public String getCafeName() {
    return cafeName;
  }
  public void setCafeName(String cafeName) {
    if (cafeName != "") {
      this.cafeName = cafeName;
    }
  }
  public String getCafeMenu() {
    return cafeMenu;
  }
  public void setCafeMenu(String cafeMenu) {
    if (cafeMenu != "") {
      this.cafeMenu = cafeMenu;
    }
  }
  public int getStarRate() {
    return starRate;
  }
  public void setStarRate(int starRate) {
      this.starRate = starRate;
  }
  
}
