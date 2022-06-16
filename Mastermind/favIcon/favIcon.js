let favIconNumber = 2;
setInterval(changeFavIcon,1000);

function changeFavIcon(){
    document.querySelector('#favIcon').setAttribute('href','favIcon/' + favIconNumber +'.png');
    document.querySelector('#logo').setAttribute('src','favIcon/' + favIconNumber +'.png');
    favIconNumber++;
    if(favIconNumber > 9) favIconNumber = 1;
}