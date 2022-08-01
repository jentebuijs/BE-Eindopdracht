INSERT INTO users (username, password, email, is_student, enabled)
    VALUES ('karel', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6', 'karelappel@gmail.com', false, true),
           ('vincent', '$2y$10$o6uZsxhnP2//MTSAICYUGuthde9LioKWaEKR7OTd17w6nf3q.2zpu', 'vincentvangogh@gmail.com', true, true);

INSERT INTO authorities (username, authority)
    VALUES ('karel', 'USER'),
           ('vincent', 'ADMIN');

INSERT INTO profiles (first_name, last_name, dob, level, contact_intensity, about_me, is_student, user_id)
VALUES ('karel', 'appel', '25-04-1921', 'beginner', 'never', 'niks te melden', false, 1),
       ('vincent', 'gogh', '30-03-1853', 'gevorderd', 'soms', 'mijn oor is kapot', true, 2);

INSERT INTO requests (id, got_accepted, got_canceled, message, sender_id, recipient_id)
VALUES (1, false, false, 'test1', 1, 2);

INSERT INTO messages (title, content, for_buddy, for_student, approved)
    VALUES('titel', 'content', true, true, true),
           ('titel2', 'content2', true, false, true),
           ('titel3', 'content3', false, true, true),
           ('titel4', 'content4', true, true, false);
