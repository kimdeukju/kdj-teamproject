const title = document.querySelector('.boardlist>ul>li:nth-child(2)');
const detail = document.querySelector('.detailContainer');
const lists = document.querySelector('.listsContainer-con');
const backFromDetailToLists = document.querySelector('.btnli2>input:nth-child(2)');
const hide = 'hide';


// 게시글 제목 클릭 시, 게시글 상세 내용 보여주기 vice versa
function showDetail(e){
    e.preventDefault();
    detail.classList.toggle(hide);
    lists.classList.toggle(hide);
}
function showLists(e){
    e.preventDefault();
    detail.classList.toggle(hide);
    lists.classList.toggle(hide);
}


title.addEventListener('click', showDetail);
backFromDetailToLists.addEventListener('click', showLists);


