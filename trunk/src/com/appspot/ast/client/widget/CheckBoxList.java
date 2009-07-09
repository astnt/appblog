package com.appspot.ast.client.widget;

import com.appspot.ast.client.model.BaseGwtObject;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 23.05.2009
 * Time: 17:24:31
 */
public class CheckBoxList<T extends BaseGwtObject> extends VerticalPanel {
  private List<UserCheckBox> checkboxes;

  public CheckBoxList() {
  }

  public CheckBoxList(T[] list) {
    setValue(list);
  }

  public void setValue(T[] list) {
    clear();
    checkboxes = new ArrayList<UserCheckBox>();
    for (T item : list) {
      UserCheckBox checkBox = new UserCheckBox();
      checkBox.setText(item.getName());
      checkBox.setFormValue(String.valueOf(item.getKey()));
      checkBox.setUserObject(item);
      add(checkBox);
      checkboxes.add(checkBox);
    }
  }

  public void setSelected(List<T> t) {
    for (UserCheckBox item : checkboxes) {
      item.setValue(false);
      for (T t1 : t) {
        if (item.getUserObject().equals(t1)) {
          item.setValue(true);
        }
      }
    }
  }

  public List<T> getValue() {
    List<T> list = new ArrayList<T>();
    for (UserCheckBox checkbox : checkboxes) {
      if (checkbox.getValue()) {
        list.add(checkbox.getUserObject());
      }
    }
    return list;
  }

  private class UserCheckBox extends CheckBox {
    private T userObject;

    public T getUserObject() {
      return userObject;
    }

    public void setUserObject(T userObject) {
      this.userObject = userObject;
    }
  }

}
