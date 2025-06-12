const express = require('express');
const mysql = require('mysql2');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const PORT = 3000;

// Middleware
app.use(cors());
app.use(bodyParser.json());

// MySQL Bağlantısı
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'message_db'
});

db.connect(err => {
    if (err) {
        console.error('MySQL bağlantı hatası:', err);
        return;
    }
    console.log('MySQL veritabanına bağlanıldı');
});

// Tablo oluşturma (Eğer yoksa)
db.query(`
    CREATE TABLE IF NOT EXISTS messages (
        id INT AUTO_INCREMENT PRIMARY KEY,
        sender VARCHAR(255) NOT NULL,
        message TEXT NOT NULL,
        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    )
`, (err) => {
    if (err) console.error('Tablo oluşturma hatası:', err);
});

// Mesaj gönderme endpoint'i
app.post('/messages', (req, res) => {
    const { sender, message } = req.body;
    const query = 'INSERT INTO messages (sender, message) VALUES (?, ?)';
    
    db.query(query, [sender, message], (err, result) => {
        if (err) {
            console.error('Mesaj kaydetme hatası:', err);
            return res.status(500).send('Mesaj kaydedilemedi');
        }
        res.status(200).send('Mesaj kaydedildi');
    });
});

// Mesajları getirme endpoint'i
app.get('/messages', (req, res) => {
    const query = 'SELECT sender, message, timestamp FROM messages ORDER BY timestamp DESC LIMIT 50';
    
    db.query(query, (err, results) => {
        if (err) {
            console.error('Mesajları getirme hatası:', err);
            return res.status(500).send('Mesajlar alınamadı');
        }
        res.status(200).json(results);
    });
});

// Sunucuyu başlat
app.listen(PORT, () => {
    console.log(`Sunucu ${PORT} portunda çalışıyor`);
});