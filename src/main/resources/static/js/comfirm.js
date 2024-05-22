function showConfirmDialog() {
    var result = confirm("Bạn có chắc chắn muốn tiếp tục?");
    if (result) {
        console.log("Người dùng đã chọn 'Có'");
        // Thực hiện hành động khi người dùng chọn 'Có'
    } else {
        console.log("Người dùng đã chọn 'Không'");
        // Thực hiện hành động khi người dùng chọn 'Không'
    }
}