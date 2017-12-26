(ns scribblebook.koch-island
  (:require [scribblebook.turtle :refer [turtle drop-turtle turtle-command]]))

(def koch-rules
  {"F" "F-F+F+FF-F-F+F"})

(defn koch-replace [s]
  (reduce (fn [s [k v]]
            (clojure.string/replace s k v)) s koch-rules))

(def num-iterations 3)

(defn draw
  [ctx]
  (let [turt (assoc (turtle ctx) :step-size (/ 20 num-iterations))]
    (drop-turtle turt)
    (reduce (fn [t chr]
              ((turtle-command chr) t))
            turt (nth (iterate koch-replace "F-F-F-F") num-iterations))
    (.stroke ctx)))

