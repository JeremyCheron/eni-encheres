USE ENI_AUCTIONS
GO


DELETE FROM bids;
DELETE FROM withdrawals;
DELETE FROM categories;
DELETE FROM articles;
DELETE FROM users;

INSERT INTO categories VALUES ('Multimedia');
INSERT INTO categories VALUES ('Home');
INSERT INTO categories VALUES ('Fashion');
INSERT INTO categories VALUES ('Leisure');