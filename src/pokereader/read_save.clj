(ns pokereader.read-save
  (:use pokereader.index))

;; (for [x (concat (range 8 10) (map char (range 65 71)))
;;       y (concat (range 10) (map char (range 65 71)))]
;;   (keyword (str x y)))

;; 0x2598 	8 	Player name
;; 0x25A3 	19 	Pokédex owned
;; 0x25B6 	19 	Pokédex seen
;; 0x25C9 	42 	Pocket item list
;; 0x25F3 	3 	Money
;; 0x25F6 	8 	Rival name
;; 0x2601 	1 	Options
;; 0x2602 	1 	Badges
;; 0x2605 	2 	Player Trainer ID
;; 0x271C 	1 	Pikachu FriendshipY
;; 0x27E6 	102 	PC item list
;; 0x284C 	1 	Current PC Box
;; 0x2850 	2 	Casino coins
;; 0x2CEE 	4 	Time played
;; 0x2F2C 	404 	Team Pokémon list
;; 0x30C0 	1122 	Current Box Pokémon list
;; 0x3523 	1 	Checksum
;; 0x4000 	1122 	PC Box 1 Pokémon list
;; 0x4462 	1122 	PC Box 2 Pokémon list
;; 0x48C4 	1122 	PC Box 3 Pokémon list
;; 0x4D26 	1122 	PC Box 4 Pokémon list
;; 0x5188 	1122 	PC Box 5 Pokémon list
;; 0x55EA 	1122 	PC Box 6 Pokémon list
;; 0x6000 	1122 	PC Box 7 Pokémon list
;; 0x6462 	1122 	PC Box 8 Pokémon list
;; 0x68C4 	1122 	PC Box 9 Pokémon list
;; 0x6D26 	1122 	PC Box 10 Pokémon list
;; 0x7188 	1122 	PC Box 11 Pokémon list
;; 0x75EA 	1122 	PC Box 12 Pokémon list


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

(def test-file "./Pokemon - Blue Version (USA, Europe).sav")

(def sav-data-file
  (byte-array (* 1024 38)))

(defn read-file
  ([] (read-file test-file))
  ([filename]
     (.read (clojure.java.io/input-stream filename) sav-data-file))
  ([filename bytes]
     (.read (clojure.java.io/input-stream filename) bytes)))

(defn- to-hex
  [x]
  (format "%02X" x))

(defn- get-name
  [bytes offset]
  (let [name (for [x (range offset (+ offset 8))]
               (keyword (to-hex (nth bytes x))))]
    (first (clojure.string/split (apply str (map #(hex-to-ascii-map %) name)) #"\n"))))

(defn get-trainer-name
  ([] (get-trainer-name sav-data-file))
  ([bytes]
     (get-name bytes 0x2598)))

(defn get-rival-name
  ([] (get-rival-name sav-data-file))
  ([bytes]
     (get-name bytes 0x25F6)))


;; Offset 	Contents 	Size
;; 0x00 	Index number of the Species 	1 byte
;; 0x01 	Current HP 	2 bytes
;; 0x03 	Level 	1 byte
;; 0x04 	Status condition 	1 byte
;; 0x05 	Type 1 	1 byte
;; 0x06 	Type 2 	1 byte
;; 0x07 	Catch rate/Held item 	1 byte
;; 0x08 	Index number of move 1 	1 byte
;; 0x09 	Index number of move 2 	1 byte
;; 0x0A 	Index number of move 3 	1 byte
;; 0x0B 	Index number of move 4 	1 byte
;; 0x0C 	Original Trainer ID number 	2 bytes
;; 0x0E 	Experience points 	3 bytes
;; 0x11 	HP EV data 	2 bytes
;; 0x13 	Attack EV data 	2 bytes
;; 0x15 	Defense EV data 	2 bytes
;; 0x17 	Speed EV data 	2 bytes
;; 0x19 	Special EV data 	2 bytes
;; 0x1B 	IV data 	2 bytes
;; 0x1D 	Move 1's PP values 	1 byte
;; 0x1E 	Move 2's PP values 	1 byte
;; 0x1F 	Move 3's PP values 	1 byte
;; 0x20 	Move 4's PP values 	1 byte
;; 0x21 	Level 	1 byte
;; 0x22 	Maximum HP 	2 bytes
;; 0x24 	Attack 	2 bytes
;; 0x26 	Defense 	2 bytes
;; 0x28 	Speed 	2 bytes
;; 0x2A 	Special 	2 bytes 

(defn- get-individual-poke-data
  [bytes offset]
  (let [;; offsets
        hex-number 0 ;1
        level 3 ;1
        type-1 5 ;1
        type-2 6 ;1
        move-1 8 ;1
        move-2 9 ;1
        move-3 10 ;1
        move-4 11 ;1
        ot 12 ;2
        exp 14 ;3
        hp-ev 17 ;2
        atk-ev 19 ;2
        def-ev 21 ;2
        spd-ev 23 ;2
        spc-ev 25 ;2
        iv 27 ;2
        move-1-pp 29 ;1
        move-2-pp 30 ;1
        move-3-pp 31 ;1
        move-4-pp 32 ;1
        level 33 ;1
        max-hp 34 ;2
        atk 36 ;2
        def 38 ;2
        spd 40 ;2
        spc 42 ;2
        ]
    (let [poke-name (get-from-hex-poke-index (keyword (to-hex (nth bytes (+ offset hex-number)))))]
      {
       :index (get-id-from-poke-index poke-name)
       :pokemon poke-name
       :level (nth bytes (+ offset level))
                                        ;:type-1 (nth bytes (+ offset type-1))
                                        ;:type-2 (nth bytes (+ offset type-2))
       :moves {:move-1 (get-from-move-set (nth bytes (+ offset move-1)))
               :move-2 (get-from-move-set (nth bytes (+ offset move-2)))
               :move-3 (get-from-move-set (nth bytes (+ offset move-3)))
               :move-4 (get-from-move-set (nth bytes (+ offset move-4)))
               ;; :move-1-pp (nth bytes (+ offset move-1-pp))
               ;; :move-2-pp (nth bytes (+ offset move-2-pp))
               ;; :move-3-pp (nth bytes (+ offset move-3-pp))
               ;; :move-4-pp (nth bytes (+ offset move-4-pp))
               }
       ;; ev's and iv
       })))

(defn get-pokemon-team
  ([] (get-pokemon-team sav-data-file))
  ([bytes]
     (let [offset 0x2F2C
           count (nth bytes offset)]
       (for [x (range (inc offset) (+ offset count 1))]
         (hex-poke-index (keyword (to-hex (nth bytes x))))))
     (let [offset 0x2F2C
           teamsize (nth bytes offset)
           species-list (range (inc offset) (+ offset 7))
           pokemon-list (range (+ offset 8) (+ offset 8 (* 44 teamsize)) 44)]
       (for [pokemon pokemon-list]
         (get-individual-poke-data bytes pokemon)))))
