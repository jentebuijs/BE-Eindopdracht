INSERT INTO users (username, password, email, is_student)
    VALUES('karel', 'appel', 'karelappel@gmail.com', false);

INSERT INTO users (username, password, email, is_student)
VALUES('vincent', 'gogh', 'vincentvangogh@gmail.com', true);

INSERT INTO profiles (first_name, is_student, user_id) VALUES ('karel', false, 1);

INSERT INTO profiles (first_name, is_student, user_id) VALUES ('vincent', true, 2);

INSERT INTO requests (id, got_accepted, got_canceled, message, sender_id, recipient_id)
VALUES (1, false, false, 'test1', 1, 2);

INSERT INTO messages (title, content, for_buddy, for_student, approved)
    VALUES('titel', 'content', true, true, true);
