const magnifier=document.querySelector('.s-img');
const leftMenu=document.querySelector('.header .header-con .gnb .main-menu-con>ul>li .li-con .left-menu');
const rightSearchBar=document.querySelector('.header .header-con .gnb .main-menu-con>ul>li .li-con .right-menu')
const hide='hide';
const show='show';

function showSearchBar(e) {
    e.preventDefault();

    console.dir(e);

    if(rightSearchBar.classList.contains(hide)){
        rightSearchBar.classList.remove(hide);
        rightSearchBar.classList.add(show);
        leftMenu.classList.add(hide);
        leftMenu.classList.remove(show);
    } else {
        leftMenu.classList.remove(hide);
        leftMenu.classList.add(show);
        rightSearchBar.classList.remove(show)
        rightSearchBar.classList.add(hide);
    }
}


magnifier.addEventListener('click', showSearchBar);
