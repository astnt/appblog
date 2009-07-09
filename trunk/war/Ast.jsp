<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.jdo.*" %>
<%@ page import="ru.artlebedev.typograf.*" %>
<%@ page import="ru.artlebedev.typograf.rule.*" %>
<%@ page import="ru.artlebedev.typograf.rule.chars.*" %>
<%@ page import="com.appspot.ast.utils.*" %>
<%@ page import="com.appspot.ast.model.*" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link href="favicon.ico" rel="shortcut icon"/>
  <title>Ast home page</title>
  <script type="text/javascript" language="javascript" src="ast/ast.nocache.js"></script>
  <link rel="stylesheet" type="text/css" href="Ast.css" media="screen"/>
  <!--[if lte IE 6]>
          <link rel="stylesheet" type="text/css" href="Ast-ie6.css" media="screen" />
  <![endif]-->
</head>
<body>
<div id="measurer"></div>
<div id="outer">
  <div id="header" class="header">
    <!-- img src="Anton.jpg" class="Logo" width="100" height="100" alt="Anton Starcev" /-->

    <div class="description">
      <h1>Name</h1>

      <p>summary</p>
    </div>
    <div class="clear"></div>
  </div>
  <div id="content" class="content">
    <%
      PersistenceManager pm = PMF.get().getPersistenceManager();
      List<BlogEntry> blogEntries = (List<BlogEntry>) pm.newQuery("select from " + BlogEntry.class.getName()).execute();
      for (BlogEntry blogEntry : blogEntries) { %>
    <h4><%=blogEntry.getHeader()%></h4>
    <p class="date"><%=blogEntry.getDatePublished()%></p>
    <div class="body"><%=String.valueOf(blogEntry.getText().getValue())%></div><%%>
    <ul class="tags">
      <% if (blogEntry.getBlogEntryTags() != null) {
           for (Key tag : blogEntry.getBlogEntryTags()) { %>
             <li><%=tag.getName()%></li>
      <% }} else { %><li>blogEntry.getBlogRecordTags() is null</li><% } %>
    </ul>
    <div class="clear"></div>
    <% } %>
  </div>
  <div class="clear"></div>
</div>
<div id="footer" class="footer">
  &copy; <a href="mailto:anton.starcev@gmail.com">anton.starcev@gmail.com</a> 2009
</div>
</body>
</html>



