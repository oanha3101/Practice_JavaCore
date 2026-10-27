# Practice JavaCore - Tổng hợp bài tập thực hành Java OOP

Repository chứa mã nguồn và tài liệu thiết kế của 3 bài tập thực hành Java Core (Lập trình hướng đối tượng - OOP).

---

## Danh mục bài tập (Table of Contents)

1. [Practice 1 – Hình học cơ bản (Shape, Rectangle, Circle)](#1-practice-1--hình-học-cơ-bản-shape-rectangle-circle)
2. [Practice 2 – Quản lý kho hàng siêu thị (Supermarket Inventory)](#2-practice-2--quản-lý-kho-hàng-siêu-thị-supermarket-inventory)
3. [Practice 3 – Quản lý phương tiện giao thông (Police Vehicle Management)](#3-practice-3--quản-lý-phương-tiện-giao-thông-police-vehicle-management)

---

## 1. Practice 1 – Hình học cơ bản (Shape, Rectangle, Circle)

### Mục tiêu
- Làm quen với cú pháp Java và nguyên lý kế thừa (`extends`).
- Sử dụng hàm dựng lớp cha (`super(...)`), ghi đè phương thức (`@Override`).

### Cấu trúc mã nguồn
- `Shape.java`: Lớp cơ sở chứa thuộc tính `width`, `height`.
- `Rectangle.java`: Kế thừa `Shape`, tính diện tích (`getArea()`) và chu vi (`getPerimeter()`).
- `Circle.java`: Kế thừa `Shape` (`width = height = diameter`), tính bán kính, diện tích và chu vi.
- `Main.java`: Khởi tạo đối tượng và hiển thị thông tin.

### Biên dịch và chạy
```bash
cd Practice1
javac *.java
java Main
```

---

## 2. Practice 2 – Quản lý kho hàng siêu thị (Supermarket Inventory)

### Mục tiêu
- Phân tích và thiết kế hướng đối tượng: Lớp trừu tượng (`abstract class`), đa hình (`Polymorphism`).
- Đóng gói (`Encapsulation`), kiểm soát tính hợp lệ dữ liệu ngay trong constructor.
- Quản lý danh sách hàng hóa bằng **mảng thuần** `Goods[]` có khả năng tự động mở rộng sức chứa.

### Các loại hàng hóa
- **Thực phẩm (`Food`):** VAT 5%, ràng buộc ngày hết hạn $\ge$ ngày sản xuất. Đánh giá *"Hard to sell"* nếu còn tồn kho và đã hết hạn.
- **Điện máy (`Electronics`):** VAT 10%, bảo hành & công suất $\ge 0$. Đánh giá *"Easy to sell"* nếu tồn kho $< 3$.
- **Gốm sứ (`Crockery`):** VAT 10%, ngày nhập kho. Đánh giá *"Slow sale"* nếu tồn kho $> 50$ và lưu kho $> 10$ ngày.

### Tài liệu thiết kế
- Xem chi tiết tại [`Practice2/DESIGN.md`](Practice2/DESIGN.md).

### Biên dịch và chạy
```bash
cd Practice2
javac *.java
# Chạy chế độ demo tự động kiểm thử
java Main --demo

# Hoặc chạy menu tương tác
java Main
```

---

## 3. Practice 3 – Quản lý phương tiện giao thông (Police Vehicle Management)

### Mục tiêu
- Hệ thống quản lý phương tiện giao thông phục vụ công an.
- Mối quan hệ đa dạng: Kế thừa (`Inheritance`), liên kết (`Association` với `Owner`), gom nhóm (`Aggregation` với `List<Vehicle>`).
- Ràng buộc dữ liệu chặt chẽ (Validation):
  - Biển số xe đúng 5 ký tự, không trùng lặp.
  - Hãng sản xuất chỉ chấp nhận: `Honda`, `Yamaha`, `Toyota`, `Suzuki`.
  - Năm sản xuất: $2000 < \text{năm} \le \text{năm hiện tại}$.
  - CMND chủ xe đúng 12 chữ số, email đúng định dạng regex.
  - Tự động nhận diện và tái sử dụng chủ xe cũ nếu trùng CMND.

### 7 Chức năng nghiệp vụ
1. Thêm phương tiện mới.
2. Tìm phương tiện theo biển số xe.
3. Tìm phương tiện theo CMND chủ xe.
4. Xóa tất cả phương tiện của một hãng sản xuất.
5. Tìm hãng có nhiều xe nhất.
6. Sắp xếp phương tiện theo số lượng phương tiện giảm dần theo hãng.
7. Thống kê số lượng theo từng loại phương tiện (`Car`, `Motorcycle`, `Truck`).

### Tài liệu thiết kế
- Xem chi tiết tại [`Practice3/DESIGN.md`](Practice3/DESIGN.md).

### Biên dịch và chạy
```bash
cd Practice3
javac *.java
# Chạy chế độ demo tự động kiểm thử
java Main --demo

# Hoặc chạy menu tương tác
java Main
```

---

## Môi trường yêu cầu
- Java SE Development Kit (JDK) $\ge 17$ (khuyến nghị OpenJDK 17 hoặc 21 LTS).
