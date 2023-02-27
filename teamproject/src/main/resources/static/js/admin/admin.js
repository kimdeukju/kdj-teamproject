

// 현재 시간 보여주기
const currentTime = document.querySelector('.currentTime');


showCurrentTime();
function showCurrentTime(){
    const current = new Date();

    let hours = current.getHours().toString().padStart(2, '0');
    let mins = current.getMinutes().toString().padStart(2, '0');
    let secs = current.getSeconds().toString().padStart(2, '0');

    currentTime.innerHTML = `${hours}:${mins}:${secs}`;
}

(()=>{
    setInterval(showCurrentTime, 1000);
})()


// 왼쪽 메뉴 버튼 클릭시 메뉴 옵션들 toggle 
const leftMenuBtns = document.querySelectorAll('.leftNavBarCon .leftNavBarCon-lists>*>*:nth-child(1)');
const hideLeftNavContents = 'hideLeftNavContents';

function showLeftMenuOptionsLeftBtns(e){  
    let leftMenuOption = e.target.nextElementSibling;
    leftMenuOption.classList.toggle(hideLeftNavContents);
}

leftMenuBtns.forEach((el)=>{
    el.addEventListener('click', showLeftMenuOptionsLeftBtns);
});

// 상단 메뉴 버튼 클릭 시, 왼쪽 메뉴 옵션들 toggle
const topMenuBtns = document.querySelectorAll('.li-con .left-menu ul li');
const leftMenuOptionsList = document.querySelectorAll('.leftNavBarCon-lists>*>*:nth-child(2)');
function showLeftMenuOptionsTopBtns(i){
    const topBtn = i.currentTarget;
    let previousSelectedElement = '';
    previousSelectedElement += topBtn;

    const index = Array.prototype.indexOf.call(topMenuBtns, topBtn);
    // console.log(topBtn.previousElementSibling);


    if(topBtn == topMenuBtns[index]){
        console.dir(`This is topBtn : ${topBtn}`);
        console.log(`This index is ${index}`);
    }  else {

    }
    leftMenuOptionsList[index].classList.toggle(hideLeftNavContents);
};   


topMenuBtns.forEach((el)=>{
    el.addEventListener('click', showLeftMenuOptionsTopBtns);
})