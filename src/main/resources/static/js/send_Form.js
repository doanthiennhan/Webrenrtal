// Lấy các trường cần kiểm tra
const requiredFields = document.querySelectorAll('.required');

// Hàm hiển thị thông báo lỗi trong vòng 3 giây
function displayErrorMessage(type, icon, title, text) {
    if (text !== 'null') {
        let newToast = document.createElement('div');
        newToast.innerHTML = `
             <div class="toast ${type}">
                 <i class="${icon}"></i>
                 <div class="content">
                     <div class="title">${title}</div>
                     <span>${text}</span>
                 </div>
                 <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
             </div>`;
        notifications.appendChild(newToast);
        console.log("đã thông báo");
        newToast.timeOut = setTimeout(() => newToast.remove(), 3000);
    }
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
        displayErrorMessage('warning','fa-solid fa-triangle-exclamation','Warning','vui lòng nhập đầy đủ thông tin');
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