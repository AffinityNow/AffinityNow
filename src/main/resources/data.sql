

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
INSERT INTO topic(id,name)
VALUES
(1000,"Gastronomy"),
(1001,"Comic book"),
(1002,"Sport"),
(1003,"Fashion"),
(1004,"Music"),
(1005,"Politics"),
(1007,"Galaxy Earrings"),
(1008,"Mathematics"),
(1009,"Literature");

INSERT INTO utilisateur_dto(id,pseudo)
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


INSERT INTO rated_topic(topic_id,user_id,rating)
VALUES
(1000,10,5),
(1000,17,2),
(1000,12,3),
(1001,11,2),
(1001,14,4),
(1001,15,1),
(1001,18,3),
(1002,12,3),
(1003,13,4),
(1004,14,1),
(1005,15,1),
(1006,16,2),
(1007,17,2),
(1008,18,4),
(1009,19,3);