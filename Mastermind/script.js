const gameBoard = document.querySelector("#gameBoard");
const leaderBoardSection = document.querySelector("#leaderBoardSection");
const gameStatsSection = document.querySelector("#gameStatsSection");
const helpSection = document.querySelector("#helpSection");
const displayPane = document.querySelector("#displayPane");
const selectPane = document.querySelector("#selectPane");
const colorSelect = document.querySelector("#colorSelect");
const checkBtn = document.querySelector("#check");
const gameRows = document.querySelector("#gameRows");
const newGameSection = document.querySelector("#newGame");
const navBarCheckBox = document.querySelector("#navBar > div > input");
const selectPreview = document.querySelector("#selectPreview");
const nextRowSound = document.querySelector("#nextRowSound");
const nextColumnSound = document.querySelector("#nextColumnSound");
const winningSound = document.querySelector("#winningSound");
const loosingSound = document.querySelector("#loosingSound");
const navIcons = Array.from(document.querySelectorAll("#navList > li"));
const closeDiv = document.querySelector("#closeDiv");
let lastBodyTemplate;
let startTime;
let endTime;
let outerDivs;
let rows;
let columns;
let selected = [];
let navBarExpanded = true;
let currentSection = gameBoard;
let lastCellRow = 0;
let lastCellColumn = 0;
let select = document.querySelectorAll(".selectMenu");
colorSelect.style.display = "none";
outerColorSelect.style.pointerEvents = "none";

function addCell(parent, row, column) {
  let outerdiv = document.createElement("DIV");
  outerdiv.classList.add("outerDiv");
  let innerdiv = document.createElement("DIV");
  innerdiv.classList.add("r" + row);
  innerdiv.classList.add("c" + column);
  innerdiv.classList.add("innerDiv");
  innerdiv.dataset.color = "transparent";

  outerdiv.style.gridRow = row + 1;
  outerdiv.style.gridColumn = column + 1;
  outerdiv.appendChild(innerdiv);
  parent.appendChild(outerdiv);
}
function changeColor(element, color) {
  element.style.background = color;
}

closeDiv.addEventListener("click", function () {
  colorSelect.style.display = "none";
  outerColorSelect.style.pointerEvents = "none";
});
select.forEach((select) =>
  select.addEventListener("click", function () {
    if (lastCellRow >= rows) {
      let allFilled = true;
      let emptyElement;
      for (let element of document.querySelectorAll(".c" + lastCellColumn)) {
        if (element.dataset.color === "black") {
          emptyElement = element;
          allFilled = false;
          break;
        }
      }
      if (allFilled) {
        alert("You filled all!Please check your guess");
      } else {
        lastCellRow = emptyElement.classList[0].substring(1);
        document
          .querySelector(".c" + lastCellColumn + ".r" + lastCellRow)
          .setAttribute("data-color", this.dataset.color);
        document
          .querySelector(".r-1" + ".c" + lastCellRow)
          .setAttribute("data-color", this.dataset.color);
        lastCellRow++;
        nextRowSound.play();
      }
    } else {
      document
        .querySelector(".c" + lastCellColumn + ".r" + lastCellRow)
        .setAttribute("data-color", this.dataset.color);
      document
        .querySelector(".r-1" + ".c" + lastCellRow)
        .setAttribute("data-color", this.dataset.color);
      lastCellRow++;
      nextRowSound.play();
    }
  })
);
function startGame(numberOfColors) {
  let colors = [
    "red",
    "lightBlue",
    "green",
    "yellow",
    "orange",
    "purple",
    "pink",
    "darkBlue",
    "gray",
  ];
  selected = [];
  for (let i = 0; i < numberOfColors; i++) {
    selected.push(colors[Math.floor(Math.random() * 9)]);
  }
}
function initGame() {
  startTime = new Date();
  displayPane.innerHTML = "";
  lastCellRow = 0;
  lastCellColumn = 0;
  selectPreview.innerHTML = "";
  rows = parseInt(gameRows.value);
  columns = rows * 2;
  displayPane.style.gridTemplateRows = "repeat(" + rows + ",1fr)";
  displayPane.style.gridTemplateColumns = "repeat(" + columns + ",1fr)";
  displayPane.style.aspectRatio = columns + "/" + rows;
  selectPreview.style.gridTemplateColumns = "repeat(" + rows + ",1fr)";
  selectPreview.style.aspectRatio = rows + "/1";
  startGame(rows);
  newGameSection.style.display = "none";
  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < columns; j++) {
      addCell(displayPane, i, j);
    }
  }
  for (let i = 0; i < rows; i++) {
    addCell(selectPreview, -1, i);
  }
  document.querySelector("#gameStatus").style.display = "none";
  document.querySelector(".pyro").style.display = "none";
  outerDivs = document.querySelectorAll(".outerDiv");
  outerDivs.forEach((outerDiv) => {
    if (
      outerDiv.children[0].classList[0].substring(1) == "-1" ||
      outerDiv.children[0].classList[1].substring(1) == lastCellColumn
    ) {
      outerDiv.children[0].dataset.color = "black";
    }
  });
  outerDivs.forEach((outerDiv) =>
    outerDiv.addEventListener("click", function () {
      if (this.children[0].classList[0].substring(1) == "-1") {
        lastCellRow = this.children[0].classList[1].substring(1);
      } else if (this.children[0].classList[1].substring(1) == lastCellColumn) {
        colorSelect.style.display = "flex";
        outerColorSelect.style.pointerEvents = "";
        lastCellRow = this.children[0].classList[0].substring(1);
      }
    })
  );
}
function checkColors() {
  let cells = document.querySelectorAll(".c" + lastCellColumn);
  for (let i = 0; i < rows; i++) {
    if (cells[i].dataset.color === "black") {
      alert("please fill all cells");
      return;
    }
  }
  let cellColors = [];
  for (let i = 0; i < rows; i++) {
    cellColors.push(cells[i].dataset.color);
  }
  let correct = 0;
  for (let i = 0; i < rows; i++) {
    if (selected.includes(cellColors[i])) {
      cells[i].parentElement.style.background = "#fff";
    }
    if (selected[i] === cellColors[i]) {
      cells[i].parentElement.style.background = "#000";
      correct++;
    }
  }
  checkBtn.style.backgroundColor = "#ffffff99";
  if (correct === rows) {
    document.querySelector("#gameStatus").textContent = "You win!";
    document.querySelector("#gameStatus").style.display = "initial";
    colorSelect.style.display = "none";
    outerColorSelect.style.pointerEvents = "none";
    document.querySelector("#newGame").style.display = "initial";
    document.querySelector(".pyro").style.display = "initial";
    winningSound.play();
    endTime = new Date();
    let timeDiff = endTime - startTime;
    timeDiff /= 1000;
    let seconds = Math.round(timeDiff % 60);
    if (seconds.toString().length == 1) seconds = "0" + seconds;
    timeDiff = Math.floor(timeDiff / 60);
    let minutes = Math.round(timeDiff % 60);
    document.querySelector("#newGameStats").innerHTML =
      "time: " + minutes + ":" + seconds + "<br>moves: " + (lastCellColumn + 1);
    if (document.querySelector("#statMove").textContent === "not recorded") {
      document.querySelector("#statMove").textContent = lastCellColumn + 1;
    } else if (
      parseInt(document.querySelector("#statMove").textContent) >
      lastCellColumn + 1
    ) {
      document.querySelector("#statMove").textContent = lastCellColumn + 1;
    }
    if (document.querySelector("#statTime").textContent === "not recorded") {
      document.querySelector("#statTime").innerHTML =
        '<span id="statTimeMin">' +
        minutes +
        '</span>&nbsp:&nbsp<span id="statTimeSec">' +
        seconds +
        "</span>";
    } else {
      let lastTime =
        parseInt(document.querySelector("#statTimeMin").textContent) * 60 +
        (seconds <=
          parseInt(document.querySelector("#statTimeSec").textContent));
      let currentTime = minutes * 60 + seconds;
      if (currentTime < lastTime) {
        document.querySelector("#statTimeMin").textContent = minutes;
        document.querySelector("#statTimeSec").textContent = seconds;
      }
    }
    return;
  }
  lastCellRow = 0;
  lastCellColumn++;
  outerDivs = document.querySelectorAll(".outerDiv");
  outerDivs.forEach((outerDiv) => {
    if (
      outerDiv.children[0].classList[0].substring(1) == "-1" ||
      outerDiv.children[0].classList[1].substring(1) == lastCellColumn
    ) {
      outerDiv.children[0].dataset.color = "black";
    }
  });
  if (lastCellColumn >= columns) {
    document.querySelector("#gameStatus").textContent = "You lost :(";
    document.querySelector("#gameStatus").style.display = "initial";
    colorSelect.style.display = "none";
    outerColorSelect.style.pointerEvents = "none";
    document.querySelector("#newGame").style.display = "initial";
    loosingSound.play();
    document.querySelector("#newGameStats").innerHTML = "";
    return;
  }
  nextColumnSound.play();
}

function changeNavBarDisplay() {
  if (navBarExpanded) {
    navBarCheckBox.checked = true;
    document.body.style.gridTemplateColumns = "1fr 5rem";
    lastBodyTemplate = "1fr 5rem";
    document.querySelector("#expandIcon").style.display = "inline-block";
    document.querySelector("#collapseIcon").style.display = "none";
    document
      .querySelectorAll("#navList > li > span")
      .forEach((span) => (span.style.display = "none"));
    displayPane.dataset.navstatus = "collapsed";
  } else {
    navBarCheckBox.checked = false;
    document.body.style.gridTemplateColumns = "3fr 1fr";
    lastBodyTemplate = "3fr 1fr";
    document.querySelector("#expandIcon").style.display = "none";
    document.querySelector("#collapseIcon").style.display = "inline-block";
    document
      .querySelectorAll("#navList > li > span")
      .forEach((span) => (span.style.display = "initial"));
    displayPane.dataset.navstatus = "expanded";
  }
  navBarExpanded = !navBarExpanded;
}

navIcons.forEach((navIcon) => {
  navIcon.addEventListener("click", function () {
    document.querySelector(".activeIcon > .iconRegular").style.display =
      "unset";
    document.querySelector(".activeIcon > .iconSolid").style.display = "none";
    document.querySelector(".activeIcon").classList.remove("activeIcon");
    this.classList.add("activeIcon");
    document.querySelector(".activeIcon > .iconRegular").style.display = "none";
    document.querySelector(".activeIcon > .iconSolid").style.display = "unset";
    currentSection.style.display = "none";
    switch (document.querySelector(".activeIcon > .iconSolid").classList[1]) {
      case "fa-gamepad-alt": {
        document.querySelector("#gameBoard").style.display = "grid";
        currentSection = document.querySelector("#gameBoard");
        break;
      }
      case "fa-medal": {
        document.querySelector("#leaderBoardSection").style.display = "flex";
        currentSection = document.querySelector("#leaderBoardSection");
        break;
      }
      case "fa-chart-pie-alt": {
        document.querySelector("#gameStatsSection").style.display = "flex";
        currentSection = document.querySelector("#gameStatsSection");
        break;
      }
      case "fa-book-open": {
        document.querySelector("#helpSection").style.display = "flex";
        currentSection = document.querySelector("#helpSection");
        break;
      }
    }
  });
});

document
  .querySelector("nav > div:first-child")
  .addEventListener("click", changeNavBarDisplay);

document.querySelector("#fillRow").addEventListener("click", function () {
  colorSelect.style.display = "flex";
  outerColorSelect.style.pointerEvents = "";
});

window.onresize = function () {
  if (
    window.getComputedStyle(document.querySelector("#navBar > div:first-child"))
      .display === "none"
  ) {
    if (
      document.body.style.gridTemplateColumns === "3fr 1fr" ||
      document.body.style.gridTemplateColumns === "1fr 5rem"
    ) {
      lastBodyTemplate = document.body.style.gridTemplateColumns;
      document.body.style.gridTemplateColumns = "";
    }
  } else {
    document.body.style.gridTemplateColumns = lastBodyTemplate;
  }
};

function showNewGameSection() {
  newGameSection.style.display = "initial";
}
