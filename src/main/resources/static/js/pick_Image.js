var selectElement = document.getElementById('addressSelect');
selectElement.addEventListener('change', function () {
    var selectedOption = selectElement.options[selectElement.selectedIndex].value;
    document.getElementById('district').value = selectedOption;
});


function openFileExplorer() {
    document.getElementById('fileInput').click();
}

document.getElementById('fileInput').addEventListener('change', function (event) {
    var files = event.target.files; // Lấy danh sách các file ảnh từ máy chọn
    var imageContainer = document.getElementById('imageContainer');
    imageContainer.innerHTML = ''; // Xóa nội dung hiện tại của container

    for (var i = 0; i < files.length; i++) {
        var file = files[i]; // Lấy mỗi file ảnh
        var reader = new FileReader(); // Tạo một đối tượng FileReader

        reader.onload = (function (file) {
            return function (e) {
                // Khi FileReader đã đọc file thành công
                var wrapper = document.createElement('div');
                wrapper.className = 'image-wrapper';

                var image = new Image(); // Tạo một phần tử hình ảnh mới
                image.src = e.target.result; // Gán đường dẫn của ảnh cho phần tử hình ảnh

                var deleteButton = document.createElement('button');
                deleteButton.className = 'delete-button';
                deleteButton.innerHTML = '&times;';
                deleteButton.onclick = function () {
                    wrapper.remove(); // Xóa phần tử hình ảnh khỏi container
                    updateImageCount(); // Cập nhật số lượng ảnh
                };

                wrapper.appendChild(image);
                wrapper.appendChild(deleteButton);
                imageContainer.appendChild(wrapper);

                updateImageCount(); // Cập nhật số lượng ảnh
            };
        })(file);

        // Đọc file ảnh
        reader.readAsDataURL(file);
    }
});

function updateImageCount() {
    var count = document.querySelectorAll('#imageContainer .image-wrapper').length;
    document.getElementById('imageCount').innerText = 'Number of images: ' + count;
}
