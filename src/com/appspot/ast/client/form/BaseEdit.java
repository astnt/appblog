package com.appspot.ast.client.form;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.client.model.BaseGwtObject;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 05.07.2009
 * Time: 13:45:35
 */
public abstract class BaseEdit extends VerticalPanel {
  protected BaseGwtObject subject;
  protected FlexTable table;
  protected Button saveButton;
  protected final BaseEditServiceAsync editService = GWT.create(BaseEditService.class);

  public void setSubject(BaseGwtObject subject) {
    this.subject = subject;
  }

  public void create() {
    add(table = new FlexTable());
    add(saveButton = new Button("Сохранить"));

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        updateModel();
        editService.save(subject, new AsyncCallback<BaseReferenceData>() {
          public void onFailure(Throwable caught) {
            GWT.log("FAIL, " + caught, null);
          }

          public void onSuccess(BaseReferenceData data) {
            GWT.log("OK, " + data, null);
            for (String messages : data.getMessages()) {
              GWT.log(messages, null);
            }
          }
        });
      }
    });
  }

  protected void addField(String label, Widget widget) {
    int count = table.getRowCount();
    table.setWidget(count, 0, new Label(label));
    table.setWidget(count, 1, widget);
  }

  public void markNew(BaseGwtObject gwtObject) {
    gwtObject.setNew(true);
  }

  public abstract void updateView();

  public abstract void updateModel();
}
