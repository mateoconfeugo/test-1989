(ns dissolve-away.controllers.api
  "API controller for dissolve-away client"  
  (:require [lead-generation.models.lead :refer [log-lead select-database]]
            [dissolve-away.config :refer [config]]))


(defn regional-phone-number [region-id]
  "Determine which regional distributor's phone number to use"
  {:phone "5555555555"})

(defn mouse-position
  [x y]
  {:x x :y y :z 0})

(defn insert-lead
  [lead_full_name lead_email lead_phone]
  (let [data {:full-name lead_full_name :email-address lead_email :phone-number lead_phone}
        db (select-database (config :lead-db-name) (config :lead-db-user) (config :lead-db-password) (config :lead-db-address))]
    (log-lead db data)))
