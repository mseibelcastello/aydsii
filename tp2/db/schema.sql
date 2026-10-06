-- Esquema de tp2 (derivado de las entidades JPA; ddl-auto=none).
-- Si tenés el dump original de Windows (mysqldump), usalo en lugar de este archivo.
CREATE DATABASE IF NOT EXISTS tp2 CHARACTER SET utf8mb4;
USE tp2;

CREATE TABLE IF NOT EXISTS categorias (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  nombre      VARCHAR(255),
  descripcion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS clientes (
  id             INT AUTO_INCREMENT PRIMARY KEY,
  nombre         VARCHAR(255),
  apellido       VARCHAR(255),
  email          VARCHAR(255),
  telefono       VARCHAR(255),
  fecha_registro DATETIME
);

CREATE TABLE IF NOT EXISTS productos (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  nombre       VARCHAR(255),
  descripcion  VARCHAR(255),
  precio       DECIMAL(12,2),
  stock        INT,
  categoria_id INT,
  FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE IF NOT EXISTS pedidos (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  cliente_id   INT,
  fecha_pedido DATE,
  estado       VARCHAR(255),
  FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS detalle_pedidos (
  id              INT AUTO_INCREMENT PRIMARY KEY,
  pedido_id       INT,
  producto_id     INT,
  cantidad        INT,
  precio_unitario DECIMAL(12,2),
  FOREIGN KEY (pedido_id)   REFERENCES pedidos(id),
  FOREIGN KEY (producto_id) REFERENCES productos(id)
);
