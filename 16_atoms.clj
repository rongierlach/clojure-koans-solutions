(ns koans.16-atoms
  (:require [koan-engine.core :refer :all]))

(def atomic-clock (atom 0))

(meditations
  "Atoms are like refs"
  ; (= __ @atomic-clock)
  (= 0 @atomic-clock)

  "You can change at the swap meet"
  ; (= __ (do
  ;         (swap! atomic-clock inc)
  ;         @atomic-clock))
  (= 1 (do
          (swap! atomic-clock inc)
          @atomic-clock))

  "Keep taxes out of this: swapping requires no transaction"
  ; (= 5 (do
  ;        __
  ;        @atomic-clock))
  (= 5 (do
        (swap! atomic-clock + 5)
        @atomic-clock))

  "Any number of arguments might happen during a swap"
  ; (= __ (do
  ;         (swap! atomic-clock + 1 2 3 4 5)
  ;         @atomic-clock))
  (= 20 (do
    (swap! atomic-clock + 1 2 3 4 5)
    @atomic-clock))

  "Atomic atoms are atomic"
  ; (= __ (do
  ;         (compare-and-set! atomic-clock 100 :fin)
  ;         @atomic-clock))
  (= 20 (do
          (compare-and-set! atomic-clock 100 :fin)
          @atomic-clock))

  "When your expectations are aligned with reality, things proceed that way"
  ; (= :fin (do
  ;           (compare-and-set! __ __ __)
  ;           @atomic-clock)))
  (= :fin (do
            (compare-and-set! atomic-clock 20 :fin)
            @atomic-clock)))
