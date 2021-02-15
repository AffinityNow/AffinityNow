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

INSERT INTO user(id,pseudo)
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

INSERT INTO user_liked_knowledges(user_id,level,id,name,liked_knowledges_key)
VALUES
(10,"FIVE",1000,"Gastronomy","Gastronomy"),
(10,"THREE",1001,"Comic book","Comic book"),
(10,"ONE",1009,"Literature","Literature"),
(10,"THREE",1004,"Music","Music"),
(11,"FIVE",1008,"Mathematics","Mathematics"),
(11,"FOUR",1007,"Galaxy Earrings","Galaxy Earrings"),
(11,"THREE",1006,"Movies","Movies"),
(11,"THREE",1005,"Politics","Politics"),
(11,"TWO",1001,"Gastronomy","Gastronomy"),
(12,"THREE",1004,"Music","Music"),
(12,"TWO",1006,"Movies","Movies"),
(12,"FOUR",1007,"Galaxy Earrings","Galaxy Earrings"),
(12,"FIVE",1000,"Gastronomy","Gastronomy"),
(12,"TWO",1009,"Literature","Literature"),
(13,"FOUR",1000,"Gastronomy","Gastronomy"),
(13,"THREE",1005,"Politics","Politics"),
(19,"TWO",1000,"Gastronomy","Gastronomy"),
(19,"FIVE",1002,"Sport","Sport"),
(19,"THREE",1005,"Politics","Politics"),
(19,"FIVE",1003,"Fashion","Fashion"),
(18,"THREE",1003,"Fashion","Fashion"),
(18,"TWO",1007,"Galaxy Earrings","Galaxy Earrings"),
(18,"FOUR",1000,"Gastronomy","Gastronomy");


INSERT INTO user_seeked_knowledges(user_id,level,id,name,seeked_knowledges_key)
VALUES
(10,"FIVE",1005,"Politics","Politics"),
(10,"THREE",1002,"Sport","Sport"),
(11,"FIVE",1003,"Fashion","Fashion"),
(11,"FOUR",1007,"Gastronomy","Gastronomy"),
(12,"THREE",1004,"Music","Music"),
(12,"TWO",1006,"Movies","Movies"),
(13,"FOUR",1000,"Gastronomy","Gastronomy"),
(13,"THREE",1005,"Politics","Politics"),
(19,"TWO",1000,"Gastronomy","Gastronomy"),
(19,"FIVE",1002,"Sport","Sport"),
(18,"TWO",1007,"Galaxy Earrings","Galaxy Earrings"),
(18,"FOUR",1000,"Gastronomy","Gastronomy");