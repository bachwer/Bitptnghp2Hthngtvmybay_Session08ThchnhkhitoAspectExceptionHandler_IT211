1. Vài trò của @Around
- Aspect @Around cho phép can thiệp Trước và sau khi method Chạy
- Có thể chỉnh sửa trực tiệp Object[] args
- Sau khi sửa tham số phỉa gọi:
- JoinPoint.proceed(args) để thực thi method với tham số đã sửa
- Nếu không gọi proceed() thì method sẽ không chạy

VD:

"john Doe"
-> trim() -> "john Doe"
-> toUpperCase() -> "JOHN DOE"


2. @Around có thể thay đổi giá trị trả về
- Sau khi gọi proceed() có thể chỉnh sửa giá trị trả về trước khi trả về cho caller
- Điều này cho phép thay đổi hành vi của method mà không cần sửa code gốc
- VD: Nếu method trả về "Hello John" có thể sửa thành "Hi John" trước khi trả về cho caller
- Điều này rất hữu ích khi muốn thêm logic bổ sung hoặc thay đổi kết quả mà không cần sửa code gốc của


 3. Vai trò của @AfterThrowing

- Tập trung xử lý logging lỗi.

- Không cần try-catch ở mọi Service.

- Khi lỗi xảy ra:

  -> lưu ErrorLog vào DB

  -> để exception tiếp tục throw ra ngoài.

4. Ưu điểm của AOP trong bài toán

- Giảm duplicate code.

- Tách biệt business logic và cross-cutting concerns.

- Dễ bảo trì và mở rộng hệ thống Microservice.