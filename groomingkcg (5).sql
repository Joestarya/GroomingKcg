-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Jun 2025 pada 06.16
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `groomingkcg`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `datapaket`
--

CREATE TABLE `datapaket` (
  `paket` varchar(100) NOT NULL,
  `harga` float NOT NULL,
  `durasi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `datapaket`
--

INSERT INTO `datapaket` (`paket`, `harga`, `durasi`) VALUES
('Dry Grooming', 100000, 45),
('Grooming Anti Kutu', 100000, 60),
('Grooming Lengkap', 150000, 70),
('Grooming Mandi', 50000, 45),
('Mandi Air', 20000, 30);

-- --------------------------------------------------------

--
-- Struktur dari tabel `layanan`
--

CREATE TABLE `layanan` (
  `id_layanan` int(11) NOT NULL,
  `nama_pelanggan` varchar(50) DEFAULT NULL,
  `jadwal` datetime DEFAULT NULL,
  `paket` varchar(50) DEFAULT NULL,
  `jumlah_kucing` int(11) DEFAULT NULL,
  `harga_total` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `layanan`
--

INSERT INTO `layanan` (`id_layanan`, `nama_pelanggan`, `jadwal`, `paket`, `jumlah_kucing`, `harga_total`) VALUES
(2, 'Arya', '2025-06-04 12:16:36', 'Grooming Anti Kutu', 2, 200000),
(5, 'boid', '2025-06-04 10:39:11', 'Mandi Air', 5, 100000),
(6, 'minyon', '2025-06-04 10:39:11', 'Grooming Lengkap', 3, 450000),
(7, 'oni', '2025-06-04 10:39:11', 'Grooming Mandi', 1, 50000),
(8, 'cimi', '2025-06-04 10:39:11', 'Dry Grooming', 1, 100000),
(12, 'ipul', '2025-06-04 10:41:59', 'Grooming Anti Kutu', 4, 400000),
(17, 'bolang', '2025-06-04 10:47:54', 'Mandi Air', 15, 300000),
(18, 'oye', '2025-06-04 10:47:54', 'Mandi Air', 1, 20000),
(19, 'sansanmaoer', '2025-06-04 10:47:54', 'Grooming Anti Kutu', 3, 300000),
(20, 'monkeycatluna', '2025-06-04 10:47:54', 'Dry Grooming', 1, 100000),
(21, 'heoseobi', '2025-06-04 10:47:54', 'Grooming Anti Kutu', 1, 100000),
(22, 'lunaistabby', '2025-06-04 10:47:54', 'Grooming Lengkap', 4, 600000),
(25, 'miw', '2025-06-04 11:04:38', 'Grooming Lengkap', 5, 750000),
(26, 'miw', '2025-06-04 11:04:38', 'Grooming Lengkap', 4, 600000);

--
-- Trigger `layanan`
--
DELIMITER $$
CREATE TRIGGER `hitung_harga_total_insert` BEFORE INSERT ON `layanan` FOR EACH ROW BEGIN
    DECLARE harga_paket INT;

    -- Ambil harga dari table datapaket sesuai dengan nama paket
    SELECT harga INTO harga_paket
    FROM datapaket
    WHERE paket = NEW.paket;

    -- Hitung total harga
    SET NEW.harga_total = NEW.jumlah_kucing * harga_paket;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `hitung_harga_total_update` BEFORE UPDATE ON `layanan` FOR EACH ROW BEGIN
    DECLARE harga_paket INT;

    SELECT harga INTO harga_paket
    FROM datapaket
    WHERE paket = NEW.paket;

    SET NEW.harga_total = NEW.jumlah_kucing * harga_paket;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `id_admin` int(3) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`id_admin`, `username`, `password`) VALUES
(1, 'nigga', 'kon'),
(2, 'Arya', '123'),
(3, '1', '1');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `datapaket`
--
ALTER TABLE `datapaket`
  ADD PRIMARY KEY (`paket`);

--
-- Indeks untuk tabel `layanan`
--
ALTER TABLE `layanan`
  ADD PRIMARY KEY (`id_layanan`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `layanan`
--
ALTER TABLE `layanan`
  MODIFY `id_layanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT untuk tabel `login`
--
ALTER TABLE `login`
  MODIFY `id_admin` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `layanan`
--
ALTER TABLE `layanan`
  ADD CONSTRAINT `layanan_ibfk_1` FOREIGN KEY (`paket`) REFERENCES `datapaket` (`paket`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
