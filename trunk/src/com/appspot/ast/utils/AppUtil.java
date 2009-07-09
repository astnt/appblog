package com.appspot.ast.utils;

import com.appspot.ast.client.model.GwtBlogEntry;
import com.appspot.ast.client.model.BaseGwtObject;
import com.appspot.ast.client.model.GwtTag;
import com.appspot.ast.model.BlogEntry;
import com.google.appengine.api.datastore.Key;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class AppUtil {
  public static boolean isAdmin(String userName) {
    return "anton.starcev".equals(userName);
  }

  public static BaseGwtObject map(GwtBlogEntry gwtBlogEntry, BlogEntry blogEntry) {
    gwtBlogEntry.setDatePublished(blogEntry.getDatePublished());
    gwtBlogEntry.setHeader(blogEntry.getHeader());
    if (blogEntry.getText() != null) {
      gwtBlogEntry.setBody(blogEntry.getText().getValue());
    } else {
      gwtBlogEntry.setBody("Text is: " + blogEntry.getText());
    }
    gwtBlogEntry.setKey(blogEntry.getKeyName());
    gwtBlogEntry.setBlogEntryTags(map(new ArrayList<GwtTag>(), blogEntry.getBlogEntryTags()));
    return gwtBlogEntry;
  }

  private static List<GwtTag> map(ArrayList<GwtTag> gwtBlogEntry, Set<Key> blogEntryTags) {
    for (Key blogEntryTag : blogEntryTags) {
      GwtTag gwtTag = new GwtTag();
      gwtTag.setKey(blogEntryTag.getName());
      gwtBlogEntry.add(gwtTag);
    }
    return gwtBlogEntry;
  }
}
