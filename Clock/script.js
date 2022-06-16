const secondScarf = document.getElementById("secondScarf");
const minuteScarf = document.getElementById("minuteScarf");
const hourScarf = document.getElementById("hourScarf");

function getTime() {
  let time = new Date();
  return {
      second: time.getSeconds(),
      minute: time.getMinutes(),
      hour: (time.getHours() >= 12) ? (time.getHours()-12) : time.getHours()
  };
}

function timeTodeg(time){
    return{
        second: time.second * 6,
        minute: time.minute * 6,
        hour: time.hour * 30
    }
}

function applyDeg(deg){
    secondScarf.style.transform = "rotateZ("+ (deg.second - 90) +"deg)";
    minuteScarf.style.transform = "rotateZ("+ ((deg.minute - 90)+(deg.second/60)) +"deg)";
    hourScarf.style.transform = "rotateZ("+ ((deg.hour - 90) + (((deg.minute)+(deg.second/60))/12)) +"deg)";
}


setInterval(function(){
    applyDeg(timeTodeg(getTime()));
},1000);


