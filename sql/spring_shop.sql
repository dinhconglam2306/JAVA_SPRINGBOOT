-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2025-08-26 11:33:38
-- サーバのバージョン： 10.4.32-MariaDB
-- PHP のバージョン: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `spring_shop`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `articles`
--

CREATE TABLE `articles` (
  `id` bigint(20) NOT NULL,
  `content` mediumtext NOT NULL,
  `name` varchar(100) NOT NULL,
  `ordering` int(11) NOT NULL,
  `public_date` datetime(6) NOT NULL,
  `slug` varchar(100) NOT NULL,
  `special` bit(1) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `author_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `articles`
--

INSERT INTO `articles` (`id`, `content`, `name`, `ordering`, `public_date`, `slug`, `special`, `status`, `author_id`, `category_id`) VALUES
(1, 'Kinh tế Mỹ - Canada gắn bó với nhau như thế nàoKinh tế Mỹ - Canada gắn bó với nhau như thế nàoKinh tế Mỹ - Canada gắn bó với nhau như thế nàoKinh tế Mỹ - Canada gắn bó với nhau như thế nàoKinh tế Mỹ - Canada gắn bó với nhau như thế nào', 'Kinh tế Mỹ - Canada gắn bó với nhau như thế nào', 1, '2025-08-24 09:26:00.000000', 'kinh-te-my-canada-gan-bo-voi-nhau-nhu-the-nao', b'1', 'ACTIVE', 1, 1),
(2, 'Có thể điều chỉnh giảm trừ gia cảnh cá nhân vào tháng 10Có thể điều chỉnh giảm trừ gia cảnh cá nhân vào tháng 10Có thể điều chỉnh giảm trừ gia cảnh cá nhân vào tháng 10Có thể điều chỉnh giảm trừ gia cảnh cá nhân vào tháng 10', 'Có thể điều chỉnh giảm trừ gia cảnh cá nhân vào tháng 10', 2, '2025-08-15 09:27:00.000000', 'co-the-dieu-chinh-giam-tru-gia-canh-ca-nhan-vao-thang-10', b'1', 'ACTIVE', 1, 1),
(3, 'Greenland có giá trị kinh tế ra sao khiến ông Trump muốn mua?Greenland có giá trị kinh tế ra sao khiến ông Trump muốn mua?Greenland có giá trị kinh tế ra sao khiến ông Trump muốn mua?Greenland có giá trị kinh tế ra sao khiến ông Trump muốn mua?', 'Greenland có giá trị kinh tế ra sao khiến ông Trump muốn mua?', 3, '2025-08-19 09:27:00.000000', 'greenland-co-gia-tri-kinh-te-ra-sao-khien-ong-trump-muon-mua', b'1', 'ACTIVE', 1, 1),
(4, 'Nghiên cứu chuyển một số tập đoàn, tổng công ty về Chính phủNghiên cứu chuyển một số tập đoàn, tổng công ty về Chính phủNghiên cứu chuyển một số tập đoàn, tổng công ty về Chính phủNghiên cứu chuyển một số tập đoàn, tổng công ty về Chính phủNghiên cứu chuyển một số tập đoàn, tổng công ty về Chính phủ', 'Nghiên cứu chuyển một số tập đoàn, tổng công ty về Chính phủ', 4, '2025-07-29 09:28:00.000000', 'nghien-cuu-chuyen-mot-so-tap-doan-tong-cong-ty-ve-chinh-phu', b'1', 'ACTIVE', 2, 1),
(5, 'Người Việt đầu tiên đoạt giải thưởng thiên văn quốc tế Đài LoanNgười Việt đầu tiên đoạt giải thưởng thiên văn quốc tế Đài LoanNgười Việt đầu tiên đoạt giải thưởng thiên văn quốc tế Đài Loan', 'Người Việt đầu tiên đoạt giải thưởng thiên văn quốc tế Đài Loan', 5, '2025-08-13 09:28:00.000000', 'nguoi-viet-dau-tien-doat-giai-thuong-thien-van-quoc-te-dai-loan', b'1', 'ACTIVE', 3, 2),
(6, 'Những ảnh hưởng của biến đổi khí hậu năm 2024Những ảnh hưởng của biến đổi khí hậu năm 2024Những ảnh hưởng của biến đổi khí hậu năm 2024Những ảnh hưởng của biến đổi khí hậu năm 2024Những ảnh hưởng của biến đổi khí hậu năm 2024Những ảnh hưởng của biến đổi khí hậu năm 2024', 'Những ảnh hưởng của biến đổi khí hậu năm 2024', 6, '2025-08-06 09:29:00.000000', 'nhung-anh-huong-cua-bien-doi-khi-hau-nam-2024', b'1', 'ACTIVE', 2, 2),
(7, 'Pin giấy phân hủy hoàn toàn chỉ trong 6 tuầnPin giấy phân hủy hoàn toàn chỉ trong 6 tuầnPin giấy phân hủy hoàn toàn chỉ trong 6 tuầnPin giấy phân hủy hoàn toàn chỉ trong 6 tuầnPin giấy phân hủy hoàn toàn chỉ trong 6 tuần', 'Pin giấy phân hủy hoàn toàn chỉ trong 6 tuần', 7, '2025-07-15 09:29:00.000000', 'pin-giay-phan-huy-hoan-toan-chi-trong-6-tuan', b'1', 'ACTIVE', 1, 1),
(8, 'Hợp kim chịu nhiệt 1.700 độ C cho phương tiện siêu thanhHợp kim chịu nhiệt 1.700 độ C cho phương tiện siêu thanhHợp kim chịu nhiệt 1.700 độ C cho phương tiện siêu thanhHợp kim chịu nhiệt 1.700 độ C cho phương tiện siêu thanh', 'Hợp kim chịu nhiệt 1.700 độ C cho phương tiện siêu thanh', 8, '2025-08-06 09:30:00.000000', 'hop-kim-chiu-nhiet-1700-do-c-cho-phuong-tien-sieu-thanh', b'1', 'ACTIVE', 3, 2),
(9, 'Runner Việt Nam chạy 42 giải marathon năm 2024Runner Việt Nam chạy 42 giải marathon năm 2024Runner Việt Nam chạy 42 giải marathon năm 2024Runner Việt Nam chạy 42 giải marathon năm 2024Runner Việt Nam chạy 42 giải marathon năm 2024', 'Runner Việt Nam chạy 42 giải marathon năm 2024', 9, '2025-08-06 09:31:00.000000', 'runner-viet-nam-chay-42-giai-marathon-nam-2024', b'0', 'ACTIVE', 2, 3),
(10, 'Bàn của Supachok vào lưới Việt Nam dẫn đầu đề cửBàn của Supachok vào lưới Việt Nam dẫn đầu đề cửBàn của Supachok vào lưới Việt Nam dẫn đầu đề cửBàn của Supachok vào lưới Việt Nam dẫn đầu đề cửBàn của Supachok vào lưới Việt Nam dẫn đầu đề cửBàn của Supachok vào lưới Việt Nam dẫn đầu đề cử', 'Bàn của Supachok vào lưới Việt Nam dẫn đầu đề cử', 10, '2025-08-26 09:31:00.000000', 'ban-cua-supachok-vao-luoi-viet-nam-dan-dau-de-cu', b'1', 'ACTIVE', 1, 3),
(11, 'Lợi ích của chạy 10 phút mỗi ngàyLợi ích của chạy 10 phút mỗi ngàyLợi ích của chạy 10 phút mỗi ngàyLợi ích của chạy 10 phút mỗi ngàyLợi ích của chạy 10 phút mỗi ngàyLợi ích của chạy 10 phút mỗi ngàyLợi ích của chạy 10 phút mỗi ngày', 'Lợi ích của chạy 10 phút mỗi ngày', 11, '2025-08-08 09:32:00.000000', 'loi-ich-cua-chay-10-phut-moi-ngay', b'1', 'ACTIVE', 1, 3),
(12, 'Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024', 'Rodri chỉ hơn Vinicius 4% số điểm Quả Bóng Vàng 2024', 12, '2025-08-15 09:32:00.000000', 'rodri-chi-hon-vinicius-4-so-diem-qua-bong-vang-2024', b'0', 'ACTIVE', 1, 3);

-- --------------------------------------------------------

--
-- テーブルの構造 `authors`
--

CREATE TABLE `authors` (
  `id` bigint(20) NOT NULL,
  `content` mediumtext NOT NULL,
  `name` varchar(100) NOT NULL,
  `ordering` int(11) NOT NULL,
  `slug` varchar(100) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `authors`
--

INSERT INTO `authors` (`id`, `content`, `name`, `ordering`, `slug`, `status`) VALUES
(1, 'ABCD', 'Đinh Công Lâm', 1, 'dinh-cong-lam', 'ACTIVE'),
(2, 'abcd123', 'Đinh Hà Khánh Vy', 2, 'dinh-ha-khanh-vy', 'ACTIVE'),
(3, '123123123', 'Đinh Công Gia Bảo', 3, 'dinh-cong-gia-bao', 'ACTIVE'),
(4, '123123', 'Hà Thị Út Nhung', 4, 'ha-thi-ut-nhung', 'ACTIVE');

-- --------------------------------------------------------

--
-- テーブルの構造 `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `is_home_page` bit(1) NOT NULL,
  `name` varchar(100) NOT NULL,
  `ordering` int(11) NOT NULL,
  `slug` varchar(100) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- テーブルのデータのダンプ `categories`
--

INSERT INTO `categories` (`id`, `is_home_page`, `name`, `ordering`, `slug`, `status`) VALUES
(1, b'1', 'Kinh tế', 1, 'kinh-te', 'ACTIVE'),
(2, b'1', 'Khoa học', 2, 'khoa-hoc', 'ACTIVE'),
(3, b'1', 'Thể thao', 3, 'the-thao', 'ACTIVE');

-- --------------------------------------------------------

--
-- テーブルの構造 `items`
--

CREATE TABLE `items` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `ordering` int(11) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsn7al9fwhgtf98rvn8nxhjt4f` (`slug`),
  ADD KEY `FKglvhv5e43dmjhmiovwhcax7aq` (`author_id`),
  ADD KEY `FK7i4rryg7kqwyyrr08temnc71e` (`category_id`);

--
-- テーブルのインデックス `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK50058x3s3i9lcreapeth7bbsw` (`slug`);

--
-- テーブルのインデックス `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKoul14ho7bctbefv8jywp5v3i2` (`slug`);

--
-- テーブルのインデックス `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `articles`
--
ALTER TABLE `articles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- テーブルの AUTO_INCREMENT `authors`
--
ALTER TABLE `authors`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- テーブルの AUTO_INCREMENT `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- テーブルの AUTO_INCREMENT `items`
--
ALTER TABLE `items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- ダンプしたテーブルの制約
--

--
-- テーブルの制約 `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `FK7i4rryg7kqwyyrr08temnc71e` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `FKglvhv5e43dmjhmiovwhcax7aq` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
