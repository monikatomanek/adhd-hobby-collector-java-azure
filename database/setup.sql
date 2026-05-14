-- ADHD Hobby Collector Database Setup
-- Java + Azure preparation version

DROP DATABASE IF EXISTS ADHD_HobbyCollector;
CREATE DATABASE ADHD_HobbyCollector;
USE ADHD_HobbyCollector;

CREATE TABLE Hobbies (
    hobby_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(50),
    skill_level VARCHAR(20) CHECK (skill_level IN ('Beginner','Intermediate','Advanced'))
);

CREATE TABLE Projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    hobby_id INT,
    project_name VARCHAR(150) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('Started','Abandoned','Finished')),
    notes VARCHAR(200) NOT NULL CHECK (notes <> ''),
    started_date DATE NOT NULL DEFAULT (CURRENT_DATE),

    finished_date DATE NULL,
    abandoned_date DATE NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    motivation_level INT CHECK (motivation_level BETWEEN 1 AND 10),

    CONSTRAINT fk_hobby FOREIGN KEY (hobby_id) REFERENCES Hobbies(hobby_id)
);

CREATE TABLE Supplies (
    supply_id INT PRIMARY KEY AUTO_INCREMENT,
    hobby_id INT,
    item_name VARCHAR(100) NOT NULL,
    cost DECIMAL(6,2) CHECK (cost >= 0),
    is_essential BOOLEAN DEFAULT TRUE,
    supply_type VARCHAR(50) DEFAULT 'General',
    CONSTRAINT fk_supply_hobby FOREIGN KEY (hobby_id) REFERENCES Hobbies(hobby_id)
);

CREATE TABLE DeletedProjects (
    deleted_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT,
    hobby_id INT,
    project_name VARCHAR(150),
    status VARCHAR(20),
    notes VARCHAR(200),
    started_date DATE,

    finished_date DATE NULL,
    abandoned_date DATE NULL,
    created_at DATETIME,
    motivation_level INT,

    deleted_date DATE DEFAULT (CURRENT_DATE)
);

INSERT INTO Hobbies (name, category, skill_level)
VALUES
('Knitting', 'Craft', 'Beginner'),
('Pottery', 'Art', 'Beginner'),
('Coding', 'Tech', 'Intermediate'),
('Skateboarding', 'Sport', 'Beginner'),
('Gardening', 'Outdoors', 'Intermediate'),
('Music Production', 'Music', 'Intermediate'),
('Guitar', 'Music', 'Beginner'),
('Cooking', 'Lifestyle', 'Beginner');

INSERT INTO Projects 
(hobby_id, project_name, status, notes, started_date, motivation_level)
VALUES
(1, 'Knit Sweater', 'Abandoned', 'Turned into scarf, then abandoned.', '2025-01-10', 8),
(2, 'Clay Mug', 'Finished', 'Collapsed during shaping, became candle holder.', '2025-01-15', 7),
(3, 'To-Do List App', 'Started', 'Ironically never finished.', '2025-01-20', 9),
(4, 'Kickflip Trick', 'Abandoned', 'Sprained ankle, switched to crosstrainer instead.', '2025-01-25', 6),
(5, 'Harvest Potatoes', 'Finished', 'Finally harvested something edible.', '2025-01-15', 5),
(5, 'Plant Tomatoes', 'Started', 'Fingers crossed.', '2025-01-22', 7),
(6, 'Lo-Fi Beat', 'Started', 'Vibey experiment in music production.', '2025-01-30', 9),
(7, 'Learn Wonderwall', 'Abandoned', 'Got bored quickly.', '2025-02-01', 4),
(8, 'Bake a Cake', 'Finished', 'Forgot sugar, had to cover with jam.', '2025-02-03', 6);

INSERT INTO Supplies (hobby_id, item_name, cost, is_essential, supply_type)
VALUES
(1, 'Yarn', 12.50, TRUE, 'Material'),
(1, 'Knitting Needles', 8.00, TRUE, 'Tool'),
(2, 'Clay', 20.00, TRUE, 'Material'),
(2, 'Glaze Paints', 15.00, FALSE, 'Consumable'),
(3, 'Laptop', 800.00, TRUE, 'Tool'),
(3, 'Energy Drinks', 5.00, FALSE, 'Consumable'),
(4, 'Skateboard', 120.00, TRUE, 'Tool'),
(4, 'Helmet', 35.00, TRUE, 'Tool'),
(5, 'Garden Gloves', 10.00, TRUE, 'Tool'),
(5, 'Shovel', 25.00, TRUE, 'Tool'),
(6, 'MIDI Keyboard', 150.00, TRUE, 'Tool'),
(6, 'Headphones', 90.00, TRUE, 'Tool'),
(7, 'Acoustic Guitar', 200.00, TRUE, 'Tool'),
(7, 'Guitar Picks', 5.00, FALSE, 'Consumable'),
(8, 'Flour', 2.50, TRUE, 'Material'),
(8, 'Mixing Bowl', 12.00, TRUE, 'Tool');

START TRANSACTION;

INSERT INTO Hobbies (name, category, skill_level)
VALUES ('Meditation', 'Mindfulness', 'Beginner');

INSERT INTO Supplies (hobby_id, item_name, cost, is_essential, supply_type)
VALUES (LAST_INSERT_ID(), 'Yoga Mat', 20.00, TRUE, 'Tool');

COMMIT;

INSERT INTO DeletedProjects 
(project_id, hobby_id, project_name, status, notes, started_date, finished_date, abandoned_date, created_at, motivation_level)
SELECT 
project_id, hobby_id, project_name, status, notes, started_date, finished_date, abandoned_date, created_at, motivation_level
FROM Projects
WHERE project_id = 4;

DELETE FROM Projects
WHERE project_id = 4;

SELECT * FROM Projects;
SELECT * FROM DeletedProjects;
SELECT * FROM Projects WHERE project_name = 'Kickflip Trick';