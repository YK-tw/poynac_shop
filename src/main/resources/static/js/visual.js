//lang dropdown
function dropdownFunction(id) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show') && openDropdown !== document.getElementById(id)) {
            openDropdown.classList.remove('show');
        }
    }
    // document.getElementById("dropdownSearch").style.display = 'none';
    document.getElementById(id).classList.toggle("show");
}

function searchShowFunction(id) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }
    var obj = document.getElementById(id);
    if (obj.style.display == "block") {
        obj.style.display = "none";
    } else {
        obj.style.display = "block";
    }
}

window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}
//lang dropdown

//carousel

$('#bigCarousel').carousel({
    interval: 6000
})
$('#smallCarousel').carousel({
    interval: 3000
})

$('.carousel__small .carousel-item').each(function () {
    var next = $(this).next();
    if (!next.length) {
        next = $(this).siblings(':first');
    }
    next.children(':first-child').clone().appendTo($(this));

    for (var i = 0; i < 2; i++) {
        next = next.next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }

        next.children(':first-child').clone().appendTo($(this));
    }
});

$('.carousel__big .carousel-item').each(function () {
    var next = $(this).next();
    if (!next.length) {
        next = $(this).siblings(':first');
    }
    next.children(':first-child').clone().appendTo($(this));

    for (var i = 0; i < 1; i++) {
        next = next.next();
        if (!next.length) {
            next = $(this).siblings(':first');
        }

        next.children(':first-child').clone().appendTo($(this));
    }
});

//carousel

// widgets open/close

const widgets = document.querySelectorAll('.widget');
widgets.forEach(function (widget) {
    widget.addEventListener('click', function (e) {
        if (e.target.classList.contains('widget__title')) {
            e.target.classList.toggle('widget_title_active');
            e.target.nextElementSibling.classList.toggle('widget_body_hidden');
        }
    });
});

// widgets open/close

//card image change on hover

const cardImages = document.querySelectorAll('.card__link');
cardImages.forEach(function (image) {
    image.onmouseenter = function () {
        var imgs = image.getElementsByTagName('img');
        for (var i = 0; i < imgs.length; i++) {
            imgs[i].classList.toggle('card_img_hidden');
        }
    }
    image.onmouseleave = function () {
        var imgs = image.getElementsByTagName('img');
        for (var i = 0; i < imgs.length; i++) {
            imgs[i].classList.toggle('card_img_hidden');
        }
    }
});

//card image change on hover

//image gallery expand
function imageExpand(imgs) {
    var expandImg = document.getElementById("expandedImg");
    expandImg.src = imgs.src;
    expandImg.parentElement.style.display = "block";
}

//image gallery expand

//amount counter +-

function addProduct(i) {
    var counter = document.getElementsByClassName("amount__input")[i];
    if (counter.value < 100) {
        counter.value++;
    }
}

function divideProduct(i) {
    var counter = document.getElementsByClassName("amount__input")[i];
    if (counter.value > 1) {
        counter.value--;
    }
}

//amount counter +-
