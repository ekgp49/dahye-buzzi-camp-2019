package ekgp49.dbc.domain;

import java.sql.Date;

public class Review {

  public String toCsvString() {
    return String.format("%d,%s,%s,%d,%s,%s,%s,%s", this.getNo(), this.getCafeName(),
        this.getCustomer(), this.getStarRate(), this.getContent(), this.getViewCount(),
        this.getCreatedDate(), this.getTimeFormFromToday());
  }

  public static Review valueOf(String csv) {
    String[] data = csv.split(",");
    Review review = new Review();
    review.setNo(Integer.parseInt(data[0]));
    review.setCafeName(data[1]);
    review.setCustomer(data[2]);
    review.setStarRate(Integer.parseInt(data[3]));
    review.setContent(data[4]);
    review.setViewCount(Integer.parseInt(data[5]));
    review.setCreatedDate(Date.valueOf(data[6]));
    review.setTimeFormFromToday(data[7]);
    return review;
  }

  private int no;
  private String cafeName;
  private String customer;
  private int starRate;
  private String content;
  private int viewCount;
  private Date createdDate;
  private String timeFormFromToday;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (cafeName == null ? 0 : cafeName.hashCode());
    result = prime * result + (content == null ? 0 : content.hashCode());
    result = prime * result + (createdDate == null ? 0 : createdDate.hashCode());
    result = prime * result + (customer == null ? 0 : customer.hashCode());
    result = prime * result + no;
    result = prime * result + starRate;
    result = prime * result + (timeFormFromToday == null ? 0 : timeFormFromToday.hashCode());
    result = prime * result + viewCount;
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
    Review other = (Review) obj;
    if (cafeName == null) {
      if (other.cafeName != null)
        return false;
    } else if (!cafeName.equals(other.cafeName))
      return false;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (createdDate == null) {
      if (other.createdDate != null)
        return false;
    } else if (!createdDate.equals(other.createdDate))
      return false;
    if (customer == null) {
      if (other.customer != null)
        return false;
    } else if (!customer.equals(other.customer))
      return false;
    if (no != other.no)
      return false;
    if (starRate != other.starRate)
      return false;
    if (timeFormFromToday == null) {
      if (other.timeFormFromToday != null)
        return false;
    } else if (!timeFormFromToday.equals(other.timeFormFromToday))
      return false;
    if (viewCount != other.viewCount)
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
