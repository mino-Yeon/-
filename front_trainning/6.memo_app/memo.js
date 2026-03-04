const memoForm = document.getElementById("memo-form");
const memoTitle = document.getElementById("memo-title");
const memoContent = document.getElementById("memo-content");
const memoList = document.getElementById("memo-list");


//솔루션을 위한 코드임
const memoDelBtn = document.querySelector(".memo-del-btn");
memoDelBtn.addEventListener('click',(e)=>{
    const confirmed = confirm("메모를 삭제할까요?");
    if (!confirmed) return;

    e.target.parentElement.remove();
})
/////////////////////////////

// 입력 시 에러 클래스 제거
memoTitle.addEventListener("input", () => {
  memoTitle.classList.remove("input-error");
});
memoContent.addEventListener("input", () => {
  memoContent.classList.remove("input-error");
});

memoForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const title = memoTitle.value.trim();
  const content = memoContent.value.trim();

  if (!title) memoTitle.classList.add("input-error");
  if (!content) memoContent.classList.add("input-error");
  if (!title || !content) return;

  const li = document.createElement("li");
  li.classList.add("memo-item");

  const h3 = document.createElement("h3");
  h3.classList.add("memo-title");
  h3.textContent = title;

  const p = document.createElement("p");
  p.classList.add("memo-content");
  p.textContent = content;

  const timeSpan = document.createElement("span");
  timeSpan.classList.add("memo-time");
  const now = new Date();
  timeSpan.textContent = now.toLocaleString();

  const delBtn = document.createElement("button");
  delBtn.classList.add("memo-del-btn");
  delBtn.textContent = "삭제";
  delBtn.addEventListener("click", () => {
    const confirmed = confirm("메모를 삭제할까요?");
    if (!confirmed) return;
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