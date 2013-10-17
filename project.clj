(defproject dissolve-away "0.1.0"
  :description "Dissolve Away Landing Site"
  :min-lein-version "2.0.0"  
  :source-paths ["src-clj"]
  :dependencies [[amalloy/ring-gzip-middleware "0.1.2" :exclusions [org.clojure/clojure]]                 
                 [com.cemerick/piggieback "0.1.0"]
                 [compojure "1.1.5"] ; Web routing https://github.com/weavejester/compojure
                 [crypto-random "1.1.0"]                 
                 [domina "1.0.1"] ; DOM manipulation
                 [enlive "1.1.1"] ; direct DOM manipulating publishing
                 [enfocus "2.0.0-beta1"] ; client side enlive
                 [flourish-common "0.1.0"]
                 [heat-mapping "0.1.0"] ; Real Time User Gesture Tracking
                 [jayq "2.4.0"] ; jQuery
                 [korma "0.3.0-RC5"] ; ORM 
                 [lead-generation "0.1.0"]; Lead generation                
                 [lib-noir "0.4.7" :exclusions [[org.clojure/clojure] [compojure] [hiccup] [ring]]]
                 [log4j "1.2.15" :exclusions [javax.mail/mail javax.jms/jms com.sun.jdmk/jmxtools com.sun.jmx/jmxri]]
                 [metis "0.3.0"] ; Validation                
                 [mysql/mysql-connector-java "5.1.6"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.clojure/clojure "1.5.1"] ; Lisp on the JVM 
                 [org.clojure/clojurescript "0.0-1853"] ; Lisp on the Browser Javascript interpreter
                 [org.clojure/core.async "0.1.0-SNAPSHOT"] ; CSP 
                 [org.clojure/core.match "0.2.0-rc5"] ; Dispatcher
                 [org.clojure/tools.nrepl "0.2.3"]                 
                 [org.clojure/google-closure-library-third-party "0.0-2029"]
                 [com.googlecode.libphonenumber/libphonenumber "5.4"]             
                 [ring "1.2.0"] ; HTTP Web app server framework                
                 [ring-anti-forgery "0.2.1"] ; 
                 [ring-server "0.2.8" :exclusions [[org.clojure/clojure] [ring]]]
                 [ring-refresh "0.1.2" :exclusions [[org.clojure/clojure] [compojure]]]
                 [shoreleave/shoreleave-remote "0.3.0"] ; seemless remote procedure called
                 [shoreleave/shoreleave-remote-ring "0.3.0"]
                 [shoreleave "0.3.0"]]
  :ring {:handler dissolve-away.handler/war-handler}
  :main dissolve-away.server
  :plugins [[lein-cljsbuild "0.3.2"]
            [lein-marginalia "0.7.1"]           
            [lein-ring "0.8.5"]
            [lein-localrepo "0.4.1"]            
            [s3-wagon-private "1.1.2"]            
            [lein-expectations "0.0.7"]
            [lein-autoexpect "0.2.5"]]
  :hooks [leiningen.cljsbuild]
  :repositories [["private" {:url "s3p://marketwithgusto.repo/releases/" :username :env :passphrase :env}]
                 ["sonatype-staging"  {:url "https://oss.sonatype.org/content/groups/staging/"}]]  
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :cljsbuild {:repl-listen-port 9000
              :repl-launch-commands
                                        ;$ lein trampoline cljsbuild repl-launch firefox <URL>
              {"firefox" ["/Applications/Firefox.app/Contents/MacOS/firefox-bin" :stdout ".repl-firefox-out" :stderr ".repl-firefox-err"]
                                        ;$ lein trampoline cljsbuild repl-launch firefox-naked
               "firefox-naked" ["firefox" "resources/private/html/naked.html"
                                :stdout ".repl-firefox-naked-out" :stderr ".repl-firefox-naked-err"]
                                        ;$ lein trampoline cljsbuild repl-launch phantom <URL>
               "phantom" ["phantomjs" "phantom/repl.js" :stdout ".repl-phantom-out" :stderr ".repl-phantom-err"]
                                        ;$ lein trampoline cljsbuild repl-launch phantom-naked
               "phantom-naked" ["phantomjs" "phantom/repl.js" "resources/private/html/naked.html"
                                :stdout ".repl-phantom-naked-out"  :stderr ".repl-phantom-naked-err"]}
              :test-commands  ;$ lein cljsbuild test
              {"unit" ["phantomjs" "phantom/unit-test.js" "resources/private/html/unit-test.html"]}
              :builds {
                       :dev
                       {:source-paths ["src-cljs"]
                        :jar true
                        :compiler {:output-to "resources/public/js/main-debug.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       :prod
                       {:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :advanced
                                   :pretty-print false}}
                       :test
                       {:source-paths ["test-cljs"]
                        :compiler {:output-to "resources/private/js/unit-test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}}})
