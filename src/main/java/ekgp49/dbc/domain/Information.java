package ekgp49.dbc.domain;

import java.io.Serializable;

public class Information implements Serializable {

  private static final long serialVersionUID = 20200131L;

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s", this.getNo(), this.getCafeName(),
        this.getCafeAddress(), this.getCafeCall(), this.getCafeWebSite(), this.getOpenTime(),
        this.getCloseTime(), this.getHolliday(), this.getCafeMenu());
  }

  public static Information valueOf(String csv) {
    String[] data = csv.split(",");
    Information info = new Information();
    info.setNo(Integer.parseInt(data[0]));
    info.setCafeName(data[1]);
    info.setCafeAddress(data[2]);
    info.setCafeCall(data[3]);
    info.setCafeWebSite(data[4]);
    info.setOpenTime(data[5]);
    info.setCloseTime(data[6]);
    info.setHolliday(data[7]);
    info.setCafeMenu(data[8]);
    return info;
  }

  private int no;
  private String cafeName;
  private String cafeAddress;
  private String cafeCall;
  private String cafeWebSite;
  private String openTime;
  private String closeTime;
  private String holliday;
  private String cafeMenu;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (cafeAddress == null ? 0 : cafeAddress.hashCode());
    result = prime * result + (cafeCall == null ? 0 : cafeCall.hashCode());
    result = prime * result + (cafeMenu == null ? 0 : cafeMenu.hashCode());
    result = prime * result + (cafeName == null ? 0 : cafeName.hashCode());
    result = prime * result + (cafeWebSite == null ? 0 : cafeWebSite.hashCode());
    result = prime * result + (closeTime == null ? 0 : closeTime.hashCode());
    result = prime * result + (holliday == null ? 0 : holliday.hashCode());
    result = prime * result + no;
    result = prime * result + (openTime == null ? 0 : openTime.hashCode());
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
    Information other = (Information) obj;
    if (cafeAddress == null) {
      if (other.cafeAddress != null)
        return false;
    } else if (!cafeAddress.equals(other.cafeAddress))
      return false;
    if (cafeCall == null) {
      if (other.cafeCall != null)
        return false;
    } else if (!cafeCall.equals(other.cafeCall))
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
    if (cafeWebSite == null) {
      if (other.cafeWebSite != null)
        return false;
    } else if (!cafeWebSite.equals(other.cafeWebSite))
      return false;
    if (closeTime == null) {
      if (other.closeTime != null)
        return false;
    } else if (!closeTime.equals(other.closeTime))
      return false;
    if (holliday == null) {
      if (other.holliday != null)
        return false;
    } else if (!holliday.equals(other.holliday))
      return false;
    if (no != other.no)
      return false;
    if (openTime == null) {
      if (other.openTime != null)
        return false;
    } else if (!openTime.equals(other.openTime))
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCafeName() {
    return cafeName;
  }

  public void setCafeName(String cafeName) {
    this.cafeName = cafeName;
  }

  public String getCafeAddress() {
    return cafeAddress;
  }

  public void setCafeAddress(String cafeAddress) {
    this.cafeAddress = cafeAddress;
  }

  public String getCafeCall() {
    return cafeCall;
  }

  public void setCafeCall(String cafeCall) {
    this.cafeCall = cafeCall;
  }

  public String getCafeWebSite() {
    return cafeWebSite;
  }

  public void setCafeWebSite(String cafeWebSite) {
    this.cafeWebSite = cafeWebSite;
  }

  public String getOpenTime() {
    return openTime;
  }

  public void setOpenTime(String openTime) {
    this.openTime = openTime;
  }

  public String getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(String closeTime) {
    this.closeTime = closeTime;
  }

  public String getHolliday() {
    return holliday;
  }

  public void setHolliday(String holliday) {
    this.holliday = holliday;
  }

  public String getCafeMenu() {
    return cafeMenu;
  }

  public void setCafeMenu(String cafeMenu) {
    this.cafeMenu = cafeMenu;
  }

}
