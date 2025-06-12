const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
app.use(cors());
app.use(bodyParser.json());

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'kitaplik'
});

conn.connect(err => {
    if (err) throw err;
    console.log('MySQL bağlantısı başarılı');
});

// Kayıt Ekle
app.post('/ekle', (req, res) => {
    const { isbn_no, kitap_adi, basim_yili } = req.body;
    const sql = "INSERT INTO kitaplar (isbn_no, kitap_adi, basim_yili) VALUES (?, ?, ?)";
    conn.query(sql, [isbn_no, kitap_adi, basim_yili], (err, result) => {
        if (err) return res.status(500).send(err);
        res.send("Kayıt eklendi");
    });
});

// Kayıt Ara
app.get('/ara/:isbn_no', (req, res) => {
    const isbn = req.params.isbn_no;
    const sql = "SELECT * FROM kitaplar WHERE isbn_no = ?";
    conn.query(sql, [isbn], (err, result) => {
        if (err) return res.status(500).send(err);
        res.json(result);
    });
});

// Listele
app.get('/listele', (req, res) => {
    conn.query("SELECT * FROM kitaplar", (err, results) => {
        if (err) return res.status(500).send(err);
        res.json(results);
    });
});

app.listen(3000, () => {
    console.log('Sunucu çalışıyor: http://localhost:3000');
});
