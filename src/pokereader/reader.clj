(ns pokereader.reader
  (:use pokereader.read-save))

(defn get-save-data
  [file]
  (let [save-file (byte-array (* 1024 38))]
    (read-file file save-file)
    {:name (get-trainer-name save-file)
     :team (get-pokemon-team save-file)}))
