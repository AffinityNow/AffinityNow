INSERT INTO topic(id, name)
VALUES (1000, 'Gastronomy'),
       (1001, 'Comic book'),
       (1002, 'Sport'),
       (1003, 'Fashion'),
       (1004, 'Music'),
       (1005, 'Politics'),
       (1006, 'Movies'),
       (1007, 'Galaxy Earrings'),
       (1008, 'Mathematics'),
       (1009, 'Literature');

INSERT INTO utilisateur(id,pseudo)
VALUES
(10,"Jean"),
(11,"Michel"),
(12,"Toto"),
(13,"Titi"),
(14,"Charles"),
(15,"Jeanne"),
(17,"Louise"),
(18,"Ginnette"),
(19,"Francoise");

INSERT INTO utilisateur_connaissances(utilisateur_id,niveau,id,name,connaissances_key)
VALUES
(10,"CINQ",1000,"Gastronomy","Gastronomy"),
(10,"TROIS",1001,"Comic book","Comic book"),
(10,"UN",1009,"Literature","Literature"),
(10,"TROIS",1004,"Music","Music"),
(11,"CINQ",1008,"Mathematics","Mathematics"),
(11,"QUATRE",1007,"Galaxy Earrings","Galaxy Earrings"),
(11,"TROIS",1006,"Movies","Movies"),
(11,"TROIS",1005,"Politics","Politics"),
(11,"DEUX",1001,"Gastronomy","Gastronomy"),
(12,"TROIS",1004,"Music","Music"),
(12,"DEUX",1006,"Movies","Movies"),
(12,"QUATRE",1007,"Galaxy Earrings","Galaxy Earrings"),
(12,"CINQ",1000,"Gastronomy","Gastronomy"),
(12,"DEUX",1009,"Literature","Literature"),
(13,"QUATRE",1000,"Gastronomy","Gastronomy"),
(13,"TROIS",1005,"Politics","Politics"),
(19,"DEUX",1000,"Gastronomy","Gastronomy"),
(19,"CINQ",1002,"Sport","Sport"),
(19,"TROIS",1005,"Politics","Politics"),
(19,"CINQ",1003,"Fashion","Fashion"),
(18,"TROIS",1003,"Fashion","Fashion"),
(18,"DEUX",1007,"Galaxy Earrings","Galaxy Earrings"),
(18,"QUATRE",1000,"Gastronomy","Gastronomy");