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
(10,"CINQ",1001,"Comic book","Comic book"),
(10,"CINQ",1009,"Literature","Literature"),
(10,"CINQ",1000,"Gastronomy","Gastronomy"),
(11,"TROIS",1000,"Gastronomy","Gastronomy"),
(12,"DEUX",1000,"Gastronomy","Gastronomy"),
(13,"QUATRE",1000,"Gastronomy","Gastronomy"),
(19,"CINQ",1000,"Gastronomy","Gastronomy"),
(18,"DEUX",1000,"Gastronomy","Gastronomy");