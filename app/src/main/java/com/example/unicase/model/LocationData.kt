package com.example.unicase.model


class LocationData {

    data class Provinsi(
        val nama: String,
        val daftarkabupatenKota: List<KabupatenKota>
    )
    data class KabupatenKota(
        val nama: String,
        val daftarKecamatan: List<Kecamatan>
    )
    data class Kecamatan(
        val nama: String,
        val daftarKodePos: List<String>
    )

    // PROVINSI ACEH "Nanggroe Aceh Darussalam"
    val provinsiAceh = Provinsi(
        nama = "Nanggroe Aceh Darussalam",
        daftarkabupatenKota = listOf(
            KabupatenKota(
                nama = "Kabupaten Aceh Barat",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Arongan Lambalek",
                        daftarKodePos = listOf("23652")
                    ),
                    Kecamatan(
                        nama = "Bubon",
                        daftarKodePos = listOf("23651")
                    ),
                    Kecamatan(
                        nama = "Panton Reu",
                        daftarKodePos = listOf("23684")
                    ),
                    Kecamatan(
                        nama = "Johan Pahlawan",
                        daftarKodePos = listOf("23611", "23612", "23613", "23614", "23615", "23616", "23617", "23618")
                    ),
                    Kecamatan(
                        nama = "Meureubo",
                        daftarKodePos = listOf("23615")
                    ),
                    Kecamatan(
                        nama = "Kaway XVI",
                        daftarKodePos = listOf("23681")
                    ),
                    Kecamatan(
                        nama = "Sungai Mas",
                        daftarKodePos = listOf("23685")
                    ),
                    Kecamatan(
                        nama = "Pante Ceureumen",
                        daftarKodePos = listOf("23680")
                    ),
                    Kecamatan(
                        nama = "Samatiga",
                        daftarKodePos = listOf("23650")
                    ),
                    Kecamatan(
                        nama = "Woyla",
                        daftarKodePos = listOf("23654")
                    ),
                    Kecamatan(
                        nama = "Woyla Barat",
                        daftarKodePos = listOf("23682")
                    ),
                    Kecamatan(
                        nama = "Woylla Timur",
                        daftarKodePos = listOf("23683")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Barat Daya",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Blang Pidie",
                        daftarKodePos = listOf("23764")
                    ),
                    Kecamatan(
                        nama = "Jeumpa",
                        daftarKodePos = listOf("23769")
                    ),
                    Kecamatan(
                        nama = "Kuala Batee",
                        daftarKodePos = listOf("23766")
                    ),
                    Kecamatan(
                        nama = "Lembah Sabil",
                        daftarKodePos = listOf("23762")
                    ),
                    Kecamatan(
                        nama = "Manggeng",
                        daftarKodePos = listOf("23760")
                    ),
                    Kecamatan(
                        nama = "Setia",
                        daftarKodePos = listOf("23763")
                    ),
                    Kecamatan(
                        nama = "Susoh",
                        daftarKodePos = listOf("23765")
                    ),
                    Kecamatan(
                        nama = "Tangan‑Tangan",
                        daftarKodePos = listOf("23768")
                    ),
                    Kecamatan(
                        nama = "Babah Rot",
                        daftarKodePos = listOf("23767")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Besar",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Pulo Aceh",
                        daftarKodePos = listOf("23991")
                    ),
                    Kecamatan(
                        nama = "Peukan Bada",
                        daftarKodePos = listOf("23351")
                    ),
                    Kecamatan(
                        nama = "Lhoknga",
                        daftarKodePos = listOf("23355")
                    ),
                    Kecamatan(
                        nama = "Leupung",
                        daftarKodePos = listOf("23353")
                    ),
                    Kecamatan(
                        nama = "Lhoong",
                        daftarKodePos = listOf("23354")
                    ),
                    Kecamatan(
                        nama = "Kuta Cot Glie",
                        daftarKodePos = listOf("23364")
                    ),
                    Kecamatan(
                        nama = "Seulimeum",
                        daftarKodePos = listOf("23951")
                    ),
                    Kecamatan(
                        nama = "Kota Jantho",
                        daftarKodePos = listOf("23918", "23919")
                    ),
                    Kecamatan(
                        nama = "Lembah Seulawah",
                        daftarKodePos = listOf("23952")
                    ),
                    Kecamatan(
                        nama = "Mesjid Raya",
                        daftarKodePos = listOf("23381")
                    ),
                    Kecamatan(
                        nama = "Darussalam",
                        daftarKodePos = listOf("23374")
                    ),
                    Kecamatan(
                        nama = "Baitussalam",
                        daftarKodePos = listOf("23373")
                    ),
                    Kecamatan(
                        nama = "Kuta Baro",
                        daftarKodePos = listOf("23372")
                    ),
                    Kecamatan(
                        nama = "Montasik",
                        daftarKodePos = listOf("23362")
                    ),
                    Kecamatan(
                        nama = "Blang Bintang",
                        daftarKodePos = listOf("23360")
                    ),
                    Kecamatan(
                        nama = "Indrapuri",
                        daftarKodePos = listOf("23363")
                    ),
                    Kecamatan(
                        nama = "Kuta Malaka",
                        daftarKodePos = listOf("23365")
                    ),
                    Kecamatan(
                        nama = "Suka Makmur",
                        daftarKodePos = listOf("23361")
                    ),
                    Kecamatan(
                        nama = "Simpang Tiga",
                        listOf("23375")
                    ),
                    Kecamatan(
                        nama = "Darul Kamal",
                        daftarKodePos = listOf("23350")
                    ),
                    Kecamatan(
                        nama = "Darul Imarah",
                        daftarKodePos = listOf("23352")
                    ),
                    Kecamatan(
                        nama = "Ingin Jaya",
                        daftarKodePos = listOf("23371")
                    ),
                    Kecamatan(
                        nama = "Krueng Barona Jaya",
                        daftarKodePos = listOf("23370")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Jaya",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Teunom",
                        daftarKodePos = listOf("23653")
                    ),
                    Kecamatan(
                        nama = "Pasie Raya",
                        daftarKodePos = listOf("23653")
                    ),
                    Kecamatan(
                        nama = "Panga",
                        daftarKodePos = listOf("23653")
                    ),
                    Kecamatan(
                        nama = "Krueng Sabee",
                        daftarKodePos = listOf("23654")
                    ),
                    Kecamatan(
                        nama = "Setia Bhakti",
                        daftarKodePos = listOf("23655")
                    ),
                    Kecamatan(
                        nama = "Sampoiniet",
                        daftarKodePos = listOf("23659")
                    ),
                    Kecamatan(
                        nama = "Darul Hikmah",
                        daftarKodePos = listOf("23656")
                    ),
                    Kecamatan(
                        nama = "Jaya",
                        daftarKodePos = listOf("23658")
                    ),
                    Kecamatan(
                        nama = "Indra Jaya",
                        daftarKodePos = listOf("23657")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Selatan",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Singkil",
                        daftarKodePos = listOf("24785")
                    ),
                    Kecamatan(
                        nama = "Singkil Utara",
                        daftarKodePos = listOf("24785")
                    ),
                    Kecamatan(
                        nama = "Gunung Meriah",
                        daftarKodePos = listOf("24782")
                    ),
                    Kecamatan(
                        nama = "Kota Baharu",
                        daftarKodePos = listOf("24787")
                    ),
                    Kecamatan(
                        nama = "Simpang Kanan",
                        daftarKodePos = listOf("24783")
                    ),
                    Kecamatan(
                        nama = "Danau Paris",
                        daftarKodePos = listOf("24788")
                    ),
                    Kecamatan(
                        nama = "Suro",
                        daftarKodePos = listOf("24784")
                    ),
                    Kecamatan(
                        nama = "Singkohor",
                        daftarKodePos = listOf("24789")
                    ),
                    Kecamatan(
                        nama = "Kuala Baru",
                        daftarKodePos = listOf("24786")
                    ),
                    Kecamatan(
                        nama = "Pulau Banyak",
                        daftarKodePos = listOf("24791")
                    ),
                    Kecamatan(
                        nama = "Pulau Banyak Barat",
                        daftarKodePos = listOf("24792")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Singkil",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Singkil",
                        daftarKodePos = listOf("24785")
                    ),
                    Kecamatan(
                        nama = "Singkil Utara",
                        daftarKodePos = listOf("24785")
                    ),
                    Kecamatan(
                        nama = "Gunung Meriah",
                        daftarKodePos = listOf("24782")
                    ),
                    Kecamatan(
                        nama = "Kota Baharu",
                        daftarKodePos = listOf("24787")
                    ),
                    Kecamatan(
                        nama = "Simpang Kanan",
                        daftarKodePos = listOf("24783")
                    ),
                    Kecamatan(
                        nama = "Danau Paris",
                        daftarKodePos = listOf("24788")
                    ),
                    Kecamatan(
                        nama = "Suro",
                        daftarKodePos = listOf("24784")
                    ),
                    Kecamatan(
                        nama = "Singkohor",
                        daftarKodePos = listOf("24789")
                    ),
                    Kecamatan(
                        nama = "Kuala Baru",
                        daftarKodePos = listOf("24786")
                    ),
                    Kecamatan(
                        nama = "Pulau Banyak",
                        daftarKodePos = listOf("24791")
                    ),
                    Kecamatan(
                        nama = "Pulau Banyak Barat",
                        daftarKodePos = listOf("24792")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Tamiang",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Banda Mulia",
                        daftarKodePos = listOf("24472")
                    ),
                    Kecamatan(
                        nama = "Bandar Pusaka",
                        daftarKodePos = listOf("24478")
                    ),
                    Kecamatan(
                        nama = "Bendahara",
                        daftarKodePos = listOf("24470")
                    ),
                    Kecamatan(
                        nama = "Karang Baru",
                        daftarKodePos = listOf("24476")
                    ),
                    Kecamatan(
                        nama = "Kejuruan Muda",
                        daftarKodePos = listOf("24477")
                    ),
                    Kecamatan(
                        nama = "Kota Kuala Simpang",
                        daftarKodePos = listOf("24475")
                    ),
                    Kecamatan(
                        nama = "Manyak Payed",
                        daftarKodePos = listOf("24471")
                    ),
                    Kecamatan(
                        nama = "Rantau",
                        daftarKodePos = listOf("24474")
                    ),
                    Kecamatan(
                        nama = "Seruway",
                        daftarKodePos = listOf("24473")
                    ),
                    Kecamatan(
                        nama = "Tamiang Hulu",
                        daftarKodePos = listOf("24479")
                    ),
                    Kecamatan(
                        nama = "Tenggulun",
                        daftarKodePos = listOf("24477")
                    ), // berbagi dengan Kejuruan Muda
                    Kecamatan(
                        nama = "Sekerak",
                        daftarKodePos = listOf("24476")
                    )   // berbagi dengan Karang Baru
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Tengah",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Atu Lintang",
                        daftarKodePos = listOf("24563")
                    ),
                    Kecamatan(
                        nama = "Bebesen",
                        daftarKodePos = listOf("24552")
                    ),
                    Kecamatan(
                        nama = "Bies",
                        daftarKodePos = listOf("24561")
                    ),
                    Kecamatan(
                        nama = "Bintang",
                        daftarKodePos = listOf("24571")
                    ),
                    Kecamatan(
                        nama = "Celala",
                        daftarKodePos = listOf("24562")
                    ),
                    Kecamatan(
                        nama = "Jagong Jeget",
                        daftarKodePos = listOf("24564")
                    ),
                    Kecamatan(
                        nama = "Kebayakan",
                        daftarKodePos = listOf("24517", "24519")
                    ),
                    Kecamatan(
                        nama = "Ketol",
                        daftarKodePos = listOf("24566")
                    ),
                    Kecamatan(
                        nama = "Kute Panang",
                        daftarKodePos = listOf("24568")
                    ),
                    Kecamatan(
                        nama = "Linge",
                        daftarKodePos = listOf("24565")
                    ),
                    Kecamatan(
                        nama = "Lut Tawar",
                        daftarKodePos = listOf("24511", "24512", "24513", "24514", "24515", "24516")
                    ),
                    Kecamatan(
                        nama = "Pegasing",
                        daftarKodePos = listOf("24560")
                    ),
                    Kecamatan(
                        nama = "Rusip Antara",
                        daftarKodePos = listOf("24567")
                    ),
                    Kecamatan(
                        nama = "Silih Nara",
                        daftarKodePos = listOf("24569")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Tenggara",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Lawe Alas",
                        daftarKodePos = listOf("24661")
                    ),
                    Kecamatan(
                        nama = "Babul Rahmah",
                        daftarKodePos = listOf("24675")
                    ),
                    Kecamatan(
                        nama = "Tanoh Alas",
                        daftarKodePos = listOf("24674")
                    ),
                    Kecamatan(
                        nama = "Lawe Sigala‑Gala",
                        daftarKodePos = listOf("24676")
                    ),
                    Kecamatan(
                        nama = "Babul Makmur",
                        daftarKodePos = listOf("24673")
                    ),
                    Kecamatan(
                        nama = "Semadam",
                        daftarKodePos = listOf("24678")
                    ),
                    Kecamatan(
                        nama = "Leuser",
                        daftarKodePos = listOf("24677")
                    ),
                    Kecamatan(
                        nama = "Bambel",
                        daftarKodePos = listOf("24672")
                    ),
                    Kecamatan(
                        nama = "Bukit Tusam",
                        daftarKodePos = listOf("24671")
                    ),
                    Kecamatan(
                        nama = "Darul Hasanah",
                        daftarKodePos = listOf("24663")
                    ),
                    Kecamatan(
                        nama = "Deleng Pokhisen",
                        daftarKodePos = listOf("24660")
                    ),
                    Kecamatan(
                        nama = "Ketambe",
                        daftarKodePos = listOf("24662")
                    ),
                    Kecamatan(
                        nama = "Babussalam",
                        daftarKodePos = listOf("24664")
                    ),
                    Kecamatan(
                        nama = "Lawe Bulan",
                        daftarKodePos = listOf("24665")
                    ),
                    Kecamatan(
                        nama = "Lawe Sumur",
                        daftarKodePos = listOf("24670")
                    ),
                    Kecamatan(
                        nama = "Badar",
                        daftarKodePos = listOf("24666")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Timur",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Banda Alam",
                        daftarKodePos = listOf("24458")
                    ),
                    Kecamatan(
                        nama = "Birem Bayeun",
                        daftarKodePos = listOf("24452")
                    ),
                    Kecamatan(
                        nama = "Darul Aman",
                        daftarKodePos = listOf("24455")
                    ),
                    Kecamatan(
                        nama = "Darul Falah",
                        daftarKodePos = listOf("24454")
                    ),
                    Kecamatan(
                        nama = "Darul Iksan",
                        daftarKodePos = listOf("24468")
                    ),
                    Kecamatan(
                        nama = "Idi Rayeuk",
                        daftarKodePos = listOf("24442")
                    ),
                    Kecamatan(
                        nama = "Idi Timur",
                        daftarKodePos = listOf("24456")
                    ),
                    Kecamatan(
                        nama = "Idi Tunong",
                        daftarKodePos = listOf("24443")
                    ),
                    Kecamatan(
                        nama = "Indra Makmur",
                        daftarKodePos = listOf("24457")
                    ),
                    Kecamatan(
                        nama = "Julok",
                        daftarKodePos = listOf("24459")
                    ),
                    Kecamatan(
                        nama = "Madat",
                        daftarKodePos = listOf("24462")
                    ),
                    Kecamatan(
                        nama = "Nurussalam",
                        daftarKodePos = listOf("24467")
                    ),
                    Kecamatan(
                        nama = "Pante Bidari",
                        daftarKodePos = listOf("24463")
                    ),
                    Kecamatan(
                        nama = "Peudawa",
                        daftarKodePos = listOf("24469")
                    ),
                    Kecamatan(
                        nama = "Peunaron",
                        daftarKodePos = listOf("24461")
                    ),
                    Kecamatan(
                        nama = "Peureulak",
                        daftarKodePos = listOf("24453")
                    ),
                    Kecamatan(
                        nama = "Peureulak Barat",
                        daftarKodePos = listOf("24450")
                    ),
                    Kecamatan(
                        nama = "Peureulak Timur",
                        daftarKodePos = listOf("24440")
                    ),
                    Kecamatan(
                        nama = "Rantau Selamat",
                        daftarKodePos = listOf("24451")
                    ),
                    Kecamatan(
                        nama = "Ranto Peureulak",
                        daftarKodePos = listOf("24441")
                    ),
                    Kecamatan(
                        nama = "Serbajadi",
                        daftarKodePos = listOf("24460")
                    ),
                    Kecamatan(
                        nama = "Simpang Jernih",
                        daftarKodePos = listOf("24464")
                    ),
                    Kecamatan(
                        nama = "Simpang Ulim",
                        daftarKodePos = listOf("24465")
                    ),
                    Kecamatan(
                        nama = "Sungai Raya",
                        daftarKodePos = listOf("24466")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Aceh Utara",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Baktiya",
                        daftarKodePos = listOf("24392")
                    ),
                    Kecamatan(
                        nama = "Baktiya Barat",
                        daftarKodePos = listOf("24396")
                    ),
                    Kecamatan(
                        nama = "Banda Baro",
                        daftarKodePos = listOf("24376")
                    ),
                    Kecamatan(
                        nama = "Cot Girek",
                        daftarKodePos = listOf("24352")
                    ),
                    Kecamatan(
                        nama = "Dewantara",
                        daftarKodePos = listOf("24354")
                    ),
                    Kecamatan(
                        nama = "Geuredong Pase",
                        daftarKodePos = listOf("24373")
                    ),
                    Kecamatan(
                        nama = "Kuta Makmur",
                        daftarKodePos = listOf("24371")
                    ),
                    Kecamatan(
                        nama = "Langkahan",
                        daftarKodePos = listOf("24394")
                    ),
                    Kecamatan(
                        nama = "Lapang",
                        daftarKodePos = listOf("24391")
                    ),
                    Kecamatan(
                        nama = "Lhoksukon",
                        daftarKodePos = listOf("24382")
                    ),
                    Kecamatan(
                        nama = "Matangkuli",
                        daftarKodePos = listOf("24386")
                    ),
                    Kecamatan(
                        nama = "Meurah Mulia",
                        daftarKodePos = listOf("24372")
                    ),
                    Kecamatan(
                        nama = "Muara Batu",
                        daftarKodePos = listOf("24355")
                    ),
                    Kecamatan(
                        nama = "Nibong",
                        daftarKodePos = listOf("24385")
                    ),
                    Kecamatan(
                        nama = "Nisam",
                        daftarKodePos = listOf("24378")
                    ),
                    Kecamatan(
                        nama = "Nisam Antara",
                        daftarKodePos = listOf("24379")
                    ),
                    Kecamatan(
                        nama = "Paya Bakong",
                        daftarKodePos = listOf("24383")
                    ),
                    Kecamatan(
                        nama = "Pirak Timur",
                        daftarKodePos = listOf("24384")
                    ),
                    Kecamatan(
                        nama = "Samudera",
                        daftarKodePos = listOf("24374")
                    ),
                    Kecamatan(
                        nama = "Sawang",
                        daftarKodePos = listOf("24377")
                    ),
                    Kecamatan(
                        nama = "Seunudon",
                        daftarKodePos = listOf("24393")
                    ),
                    Kecamatan(
                        nama = "Simpang Keramat",
                        daftarKodePos = listOf("24313")
                    ),
                    Kecamatan(
                        nama = "Syamtalira Aron",
                        daftarKodePos = listOf("24381")
                    ),
                    Kecamatan(
                        nama = "Syamtalira Bayu",
                        daftarKodePos = listOf("24370")
                    ),
                    Kecamatan(
                        nama = "Tanah Jambo Aye",
                        daftarKodePos = listOf("24395")
                    ),
                    Kecamatan(
                        nama = "Tanah Luas",
                        daftarKodePos = listOf("24387")
                    ),
                    Kecamatan(
                        nama = "Tanah Pasir",
                        daftarKodePos = listOf("24390")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Bener Meriah",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Bandar",
                        daftarKodePos = listOf("24582")
                    ),
                    Kecamatan(
                        nama = "Bener Kelipah",
                        daftarKodePos = listOf("24583")
                    ),
                    Kecamatan(
                        nama = "Bukit",
                        daftarKodePos = listOf("24581")
                    ),
                    Kecamatan(
                        nama = "Gajah Putih",
                        daftarKodePos = listOf("24553")
                    ),
                    Kecamatan(
                        nama = "Mesidah",
                        daftarKodePos = listOf("24584")
                    ),
                    Kecamatan(
                        nama = "Permata",
                        daftarKodePos = listOf("24585")
                    ),
                    Kecamatan(
                        nama = "Pintu Rime Gayo",
                        daftarKodePos = listOf("24554")
                    ),
                    Kecamatan(
                        nama = "Syiah Utama",
                        daftarKodePos = listOf("24586")
                    ),
                    Kecamatan(
                        nama = "Timang Gajah",
                        daftarKodePos = listOf("24555")
                    ),
                    Kecamatan(
                        nama = "Wih Pesam",
                        daftarKodePos = listOf("24580")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Bireuen",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Ganda Pura",
                        daftarKodePos = listOf("24356")
                    ),
                    Kecamatan(
                        nama = "Jangka",
                        daftarKodePos = listOf("24261")
                    ),
                    Kecamatan(
                        nama = "Jeumpa",
                        daftarKodePos = listOf("24251")
                    ),
                    Kecamatan(
                        nama = "Jeunieb",
                        daftarKodePos = listOf("24263")
                    ),
                    Kecamatan(
                        nama = "Juli",
                        daftarKodePos = listOf("24250")
                    ),
                    Kecamatan(
                        nama = "Kota Juang",
                        daftarKodePos = listOf("24252")
                    ),
                    Kecamatan(
                        nama = "Kuala",
                        daftarKodePos = listOf("24260")
                    ),
                    Kecamatan(
                        nama = "Kuta Blang",
                        daftarKodePos = listOf("24358")
                    ),
                    Kecamatan(
                        nama = "Makmur",
                        daftarKodePos = listOf("24357")
                    ),
                    Kecamatan(
                        nama = "Pandrah",
                        daftarKodePos = listOf("24265")
                    ),
                    Kecamatan(
                        nama = "Peudada",
                        daftarKodePos = listOf("24262")
                    ),
                    Kecamatan(
                        nama = "Peulimbang",
                        daftarKodePos = listOf("24266")
                    ),
                    Kecamatan(
                        nama = "Peusangan",
                        daftarKodePos = listOf("24267")
                    ),
                    Kecamatan(
                        nama = "Peusangan Selatan",
                        daftarKodePos = listOf("24268")
                    ),
                    Kecamatan(
                        nama = "Peusangan Siblah Krueng",
                        daftarKodePos = listOf("24269")
                    ),
                    Kecamatan(
                        nama = "Simpang Mamplam",
                        daftarKodePos = listOf("24253")
                    ),
                    Kecamatan(
                        nama = "Samalanga",
                        daftarKodePos = listOf("24264")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Gayo Lues",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Blang Jerango",
                        daftarKodePos = listOf("24655")
                    ),
                    Kecamatan(
                        nama = "Blang Kejeren",
                        daftarKodePos = listOf("24655")
                    ),
                    Kecamatan(
                        nama = "Blang Pegayon",
                        daftarKodePos = listOf("24653")
                    ),
                    Kecamatan(
                        nama = "Dabun Gelang",
                        daftarKodePos = listOf("24652")
                    ),
                    Kecamatan(
                        nama = "Kuta Panjang",
                        daftarKodePos = listOf("24650")
                    ),
                    Kecamatan(
                        nama = "Pantan Cuaca",
                        daftarKodePos = listOf("24654")
                    ),
                    Kecamatan(
                        nama = "Pining",
                        daftarKodePos = listOf("24659")
                    ),
                    Kecamatan(
                        nama = "Putri Betung",
                        daftarKodePos = listOf("24658")
                    ),
                    Kecamatan(
                        nama = "Teripe Jaya",
                        daftarKodePos = listOf("24656")
                    ),
                    Kecamatan(
                        nama = "Lawe Sigala‑Gala",
                        daftarKodePos = listOf("24676")
                    ), // jika termasuk
                    Kecamatan(
                        nama = "Semadam",
                        daftarKodePos = listOf("24678")
                    ) // jika 11
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Nagan Raya",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Beutong",
                        daftarKodePos = listOf("23672")
                    ),
                    Kecamatan(
                        nama = "Beutong Ateuh Banggalang",
                        daftarKodePos = listOf("23673")
                    ),
                    Kecamatan(
                        nama = "Darul Makmur",
                        daftarKodePos = listOf("23662")
                    ),
                    Kecamatan(
                        nama = "Kuala",
                        daftarKodePos = listOf("23661")
                    ),
                    Kecamatan(
                        nama = "Kuala Pesisir",
                        daftarKodePos = listOf("23660")
                    ),
                    Kecamatan(
                        nama = "Seunagan",
                        daftarKodePos = listOf("23671")
                    ),
                    Kecamatan(
                        nama = "Seunagan Timur",
                        daftarKodePos = listOf("23670")
                    ),
                    Kecamatan(
                        nama = "Suka Makmue",
                        daftarKodePos = listOf("23674")
                    ),
                    Kecamatan(
                        nama = "Tadu Raya",
                        daftarKodePos = listOf("23664")
                    ),
                    Kecamatan(
                        nama = "Tripa Makmur",
                        daftarKodePos = listOf("23663")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Pidie",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Batee",
                        daftarKodePos = listOf("24152")
                    ),
                    Kecamatan(
                        nama = "Delima",
                        daftarKodePos = listOf("24161")
                    ),
                    Kecamatan(
                        nama = "Geumpang",
                        daftarKodePos = listOf("24167")
                    ),
                    Kecamatan(
                        nama = "Glumpang Tiga",
                        daftarKodePos = listOf("24183")
                    ),
                    Kecamatan(
                        nama = "Kembang Tanjong",
                        daftarKodePos = listOf("24182")
                    ),
                    Kecamatan(
                        nama = "Kota Sigli",
                        daftarKodePos = listOf("24115", "24119")
                    ), // rentang ganda
                    Kecamatan(
                        nama = "Mila",
                        daftarKodePos = listOf("24163")
                    ),
                    Kecamatan(
                        nama = "Muara Tiga",
                        daftarKodePos = listOf("24153")
                    ),
                    Kecamatan(
                        nama = "Mutiara",
                        daftarKodePos = listOf("24173")
                    ),
                    Kecamatan(
                        nama = "Mutiara Timur",
                        daftarKodePos = listOf("24175")
                    ),
                    Kecamatan(
                        nama = "Padang Tiji",
                        daftarKodePos = listOf("24161")
                    ),
                    Kecamatan(
                        nama = "Peukan Baro",
                        daftarKodePos = listOf("24172")
                    ),
                    Kecamatan(
                        nama = "Pidie",
                        daftarKodePos = listOf("24151")
                    ),
                    Kecamatan(
                        nama = "Sakti",
                        daftarKodePos = listOf("24164")
                    ),
                    Kecamatan(
                        nama = "Simpang Tiga",
                        daftarKodePos = listOf("24181")
                    ),
                    Kecamatan(
                        nama = "Tangse",
                        daftarKodePos = listOf("24166")
                    ),
                    Kecamatan(
                        nama = "Tiro/Truseb",
                        daftarKodePos = listOf("24174")
                    ),
                    Kecamatan(
                        nama = "Keumala",
                        daftarKodePos = listOf("24165")
                    ),
                    Kecamatan(
                        nama = "Grong‑Grong",
                        daftarKodePos = listOf("24150")
                    ),
                    Kecamatan(
                        nama = "Mane",
                        daftarKodePos = listOf("24189")
                    ),
                    Kecamatan(
                        nama = "Glumpang Baro",
                        daftarKodePos = listOf("24180")
                    ),
                    Kecamatan(
                        nama = "Titeue",
                        daftarKodePos = listOf("24168")
                    ),
                    Kecamatan(
                        nama = "Indra Jaya",
                        daftarKodePos = listOf("24171")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Pidie Jaya",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Meureudu",
                        daftarKodePos = listOf("24186")
                    ),
                    Kecamatan(
                        nama = "Ulim",
                        daftarKodePos = listOf("24187")
                    ),
                    Kecamatan(
                        nama = "Jangka Buaya",
                        daftarKodePos = listOf("24186")
                    ),
                    Kecamatan(
                        nama = "Bandar Dua",
                        daftarKodePos = listOf("24188")
                    ),
                    Kecamatan(
                        nama = "Meurah Dua",
                        daftarKodePos = listOf("24186")
                    ),
                    Kecamatan(
                        nama = "Bandar Baru",
                        daftarKodePos = listOf("24184")
                    ),
                    Kecamatan(
                        nama = "Panteraja",
                        daftarKodePos = listOf("24185")
                    ),
                    Kecamatan(
                        nama = "Trienggadeng",
                        daftarKodePos = listOf("24185")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kabupaten Simeulue",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Simeulue Tengah",
                        daftarKodePos = listOf("23894")
                    ),
                    Kecamatan(
                        nama = "Salang",
                        daftarKodePos = listOf("23896")
                    ),
                    Kecamatan(
                        nama = "Teupah Barat",
                        daftarKodePos = listOf("23897")
                    ),
                    Kecamatan(
                        nama = "Simeulue Timur",
                        daftarKodePos = listOf("23891")
                    ),
                    Kecamatan(
                        nama = "Teluk Dalam",
                        daftarKodePos = listOf("23890")
                    ),
                    Kecamatan(
                        nama = "Simeulue Barat",
                        daftarKodePos = listOf("23892")
                    ),
                    Kecamatan(
                        nama = "Teupah Selatan",
                        daftarKodePos = listOf("23898")
                    ),
                    Kecamatan(
                        nama = "Alapan",
                        daftarKodePos = listOf("23893")
                    ), // dikenal juga sebagai Alafan
                    Kecamatan(
                        nama = "Teupah Tengah",
                        daftarKodePos = listOf("23899")
                    ) // Simeulue Cut belum termasuk dalam daftar permintaan, namun total kecamatan tetap 10
                )
            ),

            // Daftar 5 Kota
            KabupatenKota(
                nama = "Kota Banda Aceh",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Baiturrahman",
                        daftarKodePos = listOf("23241", "23242", "23243", "23244", "23245")
                    ),
                    Kecamatan(
                        nama = "Kuta Alam",
                        daftarKodePos = listOf("23122", "23123", "23125", "23126", "23128", "23129")
                    ),
                    Kecamatan(
                        nama = "Meuraxa",
                        daftarKodePos = listOf("23232", "23233", "23234")
                    ), // rentang 23232‑23234
                    Kecamatan(
                        nama = "Syiah Kuala",
                        daftarKodePos = listOf("23111", "23112", "23113", "23114", "23115", "23116")
                    ),
                    Kecamatan(
                        nama = "Lueng Bata",
                        daftarKodePos = listOf("23244", "23245", "23246", "23247", "23248", "23249")
                    ),
                    Kecamatan(
                        nama = "Kuta Raja",
                        daftarKodePos = listOf("23128", "23129", "23142")
                    ),
                    Kecamatan(
                        nama = "Banda Raya",
                        daftarKodePos = listOf("23238", "23239")
                    ),
                    Kecamatan(
                        nama = "Jaya Baru",
                        daftarKodePos = listOf(
                            "23230",
                            "23231",
                            "23232",
                            "23233",
                            "23234",
                            "23235",
                            "23236"
                        )
                    ),
                    Kecamatan(
                        nama = "Ulee Kareng",
                        daftarKodePos = listOf("23117", "23118", "23119")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kota Langsa",
                daftarKecamatan = listOf(
                    Kecamatan(
                        nama = "Langsa Timur",
                        daftarKodePos = listOf("24410", "24411", "24412", "24413", "24414", "24415")
                    ),
                    Kecamatan(
                        nama = "Langsa Barat",
                        daftarKodePos = listOf("24410", "24413", "24414", "24415")
                    ),
                    Kecamatan(nama = "Langsa Kota",
                        daftarKodePos = listOf("24410", "24412", "24414", "24416")
                    ),
                    Kecamatan(nama = "Langsa Lama",
                        daftarKodePos = listOf("24410", "24411", "24412")
                    ),
                    Kecamatan(nama = "Langsa Baro",
                        daftarKodePos = listOf("24415", "24414")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kota Lhokseumawe",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Muara Dua",
                        daftarKodePos = listOf("24351")
                    ),
                    Kecamatan(nama = "Banda Sakti",
                        daftarKodePos = listOf("24315", "24351", "24313")
                    ),
                    Kecamatan(nama = "Blang Mangat",
                        daftarKodePos = listOf("24375")
                    ),
                    Kecamatan(nama = "Muara Satu",
                        daftarKodePos = listOf("24353")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kota Sabang",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Sukakarya",
                        daftarKodePos = listOf("23512")
                    ),
                    Kecamatan(nama = "Sukajaya",
                        daftarKodePos = listOf("23511")
                    ),
                    Kecamatan(nama = "Sukamakmue",
                        daftarKodePos = listOf("23510")
                    )
                )
            ),

            KabupatenKota(
                nama = "Kota Subulussalam",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Simpang Kiri",
                        daftarKodePos = listOf("24685")
                    ),
                    Kecamatan(nama = "Penanggalan",
                        daftarKodePos = listOf("24686")
                    ),
                    Kecamatan(nama = "Rundeng",
                        daftarKodePos = listOf("24687")
                    ),
                    Kecamatan(nama = "Sultan Daulat",
                        daftarKodePos = listOf("24682")
                    ),
                    Kecamatan(nama = "Longkib",
                        daftarKodePos = listOf("24683")
                    )
                )
            )
        )
    )

    // DATA LOKASI UNTUK PROVINSI SUMATERA UTARA
    val provinsiSumateraUtara = Provinsi(
        nama = "Sumatera Utara",
        daftarkabupatenKota = listOf(
            // KABUPATEN TAPANULI TENGAH
            KabupatenKota(
                nama = "Kabupaten Tapanuli Tengah",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Barus",
                        daftarKodePos = listOf("22654")
                    ),
                    Kecamatan(nama = "Sorkam",
                        daftarKodePos = listOf("22653")
                    ),
                    Kecamatan(nama = "Pandan",
                        daftarKodePos = listOf("22611", "22612", "22613", "22614", "22615", "22616")
                    ),
                    Kecamatan(nama = "Pinangsori",
                        daftarKodePos = listOf("22655")
                    ),
                    Kecamatan(nama = "Manduamas",
                        daftarKodePos = listOf("22562")
                    ),
                    Kecamatan(nama = "Kolang",
                        daftarKodePos = listOf("22616")
                    ),
                    Kecamatan(nama = "Tapian Nauli",
                        daftarKodePos = listOf("22614")
                    ),
                    Kecamatan(nama = "Sibabangun",
                        daftarKodePos = listOf("22656")
                    ),
                    Kecamatan(nama = "Sosorgadong",
                        daftarKodePos = listOf("22652")
                    ),
                    Kecamatan(nama = "Sorkam Barat",
                        daftarKodePos = listOf("22653")
                    ),
                    Kecamatan(nama = "Sirandorung",
                        daftarKodePos = listOf("22564")
                    ),
                    Kecamatan(nama = "Andam Dewi",
                        daftarKodePos = listOf("22561")
                    ),
                    Kecamatan(nama = "Sitahuis",
                        daftarKodePos = listOf("22617")
                    ),
                    Kecamatan(nama = "Tukka",
                        daftarKodePos = listOf("22615")
                    ),
                    Kecamatan(nama = "Badiri",
                        daftarKodePos = listOf("22563")
                    ),
                    Kecamatan(nama = "Pasaribu Tobing",
                        daftarKodePos = listOf("22652")
                    ),
                    Kecamatan(nama = "Barus Utara",
                        daftarKodePos = listOf("22654")
                    ),
                    Kecamatan(nama = "Suka Bangun",
                        daftarKodePos = listOf("22560")
                    ),
                    Kecamatan(nama = "Lumut",
                        daftarKodePos = listOf("22657")
                    ),
                    Kecamatan(nama = "Sarudik",
                        daftarKodePos = listOf("22611", "22613", "22616")
                    )
                )
            ),

            // KABUPATEN TAPANULI UTARA
            KabupatenKota(
                nama = "Kabupaten Tapanuli Utara",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Tarutung",
                        daftarKodePos = listOf("22411", "22412", "22413", "22414", "22415", "22416")
                    ),
                    Kecamatan(nama = "Siatas Barita",
                        daftarKodePos = listOf("22416")
                    ),
                    Kecamatan(nama = "Adian Koting",
                        daftarKodePos = listOf("22462")
                    ),
                    Kecamatan(nama = "Sipoholon",
                        daftarKodePos = listOf("22471")
                    ),
                    Kecamatan(nama = "Pahae Julu",
                        daftarKodePos = listOf("22463")
                    ),
                    Kecamatan(nama = "Pahae Jae",
                        daftarKodePos = listOf("22465")
                    ),
                    Kecamatan(nama = "Simangumban",
                        daftarKodePos = listOf("22464")
                    ),
                    Kecamatan(nama = "Purba Tua",
                        daftarKodePos = listOf("22464")
                    ),
                    Kecamatan(nama = "Siborong-Borong",
                        daftarKodePos = listOf("22474")
                    ),
                    Kecamatan(nama = "Pagaran",
                        daftarKodePos = listOf("22473")
                    ),
                    Kecamatan(nama = "Parmonangan",
                        daftarKodePos = listOf("22455")
                    ),
                    Kecamatan(nama = "Sipahutar",
                        daftarKodePos = listOf("22472")
                    ),
                    Kecamatan(nama = "Pangaribuan",
                        daftarKodePos = listOf("22475")
                    ),
                    Kecamatan(nama = "Garoga",
                        daftarKodePos = listOf("22476")
                    ),
                    Kecamatan(nama = "Muara",
                        daftarKodePos = listOf("22477")
                    )
                )
            ),

            // KABUPATEN TAPANULI SELATAN
            KabupatenKota(
                nama = "Kabupaten Tapanuli Selatan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Angkola Barat",
                        daftarKodePos = listOf("22731")
                    ),
                    Kecamatan(nama = "Batang Toru",
                        daftarKodePos = listOf("22738")
                    ),
                    Kecamatan(nama = "Angkola Timur",
                        daftarKodePos = listOf("22733")
                    ),
                    Kecamatan(nama = "Sipirok",
                        daftarKodePos = listOf("22742")
                    ),
                    Kecamatan(nama = "Saipar Dolok Hole",
                        daftarKodePos = listOf("22758")
                    ),
                    Kecamatan(nama = "Angkola Selatan",
                        daftarKodePos = listOf("22736")
                    ),
                    Kecamatan(nama = "Batang Angkola",
                        daftarKodePos = listOf("22773")
                    ),
                    Kecamatan(nama = "Arse",
                        daftarKodePos = listOf("22742")
                    ),
                    Kecamatan(nama = "Marancar",
                        daftarKodePos = listOf("22738")
                    ),
                    Kecamatan(nama = "Sayur Matinggi",
                        daftarKodePos = listOf("22774")
                    ),
                    Kecamatan(nama = "Aek Bilah",
                        daftarKodePos = listOf("22758")
                    ),
                    Kecamatan(nama = "Muara Batang Toru",
                        daftarKodePos = listOf("22738")
                    ),
                    Kecamatan(nama = "Tano Tombangan Angkola",
                        daftarKodePos = listOf("22773")
                    ),
                    Kecamatan(nama = "Angkola Sangkunur",
                        daftarKodePos = listOf("22736")
                    ),
                    Kecamatan(nama = "Angkola Muara Tais",
                        daftarKodePos = listOf("22773")
                    )
                )
            ),

            // KABUPATEN NIAS
            KabupatenKota(
                nama = "Kabupaten Nias",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Hiliduho",
                        daftarKodePos = listOf("22872")
                    ),
                    Kecamatan(nama = "Gido",
                        daftarKodePos = listOf("22876")
                    ),
                    Kecamatan(nama = "Idanogawo",
                        daftarKodePos = listOf("22874")
                    ),
                    Kecamatan(nama = "Bawolato",
                        daftarKodePos = listOf("22873")
                    ),
                    Kecamatan(nama = "Hiliserangkai",
                        daftarKodePos = listOf("22872")
                    ),
                    Kecamatan(nama = "Botomuzoi",
                        daftarKodePos = listOf("22872")
                    ),
                    Kecamatan(nama = "Ulugawo",
                        daftarKodePos = listOf("22874")
                    ),
                    Kecamatan(nama = "Ma'u",
                        daftarKodePos = listOf("22876")
                    ),
                    Kecamatan(nama = "Somolo-Molo",
                        daftarKodePos = listOf("22876")
                    ),
                    Kecamatan(nama = "Sogae'adu",
                        daftarKodePos = listOf("22876")
                    )
                )
            ),

            // KABUPATEN LANGKAT
            KabupatenKota(
                nama = "Kabupaten Langkat",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Bahorok",
                        daftarKodePos = listOf("20774")
                    ),
                    Kecamatan(nama = "Salapian",
                        daftarKodePos = listOf("20773")
                    ),
                    Kecamatan(nama = "Kuala",
                        daftarKodePos = listOf("20772")
                    ),
                    Kecamatan(nama = "Sei Bingei",
                        daftarKodePos = listOf("20771")
                    ),
                    Kecamatan(nama = "Binjai",
                        daftarKodePos = listOf("20761")
                    ),
                    Kecamatan(nama = "Selesai",
                        daftarKodePos = listOf("20762")
                    ),
                    Kecamatan(nama = "Stabat",
                        daftarKodePos = listOf("20811", "20812", "20813", "20814", "20815", "20816")
                    ),
                    Kecamatan(nama = "Wampu",
                        daftarKodePos = listOf("20851")
                    ),
                    Kecamatan(nama = "Secanggang",
                        daftarKodePos = listOf("20855")
                    ),
                    Kecamatan(nama = "Hinai",
                        daftarKodePos = listOf("20854")
                    ),
                    Kecamatan(nama = "Tanjung Pura",
                        daftarKodePos = listOf("20853")
                    ),
                    Kecamatan(nama = "Padang Tualang",
                        daftarKodePos = listOf("20852")
                    ),
                    Kecamatan(nama = "Gebang",
                        daftarKodePos = listOf("20856")
                    ),
                    Kecamatan(nama = "Babalan",
                        daftarKodePos = listOf("20857")
                    ),
                    Kecamatan(nama = "Pangkalan Susu",
                        daftarKodePos = listOf("20858")
                    ),
                    Kecamatan(nama = "Besitang",
                        daftarKodePos = listOf("20859")
                    ),
                    Kecamatan(nama = "Sei Lepan",
                        daftarKodePos = listOf("20857")
                    ),
                    Kecamatan(nama = "Berandan Barat",
                        daftarKodePos = listOf("20857")
                    ),
                    Kecamatan(nama = "Batang Serangan",
                        daftarKodePos = listOf("20852")
                    ),
                    Kecamatan(nama = "Sawit Seberang",
                        daftarKodePos = listOf("20852")
                    ),
                    Kecamatan(nama = "Sirapit",
                        daftarKodePos = listOf("20772")
                    ),
                    Kecamatan(nama = "Kutambaru",
                        daftarKodePos = listOf("20773")
                    ),
                    Kecamatan(nama = "Pematang Jaya",
                        daftarKodePos = listOf("20859")
                    )
                )
            ),

            // KABUPATEN KARO
            KabupatenKota(
                nama = "Kabupaten Karo",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Kabanjahe",
                        daftarKodePos = listOf("22111", "22112", "22113", "22114", "22115")
                    ),
                    Kecamatan(nama = "Berastagi",
                        daftarKodePos = listOf("22152", "22153")
                    ),
                    Kecamatan(nama = "Barusjahe",
                        daftarKodePos = listOf("22162")
                    ),
                    Kecamatan(nama = "Tigapanah",
                        daftarKodePos = listOf("22154")
                    ),
                    Kecamatan(nama = "Merek",
                        daftarKodePos = listOf("22173")
                    ),
                    Kecamatan(nama = "Munte",
                        daftarKodePos = listOf("22161")
                    ),
                    Kecamatan(nama = "Juhar",
                        daftarKodePos = listOf("22163")
                    ),
                    Kecamatan(nama = "Tigabinanga",
                        daftarKodePos = listOf("22165")
                    ),
                    Kecamatan(nama = "Laubaleng",
                        daftarKodePos = listOf("22166")
                    ),
                    Kecamatan(nama = "Mardingding",
                        daftarKodePos = listOf("22167")
                    ),
                    Kecamatan(nama = "Payung",
                        daftarKodePos = listOf("22164")
                    ),
                    Kecamatan(nama = "Simpang Empat",
                        daftarKodePos = listOf("22151")
                    ),
                    Kecamatan(nama = "Kutabuluh",
                        daftarKodePos = listOf("22168")
                    ),
                    Kecamatan(nama = "Dolat Rayat",
                        daftarKodePos = listOf("22155")
                    ),
                    Kecamatan(nama = "Merdeka",
                        daftarKodePos = listOf("22156")
                    ),
                    Kecamatan(nama = "Naman Teran",
                        daftarKodePos = listOf("22157")
                    ),
                    Kecamatan(nama = "Tiganderket",
                        daftarKodePos = listOf("22158")
                    )
                )
            ),

            // KABUPATEN DELI SERDANG
            KabupatenKota(
                nama = "Kabupaten Deli Serdang",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Gunung Meriah",
                        daftarKodePos = listOf("20356")
                    ),
                    Kecamatan(nama = "Tanjung Morawa",
                        daftarKodePos = listOf("20362")
                    ),
                    Kecamatan(nama = "Sibolangit",
                        daftarKodePos = listOf("20357")
                    ),
                    Kecamatan(nama = "Kutalimbaru",
                        daftarKodePos = listOf("20354")
                    ),
                    Kecamatan(nama = "Pancur Batu",
                        daftarKodePos = listOf("20353")
                    ),
                    Kecamatan(nama = "Namorambe",
                        daftarKodePos = listOf("20356")
                    ),
                    Kecamatan(nama = "Sibiru-Biru",
                        daftarKodePos = listOf("20358")
                    ),
                    Kecamatan(nama = "Stm Hilir",
                        daftarKodePos = listOf("20361")
                    ),
                    Kecamatan(nama = "Bangun Purba",
                        daftarKodePos = listOf("20581")
                    ),
                    Kecamatan(nama = "Galang",
                        daftarKodePos = listOf("20585")
                    ),
                    Kecamatan(nama = "Stm Hulu",
                        daftarKodePos = listOf("20582")
                    ),
                    Kecamatan(nama = "Patumbak",
                        daftarKodePos = listOf("20361")
                    ),
                    Kecamatan(nama = "Deli Tua",
                        daftarKodePos = listOf("20355")
                    ),
                    Kecamatan(nama = "Sunggal",
                        daftarKodePos = listOf("20351")
                    ),
                    Kecamatan(nama = "Hamparan Perak",
                        daftarKodePos = listOf("20371")
                    ),
                    Kecamatan(nama = "Labuhan Deli",
                        daftarKodePos = listOf("20373")
                    ),
                    Kecamatan(nama = "Percut Sei Tuan",
                        daftarKodePos = listOf("20371")
                    ),
                    Kecamatan(nama = "Batang Kuis",
                        daftarKodePos = listOf("20372")
                    ),
                    Kecamatan(nama = "Lubuk Pakam",
                        daftarKodePos = listOf("20511", "20512", "20513", "20514", "20515")
                    ),
                    Kecamatan(nama = "Pagar Merbau",
                        daftarKodePos = listOf("20551")
                    ),
                    Kecamatan(nama = "Pantai Labu",
                        daftarKodePos = listOf("20553")
                    ),
                    Kecamatan(nama = "Beringin",
                        daftarKodePos = listOf("20552")
                    )
                )
            ),

            // KABUPATEN SIMALUNGUN
            KabupatenKota(
                nama = "Kabupaten Simalungun",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Siantar",
                        daftarKodePos = listOf("21151")
                    ),
                    Kecamatan(nama = "Gunung Malela",
                        daftarKodePos = listOf("21175")
                    ),
                    Kecamatan(nama = "Gunung Maligas",
                        daftarKodePos = listOf("21174")
                    ),
                    Kecamatan(nama = "Panei",
                        daftarKodePos = listOf("21161")
                    ),
                    Kecamatan(nama = "Panombeian Pane",
                        daftarKodePos = listOf("21161")
                    ),
                    Kecamatan(nama = "Jorlang Hataran",
                        daftarKodePos = listOf("21172")
                    ),
                    Kecamatan(nama = "Raya Kahean",
                        daftarKodePos = listOf("21156")
                    ),
                    Kecamatan(nama = "Bosar Maligas",
                        daftarKodePos = listOf("21184")
                    ),
                    Kecamatan(nama = "Sidamanik",
                        daftarKodePos = listOf("21171")
                    ),
                    Kecamatan(nama = "Pematang Sidamanik",
                        daftarKodePos = listOf("21171")
                    ),
                    Kecamatan(nama = "Tanah Jawa",
                        daftarKodePos = listOf("21181")
                    ),
                    Kecamatan(nama = "Hatonduhan",
                        daftarKodePos = listOf("21177")
                    ),
                    Kecamatan(nama = "Dolok Panribuan",
                        daftarKodePos = listOf("21173")
                    ),
                    Kecamatan(nama = "Purba",
                        daftarKodePos = listOf("21166")
                    ),
                    Kecamatan(nama = "Haranggaol Horison",
                        daftarKodePos = listOf("21166")
                    ),
                    Kecamatan(nama = "Girsang Sipangan Bolon",
                        daftarKodePos = listOf("21174")
                    ),
                    Kecamatan(nama = "Dolok Batu Nanggar",
                        daftarKodePos = listOf("21155")
                    ),
                    Kecamatan(nama = "Huta Bayu Raja",
                        daftarKodePos = listOf("21182")
                    ),
                    Kecamatan(nama = "Jawa Maraja Bah Jambi",
                        daftarKodePos = listOf("21182")
                    ),
                    Kecamatan(nama = "Dolok Pardamean",
                        daftarKodePos = listOf("21163")
                    ),
                    Kecamatan(nama = "Pematang Bandar",
                        daftarKodePos = listOf("21186")
                    ),
                    Kecamatan(nama = "Bandar Huluan",
                        daftarKodePos = listOf("21186")
                    ),
                    Kecamatan(nama = "Bandar",
                        daftarKodePos = listOf("21186")
                    ),
                    Kecamatan(nama = "Bandar Masilam",
                        daftarKodePos = listOf("21183")
                    ),
                    Kecamatan(nama = "Silimakuta",
                        daftarKodePos = listOf("21165")
                    ),
                    Kecamatan(nama = "Dolok Silau",
                        daftarKodePos = listOf("21168")
                    ),
                    Kecamatan(nama = "Silou Kahean",
                        daftarKodePos = listOf("21155")
                    ),
                    Kecamatan(nama = "Tapian Dolok",
                        daftarKodePos = listOf("21154")
                    ),
                    Kecamatan(nama = "Raya",
                        daftarKodePos = listOf("21162")
                    ),
                    Kecamatan(nama = "Ujung Padang",
                        daftarKodePos = listOf("21185")
                    ),
                    Kecamatan(nama = "Pamatang Silima Huta",
                        daftarKodePos = listOf("21167")
                    ),
                    Kecamatan(nama = "Dolog Masagal",
                        daftarKodePos = listOf("21166")
                    )
                )
            ),

            // KABUPATEN ASAHAN
            KabupatenKota(
                nama = "Kabupaten Asahan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Meranti",
                        daftarKodePos = listOf("21264")
                    ),
                    Kecamatan(nama = "Air Joman",
                        daftarKodePos = listOf("21263")
                    ),
                    Kecamatan(nama = "Tanjung Balai",
                        daftarKodePos = listOf("21211")
                    ),
                    Kecamatan(nama = "Sei Kepayang",
                        daftarKodePos = listOf("21262")
                    ),
                    Kecamatan(nama = "Simpang Empat",
                        daftarKodePos = listOf("21271")
                    ),
                    Kecamatan(nama = "Air Batu",
                        daftarKodePos = listOf("21272")),
                    Kecamatan(nama = "Pulau Rakyat",
                        daftarKodePos = listOf("21274")
                    ),
                    Kecamatan(nama = "Bandar Pulau",
                        daftarKodePos = listOf("21278")
                    ),
                    Kecamatan(nama = "Buntu Pane",
                        daftarKodePos = listOf("21273")
                    ),
                    Kecamatan(nama = "Bandar Pasir Mandoge",
                        daftarKodePos = listOf("21270")
                    ),
                    Kecamatan(nama = "Aek Kuasan",
                        daftarKodePos = listOf("21273")
                    ),
                    Kecamatan(nama = "Kota Kisaran Barat",
                        daftarKodePos = listOf("21211", "21212", "21213", "21214")
                    ),
                    Kecamatan(nama = "Kota Kisaran Timur",
                        daftarKodePos = listOf("21221", "21222", "21223", "21224")
                    ),
                    Kecamatan(nama = "Aek Songsongan",
                        daftarKodePos = listOf("21275")
                    ),
                    Kecamatan(nama = "Rahunig",
                        daftarKodePos = listOf("21275")
                    ),
                    Kecamatan(nama = "Sei Dadap",
                        daftarKodePos = listOf("21271")
                    ),
                    Kecamatan(nama = "Sei Kepayang Barat",
                        daftarKodePos = listOf("21262")
                    ),
                    Kecamatan(nama = "Sei Kepayang Timur",
                        daftarKodePos = listOf("21262")
                    ),
                    Kecamatan(nama = "Tinggi Raja",
                        daftarKodePos = listOf("21274")
                    ),
                    Kecamatan(nama = "Setia Janji",
                        daftarKodePos = listOf("21274")
                    ),
                    Kecamatan(nama = "Silau Laut",
                        daftarKodePos = listOf("21261")
                    ),
                    Kecamatan(nama = "Rawang Panca Arga",
                        daftarKodePos = listOf("21264")
                    ),
                    Kecamatan(nama = "Pulo Bandring",
                        daftarKodePos = listOf("21264")
                    ),
                    Kecamatan(nama = "Teluk Dalam",
                        daftarKodePos = listOf("21262")
                    ),
                    Kecamatan(nama = "Aek Ledong",
                        daftarKodePos = listOf("21273")
                    )
                )
            ),

            // KABUPATEN LABUHANBATU
            KabupatenKota(
                nama = "Kabupaten Labuhanbatu",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Rantau Utara",
                        daftarKodePos = listOf("21411", "21412", "21413", "21414")
                    ),
                    Kecamatan(nama = "Rantau Selatan",
                        daftarKodePos = listOf("21415", "21416", "21417")
                    ),
                    Kecamatan(nama = "Bilah Barat",
                        daftarKodePos = listOf("21418")
                    ),
                    Kecamatan(nama = "Bilah Hilir",
                        daftarKodePos = listOf("21472")
                    ),
                    Kecamatan(nama = "Bilah Hulu",
                        daftarKodePos = listOf("21419")
                    ),
                    Kecamatan(nama = "Pangkatan",
                        daftarKodePos = listOf("21471")
                    ),
                    Kecamatan(nama = "Panai Tengah",
                        daftarKodePos = listOf("21474")
                    ),
                    Kecamatan(nama = "Panai Hilir",
                        daftarKodePos = listOf("21475")
                    ),
                    Kecamatan(nama = "Panai Hulu",
                        daftarKodePos = listOf("21473")
                    )
                )
            ),
            KabupatenKota(
                nama = "Kabupaten Tapanuli Tengah",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Barus",
                        daftarKodePos = listOf("22654")
                    ),
                    Kecamatan(nama = "Sorkam",
                        daftarKodePos = listOf("22653")
                    ),
                    Kecamatan(nama = "Pandan",
                        daftarKodePos = listOf("22611", "22612", "22613", "22614", "22615", "22616")
                    ),
                    Kecamatan(nama = "Pinangsori",
                        daftarKodePos = listOf("22655")
                    ),
                    Kecamatan(nama = "Manduamas",
                        daftarKodePos = listOf("22562")
                    ),
                    Kecamatan(nama = "Kolang",
                        daftarKodePos = listOf("22616")
                    ),
                    Kecamatan(nama = "Tapian Nauli",
                        daftarKodePos = listOf("22614")
                    ),
                    Kecamatan(nama = "Sibabangun",
                        daftarKodePos = listOf("22656")
                    ),
                    Kecamatan(nama = "Sosorgadong",
                        daftarKodePos = listOf("22652")
                    ),
                    Kecamatan(nama = "Sorkam Barat",
                        daftarKodePos = listOf("22653")
                    ),
                    Kecamatan(nama = "Sirandorung",
                        daftarKodePos = listOf("22564")
                    ),
                    Kecamatan(nama = "Andam Dewi",
                        daftarKodePos = listOf("22561")
                    ),
                    Kecamatan(nama = "Sitahuis",
                        daftarKodePos = listOf("22617")
                    ),
                    Kecamatan(nama = "Tukka",
                        daftarKodePos = listOf("22615")
                    ),
                    Kecamatan(nama = "Badiri",
                        daftarKodePos = listOf("22563")
                    ),
                    Kecamatan(nama = "Pasaribu Tobing",
                        daftarKodePos = listOf("22652")
                    ),
                    Kecamatan(nama = "Barus Utara",
                        daftarKodePos = listOf("22654")
                    ),
                    Kecamatan(nama = "Suka Bangun",
                        daftarKodePos = listOf("22560")
                    ),
                    Kecamatan(nama = "Lumut",
                        daftarKodePos = listOf("22657")
                    ),
                    Kecamatan(nama = "Sarudik",
                        daftarKodePos = listOf("22611", "22613", "22616")
                    )
                )
            ),

            // KABUPATEN TAPANULI UTARA
            KabupatenKota(
                nama = "Kabupaten Tapanuli Utara",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Tarutung",
                        daftarKodePos = listOf("22411", "22412", "22413", "22414", "22415", "22416")
                    ),
                    Kecamatan(nama = "Siatas Barita",
                        daftarKodePos = listOf("22416")
                    ),
                    Kecamatan(nama = "Adian Koting",
                        daftarKodePos = listOf("22462")
                    ),
                    Kecamatan(nama = "Sipoholon",
                        daftarKodePos = listOf("22471")
                    ),
                    Kecamatan(nama = "Pahae Julu",
                        daftarKodePos = listOf("22463")
                    ),
                    Kecamatan(nama = "Pahae Jae",
                        daftarKodePos = listOf("22465")
                    ),
                    Kecamatan(nama = "Simangumban",
                        daftarKodePos = listOf("22464")
                    ),
                    Kecamatan(nama = "Purba Tua",
                        daftarKodePos = listOf("22464")
                    ),
                    Kecamatan(nama = "Siborong-Borong",
                        daftarKodePos = listOf("22474")
                    ),
                    Kecamatan(nama = "Pagaran",
                        daftarKodePos = listOf("22473")
                    ),
                    Kecamatan(nama = "Parmonangan",
                        daftarKodePos = listOf("22455")
                    ),
                    Kecamatan(nama = "Sipahutar",
                        daftarKodePos = listOf("22472")
                    ),
                    Kecamatan(nama = "Pangaribuan",
                        daftarKodePos = listOf("22475")
                    ),
                    Kecamatan(nama = "Garoga",
                        daftarKodePos = listOf("22476")
                    ),
                    Kecamatan(nama = "Muara",
                        daftarKodePos = listOf("22477")
                    )
                )
            ),

            // KABUPATEN TAPANULI SELATAN
            KabupatenKota(
                nama = "Kabupaten Tapanuli Selatan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Angkola Barat",
                        daftarKodePos = listOf("22731")
                    ),
                    Kecamatan(nama = "Batang Toru",
                        daftarKodePos = listOf("22738")
                    ),
                    Kecamatan(nama = "Angkola Timur",
                        daftarKodePos = listOf("22733")
                    ),
                    Kecamatan(nama = "Sipirok",
                        daftarKodePos = listOf("22742")
                    ),
                    Kecamatan(nama = "Saipar Dolok Hole",
                        daftarKodePos = listOf("22758")
                    ),
                    Kecamatan(nama = "Angkola Selatan",
                        daftarKodePos = listOf("22736")
                    ),
                    Kecamatan(nama = "Batang Angkola",
                        daftarKodePos = listOf("22773")
                    ),
                    Kecamatan(nama = "Arse",
                        daftarKodePos = listOf("22742")
                    ),
                    Kecamatan(nama = "Marancar",
                        daftarKodePos = listOf("22738")
                    ),
                    Kecamatan(nama = "Sayur Matinggi",
                        daftarKodePos = listOf("22774")
                    ),
                    Kecamatan(nama = "Aek Bilah",
                        daftarKodePos = listOf("22758")
                    ),
                    Kecamatan(nama = "Muara Batang Toru",
                        daftarKodePos = listOf("22738")
                    ),
                    Kecamatan(nama = "Tano Tombangan Angkola",
                        daftarKodePos = listOf("22773")
                    ),
                    Kecamatan(nama = "Angkola Sangkunur",
                        daftarKodePos = listOf("22736")
                    ),
                    Kecamatan(nama = "Angkola Muara Tais",
                        daftarKodePos = listOf("22773")
                    )
                )
            ),

            // KABUPATEN NIAS
            KabupatenKota(
                nama = "Kabupaten Nias",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Hiliduho",
                        daftarKodePos = listOf("22872")
                    ),
                    Kecamatan(nama = "Gido",
                        daftarKodePos = listOf("22876")
                    ),
                    Kecamatan(nama = "Idanogawo",
                        daftarKodePos = listOf("22874")
                    ),
                    Kecamatan(nama = "Bawolato",
                        daftarKodePos = listOf("22873")
                    ),
                    Kecamatan(nama = "Hiliserangkai",
                        daftarKodePos = listOf("22872")
                    ),
                    Kecamatan(nama = "Botomuzoi",
                        daftarKodePos = listOf("22872")
                    ),
                    Kecamatan(nama = "Ulugawo",
                        daftarKodePos = listOf("22874")
                    ),
                    Kecamatan(nama = "Ma'u",
                        daftarKodePos = listOf("22876")
                    ),
                    Kecamatan(nama = "Somolo-Molo",
                        daftarKodePos = listOf("22876")
                    ),
                    Kecamatan(nama = "Sogae'adu",
                        daftarKodePos = listOf("22876")
                    )
                )
            ),

            // KABUPATEN LANGKAT
            KabupatenKota(
                nama = "Kabupaten Langkat",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Bahorok",
                        daftarKodePos = listOf("20774")
                    ),
                    Kecamatan(nama = "Salapian",
                        daftarKodePos = listOf("20773")
                    ),
                    Kecamatan(nama = "Kuala",
                        daftarKodePos = listOf("20772")
                    ),
                    Kecamatan(nama = "Sei Bingei",
                        daftarKodePos = listOf("20771")
                    ),
                    Kecamatan(nama = "Binjai",
                        daftarKodePos = listOf("20761")
                    ),
                    Kecamatan(nama = "Selesai",
                        daftarKodePos = listOf("20762")
                    ),
                    Kecamatan(nama = "Stabat",
                        daftarKodePos = listOf("20811", "20812", "20813", "20814", "20815", "20816")
                    ),
                    Kecamatan(nama = "Wampu",
                        daftarKodePos = listOf("20851")
                    ),
                    Kecamatan(nama = "Secanggang",
                        daftarKodePos = listOf("20855")
                    ),
                    Kecamatan(nama = "Hinai",
                        daftarKodePos = listOf("20854")
                    ),
                    Kecamatan(nama = "Tanjung Pura",
                        daftarKodePos = listOf("20853")
                    ),
                    Kecamatan(nama = "Padang Tualang",
                        daftarKodePos = listOf("20852")
                    ),
                    Kecamatan(nama = "Gebang",
                        daftarKodePos = listOf("20856")
                    ),
                    Kecamatan(nama = "Babalan",
                        daftarKodePos = listOf("20857")
                    ),
                    Kecamatan(nama = "Pangkalan Susu",
                        daftarKodePos = listOf("20858")
                    ),
                    Kecamatan(nama = "Besitang",
                        daftarKodePos = listOf("20859")
                    ),
                    Kecamatan(nama = "Sei Lepan",
                        daftarKodePos = listOf("20857")
                    ),
                    Kecamatan(nama = "Berandan Barat",
                        daftarKodePos = listOf("20857")
                    ),
                    Kecamatan(nama = "Batang Serangan",
                        daftarKodePos = listOf("20852")
                    ),
                    Kecamatan(nama = "Sawit Seberang",
                        daftarKodePos = listOf("20852")
                    ),
                    Kecamatan(nama = "Sirapit",
                        daftarKodePos = listOf("20772")
                    ),
                    Kecamatan(nama = "Kutambaru",
                        daftarKodePos = listOf("20773")
                    ),
                    Kecamatan(nama = "Pematang Jaya",
                        daftarKodePos = listOf("20859")
                    )
                )
            ),

            // KABUPATEN KARO
            KabupatenKota(
                nama = "Kabupaten Karo",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Kabanjahe",
                        daftarKodePos = listOf("22111", "22112", "22113", "22114", "22115")
                    ),
                    Kecamatan(nama = "Berastagi",
                        daftarKodePos = listOf("22152", "22153")
                    ),
                    Kecamatan(nama = "Barusjahe",
                        daftarKodePos = listOf("22162")
                    ),
                    Kecamatan(nama = "Tigapanah",
                        daftarKodePos = listOf("22154")
                    ),
                    Kecamatan(nama = "Merek",
                        daftarKodePos = listOf("22173")
                    ),
                    Kecamatan(nama = "Munte",
                        daftarKodePos = listOf("22161")
                    ),
                    Kecamatan(nama = "Juhar",
                        daftarKodePos = listOf("22163")
                    ),
                    Kecamatan(nama = "Tigabinanga",
                        daftarKodePos = listOf("22165")
                    ),
                    Kecamatan(nama = "Laubaleng",
                        daftarKodePos = listOf("22166")
                    ),
                    Kecamatan(nama = "Mardingding",
                        daftarKodePos = listOf("22167")
                    ),
                    Kecamatan(nama = "Payung",
                        daftarKodePos = listOf("22164")
                    ),
                    Kecamatan(nama = "Simpang Empat",
                        daftarKodePos = listOf("22151")
                    ),
                    Kecamatan(nama = "Kutabuluh",
                        daftarKodePos = listOf("22168")
                    ),
                    Kecamatan(nama = "Dolat Rayat",
                        daftarKodePos = listOf("22155")
                    ),
                    Kecamatan(nama = "Merdeka",
                        daftarKodePos = listOf("22156")
                    ),
                    Kecamatan(nama = "Naman Teran",
                        daftarKodePos = listOf("22157")
                    ),
                    Kecamatan(nama = "Tiganderket",
                        daftarKodePos = listOf("22158")
                    )
                )
            ),

            // KABUPATEN DELI SERDANG
            KabupatenKota(
                nama = "Kabupaten Deli Serdang",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Gunung Meriah",
                        daftarKodePos = listOf("20356")
                    ),
                    Kecamatan(nama = "Tanjung Morawa",
                        daftarKodePos = listOf("20362")
                    ),
                    Kecamatan(nama = "Sibolangit",
                        daftarKodePos = listOf("20357")
                    ),
                    Kecamatan(nama = "Kutalimbaru",
                        daftarKodePos = listOf("20354")
                    ),
                    Kecamatan(nama = "Pancur Batu",
                        daftarKodePos = listOf("20353")
                    ),
                    Kecamatan(nama = "Namorambe",
                        daftarKodePos = listOf("20356")
                    ),
                    Kecamatan(nama = "Sibiru-Biru",
                        daftarKodePos = listOf("20358")
                    ),
                    Kecamatan(nama = "Stm Hilir",
                        daftarKodePos = listOf("20361")
                    ),
                    Kecamatan(nama = "Bangun Purba",
                        daftarKodePos = listOf("20581")
                    ),
                    Kecamatan(nama = "Galang",
                        daftarKodePos = listOf("20585")
                    ),
                    Kecamatan(nama = "Stm Hulu",
                        daftarKodePos = listOf("20582")
                    ),
                    Kecamatan(nama = "Patumbak",
                        daftarKodePos = listOf("20361")
                    ),
                    Kecamatan(nama = "Deli Tua",
                        daftarKodePos = listOf("20355")
                    ),
                    Kecamatan(nama = "Sunggal",
                        daftarKodePos = listOf("20351")
                    ),
                    Kecamatan(nama = "Hamparan Perak",
                        daftarKodePos = listOf("20371")
                    ),
                    Kecamatan(nama = "Labuhan Deli",
                        daftarKodePos = listOf("20373")
                    ),
                    Kecamatan(nama = "Percut Sei Tuan",
                        daftarKodePos = listOf("20371")
                    ),
                    Kecamatan(nama = "Batang Kuis",
                        daftarKodePos = listOf("20372")
                    ),
                    Kecamatan(nama = "Lubuk Pakam",
                        daftarKodePos = listOf("20511", "20512", "20513", "20514", "20515")
                    ),
                    Kecamatan(nama = "Pagar Merbau",
                        daftarKodePos = listOf("20551")
                    ),
                    Kecamatan(nama = "Pantai Labu",
                        daftarKodePos = listOf("20553")
                    ),
                    Kecamatan(nama = "Beringin",
                        daftarKodePos = listOf("20552")
                    )
                )
            ),

            // KABUPATEN SIMALUNGUN
            KabupatenKota(
                nama = "Kabupaten Simalungun",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Siantar",
                        daftarKodePos = listOf("21151")
                    ),
                    Kecamatan(nama = "Gunung Malela",
                        daftarKodePos = listOf("21175")
                    ),
                    Kecamatan(nama = "Gunung Maligas",
                        daftarKodePos = listOf("21174")),
                    Kecamatan(nama = "Panei",
                        daftarKodePos = listOf("21161")),
                    Kecamatan(nama = "Panombeian Pane",
                        daftarKodePos = listOf("21161")),
                    Kecamatan(nama = "Jorlang Hataran",
                        daftarKodePos = listOf("21172")),
                    Kecamatan(nama = "Raya Kahean",
                        daftarKodePos = listOf("21156")),
                    Kecamatan(nama = "Bosar Maligas",
                        daftarKodePos = listOf("21184")),
                    Kecamatan(nama = "Sidamanik",
                        daftarKodePos = listOf("21171")),
                    Kecamatan(nama = "Pematang Sidamanik",
                        daftarKodePos = listOf("21171")),
                    Kecamatan(nama = "Tanah Jawa",
                        daftarKodePos = listOf("21181")),
                    Kecamatan(nama = "Hatonduhan",
                        daftarKodePos = listOf("21177")),
                    Kecamatan(nama = "Dolok Panribuan",
                        daftarKodePos = listOf("21173")),
                    Kecamatan(nama = "Purba",
                        daftarKodePos = listOf("21166")),
                    Kecamatan(nama = "Haranggaol Horison",
                        daftarKodePos = listOf("21166")),
                    Kecamatan(nama = "Girsang Sipangan Bolon",
                        daftarKodePos = listOf("21174")),
                    Kecamatan(nama = "Dolok Batu Nanggar",
                        daftarKodePos = listOf("21155")),
                    Kecamatan(nama = "Huta Bayu Raja",
                        daftarKodePos = listOf("21182")),
                    Kecamatan(nama = "Jawa Maraja Bah Jambi",
                        daftarKodePos = listOf("21182")),
                    Kecamatan(nama = "Dolok Pardamean",
                        daftarKodePos = listOf("21163")),
                    Kecamatan(nama = "Pematang Bandar",
                        daftarKodePos = listOf("21186")),
                    Kecamatan(nama = "Bandar Huluan",
                        daftarKodePos = listOf("21186")),
                    Kecamatan(nama = "Bandar",
                        daftarKodePos = listOf("21186")),
                    Kecamatan(nama = "Bandar Masilam",
                        daftarKodePos = listOf("21183")),
                    Kecamatan(nama = "Silimakuta",
                        daftarKodePos = listOf("21165")),
                    Kecamatan(nama = "Dolok Silau",
                        daftarKodePos = listOf("21168")),
                    Kecamatan(nama = "Silou Kahean",
                        daftarKodePos = listOf("21155")),
                    Kecamatan(nama = "Tapian Dolok",
                        daftarKodePos = listOf("21154")),
                    Kecamatan(nama = "Raya",
                        daftarKodePos = listOf("21162")),
                    Kecamatan(nama = "Ujung Padang",
                        daftarKodePos = listOf("21185")),
                    Kecamatan(nama = "Pamatang Silima Huta",
                        daftarKodePos = listOf("21167")),
                    Kecamatan(nama = "Dolog Masagal",
                        daftarKodePos = listOf("21166"))
                )
            ),

            // KABUPATEN ASAHAN
            KabupatenKota(
                nama = "Kabupaten Asahan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Meranti", daftarKodePos = listOf("21264")),
                    Kecamatan(nama = "Air Joman", daftarKodePos = listOf("21263")),
                    Kecamatan(nama = "Tanjung Balai", daftarKodePos = listOf("21211")), // Note: Ini juga nama kota
                    Kecamatan(nama = "Sei Kepayang", daftarKodePos = listOf("21262")),
                    Kecamatan(nama = "Simpang Empat", daftarKodePos = listOf("21271")),
                    Kecamatan(nama = "Air Batu", daftarKodePos = listOf("21272")),
                    Kecamatan(nama = "Pulau Rakyat", daftarKodePos = listOf("21274")),
                    Kecamatan(nama = "Bandar Pulau", daftarKodePos = listOf("21278")),
                    Kecamatan(nama = "Buntu Pane", daftarKodePos = listOf("21273")),
                    Kecamatan(nama = "Bandar Pasir Mandoge", daftarKodePos = listOf("21270")),
                    Kecamatan(nama = "Aek Kuasan", daftarKodePos = listOf("21273")),
                    Kecamatan(nama = "Kota Kisaran Barat", daftarKodePos = listOf("21211", "21212", "21213", "21214")),
                    Kecamatan(nama = "Kota Kisaran Timur", daftarKodePos = listOf("21221", "21222", "21223", "21224")),
                    Kecamatan(nama = "Aek Songsongan", daftarKodePos = listOf("21275")),
                    Kecamatan(nama = "Rahunig", daftarKodePos = listOf("21275")),
                    Kecamatan(nama = "Sei Dadap", daftarKodePos = listOf("21271")),
                    Kecamatan(nama = "Sei Kepayang Barat", daftarKodePos = listOf("21262")),
                    Kecamatan(nama = "Sei Kepayang Timur", daftarKodePos = listOf("21262")),
                    Kecamatan(nama = "Tinggi Raja", daftarKodePos = listOf("21274")),
                    Kecamatan(nama = "Setia Janji", daftarKodePos = listOf("21274")),
                    Kecamatan(nama = "Silau Laut", daftarKodePos = listOf("21261")),
                    Kecamatan(nama = "Rawang Panca Arga", daftarKodePos = listOf("21264")),
                    Kecamatan(nama = "Pulo Bandring", daftarKodePos = listOf("21264")),
                    Kecamatan(nama = "Teluk Dalam", daftarKodePos = listOf("21262")),
                    Kecamatan(nama = "Aek Ledong", daftarKodePos = listOf("21273"))
                )
            ),

            // KABUPATEN LABUHANBATU
            KabupatenKota(
                nama = "Kabupaten Labuhanbatu",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Rantau Utara", daftarKodePos = listOf("21411", "21412", "21413", "21414")),
                    Kecamatan(nama = "Rantau Selatan", daftarKodePos = listOf("21415", "21416", "21417")),
                    Kecamatan(nama = "Bilah Barat", daftarKodePos = listOf("21418")),
                    Kecamatan(nama = "Bilah Hilir", daftarKodePos = listOf("21472")),
                    Kecamatan(nama = "Bilah Hulu", daftarKodePos = listOf("21419")),
                    Kecamatan(nama = "Pangkatan", daftarKodePos = listOf("21471")),
                    Kecamatan(nama = "Panai Tengah", daftarKodePos = listOf("21474")),
                    Kecamatan(nama = "Panai Hilir", daftarKodePos = listOf("21475")),
                    Kecamatan(nama = "Panai Hulu", daftarKodePos = listOf("21473"))
                )
            ),

            // KABUPATEN DAIRI
            KabupatenKota(
                nama = "Kabupaten Dairi",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Sidikalang", daftarKodePos = listOf("22211", "22212", "22213", "22214")),
                    Kecamatan(nama = "Sumbul", daftarKodePos = listOf("22281")),
                    Kecamatan(nama = "Tigalingga", daftarKodePos = listOf("22263")),
                    Kecamatan(nama = "Siempat Nempu", daftarKodePos = listOf("22261")),
                    Kecamatan(nama = "Silima Pungga Pungga", daftarKodePos = listOf("22263")),
                    Kecamatan(nama = "Tanah Pinem", daftarKodePos = listOf("22262")),
                    Kecamatan(nama = "Siempat Nempu Hulu", daftarKodePos = listOf("22261")),
                    Kecamatan(nama = "Siempat Nempu Hilir", daftarKodePos = listOf("22261")),
                    Kecamatan(nama = "Pegagan Hilir", daftarKodePos = listOf("22282")),
                    Kecamatan(nama = "Parbuluan", daftarKodePos = listOf("22283")),
                    Kecamatan(nama = "Lae Parira", daftarKodePos = listOf("22263")),
                    Kecamatan(nama = "Gunung Sitember", daftarKodePos = listOf("22263")),
                    Kecamatan(nama = "Berampu", daftarKodePos = listOf("22211")),
                    Kecamatan(nama = "Silahisabungan", daftarKodePos = listOf("22284")),
                    Kecamatan(nama = "Sitinjo", daftarKodePos = listOf("22216"))
                )
            ),

            // KABUPATEN TOBA
            KabupatenKota(
                nama = "Kabupaten Toba",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Balige", daftarKodePos = listOf("22311", "22312", "22313", "22314")),
                    Kecamatan(nama = "Laguboti", daftarKodePos = listOf("22316")),
                    Kecamatan(nama = "Silaen", daftarKodePos = listOf("22382")),
                    Kecamatan(nama = "Habinsaran", daftarKodePos = listOf("22384")),
                    Kecamatan(nama = "Pintu Pohan Meranti", daftarKodePos = listOf("22384")),
                    Kecamatan(nama = "Borbor", daftarKodePos = listOf("22384")),
                    Kecamatan(nama = "Porsea", daftarKodePos = listOf("22385")),
                    Kecamatan(nama = "Ajibata", daftarKodePos = listOf("22386")),
                    Kecamatan(nama = "Lumban Julu", daftarKodePos = listOf("22386")),
                    Kecamatan(nama = "Uluan", daftarKodePos = listOf("22386")),
                    Kecamatan(nama = "Sigumpar", daftarKodePos = listOf("22385")),
                    Kecamatan(nama = "Siantar Narumonda", daftarKodePos = listOf("22385")),
                    Kecamatan(nama = "Nassau", daftarKodePos = listOf("22384")),
                    Kecamatan(nama = "Tampahan", daftarKodePos = listOf("22312")),
                    Kecamatan(nama = "Bonatua Lunasi", daftarKodePos = listOf("22386")),
                    Kecamatan(nama = "Parmaksian", daftarKodePos = listOf("22385"))
                )
            ),

            // KABUPATEN MANDAILING NATAL
            KabupatenKota(
                nama = "Kabupaten Mandailing Natal",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Panyabungan", daftarKodePos = listOf("22911", "22912", "22913")),
                    Kecamatan(nama = "Panyabungan Utara", daftarKodePos = listOf("22915")),
                    Kecamatan(nama = "Panyabungan Timur", daftarKodePos = listOf("22916")),
                    Kecamatan(nama = "Panyabungan Selatan", daftarKodePos = listOf("22914")),
                    Kecamatan(nama = "Panyabungan Barat", daftarKodePos = listOf("22917")),
                    Kecamatan(nama = "Siabu", daftarKodePos = listOf("22976")),
                    Kecamatan(nama = "Bukit Malintang", daftarKodePos = listOf("22976")),
                    Kecamatan(nama = "Kotanopan", daftarKodePos = listOf("22994")),
                    Kecamatan(nama = "Lembah Sorik Marapi", daftarKodePos = listOf("22995")),
                    Kecamatan(nama = "Tambangan", daftarKodePos = listOf("22995")),
                    Kecamatan(nama = "Ulu Pungkut", daftarKodePos = listOf("22998")),
                    Kecamatan(nama = "Muara Sipongi", daftarKodePos = listOf("22993")),
                    Kecamatan(nama = "Batang Natal", daftarKodePos = listOf("22987")),
                    Kecamatan(nama = "Lingga Bayu", daftarKodePos = listOf("22986")),
                    Kecamatan(nama = "Batahan", daftarKodePos = listOf("22988")),
                    Kecamatan(nama = "Natal", daftarKodePos = listOf("22987")),
                    Kecamatan(nama = "Muara Batang Gadis", daftarKodePos = listOf("22989")),
                    Kecamatan(nama = "Ranto Baek", daftarKodePos = listOf("22986")),
                    Kecamatan(nama = "Huta Bargot", daftarKodePos = listOf("22918")),
                    Kecamatan(nama = "Puncak Sorik Marapi", daftarKodePos = listOf("22995")),
                    Kecamatan(nama = "Pakantan", daftarKodePos = listOf("22993")),
                    Kecamatan(nama = "Sinunukan", daftarKodePos = listOf("22988")),
                    Kecamatan(nama = "Naga Juang", daftarKodePos = listOf("22912"))
                )
            ),

            // KABUPATEN NIAS SELATAN
            KabupatenKota(
                nama = "Kabupaten Nias Selatan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Lolomatua", daftarKodePos = listOf("22862")),
                    Kecamatan(nama = "Gomo", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Lahusa", daftarKodePos = listOf("22865")),
                    Kecamatan(nama = "Hibala", daftarKodePos = listOf("22864")),
                    Kecamatan(nama = "Pulau-Pulau Batu", daftarKodePos = listOf("22881")),
                    Kecamatan(nama = "Teluk Dalam", daftarKodePos = listOf("22864")),
                    Kecamatan(nama = "Amandraya", daftarKodePos = listOf("22863")),
                    Kecamatan(nama = "Lolowau", daftarKodePos = listOf("22862")),
                    Kecamatan(nama = "Susua", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Maniamolo", daftarKodePos = listOf("22865")),
                    Kecamatan(nama = "Hilimegai", daftarKodePos = listOf("22864")),
                    Kecamatan(nama = "Toma", daftarKodePos = listOf("22865")),
                    Kecamatan(nama = "Mazino", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Umbunasi", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Aramo", daftarKodePos = listOf("22863")),
                    Kecamatan(nama = "Pulau-Pulau Batu Timur", daftarKodePos = listOf("22881")),
                    Kecamatan(nama = "Mazo", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Fanayama", daftarKodePos = listOf("22864")),
                    Kecamatan(nama = "Ulunoyo", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Huruna", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "O'o'u", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Onohazumba", daftarKodePos = listOf("22865")),
                    Kecamatan(nama = "Hilisalawa'ahe", daftarKodePos = listOf("22862")),
                    Kecamatan(nama = "Ulususua", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Sidua'ori", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Somambawa", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Boronadu", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Simuk", daftarKodePos = listOf("22881")),
                    Kecamatan(nama = "Pulau-Pulau Batu Barat", daftarKodePos = listOf("22881")),
                    Kecamatan(nama = "Pulau-Pulau Batu Utara", daftarKodePos = listOf("22881")),
                    Kecamatan(nama = "Tanah Masa", daftarKodePos = listOf("22881")),
                    Kecamatan(nama = "Luahagundre Maniamolo", daftarKodePos = listOf("22865")),
                    Kecamatan(nama = "Onolalu", daftarKodePos = listOf("22864")),
                    Kecamatan(nama = "Ulu Idanotae", daftarKodePos = listOf("22866")),
                    Kecamatan(nama = "Idanotae", daftarKodePos = listOf("22866"))
                )
            ),

            // KABUPATEN PAKPAK BHARAT
            KabupatenKota(
                nama = "Kabupaten Pakpak Bharat",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Sitelu Tali Urang Jehe", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Kerajaan", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Salak", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Sitelu Tali Urang Julu", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Pergetteng Getteng Sengkut", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Pagindar", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Tinada", daftarKodePos = listOf("22272")),
                    Kecamatan(nama = "Siempat Rube", daftarKodePos = listOf("22272"))
                )
            ),

            // KABUPATEN HUMBANG HASUNDUTAN
            KabupatenKota(
                nama = "Kabupaten Humbang Hasundutan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Parlilitan", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Pollung", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Baktiraja", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Paranginan", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Lintong Nihuta", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Dolok Sanggul", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Sijamapolang", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Onan Ganjang", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Pakkat", daftarKodePos = listOf("22253")),
                    Kecamatan(nama = "Tarabintang", daftarKodePos = listOf("22253"))
                )
            ),

            // KABUPATEN SAMOSIR
            KabupatenKota(
                nama = "Kabupaten Samosir",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Simanindo", daftarKodePos = listOf("22395")),
                    Kecamatan(nama = "Onan Runggu", daftarKodePos = listOf("22394")),
                    Kecamatan(nama = "Nainggolan", daftarKodePos = listOf("22393")),
                    Kecamatan(nama = "Palipi", daftarKodePos = listOf("22392")),
                    Kecamatan(nama = "Harian", daftarKodePos = listOf("22396")),
                    Kecamatan(nama = "Sianjar Mula Mula", daftarKodePos = listOf("22396")),
                    Kecamatan(nama = "Ronggur Nihuta", daftarKodePos = listOf("22392")),
                    Kecamatan(nama = "Pangururan", daftarKodePos = listOf("22392")),
                    Kecamatan(nama = "Sitio-Tio", daftarKodePos = listOf("22392"))
                )
            ),

            // KABUPATEN SERDANG BEDAGAI
            KabupatenKota(
                nama = "Kabupaten Serdang Bedagai",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Pantai Cermin", daftarKodePos = listOf("20987")),
                    Kecamatan(nama = "Perbaungan", daftarKodePos = listOf("20986")),
                    Kecamatan(nama = "Teluk Mengkudu", daftarKodePos = listOf("20991")),
                    Kecamatan(nama = "Sei Rampah", daftarKodePos = listOf("20995")),
                    Kecamatan(nama = "Tanjung Beringin", daftarKodePos = listOf("20996")),
                    Kecamatan(nama = "Bandar Khalipah", daftarKodePos = listOf("20994")),
                    Kecamatan(nama = "Dolok Merawan", daftarKodePos = listOf("20993")),
                    Kecamatan(nama = "Sipispis", daftarKodePos = listOf("20992")),
                    Kecamatan(nama = "Dolok Masihul", daftarKodePos = listOf("20991")),
                    Kecamatan(nama = "Kotarih", daftarKodePos = listOf("20991")),
                    Kecamatan(nama = "Silinda", daftarKodePos = listOf("20991")),
                    Kecamatan(nama = "Serba Jadi", daftarKodePos = listOf("20991")),
                    Kecamatan(nama = "Tebing Tinggi", daftarKodePos = listOf("20611")), // Note: Ini juga nama kota
                    Kecamatan(nama = "Pegajahan", daftarKodePos = listOf("20986")),
                    Kecamatan(nama = "Sei Bamban", daftarKodePos = listOf("20995")),
                    Kecamatan(nama = "Tebing Syahbandar", daftarKodePos = listOf("20995")),
                    Kecamatan(nama = "Bintang Bayu", daftarKodePos = listOf("20991"))
                )
            ),

            // KABUPATEN BATU BARA
            KabupatenKota(
                nama = "Kabupaten Batu Bara",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Medang Deras", daftarKodePos = listOf("21258")),
                    Kecamatan(nama = "Sei Suka", daftarKodePos = listOf("21257")),
                    Kecamatan(nama = "Air Putih", daftarKodePos = listOf("21256")),
                    Kecamatan(nama = "Lima Puluh", daftarKodePos = listOf("21255")),
                    Kecamatan(nama = "Talawi", daftarKodePos = listOf("21254")),
                    Kecamatan(nama = "Tanjung Tiram", daftarKodePos = listOf("21253")),
                    Kecamatan(nama = "Sei Balai", daftarKodePos = listOf("21252")),
                    Kecamatan(nama = "Laut Tador", daftarKodePos = listOf("21257")),
                    Kecamatan(nama = "Lima Puluh Pesisir", daftarKodePos = listOf("21255")),
                    Kecamatan(nama = "Datuk Lima Puluh", daftarKodePos = listOf("21255")),
                    Kecamatan(nama = "Datuk Tanah Datar", daftarKodePos = listOf("21256")),
                    Kecamatan(nama = "Nibung Hangus", daftarKodePos = listOf("21253"))
                )
            ),

            // KABUPATEN PADANG LAWAS UTARA
            KabupatenKota(
                nama = "Kabupaten Padang Lawas Utara",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Dolok Sigompulon", daftarKodePos = listOf("22754")),
                    Kecamatan(nama = "Dolok", daftarKodePos = listOf("22756")),
                    Kecamatan(nama = "Halongonan", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Padang Bolak", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Padang Bolak Julu", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Portibi", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Batang Onang", daftarKodePos = listOf("22756")),
                    Kecamatan(nama = "Simangambat", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Hulu Sihapas", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Padang Bolak Tenggara", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Halongonan Timur", daftarKodePos = listOf("22757")),
                    Kecamatan(nama = "Ujung Batu", daftarKodePos = listOf("22757"))
                )
            ),

            // KABUPATEN PADANG LAWAS
            KabupatenKota(
                nama = "Kabupaten Padang Lawas",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Sosopan", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Barumun Tengah", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Huristak", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Lubuk Barumun", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Huta Raja Tinggi", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Ulu Barumun", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Barumun", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Sosa", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Batang Lubu Sutam", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Barumun Selatan", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Aek Nabara Barumun", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Sihapas Barumun", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Barumun Baru", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Ulu Sosa", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Sosa Julu", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Barumun Barat", daftarKodePos = listOf("22753")),
                    Kecamatan(nama = "Sosa Timur", daftarKodePos = listOf("22753"))
                )
            ),

            // KABUPATEN LABUHANBATU SELATAN
            KabupatenKota(
                nama = "Kabupaten Labuhanbatu Selatan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Kotapinang", daftarKodePos = listOf("21464")),
                    Kecamatan(nama = "Kampung Rakyat", daftarKodePos = listOf("21463")),
                    Kecamatan(nama = "Torgamba", daftarKodePos = listOf("21466")),
                    Kecamatan(nama = "Sungai Kanan", daftarKodePos = listOf("21462")),
                    Kecamatan(nama = "Silangkitang", daftarKodePos = listOf("21461"))
                )
            ),

            // KABUPATEN LABUHANBATU UTARA
            KabupatenKota(
                nama = "Kabupaten Labuhanbatu Utara",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Kualuh Hulu", daftarKodePos = listOf("21457")),
                    Kecamatan(nama = "Kualuh Leidong", daftarKodePos = listOf("21458")),
                    Kecamatan(nama = "Kualuh Hilir", daftarKodePos = listOf("21459")),
                    Kecamatan(nama = "Aek Kuo", daftarKodePos = listOf("21457")),
                    Kecamatan(nama = "Marbau", daftarKodePos = listOf("21457")),
                    Kecamatan(nama = "Na Ix - X", daftarKodePos = listOf("21457")),
                    Kecamatan(nama = "Aek Natas", daftarKodePos = listOf("21457")),
                    Kecamatan(nama = "Kualuh Selatan", daftarKodePos = listOf("21457"))
                )
            ),

            // KABUPATEN NIAS UTARA
            KabupatenKota(
                nama = "Kabupaten Nias Utara",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Lotu", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Sawo", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Tuhemberua", daftarKodePos = listOf("22855")),
                    Kecamatan(nama = "Sitolu Ori", daftarKodePos = listOf("22855")),
                    Kecamatan(nama = "Namohalu Esiwa", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Alasa Talumuzoi", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Alasa", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Tugala Oyo", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Afulu", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Lahewa", daftarKodePos = listOf("22856")),
                    Kecamatan(nama = "Lahewa Timur", daftarKodePos = listOf("22856"))
                )
            ),

            // KABUPATEN NIAS BARAT
            KabupatenKota(
                nama = "Kabupaten Nias Barat",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Lahomi", daftarKodePos = listOf("22896")),
                    Kecamatan(nama = "Sirombu", daftarKodePos = listOf("22895")),
                    Kecamatan(nama = "Mandrehe Barat", daftarKodePos = listOf("22891")),
                    Kecamatan(nama = "Moro'o", daftarKodePos = listOf("22891")),
                    Kecamatan(nama = "Mandrehe", daftarKodePos = listOf("22891")),
                    Kecamatan(nama = "Mandrehe Utara", daftarKodePos = listOf("22891")),
                    Kecamatan(nama = "Lolofitu Moi", daftarKodePos = listOf("22891")),
                    Kecamatan(nama = "Ulu Moro'o", daftarKodePos = listOf("22891"))
                )
            ),

            // ========== KOTA ==========

            // KOTA MEDAN
            KabupatenKota(
                nama = "Kota Medan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Medan Kota", daftarKodePos = listOf("20211", "20212", "20213", "20214", "20215")),
                    Kecamatan(nama = "Medan Sunggal", daftarKodePos = listOf("20121", "20122", "20123")),
                    Kecamatan(nama = "Medan Helvetia", daftarKodePos = listOf("20124")),
                    Kecamatan(nama = "Medan Denai", daftarKodePos = listOf("20226", "20227", "20228", "20229")),
                    Kecamatan(nama = "Medan Barat", daftarKodePos = listOf("20111", "20112", "20113", "20114")),
                    Kecamatan(nama = "Medan Deli", daftarKodePos = listOf("20241", "20242", "20243", "20244")),
                    Kecamatan(nama = "Medan Tuntungan", daftarKodePos = listOf("20135", "20136", "20137", "20138")),
                    Kecamatan(nama = "Medan Belawan", daftarKodePos = listOf("20411", "20412", "20413", "20414")),
                    Kecamatan(nama = "Medan Amplas", daftarKodePos = listOf("20147", "20148", "20149")),
                    Kecamatan(nama = "Medan Area", daftarKodePos = listOf("20216", "20217", "20218")),
                    Kecamatan(nama = "Medan Johor", daftarKodePos = listOf("20142", "20143", "20144", "20145", "20146")),
                    Kecamatan(nama = "Medan Marelan", daftarKodePos = listOf("20250", "20251", "20252", "20254", "20255")),
                    Kecamatan(nama = "Medan Labuhan", daftarKodePos = listOf("20251", "20252", "20253")),
                    Kecamatan(nama = "Medan Tembung", daftarKodePos = listOf("20221", "20222", "20223", "20224", "20225")),
                    Kecamatan(nama = "Medan Maimun", daftarKodePos = listOf("20151", "20152", "20153", "20154", "20159")),
                    Kecamatan(nama = "Medan Polonia", daftarKodePos = listOf("20157")),
                    Kecamatan(nama = "Medan Baru", daftarKodePos = listOf("20154", "20155", "20156")),
                    Kecamatan(nama = "Medan Perjuangan", daftarKodePos = listOf("20232", "20233", "20234", "20235", "20236")),
                    Kecamatan(nama = "Medan Petisah", daftarKodePos = listOf("20112", "20115", "20116", "20117", "20118")),
                    Kecamatan(nama = "Medan Timur", daftarKodePos = listOf("20231", "20232", "20233", "20234", "20235")),
                    Kecamatan(nama = "Medan Selayang", daftarKodePos = listOf("20131", "20132", "20133", "20134"))
                )
            ),

            // KOTA PEMATANGSIANTAR
            KabupatenKota(
                nama = "Kota Pematangsiantar",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Siantar Timur", daftarKodePos = listOf("21131", "21132", "21133", "21134", "21135")),
                    Kecamatan(nama = "Siantar Barat", daftarKodePos = listOf("21111", "21112", "21113", "21114")),
                    Kecamatan(nama = "Siantar Utara", daftarKodePos = listOf("21141", "21142", "21143", "21144")),
                    Kecamatan(nama = "Siantar Selatan", daftarKodePos = listOf("21121", "21122", "21123", "21124")),
                    Kecamatan(nama = "Siantar Marihat", daftarKodePos = listOf("21127", "21128")),
                    Kecamatan(nama = "Siantar Martoba", daftarKodePos = listOf("21139")),
                    Kecamatan(nama = "Siantar Sitalasari", daftarKodePos = listOf("21136")),
                    Kecamatan(nama = "Siantar Marimbun", daftarKodePos = listOf("21128", "21129"))
                )
            ),

            // KOTA SIBOLGA
            KabupatenKota(
                nama = "Kota Sibolga",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Sibolga Utara", daftarKodePos = listOf("22531", "22532", "22533", "22534")),
                    Kecamatan(nama = "Sibolga Kota", daftarKodePos = listOf("22521", "22522", "22523", "22524")),
                    Kecamatan(nama = "Sibolga Selatan", daftarKodePos = listOf("22535", "22536", "22537", "22538")),
                    Kecamatan(nama = "Sibolga Sambas", daftarKodePos = listOf("22513"))
                )
            ),

            // KOTA TANJUNG BALAI
            KabupatenKota(
                nama = "Kota Tanjung Balai",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Tanjung Balai Selatan", daftarKodePos = listOf("21321", "21322", "21323", "21324", "21325")),
                    Kecamatan(nama = "Tanjung Balai Utara", daftarKodePos = listOf("21331", "21332", "21333", "21334", "21335")),
                    Kecamatan(nama = "Sei Tualang Raso", daftarKodePos = listOf("21341", "21342", "21343", "21344", "21345")),
                    Kecamatan(nama = "Teluk Nibung", daftarKodePos = listOf("21336", "21337", "21338")),
                    Kecamatan(nama = "Datuk Bandar", daftarKodePos = listOf("21361", "21362", "21363", "21364", "21365")),
                    Kecamatan(nama = "Datuk Bandar Timur", daftarKodePos = listOf("21361", "21362", "21363", "21364", "21365"))
                )
            ),

            // KOTA BINJAI
            KabupatenKota(
                nama = "Kota Binjai",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Binjai Utara", daftarKodePos = listOf("20741", "20742", "20743", "20744", "20745", "20746")),
                    Kecamatan(nama = "Binjai Kota", daftarKodePos = listOf("20711", "20712", "20713", "20714", "20715")),
                    Kecamatan(nama = "Binjai Barat", daftarKodePos = listOf("20721", "20722", "20723", "20724", "20725", "20726")),
                    Kecamatan(nama = "Binjai Timur", daftarKodePos = listOf("20731", "20732", "20733", "20734", "20735", "20736", "20737")),
                    Kecamatan(nama = "Binjai Selatan", daftarKodePos = listOf("20731", "20732", "20733", "20734", "20735"))
                )
            ),

            // KOTA TEBING TINGGI
            KabupatenKota(
                nama = "Kota Tebing Tinggi",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Padang Hulu", daftarKodePos = listOf("20631", "20632", "20633")),
                    Kecamatan(nama = "Rambutan", daftarKodePos = listOf("20611", "20612", "20613", "20614", "20615")),
                    Kecamatan(nama = "Padang Hilir", daftarKodePos = listOf("20621", "20622", "20623", "20624", "20625")),
                    Kecamatan(nama = "Bajenis", daftarKodePos = listOf("20626", "20627", "20628")),
                    Kecamatan(nama = "Tebing Tinggi Kota", daftarKodePos = listOf("20616", "20617", "20618", "20619"))
                )
            ),

            // KOTA PADANGSIDIMPUAN
            KabupatenKota(
                nama = "Kota Padangsidimpuan",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Padangsidimpuan Utara", daftarKodePos = listOf("22711", "22712", "22713", "22714", "22715")),
                    Kecamatan(nama = "Padangsidimpuan Selatan", daftarKodePos = listOf("22721", "22722", "22723", "22724", "22725")),
                    Kecamatan(nama = "Padangsidimpuan Batunadua", daftarKodePos = listOf("22731", "22732", "22733")),
                    Kecamatan(nama = "Padangsidimpuan Hutaimbaru", daftarKodePos = listOf("22731", "22732", "22733")),
                    Kecamatan(nama = "Padangsidimpuan Tenggara", daftarKodePos = listOf("22731", "22732", "22733")),
                    Kecamatan(nama = "Padangsidimpuan Angkola Julu", daftarKodePos = listOf("22731", "22732", "22733"))
                )
            ),

            // KOTA GUNUNGSITOLI
            KabupatenKota(
                nama = "Kota Gunungsitoli",
                daftarKecamatan = listOf(
                    Kecamatan(nama = "Gunungsitoli", daftarKodePos = listOf("22811", "22812", "22813", "22814", "22815")),
                    Kecamatan(nama = "Gunungsitoli Selatan", daftarKodePos = listOf("22815")),
                    Kecamatan(nama = "Gunungsitoli Utara", daftarKodePos = listOf("22815")),
                    Kecamatan(nama = "Gunungsitoli Idanoi", daftarKodePos = listOf("22815")),
                    Kecamatan(nama = "Gunungsitoli Alo'oa", daftarKodePos = listOf("22815")),
                    Kecamatan(nama = "Gunungsitoli Barat", daftarKodePos = listOf("22815"))
                )
            )
        )
    )
}