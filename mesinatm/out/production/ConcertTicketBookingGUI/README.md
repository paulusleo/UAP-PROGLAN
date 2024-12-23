Aplikasi Pemesanan Tiket Konser

Proyek ini adalah aplikasi GUI berbasis Java untuk memesan tiket konser. Aplikasi ini menyediakan antarmuka yang ramah pengguna untuk login, registrasi, memilih paket konser, dan menyelesaikan proses pemesanan.

Fitur

1. Autentikasi Pengguna

Login: Memungkinkan pengguna untuk masuk dengan kredensial mereka.

Registrasi: Menyediakan fungsi bagi pengguna baru untuk mendaftar dan membuat akun.

2. Pemilihan Tiket Konser

Melihat paket konser yang tersedia beserta harga dan ketersediaannya.

Memilih jumlah tiket dan menghitung total harga secara dinamis.

3. Integrasi Pembayaran

Mendukung berbagai metode pembayaran seperti transfer bank, e-wallet, dan kartu kredit.

Memungkinkan pengguna untuk mengunggah bukti pembayaran.

4. Konfirmasi Pesanan

Menampilkan tanda terima rinci setelah pemesanan, termasuk detail konser, jumlah tiket, dan informasi pembayaran.

Menyediakan opsi untuk mengunduh atau melihat tanda terima pembayaran.

5. Logout

Pengguna dapat keluar dari akun mereka dengan aman.

Instalasi

Prasyarat

Java Development Kit (JDK) versi 8 atau lebih baru

IDE untuk pengembangan Java (misalnya IntelliJ IDEA, Eclipse, atau NetBeans)

Langkah-langkah

Clone repositori atau unduh kode sumber.

Buka proyek di IDE Java Anda.

Kompilasi dan jalankan kelas ConcertTicketBookingGUI.

Penggunaan

Login:

Masukkan nama pengguna dan kata sandi Anda.

Jika belum memiliki akun, klik tombol "Register" untuk membuat akun.

Registrasi:

Isi nama pengguna, kata sandi, dan konfirmasi kata sandi.

Pastikan kata sandi sesuai dengan kolom konfirmasi.

Setelah berhasil mendaftar, masuk dengan kredensial baru.

Memesan Tiket:

Pilih konser dari tabel yang tersedia.

Masukkan nama Anda, NIK, dan jumlah tiket yang ingin dibeli.

Total harga akan dihitung secara otomatis.

Pembayaran:

Pilih metode pembayaran (Transfer Bank, E-Wallet, atau Kartu Kredit).

Unggah bukti pembayaran.

Konfirmasi pemesanan.

Konfirmasi Pesanan:

Lihat tanda terima rinci.

Simpan tanda terima untuk referensi di masa depan.

Logout:

Klik tombol "Logout" untuk mengakhiri sesi.

Struktur Proyek

ConcertTicketBookingGUI: Kelas utama yang berisi semua logika aplikasi.

Panel dan Komponen:

Layar login

Layar registrasi

Panel pemesanan utama

Penanganan pembayaran dan tanda terima

Kustomisasi

Perbarui paket konser dan harganya di metode createMainPanel.

Modifikasi metode pembayaran dan detail terkait di metode showPaymentOptions.

Teknologi yang Digunakan

Java Swing: Untuk membangun antarmuka pengguna grafis (GUI).

HashMap: Untuk menyimpan kredensial pengguna secara lokal.

Keterbatasan

Tidak ada integrasi database; data pengguna disimpan di memori (HashMap).

Verifikasi pembayaran disimulasikan dan tidak mencakup integrasi gateway pembayaran yang sebenarnya.

Peningkatan di Masa Depan

Menambahkan database backend untuk manajemen pengguna dan catatan pemesanan.

Mengintegrasikan gateway pembayaran nyata untuk transaksi yang lebih lancar.

Menerapkan notifikasi email untuk konfirmasi pemesanan.

Kontribusi

Jika Anda ingin berkontribusi pada proyek ini:

Fork repositori.

Buat cabang baru (feature-branch-name).

Commit perubahan Anda.

Push ke cabang tersebut.

Buka pull request.

Lisensi

Proyek ini bersifat open-source dan tersedia di bawah Lisensi MIT.

Kontak

Untuk pertanyaan atau saran, silakan hubungi penulis:

Email: example@example.com