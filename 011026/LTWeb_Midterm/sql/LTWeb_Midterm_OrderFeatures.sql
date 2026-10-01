-- LTWeb_Midterm_OrderFeatures.sql
-- Thêm các bảng cho chức năng Đặt hàng

USE LTWeb_Midterm;
GO

-- 1. Bảng orders
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[orders]') AND type in (N'U'))
BEGIN
    CREATE TABLE orders (
        order_id INT IDENTITY(1,1) PRIMARY KEY,
        user_id INT NOT NULL,
        order_date DATETIME NOT NULL DEFAULT GETDATE(),
        total_amount DECIMAL(18,2) NOT NULL,
        payment_method NVARCHAR(50) NOT NULL DEFAULT 'COD',
        status NVARCHAR(50) NOT NULL DEFAULT 'NEW',
        customer_name NVARCHAR(100) NOT NULL,
        phone INT NOT NULL,
        shipping_address NVARCHAR(255) NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(id)
    );
END
GO

-- 2. Bảng order_items
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[order_items]') AND type in (N'U'))
BEGIN
    CREATE TABLE order_items (
        id INT IDENTITY(1,1) PRIMARY KEY,
        order_id INT NOT NULL,
        bookid INT NOT NULL,
        quantity INT NOT NULL,
        unit_price DECIMAL(6,2) NOT NULL,
        subtotal DECIMAL(18,2) NOT NULL,
        FOREIGN KEY (order_id) REFERENCES orders(order_id),
        FOREIGN KEY (bookid) REFERENCES books(bookid)
    );
END
GO

-- CREATE INDEX for faster retrieval
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='IX_Orders_UserId' AND object_id = OBJECT_ID('orders'))
BEGIN
    CREATE INDEX IX_Orders_UserId ON orders(user_id);
END
GO

IF NOT EXISTS (SELECT * FROM sys.indexes WHERE name='IX_OrderItems_OrderId' AND object_id = OBJECT_ID('order_items'))
BEGIN
    CREATE INDEX IX_OrderItems_OrderId ON order_items(order_id);
END
GO

-- TEST THAY ĐỔI TRẠNG THÁI ĐƠN HÀNG
/*
SELECT order_id, user_id, status, order_date, total_amount
FROM orders;

UPDATE orders
SET status = 'NEW'
WHERE order_id = 1;

UPDATE orders
SET status = 'CONFIRMED'
WHERE order_id = 1;

UPDATE orders
SET status = 'PREPARING'
WHERE order_id = 1;

UPDATE orders
SET status = 'SHIPPING'
WHERE order_id = 1;

UPDATE orders
SET status = 'OUT_FOR_DELIVERY'
WHERE order_id = 1;

UPDATE orders
SET status = 'DELIVERED'
WHERE order_id = 1;

UPDATE orders
SET status = 'CANCELLED'
WHERE order_id = 1;

UPDATE orders
SET status = 'RETURNED'
WHERE order_id = 1;
*/
