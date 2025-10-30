<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Mini Zing - Trang chủ</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
<div class="container-fluid p-4">

    <!-- 🔷 HEADER -->
    <header class="d-flex flex-wrap justify-content-between align-items-center mb-4">
        <h1 class="text-primary">🎵 miniZing</h1>

        <!-- 🔍 Tìm kiếm -->
        <form action="${pageContext.request.contextPath}/SearchController" method="get" class="d-flex gap-2 flex-grow-1 mx-4">
            <input type="text" name="keyword" class="form-control" placeholder="Tìm bài hát, album, nghệ sĩ..." required />
            <button type="submit" class="btn btn-primary">Tìm</button>
        </form>

        <!-- 🔐 Đăng nhập / Phân quyền -->
        <c:choose>
            <c:when test="${sessionScope.role == 'admin'}">
                <a href="${pageContext.request.contextPath}/adminDashboard.jsp" class="btn btn-warning">Admin</a>
            </c:when>
            <c:when test="${sessionScope.role == 'user'}">
                <a href="${pageContext.request.contextPath}/userDashboard.jsp" class="btn btn-info">Cá nhân</a>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/UserController" method="post">
                    <input type="hidden" name="txtAction" value="login">
                    <button type="submit" class="btn btn-outline-dark">Đăng nhập</button>
                </form>
            </c:otherwise>
        </c:choose>
    </header>

    <div class="row">
        <!-- 📂 SIDEBAR PLAYLIST -->
        <aside class="col-md-3">
            <h4 class="mb-3">Playlist gợi ý</h4>
            <div class="list-group">
                <c:forEach var="song" items="${playlist}">
                    <a href="javascript:void(0);" class="list-group-item list-group-item-action d-flex align-items-center"
                       data-src="${pageContext.request.contextPath}/Audio/${song.file}"
                       data-cover="${pageContext.request.contextPath}/Image/${song.cover}"
                       onclick="playFromList(this)">
                        <img src="${pageContext.request.contextPath}/Image/${song.cover}" alt="cover" class="me-2 rounded" width="50" height="50" />
                        <div class="flex-grow-1">
                            <div class="fw-bold"><c:out value="${song.title}" /></div>
                            <small class="text-muted"><c:out value="${song.artist}" /></small>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </aside>

        <!-- 🎧 PLAYER -->
        <section class="col-md-9">
            <div class="card mb-4">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img id="mainCover" src="${pageContext.request.contextPath}/images/${currentSong.cover}" class="img-fluid rounded-start" alt="cover">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 id="songTitle" class="card-title"><c:out value="${currentSong.title}" /></h5>
                            <p id="songArtist" class="card-text text-muted"><c:out value="${currentSong.artist}" /></p>

                            <!-- 🎛️ Điều khiển -->
                            <div class="btn-group mb-3" role="group">
                                <button class="btn btn-success" id="playBtn" onclick="togglePlay()">▶ Play</button>
                                <button class="btn btn-outline-secondary" onclick="prevSong()">⏮</button>
                                <button class="btn btn-outline-secondary" onclick="nextSong()">⏭</button>
                                <button class="btn btn-outline-warning" onclick="toggleShuffle()">🔀</button>
                                <button class="btn btn-outline-info" onclick="toggleRepeat()">🔁</button>
                            </div>

                            <!-- 📊 Thanh tiến trình -->
                            <div class="progress mb-2" onclick="seek(event)" style="cursor:pointer;">
                                <div class="progress-bar bg-primary" id="progressFill" role="progressbar" style="width: 0%;"></div>
                            </div>
                            <div class="d-flex justify-content-between">
                                <span id="currentTime">0:00</span>
                                <span id="duration">0:00</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- 🧾 FOOTER -->
    <footer class="text-center mt-4 text-muted">
        <hr>
        <p>&copy; 2025 Mini Zing. All rights reserved.</p>
    </footer>
</div>

<!-- 🔊 AUDIO -->
<audio id="audio" preload="metadata">
    <source src="${pageContext.request.contextPath}/audio/${currentSong.file}" type="audio/mpeg" />
    Trình duyệt của bạn không hỗ trợ thẻ audio.
</audio>

<!-- ⚙️ JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/JS/music.js"></script>
</body>
</html>
