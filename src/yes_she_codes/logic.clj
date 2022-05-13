(ns yes-she-codes.logic)

(defn novo-cliente
  [nome cpf email]
  [:nome nome
   :cpf cpf
   :email email])

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero   (Long/parseLong numero)
   :cvv      (Long/parseLong cvv)
   :validade validade
   :limite   (bigdec limite)
   :cliente  cliente})

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data            (java-time/local-date "yyyy-MM-dd" data)
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (Long/parseLong cartao)})

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
  (reduce + (map (fn [compra] (:valor compra)) compras)))

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