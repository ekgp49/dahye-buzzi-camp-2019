package ekgp49.dbc.domain;

import java.sql.Date;

public class Review {
  private int no;
  private String cafeName;
  private String customer;
  private int starRate;
  private String content;
  private int viewCount;
  private Date createdDate; 
  private String timeFormFromToday;

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
  public String getCustomer() {
    return customer;
  }
  public void setCustomer(String customer) {
    this.customer = customer;
  }
  public int getStarRate() {
    return starRate;
  }
  public void setStarRate(int starRate) {
    this.starRate = starRate;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public String getTimeFormFromToday() {
    return timeFormFromToday;
  }
  public void setTimeFormFromToday(String timeFormFromToday) {
    this.timeFormFromToday = timeFormFromToday;
  }
}
