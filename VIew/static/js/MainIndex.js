const backwardBtn=document.querySelector('.change-controller .changebuttons>div button:nth-child(1) #t1');
const playBtn=document.querySelector('.change-controller .changebuttons>div button:nth-child(2)');
const forwardBtn=document.querySelector('.change-controller .changebuttons>div button:nth-child(3)');
const imageContainer=document.querySelector('.main-con .imageChange .imageChange-con>li>a');

function toBackward(){
    // e.preventDefault();

    console.log('This button is working');
    // <img src="Main/main1.jpg" alt="main1"></img>
    let img=document.createElement('img');
    img.src='"Main/main1.jpg"';
    imageContainer.appendChild(img);

}




backwardBtn.addEventListener('click', toBackward);
// playBtn.addEventListener('click', showInfo);
// forwardBtn.addEventListener('click', toForward);