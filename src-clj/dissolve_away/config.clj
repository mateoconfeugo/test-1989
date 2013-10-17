(ns dissolve-away.config
  "A *simple* config system."
  (:require [shoreleave.server-helpers :refer [safe-read]]))

(defn read-config
  "Read a config file and return it as Clojure Data.  Usually, this is a hashmap"
  ([]
     (read-config (str (System/getProperty "user.dir") "/resources/config.edn")))
  ([config-loc]
     (safe-read (slurp config-loc))))

(def config (read-config))

