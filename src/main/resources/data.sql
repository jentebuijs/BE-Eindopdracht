INSERT INTO users (username, password, email, is_student, enabled)
    VALUES ('karel', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6', 'karelappel@gmail.com', false, true),
           ('vincent', '$2y$10$o6uZsxhnP2//MTSAICYUGuthde9LioKWaEKR7OTd17w6nf3q.2zpu', 'vincentvangogh@gmail.com', true, true);

INSERT INTO authorities (username, authority)
    VALUES ('karel', 'USER'),
           ('vincent', 'ADMIN');

INSERT INTO profiles (first_name, is_student, user_id)
VALUES ('karel', false, 1),
       ('vincent', true, 2);

INSERT INTO requests (id, got_accepted, got_canceled, message, sender_id, recipient_id)
VALUES (1, false, false, 'test1', 1, 2);

INSERT INTO messages (title, content, for_buddy, for_student, approved)
    VALUES('titel', 'content', true, true, true);
