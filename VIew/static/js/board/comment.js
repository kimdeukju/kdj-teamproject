// lists html에서 COMMENTS 버튼을 클릭할 때, 사용
const commentLists = document.querySelector('.comment-con>.comment-data');
const commentsActualData = document.querySelector('.comment-lists');
const writeComment = document.querySelector('.commentInput>li:nth-child(2)');
const commentInput =document.querySelector('#commentsInputData');
const hide1 = 'hide1';



function catchAndShowComments(e){
    e.preventDefault();
    const commentInputData = commentInput.value;
    localStorage.setItem('commentInputData', commentInputData);
    console.log(localStorage.getItem('commentInputData'));
    commentLists.classList.toggle(hide1);
    AddComments();
}
let commentsData = "";
function AddComments(){
    commentsData = commentsData + `<ul class="lists">
    <!-- <li>
            <div>1</div>
        </li> -->
    <li>
      <div>Julie</div>
    </li>
    <li>
      <div class="texts">${localStorage.getItem('commentInputData')}</div>
    </li>
    <li>
      <div>2023-02-16 19시 23분</div>
    </li>
  </ul>`;
    commentsActualData.innerHTML=commentsData;
}


writeComment.addEventListener('click', catchAndShowComments);

