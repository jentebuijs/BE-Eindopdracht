INSERT INTO profiles (username, is_activated, first_name, last_name, dob, age, email, role, frequency, about_me, level)
VALUES ('Admin', true, 'Voornaam', 'Achternaam', '01-01-1970', 55, 'admin@gmail.com', 'ROLE_ADMIN', 'ONCE_A_WEEK',
        'Ik ben zo blij om admin te zijn', null),
       ('Student', true, 'Voornaam', 'Achternaam', '01-01-1970', 44, 'student@gmail.com', 'ROLE_STUDENT', 'ONCE_A_MONTH',
        'Ik ben zo hard aan het studeren', 'BEGINNER'),
       ('Buddy', true, 'Voornaam', 'Achternaam', '01-01-1970', 33, 'buddy@gmail.com', 'ROLE_BUDDY', 'EVERY_DAY',
        'Klaar om je te helpen', null),
       ('Vulling1', true, 'Voornaam', 'Achternaam', '01-01-1970', 22, 'vulling@gmail.com', 'ROLE_STUDENT', 'FEW_TIMES_A_MONTH',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'INTERMEDIATE'),
       ('Vulling2', true, 'Voornaam', 'Achternaam', '01-01-1970', 77, 'vulling@gmail.com', 'ROLE_STUDENT', 'FEW_TIMES_A_WEEK',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'UPPER_INTERMEDIATE'),
       ('Vulling3', true, 'Voornaam', 'Achternaam', '01-01-1970', 66, 'vulling@gmail.com', 'ROLE_STUDENT', 'ONCE_A_WEEK',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'ADVANCED'),
       ('Vulling4', true, 'Voornaam', 'Achternaam', '01-01-1970', 19, 'vulling@gmail.com', 'ROLE_STUDENT', 'ONCE_A_MONTH',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'INTERMEDIATE'),
       ('Vulling5', true, 'Voornaam', 'Achternaam', '01-01-1970', 24, 'vulling@gmail.com', 'ROLE_STUDENT', 'FEW_TIMES_A_WEEK',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'BEGINNER'),
       ('Vulling6', true, 'Voornaam', 'Achternaam', '01-01-1970', 35, 'vulling@gmail.com', 'ROLE_STUDENT', 'EVERY_DAY',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'UPPER_INTERMEDIATE'),
       ('Vulling7', true, 'Voornaam', 'Achternaam', '01-01-1970', 58, 'vulling@gmail.com', 'ROLE_STUDENT', 'ONCE_A_MONTH',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'PROFICIENT'),
       ('Vulling8', true, 'Voornaam', 'Achternaam', '01-01-1970', 46, 'vulling@gmail.com', 'ROLE_STUDENT', 'FEW_TIMES_A_MONTH',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', 'ELEMENTARY'),
       ('Vulling9', true, 'Voornaam', 'Achternaam', '01-01-1970', 38, 'vulling@gmail.com', 'ROLE_BUDDY', 'FEW_TIMES_A_MONTH',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null),
       ('Vulling10', true, 'Voornaam', 'Achternaam', '01-01-1970', 41, 'vulling@gmail.com', 'ROLE_BUDDY', 'ONCE_A_WEEK',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null),
       ('Vulling11', true, 'Voornaam', 'Achternaam', '01-01-1970', 62, 'vulling@gmail.com', 'ROLE_BUDDY', 'EVERY_DAY',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null),
       ('Vulling12', true, 'Voornaam', 'Achternaam', '01-01-1970', 31, 'vulling@gmail.com', 'ROLE_BUDDY', 'FEW_TIMES_A_WEEK',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null),
       ('Vulling13', true, 'Voornaam', 'Achternaam', '01-01-1970', 47, 'vulling@gmail.com', 'ROLE_BUDDY', 'FEW_TIMES_A_MONTH',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null),
       ('Vulling14', true, 'Voornaam', 'Achternaam', '01-01-1970', 51, 'vulling@gmail.com', 'ROLE_BUDDY', 'EVERY_DAY',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null),
       ('Vulling15', true, 'Voornaam', 'Achternaam', '01-01-1970', 27, 'vulling@gmail.com', 'ROLE_BUDDY', 'ONCE_A_WEEK',
        'Loze tekst zonder enige betekenis. Onzin. Quatsch. Gelul in de ruimte.', null);

INSERT INTO users (username, password, enabled, user_profile)
VALUES ('Admin', '$2a$10$MYW.SmLiwyBYYLkCTMRejujIAS9YdCJeYL7V7CZ6Q33Zo2JvjjZGS', true, 'Admin'),
       ('Student', '$2a$10$Sp8kV3OY0jiKRj1JXnZo6eM2aPz793bwbkPvhrhRelPe8cwSi4h0S', true, 'Student'),
       ('Buddy', '$2a$10$iqWL2sJQ0f1yiyg9d9qYZ..PCMewTgYMlrkeo2Z9NEks/GL7sVkdW', true, 'Buddy'),
       ('Vulling1', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling1'),
       ('Vulling2', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling2'),
       ('Vulling3', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling3'),
       ('Vulling4', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling4'),
       ('Vulling5', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling5'),
       ('Vulling6', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling6'),
       ('Vulling7', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling7'),
       ('Vulling8', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling8'),
       ('Vulling9', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling9'),
       ('Vulling10', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling10'),
       ('Vulling11', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling11'),
       ('Vulling12', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling12'),
       ('Vulling13', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling13'),
       ('Vulling14', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling14'),
       ('Vulling15', '$2a$10$eBpm4SwoKAJwAx84JaGfsOdCIw2wY1DyOciupg363Jwcpntfw9Xu2', true, 'Vulling15');


INSERT INTO authorities(name)
VALUES ('ROLE_BUDDY'),
       ('ROLE_ADMIN'),
       ('ROLE_STUDENT');

INSERT INTO user_authorities (user_username, authority_id)
VALUES ('Admin', 'ROLE_ADMIN'),
       ('Student', 'ROLE_STUDENT'),
       ('Buddy', 'ROLE_STUDENT');

INSERT INTO requests (message, sender_username, receiver_username, status)
VALUES ('Wil je met mij mailen?', 'Student', 'Buddy', 'PENDING'),
       ('Wil je met mij mailen?', 'Student', 'Vulling8', 'ACCEPTED'),
       ('Wil je met mij mailen?', 'Student', 'Vulling10', 'CANCELLED'),
       ('Wil je met mij mailen?', 'Student', 'Vulling12', 'PENDING'),
       ('Wil je met mij mailen?', 'Vulling9', 'Student', 'PENDING'),
       ('Wil je met mij mailen?', 'Vulling11', 'Student', 'ACCEPTED'),
       ('Wil je met mij mailen?', 'Vulling13', 'Student', 'DECLINED'),
       ('Wil je met mij mailen?', 'Vulling15', 'Student', 'PENDING'),
       ('Wil je met mij mailen?', 'Buddy', 'Vulling1', 'ACCEPTED'),
       ('Wil je met mij mailen?', 'Buddy', 'Vulling3', 'CANCELLED'),
       ('Wil je met mij mailen?', 'Buddy', 'Vulling5', 'PENDING'),
       ('Wil je met mij mailen?', 'Buddy', 'Vulling7', 'PENDING'),
       ('Wil je met mij mailen?', 'Vulling2', 'Buddy', 'ACCEPTED'),
       ('Wil je met mij mailen?', 'Vulling4', 'Buddy', 'DECLINED'),
       ('Wil je met mij mailen?', 'Vulling6', 'Buddy', 'PENDING'),
       ('Wil je met mij mailen?', 'Vulling3', 'Buddy', 'PENDING');

INSERT INTO messages (title, content, for_buddy, for_student, approved)
VALUES ('Interessant artikel', 'Doggo ipsum pupper fluffer. Shoob long doggo puggorino very good spot lotsa pats ' ||
                  'snoot.', true, true, true),
       ('Topinformatie', 'Maximum borkdrive doggo long doggo heck shooberino lotsa pats, shooberino very jealous pupper ' ||
                  'jealous pupper.', true, true, false),
       ('Dit moet je lezen!', 'Borkdrive aqua doggo very taste wow blep snoot borking doggo thicc much ruin diet, ' ||
                  'good spot blep, floofs pats.', true, false, true),
       ('Leertips', 'Sub woofer very hand that feed shibe lotsa pats tungg smol borking doggo with a long snoot for pats ' ||
                  'angery pupper I have ever seen shibe fluffer, boofers length boy.', true, false, false),
       ('Relevante tekst', 'Sub woofer very hand that feed shibe lotsa pats tungg smol borking doggo with a long snoot ' ||
                  'angery pupper I have ever seen shibe fluffer, boofers length boy.', false, true, true),
       ('Inspirerend verhaal', 'What a nice floof I am bekom fat, you are doin me a concern extremely cuuuuuute. Bork ' ||
                  'good spot boof blop, noodle horse wow very biscit lotsa pats.', false, true, true),
       ('Misschien heb je hier iets aan', 'Corgo very good spot snoot very jealous pupper doggorino, heckin fluffer. ' ||
                  'shooberino super chub, I am bekom fat mlem h*ck. Borkf puggo.', false, false, true),
       ('Tip van de meester', ' Blep shibe puggo shoob waggy wags floofs such treat, very good spot length boy boof. Vvv ' ||
                  'blop. Ruff long woofer shoob porgo, wow very biscit.', false, false, true);