/* ============================================================
   드릴다운 유저 탐색기 — app.solution.js
   ============================================================ */


/* ------------------------------------------------------------
   1단계: fetch 함수 작성 — async / await 사용
   ------------------------------------------------------------ */

async function getUsers() {
  const res = await fetch("https://dummyjson.com/users");
  const data = await res.json();
  return data.users;
}

async function getPosts(userId) {
  const res = await fetch(`https://dummyjson.com/posts/user/${userId}`);
  const data = await res.json();
  return data.posts;
}

async function getComments(postId) {
  const res = await fetch(`https://dummyjson.com/posts/${postId}/comments`);
  const data = await res.json();
  return data.comments;
}

// ✅ 1단계 확인용 — 완성 후 주석 해제
// getUsers().then(users => console.log("getUsers():", users));
// getPosts(1).then(posts => console.log("getPosts(1):", posts));
// getComments(15).then(comments => console.log("getComments(15):", comments));

/* ------------------------------------------------------------
   2단계: 유저 목록 렌더링 — .then() / .catch() 사용
   ------------------------------------------------------------ */

const usersContainer = document.getElementById("users-container");

function renderUsers() {
  getUsers()
    .then(users => {
      usersContainer.innerHTML = "";

      users.forEach(user => {
        const card = document.createElement("div");
        card.className = "user-card";
        card.innerHTML = `
          <div class="user-name">${user.firstName} ${user.lastName}</div>
          <div class="user-email">${user.email}</div>
          <div class="user-company">${user.company.name}</div>
        `;

        card.addEventListener("click", () => {
          document.querySelectorAll(".user-card").forEach(c => c.classList.remove("selected"));
          card.classList.add("selected");
          renderPosts(user.id, `${user.firstName} ${user.lastName}`);
        });

        usersContainer.appendChild(card);
      });
    })
    .catch(err => {
      usersContainer.innerHTML = `<p class="placeholder">불러오기 실패: ${err.message}</p>`;
    });
}

renderUsers(); // 페이지 로드 시 바로 실행


/* ------------------------------------------------------------
   3단계: 게시글 목록 렌더링 — .then() / .catch() 사용
   ------------------------------------------------------------ */

const postsContainer = document.getElementById("posts-container");
const postsTitle     = document.getElementById("posts-title");

function renderPosts(userId, userName) {
  postsTitle.textContent = `${userName}의 게시글`;
  postsContainer.innerHTML = "<p class='placeholder'>불러오는 중...</p>";

  getPosts(userId)
    .then(posts => {
      postsContainer.innerHTML = "";

      posts.forEach(post => {
        const item = document.createElement("div");
        item.className = "post-item";
        item.innerHTML = `
          <div class="post-title">${post.title}</div>
          <div class="post-body">${post.body}</div>
        `;

        item.addEventListener("click", () => {
          document.querySelectorAll(".post-item").forEach(p => p.classList.remove("selected"));
          item.classList.add("selected");
          renderComments(post);
        });

        postsContainer.appendChild(item);
      });
    })
    .catch(err => {
      postsContainer.innerHTML = `<p class="placeholder">불러오기 실패: ${err.message}</p>`;
    });
}


/* ------------------------------------------------------------
   4단계: 댓글 목록 렌더링 — async / await 사용
   ------------------------------------------------------------ */

const commentsContainer = document.getElementById("comments-container");
const commentsTitle     = document.getElementById("comments-title");

async function renderComments(post) {
  commentsTitle.textContent = post.title;
  commentsContainer.innerHTML = "<p class='placeholder'>불러오는 중...</p>";

  const comments = await getComments(post.id);

  commentsContainer.innerHTML = "";

  const summary = document.createElement("div");
  summary.className = "post-summary";
  summary.innerHTML = `
    <div class="summary-title">${post.title}</div>
    <div class="summary-body">${post.body}</div>
  `;
  commentsContainer.appendChild(summary);

  comments.forEach(comment => {
    const item = document.createElement("div");
    item.className = "comment-item";
    item.innerHTML = `
      <div class="comment-user">${comment.user.username}</div>
      <div class="comment-body">${comment.body}</div>
    `;
    commentsContainer.appendChild(item);
  });
}