(ns pokereader.read-save
  (:use pokereader.index))

(def test-file "./Pokemon - Blue Version (USA, Europe).sav")

(def sav-data-file
  (byte-array (* 1024 38)))

(defn read-file
  ([] (read-file test-file sav-data-file))
  ([filename bytes]
     (.read (clojure.java.io/input-stream filename) bytes)))

(defn- to-hex
  [x]
  (format "%02X" x))

(defn- get-multibyte-num
  [bytes offset count]
  (let [bs (for [x (range count)] (nth bytes (+ offset x)))]
    (reduce '+ (for [i (range count)]
        (let [b (negate-twos-compliment (nth bs i))]
          (bit-shift-left b (* 9 (- count i 1))))))))

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
    (let [poke-name (get-from-hex-poke-index (keyword (to-hex (nth bytes (+ offset hex-number)))))
          type-1 (get-type-from-num (nth bytes (+ offset type-1)))
          type-2 (get-type-from-num (nth bytes (+ offset type-2)))]
      {:index (get-id-from-poke-index poke-name)
       :pokemon poke-name
       :level (nth bytes (+ offset level))
       :type-1 type-1
       :type-2 (if (= type-1 type-2) nil type-2)
       :max-hp (get-multibyte-num bytes (+ offset max-hp) 2)
       :moves {:move-1 (get-from-move-set (nth bytes (+ offset move-1)))
               :move-2 (get-from-move-set (nth bytes (+ offset move-2)))
               :move-3 (get-from-move-set (nth bytes (+ offset move-3)))
               :move-4 (get-from-move-set (nth bytes (+ offset move-4)))
               :move-1-pp (nth bytes (+ offset move-1-pp))
               :move-2-pp (nth bytes (+ offset move-2-pp))
               :move-3-pp (nth bytes (+ offset move-3-pp))
               :move-4-pp (nth bytes (+ offset move-4-pp))
               }
       :stats {:attack (get-multibyte-num bytes (+ offset atk) 2)
               :defense (get-multibyte-num bytes (+ offset def) 2)
               :speed (get-multibyte-num bytes (+ offset spd) 2)
               :special (get-multibyte-num bytes (+ offset spc) 2)
               }
       ;; ev's and iv
       :evs {:hp (get-multibyte-num bytes (+ offset hp-ev) 2)
             :attack (get-multibyte-num bytes (+ offset atk-ev) 2)
             :defense (get-multibyte-num bytes (+ offset def-ev) 2)
             :speed (get-multibyte-num bytes (+ offset spd-ev) 2)
             :special (get-multibyte-num bytes (+ offset spc-ev) 2)}})))

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
