<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trang Test Comment & Reply</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .comment-box {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
        }
        .reply-area {
            margin-top: 10px;
            border-left: 3px solid #007bff;
            padding-left: 10px;
        }
    </style>
    <script>
        function showReplyForm(commentId) {
            // Hiển thị form trả lời cho comment cụ thể
            document.getElementById('reply-form-container-' + commentId).style.display = 'block';
            
            // Đặt ID của comment cha vào form trả lời ẩn
            document.getElementById('parent-id-input-' + commentId).value = commentId;
        }
    </script>
</head>
<body>

<%-- Thiết lập tạm thời User ID đang đăng nhập --%>
<%-- GIẢ SỬ User 1 là chủ bài hát hoặc là người viết comment gốc --%>
<% int currentUserID = 2; // Dùng User 2 để comment/reply và tạo thông báo cho User 1 %>
<% int currentSongId = 1; %>

<h1>Chi tiết Bài hát ID: <%= currentSongId %></h1>
<p>User đang đăng nhập (User ID): **<%= currentUserID %>**</p>
<p>**<a href="notification">XEM THÔNG BÁO</a>**</p>

<hr>

<h2>Thêm Comment Gốc</h2>
<form action="comment" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="userId" value="<%= currentUserID %>">
    <input type="hidden" name="songId" value="<%= currentSongId %>">
    
    <textarea name="content" placeholder="Viết comment gốc..." required style="width: 400px; height: 80px; padding: 5px;"></textarea>
    <br>
    <button type="submit">Gửi Comment Gốc</button>
</form>

<hr>

<h2>Danh sách Comments</h2>
<c:forEach var="c" items="${comments}">
    <div class="comment-box">
        <strong>ID Comment:</strong> ${c.commentId} 
        (User: ${c.user.userID}) <br>
        <strong>Nội dung:</strong> ${c.content} 
        
        <c:if test="${c.parentComment != null}">
            <span style="color: gray;">(Trả lời ID: ${c.parentComment.commentId})</span>
        </c:if>
        
        <br>
        
        <%-- 1. Nút Reply --%>
        <button onclick="showReplyForm(${c.commentId})">Trả Lời</button>
        
        <%-- 2. Nút Xóa Mềm --%>
        <form action="comment" method="post" style="display:inline-block;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="commentId" value="${c.commentId}">
            <button type="submit" style="color: red;">Xóa Mềm</button>
        </form>

        <%-- 3. Form Trả Lời ẨN (Chỉ hiện khi nhấn nút Trả Lời) --%>
        <div id="reply-form-container-${c.commentId}" class="reply-area" style="display:none;">
            <form action="comment" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="userId" value="<%= currentUserID %>">
                <input type="hidden" name="songId" value="<%= currentSongId %>">
                
                <%-- INPUT QUAN TRỌNG: Gửi ID của comment cha lên Controller --%>
                <input type="hidden" name="parentCommentId" id="parent-id-input-${c.commentId}" value="">
                
                <textarea name="content" placeholder="Nội dung trả lời..." required style="width: 300px; height: 50px;"></textarea>
                <br>
                <button type="submit">Gửi Trả Lời</button>
            </form>
        </div>
    </div>
</c:forEach>

</body>
</html>