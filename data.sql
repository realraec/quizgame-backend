--
-- DATABASE DUMP STARTED
--



--
-- EMPTY ALL TABLES
--

DELETE FROM public.records;
DELETE FROM public.progresses;
DELETE FROM public.answers;
DELETE FROM public.questions;
DELETE FROM public.quizzes_persons;
DELETE FROM public.quizzes;
DELETE FROM public.persons;


--
-- PERSON ENTRIES: ADMIN
--

INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (1, NULL, 'emilie.lucas@gmail.com', 'Emilie', 'LUCAS', '$2a$10$AmKVFO3iTE93s/FT98ln2OnpIEF/jkJ8vSsCdFHvg0DSkkV856rme', 'ADMIN', 'admin1');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (2, NULL, 'thomas.saligny@gmail.com', 'Thomas', 'SALIGNY', '$2a$10$FXrq8N0Ui0SYUsqVZ/Z.6uEYW4pZrKbIbWn6C2rAf6JoBnvzUyEVy', 'ADMIN', 'admin2');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (3, NULL, 'louis.bernard@gmail.com', 'Louis', 'BERNARD', '$2a$10$TKOQoVnjjI2YzHVTWOwFh.Jvyp1ghiiNwv4zKL1Y5UTR4bTRJXrry', 'ADMIN', 'admin3');


--
-- PERSON ENTRIES: INTERN
--

INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (4, 'Amazon', 'timothée.lompech@gmail.com', 'Thimothée', 'LOMPECH', '$2a$10$cqX9EvynEx77c2jqwM2i5uVmDC2IYv8hveLDiiX4f2mVIzRFY3dAu', 'INTERN', 'intern4');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (5, 'Apple', 'mathias.guillaume@gmail.com', 'Mathias', 'GUILLAUME', '$2a$10$62eRXRCdIElCaSUkGHxxguiXQNVOEIcDYwyw25wIEf4pGApC7Y7Tm', 'INTERN', 'intern5');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (6, 'Alphabet Inc.', 'camille.royer@gmail.com', 'Camille', 'ROYER', '$2a$10$sNoh4Ws1BAvP7EZ76HyLbuHUhpBDL.ww2/Oz/fspa/FlXZVyK/x1e', 'INTERN', 'intern6');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (7, 'Microsoft', 'leandre.perrin@gmail.com', 'Léandre', 'PERRIN', '$2a$10$qvD0Cokj.LTDUDDKh9tnP.n0fvCsUTzYg0d35i1MWXdna3V1A9hGu', 'INTERN', 'intern7');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (8, 'Samsung Group', 'marie.coolen@gmail.com', 'Marie', 'COOLEN', '$2a$10$NfIMe7n2JJ2VA1TclGqO1.ozieoDF90WOlHY3g1WjA/BVtRQI69KK', 'INTERN', 'intern8');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (9, 'Tencent Holdings', 'theodore.leclerc@gmail.com', 'Théodore', 'LECLERC', '$2a$10$DWsEY6DYSogwENf35G3C3O./jBccsTL/08y.08UEPykBJUzM3epuG', 'INTERN', 'intern9');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (10, 'Meta Platforms', 'celestine.meunier@gmail.com', 'Célestine', 'MEUNIER', '$2a$10$n5/H6CWqwi0ESMMceY5GiOZk9g40vEU6VArP/LblL5HJPnYx9J3ou', 'INTERN', 'intern10');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (11, 'Cisco Systems', 'chloe.carpentier@gmail.com', 'Chloé', 'CARPENTIER', '$2a$10$STPtWoLs525fvKrdpo2Nj.uXyE27KpY9fDrt0KrQ9XpMUgBwYTpCm', 'INTERN', 'intern11');
INSERT INTO public.persons (pk_person, company, email, firstname, lastname, password, role, username) VALUES (12, 'Oracle Corporation', 'diane.caudron@gmail.com', 'Diane', 'CAUDRON', '$2a$10$8c7FQdwfr1nrPSze3C760eEBwAzOGVJN8FIgmo8O7NIvSPHIjW7ge', 'INTERN', 'intern12');


--
-- QUIZ ENTRIES
--

INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (1, 'This quiz is about the Java programming language', 'Java');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (2, 'This quiz is about the SQL programming language', 'SQL');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (3, 'This quiz is about the HTML markup language', 'HTML');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (4, 'This quiz is about the CSS stylesheet language', 'CSS');
INSERT INTO public.quizzes (pk_quiz, summary, title) VALUES (5, 'This quiz is about the C programming language', 'C');


--
-- QUIZ_PERSON ENTRIES
--

INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (1, 4);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (1, 6);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (1, 8);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (2, 4);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (2, 5);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (2, 7);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (3, 7);
INSERT INTO public.quizzes_persons (pk_quiz, pk_person) VALUES (4, 5);


--
-- QUESTION ENTRIES
--

INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (1, 15, 'What is the symbol used at the end of a command in Java?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (2, 15, 'What does OOP stand for?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (3, 15, 'What are all the possible values that a boolean [as a primitive variable] can take?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (4, 30, 'What are some IDEs that can be used to write Java code?', 1);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (5, 30, 'What are some of the keywords that can be used in SQL queries?', 2);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (6, 30, 'What command is used to create a new table in SQL?', 2);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (7, 30, 'What does the following statement in SQL do? ```DROP TABLE customers;```', 2);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (8, 30, 'What kind of language is HTML?', 3);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (9, 15, 'What is the main HTML tag that is opened at the beginning and closed at the end of every HTML file?', 3);
INSERT INTO public.questions (pk_question, max_duration_in_seconds, wording, fk_quiz) VALUES (10, 15, 'Is CSS a stylesheet language?', 4);


--
-- ANSWER ENTRIES
--

INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (1, true, ';', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (2, false, '$', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (3, false, '*', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (4, false, '#', 1);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (5, true, 'Object Oriented Programming', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (6, false, 'Obviously Over-the-top Proposition', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (7, false, 'Ohmygod Ohmygod Please', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (8, false, 'Out Of Perception', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (9, false, 'None of the above', 2);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (10, true, 'true', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (11, true, 'false', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (12, false, 'null', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (13, false, 'undefined', 3);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (14, true, 'Eclipse', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (15, true, 'IntelliJ', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (16, true, 'Visual Studio Code', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (17, false, 'Internet Explorer', 4);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (18, true, 'SELECT', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (19, false, 'DRAFT', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (20, true, 'OUTER JOIN', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (21, false, 'ERASE', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (22, true, 'SORT', 5);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (23, true, 'CREATE TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (24, false, 'SETUP TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (25, false, 'PRODUCE TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (26, false, 'MAKE TABLE', 6);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (27, true, 'Delete a table called customers', 7);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (28, false, 'Create a table called customers', 7);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (29, false, 'Check if a table called customers exists', 7);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (30, true, 'A markup language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (31, false, 'A programming language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (32, false, 'A programming language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (33, false, 'A human language', 8);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (34, true, '<html>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (35, false, '<body>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (36, false, '<head>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (37, false, '<all>', 9);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (38, true, 'Yes it is', 10);
INSERT INTO public.answers (pk_answer, is_correct, wording, fk_question) VALUES (39, false, 'No it isn''t', 10);


--
-- PROGRESS ENTRIES
--

INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (2, NULL, 0, 4, 2);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (6, NULL, 0, 7, 2);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (7, NULL, 0, 7, 3);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (1, '2023-03-28 21:38:44.150333', 2, 4, 1);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (3, '2023-03-28 21:38:44.54747', 1, 5, 2);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (4, '2023-03-28 21:38:44.637718', 1, 5, 4);
INSERT INTO public.progresses (pk_progress, date_and_time_of_completion, score, fk_person, fk_quiz) VALUES (5, NULL, 1, 6, 1);


--
-- RECORD ENTRIES
--

INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (1, true, 1, 1);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (2, false, 1, 2);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (3, false, 1, 3);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (4, true, 1, 4);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (5, false, 2, 5);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (6, false, 2, 6);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (7, false, 3, 5);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (8, false, 3, 6);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (9, true, 3, 7);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (10, true, 4, 10);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (11, false, 5, 1);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (12, true, 5, 2);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (13, false, 5, 3);
INSERT INTO public.records (pk_record, is_success, fk_progress, fk_question) VALUES (14, false, 6, 5);


--
-- PERSON SEQUENCE
--

SELECT pg_catalog.setval('public.persons_pk_person_seq', 12, true);


--
-- QUIZ SEQUENCE
--

SELECT pg_catalog.setval('public.quizzes_pk_quiz_seq', 5, true);


--
-- QUESTION SEQUENCE
--

SELECT pg_catalog.setval('public.questions_pk_question_seq', 10, true);


--
-- ANSWER SEQUENCE
--

SELECT pg_catalog.setval('public.answers_pk_answer_seq', 39, true);


--
-- PROGRESS SEQUENCE
--

SELECT pg_catalog.setval('public.progresses_pk_progress_seq', 7, true);


--
-- RECORDS SEQUENCE
--

SELECT pg_catalog.setval('public.records_pk_record_seq', 14, true);



--
-- DATABASE DUMP COMPLETE
--
