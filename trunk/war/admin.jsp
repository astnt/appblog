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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link href="favicon.ico" rel="shortcut icon"/>
  <title>Редактирование постов</title>
  <script type="text/javascript" language="javascript" src="ast/ast.nocache.js"></script>
  <link rel="stylesheet" type="text/css" href="Ast.css" media="screen"/>
  <!--[if lte IE 6]>
          <link rel="stylesheet" type="text/css" href="Ast-ie6.css" media="screen" />
  <![endif]-->
</head>
<body>
<% if (UserServiceFactory.getUserService().getCurrentUser() != null
    && UserServiceFactory.getUserService().getCurrentUser().getNickname().equals("anton.starcev")) { %>
<div id="measurer"></div>
<div id="outer">
  <div id="header" class="header">
  </div>
  <div id="content" class="content">
    <div id="mainMenu" class="mainMenu"></div>
    <form action="/blogRecordEdit" class="editForm">
      <div id="BlogEntryEdit"></div>
      <dl>
        <dt></dt>
        <dd id="sendButtonContainer"></dd>
      </dl>
    </form>
    <div class="clear"></div>
  </div>
</div>
<div id="footer" class="footer">
  &copy; <a href="mailto:anton.starcev@gmail.com">anton.starcev@gmail.com</a> 2009
</div>
<% } else { %><h1>Доступ запрещен</h1><% } %>
</body>
</html>



