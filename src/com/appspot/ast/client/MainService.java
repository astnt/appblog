package com.appspot.ast.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.appspot.ast.client.ref.BaseReferenceData;
import com.appspot.ast.client.model.GwtBlogEntry;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("main")
public interface MainService extends RemoteService {
	BaseReferenceData mainServer(String name);
  String typograf(String input);
  String addTag(String text);
  BaseReferenceData newBlogRecord(GwtBlogEntry blogEntry);
}
