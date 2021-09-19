SET SCHEMA 'bookstoredb';

INSERT INTO users (email, password, full_name) VALUES ('cesare@gmail.com', 'AleaIactaEst', 'Cesare Augusto');
INSERT INTO users (email, password, full_name) VALUES ('nerone@gmail.com', 'RomeOnFire', 'Nerone Germanico');
INSERT INTO users (email, password, full_name) VALUES ('aurelio@gmail.com', 'Meditazioni', 'Marco Aurelio');
COMMIT;
