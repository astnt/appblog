package com.appspot.ast.server;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;
//import java.util.logging.Logger;
import javax.servlet.http.*;

import com.appspot.ast.model.BlogEntry;
import com.appspot.ast.model.PMF;
import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class BlogRecordServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(BlogRecordServlet.class.getName());

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String header = request.getParameter("header");
    String body = request.getParameter("body");
//		Date datePublished = request.getParameter("datePublished");

    PersistenceManager pm = PMF.get().getPersistenceManager();
    BlogEntry blogEntry = new BlogEntry(header, body, new Date());

    try {
      pm.makePersistent(blogEntry);
    } finally {
      pm.close();
    }

    response.sendRedirect("/admin.jsp");
  }

}
