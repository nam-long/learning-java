# DEMO APP

Hướng dẫn cách quản lý các màn hình trong một ứng dụng đơn giản bằng Card Layout.

### Khai báo và Tạo một màn hình mới
    1. Khai báo tên màn hình mới trong enum State.
    2. Tạo một class để thể hiện màn hình này. 
    Lưu ý: lớp này phải là lớp con (sub-class) của lớp trừu tượng (abstract class) 
    Pane. Lớp này phải cài đặt ít nhất hai phương thức:
        - onPaneOpened(): được gọi khi màn hình này được hiển thị.
        - onPaneClosed(): được gọi khi màn hình bị đóng lại.
    
### Sử dụng StateManager để quản lý các màn hình trong một ứng dụng

#### Đăng ký với StateManager
    Các màn hình trong ứng dụng, sau khi khai báo và tạo, cần phải được đăng ký
    với StateManager bằng cách gọi phương thức StateManager.getInstance().add()

#### Cách chuyển qua màn hình khác
    Khi cần chuyển sang màn hình khác, gọi phương thức StateManager.getInstance.show().
    Trong quá trình này sẽ có hai phương thức được gọi:
    - onPaneClosed(): là phương thức của màn hình hiện tại sau khi màn hình này bị đóng.
    - onPaneOpened(): là phương thức của màn hình sẽ được hiển thị ngay sau đó.

### Ví dụ
Giải thích một số màn hình có sẵn trong Demo App

#### Intro

    Tên khai báo: State.INTRO

    Lớp thể hiện: IntroState

    Là màn hình xuất hiện đầu tiên khi ứng dụng được mở, thời gian xuất hiện khoảng 3 giây,
    sau đó chuyển sang màn hình MAIN.

    Có thể thực thi một số khởi tạo có chi phí thấp tại đây.

#### MAIN

    Tên khai báo: State.MAIN

    Lớp thể hiện: MainState
    
    Là menu chính của Demo App, từ đây có thể điều hướng đến các màn hình khác trong ứng dụng.
    
#### ACTION

    Tên khai báo: State.ACTION

    Lớp thể hiện: ActionState
    
    Đây là màn hình chơi chính của một ứng dụng game.
   
### Lưu ý

##### onPaneClosed()
    Nên được cài đặt cho việc giải phóng tài nguyên không cần dùng
    nữa, lưu trữ dữ liệu,...
    
##### onPaneOpened()
    Nên được cài đặt cho việc khởi tạo các tính năng cần dùng trong
    màn hình sắp hiển thị, đọc dữ liệu đã được lưu trữ,...
    