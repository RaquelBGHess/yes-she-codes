(ns yes-she-codes.util
  (:require [java-time :as t]
            [schema.core :as s]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defprotocol ExtratorDeMes
  (mes-da-data [data]))

(extend-type java.time.LocalDate ExtratorDeMes
  (mes-da-data [data]
    (.getValue (t/month data))))

(extend-type java.lang.String ExtratorDeMes
  (mes-da-data [data]
    (->> data
         t/local-date
         mes-da-data)))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn entre-valores [min max valor]
  (and (>= valor min) (<= valor max)))

(defn min-caracteres [n]
  (s/constrained s/Str #(>= (count %) n)))

(defn opcional [schema]
  (s/maybe schema))

(def InteiroPositivo (s/pred pos-int?))
(def IdOpcional (opcional InteiroPositivo))
(def ValorPositivo (s/constrained BigDecimal (comp not neg?)))