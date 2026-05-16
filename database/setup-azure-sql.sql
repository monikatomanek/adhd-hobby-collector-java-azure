-- ADHD Hobby Collector Database Setup
-- Azure SQL version

IF OBJECT_ID('DeletedProjects', 'U') IS NOT NULL DROP TABLE DeletedProjects;
IF OBJECT_ID('Supplies', 'U') IS NOT NULL DROP TABLE Supplies;
IF OBJECT_ID('Projects', 'U') IS NOT NULL DROP TABLE Projects;
IF OBJECT_ID('Hobbies', 'U') IS NOT NULL DROP TABLE Hobbies;

CREATE TABLE Hobbies (
    hobby_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    category VARCHAR(50),
    skill_level VARCHAR(20) CHECK (skill_level IN ('Beginner','Intermediate','Advanced'))
);

CREATE TABLE Projects (
    project_id INT IDENTITY(1,1) PRIMARY KEY,
    hobby_id INT,
    project_name VARCHAR(150) NOT NULL,
    status VARCHAR(20) CHECK (status IN ('Started','Abandoned','Finished')),
    notes VARCHAR(200) NOT NULL CHECK (notes <> ''),
    started_date DATE NOT NULL DEFAULT CAST(GETDATE() AS DATE),
    CONSTRAINT fk_hobby FOREIGN KEY (hobby_id) REFERENCES Hobbies(hobby_id)
);

CREATE TABLE Supplies (
    supply_id INT IDENTITY(1,1) PRIMARY KEY,
    hobby_id INT,
    item_name VARCHAR(100) NOT NULL,
    cost DECIMAL(6,2) CHECK (cost >= 0),
    is_essential BIT DEFAULT 1,
    supply_type VARCHAR(50) DEFAULT 'General',
    CONSTRAINT fk_supply_hobby FOREIGN KEY (hobby_id) REFERENCES Hobbies(hobby_id)
);

CREATE TABLE DeletedProjects (
    deleted_id INT IDENTITY(1,1) PRIMARY KEY,
    project_id INT,
    hobby_id INT,
    project_name VARCHAR(150),
    status VARCHAR(20),
    notes VARCHAR(200),
    started_date DATE,
    deleted_date DATE DEFAULT CAST(GETDATE() AS DATE)
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

INSERT INTO Projects (hobby_id, project_name, status, notes, started_date)
VALUES
(1, 'Knit Sweater', 'Abandoned', 'Turned into scarf, then abandoned.', '2025-01-10'),
(2, 'Clay Mug', 'Finished', 'Collapsed during shaping, became candle holder.', '2025-01-15'),
(3, 'To-Do List App', 'Started', 'Ironically never finished.', '2025-01-20'),
(4, 'Kickflip Trick', 'Abandoned', 'Sprained ankle, switched to crosstrainer instead.', '2025-01-25'),
(5, 'Harvest Potatoes', 'Finished', 'Finally harvested something edible.', '2025-01-15'),
(5, 'Plant Tomatoes', 'Started', 'Fingers crossed.', '2025-01-22'),
(6, 'Lo-Fi Beat', 'Started', 'Vibey experiment in music production.', '2025-01-30'),
(7, 'Learn Wonderwall', 'Abandoned', 'Got bored quickly.', '2025-02-01'),
(8, 'Bake a Cake', 'Finished', 'Forgot sugar, had to cover with jam.', '2025-02-03');

INSERT INTO Supplies (hobby_id, item_name, cost, is_essential, supply_type)
VALUES
(1, 'Yarn', 12.50, 1, 'Material'),
(1, 'Knitting Needles', 8.00, 1, 'Tool'),
(2, 'Clay', 20.00, 1, 'Material'),
(2, 'Glaze Paints', 15.00, 0, 'Consumable'),
(3, 'Laptop', 800.00, 1, 'Tool'),
(3, 'Energy Drinks', 5.00, 0, 'Consumable'),
(4, 'Skateboard', 120.00, 1, 'Tool'),
(4, 'Helmet', 35.00, 1, 'Tool'),
(5, 'Garden Gloves', 10.00, 1, 'Tool'),
(5, 'Shovel', 25.00, 1, 'Tool'),
(6, 'MIDI Keyboard', 150.00, 1, 'Tool'),
(6, 'Headphones', 90.00, 1, 'Tool'),
(7, 'Acoustic Guitar', 200.00, 1, 'Tool'),
(7, 'Guitar Picks', 5.00, 0, 'Consumable'),
(8, 'Flour', 2.50, 1, 'Material'),
(8, 'Mixing Bowl', 12.00, 1, 'Tool');

INSERT INTO Hobbies (name, category, skill_level)
VALUES ('Meditation', 'Mindfulness', 'Beginner');

INSERT INTO Supplies (hobby_id, item_name, cost, is_essential, supply_type)
VALUES (9, 'Yoga Mat', 20.00, 1, 'Tool');

INSERT INTO DeletedProjects (project_id, hobby_id, project_name, status, notes, started_date)
SELECT project_id, hobby_id, project_name, status, notes, started_date
FROM Projects
WHERE project_id = 4;

DELETE FROM Projects
WHERE project_id = 4;