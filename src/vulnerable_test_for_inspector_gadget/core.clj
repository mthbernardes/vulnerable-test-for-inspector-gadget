(ns vulnerable-test-for-inspector-gadget.core
  (:require [clojure.xml :as xml]
            [schema.core :as s]
            [clojure.java.shell :as shell])
  (:import (org.apache.commons.io IOUtils)))

(defn parse
  [data]
  (try
    (with-open [istream (IOUtils/toInputStream data "UTF-8")]
      (xml/parse istream))
    (catch Exception _e
      {:type :validation
       :code :invalid-svg-file})))

(-> "/home/dpr/image.svg" slurp xml/parse read-string)

(defn execute-command [command]
  (->> command
       (shell/sh "bash" "-c")
       :exit))
