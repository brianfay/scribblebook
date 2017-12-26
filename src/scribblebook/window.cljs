(ns scribblebook.window
  (:require [reagent.core :as reagent]))

(defonce size (reagent/atom {:width  (.-innerWidth js/window)
                             :height (.-innerHeight js/window)}))

(defn on-resize [e]
  (reset! size {:width  (.-outerWidth js/window)
                       :height (.-outerHeight js/window)}))

(defn width []
  (:width @size))

(defn height []
  (:height @size))

(defonce resize-listener
  (.addEventListener js/window "resize" on-resize))
