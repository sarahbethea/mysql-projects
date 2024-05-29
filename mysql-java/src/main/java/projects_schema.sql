DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project;



CREATE TABLE project (
	project_id INT AUTO_INCREMENT NOT NULL,
	project_name VARCHAR(128) NOT NULL,
	estimated_hours DECIMAL(7,2),
	actual_hours DECIMAL(7,2),
	difficulty INT,
	notes TEXT,
	PRIMARY KEY (project_id)

);

CREATE TABLE material (
	material_id INT AUTO_INCREMENT NOT NULL,
	project_id INT NOT NULL,
	material_name VARCHAR(128) NOT NULL,
	num_required INT,
	cost DECIMAL(7,2),
	PRIMARY KEY (material_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE

);

CREATE TABLE step (
	step_id INT AUTO_INCREMENT NOT NULL,
	project_id INT NOT NULL,
	step_text TEXT NOT NULL,
	step_order INT NOT NULL,
	PRIMARY KEY (step_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE

);

CREATE TABLE category (
	category_id INT AUTO_INCREMENT NOT NULL,
	category_name VARCHAR(128) NOT NULL,
	PRIMARY KEY (category_id)

);

CREATE TABLE project_category (
	project_id INT NOT NULL,
	category_id INT NOT NULL,
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
	FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
	UNIQUE KEY (project_id, category_id)

);


INSERT INTO project (project_id, project_name, estimated_hours, actual_hours, difficulty, notes)
	VALUES (20, 'mailbox', 4, 3.5, 3, 'creates mailbox with flag');
	
INSERT INTO material (project_id, material_name, num_required, cost) 
	VALUES (20, 'wood post', 1, 20.00);
INSERT INTO material (project_id, material_name, num_required, cost) 
	VALUES (20, 'nails', 20, 5.00);
INSERT INTO material (project_id, material_name, num_required, cost) 
	VALUES (20, 'metal mail box', 1, 40.00);
INSERT INTO step (project_id, step_text, step_order) 
	VALUES (20, 'Attach mailbox to post with nails', 1);
INSERT INTO step (project_id, step_text, step_order) 
	VALUES (20, 'Dig post hole in ground', 2);
INSERT INTO step (project_id, step_text, step_order) 
	VALUES (20, 'Jam post into hole', 3);
INSERT INTO category (category_name) VALUES ('Outdoors'), ('Easy'), ('Fast');
INSERT INTO project_category (project_id, category_id) VALUES (20, 1);
INSERT INTO project_category (project_id, category_id) VALUES (20, 2);
INSERT INTO project_category (project_id, category_id) VALUES (20, 3);