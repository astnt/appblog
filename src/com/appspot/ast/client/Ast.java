package com.appspot.ast.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.client.model.GwtTag;
import com.appspot.ast.client.model.GwtBlogEntry;
import com.appspot.ast.client.model.BaseGwtObject;
import com.appspot.ast.client.form.BlogEntryEdit;
import com.appspot.ast.client.form.BaseEditServiceAsync;
import com.appspot.ast.client.form.BaseEditService;

public class Ast implements EntryPoint {
  private final MainServiceAsync mainService = GWT.create(MainService.class);
  private final BaseEditServiceAsync editService = GWT.create(BaseEditService.class);
  private Tree tagList;
  private Tree recordsList;

  public void onModuleLoad() {
    final BlogEntryEdit edit = new BlogEntryEdit();
    edit.create();

    RootPanel blogEntryEditContainer = RootPanel.get("BlogEntryEdit");
    blogEntryEditContainer.add(edit);

    RootPanel mainContainer = RootPanel.get("mainMenu");
    if (mainContainer != null) {
      mainContainer.add(createMenu());
    } else {
      return;
    }

    mainService.mainServer("test - test", new AsyncCallback<BaseReferenceData>() {
      public void onFailure(Throwable caught) {
        GWT.log("FAIL" + caught, null);
      }

      public void onSuccess(BaseReferenceData data) {
        GwtTag[] tags = data.getTags();
        edit.getTagsCheckBoxList().setValue(tags);
        GWT.log("OK", null);
        for (GwtTag tag : tags) {
          tagList.addItem(tag.getHeader());
        }
        for (final GwtBlogEntry entry : data.getRecords()) {
          HTML link = new HTML(entry.getHeader());
          link.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
              GWT.log("key: " + entry.getKey(), null);
              editService.get(entry.getKey(), new AsyncCallback<BaseGwtObject>() {
                @Override
                public void onFailure(Throwable throwable) {
                  GWT.log("FAIL, " + throwable.getLocalizedMessage(), null);
                }
                @Override
                public void onSuccess(BaseGwtObject baseGwtObject) {
                  GwtBlogEntry gwtBlogEntry = (GwtBlogEntry) baseGwtObject;
                  edit.setSubject(gwtBlogEntry);
                  edit.updateView();
                }
              });
            }
          });
          recordsList.addItem(link);
        }
      }
    });
  }

  private Widget createMenu() {
    StackPanel panel = new StackPanel();
    panel.setWidth("100%");
    panel.add(recordsList = new Tree(), "Записи");
    VerticalPanel tagPanel = new VerticalPanel();
    tagPanel.add(tagList = new Tree());
    final TextBox addTagInput = new TextBox();
    addTagInput.setSize("5em", "1em");
    tagPanel.add(addTagInput);

    addTagInput.addKeyUpHandler(new KeyUpHandler() {

      @Override
      public void onKeyUp(KeyUpEvent keyUpEvent) {
        if (keyUpEvent.getNativeKeyCode() == 13) {
          GWT.log("add tag " + addTagInput.getText(), null);
          addTag(addTagInput.getText());
          addTagInput.setText("");
        }
      }
    });

    panel.add(tagPanel, "Теги");
    return panel;
  }

  private void addTag(String text) {
    mainService.addTag(text, new AsyncCallback<String>() {
      @Override
      public void onFailure(Throwable throwable) {
      }

      @Override
      public void onSuccess(String value) {
        tagList.addItem(value);
        GWT.log("new tag: " + value, null);
      }
    });
  }
}
