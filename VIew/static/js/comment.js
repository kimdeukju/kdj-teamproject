const commentColumn = document.getElementsByClassName('comment-column');
const commentLists = document.querySelector('.comment-lists');
const commentCon = document.getElementsByClassName('comment');
const hide = 'hide';



function showCommentsLists(e) {
    commentLists.classList.toggle(hide);
}


// commentColumn[0].addEventListener('click', showCommentsLists);
commentCon[0].addEventListener('click', showCommentsLists);