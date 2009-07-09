package com.appspot.ast.client.form;

import com.appspot.ast.client.model.GwtBlogEntry;
import com.appspot.ast.client.model.GwtTag;
import com.appspot.ast.client.widget.CheckBoxList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 05.07.2009
 * Time: 13:53:28
 */
public class BlogEntryEdit extends BaseEdit {
  private DateBox dateBox;
  private TextBox header;
  private RichTextArea textEditor;
  private CheckBoxList<GwtTag> tagsCheckBoxList;

  public CheckBoxList<GwtTag> getTagsCheckBoxList() {
    return tagsCheckBoxList;
  }

  @Override
  public void create() {
    super.create();
    addField("Дата", dateBox = new DateBox());
    addField("Заголовок", header = new TextBox());
    addField("Текст", textEditor = new RichTextArea());
    addField("Теги", tagsCheckBoxList = new CheckBoxList<GwtTag>());
  }

  @Override
  public void updateView() {
    GwtBlogEntry blogEntry = (GwtBlogEntry) subject;
    dateBox.setValue(blogEntry.getDatePublished());
    header.setValue(blogEntry.getHeader());
    textEditor.setText(blogEntry.getBody());
//    tagsCheckBoxList.setValue(blogEntry.getBlogRecordTags().toArray(new GwtTag[blogEntry.getBlogRecordTags().size()]));
    List<GwtTag> tags = blogEntry.getBlogRecordTags();
    GWT.log("updateView.tags " + tags, null);
    for (GwtTag tag : tags) {
      GWT.log("tag: " + tag, null);
    }
    tagsCheckBoxList.setSelected(tags);
  }

  @Override
  public void updateModel() {
    GwtBlogEntry blogEntry = (GwtBlogEntry) subject;
    if (blogEntry == null) {
      blogEntry = new GwtBlogEntry();
      markNew(blogEntry);
    }
    blogEntry.setDatePublished(dateBox.getValue());
    blogEntry.setHeader(header.getText());
    GWT.log("updateModel.text: " + textEditor.getText(), null);
    blogEntry.setBody(textEditor.getText());
    blogEntry.setBlogEntryTags(tagsCheckBoxList.getValue());
    GWT.log("gwt blogEntry " + blogEntry.getBlogRecordTags().size(), null);
    subject = blogEntry;
  }
}
