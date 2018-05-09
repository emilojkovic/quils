(ns sketch.soft_edges
  (:require [quil.core :refer :all])
  (:require [utils.colors :refer :all])
  (:require [utils.distributions :refer :all])
  (:use [incanter.core :only [$=]])
  (:use [clojure.math.combinatorics :only [combinations cartesian-product]])
  (:use [clojure.pprint])
  (:use [clojure.set :only [union]])
  (:use [clojure.contrib.map-utils :only [deep-merge-with]])
  (:import [org.apache.commons.math3.distribution ParetoDistribution])
  (:import [processing.core PShape PGraphics]))

;; setup function
(defn setup []
  ; make the color mode HSB with hue in [0, 360], saturation in [0, 100],
  ; brightness in [0, 100], and alpha in [0.0, 1.0]
  (color-mode :hsb 360 100 100 1.0)

  ; make the background white
  (background 0 0 100)

  ; define top-left corner of rectangle, width, and height
  (def left-x 20)
  (def top-y 20)
  (def rect-height 260)
  (def minimum-width 360)

  ; don't create outlines on polygons
  (no-stroke)
)

;; main function
(defn draw []
  ; static images should only call once
  (no-loop) 

  ; fill the rectangles with gray with 0.1 alpha
  ; use 2% opacity
  (fill 0 0 50 0.1)
  
  ; draw rectangle layers
  ; (doseq [i (range 50)]
  ;   (let [actual-width (+ minimum-width (abs (gauss 0 100)))]
  ;     (rect left-x top-y actual-width rect-height)))

  ; random polygons (quadrilaterals)
  ; (doseq [i (range 20)]
  ;   (let [top-width (+ minimum-width (abs (gauss 0 100)))
  ;         bottom-width (+ minimum-width (abs (gauss 0 100)))]
  
  ;     ; in Processing, the y-axis is inverted with 0 at the top
  ;     (begin-shape)
  ;     (vertex left-x                  top-y)            ; top left
  ;     (vertex (+ left-x top-width)    top-y)            ; top right
  ;     (vertex (+ left-x bottom-width) (+ top-y rect-height)) ; bottom right
  ;     (vertex left-x                  (+ top-y rect-height)) ; bottom left
  ;     (end-shape :close)))

  ; bezier curve
  (doseq [i (range 20)]
    (let [top-width (+ minimum-width (abs (gauss 0 20)))
          control-1-width (+ minimum-width 40 (abs (gauss 0 100)))
          control-2-width (+ minimum-width 40 (abs (gauss 0 100)))
          bottom-width (+ minimum-width (abs (gauss 0 20)))]
  
      (begin-shape)
      (vertex left-x top-y) ; top left
  
      ; make the curve
      ; top right
      (vertex (+ left-x top-width) top-y)
  
      (bezier-vertex
        ; control 1
        (+ left-x control-1-width) (+ top-y (* 0.333 rect-height))
        ; control 2
        (+ left-x control-2-width) (+ top-y (* 0.666 rect-height))
        ; bottom right
        (+ left-x bottom-width)    (+ top-y rect-height))
  
      ; bottom left
      (vertex left-x (+ top-y rect-height))
      (end-shape :close)))

  (save "sketch.tif")
)