CREATE TABLE user (
	institutional_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT,
    phone TEXT,
    user_type TEXT NOT NULL,
	room INTEGER,
	login TEXT,
	password TEXT
)

CREATE TABLE part(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
    part_type  TEXT NOT NULL,
    max_days_for_student INTEGER NOT NULL,
    max_days_for_professor INTEGER NOT NULL
);

CREATE TABLE part_item(
	patrimonial_id INTEGER PRIMARY KEY AUTOINCREMENT,
    status TEXT NOT NULL,
    observation TEXT,
    part_id INTEGER NOT NULL,
	FOREIGN KEY (part_id) REFERENCES part(id)
);

CREATE TABLE reservation(
	reservation_id INTEGER PRIMARY KEY AUTOINCREMENT,
    date_time_scheduled_for_checkout TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    technician_id INTEGER NOT NULL,
	status TEXT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user(institutional_id),
	FOREIGN KEY (technician_id) REFERENCES user(institutional_id)	 
);

CREATE TABLE reservation_item(
	reservation_id INTEGER NOT NULL, 
	part_item_id INTEGER NOT NULL,
	FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id),
	FOREIGN KEY (part_item_id) REFERENCES part_item(patrimonial_id),
	PRIMARY KEY (reservation_id, part_item_id)
);

CREATE TABLE checkout(
	checkout_id INTEGER PRIMARY KEY AUTOINCREMENT,
    technician_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
	checkout_date TEXT NOT NULL,
    reservation_id INTEGER,
	FOREIGN KEY (technician_id) REFERENCES user(institutional_id),
	FOREIGN KEY (user_id) REFERENCES user(institutional_id),
	FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)	 
);

CREATE TABLE checkout_item(
    checkout_id INTEGER NOT NULL,
    part_item_id INTEGER NOT NULL,
    due_date TEXT NOT NULL,
    return_date TEXT,
	FOREIGN KEY (checkout_id) REFERENCES checkout(checkout_id),
	FOREIGN KEY (part_item_id) REFERENCES part_item(patrimonial_id),
	PRIMARY KEY (checkout_id, part_item_id)
);

CREATE TABLE event(
	 id INTEGER PRIMARY KEY AUTOINCREMENT,
	 event_type TEXT NOT NULL,
	 time_stamp TEXT NOT NULL,
	 part_item_id INTEGER NOT NULL,
     user_id INTEGER NOT NULL,
     technician_id INTEGER NOT NULL,
	 FOREIGN KEY(part_item_id) REFERENCES part_item(patrimonial_id),
	 FOREIGN KEY(user_id) REFERENCES user(institutional_id),
	 FOREIGN KEY(technician_id) REFERENCES user(institutional_id)
);

insert into user(name, email, phone, user_type, login, password) 
values ('Mário', 'mario@email.com','16887766776', 'Técnico', 'mprado', 'm1234'),
	   ('Paula', 'paula@email.com','16887766776', 'Professor', NULL , NULL),
	   ('Carmen', 'carmen@email.com','16887766776', 'Estudante', NULL, NULL);


INSERT INTO part(description, max_days_for_student, max_days_for_professor) 
VALUES ('valvula', 5, 15), ('helice', 15, 25), ('pastilha', 7, 7);
	
INSERT INTO part_item(status, part_id)
VALUES ('Disponível', 1), ('Disponível', 1), ('Emprestada', 1), ('Reservada', 1),
	   ('Disponível', 2), ('Disponível', 2), ('Emprestada', 2), ('Emprestada', 2),
	   ('Reservada', 3), ('Reservada', 3), ('Reservada', 3), ('Disponível', 3);
	

