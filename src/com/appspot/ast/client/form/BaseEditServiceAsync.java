package com.appspot.ast.client.form;

import com.appspot.ast.client.model.BaseGwtObject;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 05.07.2009
 * Time: 14:46:20
 */
public interface BaseEditServiceAsync {
  void save(BaseGwtObject subject, AsyncCallback<BaseReferenceData> async);
  void get(String key, AsyncCallback<BaseGwtObject> async);
}
