-- Create the paris_library1 database
CREATE DATABASE paris_library1;

-- Switch to the paris_library1 database
USE paris_library1;

-- Create the authors table
CREATE TABLE authors (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fullname VARCHAR(30),
  email VARCHAR(30)
);

-- Create the books table
CREATE TABLE books (
  id INT PRIMARY KEY AUTO_INCREMENT,
  isbn VARCHAR(13) NOT NULL,
  title VARCHAR(50) NOT NULL,
  quantity INT NOT NULL,
  author_id INT,
  FOREIGN KEY (author_id) REFERENCES authors (id)
);

-- Create the users table
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fullname VARCHAR(30) NOT NULL,
  email VARCHAR(30) NOT NULL,
  password VARCHAR(30) NOT NULL,
  role ENUM('borrower', 'admin') NOT NULL,
  telephone VARCHAR(30),
  UNIQUE KEY unique_email (email)
);

-- Create the borrowedbooks table
CREATE TABLE borrowedbooks (
  borrower_id INT,
  book_id INT,
  borrowing_date DATE,
  return_date DATE,
  status ENUM('perdu', 'emprunté', 'retourné'),
  FOREIGN KEY (borrower_id) REFERENCES users(id),
  FOREIGN KEY (book_id) REFERENCES books (id)
);

INSERT INTO users (fullname, email, password, role, telephone) VALUES
('ayoub', 'ayoub@ayoub', '1234', 'admin', NULL);
INSERT INTO users (fullname, email, password, role, telephone) VALUES
('ayoub1', 'ayoub1@ayoub', '1234', 'admin', NULL);
INSERT INTO users (fullname, email, password, role, telephone) VALUES
('ayoub2', 'ayoub2@ayoub', '1234', 'borrower', NULL);


INSERT INTO authors (fullname, email) VALUES
('Author 1', 'author1@email.com'),
('Author 2', 'author2@email.com'),
('Author 3', 'author3@email.com');

INSERT INTO books (isbn, title, quantity, author_id) VALUES
('978-1234567890', 'Book 1', 10, 1),
('978-2345678901', 'Book 2', 15, 2),
('978-3456789012', 'Book 3', 8, 3);

INSERT INTO borrowedbooks (borrower_id, book_id, borrowing_date, return_date, status) VALUES
(2, 1, '2023-08-01', '2023-08-15', 'emprunté'),
(3, 2, '2023-08-05', '2023-08-20', 'emprunté'),
(2, 3, '2023-08-10', '2023-08-25', 'emprunté');

DELIMITER $$
CREATE TRIGGER after_borrowing
AFTER INSERT ON borrowedbooks 
FOR EACH ROW
BEGIN
  UPDATE `books` SET `quantity` = `quantity` - 1 WHERE id = NEW.book_id;
END;
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_returning_or_loosing
AFTER UPDATE ON borrowedbooks 
FOR EACH ROW
BEGIN
	IF NEW.status = 'retourné' AND OLD.status != 'retourné' THEN
		UPDATE `books` SET `quantity` = `quantity` + 1 WHERE id = NEW.book_id;
	ELSEIF NEW.status = 'perdu' AND OLD.status = 'retourné' THEN
		UPDATE `books` SET `quantity` = `quantity` - 1 WHERE id = NEW.book_id;
  ELSEIF NEW.status = 'emprunté' AND OLD.status = 'retourné' THEN
		UPDATE `books` SET `quantity` = `quantity` - 1 WHERE id = NEW.book_id;
  END IF;
END;
$$
DELIMITER ;


















































