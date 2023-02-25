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