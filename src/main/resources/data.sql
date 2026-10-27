-- Insertar Categorías existentes
INSERT INTO category (name) VALUES ('Ficción');
INSERT INTO category (name) VALUES ('Novela');
INSERT INTO category (name) VALUES ('Realismo Mágico');
INSERT INTO category (name) VALUES ('Fantasía');
INSERT INTO category (name) VALUES ('Ciencia Ficción');

-- Insertar Libros
INSERT INTO book (title, author) VALUES ('Cien años de soledad', 'Gabriel García Márquez');
INSERT INTO book (title, author) VALUES ('El amor en los tiempos del cólera', 'Gabriel García Márquez');
INSERT INTO book (title, author) VALUES ('Ficciones', 'Jorge Luis Borges');
INSERT INTO book (title, author) VALUES ('El Aleph', 'Jorge Luis Borges');
INSERT INTO book (title, author) VALUES ('Dune', 'Frank Herbert');

-- Asociar Libros con Categorías (Tabla 'book_categories')
-- Cien años de soledad (id: 1)
INSERT INTO book_categories (book_id, category_name) VALUES (1, 'Novela');
INSERT INTO book_categories (book_id, category_name) VALUES (1, 'Realismo Mágico');

-- El amor en los tiempos del cólera (id: 2)
INSERT INTO book_categories (book_id, category_name) VALUES (2, 'Novela');
INSERT INTO book_categories (book_id, category_name) VALUES (2, 'Ficción');

-- Ficciones (id: 3)
INSERT INTO book_categories (book_id, category_name) VALUES (3, 'Ficción');

-- El Aleph (id: 4)
INSERT INTO book_categories (book_id, category_name) VALUES (4, 'Ficción');
INSERT INTO book_categories (book_id, category_name) VALUES (4, 'Fantasía');

-- Dune (id: 5)
INSERT INTO book_categories (book_id, category_name) VALUES (5, 'Ciencia Ficción');