const showCommentsBtn = document.querySelector('.btnli2 input:nth-child(1)');
// comments의 리스트를 가져와야 하므로, comment.js에 있는 commentsLists를 가져온다.

function showComments(e){
    e.preventDefault();
// hide1의 css 효과도 comment html에 연결된 css 폴더에 있다.
    commentLists.classList.toggle(hide1);
};



showCommentsBtn.addEventListener('click', showComments);
