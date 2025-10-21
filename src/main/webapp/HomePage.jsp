<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mini Zing - Demo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/music.css">
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">miniZing</div>

            <!-- Form tìm kiếm dùng POST -->
            <form action="SearchController" method="POST" class="search-form">
                <input type="hidden" name="txtAction" value="search">
                <input type="text" name="txtSearch" id="searchInput" placeholder="Tìm bài hát, nghệ sĩ, album..." required />
                <button type="submit">Tìm</button>
            </form>

            <!-- Form đăng nhập -->
            <div>
                <form action="UserController" method="POST">
                    <input type="hidden" name="txtAction" value="login">
                    <button type="submit" class="icon">Đăng nhập</button>
                </form>
            </div>
        </header>

        <main>
            <aside class="sidebar" id="sidebarPlaylist">
                <h3>Playlist gợi ý</h3>
                <c:forEach var="song" items="${playlist}">
                    <div class="playlist-item" 
                         data-src="${pageContext.request.contextPath}/audio/${song.file}" 
                         data-cover="${pageContext.request.contextPath}/images/${song.cover}" 
                         data-lyric="${pageContext.request.contextPath}/lyrics/${song.lyric}" 
                         onclick="playFromList(this)">
                        <img class="thumb" src="${pageContext.request.contextPath}/images/${song.cover}" alt="cover" />
                        <div class="meta">
                            <div class="title">${song.title}</div>
                            <div class="artist">${song.artist}</div>
                        </div>
                    </div>
                </c:forEach>
            </aside>

            <section class="content">
                <div class="hero">
                    <img id="mainCover" class="cover" src="${pageContext.request.contextPath}/images/${currentSong.cover}" alt="cover" />
                    <div class="info">
                        <h2 id="songTitle">${currentSong.title}</h2>
                        <div id="songArtist">${currentSong.artist}</div>

                        <div class="controls">
                            <button class="btn" id="playBtn" onclick="togglePlay()">Play</button>
                            <button class="icon" onclick="prevSong()">⟨</button>
                            <button class="icon" onclick="nextSong()">⟩</button>
                            <button class="icon" onclick="toggleShuffle()">Shuffle</button>
                            <button class="icon" onclick="toggleRepeat()">Repeat</button>
                        </div>

                        <div class="progress-container">
                            <div class="progress" id="progressBar" onclick="seek(event)"><i id="progressFill"></i></div>
                            <div class="time">
                                <span id="currentTime">0:00</span>
                                <span id="duration">0:00</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="lyrics">
                    <strong>Lời bài hát</strong>
                    <p id="lyricText">${currentSong.lyricText}</p>
                </div>
            </section>
        </main>
    </div>

    <audio id="audio" preload="metadata">
        <source src="${pageContext.request.contextPath}/audio/${currentSong.file}" type="audio/mpeg" />
        Trình duyệt của bạn không hỗ trợ thẻ audio.
    </audio>

<script src="${pageContext.request.contextPath}/JS/music.js"></script>

</body>
</html>
