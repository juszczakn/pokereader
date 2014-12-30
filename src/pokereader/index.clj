(ns pokereader.index)

(def hex-poke-index
  {:01 "Rhydon"
   :02 "Kangaskhan"
   :03 "Nidoran"
   :04 "Clefairy"
   :05 "Spearow"
   :06 "Voltorb"
   :07 "Nidoking"
   :08 "Slowbro"
   :09 "Ivysaur"
   :0A "Exeggutor"
   :0B "Lickitung"
   :0C "Exeggcute"
   :0D "Grimer"
   :0E "Gengar"
   :0F "Nidoran"
   :10 "Nidoqueen"
   :11 "Cubone"
   :12 "Rhyhorn"
   :13 "Lapras"
   :14 "Arcanine"
   :15 "Mew"
   :16 "Gyarados"
   :17 "Shellder"
   :18 "Tentacool"
   :19 "Gastly"
   :1A "Scyther"
   :1B "Staryu"
   :1C "Blastoise"
   :1D "Pinsir"
   :1E "Tangela"
   :1F "Missingno"
   :20 "Missingno"
   :21 "Growlithe"
   :22 "Onix"
   :23 "Fearow"
   :24 "Pidgey"
   :25 "Slowpoke"
   :26 "Kadabra"
   :27 "Graveler"
   :28 "Chansey"
   :29 "Machoke"
   :2A "Mr. Mime"
   :2B "Hitmonlee"
   :2C "Hitmonchan"
   :2D "Arbok"
   :2E "Parasect"
   :2F "Psyduck"
   :30 "Drowzee"
   :31 "Golem"
   :32 "Missingno"
   :33 "Magmar"
   :34 "Missingno"
   :35 "Electabuzz"
   :36 "Magneton"
   :37 "Koffing"
   :38 "Missingno"
   :39 "Mankey"
   :3A "Seel"
   :3B "Diglett"
   :3C "Tauros"
   :3D "Missingno"
   :3E "Missingno"
   :3F "Missingno"
   :40 "Farfetch"
   :41 "Venonat"
   :42 "Dragonite"
   :43 "Missingno"
   :44 "Missingno"
   :45 "Missingno"
   :46 "Doduo"
   :47 "Poliwag"
   :48 "Jynx"
   :49 "Moltres"
   :4A "Articuno"
   :4B "Zapdos"
   :4C "Ditto"
   :4D "Meowth"
   :4E "Krabby"
   :4F "Missingno"
   :50 "Missingno"
   :51 "Missingno"
   :52 "Vulpix"
   :53 "Ninetales"
   :54 "Pikachu"
   :55 "Raichu"
   :56 "Missingno"
   :57 "Missingno"
   :58 "Dratini"
   :59 "Dragonair"
   :5A "Kabuto"
   :5B "Kabutops"
   :5C "Horsea"
   :5D "Seadra"
   :5E "Missingno"
   :5F "Missingno"
   :60 "Sandshrew"
   :61 "Sandslash"
   :62 "Omanyte"
   :63 "Omastar"
   :64 "Jigglypuff"
   :65 "Wigglytuff"
   :66 "Eevee"
   :67 "Flareon"
   :68 "Jolteon"
   :69 "Vaporeon"
   :6A "Machop"
   :6B "Zubat"
   :6C "Ekans"
   :6D "Paras"
   :6E "Poliwhirl"
   :6F "Poliwrath"
   :70 "Weedle"
   :71 "Kakuna"
   :72 "Beedrill"
   :73 "Missingno"
   :74 "Dodrio"
   :75 "Primeape"
   :76 "Dugtrio"
   :77 "Venomoth"
   :78 "Dewgong"
   :79 "Missingno"
   :7A "Missingno"
   :7B "Caterpie"
   :7C "Metapod"
   :7D "Butterfree"
   :7E "Machamp"
   :7F "Missingno"
   :80 "Golduck"
   :81 "Hypno"
   :82 "Golbat"
   :83 "Mewtwo"
   :84 "Snorlax"
   :85 "Magikarp"
   :86 "Missingno"
   :87 "Missingno"
   :88 "Muk"
   :89 "Missingno"
   :8A "Kingler"
   :8B "Cloyster"
   :8C "Missingno"
   :8D "Electrode"
   :8E "Clefable"
   :8F "Weezing"
   :90 "Persian"
   :91 "Marowak"
   :92 "Missingno"
   :93 "Haunter"
   :94 "Abra"
   :95 "Alakazam"
   :96 "Pidgeotto"
   :97 "Pidgeot"
   :98 "Starmie"
   :99 "Bulbasaur"
   :9A "Venusaur"
   :9B "Tentacruel"
   :9C "Missingno"
   :9D "Goldeen"
   :9E "Seaking"
   :9F "Missingno"
   :A0 "Missingno"
   :A1 "Missingno"
   :A2 "Missingno"
   :A3 "Ponyta"
   :A4 "Rapidash"
   :A5 "Rattata"
   :A6 "Raticate"
   :A7 "Nidorino"
   :A8 "Nidorina"
   :A9 "Geodude"
   :AA "Porygon"
   :AB "Aerodactyl"
   :AC "Missingno"
   :AD "Magnemite"
   :AE "Missingno"
   :AF "Missingno"
   :B0 "Charmander"
   :B1 "Squirtle"
   :B2 "Charmeleon"
   :B3 "Wartortle"
   :B4 "Charizard"
   :B5 "Missingno"
   :B6 "Missingno"
   :B7 "Missingno"
   :B8 "Missingno"
   :B9 "Oddish"
   :BA "Gloom"
   :BB "Vileplume"
   :BC "Bellsprout"
   :BD "Weepinbell"
   :BE "Victreebel"})