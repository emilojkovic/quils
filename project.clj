(defproject quils "0.1"
  :description "Image"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.apache.commons/commons-math3 "3.3"]
                 [quil "2.2.4" :exclusions [org.clojure/clojure]]
                 [incanter "1.5.5"]]
  :jvm-opts ["-Xms4100m" "-Xmx4100M" "-server"]
  ; for closure code
  :source-paths ["src/clj"]
  ; in case you write java code
  :java-source-paths ["src/java"]
  ; enables ahead-of-time compilation
  :aot [sketch.dynamic])