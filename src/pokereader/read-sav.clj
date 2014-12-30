(ns pokereader.read-sav
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

(def test-file "/home/nick/workspace/clojure/pokereader/Pokemon - Blue Version (USA, Europe).sav")

(def sav-data-file
  (byte-array (* 1024 38)))

(defn- read-file
  ([] (read-file test-file))
  ([filename]
     (.read (clojure.java.io/input-stream filename) sav-data-file)))

(defn- get-name
  [bytes offset]
  (let [name (for [x (range offset (+ offset 8))]
               (keyword (format "%02X" (nth bytes x))))]
    (first (clojure.string/split (apply str (map #(hex-to-ascii-map %) name)) #"\n"))))

(defn get-trainer-name
  ([] (get-trainer-name sav-data-file))
  ([bytes]
     (get-name bytes 0x2598)))

(defn get-rivals-name
  ([] (get-rivals-name sav-data-file))
  ([bytes]
     (get-name bytes 0x25F6)))

(defn get-pokemon-team
  ([] (get-pokemon-team sav-data-file))
  ([bytes]
     (let [offset 0x2F2C
           count (nth bytes offset)
           ]
       )))


;; (defn get-pokedex-owned
;;   ([] (get-pokedex-owned sav-data-file))
;;   ([bytes]
;;      (let [offset 0x25A3
;;            count ]
;;        )))
