<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Test Comment Feature (Soft Delete)</title>
    <style>
        .comment-box {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .comment-box b {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Test Comment Feature</h1>

<%-- Thiết lập tạm thời userId và songId để TEST --%>
<% 
    int userID = 1; 
    int songId = 1; 
    // Giả sử Controller đã lấy danh sách comment và set vào attribute 'comments'
%>

<hr>

<h2>Thêm Comment Mới</h2>
<form action="comment" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="userId" value="<%= userID %>">
    <input type="hidden" name="songId" value="<%= songId %>">
    
    <textarea name="content" placeholder="Viết comment của bạn..." required style="width: 300px; height: 80px;"></textarea>
    <br>
    <button type="submit">Gửi Comment</button>
</form>

<hr>

<h2>Danh sách Comments (Chỉ hiển thị comment CHƯA xóa)</h2>

<c:if test="${empty comments}">
    <p>Chưa có comment nào (hoặc tất cả đã bị xóa mềm).</p>
</c:if>

<c:forEach var="c" items="${comments}">
    <div class="comment-box">
        <b>ID Comment:</b> ${c.commentId} <br>
        <b>User ID:</b> ${c.user.userID} <br>
        <b>Nội dung:</b> ${c.content} <br>
        <b>Thời gian:</b> ${c.createdAt}

        <form action="comment" method="post" style="margin-top: 8px;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="commentId" value="${c.commentId}">
            <button type="submit" 
                    onclick="return confirm('Bạn có chắc chắn muốn xóa mềm (ẩn) comment ID ${c.commentId} này không?');"
                    style="color: red;">
                Xóa Mềm (Soft Delete)
            </button>
        </form>
    </div>
</c:forEach>

</body>
</html>