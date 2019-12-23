# JTABLE

## CÁC THAO TÁC CƠ BẢN

### Tạo bảng có kích thước xác định
    Source code: https://github.com/nam-long/learning-java/tree/master/src/learning/swing/table/basic/fixedrows
    
    Nhược điểm:
    - Khó thay đổi kích thước của bảng dữ liệu (thay đổi số dòng hoặc số cột của bảng).
    - Chi phí cài đặt cao (số lượng code nhiều).
    
### Tạo bảng có kích thước linh động
    Source code: https://github.com/nam-long/learning-java/tree/master/src/learning/swing/table/basic/autorows
    
    Sử dụng các lớp cài đặt của interface java.util.List như Vector, ArrayList,...
    
    Ưu điểm:
    - Số dòng của bảng có thể tăng giảm dễ dàng.
    - Chi phí cài đặt thấp.
    
### Xử lý sự kiện trên bảng
    Source code: https://github.com/nam-long/learning-java/tree/master/src/learning/swing/table/basic/events
    
    Hướng dẫn cách xác định chỉ số dòng (hoặc cột) khi xảy ra sự kiện Mouse Clicked trên bảng.
    
    Hướng dẫn:
    - Tạo một JDialog tùy biến với JPanel để nhập dữ liệu.
    - Đưa dữ liệu bên ngoài vào JDialog.
    - Lấy dữ liệu trên JDialog sau khi đã nhập xong (chọn OK), sau đó cập nhật vào bảng.
    
### Di chuyển dữ liệu giữa hai bảng dữ liệu
    Source code: https://github.com/nam-long/learning-java/tree/master/src/learning/swing/table/customized/movingdata
    
