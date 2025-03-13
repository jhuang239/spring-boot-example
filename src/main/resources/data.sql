DROP TABLE IF EXISTS todo_list;
CREATE TABLE todo_list (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    owner VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    stage VARCHAR(255) NOT NULL
);

INSERT INTO todo_list (owner, title, description, stage) VALUES
('johndoe', 'Finish project', 'Complete the final report and submit it', 'Pending'),
('alice123', 'Buy groceries', 'Milk, eggs, bread, and cheese', 'Completed'),
('bobsmith', 'Book flight', NULL, 'Pending'),
('maryjane', 'Call client', 'Discuss the new project requirements', 'Completed'),
('tomhanks', 'Pay bills', 'Electricity, water, and internet bills', 'Pending'),
('johndoe', 'Finish project', 'Complete the final report and submit it', 'Pending'),
('johndoe', 'Buy groceries', 'Milk, eggs, bread, and cheese', 'Pending'),
('johndoe', 'Book flight', 'Purchase tickets for vacation', 'Pending'),
('johndoe', 'Call client', 'Discuss the new project requirements', 'Completed'),
('johndoe', 'Pay bills', 'Electricity, water, and internet bills', 'Pending'),
('johndoe', 'Clean the house', 'Sweep the floors and dust the furniture', 'Pending'),
('johndoe', 'Prepare presentation', 'Gather research and create slides', 'Pending'),
('johndoe', 'Submit expenses', 'Send receipts and expense report to accounting', 'Completed'),
('johndoe', 'Schedule meeting', 'Coordinate with team members and book conference room', 'Pending'),
('johndoe', 'Renew gym membership', 'Pay annual fee and update contact information', 'Completed');

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    userid VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

INSERT INTO users (userid, password, name) VALUES
('johndoe', 'password1', 'John Doe'),
('alice123', 'password2', 'Alice Smith'),
('bobsmith', 'password3', 'Bob Smith'),
('maryjane', 'password4', 'Mary Jane'),
('tomhanks', 'password5', 'Tom Hanks');