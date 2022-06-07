(ns yes-she-codes.util
  (:require [java-time :as time]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defprotocol ExtratorDeMes
  (mes-da-data [data]))

(extend-type java.time.LocalDate ExtratorDeMes
  (mes-da-data [data]
    (.getValue (time/month data))))

(extend-type java.lang.String ExtratorDeMes
  (mes-da-data [data]
    (->> data
         time/local-date
         mes-da-data)))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))