(ns pokereader.index)

(defn negate-twos-compliment
  [b]
  (if (neg? b) (+ 256 b) b))

(def hex-to-ascii-map
  {:80 "A" :81 "B" :82 "C" :83 "D" :84 "E" :85 "F" :86 "G" :87 "H"
   :88 "I" :89 "J" :8A "K" :8B "L" :8C "M" :8D "N" :8E "O" :8F "P"

   :90 "Q" :91 "R" :92 "S" :93 "T" :94 "U" :95 "V" :96 "W" :97 "X"
   :98 "Y" :99 "Z" :9A "(" :9B ")" :9C ":" :9D ";" :9E "[" :9F "]"

   :A0 "a" :A1 "b" :A2 "c" :A3 "d" :A4 "e" :A5 "f" :A6 "g" :A7 "h"
   :A8 "i" :A9 "j" :AA "k" :AB "l" :AC "m" :AD "n" :AE "o" :AF "p"

   :B0 "q" :B1 "r" :B2 "s" :B3 "t" :B4 "u" :B5 "v" :B6 "w" :B7 "x"
   :B8 "y" :B9 "z"

   :E1 "pk" :E2 "mn" :E3 "-" :E4 :E5 :E6 "?" :E7 "!" :E8 "." :EF "♂"

   :F1 "x" :F3 "/" :F4 "," :F5 "♀" :F6 "0" :F7 "1" :F8 "2" :F9 "3"
   :FA "4" :FB "5" :FC "6" :FD "7" :FE "8" :FF "9"

   :50 "\n"})

(def hex-poke-index
  {:01 "Rhydon"
   :02 "Kangaskhan"
   :03 "Nidoran♂"
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
   :0F "Nidoran♀"
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

(defn get-from-hex-poke-index
  [hex]
  (hex-poke-index hex))

(def poke-index
  {"Bulbasaur" 1
   "Ivysaur" 2  
   "Venusaur" 3 
   "Charmander" 4   
   "Charmeleon" 5   
   "Charizard" 6
   "Squirtle" 7 
   "Wartortle" 8
   "Blastoise" 9
   "Caterpie" 10 
   "Metapod" 11  
   "Butterfree" 12   
   "Weedle" 13   
   "Kakuna" 14   
   "Beedrill" 15 
   "Pidgey" 16   
   "Pidgeotto" 17
   "Pidgeot" 18  
   "Rattata" 19  
   "Raticate" 20 
   "Spearow" 21  
   "Fearow" 22   
   "Ekans" 23
   "Arbok" 24
   "Pikachu" 25  
   "Raichu" 26   
   "Sandshrew" 27
   "Sandslash" 28
   "Nidoran♀" 29  
   "Nidorina" 30 
   "Nidoqueen" 31
   "Nidoran♂" 32  
   "Nidorino" 33 
   "Nidoking" 34 
   "Clefairy" 35 
   "Clefable" 36 
   "Vulpix" 37   
   "Ninetales" 38
   "Jigglypuff" 39   
   "Wigglytuff" 40   
   "Zubat" 41
   "Golbat" 42   
   "Oddish" 43   
   "Gloom" 44
   "Vileplume" 45
   "Paras" 46
   "Parasect" 47 
   "Venonat" 48  
   "Venomoth" 49 
   "Diglett" 50  
   "Dugtrio" 51  
   "Meowth" 52   
   "Persian" 53  
   "Psyduck" 54  
   "Golduck" 55  
   "Mankey" 56   
   "Primeape" 57 
   "Growlithe" 58
   "Arcanine" 59 
   "Poliwag" 60  
   "Poliwhirl" 61
   "Poliwrath" 62
   "Abra" 63 
   "Kadabra" 64  
   "Alakazam" 65 
   "Machop" 66   
   "Machoke" 67  
   "Machamp" 68  
   "Bellsprout" 69   
   "Weepinbell" 70   
   "Victreebel" 71   
   "Tentacool" 72
   "Tentacruel" 73   
   "Geodude" 74  
   "Graveler" 75 
   "Golem" 76
   "Ponyta" 77   
   "Rapidash" 78 
   "Slowpoke" 79 
   "Slowbro" 80  
   "Magnemite" 81
   "Magneton" 82 
   "Farfetch" 83 
   "Doduo" 84
   "Dodrio" 85   
   "Seel" 86 
   "Dewgong" 87  
   "Grimer" 88   
   "Muk" 89  
   "Shellder" 90 
   "Cloyster" 91 
   "Gastly" 92   
   "Haunter" 93  
   "Gengar" 94   
   "Onix" 95 
   "Drowzee" 96  
   "Hypno" 97
   "Krabby" 98   
   "Kingler" 99  
   "Voltorb" 100  
   "Electrode" 101
   "Exeggcute" 102
   "Exeggutor" 103
   "Cubone" 104   
   "Marowak" 105  
   "Hitmonlee" 106
   "Hitmonchan" 107   
   "Lickitung" 108
   "Koffing" 109  
   "Weezing" 110  
   "Rhyhorn" 111  
   "Rhydon" 112   
   "Chansey" 113  
   "Tangela" 114  
   "Kangaskhan" 115   
   "Horsea" 116   
   "Seadra" 117   
   "Goldeen" 118  
   "Seaking" 119  
   "Staryu" 120   
   "Starmie" 121  
   "Mr. Mime" 122 
   "Scyther" 123  
   "Jynx" 124 
   "Electabuzz" 125   
   "Magmar" 126   
   "Pinsir" 127   
   "Tauros" 128   
   "Magikarp" 129 
   "Gyarados" 130 
   "Lapras" 131   
   "Ditto" 132
   "Eevee" 133
   "Vaporeon" 134 
   "Jolteon" 135  
   "Flareon" 136  
   "Porygon" 137  
   "Omanyte" 138  
   "Omastar" 139  
   "Kabuto" 140   
   "Kabutops" 141 
   "Aerodactyl" 142   
   "Snorlax" 143  
   "Articuno" 144 
   "Zapdos" 145   
   "Moltres" 146  
   "Dratini" 147  
   "Dragonair" 148
   "Dragonite" 149
   "Mewtwo" 150   
   "Mew" 151})

(defn get-id-from-poke-index
  [name]
  (poke-index name))

(def move-set
  {1 "Pound"
   2 "Karate Chop*"
   3 "Double Slap"
   4 "Comet Punch"
   5 "Mega Punch"
   6 "Pay Day"
   7 "Fire Punch"
   8 "Ice Punch"
   9 "Thunder Punch"
   10 "Scratch"
   11 "Vice Grip"
   12 "Guillotine"
   13 "Razor Wind"
   14 "Swords Dance"
   15 "Cut"
   16 "Gust*"
   17 "Wing Attack"
   18 "Whirlwind"
   19 "Fly"
   20 "Bind"
   21 "Slam"
   22 "Vine Whip"
   23 "Stomp"
   24 "Double Kick"
   25 "Mega Kick"
   26 "Jump Kick"
   27 "Rolling Kick"
   28 "Sand Attack*"
   29 "Headbutt"
   30 "Horn Attack"
   31 "Fury Attack"
   32 "Horn Drill"
   33 "Tackle"
   34 "Body Slam"
   35 "Wrap"
   36 "Take Down"
   37 "Thrash"
   38 "Double-Edge"
   39 "Tail Whip"
   40 "Poison Sting"
   41 "Twineedle"
   42 "Pin Missile"
   43 "Leer"
   44 "Bite*"
   45 "Growl"
   46 "Roar"
   47 "Sing"
   48 "Supersonic"
   49 "Sonic Boom"
   50 "Disable"
   51 "Acid"
   52 "Ember"
   53 "Flamethrower"
   54 "Mist"
   55 "Water Gun"
   56 "Hydro Pump"
   57 "Surf"
   58 "Ice Beam"
   59 "Blizzard"
   60 "Psybeam"
   61 "Bubble Beam"
   62 "Aurora Beam"
   63 "Hyper Beam"
   64 "Peck"
   65 "Drill Peck"
   66 "Submission"
   67 "Low Kick"
   68 "Counter"
   69 "Seismic Toss"
   70 "Strength"
   71 "Absorb"
   72 "Mega Drain"
   73 "Leech Seed"
   74 "Growth"
   75 "Razor Leaf"
   76 "Solar Beam"
   77 "Poison Powder"
   78 "Stun Spore"
   79 "Sleep Powder"
   80 "Petal Dance"
   81 "String Shot"
   82 "Dragon Rage"
   83 "Fire Spin"
   84 "Thunder Shock"
   85 "Thunderbolt"
   86 "Thunder Wave"
   87 "Thunder"
   88 "Rock Throw"
   89 "Earthquake"
   90 "Fissure"
   91 "Dig"
   92 "Toxic"
   93 "Confusion"
   94 "Psychic"
   95 "Hypnosis"
   96 "Meditate"
   97 "Agility"
   98 "Quick Attack"
   99 "Rage"
   100 "Teleport"
   101 "Night Shade"
   102 "Mimic"
   103 "Screech"
   104 "Double Team"
   105 "Recover"
   106 "Harden"
   107 "Minimize"
   108 "Smokescreen"
   109 "Confuse Ray"
   110 "Withdraw"
   111 "Defense Curl"
   112 "Barrier"
   113 "Light Screen"
   114 "Haze"
   115 "Reflect"
   116 "Focus Energy"
   117 "Bide"
   118 "Metronome"
   119 "Mirror Move"
   120 "Self-Destruct"
   121 "Egg Bomb"
   122 "Lick"
   123 "Smog"
   124 "Sludge"
   125 "Bone Club"
   126 "Fire Blast"
   127 "Waterfall"
   128 "Clamp"
   129 "Swift"
   130 "Skull Bash"
   131 "Spike Cannon"
   132 "Constrict"
   133 "Amnesia"
   134 "Kinesis"
   135 "Soft-Boiled"
   136 "High Jump Kick"
   137 "Glare"
   138 "Dream Eater"
   139 "Poison Gas"
   140 "Barrage"
   141 "Leech Life"
   142 "Lovely Kiss"
   143 "Sky Attack"
   144 "Transform"
   145 "Bubble"
   146 "Dizzy Punch"
   147 "Spore"
   148 "Flash"
   149 "Psywave"
   150 "Splash"
   151 "Acid Armor"
   152 "Crabhammer"
   153 "Explosion"
   154 "Fury Swipes"
   155 "Bonemerang"
   156 "Rest"
   157 "Rock Slide"
   158 "Hyper Fang"
   159 "Sharpen"
   160 "Conversion"
   161 "Tri Attack"
   162 "Super Fang"
   163 "Slash"
   164 "Substitute"
   165 "Struggle"})

(defn get-from-move-set
  [num]
  (let [num (if (neg? num) (+ 256 num) num)]
    (list num (move-set num))))

(def type-map
  {0 "Normal"
   1 "Fighting"
   2 "Flying"
   3 "Poison"
   4 "Ground"
   5 "Rock"
   7 "Bug"
   8 "Ghost"
   20 "Fire"
   21 "Water"
   22 "Grass"
   23 "Electric"
   24 "Psychic"
   25 "Ice"
   26 "Dragon"})

(defn get-type-from-num
  [num]
  (list num (type-map num)))
