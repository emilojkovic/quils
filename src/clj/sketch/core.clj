(ns sketch.core
  (:require [quil.core :as q])
  (:require [sketch.dynamic :as dynamic])
  (:gen-class))

(q/defsketch example
             :title "Sketch"
             :setup dynamic/setup
             :draw dynamic/draw
             :size [900 900])

(defn refresh []
  (use :reload 'sketch.dynamic)
  (.loop example))
