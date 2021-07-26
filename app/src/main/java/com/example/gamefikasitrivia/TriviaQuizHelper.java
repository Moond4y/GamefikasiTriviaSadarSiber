package com.example.gamefikasitrivia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class TriviaQuizHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";
    private static final int DB_VERSION = 3;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";
    private static final String OPTD = "OPTD";
    private static final String ANSWER = "ANSWER";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();

        arraylist.add(new TriviaQuestion("Passwordmu harus mudah diingat dan sulit ditebak, manakah diantara contoh berikut yang merupakan password yang kuat?", "Passw0rd", "09Agustus1999", "NamaMeong", "$ayN02#ackers", "$ayN02#ackers"));

        arraylist.add(new TriviaQuestion("Apakah karakteristik dari password yang kuat?", "panjang", "panjang dan acak", "panjang dan unik", "panjang, acak, dan unik", "panjang, acak, dan unik"));

        arraylist.add(new TriviaQuestion("Jika kamu ingin membagikan passwordmu dengan seseorang, apakah pilihan terbaiknya?", "kirimkan melalui email", "kirim melalui aplikasi pengirim pesan", "beritaukan melalui telepon", "jangan beritaukan", "jangan beritaukan"));

        arraylist.add(new TriviaQuestion("Sebuah program komputer atau bisa juga disebut virus yang dapat menggandakan dirinya secara sendiri dalam sistem komputer", "Trojan", "Worm", "Phising", "Keylogger", "Worm"));

        arraylist.add(new TriviaQuestion("Manakah diantara cara-cara berikut yang lebih aman dalam melakukan backup?", "satu pada harddisk eksternal dan satu lagi dalam cloud", "2 backup dalam 2 harddisk eksternal", "sebuah backup pada harddisk eksternal", "cukup backup pada cloud", "satu pada harddisk eksternal dan satu lagi dalam cloud"));

        arraylist.add(new TriviaQuestion("Ketika anda membuka website dan anda melihat ada logo gembok pada bar browser, manakah pernyataan berikut yang benar?", "Pasti website tidak mencurigakan", "100% aman", "lalu lintas antara browser dan server website aman", "tidak seorangpun bahkan ISP tau website yang kita kunjungi", "lalu lintas antara browser dan server website aman"));

        arraylist.add(new TriviaQuestion("Manakah pernyataan berikut yang benar terkait fitur incognito atau mode privat pada suatu browser", "Tidak seorangpun tau website yang kita kunjungi, bahkan ISP", "Pengguna lain pada device tersebut tidak bisa tau situs mana yang telah kita kunjungi", "Kita anonim terhadap website tersebut", "kita tidak bisa dilacak oleh penyedia layanan", "Pengguna lain pada device tersebut tidak bisa tau situs mana yang telah kita kunjungi"));

        arraylist.add(new TriviaQuestion("Akun email zimbramu sudah bocor dan dibajak oleh seseorang, manakah langkah yang harus diambil?", "ganti password zimbramu secepatnya", "informasikan kepada staf/asisten laboratorium", "mengubah password dari seluruh situs yang menggunakan password sama", "lakukan semuanya", "lakukan semuanya"));

        arraylist.add(new TriviaQuestion("Manakah contoh berikut yang merupakan PII?", "nomor KTP", "tanggal lahir", "alamat rumah", "semua jawaban benar", "semua jawaban benar"));

        arraylist.add(new TriviaQuestion("Keamanan siber yang terdapat dalam suatu organisasi atau instansi merupakan tanggung jawab dari:", "seluruh pihak dalam instansi tersebut", "bagian it", "pengamanan dalam atau satuan pengamanan", "pimpinan atau direksi", "seluruh pihak dalam instansi tersebut"));

        arraylist.add(new TriviaQuestion("Spear phising adalah", "Suatu serangan pada server email organisasi", "Sebuah usaha phising yang menargetkan seseorang", "usaha secara acak untuk membajak titik lemah", "serangan spam email secara masif pada seluruh pihak organisasi", "Sebuah usaha phising yang menargetkan seseorang"));

        arraylist.add(new TriviaQuestion("Manakah diantara hal berikut yang dapat dilakukan untuk memastikan apakah suatu situs belanja online dapat dipercaya?", "alamatnya dimulai dengan 'https://'", "terdapat gembok dan dikatakan '100% aman'", "melakukan riset untuk melihat reputasi dari website tersebut", "membaca review positif dari pelanggan di website tersebut", "melakukan riset untuk melihat reputasi dari website tersebut"));

        arraylist.add(new TriviaQuestion("Jika kita menerima telepon tak dikenal yang mengaku pihak berwenang, apa yang harus kita lakukan?", "Mengikuti arahan mereka", "memberikan password apabila diminta", "menelpon balik mereka", "matikan telepon", "matikan telepon"));

        arraylist.add(new TriviaQuestion("Jika kita menerima email mencurigakan, apa yang seharusnya kita lakukan?", "Balas email tersebut untuk mendapat kejelasan", "membuka lampiran yang ada untuk mendapat kejelasan", "menekan link yang ada agar dapat mengetahui apa maksud dari email tersebut", "laporkan email tersebut atas upaya tindakan phising", "laporkan email tersebut atas upaya tindakan phising"));

        arraylist.add(new TriviaQuestion("Manakah URL berikut yang akan membawa kita ke laman utama google?", "https://google.com", "https://gogle.com", "https://gooogle.com", "Semua benar", "Semua benar"));

        arraylist.add(new TriviaQuestion("Manakah diantara URL berikut yang TIDAK bisa digunakan dalam 'typosquatting attack'?", "http://microsoft.com", "http://mircosoft.com", "http://miroosoft.com", "Semua benar", "http://microsoft.com"));

        arraylist.add(new TriviaQuestion("Teknik cracking-password dengan menggunakan sebuah program, dan program ini sengaja dibuat untuk men-submit ribuan password hanya dalam hitungan detik", "Guessing", "Brute Force", "Cracking", "Hacking", "Brute Force"));

        arraylist.add(new TriviaQuestion("Apakah yang terjadi apabila kita membayar penjahat/hacker yang sudah mengenkripsi file dalam komputer kita dengan menggunakan ransomware?", "File kita akan dapat diakses kembali", "Mereka tidak akan menyerang lagi", "kita tidak perlu melakukan backup terhadap data kita", "semua jawaban salah", "semua jawaban salah"));

        arraylist.add(new TriviaQuestion("Apabila kita menemukan perangkat penyimpanan USB di jalan, apakah yang sebaiknya kita lakukan?", "kita ambil lalu umumkan agar ditemukan pemiliknya", "ambil dan gunakan perangkat tersebut untuk mengetahui identitas pemiliknya", "tinggalkan saja", "ambil dan laporkan ke bagian it bahwa kemungkinan terdapat malware", "ambil dan laporkan ke bagian it bahwa kemungkinan terdapat malware"));

        arraylist.add(new TriviaQuestion("Memperoleh informasi pribadi seperti password atau data-data lainnya menggunakan situs palsu, disebut dengan serangan apa?", "Phising", "Social Engineering", "Scam", "Flood", "Phising"));

        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
