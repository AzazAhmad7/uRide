INSERT INTO app_user (name, email, password) VALUES
('Nicole Stewart', 'sarahmartin@yahoo.com', '!_ioJUdNA0'),
('Melissa Stewart', 'toni11@yahoo.com', 'N2c0!Pvw#u'),
('Carrie Hood', 'rlynch@yahoo.com', 'n(0WVwybmI'),
('John Roberts', 'mosleywilliam@gmail.com', '^Hqu^Lp($7'),
('Andrew Rodriguez', 'carolnguyen@hotmail.com', 'zMOUm9O8*5'),
('Amy Miller', 'johnsoncharles@hotmail.com', '(3+4K_#mUu'),
('Kristen Fisher', 'nathan03@campbell.com', '^SMs9kD)&8'),
('Kim Chandler', 'adam39@chang.com', 'V%S9G*lgaF'),
('Sandra Campbell', 'robert73@murray.com', 'R6TJspZy$%'),
('Kenneth Wong', 'ronald37@davis.info', '!S%6GSc3Ky'),
('Kristin Kane MD', 'velasquezricardo@page.info', 'Xsp9SNUkP+'),
('Stephen Rodriguez', 'robertwarren@yahoo.com', 'tLU5Psh3!('),
('Gary Newman', 'wmitchell@brooks.com', '!WZX0T4l3G'),
('Terri Guzman', 'shannondavis@yahoo.com', ')7PtRA5X@c'),
('Nicholas Mccarthy', 'lindseyariana@yahoo.com', '@c4S&Rb610'),
('Ebony Turner', 'vphillips@gmail.com', 'C9Cl&Rz1+g'),
('Melissa Miller', 'jchan@hotmail.com', '$Q+9dTIq(x'),
('Antonio Todd', 'ychaney@oliver.com', '^9oSuW$uc8'),
('Joshua Richardson', 'powelljames@gray.biz', 'H)SY2Zjkw_'),
('Jason Cannon', 'droberson@yahoo.com', '1cxGxK^S+6'),
('Cynthia Smith', 'valenciadavid@gmail.com', '63tFq0L9^O'),
('Katherine Powers', 'elowe@moore.biz', '_XPs4*lh0K'),
('Catherine Harper', 'bgray@franklin.biz', ')6@L(Ym+ni'),
('Brett Walsh', 'markporter@gmail.com', '*!hROgn)a0'),
('Jessica Williams', 'yvincent@lutz.org', 'w&91rP)d1O'),
('Brian Watkins', 'gglass@yahoo.com', '_(q85OiCcl'),
('Douglas Cooper', 'lgonzales@melton.com', '2*S8sCb70k'),
('Melissa Bass', 'ianderson@hotmail.com', '@5uLEeK*+X'),
('Charlene Ramirez', 'angelacurry@hill.com', '7Cr6oAPp&r'),
('Mary Cox', 'david79@gmail.com', '6U0gsONsy@');


INSERT INTO user_roles (user_user_id, roles) VALUES
(1, 'RIDER'),
(2, 'DRIVER'),
(3, 'RIDER'),
(4, 'RIDER'),
(5, 'RIDER'),
(6, 'DRIVER'),
(7, 'RIDER'),
(8, 'RIDER'),
(9, 'DRIVER'),
(10, 'RIDER'),
(11, 'DRIVER'),
(12, 'RIDER'),
(13, 'RIDER'),
(14, 'DRIVER'),
(15, 'RIDER'),
(16, 'DRIVER'),
(17, 'RIDER'),
(18, 'RIDER'),
(19, 'DRIVER'),
(20, 'RIDER'),
(21, 'RIDER'),
(22, 'DRIVER'),
(23, 'RIDER'),
(24, 'RIDER'),
(25, 'RIDER'),
(26, 'DRIVER'),
(27, 'RIDER'),
(28, 'DRIVER'),
(29, 'RIDER'),
(30, 'DRIVER');

INSERT INTO rider (id, user_id, rating) VALUES
(1,1,4.9);

INSERT INTO driver (id, user_id, rating, available, current_location) VALUES
    (2, 2, 4.7, true, ST_GeomFromText('POINT(77.1025 28.7041)', 4326)),  -- Connaught Place
    (3, 3, 4.5, false, ST_GeomFromText('POINT(77.1115 28.6562)', 4326)),  -- Chandni Chowk
    (4, 4, 4.9, true, ST_GeomFromText('POINT(77.1734 28.5952)', 4326)),  -- South Extension
    (5, 5, 4.1, true, ST_GeomFromText('POINT(77.2519 28.5582)', 4326)),  -- Vasant Kunj
    (6, 6, 4.3, false, ST_GeomFromText('POINT(77.2090 28.6139)', 4326)),  -- India Gate
    (7, 7, 4.8, true, ST_GeomFromText('POINT(77.2928 28.5355)', 4326)),  -- Saket
    (8, 8, 4.4, true, ST_GeomFromText('POINT(77.1246 28.5696)', 4326)),  -- Rajouri Garden
    (9, 9, 4.6, false, ST_GeomFromText('POINT(77.1370 28.6904)', 4326)),  -- Rohini
    (10, 10, 4.0, true, ST_GeomFromText('POINT(77.1034 28.5356)', 4326)),  -- Dwarka
    (11, 11, 4.2, true, ST_GeomFromText('POINT(77.2397 28.6136)', 4326)),  -- Lajpat Nagar
    (12, 12, 4.7, false, ST_GeomFromText('POINT(77.1666 28.6104)', 4326)),  -- Safdarjung
    (13, 13, 4.3, true, ST_GeomFromText('POINT(77.2765 28.6296)', 4326)),  -- Mayur Vihar
    (14, 14, 4.9, true, ST_GeomFromText('POINT(77.2450 28.6565)', 4326)),  -- Yamuna Vihar
    (15, 15, 4.5, false, ST_GeomFromText('POINT(77.3045 28.5315)', 4326)),  -- Okhla
    (16, 16, 4.8, true, ST_GeomFromText('POINT(77.2334 28.5085)', 4326)),  -- Mehrauli
    (17, 17, 4.2, true, ST_GeomFromText('POINT(77.1855 28.7070)', 4326)),  -- Shalimar Bagh
    (18, 18, 4.4, false, ST_GeomFromText('POINT(77.1519 28.5503)', 4326)),  -- Patel Nagar
    (19, 19, 4.3, true, ST_GeomFromText('POINT(77.3067 28.6467)', 4326)),  -- New Ashok Nagar
    (20, 20, 4.7, false, ST_GeomFromText('POINT(77.1949 28.7045)', 4326)),  -- Pitampura
    (21, 21, 4.1, true, ST_GeomFromText('POINT(77.2167 28.6797)', 4326)),  -- Civil Lines
    (22, 22, 4.9, true, ST_GeomFromText('POINT(77.0851 28.6924)', 4326)),  -- Paschim Vihar
    (23, 23, 4.5, true, ST_GeomFromText('POINT(77.1526 28.6746)', 4326)),  -- Model Town
    (24, 24, 4.6, false, ST_GeomFromText('POINT(77.1322 28.6048)', 4326)),  -- Naraina
    (25, 25, 4.2, true, ST_GeomFromText('POINT(77.2252 28.5245)', 4326)),  -- Hauz Khas
    (26, 26, 4.4, false, ST_GeomFromText('POINT(77.0931 28.6798)', 4326)),  -- Janakpuri
    (27, 27, 4.7, true, ST_GeomFromText('POINT(77.1570 28.6295)', 4326)),  -- Karol Bagh
    (28, 28, 4.9, true, ST_GeomFromText('POINT(77.2284 28.5361)', 4326)),  -- Malviya Nagar
    (29, 29, 4.6, false, ST_GeomFromText('POINT(77.2995 28.6543)', 4326)),  -- Anand Vihar
    (30, 30, 4.5, true, ST_GeomFromText('POINT(77.2438 28.6431)', 4326));   -- Shahdara

