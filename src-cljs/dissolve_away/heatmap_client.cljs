(ns dissolve-away.heatmap-client
  (:require-macros [shoreleave.remotes.macros :as srm :refer [rpc]])
  (:require [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [shoreleave.common :as common]
            [shoreleave.browser.history :as history]))

(defn ^:export send-coords [x y]
  (srm/rpc
   (api/mouse-position x y)
   [resp]
   (.log js/console (str (:x resp)  (:y resp) (:z resp)))))


