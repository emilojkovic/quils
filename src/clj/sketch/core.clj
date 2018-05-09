(ns sketch.core
  (:require [quil.core :as q])
  (:require [sketch.soft_edges :as soft_edges])
  (:gen-class))

(q/defsketch example
             :title "Sketch"
             :setup soft_edges/setup
             :draw soft_edges/draw
             :size [650 300])

(defn refresh []
  (use :reload 'sketch.soft_edges)
  (.loop example))
