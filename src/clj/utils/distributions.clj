(ns utils.distributions
  (:require [quil.core :refer :all])
  (:use [incanter.core :only [$=]]))

; uniform distribution -- use regular random

; gaussian distribution
(defn gauss [mean variance]
  (+ mean (* variance (random-gaussian))))

; pareto distribution -- imported