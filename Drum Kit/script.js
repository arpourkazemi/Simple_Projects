window.addEventListener('keydown',function(e){
    play(e.key);
});

function play(key){
    let button = document.querySelector("button["+"key='" + key + "']");
    let audio = document.querySelector("audio["+"key='" + key + "']");
    if(!button || !audio) return;
    audio.currentTime = 0;
    audio.play();
    button.classList.add("active");
    setTimeout(function(){
        button.classList.remove("active");
    },70);
}