(ns scribblebook.scribbles.scribble-straight
  (:require [scribblebook.turtle :refer [turtle drop-turtle forward rotate-right rotate-left rotate-rand]]))

(defn draw
  [ctx]
  (let [turt (turtle ctx)]
    (drop-turtle turt)
    (loop [i 10000
           t turt]
      (when (<= 0 i)
        (recur (dec i)
               ((rand-nth [forward rotate-right rotate-left]) t))))
    (.stroke ctx)))
