(ns dissolve-away.repl
  (:require [clojure.browser.repl :as repl :refer [connect]]))

(defn ^:export connect-app []
  (repl/connect "http://localhost:9000/repl"))
