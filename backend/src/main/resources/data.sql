-- Optional: Create an admin user (password: admin123)
-- Note: Password must be BCrypt encoded. This is just for reference.
-- To create admin: Register a user normally, then update the database:
-- INSERT INTO user_roles (user_id, role) VALUES (1, 'ADMIN');

-- Sample sweets data (optional - will be created via API)
INSERT INTO sweets (name, category, price, quantity) VALUES
('Chocolate Bar', 'Chocolate', 5.99, 50),
('Lollipop', 'Candy', 2.50, 100),
('Gummy Bears', 'Gummies', 4.99, 75),
('Marshmallow', 'Soft Candy', 3.99, 60),
('Hard Candy', 'Candy', 1.99, 200)
ON CONFLICT DO NOTHING;

