(ns yes-she-codes.core
  (:require [yes-she-codes.logic :as y.logic]))

(def registros-de-clientes
  [["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
   ["Viúva Negra" "333.444.555-66" "viuva.c(asca.grossa@vingadoras.com.br"]
   ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
   ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]])

(def registros-de-cartoes
  [["1234123412341234" "111" "2023-01" "1.000" "000.111.222-33"]
   ["4321432143214321" "222" "2024-02" "2.000" "333.444.555-66"]
   ["1598159815981598" "333" "2021-03" "3.000" "666.777.888-99"]
   ["6655665566556655" "444" "2025-04" "4.000" "666.777.888-99"]
   ["3939393939393939" "555" "2026-05" "5.000" "999.123.456-78"]])

(def registros-de-compras
  [["2022-01-01" "129.90" "Outback" "Alimentação" "1234123412341234"]
   ["2022-01-02" "260.00" "Dentista" "Saúde" "1234123412341234"]
   ["2022-02-01" "20.00" "Outback" "Lazer" "1234123412341234"]
   ["2022-01-10" "150.00" "Show" "Lazer" "4321432143214321"]
   ["2022-02-10" "289.99" "Posto de gasolina" "Automóvel" "4321432143214321"]])

(def clientes (y.logic/lista-clientes registros-de-clientes))
(def cartoes (y.logic/lista-cartoes registros-de-cartoes))
(def compras (y.logic/lista-compras registros-de-compras))

(println clientes)
(println cartoes)
(println compras)

(println (y.logic/total-gasto compras))

(println (y.logic/compras-do-mes 02 compras))

(println (y.logic/total-gasto-no-mes compras 02 1234123412341234))

(println (y.logic/lista-de-compras-por-estabelecimento "Outback" compras))