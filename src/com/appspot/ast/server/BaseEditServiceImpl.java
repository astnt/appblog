package com.appspot.ast.server;

import com.appspot.ast.client.form.BaseEditService;
import com.appspot.ast.client.model.BaseGwtObject;
import com.appspot.ast.client.model.GwtBlogEntry;
import com.appspot.ast.client.model.GwtTag;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.model.BlogEntry;
import com.appspot.ast.model.BlogTag;
import com.appspot.ast.model.PMF;
import com.appspot.ast.utils.AppUtil;
import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.jdo.PersistenceManager;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 05.07.2009
 * Time: 15:09:00
 */
public class BaseEditServiceImpl extends RemoteServiceServlet implements BaseEditService {
  private static final Logger log = Logger.getLogger(BaseEditServiceImpl.class.getName());

  @Override
  public BaseReferenceData save(BaseGwtObject subject) {
    BaseReferenceData data = new BaseReferenceData();
    data.addMessage("save started...");
    GwtBlogEntry gwtBlogEntry = (GwtBlogEntry) subject;

    PersistenceManager pm = PMF.get().getPersistenceManager();
    BlogEntry blogEntry;
    if (gwtBlogEntry.isNew()) {
      blogEntry = new BlogEntry(gwtBlogEntry.getHeader());
    } else {
      blogEntry = pm.getObjectById(BlogEntry.class, subject.getKey());
    }
    blogEntry.setDatePublished(gwtBlogEntry.getDatePublished());
    data.addMessage("gwt text: " + gwtBlogEntry.getBody());
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
      log.info("blog entry saved: " + blogEntry.getHeader());
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

  @Override
  public BaseGwtObject get(String key) {
    PersistenceManager pm = PMF.get().getPersistenceManager();
    BlogEntry blogEntry = null;
    try {
      blogEntry = pm.getObjectById(BlogEntry.class, key);
      log.warning("get blog entry: " + blogEntry);
      log.warning("get blog tag: " + blogEntry.getBlogEntryTags());
    } finally {
      pm.close();
    }
    return AppUtil.map(new GwtBlogEntry(), blogEntry);
  }
}
