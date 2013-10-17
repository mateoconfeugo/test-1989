(ns dissolve-away.display-objects.lead
  (:require-macros  [cljs.core.async.macros  :refer [go]]
                    [cljs.core.match.macros :refer [match]])
  (:require [cljs.core.async :refer [<! >! chan put! alts!]]
            [flourish-common.utils.helpers :refer [click-channel]]
            [lead-generation.client :refer [send-lead]]))

(defn valid-submission? [lead] true)

(defn gather-lead [form-selector]
  [(jq/val (jq/$ [(str form-selector " input.lead_name")]))
   (jq/val (jq/$ [(str form-selector " input.lead_email")]))
   (jq/val (jq/$ [(str form-selector " input.lead_phone")]))])

(defn process-lead [selector error-channel]
  (let [[name email phone] (gather-lead selector)
        results (send-lead name email phone)]
    (if (valid-submission? results) results (put! error-channel results))))

(defn dispatch [model error-channel]
  (do
    (.log js/console "dispatching")
    (match [model]
           [:#top-form] (process-lead :#top-form error-channel)
           [:#bottom-form] (process-lead :#bottom-form error-channel)
           [:#modal-form ] (process-lead :#modal-form error-channel))))

(defn wireup
  [lead-channel error-channel]
  (let [dom-elements  ["#top-form" "#bottom-form" "#modal-form"]
        _     (.log js/console "wiring up")
                                        ;        channels [(map #(click-channel % %) dom-elements) lead-channel]]
        channels [(click-channel "#header_lead-form-submit" :#top-form) lead-channel]]        
    (go (while true
          (let [[val ch] (alts! [channels])]
            (do
              (>! lead-channel val)
              (dispatch val error-channel)))))
    channels))

