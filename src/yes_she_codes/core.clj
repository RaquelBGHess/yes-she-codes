(ns yes-she-codes.core
  (:require [yes-she-codes.logic :as y.logic]))

(def compras (y.logic/lista-compras))

(println "clientes:" (y.logic/lista-clientes))
(println "cartoes:" (y.logic/lista-cartoes))
(println  "compras:" (y.logic/lista-compras))

(println "total-gasto:" (y.logic/total-gasto compras))

(println "filtra-compras-do-mes:" (y.logic/filtra-compras-do-mes 02 compras))

(println "total-gasto-no-mes:" (y.logic/total-gasto-no-mes 1 compras))

(println "total-gasto-no-mes-com-compposition:" (y.logic/total-gasto-no-mes-com-composition 1 compras))

(println "filtra-compras-por-estabelecimento:" (y.logic/filtra-compras-do-estabelecimento "Outback" compras))

(println "filtra-compras-por-estabelecimento:" (y.logic/filtra-compras-do-estabelecimento "Alura" compras))

(println "compras-no-intevalo-de-precos:" (y.logic/compras-no-intevalo-de-precos 20.4 200.0 compras))

(println "total-de-gastos-por-categoria:" (y.logic/total-de-gastos-por-categoria compras))