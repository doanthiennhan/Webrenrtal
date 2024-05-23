/*<![CDATA[*/

/*]]>*/

let notifications = document.querySelector('.notifications');

function createToast(type, icon, title, text) {
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
        newToast.timeOut = setTimeout(() => newToast.remove(), 1500);
    }
}

// Kiểm tra nếu có thông báo từ backend
window.onload = function () {
    let sucsess = document.getElementById('success').value;
    let fail = document.getElementById('fail').value;
    let type = document.getElementById('type').value
    if (sucsess) {
        if (type === 'success') {
        console.log(sucsess);
        console.log("đã thông báo");
        createToast('success', 'fa-solid fa-circle-check', 'Success', sucsess);
        //  document.getElementById('hiddenMessage').value = '';
        sucsess = '';
        }

    }
    if (fail) {
        if (type === 'fail') {
            console.log(sucsess);
            console.log("đã thông báo");
            createToast('error', 'fa-solid fa-circle-check', 'error', fail);
            //  document.getElementById('hiddenMessage').value = '';
            fail = '';
        }

    }

}