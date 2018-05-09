(ns utils.colors
  (:require [quil.core :refer :all])
  (:use [incanter.core :only [$=]]))

; Example for gradients using rescale
; (doseq [y (range 0 1000 5)]  ; loop y from 0 to 1000 in increments of 5
;   ;(let [hue (rescale y 0 1000 130 220)] ; green at the top, blue at the bottom
;   (let [hue (rescale (random y (+ y 100)) 0 1000 130 220)] ; introduce some randomness
;     (fill hue 90 90)
;     (rect 0 y 1000 5)))  ; draw a rectangle that spans the width of the image 

(defn rescale [value old-min old-max new-min new-max]
  "Rescales value from range [old-min, old-max] to [new-min, new-max]"
  (let [old-spread (- old-max old-min)
        new-spread (- new-max new-min)]
    (+ (* (- value old-min) (/ new-spread old-spread))
       new-min)))

; Example for picking between 3 difference colors       
; (apply fill (weighted-choice [  0  0 100] 0.70    ; white, 70% chance
;                              [220 50  50] 0.20    ; blue, 20% chance
;                              [  0 80  80] 0.10))  ; red, 10% chance

; Example for picking between 3 colors with random variation on one of them
; (apply fill (weighted-choice [               0  0 100] 0.70    ; white, 70% chance
;                              [(random 200 220) 50  50] 0.20    ; blue, 20% chance
;                              [               0 80  80] 0.10))  ; red, 10% chance

; Example for chosing different probability distributions
; (apply fill
;   (if (< radius 50)

;     ; pick from the warm color palette
;     (weighted-choice [ 0  0 100] 0.70     ; white, 70% chance
;                      [54 90  95] 0.20     ; yellow, 20% chance
;                      [ 0 80  80] 0.10)    ; red, 10% chance

;     ; pick from the cool color palette
;     (weighted-choice [  0  0 100] 0.70    ; white, 70% chance
;                      [220 50  50] 0.20    ; blue, 20% chance
;                      [120 50  80] 0.10))) ; green, 10% chance

; Example for gradient probabilities for colors (ie. blue on top, red on bottom)
; (let [blue-odds (rescale y 0 1000 0.30 0.0)
;       red-odds (- 0.30 blue-odds)]
;   (apply fill (weighted-choice [  0  0 100] 0.70        ; white, 70% chance
;                                [220 50  50] blue-odds   ; blue
;                                [  0 80  80] red-odds))) ; red

(defn weighted-choice [& items-and-weights]
  "Given a sequence of alternating item, weight arguments, chooses one of the
    items with a probability equal to the weight.  Each weight should be
    between 0.0 and 1.0, and all weights should sum to 1.0."
  (assert (zero? (mod (count items-and-weights) 2)))
  (assert (>= (count items-and-weights) 2))
  (let [r (random 0 1.0)]
    (loop [weight-seen 0
            remaining-items items-and-weights]
      (if (<= (count remaining-items) 2)
        (first remaining-items)
        (let [new-weight (second remaining-items)
              end-bound (+ weight-seen new-weight)]
          (if (<= r weight-seen end-bound)
            (first remaining-items)
            (recur (+ weight-seen (second remaining-items)) (drop 2 remaining-items))))))))