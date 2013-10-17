(ns dissolve-away.views.host-dom
  (:require [flourish-common.web-page-utils  :refer [render-to-response]]
            [clojure.string :as string :refer [join]]
            [net.cgrand.enlive-html :refer [deftemplate defsnippet content sniptest]]))

(comment
(defsnippet head "templates/head.html" [:head#html-head] [{:keys[] :as model}])
(defsnippet navigation "templates/navigation.html" [:nav#navbar] [{:keys[] :as model}])
(defsnippet banner "templates/carousel_banner.html" [:div#carousel-banner] [{:keys[foo] :as model}])
(defsnippet modal"templates/dropdown_lead_modal.html" [:div#lead-modal] [{:keys[] :as model}])
(defsnippet offer "templates/free_referral_offer.html" [:section#offer-referral] [{:keys[] :as model}])

(defsnippet header "templates/header.html" [:header.container] [{:keys[] :as model}])
(defsnippet form "templates/inline_lead_form.html" [:aside] [{:keys[] :as model}])
(defsnippet scripts "templates/scripts.html" [:script] [{:keys[] :as model}])
)

(deftemplate landing-site "templates/index.html" [])

(defsnippet top-banner "templates/top_banner.html" [:div#top-banner :div.row]
  [{:keys[] :as model}])

(defsnippet thc-logo "templates/thc_logo.html" [:div.logo :img]
  [{:keys[] :as model}])

(defsnippet marketing-message "templates/market_message.html" [:div#market-message] [{:keys[] :as model}])

(defsnippet footer "templates/footer.html" [:div.container.bottom]
  [{:keys[] :as model}])

(defsnippet lead-modal "templates/lead_modal.html" [:div.modal-dialog]
  [{:keys[] :as model}])

(deftemplate carousel-landing-site "templates/carousel.html" [])

(deftemplate current-release "templates/release.html"
  []
  [:div#top-banner] (content (top-banner {}))
  [:div.col-md-2.logo] (content (thc-logo {}))
  [:div.container.marketing] (content (marketing-message {}))
  [:div#leadModal] (content (lead-modal {}))  
  [:footer] (content (footer {}))
  )


(deftemplate dissolve-away-landing-site "templates/dissolve-away.html" [])
;;  [:div#carousel-banner] (banner {}))
;;  [:head#html-head] (head {})  
;;  [:nav#navbar] (navigation {})
;;  [:header.container] (header {})  
;;  [:div.market-message] (message {})
;;  [:footer.bottom] (footer {})
;;  [:scripts] (scripts {})
;;

(deftemplate thank-you "templates/thank_you.html"
  [name]
  [:p#persons-name] (content name))

(defn render [fn] (render-to-response (fn)))

(defn index [session]
  (str "Hello.  Your session is: " session
       "</br><a href=\"/test\">Test Shoreleave</a>"))

(defn test-shoreleave []
  (slurp "resources/public/html/test.html"))


