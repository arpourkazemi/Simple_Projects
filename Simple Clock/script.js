const weekDays = ["Sunday", "Monday", "Thusday", "Wednesday", "Thursday", "Friday", "Saturday"];
const monthsName = ["January","February","March","April","May","June","July","August","September","October","November","December"];

function getTime(){
    let date = new Date();
    document.getElementById("hour").innerHTML = (date.getHours().toString().length == 1) ? ("0" + date.getHours()):(date.getHours());
    document.getElementById("minute").innerHTML = (date.getMinutes().toString().length == 1) ? ("0" + date.getMinutes()):(date.getMinutes());
    document.getElementById("second").innerHTML = (date.getSeconds().toString().length == 1) ? ("0" + date.getSeconds()):(date.getSeconds());
    document.getElementById("year").innerHTML = date.getFullYear();
    document.getElementById("month").innerHTML = ((date.getMonth()+1).toString().length == 1) ? ("0" +(date.getMonth()+1)):((date.getMonth()+1));
    document.getElementById("date").innerHTML = (date.getDate().toString().length == 1) ? ("0" + date.getDate()) : (date.getDate());
    document.getElementById("day").innerHTML = weekDays[date.getDay()];
    document.getElementById("monthName").innerHTML = monthsName[date.getMonth()];
}

setInterval(getTime,1000);

function changeColor(){
    let color = getComputedStyle(document.getElementById("colon")).color;
    let colons = Array.from(document.getElementsByClassName("colon"));
    if(color == "rgb(255, 255, 0)"){
        for(n in colons) document.getElementsByClassName("colon")[n].style.color = "rgb(0, 0, 0)";
    } else{
        for(n in colons) document.getElementsByClassName("colon")[n].style.color = "rgb(255, 255, 0)";
    }
}

setInterval(changeColor,500);

function powerButton(){
    document.getElementsByClassName("button")[0].classList.toggle("on");
    document.getElementsByClassName("wheel")[0].classList.toggle("on");
    checkDisplay();
}

function checkDisplay(){
    if(document.getElementsByClassName("button")[0].classList.contains("on")){
        document.getElementsByTagName("body")[0].style.backgroundColor = "rgb(0, 0, 0)";
        document.getElementsByClassName("clock")[0].style.visibility = "visible";
    } else{
        document.getElementsByTagName("body")[0].style.backgroundColor = "rgb(255, 255, 0)";
        document.getElementsByClassName("clock")[0].style.visibility = "hidden";
    }
}