(ns scribblebook.turtle
  (:require [scribblebook.window :as window]))

(defonce pi (.-PI js/Math))

(defn turtle
  ([ctx x y angle step-size]
   {:ctx ctx
    :x x
    :y y
    :angle angle
    :step-size step-size})
  ([ctx]
   (turtle
    ctx
    (* 0.5 (window/width))
    (* 0.5 (window/height))
    0
    10)))

(defn drop-turtle
  [turt]
  (.moveTo (:ctx turt) (:x turt) (:y turt))
  turt)

(defn forward
  [{:keys [ctx x y angle step-size] :as turt}]
  (let [new-x (+ x (* step-size (.cos js/Math angle)))
        new-y (+ y (* step-size (.sin js/Math angle)))]
    (.lineTo (:ctx turt) new-x new-y)
    (assoc turt :x new-x :y new-y)))

(defn rotate-rand
  [turt]
  (update turt :angle #(+ %
                          (* 2 pi)
                          (* 0.01 (+ 1 (rand-int 100))))))

(defn rotate-left
  [turt]
  (update turt :angle #(- % (* 0.5 pi))))

(defn rotate-right
  [turt]
  (update turt :angle #(+ % (* 0.5 pi))))

(defn draw
  [ctx]
  (let [turt (turtle ctx)]
    (drop-turtle turt)
    (loop [i 1000
           t turt]
      (when (<= 0 i)
        (recur (dec i)
               ((rand-nth [forward rotate-right rotate-left
                           rotate-rand]) t))))
    (.stroke ctx)))
