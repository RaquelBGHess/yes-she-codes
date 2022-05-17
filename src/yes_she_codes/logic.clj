(ns yes-she-codes.logic)
(use 'java-time)

(defn novo-cliente
  [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn str->Long [valor]
  (clojure.string/replace valor #" " ""))

(defn str->data [valor]
  (java-time/local-date "yyyy-MM-dd" valor))

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero   (str->Long numero)
   :cvv      (str->Long cvv)
   :validade validade
   :limite   (bigdec limite)
   :cliente  cliente})

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data            (str->data data)
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->Long cartao)})

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (mapv funcao-mapeamento)))

(defn lista-clientes []
  (processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                       (novo-cliente nome cpf email))))

(defn lista-cartoes []
  (processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                      (novo-cartao numero cvv validade limite cliente))))

(defn lista-compras []
  (processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                      (nova-compra data valor estabelecimento categoria cartao))))

(defn filtra-compras [predicado compras]
  (vec (filter predicado compras)))

(defn mes-da-data [data]
  (.getValue (java-time/month data)))

(defn filtra-compras-do-mes
  [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %)))
                  compras))

(defn filtra-compras-do-estabelecimento
  [estabelecimento compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %))
                  compras))

(defn total-gasto
  [compras]
  (reduce + (map :valor compras)))

(defn total-gasto-no-mes
  [mes compras]
  (total-gasto (filtra-compras-do-mes mes compras)))

(def total-gasto-no-mes-com-composition (comp total-gasto filtra-compras-do-mes))

(defn compras-no-intevalo-de-precos
  [valor-minimo valor-maximo compras]
  (filter #(and
             (>= (:valor %) valor-minimo)
             (<= (:valor %) valor-maximo))
          compras))

(defn total-de-gastos-por-categoria
  [compras]
    (map
      (fn [[categoria compras-da-categoria]]
        {:categoria                categoria
         :gasto-total-da-categoria (total-gasto compras-da-categoria)})
      (group-by :categoria compras)))