// Lấy các trường cần kiểm tra
const requiredFields = document.querySelectorAll('.required');

// Hàm hiển thị thông báo lỗi trong vòng 3 giây
function displayErrorMessage() {
    const errorElement = document.createElement('p');
    errorElement.textContent = 'Vui lòng nhập đầy đủ thông tin.';
    errorElement.classList.add('error-message');
    const form = document.querySelector('form');
    form.parentNode.insertBefore(errorElement, form);

    // Loại bỏ thông báo sau 3 giây
    setTimeout(function () {
        errorElement.remove();
    }, 3000);
}

// Hàm kiểm tra các trường và hiển thị thông báo lỗi
function validateForm() {
    let isValid = true;
    requiredFields.forEach(field => {
        if (field.value.trim() === '') {
            isValid = false;
            field.classList.add('error');
        } else {
            field.classList.remove('error');
        }
    });

    if (!isValid) {
        displayErrorMessage();
    }

    return isValid;
}

// Xử lý sự kiện khi người dùng gửi biểu mẫu
const form = document.querySelector('form');
form.addEventListener('submit', function (event) {
    if (!validateForm()) {
        event.preventDefault();
    }
});