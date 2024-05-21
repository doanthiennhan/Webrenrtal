
    // Toàn bộ mã JavaScript của bạn ở đây
    document.querySelector('.aw__p1vnrcrb').addEventListener('click', function () {
        var query = document.getElementById('searchInput').value.toLowerCase();
        console.log(query);
        var items = document.querySelectorAll('#listings li');
        items.forEach(function (item) {
            item.style.display = '';
        });

        items.forEach(function (item) {
            var content = item.textContent.toLowerCase();
            if (content.includes(query)) {
                item.style.display = '';
            } else {
                item.style.display = 'none';
            }
        });
    });

    var isContentHidden1 = false;
    var isContentHidden = false;

    document.addEventListener('DOMContentLoaded', function () {
        var content = document.getElementById('openn');
        var content1 = document.getElementById('thongbao');
        content.style.display = 'none';
        content1.style.display = 'none';
        isContentHidden = true;
    });

    document.addEventListener('click', function (event) {
        var content = document.getElementById('openn');
        var button = document.getElementById('btnundefinedundefined');
        if (event.target !== content && event.target !== button && !content.contains(event.target)) {
            if (isContentHidden) {
                content.style.display = 'none';
            }
        }
    });

    document.addEventListener('click', function (event) {
        var content = document.getElementById('thongbao');
        var button = document.getElementById('btnundefinedundefined');
        if (event.target !== content && event.target !== button && !content.contains(event.target)) {
            if (isContentHidden1) {
                content.style.display = 'none';
            }
        }
    });

    document.getElementById('btnundefinedundefined').addEventListener('click', function () {
        var content = document.getElementById('openn');
        if (content.style.display === 'none') {
            content.style.display = 'block';
            isContentHidden = false;
        } else {
            content.style.display = 'none';
            isContentHidden = true;
        }
    });

    document.getElementById('btnundefined').addEventListener('click', function () {
        var content = document.getElementById('thongbao');
        if (content.style.display === 'none') {
            content.style.display = 'block';
        } else {
            content.style.display = 'none';
        }
    });



