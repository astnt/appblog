package com.appspot.ast.model;

import javax.jdo.annotations.*;

import java.util.HashSet;
import java.util.Set;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 23.05.2009
 * Time: 14:13:49
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class BlogTag {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private String header;

  @Persistent
  private Set<Key> tagBlogEntries;

  public BlogTag(String header) {
    this.header = header;
    key = createKey(header);
  }

  public static Key createKey(String header) {
    return KeyFactory.createKey(BlogTag.class.getSimpleName(), "t:" + header);
  }

  public Key getKey() {
    return key;
  }

  public String getKeyName() {
    return key.getName();
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Set<Key> getTagBlogEntries() {
	if (tagBlogEntries == null) { tagBlogEntries = new HashSet<Key>(); }
    return tagBlogEntries;
  }

  public void setTagBlogEntries(Set<Key> tagBlogRecords) {
    this.tagBlogEntries = tagBlogRecords;
  }
}
