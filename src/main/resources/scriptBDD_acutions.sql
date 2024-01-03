USE ENI_AUCTIONS
GO

-- Database creation script for AUCTIONS
--   type:      SQL Server

-- --------------------------------------------------------

--
-- Table structure for table 'articles'
--
CREATE TABLE articles (
  article_id INT IDENTITY(1,1) NOT NULL,
  article_name NVARCHAR(30) NOT NULL,
  description NVARCHAR(300) NOT NULL,
  start_auction_date DATE NOT NULL,
  end_auction_date DATE NOT NULL,
  initial_price INT DEFAULT NULL,
  final_price INT DEFAULT NULL,
  user_id INT NOT NULL,
  category_id INT NOT NULL,
  CONSTRAINT article_id_pk PRIMARY KEY (article_id),
);

-- --------------------------------------------------------

--
-- Table structure for table 'bids'
--
CREATE TABLE bids (
  bid_id INT IDENTITY(1,1) NOT NULL,
  bid_date DATETIME NOT NULL,
  bid_time DATETIME NOT NULL,
  bid_amount INT NOT NULL,
  article_id INT NOT NULL,
  user_id INT NOT NULL,
  CONSTRAINT bid_id_pk PRIMARY KEY (bid_id),
);

-- --------------------------------------------------------

--
-- Table structure for table 'categories'
--
CREATE TABLE categories (
  category_id INT IDENTITY(1,1) NOT NULL,
  category_name NVARCHAR(30) NOT NULL,
  CONSTRAINT category_id_pk PRIMARY KEY (category_id)
);

-- --------------------------------------------------------

--
-- Table structure for table 'users'
--
CREATE TABLE users (
  user_id INT IDENTITY(1,1) NOT NULL,
  username NVARCHAR(30) NOT NULL,
  last_name NVARCHAR(30) NOT NULL,
  first_name NVARCHAR(30) NOT NULL,
  email NVARCHAR(50) NOT NULL,
  phone NVARCHAR(15) DEFAULT NULL,
  street NVARCHAR(30) NOT NULL,
  postal_code NVARCHAR(10) NOT NULL,
  city NVARCHAR(50) NOT NULL,
  password NVARCHAR(30) NOT NULL,
  credits INT NOT NULL,
  is_admin TINYINT NOT NULL,
  CONSTRAINT user_id_pk PRIMARY KEY (user_id)
);

-- --------------------------------------------------------

--
-- Table structure for table 'withdrawals'
--
CREATE TABLE withdrawals (
  article_id INT NOT NULL,
  street NVARCHAR(30) NOT NULL,
  postal_code NVARCHAR(15) NOT NULL,
  city NVARCHAR(30) NOT NULL,
  CONSTRAINT withdrawals_article_id_pk PRIMARY KEY (article_id),
 
);


------------------------------------------------------------------

--
-- Ajout Foreign keys
--

ALTER TABLE articles ADD CONSTRAINT articles_user_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
ALTER TABLE articles ADD CONSTRAINT articles_category_fk FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE NO ACTION ON UPDATE NO ACTION

ALTER TABLE bids ADD CONSTRAINT bids_user_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
ALTER TABLE bids ADD CONSTRAINT bids_article_fk FOREIGN KEY (article_id) REFERENCES articles (article_id) ON DELETE NO ACTION ON UPDATE NO ACTION

ALTER TABLE withdrawals ADD  CONSTRAINT withdrawals_article_fk FOREIGN KEY (article_id) REFERENCES articles (article_id) ON DELETE NO ACTION ON UPDATE NO ACTION

DROP TABLE bids