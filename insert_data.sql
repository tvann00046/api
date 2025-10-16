-- 1. Thêm Roles
INSERT INTO Roles (role_name) VALUES 
('ADMIN'), 
('USER');

-- 2. Thêm Users
INSERT INTO Users (first_name, last_name, email, password, avatar, phone, address, status) VALUES
('Nguyen', 'Van A', 'a@example.com', '123456', NULL, '0912345678', 'Hà Nội', 'ACTIVE'),
('Tran', 'Thi B', 'b@example.com', '123456', NULL, '0987654321', 'TP. Hồ Chí Minh', 'BLOCKED');

-- 3. Gán Role cho User
INSERT INTO User_Role (user_id, role_id) VALUES
(1, 1), -- Admin
(2, 2); -- User

-- 4. Thêm Rạp phim (Theaters)
INSERT INTO Theaters (name, location, phone) VALUES
('CGV Vincom', 'Hà Nội', '0241234567'),
('Galaxy Nguyễn Du', 'TP.HCM', '0289876543');

-- 5. Thêm Phòng chiếu (Screens)
INSERT INTO Screens (name, seat_capacity, theater_id) VALUES
('Phòng 1', 100, 1),
('Phòng 2', 80, 1),
('Phòng VIP', 50, 2);

-- 6. Thêm Thể loại (Genre)
INSERT INTO Genre (genre_name) VALUES
('Hành động'),
('Tình cảm'),
('Hài hước');

-- 7. Thêm Phim (Movies)
INSERT INTO movies (title, descriptions, author, image, trailer, type, duration, release_date) VALUES
('Avengers: Endgame', 'Phim siêu anh hùng nổi tiếng của Marvel', 'Marvel Studios', 'avengers.jpg', 'avengers.mp4', '3D', 180, '2019-04-26'),
('Titanic', 'Phim tình cảm kinh điển', 'James Cameron', 'titanic.jpg', 'titanic.mp4', '2D', 195, '1997-12-19');

-- 8. Gán Thể loại cho Phim (Movie_Genre)
INSERT INTO movie_genre (movie_id, genre_id) VALUES
(1, 1), -- Avengers: Hành động
(2, 2); -- Titanic: Tình cảm

-- 9. Thêm Suất chiếu (Showtimes)
INSERT INTO showtimes (screen_id, movie_id, start_time, end_time) VALUES
(1, 1, '2025-10-05 18:00:00', '2025-10-05 21:00:00'),
(2, 2, '2025-10-06 20:00:00', '2025-10-06 23:15:00');

-- 10. Thêm Ghế (Seats)
INSERT INTO seats (screen_id, seat_number, is_variable, type) VALUES
(1, 'A1', 0, 'STANDARD'),
(1, 'A2', 0, 'STANDARD'),
(2, 'B1', 0, 'VIP');

-- 11. Thêm Đặt vé (Bookings)
INSERT INTO bookings (user_id, showtime_id, total_seat, total_price_movie) VALUES
(1, 1, 2, 200000);

-- 12. Thêm Ghế trong đặt vé (Booking_Seat)
INSERT INTO booking_seat (booking_id, seat_id, quantity) VALUES
(1, 1, 1),
(1, 2, 1);

-- 13. Thêm Banner
INSERT INTO banners (url, type, position) VALUES
('banner1.jpg', 'IMAGE', 'HOME_TOP'),
('banner2.mp4', 'VIDEO', 'HOME_MIDDLE');

-- 14. Thêm Lễ hội (Festivals)
INSERT INTO festivals (title, image, start_time, end_time) VALUES
('Liên hoan phim Việt Nam', 'festival.jpg', '2025-11-01 18:00:00', '2025-11-07 22:00:00');

-- 15. Thêm Tin tức (News)
INSERT INTO news (title, content, festival_id) VALUES
('Khai mạc Liên hoan phim', 'Nội dung khai mạc...', 1);

-- 16. Thêm Giá vé (Ticket Prices)
INSERT INTO ticket_prices (type_seat, type_movie, price, day_type, start_time, end_time) VALUES
('STANDARD', '2D', 70000, 0, '08:00:00', '17:00:00'),
('VIP', '3D', 120000, 1, '17:00:00', '23:59:59');

-- 17. Thêm Thanh toán (Payments)
INSERT INTO payments (booking_id, payment_method, payment_status, amount, transaction_id) VALUES
(1, 'VNPAY', 'COMPLETED', 200000, 'TX123456');
