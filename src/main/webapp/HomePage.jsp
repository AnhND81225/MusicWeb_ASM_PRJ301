<%-- 
    Document   : HomePage
    Created on : Oct 20, 2025, 2:57:53 PM
    Author     : ASUS
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mini Zing - Demo</title>
    <!-- Dùng context path để tránh lỗi đường dẫn -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/music.css">



</head>

<body>
    <div class="container">
        <header>
            <div class="logo">miniZing</div>
            <div class="search">
                <input id="searchInput" placeholder="Tìm bài hát, nghệ sĩ, album..." />
            </div>
            <div>
                <button class="icon">Đăng nhập</button>
            </div>
        </header>

        <main>
            <aside class="sidebar" id="sidebarPlaylist">
                <h3>Playlist gợi ý</h3>

                <div class="playlist-item" data-src="${pageContext.request.contextPath}/audio/song1.mp3"
                    data-cover="${pageContext.request.contextPath}/images/cover1.jpg"
                    data-lyric="${pageContext.request.contextPath}/lyrics/song1.txt" onclick="playFromList(this)">
                    <img class="thumb" src="${pageContext.request.contextPath}/images/cover1.jpg" alt="cover" />
                    <div class="meta">
                        <div style="font-weight:600">Tên bài hát 1</div>
                        <div style="font-size:12px;color:var(--muted)">Ca sĩ A</div>
                    </div>
                </div>

                <div class="playlist-item" data-src="${pageContext.request.contextPath}/audio/song2.mp3"
                    data-cover="${pageContext.request.contextPath}/images/cover2.jpg"
                    data-lyric="${pageContext.request.contextPath}/lyrics/song2.txt" onclick="playFromList(this)">
                    <img class="thumb" src="${pageContext.request.contextPath}/images/cover2.jpg" alt="cover" />
                    <div class="meta">
                        <div style="font-weight:600">Tên bài hát 2</div>
                        <div style="font-size:12px;color:var(--muted)">Ca sĩ B</div>
                    </div>
                </div>
            </aside>

            <section class="content">
                <div class="hero">
                    <img id="mainCover" class="cover" src="${pageContext.request.contextPath}/images/cover1.jpg" alt="cover" />
                    <div class="info">
                        <h2 id="songTitle">Tên bài hát 1</h2>
                        <div id="songArtist" style="color:var(--muted);margin-top:6px">Ca sĩ A</div>

                        <div class="controls">
                            <button class="btn" id="playBtn" onclick="togglePlay()">Play</button>
                            <button class="icon" onclick="prevSong()">⟨</button>
                            <button class="icon" onclick="nextSong()">⟩</button>
                            <div style="margin-left:auto">
                                <button class="icon" onclick="toggleShuffle()">Shuffle</button>
                                <button class="icon" onclick="toggleRepeat()">Repeat</button>
                            </div>
                        </div>

                        <div style="margin-top:10px">
                            <div class="progress" onclick="seek(event)" id="progressBar"><i id="progressFill"></i></div>
                            <div style="display:flex;justify-content:space-between" class="time">
                                <span id="currentTime">0:00</span><span id="duration">0:00</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="lyrics" id="lyricsBox">
                    <strong>Lời bài hát</strong>
                    <p id="lyricText" style="white-space:pre-wrap;color:var(--muted);">
                        Đây là phần lời mẫu. Khi tải bài hát thật, bạn có thể load file lyric từ server và hiển thị vào
                        đây.
                    </p>
                </div>

                <div style="margin-top:16px">
                    <h4>Danh sách phát</h4>
                    <div id="miniList"></div>
                </div>
            </section>
        </main>
    </div>

    <div class="player" aria-label="audio player">
        <img id="miniCover" class="mini-cover" src="${pageContext.request.contextPath}/images/cover1.jpg" alt="cover" />
        <div class="title">
            <div id="miniTitle" style="font-weight:600">Tên bài hát 1</div>
            <div id="miniArtist" style="font-size:12px;color:var(--muted)">Ca sĩ A</div>
            <div class="progress" style="margin:6px 0"><i id="miniProgress"></i></div>
        </div>

        <div style="display:flex;flex-direction:column;align-items:center;gap:6px">
            <div>
                <button class="icon" onclick="prevSong()">⟨</button>
                <button class="icon" id="playBtn2" onclick="togglePlay()">▶/❚❚</button>
                <button class="icon" onclick="nextSong()">⟩</button>
            </div>
            <div class="time" id="miniTime">0:00 / 0:00</div>
        </div>
    </div>

    <audio id="audio" preload="metadata">
        <source src="${pageContext.request.contextPath}/audio/song1.mp3" type="audio/mpeg" />
        Trình duyệt của bạn không hỗ trợ thẻ audio.
    </audio>

   <script src="${pageContext.request.contextPath}/JS/music.js"></script>
</body>

</html>
