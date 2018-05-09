(ns utils.utils
  (:require [quil.core :refer :all])
  (:use [incanter.core :only [$=]]))

; rescale function -- used for gradients
(defn rescale [value old-min old-max new-min new-max]
  "Rescales value from range [old-min, old-max] to [new-min, new-max]"
  (let [old-spread (- old-max old-min)
        new-spread (- new-max new-min)]
    (+ (* (- value old-min) (/ new-spread old-spread))
       new-min)))