package com.appspot.ast.client.model;

import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 23.05.2009
 * Time: 17:28:41
 */
public abstract class BaseGwtObject implements HasName, IsSerializable {
  private String key;
  private boolean aNew;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public boolean isNew() {
    return aNew;
  }

  public void setNew(boolean aNew) {
    this.aNew = aNew;
  }
}
