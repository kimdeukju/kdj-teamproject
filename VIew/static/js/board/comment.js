const commentColumn = document.getElementsByClassName('comment-column');
const commentLists = document.querySelector('.comment-lists');
const writeComment = document.querySelector('.commentInputCon');
const commentInput =document.querySelector('#commentsInputData');
const hide = 'hide';



function showCommentsLists(e) {
    console.log(e);
    commentLists.classList.toggle(hide);
}

function catchData(e){
    e.preventDefault();
    const commentInputData = commentInput.value;
    localStorage.setItem('commentInputData', commentInputData);
    console.log(localStorage.getItem('commentInputData'));
}

commentColumn[0].addEventListener('click', showCommentsLists);
writeComment.addEventListener('submit', catchData);
