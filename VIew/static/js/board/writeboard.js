const lists1 = document.querySelector('.listsContainer-con');
const writeBoard = document.querySelector('.listsContainer-end .end-item>a');
const write = document.querySelector('.writeContainer');
const backFromWriteToLists = document.querySelector('.li4>a');

// 게시글 작성 페이지로 이동

function showWrite(e){
    e.preventDefault();
    write.classList.toggle(hide);
    lists1.classList.toggle(hide);
    
}
function showLists1(e){
    e.preventDefault();
    write.classList.toggle(hide);
    lists1.classList.toggle(hide);
}


writeBoard.addEventListener('click', showWrite);
backFromWriteToLists.addEventListener('click', showLists1);
