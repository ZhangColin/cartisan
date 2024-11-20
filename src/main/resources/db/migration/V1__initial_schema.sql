-- 创建用户表
CREATE TABLE users
(
    id    INT AUTO_INCREMENT PRIMARY KEY, -- 主键，自增
    name  VARCHAR(255) NOT NULL,          -- 用户名，不允许为空
    email VARCHAR(255) NOT NULL UNIQUE    -- 邮箱，不允许为空且唯一
);