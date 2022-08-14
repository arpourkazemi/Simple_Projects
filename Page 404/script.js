const body = document.querySelector("body");

// console.log(body);

var x,y;

window.onmousemove = function(e){
    x = e.clientX /10;
    y = e.clientY /10;
    // console.log(x+":"+y);
    body.style.backgroundPositionX = x + "px";
    body.style.backgroundPositionY = y + "px";
}




