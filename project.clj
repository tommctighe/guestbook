(defproject guestbook "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies  [;[com.bhauman/figwheel-main "0.2.3"]
                  ;[com.bhauman/rebel-readline-cljs "0.1.4"]
                  [org.clojure/clojurescript "1.9.521"]
                  [cheshire "5.8.1"]
                  [clojure.java-time "0.3.2"]
                  [com.h2database/h2 "1.4.197"]
                  [compojure "1.6.1"]
                  [conman "0.8.3"]
                  [cprop "0.1.13"]
                  [funcool/struct "1.3.0"]
                  [luminus-immutant "0.2.5"]
                  [luminus-migrations "0.6.3"]
                  [luminus-transit "0.1.1"]
                  [luminus/ring-ttl-session "0.3.2"]
                  [markdown-clj "1.0.7"]
                  [metosin/muuntaja "0.6.3"]
                  [metosin/ring-http-response "0.9.1"]
                  [mount "0.1.16"]
                  [nrepl "0.5.3"]
                  [org.clojure/clojure "1.10.0"]
                  [org.clojure/data.csv "0.1.4"]
                  [org.clojure/tools.cli "0.4.1"]
                  [org.clojure/tools.logging "0.4.1"]
                  [org.webjars.bower/tether "1.4.4"]
                  [org.webjars/bootstrap "4.2.1"]
                  [org.webjars/font-awesome "5.6.3"]
                  [org.webjars/jquery "3.3.1-1"]
                  [org.webjars/webjars-locator "0.35"]
                  [ring-webjars "0.2.0"]
                  [ring/ring-core "1.7.1"]
                  [ring/ring-defaults "0.3.2"]
                  [selmer "1.12.6"]]

  :min-lein-version "2.0.0"

  :plugins [[lein-immutant "2.1.0"] [lein-cljsbuild "1.1.7"]]

  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources" "target/cljsbuild"]
  ;:aliases {"fig" ["trampoline" "run" "-m" "figwheel.main"]
            ;"build-dev" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]} 
  
  :cljsbuild
  {:builds
   {:app
    {:source-paths ["src/cljs"]
     :compiler
     {:main          (str project-ns ".app")
      :asset-path    "/js/out"
      :output-to     "target/cljsbuild/public/js/app.js"
      :output-dir    "target/cljsbuild/public/js/out"
      :optimizations :none
      :source-map    true
      :pretty-print  true}}
    :min
    {:source-paths ["src/cljs"]
     :compiler
     {:output-to     "target/cljsbuild/public/js/app.js"
      :output-dir    "target/uberjar"
      :externs       ["react/externs/react.js"]
      :optimizations :advanced
      :pretty-print  false}}}}
  
  :target-path "target/%s/"
  :main ^:skip-aot guestbook.core

  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "guestbook.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}
   :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn"]
                  :dependencies [[expound "0.7.2"]
                                 [pjstadig/humane-test-output "0.9.0"]
                                 [prone "1.6.1"]
                                 [ring/ring-devel "1.7.1"]
                                 [ring/ring-mock "0.3.2"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.23.0"]]

                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn"]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
