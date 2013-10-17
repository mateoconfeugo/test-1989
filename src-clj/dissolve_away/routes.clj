(ns dissolve-away.routes
  (:require [compojure.core :as c-core :refer [defroutes]]
            [compojure.route :as c-route :refer [resources not-found]]
            [shoreleave.middleware.rpc :refer [remote-ns]]
            [dissolve-away.controllers.site :refer [site]]
            [dissolve-away.controllers.api]))

;; Remote APIs exposed
(remote-ns 'dissolve-away.controllers.api :as "api")

;; Core system routes
(defroutes app-routes
  (c-route/resources "/")
  (c-route/resources "/design/" {:root "templates"})  
  (c-route/not-found "404 Page not found."))

(def all-routes (c-core/routes site app-routes))

