CREATE DATABASE LTWeb_Midterm;
GO

USE LTWeb_Midterm;
GO

-- Tạo bảng sách
CREATE TABLE books (
    bookid INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    isbn INT NULL,
    title VARCHAR(200) NULL,
    publisher VARCHAR(100) NULL,
    price DECIMAL(6,2) NULL,
    description TEXT NULL,
    publish_date DATE NULL,
    cover_image VARCHAR(100) NULL,
    quantity INT NULL
);
GO

-- Tạo bảng người dùng
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    email VARCHAR(50) NOT NULL,
    fullname NVARCHAR(50) NULL,
    phone INT NULL,
    passwd VARCHAR(32) NOT NULL,
    signup_date DATETIME NULL,
    last_login DATETIME NULL,
    is_admin BIT NULL
);
GO

-- Tạo bảng tác giả
CREATE TABLE author (
    author_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    author_name VARCHAR(100) NULL,
    date_of_birth DATE NULL
);
GO

-- Tạo bảng liên kết sách và tác giả
CREATE TABLE book_author (
    bookid INT NOT NULL,
    author_id INT NOT NULL,
    PRIMARY KEY (bookid, author_id),
    FOREIGN KEY (bookid) REFERENCES books(bookid) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES author(author_id) ON DELETE CASCADE
);
GO

-- Tạo bảng đánh giá
CREATE TABLE rating (
    userid INT NOT NULL,
    bookid INT NOT NULL,
    rating TINYINT NULL,
    review_text TEXT NULL,
    PRIMARY KEY (userid, bookid),
    FOREIGN KEY (userid) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (bookid) REFERENCES books(bookid) ON DELETE CASCADE
);
GO

-- Dữ liệu mẫu sách (Tiếng Việt không dấu cho cột VARCHAR/TEXT để giữ đúng 100% schema)
INSERT INTO books (isbn, title, publisher, price, description, publish_date, cover_image, quantity) VALUES
(1001, 'Spring Thuc Chien', 'Nha xuat ban Manning', 45.00, 'Hoc framework Spring co ban den nang cao', '2020-01-01', 'Demo.png', 10),
(1002, 'Hibernate Thuc Chien', 'Nha xuat ban Manning', 40.00, 'Cam nang Hibernate ORM toan tap', '2019-05-15', 'Demo.png', 15),
(1003, 'Ma Sach (Clean Code)', 'Nha xuat ban Prentice Hall', 50.00, 'So tay thu cong phan mem linh hoat', '2008-08-01', 'Demo.png', 20),
(1004, 'Mau Thiet Ke (Design Patterns)', 'Nha xuat ban Addison-Wesley', 55.00, 'Cac thanh phan phan mem huong doi tuong tai su dung', '1994-10-31', 'Demo.png', 5),
(1005, 'Lap trinh dong thoi Java', 'Nha xuat ban Addison-Wesley', 45.00, 'Toan tap ve lap trinh dong thoi trong Java', '2006-05-09', 'Demo.png', 8),
(1006, 'Java Hieu Qua (Effective Java)', 'Nha xuat ban Addison-Wesley', 45.00, 'Cac phuong phap thuc hanh tot nhat cho nen tang Java', '2017-12-27', 'Demo.png', 25),
(1007, 'Thiet Ke Mau (Head First)', 'Nha xuat ban O''Reilly', 40.00, 'Huong dan than thien voi nao bo', '2004-10-01', 'Demo.png', 12),
(1008, 'Tai cau truc (Refactoring)', 'Nha xuat ban Addison-Wesley', 50.00, 'Cai thien thiet ke cua ma hien tai', '1999-07-08', 'Demo.png', 18),
(1009, 'Phat trien Huong Kiem Thu (TDD)', 'Nha xuat ban Addison-Wesley', 40.00, 'Phat trien phan mem theo vi du', '2002-11-08', 'Demo.png', 22),
(1010, 'Thiet Ke Huong Ten Mien (DDD)', 'Nha xuat ban Addison-Wesley', 55.00, 'Giai quyet su phuc tap trong trung tam phan mem', '2003-08-30', 'Demo.png', 7),
(1011, 'Mau Kien Truc Ung Dung Doanh Nghiep', 'Nha xuat ban Addison-Wesley', 55.00, 'Cac mau phan mem doanh nghiep', '2002-11-15', 'Demo.png', 9),
(1012, 'Lap trinh vien Thuc Te', 'Nha xuat ban Addison-Wesley', 45.00, 'Hanh trinh dat toi su thong thao', '1999-10-20', 'Demo.png', 30),
(1013, 'Tu duy nhanh va cham', 'Nha xuat ban Tre', 35.00, 'Kham pha 2 he thong tu duy cua con nguoi', '2011-10-25', 'Demo.png', 10),
(1014, 'Dac Nhan Tam', 'Nha xuat ban Tong Hop', 25.00, 'Nghe thuat thu phuc long nguoi', '1936-10-01', 'Demo.png', 50),
(1015, 'Nha Gia Kim', 'Nha xuat ban Hoi Nha Van', 20.00, 'Hanh trinh tim kiem giac mo', '1988-01-01', 'Demo.png', 40),
(1016, 'Loi song toi gian cua nguoi Nhat', 'Nha xuat ban The Gioi', 30.00, 'Giam bot do dac, tang hanh phuc', '2015-06-15', 'Demo.png', 15),
(1017, 'Toi tai gioi, ban cung the', 'Nha xuat ban Phu Nu', 28.00, 'Phuong phap hoc tap hieu qua', '1998-05-20', 'Demo.png', 20),
(1018, 'Nguoi giau co nhat thanh Babylon', 'Nha xuat ban Tre', 22.00, 'Bi quyet lam giau tu co dai', '1926-01-01', 'Demo.png', 25),
(1019, 'Nghe thuat tinh te cua viec dech quan tam', 'Nha xuat ban Dan Tri', 32.00, 'Cach song thuc te hon', '2016-09-13', 'Demo.png', 30),
(1020, 'Luoc su loai nguoi', 'Nha xuat ban Tri Thuc', 45.00, 'Tom tat lich su phat trien loai nguoi', '2011-01-01', 'Demo.png', 18);

GO

-- Dữ liệu mẫu người dùng (Có thể dùng có dấu vì cột fullname là NVARCHAR)
INSERT INTO users (email, fullname, phone, passwd, signup_date, is_admin) VALUES
('admin@iot.vn', N'Quản Trị Viên', 123456789, '123456', GETDATE(), 1),
('user@iot.vn', N'Người Dùng Bình Thường', 987654321, '123456', GETDATE(), 0);
GO

-- Dữ liệu mẫu tác giả
INSERT INTO author (author_name, date_of_birth) VALUES
('Craig Walls', '1970-01-01'),
('Christian Bauer', '1975-01-01'),
('Robert C. Martin', '1952-12-05'),
('Erich Gamma', '1961-03-13'),
('Martin Fowler', '1963-12-18'),
('Kent Beck', '1961-03-31'),
('Joshua Bloch', '1961-08-28'),
('Brian Goetz', '1965-01-01'),
('Steve McConnell', '1962-01-01'),
('Michael Feathers', '1965-01-01'),
('Andrew Hunt', '1964-01-01'),
('David Thomas', '1956-01-01'),
('Eric Evans', '1965-01-01'),
('Rod Johnson', '1970-01-01');

GO

-- Dữ liệu mẫu liên kết sách - tác giả
INSERT INTO book_author (bookid, author_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);
GO

-- Dữ liệu mẫu đánh giá (Tiếng Việt không dấu)
INSERT INTO rating (userid, bookid, rating, review_text) VALUES
(2, 1, 5, 'Sach rat tuyet voi cho nguoi moi hoc Spring.'),
(2, 3, 5, 'Cuon sach bat buoc phai doc cho moi lap trinh vien.'),
(2, 4, 4, 'Hoi kho doc mot chut nhung rat huu ich.');
GO
