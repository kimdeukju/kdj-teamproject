const magnifier=document.getElementsByClassName('img');
const leftMenu=document.querySelector('.header .header-con .gnb .main-menu-con>ul>li .li-con .left-menu');
const rightSearchBar=document.querySelector('.header .header-con .gnb .main-menu-con>ul>li .li-con .right-menu')
const searchBar=document.querySelector('#searchBar');


const hide='hide';
const show='show';

function showSearchBar(e) {
    e.preventDefault();
    const searchData=searchBar.value;
    
    
    if(rightSearchBar.classList.contains(hide)){
        rightSearchBar.classList.remove(hide);
        rightSearchBar.classList.add(show);
        leftMenu.classList.add(hide);
        leftMenu.classList.remove(show);
    } else {
        if(searchData !== ""){
            localStorage.setItem('searchData', searchData);
        }
        leftMenu.classList.remove(hide);
        leftMenu.classList.add(show);
        rightSearchBar.classList.remove(show)
        rightSearchBar.classList.add(hide);
    }
}


magnifier[0].addEventListener('click', showSearchBar);
