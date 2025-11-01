<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trang Test Comment & Reply (Song Details)</title>
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
            background-color: #eaf3ff; /* Nền nhẹ cho form reply */
            padding-top: 5px;
        }
        .reply-button { margin-right: 5px; }
    </style>
    <script>
        function showReplyForm(commentId) {
            // Lấy tất cả các form reply và ẩn chúng đi trước
            document.querySelectorAll('.reply-area').forEach(function(el) {
                el.style.display = 'none';
            });

            // Hiển thị form trả lời cho comment cụ thể
            document.getElementById('reply-form-container-' + commentId).style.display = 'block';
            
            // Đặt ID của comment cha vào form trả lời ẩn
            document.getElementById('parent-id-input-' + commentId).value = commentId;
        }
    </script>
</head>
<body>

<%-- 
    Sử dụng Scriptlet để đặt User ID đang đăng nhập (TẠM THỜI)
    Bạn có thể thay đổi ID này giữa các lần test (1 hoặc 2) để mô phỏng người dùng khác nhau.
--%>
<% int currentUserID = 2; %> 

<%-- 
    Lấy Song ID từ Request Scope. Nếu không có (vì thiếu SongController), 
    sẽ mặc định dùng ID=1 (đã có trong DB của bạn).
--%>
<c:set var="testSongId" value="${currentSongId != null ? currentSongId : 1}"/>

<h1>Chi tiết Bài hát ID: <c:out value="${testSongId}"/></h1>
<p>User đang đăng nhập (User ID): **<%= currentUserID %>**</p>
<p>**<a href="notification" style="color: blue;">>>> XEM THÔNG BÁO CỦA BẠN (Tới Notification Controller)</a>**</p>

<hr>

<h2>1. Thêm Comment Gốc</h2>
<form action="comment" method="post">
    <input type="hidden" name="action" value="add"> 
    
    <input type="hidden" name="userId" value="<%= currentUserID %>">
    <input type="hidden" name="songId" value="${testSongId}">
    
    <textarea name="content" placeholder="Viết comment gốc..." required style="width: 400px; height: 80px; padding: 5px;"></textarea>
    <br>
    <button type="submit">Gửi Comment Gốc</button>
</form>

<hr>

<h2>2. Danh sách Comments</h2>
<c:if test="${empty comments}">
    <p>Chưa có comment nào (hãy thêm comment để bắt đầu test reply).</p>
</c:if>

<c:forEach var="c" items="${comments}">
    <div class="comment-box">
        <strong>ID Comment:</strong> ${c.commentId} 
        (User: ${c.user.userID}) <br>
        <strong>Nội dung:</strong> ${c.content} 
        
        <c:if test="${c.parentComment != null}">
            <span style="color: #007bff;"> (Trả lời ID: ${c.parentComment.commentId})</span>
        </c:if>
        
        <br>
        
        <%-- 1. Nút Reply (Dùng JS để hiện form) --%>
        <button class="reply-button" onclick="showReplyForm(${c.commentId})">Trả Lời</button>
        
        <%-- 2. Nút Xóa Mềm --%>
        <form action="comment" method="post" style="display:inline-block;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="commentId" value="${c.commentId}">
            <button type="submit" style="color: red;">Xóa Mềm</button>
        </form>

        <%-- 3. Form Trả Lời ẨN (Để gửi parentCommentId) --%>
        <div id="reply-form-container-${c.commentId}" class="reply-area" style="display:none;">
            <form action="comment" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="userId" value="<%= currentUserID %>">
                <input type="hidden" name="songId" value="${testSongId}">
                
                <%-- INPUT QUAN TRỌNG: Nơi gửi ID của comment cha lên Controller --%>
                <input type="hidden" name="parentCommentId" id="parent-id-input-${c.commentId}" value="">
                
                <textarea name="content" placeholder="Nội dung trả lời..." required style="width: 300px; height: 50px; padding: 3px;"></textarea>
                <br>
                <button type="submit">Gửi Trả Lời</button>
            </form>
        </div>
    </div>
</c:forEach>

</body>
</html>