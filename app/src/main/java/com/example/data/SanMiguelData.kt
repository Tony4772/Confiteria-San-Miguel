package com.example.data

import com.example.model.BonbonFlavor
import com.example.model.BoxTier
import com.example.model.CartItem
import com.example.model.Product
import com.example.model.SensoryProfile

object SanMiguelData {
  const val BRAND_NAME = "Fernandina de Jagua"
  const val BRAND_SUBNAME = "Chocolates"
  const val BRAND_TAGLINE = "Chocolatería artesanal en San Miguel"
  const val COMPANY_NAME = "EBYZOM E.I.R.L."
  const val ADDRESS = "Jr. Comandante Juan Guillermo Moore 296"
  const val DISTRICT = "San Miguel, Lima — Perú"
  const val ZONE = "San Miguel • Lima Oeste"
  const val WHATSAPP_DISPLAY = "906 591 037"
  const val WHATSAPP_E164 = "51906591037"
  const val GOOGLE_MAPS_URL = "https://maps.google.com/?q=Jr.+Comandante+Juan+Guillermo+Moore+296,+San+Miguel,+Lima,+Peru"

  const val LOGO_URL =
    "https://lh3.googleusercontent.com/aida/AEtjO1XgS194rmpMVmsu7ZxJSU6rRsFuHF4O8B_zRNENRfuSs_oagXruylRTI0SDMiVqCf8axjQMwCjdGsE9g77u-YnjoRnv4bCv98XOkAkagt_y4NuC6phODGZ_zYkV9J1qBBRk5xFp2ZjxO9AVAHy49m7GnjltmucYDMHLYheSBjgfhrfHyRuHnwfy4e2d8Ocr948vMoRsGNeArBktKJoQ3DLaC1--lS3KRhsaq-EitSE2uDno7SfGM7AvySy7ERESR-ic4PpvrP6PBQ"

  const val HERO_IMAGE_URL =
    "https://lh3.googleusercontent.com/aida-public/AB6AXuAZO0SjunW9PAM3zWRiNq7OSnY_dJm7_U4n5GsUWKJcnHBv9qz8B-hvYPkwJRtEvJJPXCho89DAJGxEM4_WEK4DHdipXh_ldRQalHJqZhEqQtD4cMu4-NxsTG5kCV8pGnFB-jNnGMpir_DvAI8B5f_SAbwnmZ1YKEIlmYkCm9ZZVP1hhUGN9iENBDjcZyaGuUtkG3dRTJy7cwYP6a2Dwi8Jp2XNXKzi-P-BOrkJHFGeidhZe3DHEWRn"

  const val AMBIENT_IMAGE_URL =
    "https://lh3.googleusercontent.com/aida-public/AB6AXuB4dgKAy6-Uoz3AoGJtobWTTMTCL8LPpZprPRGBNgI4xDwn0jJ0oT0y89CDc0hKvnAVv3kFkUZ6dgYkpltXl2p34FSFjmzDC0SCX4d50t56N-fkXuZaz0aSw6GmDy0x1pOAVoQy5UOvv64MSXY9fOTdK5MrhUHtp1ghPSZWmLRJ1AEPbNNTH0CYRQnPajoV26BrqcJ9FpZHwSoFUOmLE_FWpS6zwJhTS2uEIUDrbziYtnhT4bt2zjh0"

  val categories = listOf(
    Pair("Barras Puras", "https://lh3.googleusercontent.com/aida-public/AB6AXuCzfn5-EatkC0GTOTODeigjbf8UwZevZ8kHwK4PRlHBsvChOrH1ew8-_tXhWtimZ9lGOhqwSHq5ajFwkKnu8mO3Fen_zdzz2qHgUOwzZ35h30pkl0gn82gpgLzXVMjj5WtT6PAGCk0HSigpuSGurUz8WUcQUsrovAsgaDXYVzAde1sfYh7lKoyZW00tzyBvfEXotle6m9M9yYd_v_kzM5OTzi3mYD_qaTFebAZPyCXlohpndZPPW0YB"),
    Pair("Bombones & Trufas", "https://lh3.googleusercontent.com/aida-public/AB6AXuCqqXN-7FJmryt3O4Cvxhtw_ytdztIDE0UAdFiI8mNvApeSFx_d52Qh2noNTUaq71MuEHo59YhDMrrschRfYMj0qTNiQam_diaTwzrohNPOEisT84IBsbQj-b3v95XfdS1r0qDSdDYhp49D900zaZzk9x2JnkSrFIsq8LLyg3HWr9aEz0Sf3gzWn0Sqet2qw1dxipUm_PNhFZOCqzQB4uur6BLstDNBV5IcRpUUxaNRqBfObkjeN0Li"),
    Pair("Cacao & Frutas", "https://lh3.googleusercontent.com/aida-public/AB6AXuAF6WthTtmWA9PvTmj0LDSmMaHbG1_D8ESXnKM7yrQsOQfA8QP40g8ZcjQm1k7c0iZ1iRYgBWqPAj8fRgV8CgXOsaqNeNWUESGVBx2jQweb_WTRpBBnX2lVYAYuuQ_xwc3-fbuFJVkxgjIjiAAlK_TkYsZ1nZxR1P4-A2r6lIlNWYjDardqnjk4IxtOJL3POImQnjGEneTRsiV2_jmYGPtCiBKL8UH86xfPH5Kei7yiu6T_A3fNAlP9"),
    Pair("Colecciones Especiales", "https://lh3.googleusercontent.com/aida-public/AB6AXuBtjvqiC1RcMrQdkyP7Yf9w62rkIHOM4Jgn0Il3klI55dtJU6zJ8x3iLaJ5GzhJ7RMAunTfKel9wrw2I4bku8rcb9YlcGtvBB1KcIf4f18VYln8OQlfNGzeEoIDnQEm2ncUwpLLijXArxLgDJQUDrnp3VAUPC7FT011GGD39yqrmID70w84oMaECDPDTmV28YION8UQGSHpb5cSk0q27Z6eOewMJKmjGI2SYhpdmg4PZKdglQ3YMGbW")
  )

  val products = listOf(
    Product(
      id = "barra-70-cacao-origen",
      name = "Barra Gran Cacao 70% Origen",
      subtitle = "Origen Seleccionado",
      price = 18.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCzfn5-EatkC0GTOTODeigjbf8UwZevZ8kHwK4PRlHBsvChOrH1ew8-_tXhWtimZ9lGOhqwSHq5ajFwkKnu8mO3Fen_zdzz2qHgUOwzZ35h30pkl0gn82gpgLzXVMjj5WtT6PAGCk0HSigpuSGurUz8WUcQUsrovAsgaDXYVzAde1sfYh7lKoyZW00tzyBvfEXotle6m9M9yYd_v_kzM5OTzi3mYD_qaTFebAZPyCXlohpndZPPW0YB",
      detailImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBGhmDQOOIZMnPOKG7142iBfX4CcFFXbwXUREKNrkDCSqB99cRmVPv8TTsb1H_saR3LlrOfxmzXTfzQYqgr9Oj4MNDH3mzqakb1o7BHe8T45Ri61AKHvZh3iqPoOlKWyVnQPMZwJjZ-6VkT9yuZZropZDg8CK7RDHrY_LNdWHaSWxld8NdRgGvh5VwxNY-S6iXYWjufnacYOXXShMur0oOJFuSizC_5t0ZKJAXnkqEvrsWs5d0Yp9Dk",
      badge = "Cacao 70%",
      badgeColor = "primary",
      category = "puras",
      categoryLabel = "Barras Puras",
      origin = "Cacao Peruano Seleccionado · San Miguel, Lima",
      shortDesc = "Notas profundas de frutos rojos secos, ligero toque floral y final a roble tostado.",
      fullDesc = "Elaborada con granos seleccionados de cacao peruano finamente tostados y refinados. Revela un perfil aromático equilibrado con notas profundas a frutos secos y un final prolongado y suave en boca.",
      ingredients = "Pasta de cacao, manteca de cacao y panela orgánica.",
      netWeight = "80 gramos",
      aromaProfile = "Frutos secos, madera noble y miel tibia",
      conservation = "16°C – 19°C · Lugar fresco y seco",
      intensity = 4.5,
      intensityDesc = "Carácter de tostado balanceado sin amargor agresivo.",
      fruitNotes = 3.8,
      fruitNotesDesc = "Matices a pasas rubias y frutos secos caramelizados.",
      persistence = 4.8,
      persistenceDesc = "Despliegue envolvente y retrogusto untuoso de cacao noble.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 5,
        sweetnessLevel = 2,
        aromaNotes = listOf("Frutos secos", "Madera noble", "Miel tibia", "Roble tostado")
      )
    ),
    Product(
      id = "bombones-artesanales-surtidos",
      name = "Bombones Artesanales Surtidos",
      subtitle = "Rellenos Nobles",
      price = 32.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCqqXN-7FJmryt3O4Cvxhtw_ytdztIDE0UAdFiI8mNvApeSFx_d52Qh2noNTUaq71MuEHo59YhDMrrschRfYMj0qTNiQam_diaTwzrohNPOEisT84IBsbQj-b3v95XfdS1r0qDSdDYhp49D900zaZzk9x2JnkSrFIsq8LLyg3HWr9aEz0Sf3gzWn0Sqet2qw1dxipUm_PNhFZOCqzQB4uur6BLstDNBV5IcRpUUxaNRqBfObkjeN0Li",
      detailImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDL-EjW4LAP0xQidxF0GjyMCbSpvpnqlafbUG1HFZxAQpy_GhH1wEMXRSJOfssnOeDc_ePpGNxDfurBstnJYtpz1HSrKhloXyRXsFa4j8LQf3TE13WpJHJnly7uAs7rYN05E84h7c4xa10XN7D2RsCLl9CmI9U8rIzrzFExaaiGCaX5metbaze8Zpz5htimEk1FECgSZf3RqcZtjtdc8XGaaDbTd31tnGscikJBwSrGrJmmZDcUAlc4",
      badge = "Selección",
      badgeColor = "secondary",
      category = "bombones",
      categoryLabel = "Bombones & Trufas",
      origin = "Valle de Chanchamayo y Quillabamba",
      shortDesc = "Ganache de maracuyá, praliné de avellanas crocantes y caramelo con infusión de vainilla.",
      fullDesc = "Caja de piezas facetadas a mano con pinceladas doradas. Cada bombón conjuga una fina capa exterior de cacao al 65% con centros aromáticos que van desde pulpa de maracuyá reducida hasta praliné tostado en piedra.",
      ingredients = "Cobertura de cacao 65%, pulpa de maracuyá, crema de leche fresca, avellanas tostadas y vainilla de convento.",
      netWeight = "12 unidades (140g aprox.)",
      aromaProfile = "Acidez tropical, praliné tostado y caramelo dorado",
      conservation = "15°C – 18°C · Consumo preferente dentro de los 21 días",
      intensity = 3.9,
      intensityDesc = "Equilibrio sedoso entre la potencia del cacao y el dulzor de fruta natural.",
      fruitNotes = 4.7,
      fruitNotesDesc = "Explosión cítrica y aromática de maracuyá de huerto fresco.",
      persistence = 4.4,
      persistenceDesc = "Sensación aterciopelada y cremosidad duradera en paladar.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 4,
        sweetnessLevel = 3,
        aromaNotes = listOf("Maracuyá", "Praliné tostado", "Avellanas", "Vainilla")
      )
    ),
    Product(
      id = "chocolate-oscuro-frutos-secos",
      name = "Chocolate Oscuro con Frutos Secos",
      subtitle = "Textura & Grano",
      price = 22.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAF6WthTtmWA9PvTmj0LDSmMaHbG1_D8ESXnKM7yrQsOQfA8QP40g8ZcjQm1k7c0iZ1iRYgBWqPAj8fRgV8CgXOsaqNeNWUESGVBx2jQweb_WTRpBBnX2lVYAYuuQ_xwc3-fbuFJVkxgjIjiAAlK_TkYsZ1nZxR1P4-A2r6lIlNWYjDardqnjk4IxtOJL3POImQnjGEneTRsiV2_jmYGPtCiBKL8UH86xfPH5Kei7yiu6T_A3fNAlP9",
      badge = "Cacao 60%",
      badgeColor = "primary",
      category = "frutas",
      categoryLabel = "Cacao & Frutas",
      origin = "Cacao San Martín y frutos secos andinos",
      shortDesc = "Almendras tostadas enteras, pistachos seleccionados y un balance crujiente excepcional.",
      fullDesc = "Elaborado con frutos secos levemente dorados al horno para despertar sus aceites esenciales naturales, integrados a una base de chocolate negro untuoso de acidez equilibrada.",
      ingredients = "Pasta de cacao 60%, azúcar de caña integral, almendras tostadas, pistachos pelados y arándanos deshidratados.",
      netWeight = "90 gramos",
      aromaProfile = "Frutos tostados, aceites nobles y madera ligera",
      conservation = "16°C – 20°C · Lugar fresco y libre de humedad",
      intensity = 4.0,
      intensityDesc = "Cuerpo untuoso con contraste crujiente de alta satisfacción.",
      fruitNotes = 3.5,
      fruitNotesDesc = "Destellos de arándanos silvestres con suave acidez.",
      persistence = 4.2,
      persistenceDesc = "Retrogusto tostado de frutos secos y cacao templado.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 4,
        sweetnessLevel = 2,
        aromaNotes = listOf("Almendras tostadas", "Pistacho", "Arándanos", "Madera noble")
      )
    ),
    Product(
      id = "barra-85-cacao-intenso",
      name = "Barra 85% Cacao Intenso",
      subtitle = "Perfil Puro",
      price = 19.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBwaMQUUOxU2iPvCRPwvZNXvGoZo-dHJnAQ5fw8Si3nKMFBq0CsqgSNJwuGG4rWYjIKWnorsQTpiLnovRPLYtDKx-BdxiJyRL7l5ubsLAacQhQsh0wH5ONTnOYx5dgiJtRvZpkIDgRkqp_SJTiin7vvfJW3PpsVoDuDvP8DSKvHKN1zk18y43YLMMU-zya3MQyU146BDR7gNCYQnu_5sfcp5fMU0BD9jSegwvo12l8HCf51DVvyCjLs",
      detailImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuC1YJP6spZPVkdxvVfCCJxkCeL7O8ExpC3W9aoIvDhd4grcJba_GyljHhoXuww6meKjxzHaNZKwcjGjUeA7Yq0-jujEg126Ru2KXITsal8mJFcQCjJTinVl9phZGn4HglxwFe4_8v6SHhGTWq9jg_p87E5mJi38OD-NbV2SoofCZk2JQopnk9HmSHB9HFPFf2eY-66FdEDmIlrvr4XNWeMlezaKrUit4PaXXLhRxBABjEjCIogSeen8",
      badge = "Cacao 85%",
      badgeColor = "primary",
      category = "puras",
      categoryLabel = "Barras Puras",
      origin = "Cacao Nativo Blanco de Piura",
      shortDesc = "Potente carácter tánico con notas terrosas, tabaco rubio y retrogusto persistente.",
      fullDesc = "Una experiencia sin concesiones para paladares experimentados. Mínima adición de azúcar de caña orgánica para resaltar la pureza vegetal y la potencia mineral del grano virgen.",
      ingredients = "Granos de cacao 85%, manteca pura de cacao y 15% de panela orgánica de Piura.",
      netWeight = "80 gramos",
      aromaProfile = "Humo dulce, tabaco rubio, cuero y tierra húmeda",
      conservation = "15°C – 19°C · Lugar fresco y seco",
      intensity = 4.9,
      intensityDesc = "Fuerza astringente controlada y notas tónicas muy marcadas.",
      fruitNotes = 2.9,
      fruitNotesDesc = "Notas secas de higo y mora silvestre.",
      persistence = 5.0,
      persistenceDesc = "Máxima duración en boca; presencia vegetal inconfundible.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 5,
        sweetnessLevel = 1,
        aromaNotes = listOf("Tabaco rubio", "Humo dulce", "Higo seco", "Tierra húmeda")
      )
    ),
    Product(
      id = "trufas-cafe-canela",
      name = "Trufas de Café y Canela",
      subtitle = "Infusión Especial",
      price = 26.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuApp9i2J-C8ZnA7RIP2KiR9Lfc_kZiddrrfRrfboMbThbur9P9ngZUwupBfXKWmw_SKRaCQ2gzq84gFMsqICwPbTW8Qx4EwOQeNC4a50OqzPyGT0tS7XAT8T-ldrpwId2SiI3yf6uKDoLwW7z0evIa1I9xPjEWiVFXek5L3rFczNoSZhaLGhVyPw1ZRAQlAfv7TFpjVb1wE8W8qFztjEjAJxrSfGqSeseK4mfFHj7DEYbqd1zNejII8",
      badge = "Aromático",
      badgeColor = "secondary",
      category = "bombones",
      categoryLabel = "Bombones & Trufas",
      origin = "Cacao Junín y Café Villa Rica",
      shortDesc = "Corazón cremoso emulsionado con café de altura y un toque sutil de canela ceilán.",
      fullDesc = "Textura untuosa que se funde en boca de manera inmediata. Emulsión lenta de crema fresca, reducción de café arábica y velo exterior de cacao puro con canela espolvoreada.",
      ingredients = "Cacao al 70%, extracto concentrado de café de Villa Rica, crema de leche de pastura, canela de Ceilán molida.",
      netWeight = "8 unidades (110g)",
      aromaProfile = "Café tostado, canela en rama y crema dulce",
      conservation = "14°C – 18°C · Mantener refrigerado si la temperatura ambiente supera 22°C",
      intensity = 4.2,
      intensityDesc = "Fusión envolvente de café tostado y chocolate denso.",
      fruitNotes = 3.1,
      fruitNotesDesc = "Matices a ciruela pasa y notas especiadas cálidas.",
      persistence = 4.6,
      persistenceDesc = "El café y la canela perduran de forma noble y tibia.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 4,
        sweetnessLevel = 3,
        aromaNotes = listOf("Café tostado", "Canela Ceilán", "Crema fresca", "Ciruela pasa")
      )
    ),
    Product(
      id = "tableta-sal-de-maras",
      name = "Tableta con Sal de Maras",
      subtitle = "Contraste Andino",
      price = 20.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBtjvqiC1RcMrQdkyP7Yf9w62rkIHOM4Jgn0Il3klI55dtJU6zJ8x3iLaJ5GzhJ7RMAunTfKel9wrw2I4bku8rcb9YlcGtvBB1KcIf4f18VYln8OQlfNGzeEoIDnQEm2ncUwpLLijXArxLgDJQUDrnp3VAUPC7FT011GGD39yqrmID70w84oMaECDPDTmV28YION8UQGSHpb5cSk0q27Z6eOewMJKmjGI2SYhpdmg4PZKdglQ3YMGbW",
      detailImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD5Y7UgItNwbHvVgJ9cQAqtvrsOYIwYG5yh0k2NSwmcS1boLfqQOg0csuqP-J9BUVoYkad2T3RfNtsvg4o0mRfhHb1GFNjFdnqYP94T6ye14s2E7q438AGF6bwIuj57BDR58VFWzMeaZ6Xm6Bz2zsDGRmYPVb03Qk9cphFtl5wZcHw5l4E6fYs1QoEeeVtD51bZbBdYuuVxk5g6WJq53Id_X8sKNlDaOStKjylSCS4ZD59nc0_No7Ua",
      badge = "Cacao 68%",
      badgeColor = "primary",
      category = "especialidades",
      categoryLabel = "Colecciones Especiales",
      origin = "Cacao Cusco y Salineras de Maras",
      shortDesc = "Cristales minerales rosados que potencian los tonos acaramelados del cacao tibio.",
      fullDesc = "La pureza de la sal recolectada artesanalmente en las salineras de Maras contrasta magistralmente con el perfil tostado y redondo de nuestro chocolate oscuro al 68%.",
      ingredients = "Pasta de cacao 68%, azúcar orgánica de caña, manteca de cacao, flor de sal rosada de Maras (Cusco).",
      netWeight = "80 gramos",
      aromaProfile = "Mineral salino, cacao tostado y caramelo moreno",
      conservation = "16°C – 19°C · Lugar fresco y seco",
      intensity = 4.3,
      intensityDesc = "El grano de sal intensifica las notas de mantequilla de cacao.",
      fruitNotes = 3.4,
      fruitNotesDesc = "Acentúa notas a higo seco y miel de chancaca.",
      persistence = 4.7,
      persistenceDesc = "Contraste mineral cristalino que limpia y aviva el paladar.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 4,
        sweetnessLevel = 2,
        aromaNotes = listOf("Sal de Maras", "Caramelo moreno", "Higo seco", "Manteca noble")
      )
    ),
    Product(
      id = "nibs-cacao-acaramelados",
      name = "Nibs de Cacao Acaramelados",
      subtitle = "Textura Pura",
      price = 16.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA-6P_kOVFE8noc3OCKjWuUGRfNP-n-lBtVDsLLF0iLM1jgeBwTZPvuiKVN3a_r4W2VWMfi3-X7ZyLD3hQ4MJ4LTwEYXWXg0vE1PS9mb2MDqE14B7gFGVX7cwtdZUTe4WNA_3h-5be0PMa8PB_wO2qsEuAaOkKtJg45kYQ0-dxEejEq2J5dhPmhudaBGqkyvU5b21qnRN4hHkE4Iqa9m74-i7urUX3W3idvvj8CDPJS3liC_5BXYjO9",
      badge = "Crujiente",
      badgeColor = "secondary",
      category = "especialidades",
      categoryLabel = "Colecciones Especiales",
      origin = "San Martín, Perú",
      shortDesc = "Trozos de grano tostado envueltos en una micro-capa de caramelo rubio artesanal.",
      fullDesc = "Nibs seleccionados tostados al punto justo para mantener su complejidad botánica, recubiertos por una sutil película crujiente de azúcar de caña morena.",
      ingredients = "Nibs de cacao nativo tostado 80%, azúcar rubia de caña 20%.",
      netWeight = "100 gramos",
      aromaProfile = "Tostado intenso, nuez y caramelo dorado",
      conservation = "Ambiente fresco y seco en envase hermético",
      intensity = 4.1,
      intensityDesc = "Crocancia directa con el amargor botánico del grano puro.",
      fruitNotes = 2.8,
      fruitNotesDesc = "Matices maderosos con dulzor de caramelo de paila.",
      persistence = 4.3,
      persistenceDesc = "Crujido sonoro y regusto largo a cacao campesino.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 4,
        sweetnessLevel = 2,
        aromaNotes = listOf("Nibs tostados", "Caramelo dorado", "Nuez", "Madera")
      )
    ),
    Product(
      id = "chocolate-fruta-amazonica",
      name = "Chocolate con Fruta Amazónica",
      subtitle = "Fruta Autóctona",
      price = 24.00,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuABBj0JQeMIhEh1jzR1w8LIJMMOpu3_HdlLar720GJv83vFADWt4uAAPP_ex5oFLGEAdpr4ddu8LmkAr8gDp6ERmcHH7I1faF1bVKbsV6C5r8823s2QmoSssSIm4jvkMiabhehkPk2ddN5-BgBsesUyERIKBMpFWGvPV6vOECXfPMppmHkKjF0rrDZKGtX_yFNsSUkAkfk7GNqPICsViDp4LUYz5HyY0bgFlQaVcwwjRxv6LKndmCAY",
      badge = "Cacao 72%",
      badgeColor = "primary",
      category = "frutas",
      categoryLabel = "Cacao & Frutas",
      origin = "Ucayali y Loreto, Amazonía Peruana",
      shortDesc = "Coulis natural de aguaje y camu-camu con acidez vibrante en cobertura intensa.",
      fullDesc = "Equilibrio maestro entre la alta concentración de polifenoles del cacao nativo y la frescura ácida de frutos amazónicos recolectados de manera sostenible en comunidades nativas.",
      ingredients = "Pasta de cacao 72%, manteca de cacao, pulpa de camu-camu y aguaje silvestre, panela orgánica.",
      netWeight = "80 gramos",
      aromaProfile = "Acidez punzante de selva baja, fruta amarilla y cacao oscuro",
      conservation = "15°C – 18°C · Lugar fresco",
      intensity = 4.4,
      intensityDesc = "Acidez vivaz que corta la densidad de la manteca de cacao.",
      fruitNotes = 4.9,
      fruitNotesDesc = "Vitamina C natural y astringencia frutal electrizante.",
      persistence = 4.5,
      persistenceDesc = "Sensación fresca y frutal prolongada.",
      sensoryProfile = SensoryProfile(
        cocoaIntensity = 4,
        sweetnessLevel = 2,
        aromaNotes = listOf("Camu-camu", "Aguaje silvestre", "Acidez amazónica", "Cacao oscuro")
      )
    )
  )

  val boxTiers = listOf(
    BoxTier(size = 6, name = "Cofre Petit", subtitle = "6 Piezas", price = 15.00),
    BoxTier(size = 12, name = "Cofre Clásico", subtitle = "12 Piezas", price = 28.00, isFavorite = true),
    BoxTier(size = 24, name = "Gran Reserva", subtitle = "24 Piezas", price = 52.00)
  )

  val bonbonFlavors = listOf(
    BonbonFlavor(
      id = "pistachio",
      name = "Ganache de Sacha Inchi",
      description = "Sacha inchi tostado de San Martín con chocolate con leche 42%",
      notes = "Notas tostadas y salinidad sutil",
      category = "milk",
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuC3oK5amoCi7GA3JS0_Wjz2pCHt7tPtE1A_6bcAXI8qRMOGMhSO70Ck680341MYk2vtWGqLM88xiT8qMmMLfdITFt8MbGX7Hi5F9gsxY58qi3cKGqWkTwrviLUFs2y9P0Rf042eje6BB4KSMD0rAYC8m0XHPZd5xL8y8Dr0HInAZqOQ7FfkzBBeVHiK1jdi7a_ZbD2Bhq0k2rC3tURegRHmMnbfLGRLjZrETsEwuP6T-QSFdrzlLT7Eew",
      bonbonImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCYN6ySGbPaPP1DMAdjsSix62JkJ8NAoMYgKIDPfxbgUjXCIjLIk_RgsILmosaxCkpKpD9RzufjTLN-OObDVzcHhoNy_4pOZMIkLMMO3FAZ8TylgzttBBAsUD2poFmhuPru7Vn6nYwnqNmyQWMJDrmlsdlMCOftfJWxlDjqRx6P6iGfcia4JkXW8I0gyz2biFEhgQPzkji6h_Cmms3toi6weCed5e_Csq2yeM4z_Wt85jnoqvSLFgUMnQ",
      tag = "Estrella"
    ),
    BonbonFlavor(
      id = "ruby",
      name = "Frambuesa Silvestre Ruby",
      description = "Cacao Ruby natural y coulis de frambuesas de montaña",
      notes = "Acidez vivaz y toque floral",
      category = "fruit",
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAXsvL5f0Sao5EQ7xfcpd9K_WBAKuCUls9EWbF8nlVW_Yq4IrUIpAy3u3G1-nZPmpfzuzRkFJp44WTQXty30fKHKunvw0AFfX1Ow0al_wYo99d8zUJAjSVKJtDU2cnEqK4jEZ1fRcZnPEQMUhra_aybmVHnD4aTx8UWV9ikhis1mWX_riP9WvCrKlEXYiGjXBVYjRFqfwOes9kbPBMADppN2PZsl-OLM0br7Ytsx0dhREaJ17rFkmmFUw",
      bonbonImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBMe7rkPEHAAtTpFc_VD8nvm2vPPiHoETfzdqEY0MTH0ly4CVvEfQfxdwvOzaB4l_-uktGTNsNT3fDq3zAVFKnPU0SZey8SvXhFo9s4dT5OKWBkmzaO8TGk1U2S3tq2mQVzV8-EW1KpJECNWmoOyBK6zruSFPdsVPdYm67fpzg6JgEoa9VYY_1TfOgpEaUNz-9fTtvvT2FzwTjsNlOCBXDM4N_eLWehOFs9luyTr03GBRkqZGFuFhHYuA"
    ),
    BonbonFlavor(
      id = "caramel",
      name = "Caramelo a la Flor de Sal",
      description = "Mantequilla de Cajamarca con cristales de sal de Maras",
      notes = "Textura melosa con contraste salino",
      category = "milk",
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA_fgV4VMRDxuso-BrW7qCid7e28mOUHuUHlHdJ7f5y-qMteHPWPcnDu1Xkzhwb_xcExszFU988EGZ4arqVIuVlvtY2wqIO7NY4ZKKJstq_iXl9DrMtqi9cHYLjv-9untjFYOPI-lLCFW34G57apk3H9Lt4VX6nHh-oZzRh1ZidgtHQ8IusfVVqk4OmjboqoFZbKLioIkYnxQSCBTpoOIGqFun_BdAjJNfGTl-H4BXDlZXbg1CJ5Z_rAw",
      bonbonImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCZm2KLv13C38zDUKxgixv0HCasz97EnAcjO3mwP2ZoEWts4_3xZgvGzAsxmd-YflVJoO-ghwD4uOk5007igL-pGGuBPfG_lw79g8wE_38RRn_Hxt3fGw5vJKqkA7T8zJ1vW4KwJr0unMxazQU5dGnII0-O0XijcxW7iPj4t_hb1niKs4jtcwCW-huAzDcAfFMvp8NJSnpdNimIgFJY972ALjATVQVkc0lKywYiCAprWg_exh7jHVhswA"
    ),
    BonbonFlavor(
      id = "moka",
      name = "Moka de Chanchamayo",
      description = "Infusión en frío de café de Chanchamayo con cacao criollo 72%",
      notes = "Amargor elegante con notas de cardamomo",
      category = "dark",
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCRxXJBO_Y2B0oB4-G-3USf47mi-F0rOr3VsWNzYEw1rcgA26EEgCmQ-0o0JZBd_cEt95wbaCcl_gna0XXOKOFqxw7cek-r9JMbeRVEOrLO_PA2mgqb9wc50tH8cbyKNbwZjr-UP3eNydHhPTzDgUMem10KKHuF6U1e0smvlLap5RU6UeiSTRQ7AqEEvyZWluyuyx8j8iB457oxVK9kDhNYBPkJ3OQ3c8EHZ2GlNqAHU-ukqCopxd-nJA",
      bonbonImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAvzIpgS7bb7wL3i6lDIaCj0GmiynfyUFOwe4NJc0kHZif4YB4-JsZg-EFw5sj41W01YJZ-Jrf_vkhOqNDK39xAblmiVNRA-be0rxTsDOAwEloGNXXVU3Lr9P2nHmuG4YUfx6u-taq0DYO21ZefB_VNv53tp4lXNmBh2YO_13eICttj3eaEJvXfXMiI0lqp29dAyjrzFU-oDiI8RhcDN3HnPeEL_txzoJmbbjChUqbrWWegL7O_UxrkwQ"
    ),
    BonbonFlavor(
      id = "chuao",
      name = "Piura Blanco 75%",
      description = "Cacao blanco de Piura, notas de ciruela y frutos del bosque",
      notes = "Cacao puro intenso sin aditivos",
      category = "dark",
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCHP9txG8FsdlHwyecvwu9NoGaWc-CaYuosPQgDV6VNopEVNVcSXZ9UGdfUlekiTRERPRT_nig-I6aYOS-8DqJWNx83KvqjT1cz5WF5RWqAMxUVeZ-WbwCDUbY40yacnddRiaeMrJn7iQ8BhojKqwLfiz9BgvNaqeB9jPB2WsfytMSXvEOjXSE6mWCBjXjToPBgpd9cJo97UNdyIPELp9mJJWUaMTXuIGvMdlcdTsdvrFKNRuprki07vg",
      bonbonImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCb-iiQf-073G7QaMo7YnDX8EjLCAA7vYKqnaGkI3anqYN_cRNogJG6yZTY-N58RIh73l27PXxUBnEwVutTVdrXsD_62CUOtHvdMnnfz57xvmDHb5v-USi-CDi4BJmRjt71_YaTLQQ_AHJwyWo4HrOPoVQE4MCQvPvglrLCzA1Gy0-4BqFRLnFq_VNJ894cwBVzZTDMRUYz9I5FGffqc571dyV3DquK0zBW_dHUVOqGMgfIhM14nFygfA"
    ),
    BonbonFlavor(
      id = "sherry",
      name = "Pisco & Naranja",
      description = "Pisco acholado con piel de naranja peruana",
      notes = "Cálido y espirituoso",
      category = "liqueur",
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCJHa4hiwT7T2W7TK-97pNJnauyj7YfavoMax0Lc6zjRi35hpJ2uh2myb4jFxmJswzRw6Kl7Xra2lSP0i_G2muoiyhZzqLRWZPEvby0wbMRabBPzV7RzTZoLnJMQn0hENpNdc7Yybgg639xKdgOQ3jLo05AW0CBv-1akVGISX8m4_MoHDwJj126IVoqmnGcH_S0xzpD7PZqSpyz3l3UciyAEi0LEdvN2XCjNcjKmy_V3L8KB4tYX7Ix4g",
      bonbonImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA_zYcXUsXeUClHqtw0QV6_g-F7FGx4jo0iZZW3HOa4Z_0vuPY1II8g_2KQdTVAYupb6ATd5mZJVB0E4ySKUw08NRbi4AkSr5RVGLFpz0v9-7LZMU3-Kl21vqCBAHhJmKKGU-dhkHTp9maW1YCVDB6VQhzBWyEmyoi-bpWmV_hdbOcvT2N_EEMNjYalZzS0OTWVfKB7yowk860x0vFMw-qcUBSKcK7EZyg87gr8oRU10xLpJlugjfghJA",
      alcoholTag = "Licor 2.5%"
    )
  )

  val initialCart = listOf(
    CartItem(
      id = "c1",
      title = "Barra Gran Cacao 70% Origen",
      subtitle = "Origen Seleccionado",
      unitPrice = 18.00,
      quantity = 1,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCzfn5-EatkC0GTOTODeigjbf8UwZevZ8kHwK4PRlHBsvChOrH1ew8-_tXhWtimZ9lGOhqwSHq5ajFwkKnu8mO3Fen_zdzz2qHgUOwzZ35h30pkl0gn82gpgLzXVMjj5WtT6PAGCk0HSigpuSGurUz8WUcQUsrovAsgaDXYVzAde1sfYh7lKoyZW00tzyBvfEXotle6m9M9yYd_v_kzM5OTzi3mYD_qaTFebAZPyCXlohpndZPPW0YB"
    )
  )

  val detailGallery = listOf(
    "https://lh3.googleusercontent.com/aida-public/AB6AXuCzfn5-EatkC0GTOTODeigjbf8UwZevZ8kHwK4PRlHBsvChOrH1ew8-_tXhWtimZ9lGOhqwSHq5ajFwkKnu8mO3Fen_zdzz2qHgUOwzZ35h30pkl0gn82gpgLzXVMjj5WtT6PAGCk0HSigpuSGurUz8WUcQUsrovAsgaDXYVzAde1sfYh7lKoyZW00tzyBvfEXotle6m9M9yYd_v_kzM5OTzi3mYD_qaTFebAZPyCXlohpndZPPW0YB",
    "https://lh3.googleusercontent.com/aida-public/AB6AXuBGhmDQOOIZMnPOKG7142iBfX4CcFFXbwXUREKNrkDCSqB99cRmVPv8TTsb1H_saR3LlrOfxmzXTfzQYqgr9Oj4MNDH3mzqakb1o7BHe8T45Ri61AKHvZh3iqPoOlKWyVnQPMZwJjZ-6VkT9yuZZropZDg8CK7RDHrY_LNdWHaSWxld8NdRgGvh5VwxNY-S6iXYWjufnacYOXXShMur0oOJFuSizC_5t0ZKJAXnkqEvrsWs5d0Yp9Dk"
  )
}
