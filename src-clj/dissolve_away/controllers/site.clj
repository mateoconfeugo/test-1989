(ns dissolve-away.controllers.site
  "Web specific controllers routes, ROA oriented"
  (:require [compojure.core :refer [defroutes GET]]
            [dissolve-away.views.host-dom :as host-dom :refer [render index test-shoreleave carousel-landing-site landing-site thank-you current-release]]))

(defroutes site
  (GET "/index" [] (host-dom/landing-site))  
  (GET "/dissolve-away/release" [] (host-dom/current-release))
  (GET "/dissolve-away/carousel" [] (host-dom/render carousel-landing-site))
  (GET "/thank-you" [name] (host-dom/thank-you name))
  (GET "/" {session :session} (host-dom/index session))
  (GET "/test" [] (host-dom/test-shoreleave)))


