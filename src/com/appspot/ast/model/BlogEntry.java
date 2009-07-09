package com.appspot.ast.model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.KeyFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class BlogEntry {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private String header;

  @Persistent
  private Text text;

  @Persistent
  private Date datePublished;

  @Persistent
  private Set<Key> blogEntryTags;

  public BlogEntry(String header) {
    this.header = header;
    key = createKey(header);
  }

  public BlogEntry(String header, String text, Date datePublished) {
    this.header = header;
    this.text = new Text(text);
    this.datePublished = datePublished;
    key = createKey(header);
  }

  public static Key createKey(String header) {
    return KeyFactory.createKey(BlogEntry.class.getSimpleName(), "k:" + header);
  }

  public String getKeyName() {
    return key.getName();
  }

  public Key getKey() {
    return key;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Text getText() {
    return text;
  }

  public void setText(String text) {
    this.text = new Text(text);
  }

  public Date getDatePublished() {
    return datePublished;
  }

  public void setDatePublished(Date datePublished) {
    this.datePublished = datePublished;
  }

  public Set<Key> getBlogEntryTags() {
    if (blogEntryTags == null) {
      blogEntryTags = new HashSet<Key>();
    }
    return blogEntryTags;
  }

  public void setBlogEntryTags(Set<Key> blogEntryTags) {
    this.blogEntryTags = blogEntryTags;
  }
}
