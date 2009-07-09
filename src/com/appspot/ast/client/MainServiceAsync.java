package com.appspot.ast.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.client.model.GwtBlogEntry;
import com.appspot.ast.client.form.BaseEditServiceAsync;

/**
 * The async counterpart of <code>TypografService</code>.
 */
public interface MainServiceAsync {
  void mainServer(String name, AsyncCallback<BaseReferenceData> async);
  void typograf(String input, AsyncCallback<String> async);
  void addTag(String text, AsyncCallback<String> asyncCallback);
  void newBlogRecord(GwtBlogEntry blogEntry, AsyncCallback<BaseReferenceData> asyncCallback);
}
