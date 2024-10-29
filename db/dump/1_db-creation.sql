CREATE TABLE department(
                           id_department INT,
                           _name_ VARCHAR(50) NOT NULL,
                           PRIMARY KEY(id_department)
);

CREATE TABLE style(
                      id_style INT,
                      _name_ VARCHAR(50),
                      PRIMARY KEY(id_style)
);

CREATE TABLE city(
                     id_city INT,
                     _name_ VARCHAR(50) NOT NULL,
                     id_department INT NOT NULL,
                     PRIMARY KEY(id_city),
                     FOREIGN KEY(id_department) REFERENCES department(id_department)
);

CREATE TABLE venue(
                      id_venue INT,
                      _name_ VARCHAR(50),
                      adress VARCHAR(500) NOT NULL,
                      url_image VARCHAR(500),
                      id_city INT NOT NULL,
                      PRIMARY KEY(id_venue),
                      FOREIGN KEY(id_city) REFERENCES city(id_city)
);

CREATE TABLE event(
                      id_event INT,
                      _name_ VARCHAR(50) NOT NULL,
                      price VARCHAR(100) NOT NULL,
                      date_time TIMESTAMP NOT NULL,
                      is_festival BOOLEAN NOT NULL,
                      id_style INT NOT NULL,
                      id_venue INT NOT NULL,
                      PRIMARY KEY(id_event),
                      FOREIGN KEY(id_style) REFERENCES style(id_style),
                      FOREIGN KEY(id_venue) REFERENCES venue(id_venue)
);
