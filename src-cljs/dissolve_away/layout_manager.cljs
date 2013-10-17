(ns dissolve-away.layout-manager
  (:require-macros [shoreleave.remotes.macros :as srm :refer [rpc]]
                   [cljs.core.match.macros :refer [match]]
                   [cljs.core.async.macros  :refer [go]]
                   [enfocus.macros :as em])
  (:require [flourish-common.utils.helpers :refer [event-chan by-id click-chan]]
;;            [dissolve-away.display-objects.lead]
            [flourish-common.utils.media-query]
            [flourish-common.display-object :refer [new-display-object]]
            [cljs.core.async :refer [<! >! chan put! alts!]]
            [enfocus.core :as ef :refer [from read-form]]
            [jayq.core  :as jq :refer [$ text val on prevent remove-class add-class remove]]            
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [shoreleave.common :as common]
            [shoreleave.browser.history :as history]))


(defn hookup-display-objects [] (map #(new-display-object %) [:lead-form :regional-phone]))

