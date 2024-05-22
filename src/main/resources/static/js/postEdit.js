function openFileExplorer() {
    // Giả sử `listingImages` là một mảng chứa tên các tệp ảnh từ `listing.image[]`.
    // Bạn cần thay thế mảng này bằng dữ liệu thực tế từ controller của bạn.
    var listingImages = ["image1.jpg", "image2.jpg", "image3.jpg"]; // Thay thế bằng mảng thực tế

    var imageContainer = document.getElementById('imageContainer');
    imageContainer.innerHTML = ''; // Xóa các hình ảnh hiện có

    // Lặp qua danh sách tên tệp ảnh và hiển thị chúng trên trang
    for (var i = 0; i < listingImages.length; i++) {
        var imageUrl = "src/main/resources/static/image/" + listingImages[i];

        // Tạo phần tử wrapper cho hình ảnh
        var wrapper = document.createElement('div');
        wrapper.className = 'image-wrapper';

        // Tạo phần tử hình ảnh
        var image = new Image();
        image.src = imageUrl;

        // Tạo nút xóa
        var deleteButton = document.createElement('button');
        deleteButton.className = 'delete-button';
        deleteButton.innerHTML = '&times;';
        deleteButton.onclick = function () {
            wrapper.remove(); // Xóa phần tử wrapper của hình ảnh
            updateImageCount(); // Cập nhật số lượng ảnh
        };

        // Thêm hình ảnh và nút xóa vào wrapper
        wrapper.appendChild(image);
        wrapper.appendChild(deleteButton);

        // Thêm wrapper vào container hình ảnh
        imageContainer.appendChild(wrapper);
    }

    updateImageCount(); // Cập nhật số lượng ảnh
}
