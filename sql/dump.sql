CREATE DATABASE IF NOT EXISTS nexus_erp;
USE nexus_erp;

DROP TABLE IF EXISTS server_logs;

CREATE TABLE server_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    server_name VARCHAR(50) NOT NULL,
    region VARCHAR(30) DEFAULT NULL,
    status VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO server_logs (server_name, region, status) VALUES
('SRV-ALPHA-01', 'Europe', 'Online'),
('SRV-BETA-02', 'Asie', 'Online'),
('SRV-GAMMA-03', NULL, 'Offline'),
('SRV-DELTA-04', 'Amerique', 'Online'),
('SRV-EPSILON-05', 'Europe', 'Offline'),
('SRV-ZETA-06', 'Asie', 'Online'),
('SRV-ETA-07', NULL, 'Offline'),
('SRV-THETA-08', 'Amerique', 'Online'),
('SRV-IOTA-09', 'Europe', 'Online'),
('SRV-KAPPA-10', 'Asie', 'Offline');