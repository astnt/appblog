package com.appspot.ast.server;

import com.appspot.ast.client.MainService;
import com.appspot.ast.client.model.GwtTag;
import com.appspot.ast.client.model.GwtBlogEntry;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.model.PMF;
import com.appspot.ast.model.BlogTag;
import com.appspot.ast.model.BlogEntry;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import javax.jdo.PersistenceManager;
import java.util.List;
import java.util.Set;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements
    MainService {

  public BaseReferenceData mainServer(String input) {
    BaseReferenceData data = new BaseReferenceData();

    PersistenceManager pm = PMF.get().getPersistenceManager();

    List<BlogTag> blogTags = (List<BlogTag>) pm.newQuery("select from " + BlogTag.class.getName()).execute();
    if (blogTags != null) { data.setTags(map(blogTags, new GwtTag[blogTags.size()])); }

    List<BlogEntry> blogEntries = (List<BlogEntry>) pm.newQuery("select from " + BlogEntry.class.getName()).execute();
//    pm.deletePersistentAll(blogEntries);
//    pm.deletePersistentAll(blogTags);

    if (blogEntries != null) { data.setRecords(map(blogEntries, new GwtBlogEntry[blogEntries.size()])); }
    pm.close();

    return data;
  }

  public String typograf(String input) {
    return "error in typograf";
  }

  @Override
  public String addTag(String text) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    BlogTag blogTag = new BlogTag(text);
    pm.makePersistentAll(blogTag);
    return blogTag.getHeader();
  }

  @Override
  public BaseReferenceData newBlogRecord(GwtBlogEntry gwtBlogEntry) {
    BaseReferenceData data = new BaseReferenceData();

    PersistenceManager pm = PMF.get().getPersistenceManager();
    BlogEntry blogEntry = new BlogEntry(gwtBlogEntry.getHeader());
    blogEntry.setDatePublished(gwtBlogEntry.getDatePublished());
    blogEntry.setText(gwtBlogEntry.getBody());

    Set<Key> tags = blogEntry.getBlogEntryTags();

    List<BlogTag> blogTags = (List<BlogTag>) pm.newQuery("select from " + BlogTag.class.getName()).execute();

    for (GwtTag gwtTag : gwtBlogEntry.getBlogRecordTags()) {
      BlogTag tag = createOrGet(blogTags, gwtTag.getHeader());
      if (tag != null && tag.getKey() != null) {
	      tags.add(tag.getKey());
	      tag.getTagBlogEntries().add(blogEntry.getKey());
	      data.getMessages().add("tag named " + tag.getHeader() + " added");
      } else {
    	  data.getMessages().add("tag named " + tag + " is null, or header is null");
      }
    }
    blogEntry.setBlogEntryTags(tags);

    try {
      pm.makePersistent(blogEntry);
      data.getMessages().add("blog entry saved: " + blogEntry.getHeader());
    } finally {
      pm.close();
    }
    return data;
  }

  private BlogTag createOrGet(List<BlogTag> blogTags, String header) {
    for (BlogTag blogTag : blogTags) {
      if (blogTag.getHeader().equals(header)) {
        return blogTag;
      }
    }
    return new BlogTag(header); // TODO наверно нужно сохранить в pm
  }

  private GwtBlogEntry[] map(List<BlogEntry> blogEntries, GwtBlogEntry[] gwtTags) {
    for (int i = 0; i < gwtTags.length; i++) {
      GwtBlogEntry entry = new GwtBlogEntry();
      BlogEntry blogEntry = blogEntries.get(i);
      entry.setKey(blogEntry.getKeyName());
      entry.setDatePublished(blogEntry.getDatePublished());
      entry.setHeader(blogEntry.getHeader());
      entry.setBody(String.valueOf(blogEntry.getText()));
      gwtTags[i] = entry;
    }
    return gwtTags;
  }

  private GwtTag[] map(List<BlogTag> blogTags, GwtTag[] gwtTags) {
    for (int i = 0; i < gwtTags.length; i++) {
      GwtTag tag = new GwtTag();
      tag.setKey(blogTags.get(i).getKeyName());
      tag.setHeader(blogTags.get(i).getHeader());
      gwtTags[i] = tag;
    }
    return gwtTags;
  }
}
