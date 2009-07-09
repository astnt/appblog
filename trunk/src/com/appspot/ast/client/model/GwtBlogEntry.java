package com.appspot.ast.client.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 23.05.2009
 * Time: 19:31:40
 */
public class GwtBlogEntry extends BaseGwtObject {

  private String header;
  private String body;
  private Date datePublished;
  private List<GwtTag> blogRecordTags;

  public GwtBlogEntry() {
  }

  @Override
  public void setName(String name) {
    header = name;
  }

  @Override
  public String getName() {
    return header;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getDatePublished() {
    return datePublished;
  }

  public void setDatePublished(Date datePublished) {
    this.datePublished = datePublished;
  }

  public List<GwtTag> getBlogRecordTags() {
    if (blogRecordTags == null) {
      blogRecordTags = new ArrayList<GwtTag>();
    }
    return blogRecordTags;
  }

  public void setBlogEntryTags(List<GwtTag> blogRecordTags) {
    this.blogRecordTags = blogRecordTags;
  }
}
