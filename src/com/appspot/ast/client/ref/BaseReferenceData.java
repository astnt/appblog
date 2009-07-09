package com.appspot.ast.client.ref;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.appspot.ast.client.model.GwtTag;
import com.appspot.ast.client.model.GwtBlogEntry;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 23.05.2009
 * Time: 17:58:24
 */
public class BaseReferenceData implements IsSerializable {
  private GwtTag[] tags;
  private GwtBlogEntry[] entries;
  private List<String> messages;

  public void setTags(GwtTag[] tags) {
    this.tags = tags;
  }

  public GwtTag[] getTags() {
    return tags;
  }

  public void setRecords(GwtBlogEntry[] entries) {
    this.entries = entries;
  }

  public GwtBlogEntry[] getRecords() {
    return entries;
  }

  public void addMessage(String msg) {
    if (messages == null) { messages = new ArrayList<String>(); }
    messages.add(msg);
  }

  public List<String> getMessages() {
    if (messages == null) { messages = new ArrayList<String>(); }
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }
}
