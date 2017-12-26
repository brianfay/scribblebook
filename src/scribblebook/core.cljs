(ns scribblebook.core
  (:require [reagent.core :as reagent]
            [scribblebook.window :as window]
            [scribblebook.turtle :as turtle]
            [scribblebook.scribbles.scribble :as scribble]))

(enable-console-print!)

(defn set-canvas-style [cnv]
  (set! (.-width cnv) (window/width))
  (set! (.-height cnv) (window/height)))

(defn set-stroke-style [ctx]
  (set! (.-strokeStyle ctx) "#000000")
  (set! (.-lineWidth ctx) 2)
  (set! (.-lineCap ctx) "round"))

(defn draw-turtle-canvas [cnv]
  (let [ctx (.getContext cnv "2d")]
    (set-canvas-style cnv)
    (set-stroke-style ctx)
    (scribble/draw ctx)))

(defn turtle-canvas []
  (let [dom-node (reagent/atom nil)]
    (reagent/create-class
     {:component-did-update (fn [this] (draw-turtle-canvas @dom-node))
      :reagent-render (fn [] [:canvas {:style {:width (window/width)
                                               :height (window/height)
                                               :position "absolute"
                                               :left 0
                                               :top 0}}])
      :component-did-mount (fn [this] (let [this-node (reagent/dom-node this)]
                                        (reset! dom-node this-node)
                                        (draw-turtle-canvas this-node)))})))

(defn app-container []
  [:div
   [turtle-canvas]])

(defn ^:export run []
  (reagent/render [app-container]
    (.getElementById js/document "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (run)
)

(defonce start (run))
