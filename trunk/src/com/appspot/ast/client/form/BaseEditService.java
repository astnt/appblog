package com.appspot.ast.client.form;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.client.model.BaseGwtObject;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 05.07.2009
 * Time: 15:03:58
 */
@RemoteServiceRelativePath("baseEdit")
public interface BaseEditService extends RemoteService {
  BaseReferenceData save(BaseGwtObject subject);
  BaseGwtObject get(String key);
}
