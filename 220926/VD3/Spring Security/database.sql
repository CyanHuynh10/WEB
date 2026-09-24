CREATE DATABASE spring_security_db;
GO

USE spring_security_db;
GO

-- Table: roles
CREATE TABLE roles (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(30) NOT NULL
);
GO

-- Table: users
CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(120) NOT NULL UNIQUE,
    password NVARCHAR(150) NOT NULL,
    full_name NVARCHAR(120) NOT NULL,
    enabled BIT NOT NULL DEFAULT 0,
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles(id),
    -- For VD2:
    username NVARCHAR(50) NOT NULL UNIQUE,
    images NVARCHAR(500)
);
GO

-- Table: products
CREATE TABLE products (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(500) NOT NULL,
    description NVARCHAR(500),
    price DECIMAL(18,2) NOT NULL,
    image_url NVARCHAR(1000),
    user_id BIGINT NOT NULL,
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_products_users FOREIGN KEY (user_id) REFERENCES users(id)
);
GO

-- Table: otp_tokens
CREATE TABLE otp_tokens (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(150) NOT NULL,
    otp_hash NVARCHAR(100) NOT NULL,
    type NVARCHAR(30) NOT NULL,
    expires_at DATETIME2 NOT NULL,
    attempts INT NOT NULL DEFAULT 0,
    used BIT NOT NULL DEFAULT 0
);
GO

-- Create indexes
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_otp_email_type ON otp_tokens(email, type);
GO

-- Sample Data: Roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
GO
