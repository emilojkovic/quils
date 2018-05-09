(ns sketch.dynamic
  (:require [quil.core :refer :all])
  (:require [utils.utils :refer :all])
  (:use [incanter.core :only [$=]])
  (:use [clojure.math.combinatorics :only [combinations cartesian-product]])
  (:use [clojure.pprint])
  (:use [clojure.set :only [union]])
  (:use [clojure.contrib.map-utils :only [deep-merge-with]])
  (:import [org.apache.commons.math3.distribution ParetoDistribution])
  (:import [processing.core PShape PGraphics]))

;; main function
(defn draw []
  ; static images should only call once
  (no-loop) 
  ; make the color mode HSB with hue in [0, 360], saturation in [0, 100],
  ; brightness in [0, 100], and alpha in [0.0, 1.0] -- instead of RBG
  (color-mode :hsb 360 100 100 1.0)

  ;(background 220 49 66)
  (doseq [y (range 0 1000 5)]  ; loop y from 0 to 1000 in increments of 5
    ;(let [hue (rescale y 0 1000 130 220)] ; green at the top, blue at the bottom
    (let [hue (rescale (random y (+ y 100)) 0 1000 130 220)] ; introduce some randomness
      (fill hue 90 90)
      (rect 0 y 1000 5)))  ; draw a rectangle that spans the width of the image
  (save "sketch.tif")
)

;; setup function
(defn setup []
)