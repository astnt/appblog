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

<html>
<head>
    <script type="text/javascript" language="javascript" src="ast/ast.nocache.js"></script>
</head>
<body>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
%>
<p>Hello, <%= user.getNickname() %>!</p>
<hr/>
<% } %>

<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
    String query = "select from " + BlogRecord.class.getName();
    List<BlogRecord> greetings = null;
    if (pm.newQuery(query).execute() != null) {
        greetings = (List<BlogRecord>) pm.newQuery(query).execute();
    }
    if (greetings != null && greetings.isEmpty()) {
%>
<p>Has no messages.</p>
<%
} else if (greetings != null) {
    for (BlogRecord g : greetings) {
%>
<p><strong><%= g.getHeader() %></strong></p>
<blockquote><%= g.getBody() %></blockquote>
<%
        }
    }
    pm.close();
%>

<% if (user != null && AppUtil.isAdmin(user.getNickname())) { %>
<form action="/postBlogRecord" name="blogRecordEdit" method="post">
    <dl>
        <dt>Заголовок:</dt>
        <dd><input name="header" size="30"/></dd>
    </dl>
    <dl>
        <dt>Сообщение:</dt>
        <dd><textarea name="body" cols="50" rows="10"></textarea></dd>
    </dl>
    <dl>
        <dt>Дата публикации:</dt>
        <dd><input name="datePublished"/></dd>
    </dl>
    <dl>
        <dt></dt>
        <dd>
            <input type="submit" name="posted" value="Сохранить"/>
        </dd>
    </dl>
</form>
<% } else { %>
<% } %>
</body>
</html>
