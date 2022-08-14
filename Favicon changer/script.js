function toggleFavIcon(button) {
    if (!button.classList.contains("active")) {
        document.getElementById("green").classList.toggle("active");
        document.getElementById("red").classList.toggle("active");
        let favIcon = document.getElementById("favIcon");
        if (button.id == ("red")) favIcon.href = "img/favicon.png";
        else favIcon.href = "img/faviconTwo.png";
    }
}

// function FavIcon() {
//     document.getElementById("green").classList.toggle("active");
//     document.getElementById("red").classList.toggle("active");
//     let favIcon = document.getElementById("favIcon");
//     let favHref = favIcon.href;
//     if (favHref == window.location.origin+"/img/faviconTwo.png") favIcon.href = "/img/favicon.png";
//     else favIcon.href = window.location.origin+"/img/faviconTwo.png";
// }


// setInterval(FavIcon,100);
