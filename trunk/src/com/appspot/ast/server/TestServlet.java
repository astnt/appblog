package com.appspot.ast.server;

import com.appspot.ast.model.PMF;
import com.appspot.ast.model.BlogTag;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.PersistenceManager;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Антон
 * Date: 23.05.2009
 * Time: 14:36:13
 */

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("test 11");
//        createDefaultTags(response);

        PersistenceManager pm = PMF.get().getPersistenceManager();
        String query = "select from " + BlogTag.class.getName();
        List<BlogTag> records = null;
        if (pm.newQuery(query).execute() != null) {
            records = (List<BlogTag>) pm.newQuery(query).execute();
        }

        for (BlogTag blogTag : records) {
            response.getWriter().println("entity: " + blogTag.getHeader() + "<br />");
        }
        pm.close();
    }

    private void createDefaultTags(HttpServletResponse response) throws IOException {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        List<BlogTag> list = new ArrayList<BlogTag>();
        list.add(new BlogTag("Linux"));
        list.add(new BlogTag("google"));
        list.add(new BlogTag("appspot"));
        try {
            for (BlogTag blogTag : list) {
                pm.makePersistent(blogTag);
                response.getWriter().println("blogTag added: " + blogTag.getHeader());
            }
        } finally {
            pm.close();
        }
    }

}
