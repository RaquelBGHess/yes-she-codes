(ns yes-she-codes.logic)
(require 'java-time)
(require `clojure.spec.alpha)

(defn novo-cliente
  [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn str->Long [valor]
  (clojure.string/replace valor #" " ""))

(defn str->date [valor]
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
  {:data            (str->date data)
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->Long cartao)})

(defn lista-clientes [registros]
  (vec (map (fn [[nome cpf email]]
              (novo-cliente nome, cpf, email))
            registros)))

(defn lista-cartoes [registros]
  (vec (map (fn [[numero cvv validade limite cliente]]
              (novo-cartao numero, cvv, validade, limite, cliente))
            registros)))

(defn lista-compras [registros]
  (vec (map (fn [[data valor estabelecimento categoria cartao]]
              (nova-compra data, valor, estabelecimento, categoria, cartao))
            registros)))

(defn total-gasto
  [compras]
  (reduce + (map :valor compras)))

(defn mes-da-data [data]
  (.getValue (java-time/month data)))

(defn compras-do-mes
  [mes lista-de-compras]
  (filter #(= (mes-da-data (:data %)) mes) lista-de-compras))

(defn total-gasto-no-mes
  [compras mes cartao]
  (total-gasto (filter #(= cartao (:cartao %)) (compras-do-mes mes compras))))

(defn lista-de-compras-por-estabelecimento
  [estabelecimento lista-de-compras]
  (filter #(= estabelecimento (:estabelecimento %)) lista-de-compras))

(defn compras-no-intevalo-de-precos
  [valor-minimo valor-maximo lista-de-compras]
  (filter #(and
             (>= (:valor %) valor-minimo)
             (<= (:valor %) valor-maximo))
          lista-de-compras))

(defn total-de-gastos-por-categoria
  [lista-de-compras]
    (map
      (fn [[categoria compras-da-categoria]]
        {:categoria                categoria
         :gasto-total-da-categoria (total-gasto compras-da-categoria)})
      (group-by :categoria lista-de-compras)))

