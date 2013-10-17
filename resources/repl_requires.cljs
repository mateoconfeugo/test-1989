(ns cljs.user (:use [jayq.core  :only [$ text val on prevent remove-class add-class remove]]
                    [cljs.repl.browser]
                    [cemerick.piggieback/cljs-repl]))

[enfocus.core :only [from read-form at html-content]]

(.log js/console "hello console")