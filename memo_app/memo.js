const memoForm = document.getElementById("memo-form");
const memoTitle = document.getElementById("memo-title");
const memoContent = document.getElementById("memo-content");
const memoList = document.getElementById("memo-list");

/* 메모 추가 시 생성될 내용
<li class="memo-item">
  <h3 class="memo-title">회의 내용 정리</h3>
  <p class="memo-content">
    오늘 회의에서 논의된 주요 안건과 다음 일정에 대해 정리했습니다. 팀원 모두 확인 부탁드립니다.
  </p>
  <span class="memo-time">2025-08-11 15:00</span>
  <button class="memo-del-btn">삭제</button>
</li>
 */

/* 날짜가 담기 <span></span> 엘리먼트 생성코드 제공
  const timeSpan = document.createElement("span");
  timeSpan.classList.add("memo-time");
  const now = new Date();
  timeSpan.textContent = now.toLocaleString();
*/

memoForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const title = memoTitle.value.trim();
  const content = memoContent.value.trim();
  if (!title || !content) return; // 둘 다 없으면 무시

  const li = document.createElement("li");
  li.classList.add("memo-item");

  const h3 = document.createElement("h3");
  h3.classList.add("memo-title");
  h3.textContent = title || "(제목 없음)";

  const p = document.createElement("p");
  p.classList.add("memo-content");
  p.textContent = content || "(내용 없음)";

  const timeSpan = document.createElement("span");
  timeSpan.classList.add("memo-time");
  const now = new Date();
  timeSpan.textContent = now.toLocaleString();

  const delBtn = document.createElement("button");
  delBtn.classList.add("memo-del-btn");
  delBtn.textContent = "삭제";
  delBtn.addEventListener("click", () => {
    li.remove();
  });

  li.appendChild(h3);
  li.appendChild(p);
  li.appendChild(timeSpan);
  li.appendChild(delBtn);

  memoList.appendChild(li);

  memoTitle.value = "";
  memoContent.value = "";
});
