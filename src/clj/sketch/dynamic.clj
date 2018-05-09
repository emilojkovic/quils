(ns sketch.dynamic
  (:require [quil.core :refer :all])
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

  (background 220 49 66)
  (rect 100 100 400 400)
  (save "sketch.tif")
)

;; setup function
(defn setup []
)