-- app_user(email, password)
set search_path TO ehotel;

insert into app_user(email, password, user_role) values
    ('yToure47@eHotel.ca', '60T0NSMz$%i4', app_role('ROLE_EMPLOYEE')),
    ('cnicolson70@eHotel.ca', '0FR#Z71lZ3Du', app_role('ROLE_EMPLOYEE')),
    ('fblain29@eHotel.ca', '0c^0%u53Zs7A', app_role('ROLE_EMPLOYEE')),
    ('nneuman86@eHotel.ca', 'bg$#h&mYC850', app_role('ROLE_EMPLOYEE')),
    ('pmacguiness66@eHotel.ca', '7OR^0xM5uzXz', app_role('ROLE_EMPLOYEE')),
    ('hingham68@eHotel.ca', '#8919x1uEb4z', app_role('ROLE_EMPLOYEE')),
    ('npohl57@eHotel.ca', 'Eb1Ps41!Hp1H', app_role('ROLE_EMPLOYEE')),
    ('ocosta80@eHotel.ca', 'r2t6dqU3**A8', app_role('ROLE_EMPLOYEE')),
    ('rmaier90@eHotel.ca', 'y%SI6$8qhV2s', app_role('ROLE_EMPLOYEE')),
    ('rnapoli58@eHotel.ca', 'pgE971vfBA&t', app_role('ROLE_EMPLOYEE')),
    ('ptremblay55@eHotel.ca', 'jU!&i81@2*mF', app_role('ROLE_EMPLOYEE')),
    ('swojewoda36@eHotel.ca', '00I8*Bp7U%@r', app_role('ROLE_EMPLOYEE')),
    ('psaller27@eHotel.ca', 'U7#5ZQX084kv', app_role('ROLE_EMPLOYEE')),
    ('kkruse92@eHotel.ca', 'q9NW7x9Jho#V', app_role('ROLE_EMPLOYEE')),
    ('yfurlan29@eHotel.ca', 'y2Z07UY&o!5U', app_role('ROLE_EMPLOYEE')),
    ('hcarey02@eHotel.ca', 'OfT&LD4o6E5n', app_role('ROLE_EMPLOYEE')), --end of employee
    ('JuliaJSingletary@rhyta.com', 'aish8Eeth', app_role('ROLE_CUSTOMER')),
    ('JohnCMerwin@dayrep.com', 'Quaeghoh9nah', app_role('ROLE_CUSTOMER')),
    ('EdwardMSavage@rhyta.com', 'deez7oDai', app_role('ROLE_CUSTOMER')),
    ('GeorgeSHolton@rhyta.com', 'ainu1IJoo', app_role('ROLE_CUSTOMER')),
    ('KatrinaJMiller@armyspy.com', 'Lohze3Jae3', app_role('ROLE_CUSTOMER')),
    ('AmandaLSmithson@rhyta.com', 'fei0IeSh', app_role('ROLE_CUSTOMER')),
    ('JohnNHolub@armyspy.com', 'Aipohx2z', app_role('ROLE_CUSTOMER')),
    ('KathyJGuerrera@teleworm.us', 'aeke5cae5Woh', app_role('ROLE_CUSTOMER')),
    ('BonnieTWhite@dayrep.com', 'thoap6Sho7a', app_role('ROLE_CUSTOMER')),
    ('ThomasCWoods@armyspy.com', 'Xoo2theik', app_role('ROLE_CUSTOMER'));



-- hotel_chain(chain_ID, chain_email, chain_name, chain_phone_number, cahin_rating, chain_central_address)

insert into hotel_chain values
    (10000005, 'main@montpellier.ca', 'Main Hotels', '229-220-1212', 3, '740 Bel Meadow Drive'),
    (10000004, 'legitinn@hotel.com', 'Legitinn Hotels', '249-210-1414', 2, '1768 Jehovah Drive'),
    (52524289, 'premierhotels@gmail.com', 'Premier Hotels', '517-626-7272', 5, '4298 Haven Lane'),
    (32903871, 'superhotels@gmail.com', 'Super Hotels', '254-605-5132', 4, '1971 Clair Street'),
    (87307385, 'championshotels@gmail.com', 'Champions Hotels', '712-247-0369', 3, '4355 Woodland Drive');


-- hotel(hotel_ID, chain_ID, hotel_name, hotel_address, hotel_email, hotel_phone_number, hotel_rating)

insert into hotel(hotel_ID, chain_ID, hotel_name, hotel_address, hotel_email, hotel_phone_number, hotel_rating, city)
values
    (49484327, 10000005, 'Enitan', '738 Beacon Court Orlando','enitan@montpellier.ca', '731-875-5635', 5, 'Orlando'),
    (59320112, 10000005, 'Elder Camp Resort', '7505 Del Monte Ave. Oakland Gardens','eldercampresort@montpellier.ca', '332-499-7014', 1, 'Oakland Gardens'),
    (78510296, 10000005, 'Sunset Monarch Hotel', '709 East Courtland Street Barrington','sunsetmonarchhotel@montpellier.ca', '820-446-2090', 1, 'Barrington'),
    (23360762, 10000005, 'Ruby Creek Hotel', '666 Marlborough Road Germantown','rubycreekhotel@montpellier.ca', '825-855-0187', 3, 'GermanTown'),
    (84444557, 10000005, 'Autumn Summit Hotel', '7482 Meadow Street Stevens','autumnsummithotel@montpellier.ca', '837-786-4919', 3, 'Ottawa'),
    (52062096, 10000005, 'Autumn Nugget Hotel & Spa', '31 Carriage Street Parsippany','autumnnuggethotelspa@montpellier.ca', '367-612-9507', 5, 'Parsippany'),
    (72451204, 10000005, 'Ebony Spire Resort & Spa', '38 S. Hamilton St. Christmas Island','ebonyspireresortspa@montpellier.ca', '663-952-8672', 2, 'Ottawa'),
    (40644956, 10000005, 'Windmill Resort', '577 S. Fairground Street Edmundston','windmillresort@montpellier.ca', '665-614-3900', 4, 'Edmundston'),
    (25072973, 10000005, 'Cosmos Resort', '7940 S. Marconi Lane Essex,','cosmosresort@montpellier.ca', '332-320-9860', 3, 'Montreal'),
    (50273685, 10000005, 'Mirror Hotel', '265 Linda Lane Dorchester','mirrorhotel@montpellier.ca', '830-644-8968', 3, 'Montreal'),
    (68744259, 10000004, 'Orbit Resort', '58 Acacia Ave. Petitcodiac','orbitresort@montpellier.ca', '587-308-3192', 4, 'Ottawa'),
    (85200057, 10000004, 'Prism Hotel', '77275 North Clark Street Salmon Arm','prismhotel@montpellier.ca', '932-567-2866', 1, 'Quebec city'),
    (33216536, 10000004, 'History Hotel', '9619 Wrangler Dr. Reserved','historyhotel@montpellier.ca', '756-954-5734', 2, 'Quebec city'),
    (79140742, 10000004, 'Onyx Shroud Resort', '7627 Market Ave. Montague','onyxshroudresort@montpellier.ca', '276-935-0513', 1, 'Toronto'),
    (75131135, 10000004, 'Ancient Bear Motel', '644 North Miller Street La Tuque','ancientbearmotel@montpellier.ca', '837-353-3277', 2, 'Toronto'),
    (32304177, 10000004, 'Noble Star Motel', '897 Shadow Brook Lane Estrie-Ouest','noblestarmotel@montpellier.ca', '455-713-8489', 4, 'Toronto'),
    (16920448, 10000004, 'Secluded Cave Hotel', '47 Walt Whitman St. Delson','secludedcavehotel@montpellier.ca', '643-585-3944', 2, 'Ottawa'),
    (44736500, 10000004, 'Eastern Bluff Resort', '639 North Ohio Dr. Redwood Meadows','easternbluffresort@montpellier.ca', '294-353-8862', 3, 'Ottawa'),
    (78263435, 10000004, 'Atlantic Peak Resort', '434 King St. Castlegar','atlanticpeakresort@montpellier.ca', '563-749-6403', 1, 'Montreal'),
    (65430777, 10000004, 'Sightsee Hotel & Spa', '30 NE. Ridgewood Ave. Manotick','sightseehotelspa@montpellier.ca', '206-867-5411', 5, 'Paris'),
    (87616494, 10000004, 'Troposphere Resort & Spa', '52 Marlborough St. Dawson Creek','troposphereresortspa@montpellier.ca', '460-229-6878', 5, 'Paris'),
    (32098748, 52524289, 'Soft Crown Hotel', '3491 Thomas Street', 'softcrownhotel@gmail.com', '847-517-8944', 5, 'Paris'),
    (51473305, 52524289, 'Exalted Cosmos Hotel', '3279 Petunia Way', 'exaltedcosmoshotel@gmail.com', '205-838-3219', 5, 'Paris'),
    (94629792, 52524289, 'Lunar Garden Hotel', '3311 Wilson Avenue', 'lunargardenhotel@gmail.com', '972-540-9208', 2, 'Ottawa'),
    (16984677, 52524289, 'Elite Bear Hotel', '2613 Hamill Avenue', 'elitebearhotel@gmail.com', '858-430-2662', 3, 'Ottawa'),
    (76325763, 52524289, 'Modest Vale Resort & Spa', '1995 Sigley Road', 'modestvaleresortandspa@gmail.com', '785-489-7153', 3, 'Ottawa'),
    (78097775, 52524289, 'Twin Mansion Hotel', '1589 Breezewood Court', 'twinmansionhotel@gmail.com', '620-362-6186', 3, 'Ottawa'),
    (93041792, 52524289, 'Ruby Peaks Hotel', '1146 Viking Drive', 'rubypeakshotel@gmail.com', '740-794-3721', 1, 'Ottawa'),
    (02356455, 52524289, 'Aquamarine Orb Hotel', '3501 Davis Lane', 'aquamarineorbhotel@gmail.com', '720-308-8536', 1, 'Montreal'),
    (75945995, 32903871, 'Secret Brewery Hotel', '4132 Victoria Street', 'secretbreweryhotel@gmail.com', '225-205-1417', 5, 'Montreal'),
    (68207932, 32903871, 'Primal Lake Hotel', '3054 Pallet Street', 'primallakehotel@gmail.com', '914-318-2875', 2, 'Montreal'),
    (55817784, 32903871, 'Parallel Pier Hotel & Spa', '1065 North Avenue', 'parallelpierhotelandspa@gmail.com', '402-828-6308', 4, 'Montreal'),
    (79367040, 32903871, 'Emerald Mantle Hotel', '4124 Little Street', 'emeraldmantlehotel@gmail.com', '330-654-7269', 3, 'Montreal'),
    (03199767, 32903871, 'Peaceful Trinket Resort', '1039 Park Avenue', 'peacefultrinketresort@gmail.com', '916-478-7814', 2, 'Montreal'),
    (66323747, 32903871, 'Light Obelisk Hotel', '3819 Frum Street', 'lightobeliskhotel@gmail.com', '615-275-0921', 4, 'Paris'),
    (28190714, 32903871, 'Marina Horizon Hotel', '175 Raoul Wallenberg Place', 'marinahorizonhotel@gmail.com', '203-837-2462', 4, 'Paris'),
    (42932814, 32903871, 'Double Cove Hotel & Spa', '1157 Lynn Street', 'doublecovehotelandspa@gmail.com', '617-252-9544', 1, 'Quebec city'),
    (53214452, 87307385, 'Mellow Orb Resort', '1827 Villa Drive', 'melloworbresort@gmail.com', '574-279-4656', 2, 'Quebec city'),
    (76059485, 87307385, 'Double Atoll Hotel', '4598 Woodhill Avenue', 'doubleatollhotel@gmail.com', '410-757-6098', 5, 'Quebec city'),
    (46113850, 87307385, 'Northern Isle Hotel', '2339 Hurry Street', 'northernislehotel@gmail.com', '540-278-5650', 1, 'Quebec city'),
    (81651832, 87307385, 'Grandiose Harbor Resort', '2425 Wilkinson Court', 'grandioseharborresort@gmail.com', '239-495-0964', 2, 'Quebec city'),
    (56589988, 87307385, 'Spring Nugget Hotel & Spa', '1551 Daffodil Lane', 'springnuggethotelandspa@gmail.com', '703-469-8732', 3, 'Ottawa'),
    (23017979, 87307385, 'Sunny Tide Resort', '1968 Parkway Street', 'sunnytideresort@gmail.com', '760-318-7304', 2, 'Ottawa'),
    (73221834, 87307385, 'Silver Galaxy Hotel', '4934 Myra Street', 'silvergalaxyhotel@gmail.com', '401-565-2264', 3, 'Ottawa'),
    (29096486, 87307385, 'Western Maple Hotel', '2819 Brownton Road', 'westernmaplehotel@gmail.com', '662-386-0854', 1, 'Ottawa');


-- role(role_ID, hotel_ID, dept_name)

insert into role values
    (06323503, 49484327, 'Manager'),
    (11382105, 49484327, 'Regular'),
    (80494580, 51473305, 'Manager'),
    (96484593, 51473305, 'Regular'),
    (94303452, 68207932, 'Manager'),
    (33095515, 68207932, 'Regular'),
    (19326093, 23017979, 'Manager'),
    (76595062, 23017979, 'Regular'),
    (32316149, 78263435, 'Manager'),
    (05433877, 78263435, 'Regular'),
    (00171745, 65430777, 'Manager'),
    (73192574, 65430777, 'Regular'),
    (74331032, 87616494, 'Manager'),
    (13821714, 87616494, 'Regular'),
    (62969280, 03199767, 'Manager'),
    (45024423, 03199767, 'Regular');


-- department(dept_ID, hotel_ID, dept_name, dept_manager_NAS)

insert into department values
    (49184917, 49484327, 'Management'),
    (39623850, 49484327, 'Housekeeping'),
    (28937082, 49484327, 'Front Office'),
    (05543308, 49484327, 'Services'),
    (81986212, 51473305, 'Management'),
    (14015582, 51473305, 'Housekeeping'),
    (71922338, 51473305, 'Front Office'),
    (59732796, 51473305, 'Services'),
    (95069897, 68207932, 'Management'),
    (80534100, 68207932, 'Housekeeping'),
    (73529145, 68207932, 'Front Office'),
    (49935712, 68207932, 'Services'),
    (50234060, 23017979, 'Management'),
    (74798403, 23017979, 'Housekeeping'),
    (86601239, 23017979, 'Front Office'),
    (13947153, 23017979, 'Services'),
    (83112427, 78263435, 'Management'),
    (70954512, 78263435, 'Housekeeping'),
    (22502829, 78263435, 'Front Office'),
    (14363114, 78263435, 'Services'),
    (28923795, 65430777, 'Management'),
    (52119529, 65430777, 'Housekeeping'),
    (95987032, 65430777, 'Front Office'),
    (92970051, 65430777, 'Services'),
    (16450258, 87616494, 'Management'),
    (03233321, 87616494, 'Housekeeping'),
    (57897769, 87616494, 'Front Office'),
    (34160795, 87616494, 'Services'),
    (42880945, 03199767, 'Management'),
    (02865928, 03199767, 'Housekeeping'),
    (70492095, 03199767, 'Front Office'),
    (16255033, 03199767, 'Services');



-- employee(employee_NAS, employee_email, employee_role, employee_dept, employee_phone_number, employee_name, employee_middle_name, employee_last_name, employee_address, employee_salary, employee_DOB, employee_registration_Date)

insert into employee values
    ('479816029', 'yToure47@eHotel.ca', 06323503, 49184917, '613-328-7710', 'Yaya', 'Gnégnéri', 'Toure', '148 Davis Drive', 32000.00, '1971-12-22', '2022-09-25'),
    ('707147102', 'cnicolson70@eHotel.ca', 11382105, 49184917, '306-398-8166', 'Cecilio', 'Lyon', 'Nicolson', '2751 rue Saint-Antoine', 68000.00, '1997-03-07', '2020-01-26'),
    ('297415402', 'fblain29@eHotel.ca', 80494580, 81986212, '905-509-1278', 'Finnley', 'Stewart', 'Blain', '4139 Queens Bay', 54000.00, '1988-03-30', '2022-05-11'),
    ('868050871', 'nneuman86@eHotel.ca', 96484593, 81986212, '519-633-3392', 'Nöl', 'Hans','Neuman', '4617 Robson St', 73000.00, '1961-03-24', '2021-09-14'),
    ('667336869', 'pmacguiness66@eHotel.ca', 94303452, 95069897, '250-239-7589', 'Pomponius', ' Record', 'MacGuinness', '2930 Algonquin Blvd', 45000.00, '1964-02-03', '2022-10-03'),
    ('685812236', 'hingham68@eHotel.ca', 33095515, 95069897, '514-487-5940', 'Hedvig', 'Loris', 'Ingham', '3072 rue Saint-Antoine', 50000.00, '1997-06-25', '2022-10-21'),
    ('575375287', 'npohl57@eHotel.ca', 19326093, 50234060, '705-750-8910', 'Nwanneka', 'Chikwangue', 'Pohl', '2537 Bayfield St', 52000.00, '1966-06-23', '2020-08-14'),
    ('804391160', 'ocosta80@eHotel.ca', 76595062, 50234060, '604-717-4077', 'Othmar', 'Ruis', 'Costa', '4645 Yonge Street', 59000.00, '1962-11-03', '2021-03-10'),
    ('906762352', 'rmaier90@eHotel.ca', 32316149, 83112427, '705-457-2897', 'Roope', 'Hintz', 'Maier', '1096 Gateway Blvd', 67000.00, '1985-07-27', '2021-07-29'),
    ('588621760', 'rnapoli58@eHotel.ca', 05433877, 83112427, '514-884-8265', 'Rolande', 'Seriea', 'Napoli', '3207 Manitoba Street', 34000.00, '1964-11-12', '2020-03-15'),
    ('555758353', 'ptremblay55@eHotel.ca', 00171745, 28923795, '306-759-2949', 'Paul', 'Guillaume', 'Tremblay', '696 Maynard Rd', 55000.00, '1970-08-26', '2021-02-27'),
    ('364290788', 'swojewoda36@eHotel.ca', 73192574, 28923795, '819-768-6128', 'Sameera','Mariam', 'Wojewoda', '4686 Granville St', 30000.00, '1995-04-11', '2020-10-12'),
    ('277230183', 'psaller27@eHotel.ca', 74331032, 16450258, '519-257-9589', 'Petko', 'Brun', 'Saller', '91 Adelaide St', 44000.00, '1989-09-06', '2021-08-14'),
    ('920148151', 'kkruse92@eHotel.ca', 13821714, 16450258, '519-223-1749', 'Krunoslav', 'Josef', 'Kruse', '3168 Jade St', 63000.00, '1960-09-16', '2022-09-19'),
    ('291601185', 'yfurlan29@eHotel.ca', 62969280, 42880945, '807-532-7338', 'Yumiko', 'Suzan', 'Furlan', '3760 Merivale Road', 43000.00, '1988-11-19', '2021-09-21'),
    ('025385320', 'hcarey02@eHotel.ca', 45024423, 42880945, '613-813-8625', 'Haji', 'Jorge', 'Carey', '1874 Toy Avenue', 64000.00, '1971-04-09', '2021-09-12');

-- department add dept manager
UPDATE department SET dept_manager_NAS = '479816029' WHERE dept_ID = 49184917;
UPDATE department SET dept_manager_NAS = '297415402' WHERE dept_ID = 81986212;
UPDATE department SET dept_manager_NAS = '667336869' WHERE dept_ID = 95069897;
UPDATE department SET dept_manager_NAS = '575375287' WHERE dept_ID = 50234060;
UPDATE department SET dept_manager_NAS = '906762352' WHERE dept_ID = 83112427;
UPDATE department SET dept_manager_NAS = '555758353' WHERE dept_ID = 28923795;
UPDATE department SET dept_manager_NAS = '277230183' WHERE dept_ID = 16450258;
UPDATE department SET dept_manager_NAS = '291601185' WHERE dept_ID = 42880945;

-- customer(customer_NAS, customer_email, customer_phone_number, customer_name, customer_middle_name, customer_last_name, customer_address, customer_DOB, customer_registration_Date)

insert into customer values
    ('978974950', 'JuliaJSingletary@rhyta.com', '917-366-9046', 'Julia', 'Jesse', 'Singletary', '1616 Bicetown Road', '1989-09-11', '2020-05-24'),
    ('431878412', 'JohnCMerwin@dayrep.com', '517-581-6231', 'John', 'Carl', 'Merwin', '2436 Elk Avenue', '1965-03-10', '2020-12-20'),
    ('374317217', 'EdwardMSavage@rhyta.com', '414-614-0197', 'Edward', 'Matthew', 'Savage', '2503 Woodlawn Drive', '1962-03-04', '2020-12-24'),
    ('063311427', 'GeorgeSHolton@rhyta.com', '925-339-7603', 'George', 'Samuel', 'Holton', '2346 Water Street', '1942-08-20', '2021-01-10'),
    ('188589033', 'KatrinaJMiller@armyspy.com', '717-868-2389', 'Katrina', 'Jane', 'Miller', '4103 Simpson Avenue', '1992-01-23', '2021-02-14'),
    ('464209644', 'AmandaLSmithson@rhyta.com', '540-798-9878', 'Amanda', 'Lynn', 'Smithson', '2568 Meadowview Drive', '1963-05-29', '2021-08-09'),
    ('308716312', 'JohnNHolub@armyspy.com', '248-548-5675', 'John', 'Nathaniel', 'Holub', '659 Bartlett Avenue', '1948-02-24', '2021-09-08'),
    ('590919716', 'KathyJGuerrera@teleworm.us', '919-868-0921', 'Kathy', 'Jasmine', 'Guerrera', '1338 Johnson Street', '2000-01-12', '2021-10-16'),
    ('546162956', 'BonnieTWhite@dayrep.com', '917-340-7050', 'Bonnie', 'Teresa', 'White', '4912 Bicetown Road', '1969-11-24', '2022-06-30'),
    ('752776445', 'ThomasCWoods@armyspy.com', '414-375-6072', 'Thomas', 'Calvin', 'Woods', '4205 Grant View Drive', '1954-01-23', '2022-11-30');


-- room_view(view_ID, hotel_ID, view_name, view_description)

insert into room_view values
    (0189, 49484327,'ocean', 'Beautiful view of the ocean'),
    (7624, 49484327,'ocean', 'Beautiful view of the ocean'),
    (7602, 03199767,'mountain', 'Beautiful view of the mountain'),
    (9490, 51473305,'city', 'Beautiful view of the city'),
    (3411, 87616494,'forest', 'Beautiful view of the forest'),
    (7216, 68207932,'desert', 'Beautiful view of the desert'),
    (5458, 65430777,'ocean', 'Beautiful view of the ocean'),
    (2516, 23017979,'mountain', 'Beautiful view of the mountain'),
    (3664, 78263435,'city', 'Beautiful view of the city'),
    (8265, 49484327,'forest', 'Beautiful view of the forest'),
    (8421, 51473305,'desert', 'Beautiful view of the desert'),
    (4197, 68207932,'ocean', 'Beautiful view of the ocean'),
    (3456, 23017979,'mountain', 'Beautiful view of the mountain'),
    (4792, 78263435,'city', 'Beautiful view of the city'),
    (7224, 65430777,'forest', 'Beautiful view of the forest'),
    (0447, 87616494,'desert', 'Beautiful view of the desert'),
    (4842, 03199767,'mountain', 'Beautiful view of the mountain'),
    (0101, 51473305,'city', 'Beautiful view of the city'),
    (0016, 87616494,'forest', 'Beautiful view of the forest'),
    (4062, 03199767,'desert', 'Beautiful view of the desert');


-- room_type(type_ID, hotel_ID, view_ID, price_per_night, capacity, room_name, room_description)

insert into room_type values
    (905114, 49484327, 0189, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (485944, 49484327, 7624, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (510189, 51473305, 7602, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (805006, 51473305, 9490, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (457984, 68207932, 3411, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (333108, 68207932, 7216, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (765842, 23017979, 5458, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (727350, 23017979, 2516, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (561561, 78263435, 3664, 75.00, 3, 'triple bedroom', 'Bedroom containing three beds'),
    (175521, 78263435, 8265, 75.00, 3, 'triple bedroom', 'Bedroom containing three beds'),
    (658808, 65430777, 8421, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (933444, 65430777, 4197, 75.00, 3, 'triple bedroom', 'Bedroom containing three beds'),
    (880316, 87616494, 3456, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (411358, 87616494, 4792, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (339049, 03199767, 7224, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (351480, 03199767, 0447, 75.00, 3, 'triple bedroom', 'Bedroom containing three beds'),
    (727566, 78263435, 4842, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (386798, 51473305, 0101, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed'),
    (334967, 87616494, 0016, 50.00, 2, 'double bedroom', 'Bedroom containing two beds'),
    (961726, 23017979, 4062, 25.00, 1, 'simple bedroom', 'Bedroom containing one bed');


-- room(roomID, hotelID, roomTypeID, occupancyStatus, room_number)

insert into room values
    (76769, 03199767, 905114, 'UNOCCUPIED', 691),
    (75541, 03199767, 485944, 'UNOCCUPIED', 547),
    (36813, 87616494, 510189, 'OCCUPIED', 867),
    (18260, 87616494, 805006, 'OCCUPIED', 044),
    (24635, 65430777, 457984, 'OCCUPIED', 323),
    (54810, 65430777, 333108, 'OCCUPIED', 960),
    (99105, 78263435, 765842, 'OCCUPIED', 491),
    (14866, 78263435, 727350, 'UNOCCUPIED', 684),
    (63326, 23017979, 561561, 'UNOCCUPIED', 657),
    (13373, 23017979, 175521, 'OCCUPIED', 292),
    (82552, 51473305, 658808, 'OCCUPIED', 389),
    (05866, 51473305, 933444, 'UNOCCUPIED', 340),
    (91972, 49484327, 880316, 'UNOCCUPIED', 711),
    (43529, 49484327, 411358, 'UNOCCUPIED', 400),
    (37814, 68207932, 339049, 'OCCUPIED', 977),
    (43743, 68207932, 351480, 'UNOCCUPIED', 352),
    (61186, 65430777, 727566, 'UNOCCUPIED', 406),
    (65512, 23017979, 386798, 'OCCUPIED', 361),
    (12066, 49484327, 334967, 'OCCUPIED', 174),
    (36872, 87616494, 961726, 'UNOCCUPIED', 398);


-- incident(incident_ID, room_ID, reporter_id, incident_description, incident_date)

insert into incident values
    (817393, 14866, '588621760', 'Apparition of roaches', '2020-06-03'),
    (965408, 75541, '025385320', 'Unlocakble door','2020-09-19'),
    (655222, 63326, '804391160', 'Dirty bedsheets','2021-11-26'),
    (874026, 43743, '685812236', 'Broken windows', '2021-12-28');


-- commodity(com_ID, room_ID, com_name)

insert into commodity values
    (9151, 76769, 'Coffe machine'),
    (4186, 75541, 'Security safe'),
    (9719, 36813, 'Air conditioner'),
    (2482, 18260, 'Television'),
    (4813, 24635, 'Fridge'),
    (2459, 54810, 'Mircowave'),
    (8970, 99105, 'Hair dryer'),
    (7705, 14866, 'Towels'),
    (9117, 63326, 'Toiletriies'),
    (1054, 13373, 'Mini bar'),
    (1645, 82552, 'WIFI'),
    (4041, 05866, 'Ironing capabilities'),
    (8884, 91972, 'Shower'),
    (2282, 43529, 'Desk'),
    (9798, 37814, 'Telephone'),
    (7431, 43743, 'Luggage storage'),
    (6002, 61186, 'Robes and slippers'),
    (4170, 65512, 'Alarm clock'),
    (4204, 12066, 'Wardrobe'),
    (3891, 36872, 'Cloth hangers');


-- extension(extension_ID, room_ID, extension_name, extension_price)

insert into extension values
    (383665, 36813, 'Add an extra bed', 5.00),
    (202123, 18260, 'Add an extra bed', 5.00),
    (334854, 24635, 'Add an extra bed', 5.00),
    (912323, 54810, 'Add an extra bed', 5.00),
    (331045, 99105, 'Add an extra bed', 5.00),
    (797808, 13373, 'Add an extra bed', 5.00),
    (053894, 82552, 'Add an extra bed', 5.00),
    (216194, 37814, 'Add an extra bed', 5.00),
    (094754, 65512, 'Add an extra bed', 5.00),
    (474148, 12066, 'Add an extra bed', 5.00),
    (664950, 76769, 'Add an extra bed', 5.00),
    (260662, 75541, 'Add an extra bed', 5.00),
    (135164, 14866, 'Add an extra bed', 5.00),
    (890750, 63326, 'Add an extra bed', 5.00),
    (228605, 05866, 'Add an extra bed', 5.00),
    (507106, 91972, 'Add an extra bed', 5.00),
    (710311, 43529, 'Add an extra bed', 5.00),
    (868165, 43743, 'Add an extra bed', 5.00),
    (189453, 61186, 'Add an extra bed', 5.00),
    (863324, 36872, 'Add an extra bed', 5.00);


-- stay(stay_ID, customer_NAS, employee_NAS, payment_total, payment_status, check_in_date, check_out_date, creation_date)

insert into stay values
    (78038116, '978974950', '479816029', 656.25, 'UNPAID', '2020-05-24', '2020-05-31', '2020-05-24'),
    (57427124, '431878412', '297415402', 312.50, 'UNPAID', '2020-12-20', '2020-12-25', '2020-12-20'),
    (35918864, '374317217', '667336869', 62.50, 'UNPAID', '2020-12-24', '2020-12-26', '2020-12-24'),
    (83930913, '063311427', '575375287', 93.75, 'UNPAID', '2021-01-10', '2021-01-13', '2021-01-10'),
    (87865335, '188589033', '906762352', 625.00, 'UNPAID', '2021-02-14', '2021-02-24', '2021-02-14');


-- reservation(reservation_ID, customer_NAS, reservation_status, special_request, total_price, check_in_date, check_out_date, creation_date)

insert into reservation values
    (56730527, '464209644', 'PENDING', 'Receive extra pillows', 187.50, '2021-08-16', '2021-08-18', '2021-08-09'),
    (37119595, '308716312', 'PENDING', 'Do not disturb', 31.25, '2021-09-09', '2021-09-10', '2021-09-08'),
    (22978826, '590919716', 'PENDING', 'Receive extra towels', 156.25, '2021-11-16', '2021-10-21', '2021-10-16'),
    (44740926, '546162956', 'PENDING', 'Do not disturb', 187.50, '2022-07-01', '2022-07-04', '2022-06-30'),
    (69178932, '752776445', 'PENDING', 'Receive extra bedsheets', 218.75, '2022-12-01', '2022-12-08', '2022-11-30');


-- extension_stay(stay_ID, extension_ID)

insert into extension_stay values
    (78038116, 797808),
    (87865335, 474148),
    (57427124, 053894);


-- extension_reservation(reservation_ID, extension_ID)

insert into extension_reservation values
    (56730527, 228605),
    (22978826, 664950),
    (44740926, 710311),
    (69178932, 189453);


-- reviews(hotel_ID, customer_NAS, review_comment, review_rating, review_date)

insert into reviews values
    (23017979, '978974950', 'The staff at this property are all great', 3, '2020-05-31'),
    (51473305, '431878412', 'I had a wonderful experience at this hotel', 5, '2020-12-25'),
    (68207932, '374317217', 'The rooms were clean', 2, '2020-12-26'),
    (49484327, '188589033', 'Will recommend to my colleagues', 4, '2021-02-24'),
    (03199767, '590919716', 'Service top notch as always', 3, '2021-10-21'),
    (65430777, '752776445', 'Property is clean and has a fantastic old time charm', 4, '2022-12-08');


-- room_reservation(room_ID, reservation_ID)

insert into room_reservation values
    (05866, 56730527),
    (91972, 37119595),
    (76769, 22978826),
    (43529, 44740926),
    (61186, 69178932);


-- room_stay(room_ID, stay_ID)

insert into room_stay values
    (13373, 78038116),
    (82552, 57427124),
    (37814, 35918864),
    (65512, 83930913),
    (12066, 87865335);