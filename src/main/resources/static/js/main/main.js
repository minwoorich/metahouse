let test = document.querySelector("#color-test");
function next(el) {
    let list = el.parentNode.querySelector(".banner-slider-list");

    let listSize = list.offsetWidth;
    let itemSize = list.firstElementChild.offsetWidth;
    let countOfView = parseInt(listSize / itemSize);



    let index = Number(list.getAttribute("index"));
    let itemCount = list.childElementCount;

    if (index + countOfView >= itemCount) {
        return;
    }
    index++;
    list.setAttribute("index", index);


    list.style.left = -itemSize * index + "px";

    colorChange(index);
}

function pre(el) {
    let list = el.parentNode.querySelector(".banner-slider-list");

    let listSize = list.offsetWidth;
    let itemSize = list.firstElementChild.offsetWidth;
    let countOfView = parseInt(listSize / itemSize);



    let index = Number(list.getAttribute("index"));
    let itemCount = list.childElementCount;

    if (index <= 0) {
        list.style.left = 0;
        list.setAttribute("index", 0);
        return;
    }
    index--;
    list.setAttribute("index", index);

    list.style.left = -itemSize * index + "px";

    colorChange(index);
}

function colorChange(index) {

    switch (index) {
        case 0:
            test.style.backgroundColor = "#6C4AB6";
            break;
        case 1:
            test.style.backgroundColor = "black";
            break;
        default:
            test.style.backgroundColor = "black";
            break;
    }
}



document.addEventListener('DOMContentLoaded', function() {
    var leftHalf = document.querySelector('.left-half');
    var rightHalf = document.querySelector('.right-half');

    leftHalf.addEventListener('click', function() {
      window.location.href = leftHalf.href;
    });

    rightHalf.addEventListener('click', function() {
      window.location.href = rightHalf.href;
    });
  });


