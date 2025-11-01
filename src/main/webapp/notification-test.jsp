<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách Thông báo</title>
    <style>
        .notification-item {
            padding: 15px;
            margin-bottom: 10px;
            border-bottom: 1px solid #eee;
            cursor: pointer;
        }
        .unread {
            background-color: #e6f7ff; /* Màu xanh nhạt cho thông báo chưa đọc */
            font-weight: bold;
            border-left: 5px solid #007bff;
        }
        .read {
            color: #6c757d; /* Màu xám cho thông báo đã đọc */
        }
    </style>
</head>
<body>

<h1>Thông báo của bạn</h1>
<p>Tổng số thông báo chưa đọc: **${unreadCount != null ? unreadCount : 0}**</p>

<hr>

<c:if test="${empty notifications}">
    <p>Bạn không có thông báo nào mới.</p>
</c:if>

<c:forEach var="n" items="${notifications}">
    <%-- Xây dựng URL để đánh dấu đã đọc và chuyển hướng đến nội dung --%>
    <c:url var="targetUrl" value="notification">
        <c:param name="action" value="read"/>
        <c:param name="id" value="${n.notificationId}"/>
    </c:url>

    <div class="notification-item ${n.isRead ? 'read' : 'unread'}">
        <a href="${targetUrl}" style="text-decoration: none; color: inherit;">
            <p>
                [<c:out value="${n.isRead ? 'Đã đọc' : 'CHƯA ĐỌC'}"/>] 
                <c:out value="${n.message}"/> 
                <span style="float: right; font-size: 0.8em;">(${n.createdAt})</span>
            </p>
            <c:if test="${n.song != null}">
                <p style="margin: 0; font-size: 0.9em; color: #007bff;">
                    > **Liên quan đến bài hát ID: ${n.song.songId}**
                </p>
            </c:if>
        </a>
    </div>
</c:forEach>

</body>
</html>