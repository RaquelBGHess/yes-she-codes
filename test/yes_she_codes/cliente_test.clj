(ns yes-she-codes.cliente-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.dominio.cliente :refer :all]))

(deftest testando-cliente-schema
  (testing "testa se o esquema de clientes aceita todos os campos válidos"
    (let [cliente-valido {:id    1
                          :nome  "ana"
                          :cpf   000.111.222.33
                          :email "ana@gmail.com"}]
      (is (= (s/validade ClienteSchema cliente-valido)
             cliente-valido))))

  (testing "testa se o esquema de clientes aceita todos os campos válidos"
    (let [cliente-valido {:id    1
                          :nome  "ana"
                          :cpf   000.111.222.33
                          :email "ana@gmail.com"}]
      (is (thrown? clojure.lang.ExceptionInfo
             (s/validate ClienteSchema cliente-com-cpf-valido)))))
  )